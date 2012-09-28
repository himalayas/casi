#!/bin/bash
LOCKPATH="/tmp"
cleanup() {
        shflock_cleanhook
        cd $LOCKPATH
        [ -e lock.pid ] || exit
        read pid >/dev/null 2>&1 <lock.pid
        if [ -n "$pid" ]; then
            if [ "$pid" == "$$" ]; then
                rm -f lock.$pid
                rm -f lock.pid
                exit
            fi
        fi
        exit
}

#  trap EXIT ?
trap 'cleanup' HUP INT TERM
getlock() {
        oldpath=`pwd`
        cd $LOCKPATH
        while
                echo $$ > lock.$$
                [ -e lock.pid ]
        do
                rm lock.$$
                read pid >/dev/null 2>&1 <lock.pid
                if [ -n "$pid" ]; then
                        if [ -e /proc/$pid ]; then
                                cd $oldpath
                                return 1 # Lock is taken by others
                        else
                                #unsafe: rm -f lock.$pid
                                echo WARN: please delete stale lock.pid by HAND.
                                return 2
                        fi
                else
                        # sleep some seconds,then back to 'while' loop
                        # 11 is a prime number, $$ as a random.
                        echo sleep $(( $$ % 11 ))
                        sleep $(( $$ % 11 ))
                fi
        done
        # 'ln -s' is an atom op.
        ln -s lock.$$ lock.pid >/dev/null 2>&1
        if [ $? -eq 0 ]; then
                cd $oldpath
                return 0 # We got the lock
        else
                [ -e lock.pid ] || echo WARN: please delete hanging lock.pid by HAND.
                cd $oldpath
                return 3 # Lock is probably taken by others.
        fi
}

putlock () {
    oldpath=`pwd`
    cd $LOCKPATH && rm -f lock.$$ lock.pid
    cd $oldpath
}

## your code
shflock_cleanhook() {
    echo i\'m a hook.
}
while true; do
    while ! getlock; do
        #echo wait a second...
        sleep 1
    done


    echo \[$$\] `date` ,now hold lock for 3 seconds...
    sleep 3
    #echo putlock
    putlock
    sleep 1 # yield
done
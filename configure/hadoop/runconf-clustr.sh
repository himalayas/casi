#!/usr/bin/env bash

##################################################################
#pssh 在多个主机上并行地运行命令。
#pscp 把文件并行地复制到多个主机上。
#prsync 通过 rsync 协议把文件高效地并行复制到多个主机上。
#pslurp 把文件并行地从多个远程主机复制到中心主机上。
#pnuke 并行地在多个远程主机上杀死进程。
###################################################################

#edit /etc/hostsi
# -eq 只能用于 整数比较
#字符串比较要用 =
if [ $1 = "hosts" ]
then	
	echo "conf hosts......"
	prsync -h hadoop-hosts-list -l root -A  hosts  /etc/hosts
fi

#edit hadoop-site.xml
if [ $1 = "slave" ] 
then
	echo "conf slave"
#	echo "start rm file on target hosts......"
#	pssh -h hadoop-hosts-list -l hadoop -P  "rm -rf /home/a/soft/hadoop-0.19.2/conf/hadoop-site.xml"
#	echo "start pscp file to hosts......"
	prsync -h hadoop-hosts-list -l hadoop /home/a/soft/hadoop/conf/hdfs-site.xml  /home/a/soft/hadoop/conf/hdfs-site.xml
	prsync -h hadoop-hosts-list -l hadoop /home/a/soft/hadoop/conf/core-site.xml  /home/a/soft/hadoop/conf/core-site.xml
	prsync -h hadoop-hosts-list -l hadoop /home/a/soft/hadoop/conf/mapred-site.xml  /home/a/soft/hadoop/conf/mapred-site.xml

fi
if [ $1 = "distribution" ]
then
	pssh -h hadoop-hosts-list -l hadoop -P  "rm -rf /home/a/soft/hadoop"
	pscp -h hadoop-hosts-list -l hadoop /home/a/soft/hadoop.tar.gz  /home/a/soft/
	pssh -h hadoop-hosts-list -l hadoop "tar jvxf /home/a/soft/hadoop.tar.gz"
fi
#pscp -h masters_hosts -l hadoop /home/a/soft/hadoop-0.19.2/conf/slaves  /home/a/soft/hadoop-0.19.2/conf/slaves

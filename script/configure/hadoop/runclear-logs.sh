#!/usr/bin/env bash

##################################################################
#pssh 在多个主机上并行地运行命令。
#pscp 把文件并行地复制到多个主机上。
#prsync 通过 rsync 协议把文件高效地并行复制到多个主机上。
#pslurp 把文件并行地从多个远程主机复制到中心主机上。
#pnuke 并行地在多个远程主机上杀死进程。
###################################################################

#edit /etc/hosts
#pssh -h hadoop_hosts -l root -A -P  "echo '10.13.120.241	slave2' >> /etc/hosts"
#edit /home/a/soft/hadoop-0.19.2/conf/slaves
#delete   /home/a/soft/hadoop-0.19.2/conf/hadoop-site.xml
echo "start clear logs on target hosts......"
pssh -h hadoop-hosts-list -l hadoop -P  "rm -rf /home/a/soft/hadoop-0.19.2/logs/*"
#echo "start pscp file to hosts......"
#pscp -h hadoop_hosts -l hadoop /home/a/soft/hadoop-0.19.2/conf/hadoop-site.xml  /home/a/soft/hadoop-0.19.2/conf/

#pssh -h masters_hosts -l hadoop -P  "rm -rf /home/a/soft/hadoop-0.19.2/conf/slaves"
#pscp -h masters_hosts -l hadoop /home/a/soft/hadoop-0.19.2/conf/slaves  /home/a/soft/hadoop-0.19.2/conf/slaves

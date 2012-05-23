#!/usr/bin/env bash

##################################################################
#pssh 在多个主机上并行地运行命令。
#pscp 把文件并行地复制到多个主机上。
#prsync 通过 rsync 协议把文件高效地并行复制到多个主机上。
#pslurp 把文件并行地从多个远程主机复制到中心主机上。
#pnuke 并行地在多个远程主机上杀死进程。
###################################################################

echo "start delete ... ... /user/hadoop"
hadoop fs -rmr /user/hadoop
echo "start delete ... .../user/hive"
hadoop fs -rmr /user/hive
echo "start delete ... .../home/a/hadoop_tmp_dir"
pssh -h hadoop-hosts-list -l hadoop -P  "rm -rf /home/a/hadoop_tmp_dir"
echo "format namenode ... ..."
hadoop namenode -format

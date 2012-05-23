#!/usr/bin/env bash
#hadoop fs -rmr input
#hadoop fs -rmr output
#echo 'starting gizp......'
#gzip -1 /home/hadoop/project/hadoop.txt
#echo 'starting -put file......'
#hadoop fs -put /home/hadoop/data/hadoop.txt  input/hadoop.txt
#hadoop fs -put /home/hadoop/date/hadoop_hive.txt  input/hadoop_hive.txt
echo "starting job......!"
hadoop jar /home/hadoop/IdeaProjects/hadoop/target/hadoop-1.0.jar com.casi.hadoop.job.LogJob

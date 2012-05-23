package com.casi.hadoop.maper;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * User: hadoop
 * Date: 11-12-28
 * Time: 下午12:47
 */
public class LogMaper extends MapReduceBase implements Mapper<Text,Text,Text,Text> {

    @Override
    public void map(Text key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        output.collect(key,value);
    }
}

package com.casi.hadoop.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: hadoop
 * Date: 11-12-28
 * Time: 下午12:49
 */
public class LogReducer extends MapReduceBase implements Reducer<Text,Text,Text,LongWritable> {
    @Override
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, LongWritable> output, Reporter reporter) throws IOException {
        long i=0l;
        while(values.hasNext()){
                i++;
                values.next();
          }
        output.collect(key,new LongWritable(i));
    }
}

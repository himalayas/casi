package com.casi.hadoop.job;

import com.casi.hadoop.maper.LogMaper;
import com.casi.hadoop.reducer.LogReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Progressable;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * User: hadoop
 * Date: 11-12-28
 * Time: 下午12:44
 */
public class LogJob extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        JobConf conf = new JobConf(getConf(), LogJob.class);

        List<Path> paths = new ArrayList<Path>();
        paths.add(new Path("input0"));
        paths.add(new Path("input1"));
        paths.add(new Path("input2"));

        KeyValueTextInputFormat.setInputPaths(conf, paths.get(0), paths.get(1), paths.get(2));
        TextOutputFormat.setOutputPath(conf, new Path("output"+ System.currentTimeMillis()));
        conf.set("key.value.separator.in.input.line","|");
        conf.setInputFormat(KeyValueTextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        conf.setCompressMapOutput(true);

        conf.setNumReduceTasks(2);
        conf.setNumMapTasks(2);

        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(Text.class);

        conf.setMapperClass(LogMaper.class);
        conf.setReducerClass(LogReducer.class);

        JobClient.runJob(conf);
        return 0;
    }

    public static void main(String[] args) throws Exception {

        String[] locals = {"/home/hadoop/test-data/input/d0/d0_file0_0.txt",
                           "/home/hadoop/test-data/input/d1/d1_file0_0.txt",
                           "/home/hadoop/test-data/input/d2/d2_file0_0.txt"
        };

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        System.out.println("delete input* ......");
        fs.delete(new Path("input*"), true);
        System.out.println("delete output ......");
        fs.delete(new Path("output"),true);

        for (int i=0;i<locals.length;i++) {
            String remot = "input"+i;
            InputStream is = new BufferedInputStream(new FileInputStream(locals[i]));
            fs = FileSystem.get(URI.create(remot), conf, "hadoop");
            OutputStream os=fs.create(new Path(remot), new Progressable() {
                @Override
                public void progress() {
                    System.out.print(".");
                }
            });
            System.out.println("Step "+i+"  is starting;");
            IOUtils.copyBytes(is, os, 4096, true);
            System.out.println("Step "+i+"  is end;");
        }
        int res = ToolRunner.run(new LogJob(), args);
        System.exit(res);
    }
}

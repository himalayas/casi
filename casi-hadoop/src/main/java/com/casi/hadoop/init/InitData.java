package com.casi.hadoop.init;

import java.io.*;

/**
 * User: hadoop
 * Date: 11-12-28
 * Time: 下午4:06
 */
public class InitData {
    public static void main(String[] args) throws IOException {
        String[] locals = {
                "/home/hadoop/test-data/input/d0/",
                "/home/hadoop/test-data/input/d1/",
                "/home/hadoop/test-data/input/d2/"
        };
//        for(String s:locals){
//            File f=new File(s);
//            f.mkdirs();
//
//        }
        File [ ] files={
                        new File(locals[0]+"d0_file0_0.txt"),
                        new File(locals[1]+"d1_file0_0.txt"),
                        new File(locals[2]+"d2_file0_0.txt")
        };
        for(File f:files){
             // f.mkdirs();
              f.createNewFile();
        }
        OutputStream [] os={
                new BufferedOutputStream(new FileOutputStream(files[0]),1024),
                new BufferedOutputStream(new FileOutputStream(files[1]),1024),
                new BufferedOutputStream(new FileOutputStream(files[2]),1024)
        };
        for(int i=0;i<1000000000;i++){
            Double  d=Math.random()*100;
            int ds=d.intValue()%3;
            String text="d"+ds+"_"+d.intValue()+"|"+d.toString()+"\t\r";
            os[ds].write(text.getBytes());
            if(i%1000==0){
                os[0].flush();
                os[1].flush();
                os[2].flush();
            }
        }
    }
}

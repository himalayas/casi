package com.casi.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 12-10-15
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
public class JavaDemo2 {
    public static void main(String[] args) throws IOException {
      String str="123456=000"+(char)2+" abcd=ab";
        System.out.println(str);
        str=str.replace((char)2,(char)10);
        System.out.println(str);
        Properties p=new Properties();
        p.load(new ByteArrayInputStream(str.getBytes()));
        p.load(new ByteArrayInputStream("abc=123".getBytes()));
        System.out.println(p.getProperty("abcd"));

    }
}

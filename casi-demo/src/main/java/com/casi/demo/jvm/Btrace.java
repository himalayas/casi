package com.casi.demo.jvm;

/**
 * Created with IntelliJ IDEA.
 * User: kevin
 * Date: 13-3-4
 * Time: 下午12:57
 * To change this template use File | Settings | File Templates.
 */
import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;
@BTrace public class Btrace{
        @OnMethod(
                clazz="java.util.zip.Inflater",
                method="/.*/"
        )
        public static void traceExecute(@ProbeMethodName String methodName){
            println(concat("who call Inflater.",methodName));
            jstack();
        }
}

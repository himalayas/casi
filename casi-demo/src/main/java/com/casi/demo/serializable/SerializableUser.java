package com.casi.demo.serializable;

import java.io.*;

/**
 * User: Think
 * Date: 12-10-22
 * Time: 下午8:21
 */
public class SerializableUser {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File serializableFile=new File("/serializableFile.txt");
        if(serializableFile.exists())
            serializableFile.delete();
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(serializableFile));
        User user=new User();
        user.setAge(100);
        user.setName("xiujguo");
        oos.writeObject(user);
        oos.close();

        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(serializableFile));
        ois.readObject();

    }
}

package com.casi.demo.serializable;

import java.io.*;

/**
 * User: Think
 * Date: 12-10-22
 * Time: 下午8:21
 */
public class SerializableUser {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        File serializableFile=new File("/serializableFile.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        if(serializableFile.exists())
//            serializableFile.delete();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        User user = new User(121);
        user.setAge(100);
        user.setName("xiujguo");
        oos.writeObject(user);
        oos.close();

//        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(serializableFile));
//        User u= (User) ois.readObject();
//        System.out.println(byteArrayOutputStream.toByteArray().length);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        User u = (User) ois.readObject();
        System.out.println(u.getName());

    }
}

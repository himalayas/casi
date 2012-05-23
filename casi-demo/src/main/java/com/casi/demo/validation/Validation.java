package com.casi.demo.validation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.*;

/**
 * User: xiujguo
 * Date: 12-4-6
 * Time: 下午1:23
 */
public class Validation {
    //validation framwork


    public static void valiImg(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        try {
            BufferedImage sourceImg = null;
            sourceImg = ImageIO.read(is);
            System.out.println("width = " + sourceImg.getWidth() + "height = " + sourceImg.getHeight());
        } catch (Exception e) {
            System.err.print(e);
        }
    }


    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Jellyfish.jpg");
        valiImg(file);
    }
}

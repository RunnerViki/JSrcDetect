package com.viki.java.utils.JavaPImplements;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Viki on 2017/5/16.
 * Function: TODO
 */
public class FileHeadGern {

    public static void main(String[] args) {
        System.out.println(run(new File("E:\\viki\\JSrcDetect\\src\\main\\java\\com\\viki\\java\\utils\\JavaPImplements\\Test.java")));
//        new com.sun.java.util.jar.pack.ClassReader()
    }

    /*
    * 计算文件头
    * */
    public static String run(File file){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileChannel.open(file.toPath()).read(byteBuffer);
            stringBuilder.append("Classfile ").append(file.getAbsolutePath()).append("\n")
                    .append("\tLast modified ").append(simpleDateFormat.format(new Date(file.lastModified()))).append("; size ").append(file.length()).append(" bytes\n")
                    .append("MD5 checksum ").append(""/*Signature.getInstance("MD5withRSA").sign(byteBuffer.array(), 0, (int) file.length())*/).append("\n")
                    .append("Compiled from \"").append(file.getName()).append("\"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}

package com.viki.java.nio.file;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Viki on 2017/5/18.
 * Function: TODO
 */
public class FilesTest {

    Path path = Paths.get("E:\\viki\\个人源码");

    /*
    * 批量获取文件属性接口，与平台无关，与文件系统无关
    * */
    @Test
    public void t1(){
        try {
            System.out.println(Files.readAttributes(path, "*"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

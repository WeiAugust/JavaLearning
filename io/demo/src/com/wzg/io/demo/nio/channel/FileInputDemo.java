package com.wzg.io.demo.nio.channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName FileInputDemo
 * @Description TODO
 * @Author wzg
 * @Date 2020/9/24 22:37
 * @Version 1.0
 **/
public class FileInputDemo {

    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("src\\test.txt");

        FileChannel fileChannel = fin.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fileChannel.read(buffer);
        buffer.flip();
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.print((char)b);
        }
        fileChannel.close();
    }
}

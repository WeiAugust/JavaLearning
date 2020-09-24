package com.wzg.io.demo.nio.buffer;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName BufferDemo
 * @Description TODO
 * @Author wzg
 * @Date 2020/9/24 11:28
 * @Version 1.0
 **/
public class BufferDemo {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src\\test.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        output("init", byteBuffer);

        fileChannel.read(byteBuffer);
        output("do read()", byteBuffer);

        byteBuffer.flip();
        output("do flip()",byteBuffer);

        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.print((char)b);
        }
        System.out.println();
        byteBuffer.clear();
        output("do clear", byteBuffer);
        fileInputStream.close();

    }

    public static void output(String step, ByteBuffer buffer) {
        System.out.println(step + ":");
        System.out.println("capacity: " + buffer.capacity() + ", ");
        System.out.println("position: " + buffer.position() + ", ");
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }
}

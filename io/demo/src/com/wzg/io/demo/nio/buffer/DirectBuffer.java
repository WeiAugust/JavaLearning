package com.wzg.io.demo.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName DirectBuffer
 * @Description TODO
 * @Author wzg
 * @Date 2020/9/24 22:30
 * @Version 1.0
 **/
public class DirectBuffer {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInput = new FileInputStream("src\\test.txt");
        FileChannel fileChannel = fileInput.getChannel();

        String outFile = String.format("src\\testcopy.txt");
        FileOutputStream fileOutput = new FileOutputStream(outFile);
        FileChannel fout = fileOutput.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            buffer.clear();
            int r = fileChannel.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fout.write(buffer);
        }
    }
}

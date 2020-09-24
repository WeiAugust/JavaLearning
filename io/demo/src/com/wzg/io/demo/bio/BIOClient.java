package com.wzg.io.demo.bio;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * @ClassName BIOClient
 * @Description BIO client
 * @Author wzg
 * @Date 2020/9/24 11:06
 * @Version 1.0
 **/
public class BIOClient {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8080);
        OutputStream output = client.getOutputStream();
        String name = UUID.randomUUID().toString();
        System.out.println("client send data: "+ name);
        output.write(name.getBytes());
        output.close();
        client.close();
    }
}

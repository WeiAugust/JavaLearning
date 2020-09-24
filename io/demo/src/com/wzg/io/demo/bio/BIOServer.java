package com.wzg.io.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName BIOServer
 * @Description bio server同步阻塞IO模型
 * @Author wzg
 * @Date 2020/9/24 10:57
 * @Version 1.0
 **/
public class BIOServer {

    ServerSocket server;

    public BIOServer(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("BIO server started, listening port: "+ port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            try (Socket socket = server.accept();
                 InputStream input = socket.getInputStream()) {
               byte[] buffer = new byte[1024];
               int len = input.read(buffer);
               if (len > 0) {
                   System.out.println("get message: "+ new String(buffer));
               }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BIOServer(8080).listen();
    }
}

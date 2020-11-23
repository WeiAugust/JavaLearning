package cool.wzg.netty.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName Client
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/18 21:06
 * @Version 1.0
 **/
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        socket.getOutputStream().write("hello server".getBytes());
        socket.getOutputStream().flush();
        System.out.println("send data to server");
        byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);
        System.out.println("received data from server: "+new String(bytes));
        socket.close();

    }
}

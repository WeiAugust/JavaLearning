package cool.wzg.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Server
 * @Description TODO
 * @Author wzg
 * @Date 2020/11/18 21:07
 * @Version 1.0
 **/
public class Server {

    static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("waiting for connection");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("connected");
            service.submit(new Thread(()-> handler(socket)));
        }
    }

    private static void handler(Socket socket) {
        System.out.println("Thread: " + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        System.out.println("read data...");
        int read = 0;
        try {
            read = socket.getInputStream().read(bytes);
            if (read != -1) {
                System.out.println("received data: " + new String(bytes, 0, read));
            }
            socket.getOutputStream().write("Hello client".getBytes());
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

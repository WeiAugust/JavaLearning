package byteDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName byteDemo.ByteStreams
 * @Description 读取in.txt,写入out.txt 字节
 * @Author wzg
 * @Date 2020/8/16 21:28
 * @Version 1.0
 **/
public class ByteStreams {

    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream("src/byteDemo/in.txt");
                FileOutputStream outputStream = new FileOutputStream("src/byteDemo/out.txt")){
            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        }
    }
}

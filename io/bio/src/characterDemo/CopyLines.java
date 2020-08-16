package characterDemo;

import java.io.*;

/**
 * @ClassName CopyLines
 * @Description 整行复制
 * @Author wzg
 * @Date 2020/8/16 21:55
 * @Version 1.0
 **/
public class CopyLines {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/characterDemo/reader.txt"));
             PrintWriter writer = new PrintWriter(new FileWriter("src/characterDemo/writer.txt"))){
            String s;
            while ((s = reader.readLine()) != null) {
                writer.println(s);
            }
        }
    }
}

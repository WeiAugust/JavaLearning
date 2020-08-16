package characterDemo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName CharacterStreams
 * @Description 字符
 * @Author wzg
 * @Date 2020/8/16 21:46
 * @Version 1.0
 **/
public class CharacterStreams {

    public static void main(String[] args) throws IOException {
        try (FileReader reader = new FileReader("src/characterDemo/reader.txt");
             FileWriter writer = new FileWriter("src/characterDemo/writer.txt")){
            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        }
    }
}

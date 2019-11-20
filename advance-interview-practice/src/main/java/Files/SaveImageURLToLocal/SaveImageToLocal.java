package Files.SaveImageURLToLocal;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by omprakash.yadav on 06/03/16.
 */
public class SaveImageToLocal {

    void saveImage(String url) throws IOException {

        //logic
        URL url_ = new URL(url);
        String fileName = url_.getFile();
        String destination = "/tmp" + fileName.substring(fileName.lastIndexOf("/"));
        System.out.println(destination);

        try {
            InputStream inputStream = url_.openStream();
            OutputStream outputStream = new FileOutputStream(destination);

            byte[] b = new byte[2048];
            int length;

            while ((length = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

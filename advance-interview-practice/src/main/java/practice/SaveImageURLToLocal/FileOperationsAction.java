package practice.SaveImageURLToLocal;

import java.io.*;
import java.net.URL;

/**
 * Created by omprakash.yadav on 06/03/16.
 */
public class FileOperationsAction {

  public void saveFile(String saveToLocation, String url, FileType fileType) throws IOException{
        //logic
        URL url_ = new URL(url);
        String fileName = url_.getFile();
        String destination = saveToLocation + fileName.substring(fileName.lastIndexOf("/"));
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
  public void moveFile(String savedLocation, String finalLocation, FileType fileType)throws IOException{

      File file = new File(savedLocation);

      try{
         file.renameTo(new File(finalLocation + file.getName()));
          System.out.println("file moved to : "+ finalLocation);
      }catch (Exception e){
          e.printStackTrace();
      }
  }
  public void deleteFile(String savedLocation, FileType fileType) throws IOException{
      File file = new File(savedLocation);

      try{
          file.delete();
          System.out.println("deleted from : "+ savedLocation);
      }catch (Exception e){
          e.printStackTrace();
      }
  }


}

package practice.SaveImageURLToLocal;

import java.io.IOException;

/**
 * Created by omprakash.yadav on 06/03/16.
 */
public class SaveImageWithUrl {

    public static void main(String[] args) throws IOException{

        String ImageURL = Files.ImageUrl1;
        String ImageURL_ = Files.ImageUrl2;
        FileOperationsAction fileOperationsAction = new FileOperationsAction();

//        fileOperationsAction.saveFile(Files.SaveTo, ImageURL, FileType.PNG);
//        fileOperationsAction.saveFile(Files.SaveTo, ImageURL_, FileType.PNG);

        System.out.println("Image saved to tmp location");
        //move
        fileOperationsAction.moveFile(Files.SaveTo+ImageURL_.substring(ImageURL.lastIndexOf("/")), Files.MoveTo, FileType.PNG);

        //delete
       // fileOperationsAction.deleteFile(Files.SaveTo+ImageURL.substring((ImageURL.lastIndexOf("/"))),FileType.PNG);



    }
}

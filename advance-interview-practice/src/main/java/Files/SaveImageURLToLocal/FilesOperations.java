package Files.SaveImageURLToLocal;

/**
 * Created by omprakash.yadav on 06/03/16.
 */
public interface FilesOperations{

    public void moveFile(String s , FileType fileType);
    public void deleteFile(String s, FileType fileType);
    public void saveFile(String s, FileType fileType);

}

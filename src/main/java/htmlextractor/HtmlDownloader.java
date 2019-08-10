package htmlextractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class HtmlDownloader{

    
    


    static public File toText(String s) {
        File temp = null;
        
        try {
            temp = File.createTempFile("temp", ".txt");
            temp.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
		}
        
        try (InputStream inputStream = new URL(s).openStream()){
            Files.copy(inputStream, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e);
        }
        return temp;
    }

}
package htmlextractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class HtmlDownloader{

    
    


    // static public File toText(String s) {
    //     File temp = null;
       

    //     try {
    //         temp = File.createTempFile("temp", ".txt");
    //         System.out.println("created temp file at" + temp.getAbsolutePath()); // create Logger
    //         temp.deleteOnExit();
    //     } catch (IOException e) {
    //         e.printStackTrace();
	// 	}
        
    //     try (InputStream inputStream = new URL(s).openStream()){
    //         System.out.println("downloading from... " + s);
    //         Files.copy(inputStream, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
    //         System.out.println("copied html to file"); // create logger
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    //     return temp;
    // }

    static public Document toDocument(String s){
        
        File temp = null;
       

        try {
            temp = File.createTempFile("temp", ".txt");
            System.out.println("created temp file at" + temp.getAbsolutePath()); // create Logger
            temp.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc = new Document(temp.getAbsolutePath());
        try{
            doc = Jsoup.connect(s).get();
            return doc;
            }catch (Exception e){
                System.out.println(e.getCause());
            }
        return doc;
    }

}
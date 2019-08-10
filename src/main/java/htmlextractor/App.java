package htmlextractor;

import java.util.*;
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println("enter the url to extract data from:");
        String s = "";
        try(Scanner scan = new Scanner(System.in)){
            s = scan.nextLine();
        }catch (Exception e){
            System.out.println(e);
        }

        HtmlReader.readToString(HtmlDownloader.toText(s));

    }
}

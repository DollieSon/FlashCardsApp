import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        FileManager File2 = FileHandlerFactory.getFileManager("src\\Sample.txt");
        System.out.println( File2);
        try{
            System.out.println(File2.GetString());
            File2.WriteLine("Gibbie");
            System.out.println(File2.GetString());
        }catch (IOException e){

        }
        System.out.println("HEllo world");
    }
}
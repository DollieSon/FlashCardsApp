// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        FileManager File1 = FileHandlerFactory.getFileManager("src\\Quiz1.txt");
        FileManager File2 = FileHandlerFactory.getFileManager("src\\Sample.txt");
        FileManager File3 = FileHandlerFactory.getFileManager("src\\Quiz1.txt");
        System.out.println( File1);
        System.out.println( File2);
        System.out.println( File3);

        System.out.println("HEllo world");
    }
}
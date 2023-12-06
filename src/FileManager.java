import java.io.*;

public class FileManager {
    private BufferedReader Reader;
    private BufferedWriter Writer;

    public FileManager(String Location) throws FileNotFoundException,IOException{
        try{
            Reader = new BufferedReader(new FileReader(Location));
            Writer = new BufferedWriter(new FileWriter(Location));
        }catch(FileNotFoundException e){
            throw new FileNotFoundException("Error In Buffered Reader");
        }catch (IOException e){
            throw new IOException("Error in Writer");
        }
        System.out.println("FM created");
    }


}

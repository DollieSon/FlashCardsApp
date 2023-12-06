import java.io.*;

public class FileManager {
    private BufferedReader Reader;
    private BufferedWriter Writer;

    private String Location;

    public FileManager(String Location) throws FileNotFoundException{
        this.Location = Location;
        System.out.println("FM created");
    }

    public String GetString() throws IOException {
        Reader = new BufferedReader( new FileReader(Location));
        try{
            return Reader.readLine();
        }catch (IOException e){
            throw new IOException("Error REading Line");
        }
    }

    public Boolean WriteLine(String line) throws IOException{
        Writer = new BufferedWriter(new FileWriter(Location));
        try{
            Writer.write(line);
            Writer.flush();
        }catch (IOException e){
            throw new IOException("Error Writing to File");
        }
        return true;
    }

}

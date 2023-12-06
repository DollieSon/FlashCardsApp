import java.util.HashMap;
import java.util.Map;

public class FileHandlerFactory {
    private static final Map<String,FileManager> Files = new HashMap<>();

    public static FileManager getFileManager(String Location){
        if(Files.get(Location) == null){
            FileManager Fm = null;
            try{
                Fm = new FileManager(Location);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            Files.put(Location,Fm);
        }
        return Files.get(Location);
    }
}

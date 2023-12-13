package FolderUser;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionOfFolder {
    private ArrayList<Folder> FolderList;

    private String FolderName;

    public CollectionOfFolder(){
        FolderList = new ArrayList<>();
    }

    public void setFlolderName(String name){
        this.FolderName = name;
    }
    public String getFlolderName(){
        return this.FolderName;
    }

    public ArrayList<Folder> getFolderList(){
        return this.FolderList;
    }

    public void addFolder(Folder folder){
        this.FolderList.add(folder);
    }
    public void removeFolder(Folder folder){
        this.FolderList.remove(folder);
    }

}

package FolderUser;

public class user {
    private String name;
    private String password;

    public user(String name, String password){
        this.name = name;
        this.password = password;
    }

    public void setName(String name){
        this.name = name;

    }

    public String getName(){
        return this.name;
    }
}

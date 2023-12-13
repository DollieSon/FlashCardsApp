package UIs;
import FolderUser.Folder;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.util.Scanner;

import FolderUser.CollectionOfFolder;

import javax.swing.*;
import java.awt.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;



public class UserPage extends JFrame{
    private JPanel MainFrame;
    private JScrollPane JSPUserPage;
    private JPanel JPUserPage;
    private JLabel AppTitle;
    private JPanel JPHeaderPanel;
    private JScrollPane JSPFolderPanel;
    private JPanel JPFolderContainer;

    private static UserPage UserPageInstance = null;

    public static Folder openfolder = null;

    public static  UserPage getInstance() {
        if (UserPageInstance == null) {
            UserPageInstance = new UserPage();
        }
        return UserPageInstance;
    }

    public static UserPage refreshInstance() {

        UserPageInstance = new UserPage();

        return UserPageInstance;

    }

    private UserPage(){
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        CollectionOfFolder mainpage = new CollectionOfFolder();
        Scanner sc = new Scanner(System.in);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("UserFolder"), path -> Files.isDirectory(path))) {
            for (Path path : stream) {
                Folder folder = new Folder((path.getFileName()).toString());
                folder.setDirectory(path.toString());
                mainpage.addFolder(folder);
            }
        } catch (IOException e) {

        }


        int n = 1;
        while(n>0 && n<5){
            System.out.print("Current Folders availabe: ");
            if(mainpage.getFolderList().isEmpty()){
                System.out.println("none");
            }
            else{
                for(Folder f: mainpage.getFolderList()){
                    System.out.print(f.getName()+" "+f.getDirectory()+" | ");
                }
            }
            System.out.print("\n1. add Folder, 2. delete a Folder, 3. Rename a Folder 4. Open Folder: ");
            n = sc.nextInt();
            sc.nextLine();
            if(n == 1){
                String name;
                System.out.print("Add\nEnter name: ");
                name = sc.nextLine();

                String folderPath = "UserFolder\\"+name;

                // Create a Path object
                Path folder = Paths.get(folderPath);


                // Check if the folder does not exist, then create it
                if (!Files.exists(folder)) {
                    try {
                        Files.createDirectory(folder);
                        System.out.println("Folder created successfully!");
                    } catch (IOException e) {
                        System.err.println("Failed to create folder: " + e.getMessage());
                    }
                } else {
                    System.out.println("Folder already exists.");
                }
                Folder newfolder = new Folder(name);
                newfolder.setDirectory(folderPath);
                mainpage.addFolder(newfolder);
            }
            else if(n == 2){
                System.out.print("Delete\nEnter index: ");
                int index = sc.nextInt();

                String path = "UserFolder\\"+mainpage.getFolderList().get(index).getName();
                System.out.println(path);

                File folder = new File(path);
                File[] contents = folder.listFiles();

                if(contents.length == 0){
                    folder.delete();
                }
                else{
                    System.out.println("nn");
                    for(File cc: contents){
                        cc.delete();
                    }
                }
                folder.delete();


                mainpage.getFolderList().remove(index);
            }
            else if(n == 3){
                System.out.print("Edit\nEnter index: ");
                int index = sc.nextInt();
                System.out.print("Enter name to replace: ");
                sc.nextLine();
                String repname = sc.nextLine();

                String path = "UserFolder\\"+mainpage.getFolderList().get(index).getName();

                File folder = new File(path);
                File repfolder = new File("UserFolder\\"+repname);
                folder.renameTo(repfolder);
                mainpage.getFolderList().get(index).setName(repname);
                mainpage.getFolderList().get(index).setDirectory("UserFolder\\"+repname);
            }
            else if(n == 4){
                System.out.println("Enter index to open: ");
                int indx = sc.nextInt();
                openfolder = mainpage.getFolderList().get(indx);

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                FolderForQuiz folderquiz;
                if(FolderForQuiz.getInstance() != null){
                    FolderForQuiz.getInstance().setVisible(false);
                    folderquiz = FolderForQuiz.refreshInstance();
                }
                else{
                    folderquiz = FolderForQuiz.getInstance();
                }
                folderquiz.setContentPane(folderquiz.JPFolderContainerPanel);
                folderquiz.setSize(1200, 750);
                folderquiz.setResizable(false);
                folderquiz.folderfirst = openfolder;
                folderquiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
                folderquiz.setTitle("FolderUser.Folder");
                folderquiz.setVisible(true);
            }
            else{
                System.out.println("Exit");
            }
            System.out.println("\n");
        }
    }
}

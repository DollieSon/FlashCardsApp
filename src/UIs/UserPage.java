package UIs;
import FolderUser.Folder;
import java.util.Scanner;

import FolderUser.CollectionOfFolder;

import javax.swing.*;
import java.awt.*;

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

        int n = 1;
        while(n>0 && n<4){
            System.out.print("Current Folders availabe: ");
            if(mainpage.getFolderList().isEmpty()){
                System.out.println("none");
            }
            else{
                for(Folder f: mainpage.getFolderList()){
                    System.out.print(f.getName()+" ");
                }
            }
            System.out.print("\n1. add Folder, 2. delete a Folder, 3. Rename a Folder 4. Open Folder: ");
            n = sc.nextInt();
            sc.nextLine();
            if(n == 1){
                String name;
                System.out.print("Add\nEnter name: ");
                name = sc.nextLine();

                Folder createdFolder = new Folder(name);

                mainpage.addFolder(createdFolder);

            }
            else if(n == 2){
                System.out.print("Delete\nEnter index: ");
                int index = sc.nextInt();
                mainpage.getFolderList().remove(index);
            }
            else if(n == 3){
                System.out.print("Edit\nEnter index: ");
                int index = sc.nextInt();
                System.out.print("Enter name to replace: ");
                sc.nextLine();
                String repname = sc.nextLine();
                mainpage.getFolderList().get(index).setName(repname);
            }
            else if(n == 4){
                System.out.println("Enter index to open: ");
                int indx = sc.nextInt();
                openfolder = mainpage.getFolderList().get(indx);
                System.out.println();

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                FolderForQuiz folderquiz = FolderForQuiz.getInstance();
                folderquiz.setContentPane(folderquiz.JPFolderContainerPanel);
                folderquiz.setSize(1200, 750);
                folderquiz.setResizable(false);
                folderquiz.folderfirst = openfolder;
                folderquiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
                folderquiz.setTitle("FolderUser.Folder");
                folderquiz.setVisible(true);
            }
            System.out.println("\n");
        }
    }
}

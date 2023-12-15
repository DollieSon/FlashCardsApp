package UIs;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import FolderUser.CollectionOfFolder;
import FolderUser.Folder;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class UserPage extends JFrame{
    public JPanel MainFrame;
    private JPanel JPUserPage;
    private JLabel AppTitle;
    private JPanel JPHeaderPanel;
    private JScrollPane JSPFolderPanel;
    private JPanel JPFolderPanel;
    private JButton createFolderButton;
    private JPanel JPButtonCreat;
    private JPanel JPFolderContainer;

    private ArrayList<JButton> deleteButtonList = new ArrayList<>();
    private ArrayList<JButton> openButtonList = new ArrayList<>();
    private ArrayList<JButton> renameButtonList = new ArrayList<>();
    private static UserPage UserPageInstance = null;

    public static Folder openfolder = null;

    private JLabel displabell;
    ImageIcon image;

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
        JPHeaderPanel.setLayout(new BoxLayout(JPHeaderPanel,BoxLayout.X_AXIS));
        image = new ImageIcon(getClass().getResource("logo.png"));
        displabell = new JLabel(image);

        Image resized = image.getImage().getScaledInstance(110,80,Image.SCALE_SMOOTH);
        image = new ImageIcon(resized);
        displabell.setIcon(image);

        displabell.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        displabell.setBorder(new EmptyBorder(0,30,0,0));

        JPHeaderPanel.add(displabell);

        JPHeaderPanel.setMinimumSize(new Dimension(1165,100));
        JPButtonCreat.setBorder(new EmptyBorder(0,0,30,0));
        CollectionOfFolder mainpage = new CollectionOfFolder();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("UserFolder"), path -> Files.isDirectory(path))) {
            for (Path path : stream) {
                Folder folder = new Folder((path.getFileName()).toString());
                folder.setDirectory(path.toString());
                mainpage.addFolder(folder);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"An IOException has occured");
        }
        cover(mainpage);
        createFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result  = JOptionPane.showInputDialog("Enter Folder Name: ");
                if(result != null){
                    if(!result.isEmpty()){
                        String folderPath = "UserFolder\\"+result;

                        // Create a Path object
                        Path folder = Paths.get(folderPath);

                        // Check if the folder does not exist, then create it
                        if (!Files.exists(folder)) {
                            try {
                                Files.createDirectory(folder);
                                Folder newfolder = new Folder(result);
                                newfolder.setDirectory(folderPath);
                                mainpage.addFolder(newfolder);

                                JPFolderPanel.removeAll();
                                deleteButtonList = new ArrayList<>();
                                renameButtonList = new ArrayList<>();
                                openButtonList = new ArrayList<>();
                                cover(mainpage);


                            } catch (IOException ee) {
                                JOptionPane.showMessageDialog(null,"Failed to create folder: " + ee.getMessage());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"File already existed");
                        }
                    }
                }
            }
        });
    }

    protected static ImageIcon createImageIcon(String path) {
        URL imgURL = UserPage.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void cover(CollectionOfFolder mainpage){
        JPFolderPanel.setLayout(new BoxLayout(JPFolderPanel,BoxLayout.Y_AXIS));
        JPFolderPanel.setPreferredSize(new Dimension(1000,600));
        JPanel gridPanel = new JPanel();
        gridPanel.setBackground(new Color(250, 238, 209));
        gridPanel.setLayout(new BoxLayout(gridPanel,BoxLayout.Y_AXIS));

        JPanel Folderrow = new JPanel();
        Folderrow.setBackground(new Color(250, 238, 209));
        Folderrow.setLayout(new BoxLayout(Folderrow,BoxLayout.X_AXIS));
        gridPanel.add(Folderrow);



        // Add elements to the grid
        for(int loop = 1; loop<=mainpage.getFolderList().size(); loop++){
            JPanel marginFolder= new JPanel();
            marginFolder.setBorder(new EmptyBorder(10,20,10,20));
            JPanel Foldercontainer = new JPanel();
            Foldercontainer.setLayout(new BoxLayout(Foldercontainer,BoxLayout.Y_AXIS));
            Foldercontainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            Foldercontainer.setMinimumSize(new Dimension(250,250));
            Foldercontainer.setPreferredSize(new Dimension(250,250));
            Foldercontainer.setMaximumSize(new Dimension(250,250));
            marginFolder.add(Foldercontainer);
            marginFolder.setBackground(new Color(222, 208, 182));
            Foldercontainer.setBackground(new Color(178, 165, 155));
            Foldercontainer.setBorder(BorderFactory.createLineBorder(new Color(96, 114, 116),3));

            JPanel margintitle = new JPanel();
            margintitle.setBackground(new Color(178, 165, 155));
            JPanel titlepanel = new JPanel();
            titlepanel.setBackground(new Color(178, 165, 155));
            titlepanel.setBorder(new MatteBorder(0,0,4,0,new Color(250, 238, 209)));
            titlepanel.setLayout(new BoxLayout(titlepanel,BoxLayout.X_AXIS));
            JLabel titlefolder = new JLabel();
            titlefolder.setText(mainpage.getFolderList().get(loop-1).getName());
            titlefolder.setForeground(new Color(250, 238, 209));
            titlefolder.setFont(new Font("",Font.BOLD,30));
            titlepanel.add(titlefolder);
            JScrollPane titlescorll = new JScrollPane(titlepanel);
            titlescorll.setMinimumSize(new Dimension(240,60));
            titlescorll.setPreferredSize(new Dimension(240,60));
            titlescorll.setMaximumSize(new Dimension(240,60));
            titlescorll.setBorder(new EmptyBorder(0,0,0,0));
            margintitle.add(titlescorll);
            margintitle.setBorder(new EmptyBorder(60,20,0,0));


            JPanel buttonpanel = new JPanel();
            buttonpanel.setLayout(new BoxLayout(buttonpanel,BoxLayout.X_AXIS));
            buttonpanel.setBorder(new EmptyBorder(0,0,20,0));
            buttonpanel.setBackground(new Color(178, 165, 155));

            JPanel deletePanel = new JPanel();
            deletePanel.setLayout(new BoxLayout(deletePanel,BoxLayout.X_AXIS));
            JButton deleteButton = new JButton("Delete");
            deleteButton.setFont(new Font("",Font.PLAIN,14));
            deletePanel.add(deleteButton);
            deletePanel.setBorder(new EmptyBorder(0,0,0,5));
            deletePanel.setBackground(new Color(178, 165, 155));
            deleteButton.setBackground(new Color(96, 114, 116));

            JPanel renamePanel = new JPanel();
            renamePanel.setLayout(new BoxLayout(renamePanel,BoxLayout.X_AXIS));
            JButton renameButton = new JButton("Rename");
            renameButton.setFont(new Font("",Font.PLAIN,14));
            renamePanel.add(renameButton);
            renamePanel.setBorder(new EmptyBorder(0,0,0,7));
            renamePanel.setBackground(new Color(178, 165, 155));
            renameButton.setBackground(new Color(96, 114, 116));

            JPanel OpenPanel = new JPanel();
            OpenPanel.setLayout(new BoxLayout(OpenPanel,BoxLayout.X_AXIS));
            JButton OpenButton = new JButton("Open");
            OpenButton.setFont(new Font("",Font.PLAIN,14));
            OpenPanel.add(OpenButton);
            OpenPanel.setBorder(new EmptyBorder(0,0,0,7));
            OpenPanel.setBackground(new Color(178, 165, 155));
            OpenButton.setBackground(new Color(96, 114, 116));


            buttonpanel.add(deletePanel);
            buttonpanel.add(renamePanel);
            buttonpanel.add(OpenPanel);

            deleteButtonList.add(deleteButton);
            openButtonList.add(OpenButton);
            renameButtonList.add(renameButton);


            Foldercontainer.add(margintitle);

            Foldercontainer.add(buttonpanel);

            Folderrow.add(marginFolder);
            if(loop%3 == 0){
                Folderrow = new JPanel();
                Folderrow.setBackground(new Color(250, 238, 209));
                Folderrow.setLayout(new BoxLayout(Folderrow,BoxLayout.X_AXIS));
                gridPanel.add(Folderrow);
            }
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    int index = deleteButtonList.indexOf(deleteButton);
                    JPFolderPanel.revalidate();
                    JPFolderPanel.repaint();

                    JPUserPage.revalidate();
                    JPUserPage.repaint();

                    String path = "UserFolder\\"+mainpage.getFolderList().get(index).getName();
                    System.out.println(path);

                    File folder = new File(path);
                    File[] contents = folder.listFiles();

                    if(contents.length == 0){
                        folder.delete();
                    }
                    else{
                        for(File cc: contents){
                            cc.delete();
                        }
                    }
                    folder.delete();



                    mainpage.getFolderList().remove(index);

                    JPFolderPanel.removeAll();
                    deleteButtonList = new ArrayList<>();
                    renameButtonList = new ArrayList<>();
                    openButtonList = new ArrayList<>();
                    cover(mainpage);

                    JPFolderPanel.revalidate();
                    JPFolderPanel.repaint();

                    JPUserPage.revalidate();
                    JPUserPage.repaint();
                }
            });

            renameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int indx = renameButtonList.indexOf(renameButton);
                    String result  = JOptionPane.showInputDialog("Enter Folder Name: ");
                    if(result != null){
                        if(!result.isEmpty()){
                            String path = "UserFolder\\"+mainpage.getFolderList().get(indx).getName();

                            File folder = new File(path);
                            File repfolder = new File("UserFolder\\"+result);
                            folder.renameTo(repfolder);
                            mainpage.getFolderList().get(indx).setName(result);
                            mainpage.getFolderList().get(indx).setDirectory("UserFolder\\"+result);

                            JPFolderPanel.removeAll();
                            deleteButtonList = new ArrayList<>();
                            renameButtonList = new ArrayList<>();
                            openButtonList = new ArrayList<>();
                            cover(mainpage);

                            JPFolderPanel.revalidate();
                            JPFolderPanel.repaint();

                            JPUserPage.revalidate();
                            JPUserPage.repaint();
                        }
                    }
                }
            });

            OpenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int indx = openButtonList.indexOf(OpenButton);
                    openfolder = mainpage.getFolderList().get(indx);

                    try{
                        UserPage.getInstance().setVisible(false);
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                        FolderForQuiz folderquiz = FolderForQuiz.refreshInstance();
                        folderquiz.setContentPane(folderquiz.JPFolderContainerPanel);
                        folderquiz.setLocation(UserPage.getInstance().getLocation());
                        folderquiz.setSize(1200, 750);
                        folderquiz.setResizable(false);
                        folderquiz.folderfirst = openfolder;
                        folderquiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        folderquiz.setTitle("FolderUser.Folder");
                        folderquiz.setVisible(true);
                    }
                    catch (Exception exception){
                        if(exception instanceof  UnsupportedLookAndFeelException ){
                            JOptionPane.showMessageDialog(null,"UnsupportedLookAndFeelException occurred");
                        }
                        else if(exception instanceof  ClassNotFoundException){
                            JOptionPane.showMessageDialog(null,"ClassNotFoundException occurred");
                        }
                        else if(exception instanceof InstantiationException){
                            JOptionPane.showMessageDialog(null,"InstantiationException occurred");
                        }
                        else if(exception instanceof IllegalAccessException){
                            JOptionPane.showMessageDialog(null,"Illegal Access Exception occurred");
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"An error occurred");
                            exception.printStackTrace();
                        }
                    }

                }
            });
        }

        // Add the grid panel to the JFrame
        JPFolderPanel.add(gridPanel);
        JPFolderPanel.setPreferredSize(new Dimension(880,JPFolderPanel.getPreferredSize().height));
        JPUserPage.revalidate();
        JPUserPage.repaint();


    }


    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        UserPage MainUserPage = UserPage.getInstance();
        JScrollPane scrollme = new JScrollPane(MainUserPage.MainFrame);
        MainUserPage.setContentPane(scrollme);
        MainUserPage.setSize(1200, 750);
        MainUserPage.setResizable(false);
        MainUserPage.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MainUserPage.setTitle("Creating Quiz");
        MainUserPage.setVisible(true);
    }
}





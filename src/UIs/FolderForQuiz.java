package UIs;

import FolderForUserComponent.QuizFirstSection;
import FolderForUserComponent.QuizSecondSection;
import QuizPackage.Quiz;
import FolderUser.*;
import QuizPackage.SerializationUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FolderForQuiz extends JFrame{
    public JPanel JPFolderQuizPanel;
    public JScrollPane JSPFolderScrollPane;
    public JPanel JPFolderContainerPanel;
    public JPanel JPFolderSubjectContainerPanel;
    public JLabel JLSubjectLabel;
    public JScrollPane JSPSubjectContent;
    public JPanel JPSubjectContent;
    public JLabel JLSubjectContent;
    public JButton JBCreateQuizButton;
    public JScrollPane JSPQuzListScrollPanel;
    public JPanel JPQuizListPanel;
    private JButton homeButton;
    private JPanel JPButtonHome;

    private ArrayList<JPanel> deleteIndicator = new ArrayList<>();
    public ArrayList<JPanel> editIndicator = new ArrayList<>();

    public ArrayList<JPanel> answerIndicator = new ArrayList<>();


    private static FolderForQuiz FolderForQuizInstance = null;

    public static  FolderForQuiz getInstance() {
        if (FolderForQuizInstance == null) {
            FolderForQuizInstance = new FolderForQuiz();
        }
        return FolderForQuizInstance;
    }

    public static FolderForQuiz refreshInstance() {

        FolderForQuizInstance = new FolderForQuiz();

        return FolderForQuizInstance;

    }
    private int countQuizes = 1;

    public Folder folderfirst = UserPage.openfolder;

    public int quizIndex = -1;

    private FolderForQuiz(){
        homeButton.setBackground(new Color(96, 114, 116));
        JBCreateQuizButton.setBackground(new Color(96, 114, 116));
        JPButtonHome.setBorder(new EmptyBorder(0,20,0,0));
        JPFolderSubjectContainerPanel.setMinimumSize(new Dimension(1183,80));
        JLSubjectContent.setText(folderfirst.getName());
        JLSubjectContent.setForeground(new Color(250, 238, 209));
        JLSubjectContent.setBackground(new Color(96, 114, 116));
        JSPQuzListScrollPanel.setBorder(new EmptyBorder(0,0,0,0));
        JSPQuzListScrollPanel.setBorder(BorderFactory.createLineBorder(new Color(96, 114, 116),3));

        JSPSubjectContent.setPreferredSize(new Dimension(500,JSPSubjectContent.getPreferredSize().height));
        JSPSubjectContent.setBorder(new EmptyBorder(0,0,0,0));

        JPQuizListPanel.setBorder(new EmptyBorder(20,20,20,20));
        JPQuizListPanel.setLayout(new BoxLayout(JPQuizListPanel,BoxLayout.Y_AXIS));
        System.out.println(folderfirst.getDirectory());


        File folder = new File (folderfirst.getDirectory());
        File [] listOfFiles = folder.listFiles ();
        for (File file : listOfFiles) {
            if (file.isFile ()) {
                System.out.println (file.getName ());
                Quiz temp = null;
                try {
                    temp = (Quiz) SerializationUtil.deserialize(file.getAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                folderfirst.createQuiz(temp);
            }
        }

        for(Quiz quizess: folderfirst.getQuiz()){
            createQuizContainer(quizess.getQuizName(),quizess.getCards().size());
        }


        JSPQuzListScrollPanel.revalidate();
        JSPQuzListScrollPanel.repaint();


        JBCreateQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                goToMakeQuiz();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    FolderForQuiz.getInstance().setVisible(false);
                    folderfirst.getQuiz().removeAll(folderfirst.getQuiz());
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    UserPage MainUserPage = UserPage.getInstance();
                    JScrollPane scrollme = new JScrollPane(MainUserPage.MainFrame);
                    MainUserPage.setLocation(FolderForQuiz.getInstance().getLocation());
                    MainUserPage.setContentPane(scrollme);
                    MainUserPage.setSize(1200, 750);
                    MainUserPage.setResizable(false);
                    MainUserPage.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    MainUserPage.setTitle("Creating Quiz");
                    MainUserPage.setVisible(true);
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

    public void goToMakeQuiz(){
        //catching for errors expecially the setLookAndFeel
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            setMakeQuizFrame();

        }
        catch (Exception exception){
            ExceptionConditions(exception);
        }

    }

    public void refreshQuizContainer(){
        JPQuizListPanel.removeAll();

        deleteIndicator = new ArrayList<>();
        editIndicator = new ArrayList<>();
        answerIndicator = new ArrayList<>();

        countQuizes = 1;
        for(Quiz quizess: folderfirst.getQuiz()){
            createQuizContainer(quizess.getQuizName(),quizess.getCards().size());
        }


        JSPQuzListScrollPanel.revalidate();
        JSPQuzListScrollPanel.repaint();
    }

    public void createQuizContainer(String quizName, int quizlength){
        //setting panel for margin
        JPanel forMargin = new JPanel();
        forMargin.setLayout(new BoxLayout(forMargin,BoxLayout.X_AXIS));
        forMargin.setBorder(new EmptyBorder(0,0,20,0));
        forMargin.setBackground(new Color(222, 208, 182));

        //setting up the Container
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel,BoxLayout.X_AXIS));
        quizPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        quizPanel.setMinimumSize(new Dimension(700,200));
        quizPanel.setPreferredSize(new Dimension(700,200));
        quizPanel.setMaximumSize(new Dimension(700,200));


        forMargin.add(quizPanel);

        JPQuizListPanel.add(forMargin);

        //adding sections to the mainpanel
        QuizFirstSection first = new QuizFirstSection(quizName,quizlength);
        first.countQuizes = countQuizes;
        JPanel firstsection = first.addsection();

        QuizSecondSection second = new QuizSecondSection(quizName,quizlength);
        JPanel secondsection = second.addsection();
        secondsection.setBackground(new Color(96, 114, 116));

        quizPanel.add(firstsection);
        quizPanel.add(secondsection);

        firstsection.setBackground(new Color(178, 165, 155));


        //adding buttons

        JPanel buttonPanelContainer = new JPanel();
        buttonPanelContainer.setLayout(new BoxLayout(buttonPanelContainer,BoxLayout.X_AXIS));
        buttonPanelContainer.setBackground(new Color(178, 165, 155));

        JPanel editPanel = first.addEditButton();
        editPanel.setBackground(new Color(178, 165, 155));

        JPanel DeletePanel = first.addDeleteteButton();
        DeletePanel.setBackground(new Color(178, 165, 155));


        JPanel TakePanel = first.addTakeButton();
        TakePanel.setBackground(new Color(178, 165, 155));

        buttonPanelContainer.add(DeletePanel);
        buttonPanelContainer.add(editPanel);
        buttonPanelContainer.add(TakePanel);
        firstsection.add(buttonPanelContainer);

        deleteIndicator.add(DeletePanel);
        editIndicator.add(editPanel);
        answerIndicator.add(TakePanel);

        JButton Deletebutton = first.deletebut;
        JButton Editbutton = first.editbut;
        JButton TakeButton = first.takebut;

        Deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = deleteIndicator.indexOf(DeletePanel);
                deleteIndicator.remove(DeletePanel);


                System.out.println(index);

                Path path = Paths.get (folderfirst.getDirectory()+"/"+folderfirst.getQuiz().get(index).getQuizName()+".ser");
                try {
                    if (Files.deleteIfExists (path)) {
                        System.out.println ("File deleted successfully");
                    } else {
                        System.out.println ("File does not exist");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                folderfirst.getQuiz().remove(index);
                refreshQuizContainer();

            }
        });

        Editbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int n = editIndicator.indexOf(editPanel);

                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    setMakeQuizFrame();
                    MakingQuiz.getInstance().editorview(folderfirst.getQuiz().get(n));
                }
                catch (Exception exception){
                    ExceptionConditions(exception);
                }



            }
        });

        TakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                quizIndex = answerIndicator.indexOf(TakePanel);

                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FolderForQuiz.getInstance().setVisible(false);

                    TakeQuiz appme = TakeQuiz.refreshInstance();
                    JScrollPane scrollPane = new JScrollPane(appme.JTakequiz);
                    appme.setLocation(FolderForQuiz.getInstance().getLocation());
                    appme.setContentPane(scrollPane);
                    appme.setSize(1200, 750);
                    appme.setResizable(false);
                    appme.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    appme.setTitle("Creating Quiz");
                    appme.setVisible(true);
                }
                catch (Exception exception){
                    ExceptionConditions(exception);
                }

            }
        });

        countQuizes++;
    }


    public void ExceptionConditions(Exception exception){
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

    public void setMakeQuizFrame(){
        FolderForQuiz.getInstance().setVisible(false);
        MakingQuiz app = MakingQuiz.refreshInstance();
        JScrollPane scrollPane = new JScrollPane(app.jpanel);
        app.setLocation(FolderForQuiz.getInstance().getLocation());
        app.setContentPane(scrollPane);
        app.setSize(1200, 750);
        app.setResizable(false);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Making Quiz");
        app.setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

    }
}

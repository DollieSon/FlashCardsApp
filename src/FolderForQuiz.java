
import QuizPackage.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    /**delete this later(Sample data)**/
    public Folder folderfirst = new Folder("Gmajor","Jason");

    public int quizIndex = -1;


    private FolderForQuiz(){

        JLSubjectContent.setText(folderfirst.getName());

        JSPSubjectContent.setPreferredSize(new Dimension(500,JSPSubjectContent.getPreferredSize().height));
        JSPSubjectContent.setBorder(new EmptyBorder(0,0,0,0));

        JPQuizListPanel.setBorder(new EmptyBorder(20,20,20,20));
        JPQuizListPanel.setLayout(new BoxLayout(JPQuizListPanel,BoxLayout.Y_AXIS));

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
    }

    public void goToMakeQuiz(){
        //catching for errors expecially the setLookAndFeel
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
            }
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
        JPanel firstsection = new JPanel();
        firstsection.setLayout(new BoxLayout(firstsection,BoxLayout.Y_AXIS));
        firstsection.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        firstsection.setMinimumSize(new Dimension(450,200));
        firstsection.setPreferredSize(new Dimension(450,200));
        firstsection.setMaximumSize(new Dimension(450,200));

        JPanel secondsection = new JPanel();
        secondsection.setLayout(new BoxLayout(secondsection,BoxLayout.Y_AXIS));
        secondsection.setMinimumSize(new Dimension(245,200));
        secondsection.setPreferredSize(new Dimension(245,200));
        secondsection.setMaximumSize(new Dimension(245,200));

        quizPanel.add(firstsection);
        quizPanel.add(secondsection);

        //addingQuiztitle

        JPanel countPanel = new JPanel();
        countPanel.setLayout(new BoxLayout(countPanel,BoxLayout.Y_AXIS));
        countPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel countLabel = new JLabel("#"+countQuizes);
        countLabel.setFont(new Font("",Font.BOLD,17));
        countLabel.setBorder(new EmptyBorder(10,10,10,10));

        countPanel.add(countLabel);

        JPanel extendedcontainer = new JPanel();
        extendedcontainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        extendedcontainer.setMinimumSize(new Dimension(450,50));
        extendedcontainer.setMaximumSize(new Dimension(450,50));
        extendedcontainer.add(countPanel);
        firstsection.add(extendedcontainer);


        //adding itemlabel
        JPanel QuizTitlePanel = new JPanel();
        QuizTitlePanel.setLayout(new BoxLayout(QuizTitlePanel,BoxLayout.X_AXIS));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setLayout(new BoxLayout(TitlePanel,BoxLayout.X_AXIS));
        JLabel QuizTitleLabel = new JLabel("Title: ");
        QuizTitleLabel.setFont(new Font("",Font.BOLD,16));
        TitlePanel.add(QuizTitleLabel);

        JPanel QuizTitlePanelfirstsection = new JPanel();
        QuizTitlePanelfirstsection.setLayout(new BoxLayout(QuizTitlePanelfirstsection,BoxLayout.X_AXIS));
        JLabel QuiztitleContent = new JLabel(quizName);
        QuizTitlePanelfirstsection.add(QuiztitleContent);
        QuiztitleContent.setFont(new Font("",Font.PLAIN,20));

        JScrollPane QuizTitleScrollPanel = new JScrollPane(QuizTitlePanelfirstsection);
        QuizTitleScrollPanel.setMinimumSize(new Dimension(350,50));
        QuizTitleScrollPanel.setMaximumSize(new Dimension(350,50));
        QuizTitleScrollPanel.setPreferredSize(new Dimension(350,QuizTitleScrollPanel.getPreferredSize().height));
        QuizTitleScrollPanel.setBorder(new EmptyBorder(0,0,0,0));

        QuizTitlePanel.add(TitlePanel);
        QuizTitlePanel.add(QuizTitleScrollPanel);
        firstsection.add(QuizTitlePanel);

        JPanel ItemsPanel = new JPanel();
        ItemsPanel.setLayout(new BoxLayout(ItemsPanel,BoxLayout.X_AXIS));
        ItemsPanel.setMinimumSize(new Dimension(387,30));
        ItemsPanel.setMaximumSize(new Dimension(387,30));

        JLabel ItemsLabel= new JLabel("Items: ");
        ItemsLabel.setFont(new Font("",Font.BOLD,16));

        JLabel ItemsContent= new JLabel(""+quizlength);
        ItemsContent.setFont(new Font("",Font.PLAIN,20));

        ItemsPanel.add(ItemsLabel);
        ItemsPanel.add(ItemsContent);

        firstsection.add(ItemsPanel);

        //adding buttons

        JPanel buttonPanelContainer = new JPanel();
        buttonPanelContainer.setLayout(new BoxLayout(buttonPanelContainer,BoxLayout.X_AXIS));

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel,BoxLayout.X_AXIS));
        editPanel.setBorder(new EmptyBorder(20,20,0,0));
        JButton Editbutton = new JButton();
        Editbutton.setText("Edit");
        Editbutton.setFont(new Font("",Font.PLAIN,16));
        editPanel.add(Editbutton);

        JPanel DeletePanel = new JPanel();
        DeletePanel.setLayout(new BoxLayout(DeletePanel,BoxLayout.X_AXIS));
        DeletePanel.setBorder(new EmptyBorder(20,0,0,0));
        JButton Deletebutton = new JButton();
        Deletebutton.setText("Delete");
        Deletebutton.setFont(new Font("",Font.PLAIN,16));
        DeletePanel.add(Deletebutton);


        JPanel TakePanel = new JPanel();
        TakePanel.setLayout(new BoxLayout(TakePanel,BoxLayout.X_AXIS));
        TakePanel.setBorder(new EmptyBorder(20,20,0,0));
        JButton TakeButton = new JButton();
        TakeButton.setText("Answer");
        TakeButton.setFont(new Font("",Font.PLAIN,16));
        TakePanel.add(TakeButton);

        buttonPanelContainer.add(DeletePanel);
        buttonPanelContainer.add(editPanel);
        buttonPanelContainer.add(TakePanel);
        firstsection.add(buttonPanelContainer);

        deleteIndicator.add(DeletePanel);
        editIndicator.add(editPanel);
        answerIndicator.add(TakePanel);



        Deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index = deleteIndicator.indexOf(DeletePanel);
                deleteIndicator.remove(DeletePanel);

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
                    FolderForQuiz.getInstance().setVisible(false);
                    MakingQuiz app = MakingQuiz.refreshInstance();
                    MakingQuiz.getInstance().editorview(folderfirst.getQuiz().get(n));
                    JScrollPane scrollPane = new JScrollPane(app.jpanel);
                    app.setLocation(FolderForQuiz.getInstance().getLocation());
                    app.setContentPane(scrollPane);
                    app.setSize(1200, 750);
                    app.setResizable(false);
                    app.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    app.setTitle("Making Quiz");
                    app.setVisible(true);
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

        TakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                quizIndex = answerIndicator.indexOf(TakePanel);

                try{
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    FolderForQuiz.getInstance().setVisible(false);

                    TakeQuiz appme = TakeQuiz.refreshInstance();
                    JScrollPane scrollPane = new JScrollPane(appme.JTakequiz);
                    appme.setContentPane(scrollPane);
                    appme.setSize(1200, 750);
                    appme.setResizable(false);
                    appme.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    appme.setTitle("Creating Quiz");
                    appme.setVisible(true);
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

        countQuizes++;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        FolderForQuiz folderquiz = FolderForQuiz.getInstance();
        folderquiz.setContentPane(folderquiz.JPFolderContainerPanel);
        folderquiz.setSize(1200, 750);
        folderquiz.setResizable(false);
        folderquiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
        folderquiz.setTitle("Folder");
        folderquiz.setVisible(true);

    }
}

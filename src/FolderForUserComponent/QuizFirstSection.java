package FolderForUserComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QuizFirstSection extends FolderQuizContainer{

    public JButton deletebut;
    public JButton editbut;
    public JButton takebut;
    public QuizFirstSection(String q, int l){
        super(q,l);
    }
    public int countQuizes = 0;
    public JPanel addsection(){
        JPanel firstsection = super.addsection();
        firstsection.setMinimumSize(new Dimension(550,200));
        firstsection.setPreferredSize(new Dimension(550,200));
        firstsection.setMaximumSize(new Dimension(550,200));

        //addingQuiztitle
        JPanel countPanel = new JPanel();
        countPanel.setLayout(new BoxLayout(countPanel,BoxLayout.Y_AXIS));
        countPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        countPanel.setBackground(new Color(250, 238, 209));

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
        extendedcontainer.setBackground(new Color(178, 165, 155));

        //adding itemlabel
        JPanel QuizTitlePanel = new JPanel();
        QuizTitlePanel.setLayout(new BoxLayout(QuizTitlePanel,BoxLayout.X_AXIS));
        QuizTitlePanel.setBackground(new Color(178, 165, 155));

        JPanel TitlePanel = new JPanel();
        TitlePanel.setLayout(new BoxLayout(TitlePanel,BoxLayout.X_AXIS));
        JLabel QuizTitleLabel = new JLabel("Title: ");
        QuizTitleLabel.setFont(new Font("",Font.BOLD,16));
        TitlePanel.add(QuizTitleLabel);
        TitlePanel.setBackground(new Color(178, 165, 155));

        JPanel QuizTitlePanelfirstsection = new JPanel();
        QuizTitlePanelfirstsection.setLayout(new BoxLayout(QuizTitlePanelfirstsection,BoxLayout.X_AXIS));
        JLabel QuiztitleContent = new JLabel(quizName);
        QuizTitlePanelfirstsection.add(QuiztitleContent);
        QuiztitleContent.setFont(new Font("",Font.PLAIN,20));
        QuizTitlePanelfirstsection.setBackground(new Color(178, 165, 155));
        QuiztitleContent.setBackground(new Color(178, 165, 155));

        JScrollPane QuizTitleScrollPanel = new JScrollPane(QuizTitlePanelfirstsection);
        QuizTitleScrollPanel.setMinimumSize(new Dimension(350,50));
        QuizTitleScrollPanel.setMaximumSize(new Dimension(350,50));
        QuizTitleScrollPanel.setPreferredSize(new Dimension(350,QuizTitleScrollPanel.getPreferredSize().height));
        QuizTitleScrollPanel.setBorder(new EmptyBorder(0,0,0,0));
        QuizTitleScrollPanel.setBackground(new Color(178, 165, 155));

        QuizTitlePanel.add(TitlePanel);
        QuizTitlePanel.add(QuizTitleScrollPanel);
        firstsection.add(QuizTitlePanel);

        JPanel ItemsPanel = new JPanel();
        ItemsPanel.setLayout(new BoxLayout(ItemsPanel,BoxLayout.X_AXIS));
        ItemsPanel.setMinimumSize(new Dimension(387,30));
        ItemsPanel.setMaximumSize(new Dimension(387,30));
        ItemsPanel.setBackground(new Color(178, 165, 155));

        JLabel ItemsLabel= new JLabel("Items: ");
        ItemsLabel.setFont(new Font("",Font.BOLD,16));
        ItemsLabel.setBackground(new Color(178, 165, 155));

        JLabel ItemsContent= new JLabel(""+quizlength);
        ItemsContent.setFont(new Font("",Font.PLAIN,20));
        ItemsContent.setBackground(new Color(178, 165, 155));

        ItemsPanel.add(ItemsLabel);
        ItemsPanel.add(ItemsContent);

        firstsection.add(ItemsPanel);


        return firstsection;
    }
    public JPanel addDeleteteButton(){
        JPanel DeletePanel = new JPanel();
        DeletePanel.setLayout(new BoxLayout(DeletePanel,BoxLayout.X_AXIS));
        DeletePanel.setBorder(new EmptyBorder(20,0,0,0));
        JButton Deletebutton = new JButton();
        Deletebutton.setBackground(new Color(96, 114, 116));
        Deletebutton.setText("Delete");
        Deletebutton.setFont(new Font("",Font.PLAIN,16));
        DeletePanel.add(Deletebutton);
        this.deletebut = Deletebutton;

        return DeletePanel;
    }
    public JPanel addEditButton(){
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new BoxLayout(editPanel,BoxLayout.X_AXIS));
        editPanel.setBorder(new EmptyBorder(20,20,0,0));
        JButton Editbutton = new JButton();
        Editbutton.setBackground(new Color(96, 114, 116));
        Editbutton.setText("Edit");
        Editbutton.setFont(new Font("",Font.PLAIN,16));
        editPanel.add(Editbutton);
        this.editbut = Editbutton;

        return editPanel;
    }

    public JPanel addTakeButton(){
        JPanel TakePanel = new JPanel();
        TakePanel.setLayout(new BoxLayout(TakePanel,BoxLayout.X_AXIS));
        TakePanel.setBorder(new EmptyBorder(20,20,0,0));
        JButton TakeButton = new JButton();
        TakeButton.setBackground(new Color(96, 114, 116));
        TakeButton.setText("Answer");
        TakeButton.setFont(new Font("",Font.PLAIN,16));
        TakePanel.add(TakeButton);
        this.takebut = TakeButton;

        return TakePanel;
    }
}

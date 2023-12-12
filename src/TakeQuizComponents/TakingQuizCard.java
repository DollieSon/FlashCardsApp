package TakeQuizComponents;

import QuizPackage.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class TakingQuizCard{

    public JPanel JPQuestionQuizPanel;
    public JScrollPane errormessage;
    public JTextField identificationAnswer;
    public JLabel questionccontent;


    public JLabel LabelError;

    public TakingQuizCard(){
        questionccontent = new JLabel();

    }

    public abstract JPanel getAnswerComponent();


    public void submitMessageLabels(){
        JPQuestionQuizPanel.remove(errormessage);
        JLabel errorlabel = new JLabel("Warning: Choose an answer");
        errorlabel.setFont(new Font("",Font.PLAIN,14));
        errorlabel.setForeground(new Color(139, 128, 0));

        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

        errorPanel.add(errorlabel);

        errormessage = new JScrollPane(errorPanel);
        errormessage.setMaximumSize(new Dimension(300,50));
        errormessage.setMinimumSize(new Dimension(300,50));
        errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
        errormessage.setBorder(new EmptyBorder(0,0,0,0));

        JPQuestionQuizPanel.add(errormessage);

        LabelError = errorlabel;

        JPQuestionQuizPanel.revalidate();
        JPQuestionQuizPanel.repaint();
    }

    public JPanel getQuestionComponent(Quiz quizcards,int count){
        JPanel QuestionComponents = new JPanel();
        QuestionComponents.setMinimumSize(new Dimension(550,100));
        QuestionComponents.setMaximumSize(new Dimension(550,100));
        QuestionComponents.setLayout(new BoxLayout(QuestionComponents,BoxLayout.X_AXIS));

        JPanel QuestionContainer = new JPanel();
        QuestionContainer.setLayout(new BoxLayout(QuestionContainer,BoxLayout.X_AXIS));
        QuestionContainer.setBorder(new EmptyBorder(0,20,0,20));

        JLabel QuestionContent = new JLabel();
        QuestionContent.setText(quizcards.getCards().get(count).getQuestion());
        QuestionContent.setFont(new Font("",Font.PLAIN,20));

        QuestionContainer.add(QuestionContent);

        JScrollPane QuestionScroll= new JScrollPane(QuestionContainer);
        QuestionScroll.setMaximumSize(new Dimension(600,70));
        QuestionScroll.setPreferredSize(new Dimension(300,QuestionScroll.getPreferredSize().height));

        QuestionComponents.add(QuestionScroll);

        questionccontent = QuestionContent;

        return QuestionComponents;

    }

}

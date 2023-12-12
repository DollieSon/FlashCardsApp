package TakeQuizComponents;
import QuizPackage.Quiz;

import Card.MultipleChoiceCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TQIdentificationCard extends TakingQuizCard{

    public JTextField answerContent;


    public JTextField changecolor;
    public TQIdentificationCard(){
        super();
        answerContent = new JTextField();
    }


    public void submitMessageLabels(){
        JPQuestionQuizPanel.remove(errormessage);
        identificationAnswer.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        JLabel errorlabel = new JLabel();
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
        changecolor = identificationAnswer;
    }

    public JPanel getAnswerComponent(){
        //Panel for User answer
        JPanel AnswerComponents = new JPanel();
        AnswerComponents.setLayout(new BoxLayout(AnswerComponents,BoxLayout.X_AXIS));
        AnswerComponents.setLayout(new FlowLayout(FlowLayout.CENTER));
        AnswerComponents.setMinimumSize(new Dimension(550,100));
        AnswerComponents.setMaximumSize(new Dimension(550,100));
        AnswerComponents.setBorder(new EmptyBorder(40,0,0,0));


        //Add Question Label and Content
        JLabel AnswerLabel = new JLabel("   Answer: ");
        AnswerComponents.add(AnswerLabel);
        AnswerLabel.setMinimumSize(new Dimension(100,50));
        AnswerLabel.setMaximumSize(new Dimension(100,50));
        AnswerLabel.setFont(new Font("",Font.PLAIN,20));

        JTextField AnswerContent = new JTextField();
        AnswerContent.setPreferredSize(new Dimension(300, 40));
        AnswerContent.setFont(new Font("",Font.PLAIN,16));
        AnswerComponents.add(AnswerContent);

        this.answerContent = AnswerContent;

        return AnswerComponents;
    }
}

package TakeQuizComponents;
import QuizPackage.Quiz;

import Card.TrueOrFalseCard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TQTrueOrFalseCard extends TakingQuizCard{

    public int count;
    public Quiz quizcards;

    public JRadioButton Truebutton,Falsebutton;
    public TQTrueOrFalseCard(){
        super();
        count = 0;
        quizcards = new Quiz();
        Truebutton = new JRadioButton();
        Falsebutton = new JRadioButton();
    }

    @Override

    public JPanel getAnswerComponent(){
        //Panel for User answer
        JPanel AnswerPanel = new JPanel();
        AnswerPanel.setLayout(new BoxLayout(AnswerPanel,BoxLayout.X_AXIS));

        JRadioButton TrueChoice = new JRadioButton();
        TrueChoice.setText(((TrueOrFalseCard) quizcards.getCards().get(count)).getChoices().get(0));
        TrueChoice.setFont(new Font("",Font.PLAIN,16));

        JRadioButton FalseChoice = new JRadioButton();
        FalseChoice.setText(((TrueOrFalseCard) quizcards.getCards().get(count)).getChoices().get(1));
        FalseChoice.setFont(new Font("",Font.PLAIN,16));

        AnswerPanel.add(TrueChoice);
        AnswerPanel.add(FalseChoice);

        AnswerPanel.setBorder(new EmptyBorder(40,0,0,0));

        ButtonGroup ChoiceGroup = new ButtonGroup();
        ChoiceGroup.add(TrueChoice);
        ChoiceGroup.add(FalseChoice);

        this.Truebutton = TrueChoice;
        this.Falsebutton = FalseChoice;

        return AnswerPanel;

    }
}

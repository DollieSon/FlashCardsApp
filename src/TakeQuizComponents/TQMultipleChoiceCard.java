package TakeQuizComponents;

import Card.MultipleChoiceCard;
import QuizPackage.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class TQMultipleChoiceCard extends TakingQuizCard{

    public int count;
    public Quiz quizcards;

    public ArrayList<JRadioButton>ChoicesRadioButton;
    public TQMultipleChoiceCard(){
        super();
        count = 0;
        quizcards = new Quiz();
        ChoicesRadioButton = new ArrayList<>();
    }

    @Override

    public JPanel getAnswerComponent(){
        JPanel AnswerPanel = new JPanel();
        AnswerPanel.setLayout(new BoxLayout(AnswerPanel,BoxLayout.Y_AXIS));


        for(String s: ((MultipleChoiceCard) quizcards.getCards().get(count)).getChoices()){

            JRadioButton choiceradio = new JRadioButton();
            ChoicesRadioButton.add(choiceradio);


            JPanel ChoicePanel = new JPanel();
            ChoicePanel.setLayout(new BoxLayout(ChoicePanel,BoxLayout.X_AXIS));

            JScrollPane ChoiceScrollPane = new JScrollPane(ChoicePanel);
            ChoiceScrollPane.setMaximumSize(new Dimension(300,50));
            ChoiceScrollPane.setMinimumSize(new Dimension(300,50));
            ChoiceScrollPane.setPreferredSize(new Dimension(300,ChoiceScrollPane.getPreferredSize().height));

            JLabel ChoiceContentLabel = new JLabel(s);
            ChoiceContentLabel.setFont(new Font("",Font.PLAIN,14));
            ChoiceContentLabel.setBorder(new EmptyBorder(0,10,0,10));
            ChoicePanel.add(ChoiceContentLabel);


            JPanel storeChoiceScrollPane = new JPanel();
            storeChoiceScrollPane.setLayout(new BoxLayout(storeChoiceScrollPane,BoxLayout.X_AXIS));
            storeChoiceScrollPane.setBorder(new EmptyBorder(0,0,10,0));

            storeChoiceScrollPane.add(choiceradio);
            storeChoiceScrollPane.add(ChoiceScrollPane);

            AnswerPanel.add(storeChoiceScrollPane);

        }
        return AnswerPanel;
    }
}

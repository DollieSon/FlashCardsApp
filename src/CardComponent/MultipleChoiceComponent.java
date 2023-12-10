package CardComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Optional;

public class MultipleChoiceComponent extends CardComp{

    JPanel questionContainer;
    JPanel forOption;

    public MultipleChoiceComponent(){
        super();
        JPanel appendLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        appendLabel.setLayout(new BoxLayout(appendLabel, BoxLayout.X_AXIS));
        Answer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel answerLabel = new JLabel("Choices");
        answerLabel.setBorder(new EmptyBorder(0,0,0,10));
        JButton addOption = new JButton("Add Option");
        appendLabel.add(answerLabel);
        appendLabel.add(addOption);
        Answer.add(appendLabel);
        Answer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Answer.setPreferredSize(new Dimension(500,200));
        Answer.setMinimumSize(new Dimension(500,200));
        Answer.setMaximumSize(new Dimension(500,200));
        Answer.setLayout(new BoxLayout(Answer, BoxLayout.Y_AXIS));

    }
    //TODO Implement get AnswerInput
    @Override
    public String getAnswerInput() {
        return null;
    }

    //TODO add Button Listeners Here
    @Override
    public JPanel getComponent() {
        questionContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));
        questionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionContainer.setPreferredSize(new Dimension(ComponentOptions.maxCardWidth,350));
        questionContainer.setMinimumSize(new Dimension(ComponentOptions.maxCardWidth,350));
        questionContainer.setMaximumSize(new Dimension(ComponentOptions.maxCardWidth,350));
        questionContainer.add(Question);
        questionContainer.add(Answer);
        questionContainer.add(Delete);
        JPanel WholePanel = new JPanel();
        WholePanel.add(questionContainer);
        WholePanel.setBorder(new EmptyBorder(10,0,5,0));



        return WholePanel;
    }


}

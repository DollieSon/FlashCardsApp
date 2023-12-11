package CardComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class CardComp implements CardComponent{
    JPanel Answer;
    JPanel Question;
    JPanel Delete;

    public CardComp(){
        //Delete
        Delete = new JPanel();
        JButton deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setMaximumSize(new Dimension(150,20));
        Delete.add(deleteButton);
        Delete.setBorder(new EmptyBorder(0,0,10,0));

        //SetQuestion
        Question = new JPanel();
        Question.setLayout(new BoxLayout(Question, BoxLayout.X_AXIS));
        JLabel questionLabel = new JLabel("Question: ");
        JTextField questionField = new JTextField();
        questionField.setBorder(ComponentOptions.getTextFieldBorder());
        Question.setBorder(ComponentOptions.getCardPanelBorder());
        Question.add(questionLabel);
        Question.add(questionField);

        //SetAnswer
        Answer = new JPanel();
        Answer.setLayout(new BoxLayout(Answer, BoxLayout.X_AXIS));
        JLabel answerLabel = new JLabel("Answer:    ");
        Answer.setBorder(ComponentOptions.getCardPanelBorder());
        Answer.add(answerLabel);
        System.out.println(Answer);
    }
    @Override
    public String getQuestionInput() {
        return ((JTextField) Question.getComponent(1)).getText();
    }

    @Override
    public abstract String getAnswerInput();

    @Override
    public CardComponent setQuestion(JPanel Question) {
        this.Question = Question;
        return null;
    }
    @Override
    public CardComponent setAnswer(JPanel Answer) {
        this.Answer = Answer;
        return this;
    }

    @Override
    public JPanel getComponent(JPanel MeComp, List<CardComponent> GlobalList) {
        JPanel WholePanel = new JPanel();
        JPanel questionContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));

        questionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionContainer.setPreferredSize(new Dimension(ComponentOptions.maxCardWidth,ComponentOptions.maxCardHeight));
        questionContainer.setMinimumSize(new Dimension(ComponentOptions.maxCardWidth,ComponentOptions.maxCardHeight));
        questionContainer.setMaximumSize(new Dimension(ComponentOptions.maxCardWidth,ComponentOptions.maxCardHeight));

        questionContainer.add(Question);
        questionContainer.add(Answer);
        questionContainer.add(Delete);

        WholePanel.add(questionContainer);
        WholePanel.setBorder(new EmptyBorder(10,0,5,0));

        JButton deleteButton = (JButton) Delete.getComponent(0);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MeComp.remove(WholePanel);
                GlobalList.remove(CardComp.this);
                // Refresh the view
                MeComp.revalidate();
                MeComp.repaint();
            }
        });

        return WholePanel;
    }
}

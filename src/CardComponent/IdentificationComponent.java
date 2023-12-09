
package CardComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IdentificationComponent implements CardComponent{
        JPanel Question;
        JPanel Answer;
        JPanel Delete;
        public IdentificationComponent(){

            Question = new JPanel();
            Question.setLayout(new BoxLayout(Question, BoxLayout.X_AXIS));
            JLabel questionLabel = new JLabel("Question: ");
            JTextField questionField = new JTextField();
            questionField.setBorder(ComponentOptions.getTextFieldBorder());
            Question.setBorder(ComponentOptions.getCardPanelBorder());
            Question.add(questionLabel);
            Question.add(questionField);

            Answer = new JPanel();
            Answer.setLayout(new BoxLayout(Answer, BoxLayout.X_AXIS));
            JLabel answerLabel = new JLabel("Answer:    ");
            JTextField answerField = new JTextField();
            answerField.setBorder(ComponentOptions.getTextFieldBorder());
            Answer.setBorder(ComponentOptions.getCardPanelBorder());
            Answer.add(answerLabel);
            Answer.add(answerField);

            Delete = new JPanel();
            JButton deleteButton = new JButton();
            deleteButton.setText("Delete");
            deleteButton.setMaximumSize(new Dimension(150,20));
            Delete.add(deleteButton);
            Delete.setBorder(new EmptyBorder(0,0,10,0));

        }

        public String getQuestionInput(){
            return ((JTextField)Question.getComponent(1)).getText();
        }
        public String getAnswerInput(){
            return ((JTextField)Answer.getComponent(1)).getText();
        }
        // Can be Optimized
        public JPanel getComponent(){

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

            return WholePanel;
        }
}

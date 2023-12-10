package CardComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultipleChoiceComponent extends CardComp{

    JPanel questionContainer;
    JPanel forOption;
    ButtonGroup choiceGrp;

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
        choiceGrp = new ButtonGroup();

    }
    //TODO Implement get AnswerInput
    @Override
    public String getAnswerInput() {
        return null;
    }

    //TODO add Button Listeners Here
    @Override
    public JPanel getComponent(JPanel MeComp) {
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

        JButton addOption = (JButton) ((JPanel)Answer.getComponent(0)).getComponent(1);

        addOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Answer.getComponentCount() >= 5){
                    return;
                }

                JButton deleteOption = new JButton("Delete");


                JRadioButton choiceRadio = new JRadioButton();
                choiceGrp.add(choiceRadio);

                JTextField option = new JTextField();
                option.setPreferredSize(new Dimension(200,25));
                option.setMinimumSize(new Dimension(200,25));
                option.setMaximumSize(new Dimension(200,25));
                option.setBorder(new EmptyBorder(0,20,0,10));

                JPanel forOption = new JPanel();

                forOption.setLayout(new BoxLayout(forOption, BoxLayout.X_AXIS));
                forOption.setBorder(new EmptyBorder(10,0,0,0));
                forOption.add(choiceRadio);
                forOption.add(option);
                forOption.add(deleteOption);
                Answer.add(forOption);

                questionContainer.revalidate();

                deleteOption.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Answer.remove(forOption);
                        //count--;
                        MeComp.revalidate();
                        MeComp.repaint();
                    }
                });

            }
        });

        JButton deleteButton = (JButton) Delete.getComponent(0);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MeComp.remove(WholePanel);
                // Refresh the view
                MeComp.revalidate();
                MeComp.repaint();
            }
        });

        return WholePanel;
    }


}

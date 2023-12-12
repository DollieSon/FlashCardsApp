package TakeQuizComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ButtonsClass {

    public JLabel JLCorrect;
    public int maxProgress;

    public JPanel JPButtonsPanel;

    public JPanel JPQuestionQuizPanel;

    public void showScore(){

        JPanel scoreTitlePanel = new JPanel();
        scoreTitlePanel.setLayout(new BoxLayout(scoreTitlePanel,BoxLayout.X_AXIS));

        JLabel scoreTitleLabel = new JLabel("Your Score: ");
        scoreTitleLabel.setFont(new Font("",Font.PLAIN,30));

        JLabel yourScore = new JLabel(JLCorrect.getText()+"/"+maxProgress);
        yourScore.setFont(new Font("",Font.BOLD,40));

        scoreTitlePanel.add(scoreTitleLabel);
        scoreTitlePanel.add(yourScore);

        JPQuestionQuizPanel.add(scoreTitlePanel);

        JPanel ScoredisplayPanel = new JPanel();
        ScoredisplayPanel.setLayout(new BoxLayout(ScoredisplayPanel,BoxLayout.Y_AXIS));


        JProgressBar FinalScore = new JProgressBar(0,maxProgress);
        FinalScore.setStringPainted(true);
        FinalScore.setValue(Integer.parseInt(JLCorrect.getText()));

        double gg = FinalScore.getPercentComplete();
        System.out.println(gg);

        JLabel message = new JLabel();
        message.setBorder(new EmptyBorder(50,150,0,0));

        if(gg >= 0.60 && gg <0.80){
            FinalScore.setForeground(Color.BLUE);
            message.setText("Great Job!!!");
            message.setFont(new Font("",Font.BOLD,20));
        }
        else if(gg >=0.80){
            FinalScore.setForeground(Color.GREEN);
            message.setText("Genius!!!");
            message.setFont(new Font("",Font.BOLD,20));
        }
        else{
            FinalScore.setForeground(Color.RED);
            message.setText("Better Luck Next Time");
            message.setFont(new Font("",Font.BOLD,20));
        }

        ScoredisplayPanel.add(FinalScore);
        ScoredisplayPanel.add(message);
        ScoredisplayPanel.setBorder(new EmptyBorder(70,0,0,0));


        JPQuestionQuizPanel.add(ScoredisplayPanel);

        JPButtonsPanel.removeAll();
        JPButtonsPanel.setLayout(new BoxLayout(JPButtonsPanel,BoxLayout.X_AXIS));

        JButton DoneButton = new JButton();
        DoneButton.setFont(new Font("",Font.PLAIN,16));
        DoneButton.setText("Done");
        JPButtonsPanel.add(DoneButton);

        JPQuestionQuizPanel.revalidate();
        JPQuestionQuizPanel.repaint();
        JPButtonsPanel.revalidate();
        JPButtonsPanel.repaint();
    }
}

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class MakingQuiz extends JFrame{
    private JTextField tFTitle;
    private JPanel jpanel;
    private JButton saveButton;
    private JLabel LAuthor;
    private JLabel LBy;
    private JLabel Ltitle;
    private JButton BaddQuestion;
    private JRadioButton RBmultipleChoice;
    private JRadioButton RBidentification;
    private JRadioButton RBtrueOrFalse;
    private JLabel Ltype;

    public MakingQuiz(){
        JRadioButton[] jr = {RBidentification,RBmultipleChoice,RBtrueOrFalse};
        BaddQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for(JRadioButton aa: jr){
                    if(aa.isSelected()){
                        JOptionPane.showMessageDialog(null,"GOOD");
                        return;
                    }
                }


                JOptionPane.showConfirmDialog(null,"STOP");
            }
        });
    }


    public static void main(String[] args) {
        MakingQuiz app = new MakingQuiz();
        app.setContentPane(app.jpanel);
        JButton btn = new JButton("HIEFS");
        app.setSize(1000, 600);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Hello World");
        app.setVisible(true);
    }
}

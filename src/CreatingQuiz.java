
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatingQuiz extends JFrame{
    private JButton btt;
    private JPanel jpanel;
    private JPanel good;

    public CreatingQuiz(){
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.Y_AXIS));
        good.setLayout(new BoxLayout(good, BoxLayout.Y_AXIS));
        btt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                good.add(new JLabel("Hello"));

                jpanel.revalidate();
            }
        });
    }

    public static void main(String[] args) {

        CreatingQuiz appme = new CreatingQuiz();
        JScrollPane scrollPane = new JScrollPane(appme.jpanel);
        appme.setContentPane(scrollPane);
        appme.setSize(1200, 750);
        appme.setResizable(true);
        appme.setDefaultCloseOperation(EXIT_ON_CLOSE);
        appme.setTitle("Creting Quiz");
        appme.setVisible(true);
    }

}

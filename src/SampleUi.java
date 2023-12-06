import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleUi extends JFrame{
    private JPanel jpanel;
    private JTextField tfAnswer;
    private JButton bCheck;


    public SampleUi(){
        bCheck.setText("Check Answer");
        bCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bb = tfAnswer.getText();
                if(bb.equals("")){
                    JOptionPane.showConfirmDialog(null,"Enter a value");
                }
                else{
                    JOptionPane.showConfirmDialog(null,"Good");
                }
            }
        });
    }
    public static void main(String [] args){
        SampleUi m = new SampleUi();
        m.setContentPane(m.jpanel);
        m.setSize(500, 300);
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);
        m.setVisible(true);
    }
}

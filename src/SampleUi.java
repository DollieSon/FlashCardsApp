import javax.swing.*;

public class SampleUi extends JFrame{
    private JPanel jpanel;
    private JTextField tfAnswer;
    private JButton bCheck;


    public static void main(String [] args){
        SampleUi m = new SampleUi();
        m.setContentPane(m.jpanel);
        m.setSize(500, 300);
        m.setDefaultCloseOperation(EXIT_ON_CLOSE);
        m.setVisible(true);
    }
}

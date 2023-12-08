
import javax.swing.*;

public class TakeQuiz extends JFrame{

    private JPanel JTakequiz;
    private JProgressBar progressBar1;
    private JButton previousButton;
    private JButton nextButton;
    private JButton submitButton;
    private JPanel JPCorrectAnswerPanel;

    public TakeQuiz(){
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        TakeQuiz appme = new TakeQuiz();
        JScrollPane scrollPane = new JScrollPane(appme.JTakequiz);
        appme.setContentPane(scrollPane);
        appme.setSize(1200, 750);
        appme.setResizable(true);
        appme.setDefaultCloseOperation(EXIT_ON_CLOSE);
        appme.setTitle("Creting Quiz");
        appme.setVisible(true);
    }

}

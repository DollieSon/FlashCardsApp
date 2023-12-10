import UIs.MakingQuiz;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static MakingQuiz app;
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        app = new MakingQuiz();
        JScrollPane scrollPane = new JScrollPane(app.jpanel);
        app.setContentPane(scrollPane);
        app.setSize(1200, 750);
        app.setResizable(false);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Making Quiz");
        app.setVisible(true);

    }
}
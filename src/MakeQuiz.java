import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MakeQuiz extends JFrame {

    private JPanel containerPanel;

    public MakeQuiz() {
        setTitle("MakeQuiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to act as the container for existing and new components
        containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        // Existing contents (replace this with your existing components)
        JPanel Typecont = new JPanel();
        containerPanel.add(Typecont);

        JRadioButton multiple_choice = new JRadioButton();
        multiple_choice.setText("Multiple Choice");

        JRadioButton identification = new JRadioButton();
        identification.setText("Identification");

        JRadioButton TrueorFalse = new JRadioButton();
        TrueorFalse.setText("Identification");

        Typecont.add(multiple_choice);
        Typecont.add(identification);
        Typecont.add(TrueorFalse);

        Typecont.revalidate();
        Typecont.repaint();

        containerPanel.revalidate();
        containerPanel.repaint();



        // Create a JScrollPane and add the container panel to it
        JScrollPane scrollPane = new JScrollPane(containerPanel);

        // Create a button to dynamically add a JLabel
        JButton addButton = new JButton("Add JLabel");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewLabel();
            }
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        // Set up the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    private void addNewLabel() {
        // Create a new JLabel and add it to the containerPanel
        JLabel newLabel = new JLabel("New Label");
        containerPanel.add(newLabel);

        // Refresh the view
        containerPanel.revalidate();
        containerPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DynamicScrollPaneExample().setVisible(true);
        });
    }
}


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FolderForQuiz extends JFrame{
    private JPanel JPFolderQuizPanel;
    private JScrollPane JSPFolderScrollPane;
    private JPanel JPFolderContainerPanel;
    private JPanel JPFolderSubjectContainerPanel;
    private JLabel JLSubjectLabel;
    private JScrollPane JSPSubjectContent;
    private JPanel JPSubjectContent;
    private JLabel JLSubjectContent;


    public FolderForQuiz(){

        JSPSubjectContent.setPreferredSize(new Dimension(500,JSPSubjectContent.getPreferredSize().height));
        JSPSubjectContent.setBorder(new EmptyBorder(0,0,0,0));

    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        FolderForQuiz folderquiz = new FolderForQuiz();
        folderquiz.setContentPane(folderquiz.JPFolderContainerPanel);
        folderquiz.setSize(1200, 750);
        folderquiz.setResizable(false);
        folderquiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
        folderquiz.setTitle("Folder");
        folderquiz.setVisible(true);

    }
}

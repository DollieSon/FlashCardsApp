package FolderForUserComponent;

import javax.swing.*;
import java.awt.*;

public class QuizSecondSection extends FolderQuizContainer{

    public QuizSecondSection(String q, int l){
        super(q,l);
    }
    public JPanel addsection(){
        JPanel secondsection = super.addsection();
        secondsection.setBackground(new Color(96, 114, 116));
        secondsection.setLayout(new BoxLayout(secondsection,BoxLayout.Y_AXIS));
        secondsection.setMinimumSize(new Dimension(150,200));
        secondsection.setPreferredSize(new Dimension(150,200));
        secondsection.setMaximumSize(new Dimension(150,200));
        return secondsection;
    }
}

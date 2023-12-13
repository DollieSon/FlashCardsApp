package FolderForUserComponent;

import UIs.FolderForQuiz;

import javax.swing.*;

public class FolderQuizContainer {
    String quizName;

    int quizlength;
    public FolderQuizContainer(String q,int l){
        this.quizName = q;
        this.quizlength = l;
    }
    public JPanel addsection(){
        JPanel section = new JPanel();
        section.setLayout(new BoxLayout(section,BoxLayout.Y_AXIS));
        return section;

    }
}

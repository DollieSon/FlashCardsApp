package FolderUser;

import QuizPackage.Quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Folder implements Serializable {
    private String name;
    private List<Quiz> quizzes;

    public Folder(String name){
        quizzes = new ArrayList<>();
        this.name = name;
    }

    public void createQuiz(Quiz e){
        quizzes.add(e);
    }

    public void deleteQuiz(String quizName){
        quizzes.stream().filter(q -> q.getQuizName().equals(quizName)).forEach(q -> quizzes.remove(q));
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<Quiz> getQuiz(){
        return quizzes;
    }

}

import QuizPackage.Quiz;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    private String name;
    private final String author;

    private List<Quiz> quizzes;

    public Folder(String name, String author){
        quizzes = new ArrayList<>();
        this.name = name;
        this.author = author;
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


}

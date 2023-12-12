package Card;

import java.io.Serializable;

// TODO Make an inteface
public abstract class Card implements Serializable {
    private String Question;
    private String Answer;

    protected Card(String question, String answer) {
        Question = question;
        Answer = answer;
    }

    public Card setQuestion(String question_name){
        this.Question = question_name;
        return this;
    }
    public String getQuestion() { return Question; }

    public Card setAnswer(String Answer){
        this.Answer = Answer;
        return this;
    }
    public String getAnswer(){
        return this.Answer;
    }

    public boolean checkAnswer(Object Answer){
        return this.getAnswer().equals((String) Answer);
    }
}
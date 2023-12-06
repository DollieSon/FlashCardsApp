package Card;

public abstract class Card implements Answer{
    private String Question;

    public void setQuestion(String question_name){
        this.Question = question_name;
    }
    public String getQuestion(){
        return Question;
    }
}
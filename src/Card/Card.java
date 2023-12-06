package Card;

public abstract class Card{
    private String Question;
    private String Answer;
    public void setQuestion(String question_name){
        this.Question = question_name;
    }
    public String getQuestion(){
        return Question;
    }

    public Card setAnswer(String Answer){
        this.Answer = Answer;
    }

    public String getAnswer(){
        return this.Answer;
    }


    public boolean checkAnswer(Object Answer){
        return this.getAnswer().equals((String) Answer);
    }

}
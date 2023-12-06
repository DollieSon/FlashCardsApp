import java.util.List;

public class Quiz {
    private String QuizName;
    private List<Card> Questions;
    private String Author;

    public void setName(String Name){
        QuizName = Name;
    }

    public Quiz addCard(Card card){
        Questions.add(card);
        return this;
    }

    public String getQuizName(){
        return QuizName;
    }

    //TODO IMPLEMENT
    public void RemoveCard( Card card){


    }

}

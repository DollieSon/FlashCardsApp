import Card.Card;

import java.util.List;

public class Quiz {
    private List<Card> Questions;
    private String QuizName;
    private String Author;

    public void setQuizName(String Name) { QuizName = Name; }
    public String getQuizName() { return QuizName; }

    public Quiz addCard(Card card) {
        Questions.add(card);
        return this;
    }

    //TODO IMPLEMENT
    public void removeCard(Card card) {

    }
}

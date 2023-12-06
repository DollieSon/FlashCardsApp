import Card.Card;

import java.util.List;

public class Quiz {
    private List<Card> cards;
    private String QuizName;
    private String Author;

    public void setQuizName(String Name) { QuizName = Name; }
    public String getQuizName() { return QuizName; }

    public Quiz addCard(Card card) {
        cards.add(card);
        return this;
    }

    //TODO IMPLEMENT
    public void removeCard(Card card) {

    }


}

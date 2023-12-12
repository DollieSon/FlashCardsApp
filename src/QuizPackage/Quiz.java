package QuizPackage;

import Card.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable {
    private List<Card> cards;
    private String QuizName;
    private String Author;

    public Quiz(){
        cards = new ArrayList<Card>();
    }


    public Quiz setQuizName(String Name) {
        QuizName = Name;
        return this;
    }
    public String getQuizName() { return QuizName; }

    public Quiz addCard(Card card) {
        cards.add(card);
        return this;
    }

    public Card getCard(int index){
        return cards.get(index);
    }
    public List<Card> getCards(){
        return cards;
    }

    public String getAuthor(){
        return Author;
    }

    public Quiz setAuthor(String Author){
        this.Author = Author;
        return this;
    }


    //TODO IMPLEMENT
    public void removeCard(Card card){
            cards.remove(card);
    }


}

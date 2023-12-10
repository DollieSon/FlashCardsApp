package Card;

import java.util.ArrayList;
import java.util.List;
// Todo make an interface
public class CardFactory{
    public enum type {
        TRUE_OR_FALSE, MULTIPLE_CHOICE,IDENTIFICATION
    };

    public static Card MakeCard(type Type, String Question, String Answer){
        Card card = MakeCard(Type);
        card.setAnswer(Answer);
        card.setQuestion(Question);
        return card;
    }
    public static Card MakeCard(type Type){
        Card card = null;
        switch (Type){
            case TRUE_OR_FALSE:
                card = new TrueOrFalseCard();
                break;
            case MULTIPLE_CHOICE:
                card = new MultipleChoiceCard();
                //dire lang siguro pag ask og choices
                //((MultipleChoiceCard) card).setChoices(Choices);
                break;
            case IDENTIFICATION:
                card = new IdentificationCard();
                break;
        }
        return card;
    }
}

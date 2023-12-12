package Card;

import java.util.ArrayList;
import java.util.List;
// Todo make an interface
public class CardFactory{
    public static int TRUE_OR_FALSE = 1;
    public enum type {
        TRUE_OR_FALSE, MULTIPLE_CHOICE,IDENTIFICATION
    };

    public static Card MakeCard(type Type, String Question, String Answer){
        Card card = null;
        switch (Type){
            case TRUE_OR_FALSE:
                card = new TrueOrFalseCard(Question,Answer);
                break;
            case MULTIPLE_CHOICE:
                card = new MultipleChoiceCard(Question,Answer);
                //dire lang siguro pag ask og choices
                //((MultipleChoiceCard) card).setChoices(Choices);
                break;
            case IDENTIFICATION:
                card = new IdentificationCard(Question,Answer);
                break;
        }
        card.setAnswer(Answer);
        card.setQuestion(Question);
        return card;
    }
}

package Card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory{
    public static int TRUE_OR_FALSE = 1;
    public enum type {
        TRUE_OR_FALSE, MULTIPLE_CHOICE,IDENTIFICATION
    };

    public static Card MakeCard(type Type, String Question, String Answer, ArrayList<String> Choices){
        Card card = null;
        switch (Type){
            case TRUE_OR_FALSE:
                card = new TrueOrFalseCard();
            case MULTIPLE_CHOICE:
                card = new MultipleChoiceCard();
                ((MultipleChoiceCard) card).setChoices(Choices);
            case IDENTIFICATION:
                card = new IdentificationCard();
        }
        card.setAnswer(Answer);
        card.setQuestion(Question);
        return card;
    }
}

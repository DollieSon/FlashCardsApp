package CardComponent;

import Card.Card;

public class CardComponentFactory{

    public enum type{
        True_False,MultipleChoice,Identification
    }
    public static CardComponent getCardComponent(type CardType){
        CardComponent Cp = null;
        switch (CardType){
            case True_False:
                Cp = new TrueOrFalseComponent();
                break;
            case MultipleChoice:
                Cp = new MultipleChoiceComponent();
                break;
            case Identification:
                Cp = new IdentificationComponent();
                break;
        }
        return Cp;
    }

}

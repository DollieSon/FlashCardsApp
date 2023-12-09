package CardComponent;

import Card.Card;

import javax.swing.border.EmptyBorder;

public class ComponentOptions {
    public static final int maxCardWidth = 600;
    public static final int maxCardHeight = 200;


    private static EmptyBorder TextFieldBorder;
    private static EmptyBorder CardComponentBorder;
    public static EmptyBorder getTextFieldBorder(){
        if(TextFieldBorder == null){
            TextFieldBorder = new EmptyBorder(0,20,0,10);
        }
        return TextFieldBorder;
    }

    public static EmptyBorder getCardPanelBorder(){
        if(CardComponentBorder == null){
            CardComponentBorder = new EmptyBorder(20,20,20,20);
        }
        return CardComponentBorder;
    }


}

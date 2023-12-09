package CardComponent;

import Card.Card;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TrueOrFalseComponent extends CardComp{

    public TrueOrFalseComponent(){
        super();
        JRadioButton trueRadioButton = new JRadioButton("True");
        JRadioButton flaseRadioButton = new JRadioButton("False");
        JPanel answerField = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueRadioButton);
        buttonGroup.add(flaseRadioButton);
        answerField.add(trueRadioButton);
        answerField.add(flaseRadioButton);
        answerField.setBorder(ComponentOptions.getTextFieldBorder());
        Answer.add(answerField);
    }

    //TODO implement getAnswerInput()
    @Override
    public String getAnswerInput() {

        return null;
    }
}

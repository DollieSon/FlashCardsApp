
package CardComponent;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IdentificationComponent extends CardComp{
        public IdentificationComponent(){
            super();
            JTextField answerField = new JTextField();
            answerField.setBorder(ComponentOptions.getTextFieldBorder());
            Answer.setBorder(ComponentOptions.getCardPanelBorder());
            Answer.add(answerField);
        }

        public String getAnswerInput(){
            return ((JTextField)Answer.getComponent(1)).getText();
        }
        //TODO Add Button Listeners Here


        //TODO Implement Event Listeners
}

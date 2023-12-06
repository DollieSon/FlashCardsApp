package Card;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceCard extends Card {
    private List<String> Choices;

    public MultipleChoiceCard(){
        choices = new ArrayList<String>();

    }

    public List<String> getChoices(){
      return Choices;
    };

    public void setChoices(ArrayList<String> choices){
        this.choices = choices;
    }

    public void addChoices(String Choice){
        Choices.add(Choice);
    }

}

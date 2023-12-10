package Card;

import CardComponent.MultipleChoiceComponent;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceCard extends Card {
    private List<String> choices;

    public MultipleChoiceCard(){
        choices = new ArrayList<String>();

    }

    public List<String> getChoices(){
      return choices;
    };

    public MultipleChoiceCard setChoices(List<String> choices){
        this.choices = choices;
        return this;
    }

    public MultipleChoiceCard addChoices(String Choice){
        choices.add(Choice);
        return this;
    }

}

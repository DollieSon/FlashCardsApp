package Card;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceCard extends Card {
    private ArrayList<String> choices;

    public MultipleChoiceCard(String Question, String Answer){
        super(Question,Answer);
        choices = new ArrayList<String>();
    }

    public List<String> getChoices(){
      return choices;
    };

    public void setChoices(ArrayList<String> choices){
        this.choices = choices;
    }

    public void addChoices(String Choice){
        choices.add(Choice);
    }

}

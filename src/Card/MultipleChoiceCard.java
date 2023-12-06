package Card;

import java.util.List;

public class MultipleChoiceCard extends Card {
    private List<String> Choices;

    public List<String> getChoices(){
      return Choices;
    };
    public void addChoices(String Choice){
        Choices.add(Choice);
    }

}

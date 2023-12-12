package Card;

public class TrueOrFalseCard extends MultipleChoiceCard{
    public TrueOrFalseCard(String Question, String Answer){
        super(Question,Answer);
        addChoices("True");
        addChoices("False");
    }




}

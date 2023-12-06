package Card;

import Card.Card;

public class IdentificationCard extends Card {
    private String Answer;

    public void setAnswer(String Answer){
        this.Answer = Answer;
    }
    public String getAnswer(){
        return Answer;
    }

    public void getAnswer(){

    }

    public boolean checkAnswer() {
        return true;
    }
}

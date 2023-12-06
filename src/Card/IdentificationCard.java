package Card;

public class IdentificationCard extends Card {
    private String answer;

    @Override
    public void getAnswer() {
        // IMPLEMENT
    }

    public void setAnswer(String Answer) { this.answer = Answer; }

    @Override
    public boolean checkAnswer() { return false; }
}

package Card;

public class IdentificationCard extends Card {
    private String answer;

    @Override
    public boolean checkAnswer() {
        return false;
    }

    @Override
    public void getAnswer() {
        // IMPLEMENT
    }

    public void setAnswer(String Answer) { this.answer = Answer; }


    public boolean checkAnswer(String Answer) {
        return answer.equals(Answer);
    }
}

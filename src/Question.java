/*Fucntions:
    getQuestion
    getAnswer
    getChoices
 */

import java.util.List;

public abstract class Question {

    abstract String getQuestion();
    abstract String getAnswer();
    abstract List<String> getChoices();

    abstract void setQuestion(String question);
    abstract void setAnswer(String answer);
    abstract void setChoices(List<String> choices);
}

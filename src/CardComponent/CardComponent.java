package CardComponent;

import javax.swing.*;

public interface CardComponent {
    public String getQuestionInput();
    public String getAnswerInput();
    public CardComponent setQuestion(JPanel Question);
    public CardComponent setAnswer(JPanel Answer);
    public JPanel getComponent(JPanel MeComp);
}

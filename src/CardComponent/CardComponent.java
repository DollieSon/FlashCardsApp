package CardComponent;

import javax.swing.*;
import java.util.List;

public interface CardComponent {
    public String getQuestionInput();
    public String getAnswerInput();
    public CardComponent setQuestion(JPanel Question);
    public CardComponent setAnswer(JPanel Answer);
    public JPanel getComponent(JPanel MeComp, List<CardComponent> GlobalList);
}

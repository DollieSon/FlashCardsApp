package UIs;

import Card.*;
import CardComponent.*;
import CardComponent.CardComponentFactory;
import QuizPackage.Quiz;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MakingQuiz extends JFrame{

    public final int maxCarWidth = 600;
    public final int maxCarHeight = 200;
    public JTextField tFTitle;
    public JPanel jpanel;
    public JButton saveButton;
    public JLabel LAuthor;
    public JLabel LBy;
    public JLabel Ltitle;
    public JButton BaddQuestion;
    public JRadioButton RBmultipleChoice;
    public JRadioButton RBidentification;
    public JRadioButton RBtrueOrFalse;
    public JLabel Ltype;
    public JPanel jpCard;
    public JPanel JpTypes;
    public JPanel JSPQuestionCont;
    public JScrollPane JSPContainer;

    private java.util.List<CardComponent> MyCards;

    private java.util.List<JRadioButton> jr;
    int count;
    Card c;

    Quiz holdertype;

    public MakingQuiz(){
        MyCards = new ArrayList<CardComponent>();
        jr = new ArrayList<>();
        jr.add(RBidentification);
        jr.add(RBmultipleChoice);
        jr.add(RBtrueOrFalse);

        JpTypes.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JSPQuestionCont.setLayout(new BoxLayout(JSPQuestionCont, BoxLayout.Y_AXIS));

        JSPContainer.setMinimumSize(new Dimension(600,600));
        JSPContainer.setMaximumSize(new Dimension(600,600));
        JSPContainer.setPreferredSize(new Dimension(600,600));
        count = 0;
        c = null;
        this.holdertype = holdertype;

       BaddQuestion.addActionListener(addQuestionListener());
        //Saving
        saveButton.addActionListener(saveButtonListener());
    }

    public MakingQuiz(Quiz prequiz){
        this();
        JPanel JP = null;
        CardComponent Cp;
        tFTitle.setText(prequiz.getQuizName());
        LAuthor.setText(prequiz.getAuthor());
        for(Card card:prequiz.getCards()){
            if(card instanceof TrueOrFalseCard){
                Cp = CardComponentFactory.getCardComponent(CardComponentFactory.type.True_False);
            }else if(card instanceof MultipleChoiceCard){
                Cp = CardComponentFactory.getCardComponent(CardComponentFactory.type.MultipleChoice);
                
            }else if(card instanceof IdentificationCard){
                Cp = CardComponentFactory.getCardComponent(CardComponentFactory.type.Identification);
            }
        }
    }

    public void Reload(){
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();
    }

    public ActionListener addQuestionListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel WholePanel = null;
                CardComponent TempCardComp;
                if(RBidentification.isSelected()){
                    TempCardComp = CardComponentFactory.getCardComponent(CardComponentFactory.type.Identification);
                }else if(RBmultipleChoice.isSelected()){
                    TempCardComp = CardComponentFactory.getCardComponent(CardComponentFactory.type.MultipleChoice);
                }else if(RBtrueOrFalse.isSelected()){
                    TempCardComp = CardComponentFactory.getCardComponent(CardComponentFactory.type.True_False);
                }else{
                    return;
                }
                WholePanel = TempCardComp.getComponent(JSPQuestionCont,MyCards);
                MyCards.add(TempCardComp);
                JSPQuestionCont.add(WholePanel);
                System.out.println("Count:" + MyCards.size());
                Reload();
                count++;
            }
        };
    }

    public ActionListener saveButtonListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int card_index = 0;

                if(tFTitle.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No Title");
                    return;
                }else{
                    holdertype.setQuizName(tFTitle.getText());
                }
                if(JSPQuestionCont.getComponentCount() == 0){
                    JOptionPane.showMessageDialog(null,"No Questions");
                    return;
                }
                for(CardComponent tempComp: MyCards){
                    Card card = null;
                    String question = tempComp.getQuestionInput();
                    String answer = tempComp.getAnswerInput();
                    if(tempComp instanceof TrueOrFalseComponent){
                        card = CardFactory.MakeCard(CardFactory.type.TRUE_OR_FALSE);
                    }else if(tempComp instanceof MultipleChoiceComponent){
                        card = CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE);
                        ((MultipleChoiceCard) card).setChoices(
                                ((MultipleChoiceComponent) tempComp).getChoices()
                        );
                    }else if(tempComp instanceof IdentificationComponent){
                        card = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION);
                    }
                    card.setQuestion(question)
                            .setAnswer(answer);
                    holdertype.addCard(card);
                }
                holdertype.setAuthor("None Set");
                JOptionPane.showMessageDialog(null,"Saved");
            }
        };
    }


    public Quiz getQuiz(){
        return holdertype;
    }
}

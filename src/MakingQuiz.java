import Card.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;


public class MakingQuiz extends JFrame{

    private final int maxCarWidth = 600;
    private final int maxCarHeight = 200;
    private JTextField tFTitle;
    private JPanel jpanel;
    private JButton saveButton;
    private JLabel LAuthor;


    private JLabel LBy;
    private JLabel Ltitle;
    private JButton BaddQuestion;
    private JRadioButton RBmultipleChoice;
    private JRadioButton RBidentification;
    private JRadioButton RBtrueOrFalse;
    private JLabel Ltype;
    private JPanel jpCard;
    private JPanel JpTypes;
    private JPanel JSPQuestionCont;


    private java.util.List<JRadioButton> jr;

    int count = 0;



    public MakingQuiz(){
        jr = new ArrayList<>();
        jr.add(RBidentification);
        jr.add(RBmultipleChoice);
        jr.add(RBtrueOrFalse);

        JpTypes.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JSPQuestionCont.setLayout(new BoxLayout(JSPQuestionCont, BoxLayout.Y_AXIS));

       BaddQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*for(Card c: thisQuiz.getCards()){
                    if(c instanceof IdentificationCard){
                            if(c.getQuestion().isEmpty()||c.getAnswer().isEmpty()){
                                System.out.println("error");
//                                thisQuiz.getCards().remove(c);
                                return;
                            }
                    }
                }*/



                if(RBidentification.isSelected()){
                    addIdentificationCard();
                }else if(RBmultipleChoice.isSelected()){
                    addMultipleChoiceCard();
                }else if(RBtrueOrFalse.isSelected()){
                    addTrueOrFalseCard();
                }

                count++;
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Component component : JSPQuestionCont.getComponents()) {
                    if (component instanceof JPanel) {
                        System.out.println("1");
                        JPanel questionPanel = (JPanel) component;
                        for (Component subComponent : questionPanel.getComponents()) {
                            System.out.println("2");
                            if(subComponent instanceof JPanel){
                                JPanel fieldPanel = (JPanel) component;
                                for(Component field: fieldPanel.getComponents()){
                                    System.out.println("3");
                                    if(field instanceof JTextField){
                                        if(((JTextField) field).getText().isEmpty()){
                                            JOptionPane.showMessageDialog(null,"Their is an empty text field");
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                JOptionPane.showMessageDialog(null,"Saving");
            }
        });
    }


    private void addIdentificationCard(){
        JPanel holddeletebutton = new JPanel();
        JButton deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setMaximumSize(new Dimension(150,20));
        holddeletebutton.add(deleteButton);
        holddeletebutton.setBorder(new EmptyBorder(0,0,10,0));

        JPanel WholePanel = new JPanel();
        JPanel questionContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));

        JPanel forQuestionPanel = new JPanel();
        forQuestionPanel.setLayout(new BoxLayout(forQuestionPanel, BoxLayout.X_AXIS));

        JPanel forAnswerPanel = new JPanel();
        forAnswerPanel.setLayout(new BoxLayout(forAnswerPanel, BoxLayout.X_AXIS));

        JLabel questionLabel = new JLabel("Question: ");
        JTextField questionField = new JTextField();

        questionField.setBorder(new EmptyBorder(0,20,0,10));

        JLabel answerLabel = new JLabel("Answer:    ");
        JTextField answerField = new JTextField();

        answerField.setBorder(new EmptyBorder(0,20,0,10));

        forQuestionPanel.add(questionLabel);
        forQuestionPanel.add(questionField);

        forAnswerPanel.add(answerLabel);
        forAnswerPanel.add(answerField);

        forQuestionPanel.setBorder(new EmptyBorder(20,20,20,20));
        forAnswerPanel.setBorder(new EmptyBorder(20,20,20,20));

        questionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionContainer.setPreferredSize(new Dimension(maxCarWidth,maxCarHeight));
        questionContainer.setMinimumSize(new Dimension(maxCarWidth,maxCarHeight));
        questionContainer.setMaximumSize(new Dimension(maxCarWidth,maxCarHeight));

        questionContainer.add(forQuestionPanel);
        questionContainer.add(forAnswerPanel);
        questionContainer.add(holddeletebutton);


        WholePanel.add(questionContainer);
        WholePanel.setBorder(new EmptyBorder(10,0,5,0));

        JSPQuestionCont.add(WholePanel);

        // Refresh the view
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();

        Card cc = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,questionField.getText(), answerField.getText());

        thisQuiz.addCard(cc);
        // Create a Card class
        /*class Card {
            // Declare the fields for the answer and question
            private String answer;
            private String question;

            // Create a constructor that takes the answer and question as parameters
            public Card(String answer, String question) {
                this.answer = answer;
                this.question = question;
            }

            // Optionally, you can add getters and setters for the fields
            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getQuestion() {
                return question;
            }

            public void setQuestion(String question) {
                this.question = question;
            }
        }


        BaddQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text from the fields
                String answer = answerField.getText();
                String question = questionField.getText();
                boolean valid = !answer.isEmpty() && !question.isEmpty();

                BaddQuestion.setEnabled(valid);

                // Create a new Card object with the answer and question


                // Add the card to the list
                thisQuiz.getCards().add(cc);

                // Optionally, you can clear the fields after adding the card
                answerField.setText("");
                questionField.setText("");
            }
        });*/


        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                thisQuiz.removeCard(cc);

                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );


        //return CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,questionField.getText(), answerField.getText());

    }

    private void addTrueOrFalseCard(){
        JPanel holddeletebutton = new JPanel();
        JButton deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setMaximumSize(new Dimension(150,20));
        holddeletebutton.add(deleteButton);
        holddeletebutton.setBorder(new EmptyBorder(0,0,10,0));

        JPanel WholePanel = new JPanel();
        JPanel questionContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));

        JPanel forQuestionPanel = new JPanel();
        forQuestionPanel.setLayout(new BoxLayout(forQuestionPanel, BoxLayout.X_AXIS));

        JPanel forAnswerPanel = new JPanel();
        forAnswerPanel.setLayout(new BoxLayout(forAnswerPanel, BoxLayout.X_AXIS));

        JLabel questionLabel = new JLabel("Question: ");
        JTextField questionField = new JTextField();

        questionField.setBorder(new EmptyBorder(0,20,0,10));

        JLabel answerLabel = new JLabel("Answer:    ");
        JRadioButton trueRadioButton = new JRadioButton("True");
        JRadioButton flaseRadioButton = new JRadioButton("False");
        JPanel answerField = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueRadioButton);
        buttonGroup.add(flaseRadioButton);
        answerField.add(trueRadioButton);
        answerField.add(flaseRadioButton);


        answerField.setBorder(new EmptyBorder(0,20,0,10));

        forQuestionPanel.add(questionLabel);
        forQuestionPanel.add(questionField);

        forAnswerPanel.add(answerLabel);
        forAnswerPanel.add(answerField);


        forQuestionPanel.setBorder(new EmptyBorder(20,20,20,20));
        forAnswerPanel.setBorder(new EmptyBorder(20,20,20,20));

        questionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionContainer.setPreferredSize(new Dimension(maxCarWidth,maxCarHeight));
        questionContainer.setMinimumSize(new Dimension(maxCarWidth,maxCarHeight));
        questionContainer.setMaximumSize(new Dimension(maxCarWidth,maxCarHeight));

        questionContainer.add(forQuestionPanel);
        questionContainer.add(forAnswerPanel);
        questionContainer.add(holddeletebutton);




        WholePanel.add(questionContainer);
        WholePanel.setBorder(new EmptyBorder(10,0,5,0));



        JSPQuestionCont.add(WholePanel);

        // Refresh the view
        JSPQuestionCont.revalidate();



        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);


                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
    }

    private void addMultipleChoiceCard(){
        JPanel holddeletebutton = new JPanel();
        JButton deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setMaximumSize(new Dimension(150,20));
        holddeletebutton.add(deleteButton);
        holddeletebutton.setBorder(new EmptyBorder(0,0,10,0));

        JPanel WholePanel = new JPanel();
        JPanel questionContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));

        JPanel forQuestionPanel = new JPanel();
        forQuestionPanel.setLayout(new BoxLayout(forQuestionPanel, BoxLayout.X_AXIS));


        JLabel questionLabel = new JLabel("Question: ");
        JTextField questionField = new JTextField();
        questionField.setPreferredSize(new Dimension(500,50));
        questionField.setMinimumSize(new Dimension(500,50));
        questionField.setMaximumSize(new Dimension(500,50));

        questionField.setBorder(new EmptyBorder(0,20,0,10));


        JPanel appendLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        appendLabel.setLayout(new BoxLayout(appendLabel, BoxLayout.X_AXIS));

        JLabel answerLabel = new JLabel("Choices");
        answerLabel.setBorder(new EmptyBorder(0,0,0,10));
        JButton addOption = new JButton("Add Option");
        appendLabel.add(answerLabel);
        appendLabel.add(addOption);


        forQuestionPanel.add(questionLabel);
        forQuestionPanel.add(questionField);


        JPanel encompase_answer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        encompase_answer.add(appendLabel);
        encompase_answer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        encompase_answer.setPreferredSize(new Dimension(500,200));
        encompase_answer.setMinimumSize(new Dimension(500,200));
        encompase_answer.setMaximumSize(new Dimension(500,200));


        forQuestionPanel.setBorder(new EmptyBorder(20,20,20,20));

        questionContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionContainer.setPreferredSize(new Dimension(maxCarWidth,350));
        questionContainer.setMinimumSize(new Dimension(maxCarWidth,350));
        questionContainer.setMaximumSize(new Dimension(maxCarWidth,350));
        encompase_answer.setLayout(new BoxLayout(encompase_answer, BoxLayout.Y_AXIS));


        questionContainer.add(forQuestionPanel);
        questionContainer.add(encompase_answer);
        questionContainer.add(holddeletebutton);



        ButtonGroup choiceGrp = new ButtonGroup();
        addOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(encompase_answer.getComponentCount() >= 5){
                    return;
                }

                JButton deleteOption = new JButton("Delete");


                JRadioButton choiceRadio = new JRadioButton();
                choiceGrp.add(choiceRadio);

                JTextField option = new JTextField();
                option.setPreferredSize(new Dimension(200,25));
                option.setMinimumSize(new Dimension(200,25));
                option.setMaximumSize(new Dimension(200,25));
                option.setBorder(new EmptyBorder(0,20,0,10));

                JPanel forOption = new JPanel();

                forOption.setLayout(new BoxLayout(forOption, BoxLayout.X_AXIS));
                forOption.setBorder(new EmptyBorder(10,0,0,0));
                forOption.add(choiceRadio);
                forOption.add(option);
                forOption.add(deleteOption);
                encompase_answer.add(forOption);

                questionContainer.revalidate();

                System.out.println(encompase_answer);

                deleteOption.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        encompase_answer.remove(forOption);
                        count--;
                        JSPQuestionCont.revalidate();
                        JSPQuestionCont.repaint();
                    }
                });

            }
        });






        WholePanel.add(questionContainer);
        WholePanel.setBorder(new EmptyBorder(10,0,5,0));



        JSPQuestionCont.add(WholePanel);

        // Refresh the view
        JSPQuestionCont.revalidate();



        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
    }









    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        MakingQuiz app = new MakingQuiz();
        app.setContentPane(app.jpanel);
        app.setSize(1200, 700);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Making Quiz");
        app.setVisible(true);
    }
}

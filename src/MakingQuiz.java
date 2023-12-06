import Card.*;


import javax.swing.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Optional;


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
        jr = new ArrayList<JRadioButton>();
        jr.add(RBidentification);
        jr.add(RBmultipleChoice);
        jr.add(RBtrueOrFalse);

        JpTypes.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JSPQuestionCont.setLayout(new BoxLayout(JSPQuestionCont, BoxLayout.Y_AXIS));

       BaddQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RBidentification.isSelected()){
                    addIdentificationCard();
                }else if(RBmultipleChoice.isSelected()){
                    addMultipleChoiceCard();
                }else if(RBtrueOrFalse.isSelected()){
                    addTrueOrFalseCard();
                }
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void addIdentificationCard(){





        JPanel holdbut = new JPanel();
        JButton deleteCont = new JButton();
        deleteCont.setText("Delete");
        deleteCont.setMaximumSize(new Dimension(150,20));
        holdbut.add(deleteCont);
        holdbut.setBorder(new EmptyBorder(0,0,10,0));

        JPanel Whole = new JPanel();
        JPanel questionCont = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionCont.setLayout(new BoxLayout(questionCont, BoxLayout.Y_AXIS));

        JPanel forQuestion = new JPanel();
        forQuestion.setLayout(new BoxLayout(forQuestion, BoxLayout.X_AXIS));

        JPanel forAnswer = new JPanel();
        forAnswer.setLayout(new BoxLayout(forAnswer, BoxLayout.X_AXIS));

        JLabel questionLabel = new JLabel("Question: ");
        JTextField questionField = new JTextField();

        questionField.setBorder(new EmptyBorder(0,20,0,10));
//        questionfield.setMaximumSize(new Dimension(100,50));

        JLabel answerLabel = new JLabel("Answer:    ");
        JTextField answerField = new JTextField();

        answerField.setBorder(new EmptyBorder(0,20,0,10));

        forQuestion.add(questionLabel);
        forQuestion.add(questionField);

        forAnswer.add(answerLabel);
        forAnswer.add(answerField);


        forQuestion.setBorder(new EmptyBorder(20,20,20,20));
        forAnswer.setBorder(new EmptyBorder(20,20,20,20));

        questionCont.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionCont.setPreferredSize(new Dimension(maxCarWidth,maxCarHeight));
        questionCont.setMinimumSize(new Dimension(maxCarWidth,maxCarHeight));
        questionCont.setMaximumSize(new Dimension(maxCarWidth,maxCarHeight));

        questionCont.add(forQuestion);
        questionCont.add(forAnswer);
        questionCont.add(holdbut);

        Card card = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,questionField.getText(),answerField.getText());

        //Add to the list cards

        Whole.add(questionCont);
        Whole.setBorder(new EmptyBorder(10,0,5,0));


        JSPQuestionCont.add(Whole);

        // Refresh the view
        JSPQuestionCont.revalidate();



        deleteCont.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(Whole);
//                JSPQuestionCont.removeAll();
//                questionCont.remove(Whole);
//                questionCont.remove(forQuestion);
//                questionCont.remove(forAnswer);
//                questionCont.remove(deleteCont);


                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
    }

    private void addTrueOrFalseCard(){

        JPanel holdbut = new JPanel();
        JButton deleteCont = new JButton();
        deleteCont.setText("Delete");
        deleteCont.setMaximumSize(new Dimension(150,20));
        holdbut.add(deleteCont);
        holdbut.setBorder(new EmptyBorder(0,0,10,0));

        JPanel Whole = new JPanel();
        JPanel questionCont = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionCont.setLayout(new BoxLayout(questionCont, BoxLayout.Y_AXIS));

        JPanel forQuestion = new JPanel();
        forQuestion.setLayout(new BoxLayout(forQuestion, BoxLayout.X_AXIS));

        JPanel forAnswer = new JPanel();
        forAnswer.setLayout(new BoxLayout(forAnswer, BoxLayout.X_AXIS));

        JLabel questionLabel = new JLabel("Question: ");
        JTextField questionField = new JTextField();

        questionField.setBorder(new EmptyBorder(0,20,0,10));
//        questionfield.setMaximumSize(new Dimension(100,50));

        JLabel answerLabel = new JLabel("Answer:    ");
        JRadioButton trueBtn = new JRadioButton("True");
        JRadioButton falseBtn = new JRadioButton("False");
        JPanel answerField = new JPanel();
        ButtonGroup btngrp = new ButtonGroup();
        btngrp.add(trueBtn);
        btngrp.add(falseBtn);
        answerField.add(trueBtn);
        answerField.add(falseBtn);


        answerField.setBorder(new EmptyBorder(0,20,0,10));

        forQuestion.add(questionLabel);
        forQuestion.add(questionField);

        forAnswer.add(answerLabel);
        forAnswer.add(answerField);


        forQuestion.setBorder(new EmptyBorder(20,20,20,20));
        forAnswer.setBorder(new EmptyBorder(20,20,20,20));

        questionCont.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionCont.setPreferredSize(new Dimension(maxCarWidth,maxCarHeight));
        questionCont.setMinimumSize(new Dimension(maxCarWidth,maxCarHeight));
        questionCont.setMaximumSize(new Dimension(maxCarWidth,maxCarHeight));

        questionCont.add(forQuestion);
        questionCont.add(forAnswer);
        questionCont.add(holdbut);




        Whole.add(questionCont);
        Whole.setBorder(new EmptyBorder(10,0,5,0));


        JSPQuestionCont.add(Whole);

        // Refresh the view
        JSPQuestionCont.revalidate();



        deleteCont.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(Whole);
//                JSPQuestionCont.removeAll();
//                questionCont.remove(Whole);
//                questionCont.remove(forQuestion);
//                questionCont.remove(forAnswer);
//                questionCont.remove(deleteCont);


                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
    }

    private void addMultipleChoiceCard(){
         JPanel holdbut = new JPanel();
        JButton deleteCont = new JButton();
        deleteCont.setText("Delete");
        deleteCont.setMaximumSize(new Dimension(150,20));
        holdbut.add(deleteCont);
        holdbut.setBorder(new EmptyBorder(0,0,10,0));

        JPanel Whole = new JPanel();
        JPanel questionCont = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionCont.setLayout(new BoxLayout(questionCont, BoxLayout.Y_AXIS));

        JPanel forQuestion = new JPanel();
        forQuestion.setLayout(new BoxLayout(forQuestion, BoxLayout.X_AXIS));


        JLabel questionLabel = new JLabel("Question: ");
        JTextField questionField = new JTextField();
        questionField.setPreferredSize(new Dimension(500,50));
        questionField.setMinimumSize(new Dimension(500,50));
        questionField.setMaximumSize(new Dimension(500,50));

        questionField.setBorder(new EmptyBorder(0,20,0,10));
//        questionfield.setMaximumSize(new Dimension(100,50));
        JPanel appendLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        appendLabel.setLayout(new BoxLayout(appendLabel, BoxLayout.X_AXIS));

        JLabel answerLabel = new JLabel("Choices");
        answerLabel.setBorder(new EmptyBorder(0,0,0,10));
        JButton addOption = new JButton("Add Option");
        appendLabel.add(answerLabel);
        appendLabel.add(addOption);


        forQuestion.add(questionLabel);
        forQuestion.add(questionField);


        JPanel encompase_answer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        encompase_answer.add(appendLabel);
        encompase_answer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        encompase_answer.setPreferredSize(new Dimension(500,200));
        encompase_answer.setMinimumSize(new Dimension(500,200));
        encompase_answer.setMaximumSize(new Dimension(500,200));


        forQuestion.setBorder(new EmptyBorder(20,20,20,20));

        questionCont.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        questionCont.setPreferredSize(new Dimension(maxCarWidth,350));
        questionCont.setMinimumSize(new Dimension(maxCarWidth,350));
        questionCont.setMaximumSize(new Dimension(maxCarWidth,350));
        encompase_answer.setLayout(new BoxLayout(encompase_answer, BoxLayout.Y_AXIS));


        questionCont.add(forQuestion);
        questionCont.add(encompase_answer);
        questionCont.add(holdbut);



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

                questionCont.revalidate();
                /*JSPQuestionCont.add(Whole);

                // Refresh the view
                JSPQuestionCont.revalidate();*/

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






        Whole.add(questionCont);
        Whole.setBorder(new EmptyBorder(10,0,5,0));


        JSPQuestionCont.add(Whole);

        // Refresh the view
        JSPQuestionCont.revalidate();



        deleteCont.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(Whole);
//                JSPQuestionCont.removeAll();
//                questionCont.remove(Whole);
//                questionCont.remove(forQuestion);
//                questionCont.remove(forAnswer);
//                questionCont.remove(deleteCont);


                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
    }









    public static void main(String[] args) {
        MakingQuiz app = new MakingQuiz();
        app.setContentPane(app.jpanel);
        app.setSize(1200, 700);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Making Quiz");
        app.setVisible(true);
    }
}

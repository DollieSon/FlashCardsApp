package UIs;

import Card.*;
import QuizPackage.Quiz;
import QuizPackage.SerializationUtil;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    private JButton JBback;


    private java.util.List<JRadioButton> jr;

    private Quiz editorial = null;
    int count;
    Card c;

    Quiz holdertype;



    private static MakingQuiz instance = null;

    public static MakingQuiz getInstance() {
        if (instance == null) {
            instance = new MakingQuiz();
        }
        return instance;
    }

    public static MakingQuiz refreshInstance() {

        instance = new MakingQuiz();

        return instance;

    }

    private MakingQuiz(){

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
        holdertype = new Quiz();


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

                count++;
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Quiz quizzAppend = new Quiz();
                int card_index = 0;

                if(tFTitle.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No Title");
                    return;
                }
                else{
                    quizzAppend.setQuizName(tFTitle.getText());
                }
                if(JSPQuestionCont.getComponentCount() == 0){
                    JOptionPane.showMessageDialog(null,"No Questions");
                    return;
                }

                for (Component component : JSPQuestionCont.getComponents()) {
                    if(holdertype.getCard(card_index) instanceof IdentificationCard){
                        JPanel questionPanel = (JPanel) component;
                        String storeQuestion = "";
                        String storeAnswer = "";
                        int n = 0;
                        for (Component subComponent : questionPanel.getComponents()) {
                            if(subComponent instanceof JPanel){
                                JPanel fieldPanel = (JPanel) subComponent;
                                for(Component field: fieldPanel.getComponents()){
                                    JPanel infield = (JPanel) field;
                                    for(Component fields: infield.getComponents()){
                                        if(fields instanceof JTextField){
                                            if(((JTextField) fields).getText().isEmpty()){
                                                JOptionPane.showMessageDialog(null,"Please Fill Up the Text Field");
                                                return;
                                            }
                                            else{
                                                if(n == 0){
                                                    storeQuestion = ((JTextField) fields).getText();
                                                }
                                                else{
                                                    storeAnswer = ((JTextField) fields).getText();
                                                }
                                                n++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Card questionCard = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,storeQuestion,storeAnswer);
                        quizzAppend.addCard(questionCard);
                    }
                    else if(holdertype.getCard(card_index) instanceof TrueOrFalseCard){
                        JPanel questionPanel = (JPanel) component;
                        String storeQuestion = "";
                        String storeAnswer = "";
                        for (Component subComponent : questionPanel.getComponents()) {
                            if(subComponent instanceof JPanel){
                                JPanel fieldPanel = (JPanel) subComponent;
                                for(Component field: fieldPanel.getComponents()){
                                    JPanel infield = (JPanel) field;
                                    for(Component fields: infield.getComponents()){
                                        if(fields instanceof JTextField){
                                            if(((JTextField) fields).getText().isEmpty()){
                                                JOptionPane.showMessageDialog(null,"Please Fill Up the Question");
                                                return;
                                            }
                                            else{
                                                storeQuestion = ((JTextField) fields).getText();
                                            }
                                        }
                                        else if(fields instanceof JPanel){
                                            int radion_button_indicator = 0;
                                            JPanel radio_button_select = (JPanel) fields;
                                            for(Component radio_butt: radio_button_select.getComponents()){
                                                if(radio_butt instanceof JRadioButton){
                                                    if(((JRadioButton) radio_butt).isSelected() == false){
                                                        radion_button_indicator++;
                                                    }
                                                    if(((JRadioButton) radio_butt).isSelected() == true){
                                                        storeAnswer = ((JRadioButton) radio_butt).getText();
                                                    }
                                                }
                                            }
                                            if(radion_button_indicator == 2){
                                                JOptionPane.showMessageDialog(null,"Please Select True or False");
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Card questionCard = CardFactory.MakeCard(CardFactory.type.TRUE_OR_FALSE,storeQuestion,storeAnswer);
                        quizzAppend.addCard(questionCard);
                    }
                    else if(holdertype.getCard(card_index) instanceof MultipleChoiceCard){
                        int count_for_radio_button = 0;
                        String storeQuestion = "";
                        String storeAnswer = "";
                        JRadioButton radioAnswer = null;
                        ArrayList<String> choicestore = new ArrayList<>();
                        List<JRadioButton> choiceradio = new ArrayList<>();
                        if (component instanceof JPanel) {
                            JPanel questionPanel = (JPanel) component;
                            for (Component subComponent : questionPanel.getComponents()) {
                                if(subComponent instanceof JPanel){
                                    JPanel fieldPanel = (JPanel) subComponent;
                                    for(Component field: fieldPanel.getComponents()) {
                                        if (field instanceof JPanel) {
                                            JPanel contain_elements = (JPanel) field;
                                            for (Component cm : contain_elements.getComponents()) {
                                                if (cm instanceof JTextField) {
                                                    if (((JTextField) cm).getText().isEmpty()) {
                                                        JOptionPane.showMessageDialog(null, "Please Fill Up the Question");
                                                        return;
                                                    }
                                                    else{
                                                        storeQuestion = ((JTextField) cm).getText();
                                                    }
                                                }
                                                else if(cm instanceof JPanel && contain_elements.getComponentCount() == 1 && ((JPanel) cm).getComponentCount() == 2){
                                                    JOptionPane.showMessageDialog(null, "Add Choices");
                                                    return;
                                                }
                                                else if(cm instanceof JPanel){
                                                    JPanel choices = (JPanel) cm;
                                                    for(Component cn: choices.getComponents()){
                                                        if(cn instanceof JTextField){
                                                            if(((JTextField) cn).getText().isEmpty()){
                                                                JOptionPane.showMessageDialog(null, "Add Answer");
                                                                return;
                                                            }
                                                            else{
                                                                choicestore.add(((JTextField) cn).getText());
                                                            }
                                                        }
                                                        else if(cn instanceof JRadioButton){
                                                            choiceradio.add((JRadioButton) cn);
                                                            if(((JRadioButton) cn).isSelected()){
                                                                radioAnswer = ((JRadioButton) cn);
                                                                count_for_radio_button = count_for_radio_button+1;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    System.out.println("\n");
                                }
                            }
                        }
                        if(count_for_radio_button == 0){
                            JOptionPane.showMessageDialog(null, "Select the Correct Answer");
                            return;
                        }
                        else{
                            int findIndex = choiceradio.indexOf(radioAnswer);
                            storeAnswer = choicestore.get(findIndex);
                        }
                        Card questionCard = CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE,storeQuestion,storeAnswer);
                        ((MultipleChoiceCard)questionCard).setChoices(choicestore);
                        quizzAppend.addCard(questionCard);

                    }
                    card_index++;
                }

                if(editorial != null){
                   int n = FolderForQuiz.getInstance().folderfirst.getQuiz().indexOf(editorial);
                   File myobj = new File(editorial.getQuizName() + ".ser");
                   myobj.delete();
                    FolderForQuiz.getInstance().folderfirst.getQuiz().set(n,quizzAppend);

                }
                else{
                    FolderForQuiz.getInstance().folderfirst.createQuiz(quizzAppend);
                }
                try {
                    SerializationUtil.serialize(quizzAppend, "Quizes/"+quizzAppend.getQuizName()+".ser");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    editorial = null;
                }
                backtofolder();


            }
        });

        JBback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorial = null;
                backtofolder();
            }
        });
    }

    public void editorview(Quiz edit){
        tFTitle.setText(edit.getQuizName());
        for(Card cd: edit.getCards()){
            if(cd instanceof IdentificationCard){
                addIdentificationCard(cd);
            }
            else if(cd instanceof TrueOrFalseCard){
                addTrueOrFalseCard(cd);
            }
            else if(cd instanceof MultipleChoiceCard){
                addMultipleChoiceCard(cd);
            }
        }
        this.editorial = edit;
    }

    public void backtofolder(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exception){
            if(exception instanceof  UnsupportedLookAndFeelException ){
                JOptionPane.showMessageDialog(null,"UnsupportedLookAndFeelException occurred");
            }
            else if(exception instanceof  ClassNotFoundException){
                JOptionPane.showMessageDialog(null,"ClassNotFoundException occurred");
            }
            else if(exception instanceof InstantiationException){
                JOptionPane.showMessageDialog(null,"InstantiationException occurred");
            }
            else if(exception instanceof IllegalAccessException){
                JOptionPane.showMessageDialog(null,"Illegal Access Exception occurred");
            }
            else{
                JOptionPane.showMessageDialog(null,"An error occurred");
            }
        }

        MakingQuiz.getInstance().setVisible(false);
        FolderForQuiz folderquiz = FolderForQuiz.getInstance();
        folderquiz.setContentPane(folderquiz.JPFolderContainerPanel);
        folderquiz.setLocation(MakingQuiz.getInstance().getLocation());
        folderquiz.setSize(1200, 750);
        folderquiz.setResizable(false);
        folderquiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
        folderquiz.setTitle("FolderUser.Folder");
        folderquiz.setVisible(true);

        folderquiz.refreshQuizContainer();
    }


    private void addIdentificationCard(Card edit){
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
        questionField.setText(edit.getQuestion());

        questionField.setBorder(new EmptyBorder(0,20,0,10));

        JLabel answerLabel = new JLabel("Answer:    ");
        JTextField answerField = new JTextField();
        answerField.setText(edit.getAnswer());

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

        Card cc = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,null,null);
        holdertype.addCard(cc);

        // Refresh the view
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();


        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                holdertype.removeCard(cc);

                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );


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

        Card cc = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,null,null);
        holdertype.addCard(cc);

        // Refresh the view
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();


        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                holdertype.removeCard(cc);

                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );



    }

    public void addTrueOrFalseCard(Card edit){
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
        questionField.setText(edit.getQuestion());

        questionField.setBorder(new EmptyBorder(0,20,0,10));

        JLabel answerLabel = new JLabel("Answer:    ");
        JRadioButton trueRadioButton = new JRadioButton("True");
        JRadioButton flaseRadioButton = new JRadioButton("False");

        if(trueRadioButton.getText().equals(edit.getAnswer())){
            trueRadioButton.setSelected(true);
        }
        else{
            flaseRadioButton.setSelected(true);
        }

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

        Card cc = CardFactory.MakeCard(CardFactory.type.TRUE_OR_FALSE,null,null);
        holdertype.addCard(cc);

        // Refresh the view
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();


        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                holdertype.removeCard(cc);

                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
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

        Card cc = CardFactory.MakeCard(CardFactory.type.TRUE_OR_FALSE,null,null);
        holdertype.addCard(cc);

        // Refresh the view
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();


        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                holdertype.removeCard(cc);

                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
    }

    private void addMultipleChoiceCard(Card edit){
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
        questionField.setText(edit.getQuestion());
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

        WholePanel.add(questionContainer);
        WholePanel.setBorder(new EmptyBorder(10,0,5,0));



        JSPQuestionCont.add(WholePanel);

        Card cc = CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE,null,null);
        holdertype.addCard(cc);

        // Refresh the view
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();

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



        for(String str: ((MultipleChoiceCard)edit).getChoices()){
            if(encompase_answer.getComponentCount() >= 5){
                return;
            }

            JButton deleteOption = new JButton("Delete");


            JRadioButton choiceRadio = new JRadioButton();
            if(str.equals(edit.getAnswer())){
                choiceRadio.setSelected(true);
            }
            choiceGrp.add(choiceRadio);

            JTextField option = new JTextField();
            option.setText(str);
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



        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                holdertype.removeCard(cc);
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

        WholePanel.add(questionContainer);
        WholePanel.setBorder(new EmptyBorder(10,0,5,0));



        JSPQuestionCont.add(WholePanel);

        Card cc = CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE,null,null);
        holdertype.addCard(cc);

        // Refresh the view
        JSPQuestionCont.revalidate();
        JSPQuestionCont.repaint();


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


        deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                JSPQuestionCont.remove(WholePanel);

                holdertype.removeCard(cc);
                // Refresh the view
                JSPQuestionCont.revalidate();
                JSPQuestionCont.repaint();
            }
        } );
    }









    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        MakingQuiz app = MakingQuiz.getInstance();
        JScrollPane scrollPane = new JScrollPane(app.jpanel);
        app.setContentPane(scrollPane);
        app.setSize(1200, 750);
        app.setResizable(false);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Making Quiz");
        app.setVisible(true);

    }
}

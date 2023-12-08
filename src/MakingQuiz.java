import Card.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
    private JScrollPane JSPContainer;


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
                int card_index = 0;

                if(tFTitle.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No Title");
                    return;
                }
                if(JSPQuestionCont.getComponentCount() == 0){
                    JOptionPane.showMessageDialog(null,"No Questions");
                    return;
                }

                for (Component component : JSPQuestionCont.getComponents()) {
                    if(holdertype.getCard(card_index) instanceof IdentificationCard){

                    }
                    else if(holdertype.getCard(card_index) instanceof TrueOrFalseCard){
                        JPanel questionPanel = (JPanel) component;
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
                                        }
                                        else if(fields instanceof JPanel){
                                            int radion_button_indicator = 0;
                                            JPanel radio_button_select = (JPanel) fields;
                                            for(Component radio_butt: radio_button_select.getComponents()){
                                                if(radio_butt instanceof JRadioButton){
                                                    if(((JRadioButton) radio_butt).isSelected() == false){
                                                        radion_button_indicator++;
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
                    }
                    else if(holdertype.getCard(card_index) instanceof MultipleChoiceCard){
                        int count_for_radio_button = 0;
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
                                                        }
                                                        else if(cn instanceof JRadioButton){
                                                            if(((JRadioButton) cn).isSelected()){
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
                    }
                    card_index++;
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

        MakingQuiz app = new MakingQuiz();
        JScrollPane scrollPane = new JScrollPane(app.jpanel);
        app.setContentPane(scrollPane);
        app.setSize(1200, 750);
        app.setResizable(true);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Making Quiz");
        app.setVisible(true);

    }
}

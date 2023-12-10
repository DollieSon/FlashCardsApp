import Card.*;
import QuizPackage.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TakeQuiz extends JFrame{

    public JPanel JTakequiz;
    public JProgressBar JPBProgressbar;
    public JButton JBPreviousButton;
    public JButton JBNextButton;
    public JButton JBsubmitButton;
    public JPanel JPCorrectPanel;
    public JPanel JPTitleandDescription;
    public JPanel JPQuizComponents;
    public JLabel JLTitleLabel;
    public JLabel JLDescriptionLabel;
    public JPanel JPDescriptionPanel;
    public JPanel JPProgressPanel;
    public JLabel JLIncorrectLabel;
    public JLabel JLProgressLabel;
    public JLabel JLCorrectLabel;
    public JScrollPane JSPIncorrectScrollPanel;
    public JPanel JPIncorrectPanel;
    public JLabel JLIncorrect;
    public JScrollPane JSPCorrectScrollPanel;
    public JLabel JLCorrect;
    public JScrollPane JSPQuestionQuizScrollPanel;
    public JPanel JPQuestionQuizPanel;
    public JPanel JPButtonsPanel;
    public JPanel JPTitlePnaelContainer;
    public JPanel JPDescriptionPaneContainer;
    public JTextArea JTADescriptionContent;
    private JScrollPane JSPTitleScrollPanel;
    private JPanel JPTItleContentPanel;
    private JLabel JLTitleLabelContent;

    int cardcounter = 0;

    ArrayList<JRadioButton> ChoicesRadioButton = new ArrayList<>();

    JLabel questioncheck;

    JTextField identificationAnswer;

    JScrollPane errormessage = new JScrollPane();

    JRadioButton storeUserAnswer;

    boolean removeCardIndicator = false;

    public int maxProgress;




    Quiz quizcards = new Quiz();//list of cards to take

    public TakeQuiz(){
        JSPTitleScrollPanel.setBorder(new EmptyBorder(0,70,0,0));
        JPQuestionQuizPanel.setLayout(new BoxLayout(JPQuestionQuizPanel,BoxLayout.Y_AXIS));

        JSPTitleScrollPanel.setMinimumSize(new Dimension(900,50));
        JSPTitleScrollPanel.setMaximumSize(new Dimension(900,50));
        JSPTitleScrollPanel.setPreferredSize(new Dimension(900,JSPTitleScrollPanel.getPreferredSize().height));


        //placing cards in the quizcards list(This is just for testing, it might/will change depending on how you get your data from a file)
        Card cc1= CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,"1What is Gitksadjfjkjjjjjjjjjosk","Git");

        Card cc2= CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE,"2Pick the word aasdfdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffffffffhihih","aa");
        ((MultipleChoiceCard)cc2).addChoices("sdkjfnjksdnfkjdas");
        ((MultipleChoiceCard)cc2).addChoices("aa");
        ((MultipleChoiceCard)cc2).addChoices("lsdjfjljasldkf");
        ((MultipleChoiceCard)cc2).addChoices(";asdfsdf");

        Card cc3 = CardFactory.MakeCard(CardFactory.type.TRUE_OR_FALSE,"3Is your father your parent?","True");
        Card cc4 = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,"4Hello. Say Hi","Hi");

        Card cc5= CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE,"5Pick the word bbb","bbb");
        ((MultipleChoiceCard)cc5).addChoices("aa");
        ((MultipleChoiceCard)cc5).addChoices("bbb");
        ((MultipleChoiceCard)cc5).addChoices("c");

        quizcards.addCard(cc1);
        quizcards.addCard(cc2);
        quizcards.addCard(cc3);
        quizcards.addCard(cc4);
        quizcards.addCard(cc5);
        quizcards.addCard(cc5);

        quizcards.setQuizName("testingtesting");
        quizcards.getQuizName();



        //Placing quiestions on the Container(do not change ths)
        if(quizcards.getCards().get(cardcounter) instanceof IdentificationCard){
            showIdentification(cardcounter);
        }
        else if(quizcards.getCards().get(cardcounter) instanceof TrueOrFalseCard){
            showTrueorFalse(cardcounter);
        }
        else if(quizcards.getCards().get(cardcounter) instanceof MultipleChoiceCard){
            showMultipleChoice(cardcounter);
        }

        maxProgress = quizcards.getCards().size();
        JPBProgressbar.setMinimum(0);
        JPBProgressbar.setMaximum(maxProgress);
        JPBProgressbar.setStringPainted(true);
        JLProgressLabel.setText("0/"+maxProgress);

        JLTitleLabelContent.setText(quizcards.getQuizName());
        JBNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(removeCardIndicator == true){
                    quizcards.getCards().remove(cardcounter);
                    cardcounter--;
                }
                if(quizcards.getCards().size() == 0){
                    JPIncorrectPanel.setBackground(null);
                    JPCorrectPanel.setBackground(null);
                    JPQuestionQuizPanel.removeAll();


                    removeCardIndicator = false;

                    JPanel scoreTitlePanel = new JPanel();
                    scoreTitlePanel.setLayout(new BoxLayout(scoreTitlePanel,BoxLayout.X_AXIS));

                    JLabel scoreTitleLabel = new JLabel("Your Score: ");
                    scoreTitleLabel.setFont(new Font("",Font.PLAIN,30));

                    JLabel yourScore = new JLabel(JLCorrect.getText()+"/"+maxProgress);
                    yourScore.setFont(new Font("",Font.BOLD,40));

                    scoreTitlePanel.add(scoreTitleLabel);
                    scoreTitlePanel.add(yourScore);

                    JPQuestionQuizPanel.add(scoreTitlePanel);

                    JPanel ScoredisplayPanel = new JPanel();
                    ScoredisplayPanel.setLayout(new BoxLayout(ScoredisplayPanel,BoxLayout.Y_AXIS));


                    JProgressBar FinalScore = new JProgressBar(0,maxProgress);
                    FinalScore.setStringPainted(true);
                    FinalScore.setValue(Integer.parseInt(JLCorrect.getText()));

                    double gg = FinalScore.getPercentComplete();
                    System.out.println(gg);

                    JLabel message = new JLabel();
                    message.setBorder(new EmptyBorder(50,150,0,0));

                    if(gg >= 0.60 && gg <0.80){
                        FinalScore.setForeground(Color.BLUE);
                        message.setText("Great Job!!!");
                        message.setFont(new Font("",Font.BOLD,20));
                    }
                    else if(gg >=0.80){
                        FinalScore.setForeground(Color.GREEN);
                        message.setText("Genius!!!");
                        message.setFont(new Font("",Font.BOLD,20));
                    }
                    else{
                        FinalScore.setForeground(Color.RED);
                        message.setText("Better Luck Next Time");
                        message.setFont(new Font("",Font.BOLD,20));
                    }

                    ScoredisplayPanel.add(FinalScore);
                    ScoredisplayPanel.add(message);
                    ScoredisplayPanel.setBorder(new EmptyBorder(70,0,0,0));


                    JPQuestionQuizPanel.add(ScoredisplayPanel);

                    JPButtonsPanel.removeAll();
                    JPButtonsPanel.setLayout(new BoxLayout(JPButtonsPanel,BoxLayout.X_AXIS));

                    JButton DoneButton = new JButton();
                    DoneButton.setFont(new Font("",Font.PLAIN,16));
                    DoneButton.setText("Done");
                    JPButtonsPanel.add(DoneButton);

                    JPQuestionQuizPanel.revalidate();
                    JPQuestionQuizPanel.repaint();
                    JPButtonsPanel.revalidate();
                    JPButtonsPanel.repaint();



                    return;
                }
                JPQuestionQuizPanel.removeAll();

                identificationAnswer.setEditable(true);
                JBsubmitButton.setEnabled(true);
                JPIncorrectPanel.setBackground(null);
                JPCorrectPanel.setBackground(null);

                if(cardcounter >= quizcards.getCards().size()-1){
                    cardcounter = -1;
                }
                cardcounter++;

                if(quizcards.getCards().size() == 1){
                    cardcounter = 0;
                }

                if(quizcards.getCards().get(cardcounter) instanceof IdentificationCard){
                    showIdentification(cardcounter);
                }
                else if(quizcards.getCards().get(cardcounter) instanceof TrueOrFalseCard){
                    showTrueorFalse(cardcounter);
                }
                else if(quizcards.getCards().get(cardcounter) instanceof MultipleChoiceCard){
                    showMultipleChoice(cardcounter);
                }

                JPQuestionQuizPanel.revalidate();
                JPQuestionQuizPanel.repaint();
                removeCardIndicator = false;

            }
        });
        JBPreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(removeCardIndicator == true){
                    quizcards.getCards().remove(cardcounter);
                }
                if(quizcards.getCards().size() == 0){
                    JPIncorrectPanel.setBackground(null);
                    JPCorrectPanel.setBackground(null);
                    JPQuestionQuizPanel.removeAll();


                    removeCardIndicator = false;

                    JPanel scoreTitlePanel = new JPanel();
                    scoreTitlePanel.setLayout(new BoxLayout(scoreTitlePanel,BoxLayout.X_AXIS));

                    JLabel scoreTitleLabel = new JLabel("Your Score: ");
                    scoreTitleLabel.setFont(new Font("",Font.PLAIN,30));

                    JLabel yourScore = new JLabel(JLCorrect.getText()+"/"+maxProgress);
                    yourScore.setFont(new Font("",Font.BOLD,40));

                    scoreTitlePanel.add(scoreTitleLabel);
                    scoreTitlePanel.add(yourScore);

                    JPQuestionQuizPanel.add(scoreTitlePanel);

                    JPanel ScoredisplayPanel = new JPanel();
                    ScoredisplayPanel.setLayout(new BoxLayout(ScoredisplayPanel,BoxLayout.Y_AXIS));


                    JProgressBar FinalScore = new JProgressBar(0,maxProgress);
                    FinalScore.setStringPainted(true);
                    FinalScore.setValue(Integer.parseInt(JLCorrect.getText()));

                    double gg = FinalScore.getPercentComplete();
                    System.out.println(gg);

                    JLabel message = new JLabel();
                    message.setBorder(new EmptyBorder(50,150,0,0));

                    if(gg >= 0.60 && gg <0.80){
                        FinalScore.setForeground(Color.BLUE);
                        message.setText("Great Job!!!");
                        message.setFont(new Font("",Font.BOLD,20));
                    }
                    else if(gg >=0.80){
                        FinalScore.setForeground(Color.GREEN);
                        message.setText("Genius!!!");
                        message.setFont(new Font("",Font.BOLD,20));
                    }
                    else{
                        FinalScore.setForeground(Color.RED);
                        message.setText("Better Luck Next Time");
                        message.setFont(new Font("",Font.BOLD,20));
                    }

                    ScoredisplayPanel.add(FinalScore);
                    ScoredisplayPanel.add(message);
                    ScoredisplayPanel.setBorder(new EmptyBorder(70,0,0,0));


                    JPQuestionQuizPanel.add(ScoredisplayPanel);

                    JPButtonsPanel.removeAll();
                    JPButtonsPanel.setLayout(new BoxLayout(JPButtonsPanel,BoxLayout.X_AXIS));

                    JButton DoneButton = new JButton();
                    DoneButton.setFont(new Font("",Font.PLAIN,16));
                    DoneButton.setText("Done");
                    JPButtonsPanel.add(DoneButton);

                    JPQuestionQuizPanel.revalidate();
                    JPQuestionQuizPanel.repaint();
                    JPButtonsPanel.revalidate();
                    JPButtonsPanel.repaint();



                    return;
                }
                JPQuestionQuizPanel.removeAll();

                identificationAnswer.setEditable(true);
                JBsubmitButton.setEnabled(true);

                JPIncorrectPanel.setBackground(null);
                JPCorrectPanel.setBackground(null);

                if(cardcounter <= 0){
                    cardcounter = quizcards.getCards().size();
                }
                cardcounter--;

                if(quizcards.getCards().size() == 1){
                    cardcounter = 0;
                }

                if(quizcards.getCards().get(cardcounter) instanceof IdentificationCard){
                    showIdentification(cardcounter);
                }
                else if(quizcards.getCards().get(cardcounter) instanceof TrueOrFalseCard){
                    showTrueorFalse(cardcounter);
                }
                else if(quizcards.getCards().get(cardcounter) instanceof MultipleChoiceCard){
                    showMultipleChoice(cardcounter);
                }

                JPQuestionQuizPanel.revalidate();
                JPQuestionQuizPanel.repaint();
                removeCardIndicator = false;
            }
        });
        JBsubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(quizcards.getCards().get(cardcounter) instanceof IdentificationCard){
                    submitIdentification();
                }
                else if(quizcards.getCards().get(cardcounter) instanceof TrueOrFalseCard){
                    submitTrueorFalse();
                }
                else if(quizcards.getCards().get(cardcounter) instanceof MultipleChoiceCard){
                    submitMultipleChoice();
                }

            }
        });
    }

    public void submitIdentification(){
        if(identificationAnswer.getText().isEmpty()){
            JPQuestionQuizPanel.remove(errormessage);
            identificationAnswer.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            JLabel errorlabel = new JLabel("Warning: Enter an answer");
            errorlabel.setFont(new Font("",Font.PLAIN,14));
            errorlabel.setForeground(new Color(139, 128, 0));

            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

            errorPanel.add(errorlabel);

            errormessage = new JScrollPane(errorPanel);
            errormessage.setMaximumSize(new Dimension(300,50));
            errormessage.setMinimumSize(new Dimension(300,50));
            errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
            errormessage.setBorder(new EmptyBorder(0,0,0,0));

            JPQuestionQuizPanel.add(errormessage);


        }
        else if(identificationAnswer.getText().equals(quizcards.getCards().get(cardcounter).getAnswer())){
            JPQuestionQuizPanel.remove(errormessage);
            identificationAnswer.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            JLabel errorlabel = new JLabel("Correct");
            errorlabel.setFont(new Font("",Font.PLAIN,14));
            errorlabel.setForeground(Color.GREEN);

            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

            errorPanel.add(errorlabel);

            errormessage = new JScrollPane(errorPanel);
            errormessage.setMaximumSize(new Dimension(300,50));
            errormessage.setMinimumSize(new Dimension(300,50));
            errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
            errormessage.setBorder(new EmptyBorder(0,0,0,0));

            JPQuestionQuizPanel.add(errormessage);

            int check = Integer.parseInt(JLCorrect.getText())+1;
            JLCorrect.setText(check+"");
            JPCorrectPanel.setBackground(new Color(138, 255, 182));

            JPQuestionQuizPanel.add(errormessage);

            identificationAnswer.setEditable(false);
            JBsubmitButton.setEnabled(false);

            removeCardIndicator= true;

            int Progressval = JPBProgressbar.getValue();
            if(Progressval < maxProgress ){
                Progressval = Progressval+1;
                JPBProgressbar.setValue(Progressval);
                JLProgressLabel.setText(Progressval+"/"+maxProgress);
            }
        }
        else{
            JPQuestionQuizPanel.remove(errormessage);
            identificationAnswer.setBorder(BorderFactory.createLineBorder(Color.RED));
            JLabel errorlabel = new JLabel("Incorrect: The Answer is "+quizcards.getCards().get(cardcounter).getAnswer());
            errorlabel.setFont(new Font("",Font.PLAIN,14));
            errorlabel.setForeground(Color.RED);

            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

            errorPanel.add(errorlabel);

            errormessage = new JScrollPane(errorPanel);
            errormessage.setMaximumSize(new Dimension(300,50));
            errormessage.setMinimumSize(new Dimension(300,50));
            errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
            errormessage.setBorder(new EmptyBorder(0,0,0,0));

            JPQuestionQuizPanel.add(errormessage);

            int wrong = Integer.parseInt(JLIncorrect.getText())+1;
            JLIncorrect.setText(wrong+"");
            JPIncorrectPanel.setBackground(new Color(255, 136, 148));

            JPQuestionQuizPanel.add(errormessage);

            identificationAnswer.setEditable(false);
            JBsubmitButton.setEnabled(false);

            removeCardIndicator= true;

            int Progressval = JPBProgressbar.getValue();
            if(Progressval < maxProgress ){
                Progressval = Progressval+1;
                JPBProgressbar.setValue(Progressval);
                JLProgressLabel.setText(Progressval+"/"+maxProgress);
            }
        }
        JPQuestionQuizPanel.revalidate();
        JPQuestionQuizPanel.repaint();
    }

    public void submitTrueorFalse(){
        if(ChoicesRadioButton.get(0).isSelected() == false && ChoicesRadioButton.get(1).isSelected() == false){
            JPQuestionQuizPanel.remove(errormessage);
            JLabel errorlabel = new JLabel("Warning: Choose an answer");
            errorlabel.setFont(new Font("",Font.PLAIN,14));
            errorlabel.setForeground(new Color(139, 128, 0));

            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

            errorPanel.add(errorlabel);

            errormessage = new JScrollPane(errorPanel);
            errormessage.setMaximumSize(new Dimension(300,50));
            errormessage.setMinimumSize(new Dimension(300,50));
            errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
            errormessage.setBorder(new EmptyBorder(0,0,0,0));

            JPQuestionQuizPanel.add(errormessage);

            JPQuestionQuizPanel.revalidate();
            JPQuestionQuizPanel.repaint();

            return;
        }

        for(JRadioButton chc: ChoicesRadioButton){
            if(chc.isSelected() == true && chc.getText().equals(quizcards.getCards().get(cardcounter).getAnswer())){
                JPQuestionQuizPanel.remove(errormessage);
                JLabel errorlabel = new JLabel("Correct");
                errorlabel.setFont(new Font("",Font.PLAIN,14));
                errorlabel.setForeground(Color.GREEN);

                JPanel errorPanel = new JPanel();
                errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

                errorPanel.add(errorlabel);

                errormessage = new JScrollPane(errorPanel);
                errormessage.setMaximumSize(new Dimension(300,50));
                errormessage.setMinimumSize(new Dimension(300,50));
                errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
                errormessage.setBorder(new EmptyBorder(0,0,0,0));

                JPQuestionQuizPanel.add(errormessage);

                int check = Integer.parseInt(JLCorrect.getText())+1;
                JLCorrect.setText(check+"");
                JPCorrectPanel.setBackground(new Color(138, 255, 182));

                JPQuestionQuizPanel.add(errormessage);


                JBsubmitButton.setEnabled(false);
                ChoicesRadioButton.get(0).setEnabled(false);
                ChoicesRadioButton.get(1).setEnabled(false);

                removeCardIndicator= true;

                JPQuestionQuizPanel.revalidate();
                JPQuestionQuizPanel.repaint();

                int Progressval = JPBProgressbar.getValue();
                if(Progressval < maxProgress ){
                    Progressval = Progressval+1;
                    JPBProgressbar.setValue(Progressval);
                    JLProgressLabel.setText(Progressval+"/"+maxProgress);
                }

                return;
            }
        }
        JPQuestionQuizPanel.remove(errormessage);
        JLabel errorlabel = new JLabel("Incorrect: The Answer is "+quizcards.getCards().get(cardcounter).getAnswer());
        errorlabel.setFont(new Font("",Font.PLAIN,14));
        errorlabel.setForeground(Color.RED);

        JPanel errorPanel = new JPanel();
        errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

        errorPanel.add(errorlabel);

        errormessage = new JScrollPane(errorPanel);
        errormessage.setMaximumSize(new Dimension(300,50));
        errormessage.setMinimumSize(new Dimension(300,50));
        errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
        errormessage.setBorder(new EmptyBorder(0,0,0,0));

        JPQuestionQuizPanel.add(errormessage);

        int wrong = Integer.parseInt(JLIncorrect.getText())+1;
        JLIncorrect.setText(wrong+"");
        JPIncorrectPanel.setBackground(new Color(255, 136, 148));

        JPQuestionQuizPanel.add(errormessage);

        JBsubmitButton.setEnabled(false);
        ChoicesRadioButton.get(0).setEnabled(false);
        ChoicesRadioButton.get(1).setEnabled(false);

        removeCardIndicator= true;

        JPQuestionQuizPanel.revalidate();
        JPQuestionQuizPanel.repaint();

        int Progressval = JPBProgressbar.getValue();
        if(Progressval < maxProgress ){
            Progressval = Progressval+1;
            JPBProgressbar.setValue(Progressval);
            JLProgressLabel.setText(Progressval+"/"+maxProgress);
        }
    }

    public void submitMultipleChoice(){
        boolean indicate_selection = false;
        for(JRadioButton multchoice: ChoicesRadioButton){
            if(multchoice.isSelected() == true){
                indicate_selection = true;
                storeUserAnswer = multchoice;
            }
        }
        if(indicate_selection == false){
            JPQuestionQuizPanel.remove(errormessage);
            JLabel errorlabel = new JLabel("Warning: Choose an answer");
            errorlabel.setFont(new Font("",Font.PLAIN,14));
            errorlabel.setForeground(new Color(139, 128, 0));

            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

            errorPanel.add(errorlabel);

            errormessage = new JScrollPane(errorPanel);
            errormessage.setMaximumSize(new Dimension(300,50));
            errormessage.setMinimumSize(new Dimension(300,50));
            errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
            errormessage.setBorder(new EmptyBorder(0,0,0,0));

            JPQuestionQuizPanel.add(errormessage);

            JPQuestionQuizPanel.revalidate();
            JPQuestionQuizPanel.repaint();

        }
        else if(((MultipleChoiceCard) quizcards.getCards().get(cardcounter)).getChoices().get(ChoicesRadioButton.indexOf(storeUserAnswer)).equals(quizcards.getCards().get(cardcounter).getAnswer())){
            JPQuestionQuizPanel.remove(errormessage);
            JLabel errorlabel = new JLabel("Correct");
            errorlabel.setFont(new Font("",Font.PLAIN,14));
            errorlabel.setForeground(Color.GREEN);

            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

            errorPanel.add(errorlabel);

            errormessage = new JScrollPane(errorPanel);
            errormessage.setMaximumSize(new Dimension(300,50));
            errormessage.setMinimumSize(new Dimension(300,50));
            errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
            errormessage.setBorder(new EmptyBorder(0,0,0,0));

            JPQuestionQuizPanel.add(errormessage);

            int check = Integer.parseInt(JLCorrect.getText())+1;
            JLCorrect.setText(check+"");
            JPCorrectPanel.setBackground(new Color(138, 255, 182));

            JPQuestionQuizPanel.add(errormessage);


            JBsubmitButton.setEnabled(false);

            for(JRadioButton jrb: ChoicesRadioButton){
                jrb.setEnabled(false);
            }

            removeCardIndicator= true;

            JPQuestionQuizPanel.revalidate();
            JPQuestionQuizPanel.repaint();

            int Progressval = JPBProgressbar.getValue();
            if(Progressval < maxProgress ){
                Progressval = Progressval+1;
                JPBProgressbar.setValue(Progressval);
                JLProgressLabel.setText(Progressval+"/"+maxProgress);
            }
        }
        else{
            JPQuestionQuizPanel.remove(errormessage);
            JLabel errorlabel = new JLabel("Incorrect: The Answer is "+quizcards.getCards().get(cardcounter).getAnswer());
            errorlabel.setFont(new Font("",Font.PLAIN,14));
            errorlabel.setForeground(Color.RED);

            JPanel errorPanel = new JPanel();
            errorPanel.setLayout(new BoxLayout(errorPanel,BoxLayout.X_AXIS));

            errorPanel.add(errorlabel);

            errormessage = new JScrollPane(errorPanel);
            errormessage.setMaximumSize(new Dimension(300,50));
            errormessage.setMinimumSize(new Dimension(300,50));
            errormessage.setPreferredSize(new Dimension(300,errormessage.getPreferredSize().height));
            errormessage.setBorder(new EmptyBorder(0,0,0,0));

            JPQuestionQuizPanel.add(errormessage);

            int wrong = Integer.parseInt(JLIncorrect.getText())+1;
            JLIncorrect.setText(wrong+"");
            JPIncorrectPanel.setBackground(new Color(255, 136, 148));

            JPQuestionQuizPanel.add(errormessage);

            JBsubmitButton.setEnabled(false);
            for(JRadioButton jrb: ChoicesRadioButton){
                jrb.setEnabled(false);
            }

            removeCardIndicator= true;

            JPQuestionQuizPanel.revalidate();
            JPQuestionQuizPanel.repaint();

            int Progressval = JPBProgressbar.getValue();
            if(Progressval < maxProgress ){
                Progressval = Progressval+1;
                JPBProgressbar.setValue(Progressval);
                JLProgressLabel.setText(Progressval+"/"+maxProgress);
            }
        }
    }

    public void showMultipleChoice(int count){
        ChoicesRadioButton = new ArrayList<>();
        JPQuestionQuizPanel.setBorder(new EmptyBorder(20,20,20,20));
        JPanel WholeQuestion = new JPanel();
        WholeQuestion.setLayout(new BoxLayout(WholeQuestion,BoxLayout.Y_AXIS));
        //Panel for Question

        JPanel QuestionComponents = new JPanel();
        QuestionComponents.setMinimumSize(new Dimension(550,100));
        QuestionComponents.setMaximumSize(new Dimension(550,100));
        QuestionComponents.setLayout(new BoxLayout(QuestionComponents,BoxLayout.X_AXIS));

        WholeQuestion.add(QuestionComponents);


        JPanel QuestionContainer = new JPanel();
        QuestionContainer.setLayout(new BoxLayout(QuestionContainer,BoxLayout.X_AXIS));
        QuestionContainer.setBorder(new EmptyBorder(0,20,0,20));

        JLabel QuestionContent = new JLabel();
        QuestionContent.setText(quizcards.getCards().get(count).getQuestion());
        QuestionContent.setFont(new Font("",Font.PLAIN,20));

        QuestionContainer.add(QuestionContent);

        JScrollPane QuestionScroll= new JScrollPane(QuestionContainer);
        QuestionScroll.setMaximumSize(new Dimension(600,70));
        QuestionScroll.setPreferredSize(new Dimension(300,QuestionScroll.getPreferredSize().height));

        QuestionComponents.add(QuestionScroll);

        questioncheck = QuestionContent;



        //Panel for User answer

        JPanel AnswerPanel = new JPanel();
        AnswerPanel.setLayout(new BoxLayout(AnswerPanel,BoxLayout.Y_AXIS));


        for(String s: ((MultipleChoiceCard) quizcards.getCards().get(count)).getChoices()){

            JRadioButton choiceradio = new JRadioButton();
            ChoicesRadioButton.add(choiceradio);


            JPanel ChoicePanel = new JPanel();
            ChoicePanel.setLayout(new BoxLayout(ChoicePanel,BoxLayout.X_AXIS));

            JScrollPane ChoiceScrollPane = new JScrollPane(ChoicePanel);
            ChoiceScrollPane.setMaximumSize(new Dimension(300,50));
            ChoiceScrollPane.setMinimumSize(new Dimension(300,50));
            ChoiceScrollPane.setPreferredSize(new Dimension(300,ChoiceScrollPane.getPreferredSize().height));

            JLabel ChoiceContentLabel = new JLabel(s);
            ChoiceContentLabel.setFont(new Font("",Font.PLAIN,14));
            ChoiceContentLabel.setBorder(new EmptyBorder(0,10,0,10));
            ChoicePanel.add(ChoiceContentLabel);


            JPanel storeChoiceScrollPane = new JPanel();
            storeChoiceScrollPane.setLayout(new BoxLayout(storeChoiceScrollPane,BoxLayout.X_AXIS));
            storeChoiceScrollPane.setBorder(new EmptyBorder(0,0,10,0));

            storeChoiceScrollPane.add(choiceradio);
            storeChoiceScrollPane.add(ChoiceScrollPane);

            AnswerPanel.add(storeChoiceScrollPane);


        }

        ButtonGroup btnmultiplechoice = new ButtonGroup();
        for(JRadioButton jr : ChoicesRadioButton){
            btnmultiplechoice.add(jr);
        }

        WholeQuestion.add(AnswerPanel);
        JPQuestionQuizPanel.add(WholeQuestion);


        JSPQuestionQuizScrollPanel.revalidate();
    }

    public void showIdentification(int count){
        JPQuestionQuizPanel.setBorder(new EmptyBorder(70,20,20,20));
        //Panel for Question and Answer Panel

        JPanel WholeQuestion = new JPanel();
        WholeQuestion.setLayout(new BoxLayout(WholeQuestion,BoxLayout.Y_AXIS));
        //Panel for Question

        JPanel QuestionComponents = new JPanel();
        QuestionComponents.setMinimumSize(new Dimension(550,100));
        QuestionComponents.setMaximumSize(new Dimension(550,100));
        QuestionComponents.setLayout(new BoxLayout(QuestionComponents,BoxLayout.X_AXIS));

        WholeQuestion.add(QuestionComponents);




        JPanel QuestionContainer = new JPanel();
        QuestionContainer.setLayout(new BoxLayout(QuestionContainer,BoxLayout.X_AXIS));
        QuestionContainer.setBorder(new EmptyBorder(0,20,0,20));

        JLabel QuestionContent = new JLabel();
        QuestionContent.setText(quizcards.getCards().get(count).getQuestion());
        QuestionContent.setFont(new Font("",Font.PLAIN,20));

        QuestionContainer.add(QuestionContent);

        JScrollPane QuestionScroll= new JScrollPane(QuestionContainer);
        QuestionScroll.setMaximumSize(new Dimension(600,70));
        QuestionScroll.setPreferredSize(new Dimension(300,QuestionScroll.getPreferredSize().height));

        QuestionComponents.add(QuestionScroll);

        questioncheck = QuestionContent;


        //Panel for User answer
        JPanel AnswerComponents = new JPanel();
        AnswerComponents.setLayout(new BoxLayout(AnswerComponents,BoxLayout.X_AXIS));
        AnswerComponents.setLayout(new FlowLayout(FlowLayout.CENTER));
        AnswerComponents.setMinimumSize(new Dimension(550,100));
        AnswerComponents.setMaximumSize(new Dimension(550,100));
        AnswerComponents.setBorder(new EmptyBorder(40,0,0,0));

        WholeQuestion.add(AnswerComponents);

        //Add Question Label and Content
        JLabel AnswerLabel = new JLabel("   Answer: ");
        AnswerComponents.add(AnswerLabel);
        AnswerLabel.setMinimumSize(new Dimension(100,50));
        AnswerLabel.setMaximumSize(new Dimension(100,50));
        AnswerLabel.setFont(new Font("",Font.PLAIN,20));

        JTextField AnswerContent = new JTextField();
        AnswerContent.setPreferredSize(new Dimension(300, 40));
        AnswerContent.setFont(new Font("",Font.PLAIN,16));
        AnswerComponents.add(AnswerContent);

        identificationAnswer = AnswerContent;

        JPQuestionQuizPanel.add(WholeQuestion);

        JSPQuestionQuizScrollPanel.revalidate();

    }

    public void showTrueorFalse(int count){
        ChoicesRadioButton = new ArrayList<>();
        JPQuestionQuizPanel.setBorder(new EmptyBorder(70,20,20,20));
        //Panel for Question and Answer Panel

        JPanel WholeQuestion = new JPanel();
        WholeQuestion.setLayout(new BoxLayout(WholeQuestion,BoxLayout.Y_AXIS));
        //Panel for Question

        JPanel QuestionComponents = new JPanel();
        QuestionComponents.setMinimumSize(new Dimension(550,100));
        QuestionComponents.setMaximumSize(new Dimension(550,100));
        QuestionComponents.setLayout(new BoxLayout(QuestionComponents,BoxLayout.X_AXIS));

        WholeQuestion.add(QuestionComponents);


        JPanel QuestionContainer = new JPanel();
        QuestionContainer.setLayout(new BoxLayout(QuestionContainer,BoxLayout.X_AXIS));
        QuestionContainer.setBorder(new EmptyBorder(0,20,0,20));

        JLabel QuestionContent = new JLabel();
        QuestionContent.setText(quizcards.getCards().get(count).getQuestion());
        QuestionContent.setFont(new Font("",Font.PLAIN,20));

        QuestionContainer.add(QuestionContent);

        JScrollPane QuestionScroll= new JScrollPane(QuestionContainer);
        QuestionScroll.setMaximumSize(new Dimension(600,70));
        QuestionScroll.setPreferredSize(new Dimension(300,QuestionScroll.getPreferredSize().height));

        QuestionComponents.add(QuestionScroll);

        questioncheck = QuestionContent;



        //Panel for User answer
        JPanel AnswerPanel = new JPanel();
        AnswerPanel.setLayout(new BoxLayout(AnswerPanel,BoxLayout.X_AXIS));

        JRadioButton TrueChoice = new JRadioButton();
        TrueChoice.setText(((TrueOrFalseCard) quizcards.getCards().get(count)).getChoices().get(0));
        TrueChoice.setFont(new Font("",Font.PLAIN,16));

        JRadioButton FalseChoice = new JRadioButton();
        FalseChoice.setText(((TrueOrFalseCard) quizcards.getCards().get(count)).getChoices().get(1));
        FalseChoice.setFont(new Font("",Font.PLAIN,16));

        AnswerPanel.add(TrueChoice);
        AnswerPanel.add(FalseChoice);

        AnswerPanel.setBorder(new EmptyBorder(40,0,0,0));

        ButtonGroup ChoiceGroup = new ButtonGroup();
        ChoiceGroup.add(TrueChoice);
        ChoiceGroup.add(FalseChoice);

        ChoicesRadioButton.add(TrueChoice);
        ChoicesRadioButton.add(FalseChoice);

        WholeQuestion.add(AnswerPanel);
        JPQuestionQuizPanel.add(WholeQuestion);


        JSPQuestionQuizScrollPanel.revalidate();
    }



    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        TakeQuiz appme = new TakeQuiz();
        JScrollPane scrollPane = new JScrollPane(appme.JTakequiz);
        appme.setContentPane(scrollPane);
        appme.setSize(1200, 750);
        appme.setResizable(false);
        appme.setDefaultCloseOperation(EXIT_ON_CLOSE);
        appme.setTitle("Creting Quiz");
        appme.setVisible(true);
    }

}

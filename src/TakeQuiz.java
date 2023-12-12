import Card.*;
import QuizPackage.Quiz;
import TakeQuizComponents.*;

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
    public JScrollPane JSPTitleScrollPanel;
    public JPanel JPTItleContentPanel;
    public JLabel JLTitleLabelContent;
    public JButton JBBack;

    int cardcounter = 0;

    ArrayList<JRadioButton> ChoicesRadioButton = new ArrayList<>();

    JLabel questioncheck;

    JTextField identificationAnswer = new JTextField();

    JScrollPane errormessage = new JScrollPane();

    JRadioButton storeUserAnswer;

    boolean removeCardIndicator = false;

    public int maxProgress;


    private static TakeQuiz TQinstance = null;

    public Quiz quizcards = new Quiz();

    public static TakeQuiz getInstance() {
        if (TQinstance == null) {
            TQinstance = new TakeQuiz();
        }
        return TQinstance;
    }

    public static TakeQuiz refreshInstance() {

        TQinstance = new TakeQuiz();

        return TQinstance;

    }



    private TakeQuiz(){
        //Holder for Quize
        quizcards = new Quiz();

        //Puting scroll on the Title content Holder

        JSPTitleScrollPanel.setBorder(new EmptyBorder(0, 70, 0, 0));
        JPQuestionQuizPanel.setLayout(new BoxLayout(JPQuestionQuizPanel, BoxLayout.Y_AXIS));
        JSPTitleScrollPanel.setMinimumSize(new Dimension(900, 40));
        JSPTitleScrollPanel.setPreferredSize(new Dimension(900, JSPTitleScrollPanel.getPreferredSize().height));
        JSPTitleScrollPanel.setMaximumSize(new Dimension(900, 40));

        //Getting The Cards
        for(Card card:  FolderForQuiz.getInstance().folderfirst.getQuiz().get(FolderForQuiz.getInstance().quizIndex).getCards()){
            quizcards.addCard(card);
        }

        //Setting the quizName
        quizcards.setQuizName(FolderForQuiz.getInstance().folderfirst.getQuiz().get(FolderForQuiz.getInstance().quizIndex).getQuizName());
        JLTitleLabelContent.setText(quizcards.getQuizName());

        //Placing quiestions on the Container
        if (quizcards.getCards().get(cardcounter) instanceof IdentificationCard) {
            showIdentification(cardcounter);
        } else if (quizcards.getCards().get(cardcounter) instanceof TrueOrFalseCard) {
            showTrueorFalse(cardcounter);
        } else if (quizcards.getCards().get(cardcounter) instanceof MultipleChoiceCard) {
            showMultipleChoice(cardcounter);
        }

        //Preparing Progress Bar
        maxProgress = quizcards.getCards().size();
        JPBProgressbar.setMinimum(0);
        JPBProgressbar.setMaximum(maxProgress);
        JPBProgressbar.setStringPainted(true);
        JLProgressLabel.setText("0/" + maxProgress);

        JBNextButton.addActionListener(new ActionListener() {
            ButtonsClass showscore = new ButtonsClass();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(removeCardIndicator == true){
                    //remove card after answering
                    quizcards.getCards().remove(cardcounter);
                    cardcounter--;
                }
                if(quizcards.getCards().size() == 0){
                    //show total score
                    JPIncorrectPanel.setBackground(null);
                    JPCorrectPanel.setBackground(null);
                    JPQuestionQuizPanel.removeAll();
                    removeCardIndicator = false;

                    showscore.JLCorrect = JLCorrect;
                    showscore.JPButtonsPanel = JPButtonsPanel;
                    showscore.maxProgress = maxProgress;
                    showscore.JPQuestionQuizPanel = JPQuestionQuizPanel;

                    JButton DoneButton = showscore.showScore();

                    JLCorrect = showscore.JLCorrect;
                    JPButtonsPanel = showscore.JPButtonsPanel;
                    maxProgress = showscore.maxProgress;
                    JPQuestionQuizPanel = showscore.JPQuestionQuizPanel;


                    DoneButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            backtofolder();
                        }
                    });

                    return;
                }

                //show next card
                startDisplay();
                if(cardcounter >= quizcards.getCards().size()-1){
                    cardcounter = -1;
                }
                cardcounter++;
                if(quizcards.getCards().size() == 1){
                    cardcounter = 0;
                }
                endDisplay();


            }
        });
        JBPreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonsClass showscore = new ButtonsClass();
                if(removeCardIndicator == true){
                    //remove card after answering
                    quizcards.getCards().remove(cardcounter);
                }
                if(quizcards.getCards().size() == 0){
                    //show total score
                    JPIncorrectPanel.setBackground(null);
                    JPCorrectPanel.setBackground(null);
                    JPQuestionQuizPanel.removeAll();
                    removeCardIndicator = false;

                    showscore.JLCorrect = JLCorrect;
                    showscore.JPButtonsPanel = JPButtonsPanel;
                    showscore.maxProgress = maxProgress;
                    showscore.JPQuestionQuizPanel = JPQuestionQuizPanel;

                    JButton DoneButton = showscore.showScore();

                    JLCorrect = showscore.JLCorrect;
                    JPButtonsPanel = showscore.JPButtonsPanel;
                    maxProgress = showscore.maxProgress;
                    JPQuestionQuizPanel = showscore.JPQuestionQuizPanel;

                    DoneButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            backtofolder();
                        }
                    });

                    return;
                }
                //show prevoius card
                startDisplay();
                if(cardcounter <= 0){
                    cardcounter = quizcards.getCards().size();
                }
                cardcounter--;
                if(quizcards.getCards().size() == 1){
                    cardcounter = 0;
                }
                endDisplay();
            }
        });
        JBsubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //select what type of card to submit
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
        JBBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backtofolder();
            }
        });
    }

    public void startDisplay(){
        JPQuestionQuizPanel.removeAll();
        identificationAnswer.setEditable(true);
        JBsubmitButton.setEnabled(true);
        JPIncorrectPanel.setBackground(null);
        JPCorrectPanel.setBackground(null);
    }
    public void endDisplay(){
        //select the type of card to show
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


    public void checkComponents(){
        //increase score
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

    public void wrongComponents(){
        //decrease score
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
    public void submitIdentification(){
        TQIdentificationCard IdentificationSubmitLabel = new TQIdentificationCard();
        if(identificationAnswer.getText().isEmpty()){
            //show warning message
            IdentificationSubmitLabel.JPQuestionQuizPanel = JPQuestionQuizPanel;
            IdentificationSubmitLabel.identificationAnswer = identificationAnswer;
            IdentificationSubmitLabel.errormessage = errormessage;

            IdentificationSubmitLabel.submitMessageLabels();

            IdentificationSubmitLabel.LabelError.setText("Warning: Enter an answer");

            JPQuestionQuizPanel=IdentificationSubmitLabel.JPQuestionQuizPanel;
            identificationAnswer=IdentificationSubmitLabel.identificationAnswer;
            errormessage = IdentificationSubmitLabel.errormessage;

        }
        else if(identificationAnswer.getText().equals(quizcards.getCards().get(cardcounter).getAnswer())){
            //show correct message
            IdentificationSubmitLabel.JPQuestionQuizPanel = JPQuestionQuizPanel;
            IdentificationSubmitLabel.identificationAnswer = identificationAnswer;
            IdentificationSubmitLabel.errormessage = errormessage;

            IdentificationSubmitLabel.submitMessageLabels();

            IdentificationSubmitLabel.LabelError.setText("Correct");
            IdentificationSubmitLabel.LabelError.setForeground(Color.GREEN);
            ((TQIdentificationCard)IdentificationSubmitLabel).changecolor.setBorder(BorderFactory.createLineBorder(Color.GREEN));

            JPQuestionQuizPanel=IdentificationSubmitLabel.JPQuestionQuizPanel;
            identificationAnswer=IdentificationSubmitLabel.identificationAnswer;
            errormessage = IdentificationSubmitLabel.errormessage;

            checkComponents();

        }
        else{
            //show incorrect message
            IdentificationSubmitLabel.JPQuestionQuizPanel = JPQuestionQuizPanel;
            IdentificationSubmitLabel.identificationAnswer = identificationAnswer;
            IdentificationSubmitLabel.errormessage = errormessage;

            IdentificationSubmitLabel.submitMessageLabels();

            IdentificationSubmitLabel.LabelError.setText("Incorrect: The Answer is "+quizcards.getCards().get(cardcounter).getAnswer());
            IdentificationSubmitLabel.LabelError.setForeground(Color.RED);
            ((TQIdentificationCard)IdentificationSubmitLabel).changecolor.setBorder(BorderFactory.createLineBorder(Color.RED));

            JPQuestionQuizPanel=IdentificationSubmitLabel.JPQuestionQuizPanel;
            identificationAnswer=IdentificationSubmitLabel.identificationAnswer;
            errormessage = IdentificationSubmitLabel.errormessage;

            wrongComponents();

        }
        JPQuestionQuizPanel.revalidate();
        JPQuestionQuizPanel.repaint();
    }

    public void submitTrueorFalse(){
        TQTrueOrFalseCard TrueorFalseSubmitLabel = new TQTrueOrFalseCard();
        if(ChoicesRadioButton.get(0).isSelected() == false && ChoicesRadioButton.get(1).isSelected() == false){

            //show warning message
            TrueorFalseSubmitLabel.JPQuestionQuizPanel = JPQuestionQuizPanel;
            TrueorFalseSubmitLabel.identificationAnswer = identificationAnswer;
            TrueorFalseSubmitLabel.errormessage = errormessage;

            TrueorFalseSubmitLabel.submitMessageLabels();

            TrueorFalseSubmitLabel.LabelError.setText("Warning: Choose an answer");
            TrueorFalseSubmitLabel.LabelError.setForeground(new Color(139, 128, 0));

            JPQuestionQuizPanel=TrueorFalseSubmitLabel.JPQuestionQuizPanel;
            identificationAnswer=TrueorFalseSubmitLabel.identificationAnswer;
            errormessage = TrueorFalseSubmitLabel.errormessage;

            return;
        }

        for(JRadioButton chc: ChoicesRadioButton){
            if(chc.isSelected() == true && chc.getText().equals(quizcards.getCards().get(cardcounter).getAnswer())){
                //show correct message
                TrueorFalseSubmitLabel.JPQuestionQuizPanel = JPQuestionQuizPanel;
                TrueorFalseSubmitLabel.identificationAnswer = identificationAnswer;
                TrueorFalseSubmitLabel.errormessage = errormessage;

                TrueorFalseSubmitLabel.submitMessageLabels();

                TrueorFalseSubmitLabel.LabelError.setText("Correct");
                TrueorFalseSubmitLabel.LabelError.setForeground(Color.GREEN);

                JPQuestionQuizPanel=TrueorFalseSubmitLabel.JPQuestionQuizPanel;
                errormessage = TrueorFalseSubmitLabel.errormessage;

                checkComponents();

                return;
            }
        }

        //show incorrect message
        TrueorFalseSubmitLabel.JPQuestionQuizPanel = JPQuestionQuizPanel;
        TrueorFalseSubmitLabel.identificationAnswer = identificationAnswer;
        TrueorFalseSubmitLabel.errormessage = errormessage;

        TrueorFalseSubmitLabel.submitMessageLabels();

        TrueorFalseSubmitLabel.LabelError.setText("Incorrect: The Answer is "+quizcards.getCards().get(cardcounter).getAnswer());
        TrueorFalseSubmitLabel.LabelError.setForeground(Color.RED);

        JPQuestionQuizPanel=TrueorFalseSubmitLabel.JPQuestionQuizPanel;
        errormessage = TrueorFalseSubmitLabel.errormessage;

        wrongComponents();
    }

    public void submitMultipleChoice(){
        TQMultipleChoiceCard MultipleChoiceFlashCard = new TQMultipleChoiceCard();
        boolean indicate_selection = false;
        for(JRadioButton multchoice: ChoicesRadioButton){
            if(multchoice.isSelected() == true){
                indicate_selection = true;
                storeUserAnswer = multchoice;
            }
        }
        if(indicate_selection == false){
            //show warning message
            MultipleChoiceFlashCard.JPQuestionQuizPanel = JPQuestionQuizPanel;
            MultipleChoiceFlashCard.identificationAnswer = identificationAnswer;
            MultipleChoiceFlashCard.errormessage = errormessage;

            MultipleChoiceFlashCard.submitMessageLabels();

            MultipleChoiceFlashCard.LabelError.setText("Warning: Choose an answer");
            MultipleChoiceFlashCard.LabelError.setForeground(new Color(139, 128, 0));

            JPQuestionQuizPanel=MultipleChoiceFlashCard.JPQuestionQuizPanel;
            identificationAnswer=MultipleChoiceFlashCard.identificationAnswer;
            errormessage = MultipleChoiceFlashCard.errormessage;

        }
        else if(((MultipleChoiceCard) quizcards.getCards().get(cardcounter)).getChoices().get(ChoicesRadioButton.indexOf(storeUserAnswer)).equals(quizcards.getCards().get(cardcounter).getAnswer())){
            //show correct message
            MultipleChoiceFlashCard.JPQuestionQuizPanel = JPQuestionQuizPanel;
            MultipleChoiceFlashCard.identificationAnswer = identificationAnswer;
            MultipleChoiceFlashCard.errormessage = errormessage;

            MultipleChoiceFlashCard.submitMessageLabels();

            MultipleChoiceFlashCard.LabelError.setText("Correct");
            MultipleChoiceFlashCard.LabelError.setForeground(Color.GREEN);

            JPQuestionQuizPanel=MultipleChoiceFlashCard.JPQuestionQuizPanel;
            errormessage = MultipleChoiceFlashCard.errormessage;

            checkComponents();
        }
        else{
            //show incorrect message
            MultipleChoiceFlashCard.JPQuestionQuizPanel = JPQuestionQuizPanel;
            MultipleChoiceFlashCard.identificationAnswer = identificationAnswer;
            MultipleChoiceFlashCard.errormessage = errormessage;

            MultipleChoiceFlashCard.submitMessageLabels();

            MultipleChoiceFlashCard.LabelError.setText("Incorrect: The Answer is "+quizcards.getCards().get(cardcounter).getAnswer());
            MultipleChoiceFlashCard.LabelError.setForeground(Color.RED);

            JPQuestionQuizPanel=MultipleChoiceFlashCard.JPQuestionQuizPanel;
            errormessage = MultipleChoiceFlashCard.errormessage;


            wrongComponents();
        }
    }

    public void backtofolder(){
        //goes back to folder
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

        TakeQuiz.getInstance().setVisible(false);
        FolderForQuiz folderquiz = FolderForQuiz.getInstance();
        folderquiz.setContentPane(folderquiz.JPFolderContainerPanel);
        folderquiz.setLocation(TakeQuiz.getInstance().getLocation());
        folderquiz.setSize(1200, 750);
        folderquiz.setResizable(false);
        folderquiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
        folderquiz.setTitle("Folder");
        folderquiz.setVisible(true);

        folderquiz.refreshQuizContainer();
    }

    public void showMultipleChoice(int count){
        ChoicesRadioButton = new ArrayList<>();
        JPQuestionQuizPanel.setBorder(new EmptyBorder(20,20,20,20));
        JPanel WholeQuestion = new JPanel();
        WholeQuestion.setLayout(new BoxLayout(WholeQuestion,BoxLayout.Y_AXIS));


        //Setting Up Question Components
        TakingQuizCard MultipleChoiceFlashCard = new TQMultipleChoiceCard();

        JPanel hold = MultipleChoiceFlashCard.getQuestionComponent(quizcards,count);
        WholeQuestion.add(hold);

        questioncheck = MultipleChoiceFlashCard.questionccontent;


        //Setting Up Answer Components
        ((TQMultipleChoiceCard)MultipleChoiceFlashCard).count = count;
        ((TQMultipleChoiceCard)MultipleChoiceFlashCard).quizcards = quizcards;

        JPanel holdAnswer = MultipleChoiceFlashCard.getAnswerComponent();
        ChoicesRadioButton = ((TQMultipleChoiceCard)MultipleChoiceFlashCard).ChoicesRadioButton;

        ButtonGroup btnmultiplechoice = new ButtonGroup();

        for(JRadioButton jr : ChoicesRadioButton){
            btnmultiplechoice.add(jr);
        }

        WholeQuestion.add(holdAnswer);
        JPQuestionQuizPanel.add(WholeQuestion);


        JSPQuestionQuizScrollPanel.revalidate();
    }

    public void showIdentification(int count){
        JPQuestionQuizPanel.setBorder(new EmptyBorder(70,20,20,20));

        //Setting Up Question Components

        JPanel WholeQuestion = new JPanel();
        WholeQuestion.setLayout(new BoxLayout(WholeQuestion,BoxLayout.Y_AXIS));

        TakingQuizCard identificationFlashCard = new TQIdentificationCard();

        JPanel hold = identificationFlashCard.getQuestionComponent(quizcards,count);
        WholeQuestion.add(hold);

        questioncheck = identificationFlashCard.questionccontent;


        //Setting Up Answer Components
        JPanel holdAnswerComponent = identificationFlashCard.getAnswerComponent();

        WholeQuestion.add(holdAnswerComponent);
        identificationAnswer = ((TQIdentificationCard)identificationFlashCard).answerContent;

        JPQuestionQuizPanel.add(WholeQuestion);

        JSPQuestionQuizScrollPanel.revalidate();

    }

    public void showTrueorFalse(int count){
        ChoicesRadioButton = new ArrayList<>();
        JPQuestionQuizPanel.setBorder(new EmptyBorder(70,20,20,20));

        JPanel WholeQuestion = new JPanel();
        WholeQuestion.setLayout(new BoxLayout(WholeQuestion,BoxLayout.Y_AXIS));

        //Setting Up Question Components
        TakingQuizCard trueOrFalseFlashCard = new TQTrueOrFalseCard();

        JPanel hold = trueOrFalseFlashCard.getQuestionComponent(quizcards,count);
        WholeQuestion.add(hold);

        questioncheck = trueOrFalseFlashCard.questionccontent;

        //Setting Up Answer Components

        ((TQTrueOrFalseCard)trueOrFalseFlashCard).count = count;
        ((TQTrueOrFalseCard)trueOrFalseFlashCard).quizcards = quizcards;

        WholeQuestion.add(trueOrFalseFlashCard.getAnswerComponent());

        ChoicesRadioButton.add(((TQTrueOrFalseCard)trueOrFalseFlashCard).Truebutton);
        ChoicesRadioButton.add(((TQTrueOrFalseCard)trueOrFalseFlashCard).Falsebutton);

        JPQuestionQuizPanel.add(WholeQuestion);


        JSPQuestionQuizScrollPanel.revalidate();
    }



    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        TakeQuiz appme = TakeQuiz.getInstance();
        JScrollPane scrollPane = new JScrollPane(appme.JTakequiz);
        appme.setContentPane(scrollPane);
        appme.setSize(1200, 750);
        appme.setResizable(false);
        appme.setDefaultCloseOperation(EXIT_ON_CLOSE);
        appme.setTitle("Creating Quiz");
        appme.setVisible(true);
    }

}

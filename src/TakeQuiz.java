import Card.*;
import QuizPackage.Quiz;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
    public JLabel JLTitleCContentLAbel;
    public JTextArea JTADescriptionContent;
    public JPanel JPTittleContentPanel;


    Quiz quizcards = new Quiz();//list of cards to take

    public TakeQuiz(){
        JPTittleContentPanel.setBorder(new EmptyBorder(0,70,0,0));
        JPQuestionQuizPanel.setLayout(new BoxLayout(JPQuestionQuizPanel,BoxLayout.Y_AXIS));
        JPQuestionQuizPanel.setBorder(new EmptyBorder(70,20,20,20));




        //placing cards in the quizcards list(This is just for testing, it might/will change depending on how you get your data from a file)
        Card cc1= CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,"What is Gitksadjfjkjjjjjjjjjosk","Git");

        Card cc2= CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE,"Pick the word aa","aa");
        ((MultipleChoiceCard)cc2).addChoices("aa");
        ((MultipleChoiceCard)cc2).addChoices("bb");
        ((MultipleChoiceCard)cc2).addChoices("cc");

        Card cc3 = CardFactory.MakeCard(CardFactory.type.TRUE_OR_FALSE,"Is your father your parent?","True");
        Card cc4 = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,"Hello. Say Hi","Hi");

        Card cc5= CardFactory.MakeCard(CardFactory.type.MULTIPLE_CHOICE,"Pick the word bbb","bbb");
        ((MultipleChoiceCard)cc5).addChoices("aa");
        ((MultipleChoiceCard)cc5).addChoices("bbb");
        ((MultipleChoiceCard)cc5).addChoices("c");

        quizcards.addCard(cc1);
        quizcards.addCard(cc2);
        quizcards.addCard(cc3);
        quizcards.addCard(cc4);
        quizcards.addCard(cc5);


        //Placing quiestions on the Container(do not change ths)
        if(quizcards.getCards().get(0) instanceof IdentificationCard){
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
            QuestionContent.setText(quizcards.getCards().get(0).getQuestion());
            QuestionContent.setFont(new Font("",Font.PLAIN,20));

            QuestionContainer.add(QuestionContent);

            JScrollPane QuestionScroll= new JScrollPane(QuestionContainer);
            QuestionScroll.setMaximumSize(new Dimension(600,70));
            QuestionScroll.setPreferredSize(new Dimension(300,QuestionScroll.getPreferredSize().height));

            QuestionComponents.add(QuestionScroll);


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

            JPQuestionQuizPanel.add(WholeQuestion);

            JSPQuestionQuizScrollPanel.revalidate();


        }
        else if(quizcards.getCards().get(0) instanceof TrueOrFalseCard){
            System.out.println("Tf");
        }
        else if(quizcards.getCards().get(0) instanceof MultipleChoiceCard){
            System.out.println("M");
        }
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

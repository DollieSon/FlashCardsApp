import Card.IdentificationCard;
import Card.*;
import QuizPackage.*;
import java.io.BufferedReader;
import java.io.FileReader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    /*
    QuizName
    QuizAuthor
    QuestionType,Question,Answer,Choice1,Choice2
    QuestionType,Question,Answer,Choice1,Choice2
*/
    public static void main(String[] args) {
        ReadQuiz rq = new ReadQuiz();
        WriteQuiz wq = new WriteQuiz();
        try{
            Quiz qz = rq.ReadQuizFile("Quizes/Quiz1.txt");
            System.out.println(qz.getQuizName());
            for(Card card: qz.getCards()){
                System.out.println(card.getQuestion());
            }
            wq.CreateQuizFile(qz);
        }catch (Exception e){
            System.out.println((e.getMessage()));
        }
        System.out.println("Done");
    }
}
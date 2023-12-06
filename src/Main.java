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
        Card card = CardFactory.MakeCard(CardFactory.type.IDENTIFICATION,"Question","Answer");
        System.out.println("Done");
    }
}
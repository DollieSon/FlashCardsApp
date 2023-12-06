package QuizPackage;

import Card.CardFactory;
import Card.MultipleChoiceCard;
import Card.Card;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadQuiz {

    private CardFactory.type GetType(String type){
        switch (type) {
            case "TrueOrFalse" -> {
                return CardFactory.type.TRUE_OR_FALSE;
            }
            case "Identification" -> {
                return CardFactory.type.IDENTIFICATION;
            }
            case "MultipleChoice" -> {
                return CardFactory.type.MULTIPLE_CHOICE;
            }
        }
        return CardFactory.type.MULTIPLE_CHOICE;
    }



    /*
        QuizName
        QuizAuthor
        QuestionType,Question,Answer,Choice1,Choice2
        QuestinoType,Question,Answer,Choice1,Choice2
    */


    public Quiz ReadQuizFile(String Location) throws FileNotFoundException {
        BufferedReader br;
        Quiz ResQuiz;
        try{
            br = new BufferedReader(new FileReader(Location));
            ResQuiz = new Quiz();
            String Line;
            //Set The Quizname and Author
            ResQuiz.setQuizName(br.readLine()).setAuthor(br.readLine());
            while((Line = br.readLine())!= null){
                String[] Parts = Line.split(",");
                //Type,Question,Answer;
                Card card = CardFactory.MakeCard(GetType(Parts[0]),Parts[1],Parts[2]);
                System.out.println(Parts.length);
                int ChoiceNum = 3;
                if(ChoiceNum < Parts.length){
                    ((MultipleChoiceCard) card).addChoices(Parts[ChoiceNum]);
                    ChoiceNum++;
                }
                ResQuiz.addCard(card);
            }
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("File Error In Reading Quiz File");
        }catch (IOException e){
            throw new FileNotFoundException("Read Lines Error");
        }
        return ResQuiz;
    }
}

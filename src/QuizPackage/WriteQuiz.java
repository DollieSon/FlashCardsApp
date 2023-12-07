package QuizPackage;

import Card.*;
import java.io.*;
import java.util.*;
// TODO make an interface
public class WriteQuiz {

    private String GetType(Card type){
        if(type instanceof IdentificationCard){
            return "Identification";
        } else if (type instanceof TrueOrFalseCard) {
            return "TrueOrFalse";
        }else if (type instanceof MultipleChoiceCard) {
            return "MultipleChoice";
        }
        return "";
    }

    public void CreateQuizFile(Quiz quiz) throws IOException {
        int LatestQuiz = -1;
        try{
            BufferedReader br = new BufferedReader( new FileReader(FileLocations.GetQuizList()));
            LatestQuiz = Integer.parseInt(br.readLine());
            LatestQuiz++;
            String QuizFileName = "Quiz";
            QuizFileName+=Integer.toString(LatestQuiz);
            ChangeQuizFile(quiz,"Quizes/"+QuizFileName+".txt");
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(FileLocations.GetQuizList()));
            bw.write(Integer.toString(LatestQuiz));
            bw.flush();
            bw.close();
        }catch (IOException e){
            throw new IOException("Error In Created QuizFile");
        }
    }

    public int ChangeQuizFile(Quiz quiz, String Location) throws IOException{
        int LatestQuiz = -1;
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(Location));
            bw.append(quiz.getQuizName() + "\n");
            bw.append(quiz.getAuthor()+ "\n");
            for(Card card: quiz.getCards()){
                String Line = GetType(card) + ",";
                Line += card.getQuestion() + ",";
                Line += card.getAnswer() + ",";
                if(card instanceof MultipleChoiceCard){
                    List<String> choices = ((MultipleChoiceCard) card).getChoices();
                    for(String choice: choices){
                        Line += choice + ",";
                    }
                }
                bw.append(Line+ "\n");
            }
            bw.flush();
            bw.close();
        }catch (FileNotFoundException e){
            throw new FileNotFoundException("Error In Create Quiz File");
        }catch (IOException e){
            throw new IOException("Error In Appending");
        }
        return LatestQuiz;
    }

}

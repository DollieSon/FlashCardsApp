import Card.CardFactory;
import Card.IdentificationCard;
import QuizPackage.Quiz;
import QuizPackage.SerializationUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Quiz q1 = new Quiz();
        q1.setAuthor("Danjo");
        q1.addCard(CardFactory.MakeCard(CardFactory.type.TRUE_OR_FALSE,"Gwapo ko?","True"));
        try {
            SerializationUtil.serialize(q1,"q1.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Quiz temp = null;
        try {
            temp = (Quiz) SerializationUtil.deserialize("q1.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Q1: "+ q1.getAuthor());
        System.out.println("temp: "+temp.getAuthor());
        System.out.println(temp.getCard(0).getQuestion());
        System.out.println(temp.getCard(0).getAnswer());


    }
}
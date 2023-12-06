package QuizPackage;

public class FileLocations {
    private static String QuizFiles = "Quizes/";
    private static String QuizList = "QuizList.txt";

    public static String GetQuizFile(){
        return QuizFiles;
    }
    public static String GetQuizList(){
        return QuizFiles + QuizList;
    }

}

import java.io.BufferedReader;
import java.io.FileReader;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*FileManager File1 = FileHandlerFactory.getFileManager("src\\Quiz1.txt");
        FileManager File2 = FileHandlerFactory.getFileManager("src\\Sample.txt");
        FileManager File3 = FileHandlerFactory.getFileManager("src\\Quiz1.txt");
        System.out.println( File1);
        System.out.println( File2);
        System.out.println( File2);*/

        BufferedReader br = null;
        int indx = 0;
        int ident = 0;

        String[] label = {"QuestionId: ","Question: ","Type: ","Answer: ","Choices: "};
        try {
            br = new BufferedReader(new FileReader("src\\Quiz1.txt"));
            String conv = "hiq";

            while(true){
                conv= br.readLine();
                if(conv == null) {
                    br.close();
                    break;
                }
                String[] qq = conv.split(",");
                if(indx == 0){
                    System.out.println("Quiz Id: "+qq[0]+" Quiz Name: " +qq[1]);
                }
                else{
                    if(qq[2] == "Identification"){
                        ident = 1;
                    }

                    int ll = 0;
                    for(int loop = 0; loop<qq.length; loop++){
                        System.out.print(label[ll]);
                        if(label[ll] == "Choices: "){
                            System.out.print(qq[loop]+" ");
                        }
                        else {
                            System.out.println(qq[loop]);
                            ll++;
                        }

                    }

                }
                indx++;
                System.out.println("\n");
            }


        }
        catch(Exception e){
            System.out.println("Error occured");
            e.printStackTrace();
        }

        System.out.println("HEllo world");
    }
}
package First;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SingleQues {
    Set<?>[] ques = new Set<?>[5];
    public void QuesPass(Stage primaryStage, Set<?>[] numbers, int marks) throws IOException {
        for(int i=0;i<5;i++)
        {
            ques[i]= (HashSet<?>) numbers[i];
        }
        easy goEasy = new easy();
        GiveQues(primaryStage,(Set<Integer>) ques[0],10,0,1,0);
    }

    public void GiveQues(Stage primaryStage, Set<Integer> numbers,int qTotal, int marks,int min, int sec) throws IOException {
        if(marks<2)
        {
            System.out.println("beshie easy");
            System.out.println(ques[0]);
            easy goEasy = new easy();
            goEasy.EASY(primaryStage,2, (Set<Integer>) ques[0],qTotal,marks,min,sec);
        }
        else if(marks<6)
        {
            System.out.println("im boss");
            System.out.println(ques[1]);
            easy goEasy = new easy();
            goEasy.EASY(primaryStage,2, (Set<Integer>) ques[1],qTotal,marks,min,sec);
        }
        else if(marks<10)
        {
            System.out.println("Arre shob pari");
            medium goMedium = new medium();
            goMedium.Medium(primaryStage,2,(Set<Integer>)ques[2],qTotal,marks,min,sec);
        }
        else if(marks<15)
        {
            System.out.println("eito pere gelam");
            medium goMedium = new medium();
            goMedium.Medium(primaryStage,2,(Set<Integer>)ques[3],qTotal,marks,min,sec);
        }
        else
        {
            System.out.println("Okay Cholche");
            hard goHard = new hard();
            goHard.Hard(primaryStage,2,(Set<Integer>)ques[4],qTotal,marks,min,sec);
        }
    }
}

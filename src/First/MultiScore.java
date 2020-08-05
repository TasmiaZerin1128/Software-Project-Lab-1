package First;

import javafx.stage.Stage;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MultiScore {
    ArrayList<Integer> marksInd = new ArrayList<Integer>();
    static String N;
    static int TotalM;
    public void Score(int qMarks)
    {
        marksInd.add(qMarks);
    }
    public void setName(String name)
    {
        N = name;
        System.out.println(N);
    }
    public void setFinalMarks(int marks)
    {
        TotalM = marks;
    }
    public void sendP()
    {
        Pair<Integer,String> p = new Pair<>(TotalM,N);
        System.out.println(p.getKey()+" Name: "+p.getValue()+" "+ N);
    }
}

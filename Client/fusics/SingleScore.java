package fusics;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedHashSet;
import java.util.Set;

public class SingleScore {
    int[][] questionSet = new int[10][5];
    static int i=0;
    public void StoreScore(int marks, int mTotal, int qNo, int minute,int diff)
    {
        questionSet[i][0] = marks;
        questionSet[i][1] = mTotal;
        questionSet[i][2] = qNo;
        questionSet[i][3] = minute;
        questionSet[i][4] = diff;
        i++;
    }
    public int[][] sendScore()
    {
        return questionSet;
    }
}

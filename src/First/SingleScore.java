package First;

import javafx.stage.Stage;

import java.util.Set;

public class SingleScore {
    Set<?>[] questionSet = new Set<?>[5];
    int i=0;
    public void StoreScore(int marks, int mTotal, int qNo, int minute, int second)
    {
        ((Set<Integer>)questionSet[i]).add(marks);
        ((Set<Integer>)questionSet[i]).add(mTotal);
        ((Set<Integer>)questionSet[i]).add(qNo);
        ((Set<Integer>)questionSet[i]).add(minute);
        ((Set<Integer>)questionSet[i]).add(second);
        i++;
    }
}

package fusics;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundUpDecimal {
    public static boolean round(double a, double b) {

        BigDecimal aa = new BigDecimal(a); 
        BigDecimal bb = new BigDecimal(b);
        aa = aa.setScale(1, RoundingMode.HALF_UP);
        bb = bb.setScale(1, RoundingMode.HALF_UP);
        int A = aa.intValue();
        int B = bb.intValue();
        System.out.println("aa" + aa + " bb " + bb);
        if(aa.equals(bb)==true || Math.abs(A-B)==0)
        {
           return true;
        }
        else
        {
            return false;
        }
    }
}

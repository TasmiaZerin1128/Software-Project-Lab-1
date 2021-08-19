package First;

public  class Object {
    static SingleQues sq;
    static SingleScore Sscore;
    static practice Practice;
    static MultiScore MS;
    static wait W;
    public static void setSq(SingleQues single)
    {
        sq = single;
    }
    public static SingleQues getSq()
    {
        return sq;
    }
    public static void setSscore(SingleScore score){
        Sscore=score;
    }
    public static SingleScore getSscore()
    {
        return Sscore;
    }
    public static void setPractice(practice pprac){
        Practice = pprac;
    }
    public static practice getPractice()
    {
        return Practice;
    }
    public static void setMulti(MultiScore mul) { MS = mul; }
    public static MultiScore getMulti() { return MS; }
    public static void setWait(wait w){ W = w;}
    public static wait getWait(){ return W;}

}

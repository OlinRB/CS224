public class Interval {

    int start;
    int stop;
     
    public Interval(int start, int stop)
    {
        this.start = start;
        this.stop = stop;
    }

    public boolean checkIntersection(Interval first, Interval second) {
        return true;
    }

    public String toString(){
        return this.start + " " + this.stop;
      }
};

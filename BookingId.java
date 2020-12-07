import java.util.*;

public class BookingId {

    int id;
    int fnum;
    boolean meal;
    int ntickets;
    char tClass;
    ArrayList<Integer> st = new ArrayList<Integer>();

    BookingId(int id, boolean meal, int ntickets, char tClass, int fnum) {
        this.id = id;
        this.meal = meal;
        this.ntickets = ntickets;
        this.tClass = tClass;
        this.fnum = fnum;
    }

}

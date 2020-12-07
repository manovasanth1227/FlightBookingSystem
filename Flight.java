import java.util.*;

public class Flight {
    int fnumber;
    int btickets = 0;
    int etickets = 0;
    int transaction = 0;
    public int seats[] = new int[18];

    Flight(int fnumber) {
        this.fnumber = fnumber;
    }

    public boolean setSeat(int n, char c, ArrayList<Integer> st) {
        int count = 0;
        if (c == 'B' && (6 - btickets) >= n) {
            for (int i = 0; i < 6; i++) {
                if (seats[i] == 0) {
                    seats[i] = -1;
                    st.add(i + 1);
                    btickets++;
                    count++;
                }
                if (count == n) {
                    transaction++;
                    return true;
                }
            }
        } else if (c == 'E' && (12 - etickets) >= n) {
            for (int i = 6; i < 18; i++) {
                if (seats[i] == 0) {
                    seats[i] = -1;
                    st.add(i + 1);
                    etickets++;
                    count++;
                }
                if (count == n) {
                    transaction++;
                    return true;
                }
            }
        }
        return false;
    }
}

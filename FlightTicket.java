import java.util.ArrayList;
import java.util.*;
import java.lang.*;

public class FlightTicket {
    public static ArrayList<Flight> nflights = new ArrayList<Flight>();
    public static ArrayList<BookingId> bid = new ArrayList<BookingId>();
    public static int bookingIDVal = 1;

    public static void createFlight(int fnum) {

        for(int i=0;i<nflights.size();i++){
            if(nflights.get(i).fnumber==fnum){
                System.out.println("========Already Exist=======");
                return;
            }
        }
        Flight f = new Flight(fnum);
        nflights.add(f);
        System.out.println("---------------------------Successfully Created----------------------------");
    }

    public static void handleBooking(int id, int fnum, char tclass, int ntickets, boolean meal) {
        BookingId b = new BookingId(id, meal, ntickets, tclass, fnum);
        for (int i = 0; i < nflights.size(); i++) {
            if (nflights.get(i).fnumber == b.fnum) {
                if (nflights.get(i).setSeat(ntickets, tclass, b.st)) {
                    bid.add(b);
                    System.out.println("Your Booking Id : " + id);
                    bookingIDVal++;
                    System.out.println("---------------------------Successfully Booked-------------------------");

                } else {
                    System.out.println("\n--------------------------Currently Not Available---------------------------");
                }
            }
        }

    }

    public static void viewAvailableSeat() {
        for (int i = 0; i < nflights.size(); i++) {
            System.out.println("Flight ID : " + nflights.get(i).fnumber);
            int f = 0;
            System.out.print("Bussiness class : ");
            
            for (int j = 0; j < 6; j++) {
                if (nflights.get(i).seats[j] == 0) {
                    f = 1;
                    System.out.print(j + 1 + " ");
                }
            }
            if (f == 0) {
                System.out.print("Not available  ");
            }
            System.out.println();
            f = 0;
            System.out.print("Economy class   : ");
            for (int j = 6; j < nflights.get(i).seats.length; j++) {
                if (nflights.get(i).seats[j] == 0) {
                    f = 1;
                    System.out.print(j + 1 + " ");
                }
            }
            if (f == 0) {
                System.out.print("Not available  ");
            }
            if(nflights.size()!=1  && i!=nflights.size()-1)System.out.println("\n");

        }
    }

    public static void cancel(int id) {
        BookingId d = bid.get(id - 1);
        for (int i = 0; i < d.st.size(); i++) {
            int t = d.st.get(i);
            for (int j = 0; j < nflights.size(); j++) {
                if (nflights.get(j).fnumber == d.fnum) {
                    nflights.get(j).seats[t - 1] = 0;
                }
            }
        }
        d.st.clear();
        System.out.println("------------------------------Cancellation Successfull------------------------------"); 

    }

    public static void print(int id){
        BookingId b = bid.get(id-1);
        System.out.println("Booking ID        :  " +b.id);
        System.out.println("Flight Number     :  " +b.fnum);
        if(b.tClass=='E'){
        System.out.println("Ticket Class      :  Economy");
        }else{
        System.out.println("Ticket Class      :  Bussiness");
        }
        System.out.println("Number of Tickets :  " +b.ntickets);
          System.out.print("Seat Number       :  ");
        for(int i=0;i<b.st.size();i++){
            System.out.print(b.st.get(i)+ " ");
        }
        String m =  b.meal? "Added" : "Not Added";
        System.out.println("\nMeals           :  " + m);
        System.out.print("Total Price       :  " );
        price(id);

    }

    public static void price(int id) {
        int price = 0;
        for (int i = 0; i < bid.size(); i++) {
            if (bid.get(i).id == id) {
                int n = bid.get(i).ntickets;
                int c = bid.get(i).tClass;
                if (c == 'E') {
                    int t = 1000;
                    for (int k = 0; k < nflights.size(); k++) {
                        if (bid.get(i).fnum == nflights.get(k).fnumber && nflights.get(k).transaction > 1) {
                            t += (nflights.get(k).transaction - 1) * 100;
                        }
                    }
                    price = n * t;

                } else if (c == 'B') {
                    int t = 2000;
                    for (int k = 0; k < nflights.size(); k++) {
                        if (bid.get(i).fnum == nflights.get(k).fnumber && nflights.get(k).transaction > 1) {
                            t += (nflights.get(k).transaction - 1) * 200;
                        }
                    }
                    price = n * t;
                }
                if (bid.get(i).meal == true) {
                    price += 200 * n;
                }
            }
        }
        System.out.println(price);
    }

    public static void main(String[] args) {
        createFlight(101);
        //handleBooking(bookingIDVal, 101, 'E', 4, true);
        Scanner s = new Scanner(System.in);
        int c=1;
        while(c==1){

            System.out.println("\n\n-----------------------------Choose any one number ----------------------------");
            System.out.println("1 - Create Flight" );
            System.out.println("2 - View Availability" );
            System.out.println("3 - Book Tickets" );
            System.out.println("4 - Cancel Tickets" );
            System.out.println("5 - Print Ticket" );
            System.out.println("6 - Exit" );
            int choice  = s.nextInt();
            if(choice==6)break;
            if(choice==1){
                System.out.println();
                System.out.println("------------------------------Create Flight------------------------------");
                System.out.println("Enter Flight Number :");
                int fn = s.nextInt();
                System.out.println("Loading.......");
                createFlight(fn);               
            }
            if(choice==2){
                System.out.println("-----------------------------Seats Available----------------------------\n");
                viewAvailableSeat();
                System.out.println("\n\n-----------------------------Seats Available----------------------------");
            }
            if(choice==3){
                System.out.println("------------------------------Ticket Booking------------------------------");             
                System.out.println("Enter Flight Number :   ");
                int i = s.nextInt();
                System.out.println("Choose Ticket Class  1-Economy / 2-Bussiness :   ");
                int cls = s.nextInt();
                System.out.println("Enter Total number of Tickets :   ");
                int nt = s.nextInt();
                System.out.println("Enter 1 to add food :   ");
                int f = s.nextInt();
                
                System.out.println("\n========================================================================\nLoading.........\n");
                if(cls==1){
                    if(f==1) {
                        handleBooking(bookingIDVal, i, 'E', nt, true);
                    }else{
                        handleBooking(bookingIDVal, i, 'E', nt, false);
                    }
                   
                }else if(cls==2){
                    if(f==1) {
                        handleBooking(bookingIDVal, i, 'B', nt, true);
                    }else{
                        handleBooking(bookingIDVal, i, 'B', nt, false);
                    }
                }
            }
            if(choice==4){
                System.out.println("------------------------------Ticket Cancelling------------------------------"); 
                System.out.println("Enter Booking ID :"); 

                int cid = s.nextInt();
                System.out.println("Loading.......");
                cancel(cid);
            }
            if(choice==5){
                System.out.println("----------------------------------------------------------------------------"); 
                System.out.println("Enter Booking ID :");
                int bd = s.nextInt();
                System.out.println("==============================Printed Summary==============================");
                print(bd);
                System.out.println("==============================Printed Summary==============================");
            }
            System.out.println("\nDo you wish to continue 1-Y/2-N : ");
            c= s.nextInt();
            
            
        }

       

    }
}
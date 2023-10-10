import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class room {
  int rno;
  String ac;
  int beds;
  String status;

  public room(int rno, String ac, int beds, String status) {
    this.rno = rno;
    this.status = status;
    this.ac = ac;
    this.beds = beds;
  }

  public room() {
  }

  @Override
  public String toString() {
    return "Hotel Room [Room.No=" + rno + ",Room status =" + status + ", Ac status=" + ac + ", BedRoom status=" + beds
        + "]";
  }
}

class customer {

  int cid;
  String cname;
  String checkin;
  String checkout;
  String phno;
  int room_no;
  int rent;

  public customer(int cid, String cname, String checkin, String checkout, String phno, int room_no, int rent) {
    this.cid = cid;
    this.cname = cname;
    this.checkin = checkin;
    this.checkout = checkout;
    this.phno = phno;
    this.room_no = room_no;
    this.rent = rent;
  }

  @Override
  public String toString() {
    return "customer details [id=" + cid + ",name =" + cname + ", check-in=" + checkin + ", check-out=" + checkout
        + ", phno =" + phno + ",room-no =" + room_no + ",rent = " + rent + "]";
  }
}

public class App {
  public static List<room> rooms = new ArrayList<room>();
  public static List<customer> customers = new ArrayList<customer>();

  public static void printHelp() {
    System.out.println("Help for commands:");
    System.out.println("-a <filename> for operation A on filename");
    System.out.println("-b <recordType> <string> for operation on <recordType> and search <string>");
    System.out.println("-h (or any) for help menu");
  }

  public static void main(String[] args) throws IOException, SQLException {


    
      switch ("-a") {
        
       case "-a":
      Connection con  =  connectiontoDB.getcon();
      BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\room.csv"));
      String roo;
      while((roo = reader.readLine()) != null){
        main file = new main();
        file.insertRoomstoDB(roo, con);
    } 
    reader.close();


    case "-b":
    Connection con1  =  connectiontoDB.getcon();
    BufferedReader reader1 = new BufferedReader(new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\customers.csv"));
    String cust;
    
    while((cust = reader1.readLine()) != null){
        main file  = new main();
        file.insertCustomerstoDB(cust, con1);
    }
    customers.forEach(System.out::println);
   /* PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter("customers_data.csv")));
    rooms.forEach(r-> w.println(r));
    wr.close();
    */
    reader1.close();
     break;





    case "-c":
    Connection con2  =  connectiontoDB.getcon();
    String update;
    BufferedReader reader3 = new BufferedReader(new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\updatecus.csv"));
    while ((update = reader3.readLine()) != null){
      main file  = new main();
      file.updateCustomerstoDB(update, con2);
    }
    reader3.close();
  





    case "-d":
    Connection con3  =  connectiontoDB.getcon();
    searchoperation s = new searchoperation();
    s.SearchthroughID(3, con3);
    System.out.println("\n");
    System.out.println("\n");
    s.SearchthroughName("meghana", con3);
    System.out.println("\n");
    System.out.println("\n");
    s.SearchthroughNumeric(1500, con3);
  




    case "-e":
    Connection con4  =  connectiontoDB.getcon();
    BufferedReader reader4 = new BufferedReader(new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\customers.csv"));
    while ((update = reader4.readLine()) != null){
      main file  = new main();
      file.deletecustomersfromDB(update, con4,1);
     }
     reader4.close();
     break;
     
      }
    }
}


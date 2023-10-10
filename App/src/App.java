import java.io.IOException;
import java.sql.SQLException;

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

  public room(){
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

  public static void printHelp() {
    System.out.println("Help for commands:");
    System.out.println("-a to insert Rooms data into DB ");
    System.out.println("-b to insert Customers data into DB");
    System.out.println("-c to update Customers data into DB");
    System.out.println("-d to calculate bill");
    System.out.println("-e to search Customers data through ID in DB");
    System.out.println("-f to search Customers data through Name in DB");
    System.out.println("-g to search Customers data through rent in DB");
    System.out.println("-h to delete Customers data in DB");
    System.out.println("-i (or any) for help menu");
    System.out.println("-j to delete all values in the tables ");
    System.out.println("-k search names through partial strings ");
  }

  public static void main(String[] args) throws IOException, SQLException {
    main M1 = new main();
    searchoperation s = new searchoperation();
    switch (args[0]) {

      case "-a":
        M1.insertRoomstoDB();
        break;

      case "-b":
        M1.insertCustomerstoDB();
        break;

      case "-c":
        M1.updateCustomerstoDB();
        break;

        case "-d":
        M1.CalculateBill(Integer.valueOf(args[1]));
        break;
      case "-e":
        s.SearchthroughID(Integer.valueOf(args[1]));
        break;
      case "-f":
        s.SearchthroughName(args[1]);
        break;
      case "-g":
        s.SearchthroughNumeric(Integer.valueOf(args[1]));
        break;

      case "-h":
        M1.deletecustomersfromDB(Integer.valueOf(args[1]));
        break;
      case "-i":
          printHelp();
          break;

      case "-j":
         Delete_values d = new Delete_values();
             d.delete();
               break;

       case "-k":
      s.SearchthroughPartialString(args[1]);
      break;

      default:
        printHelp();
        break;
    }
  }
}

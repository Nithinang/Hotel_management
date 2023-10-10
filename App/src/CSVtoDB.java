import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

abstract class CSV{
    abstract void insertRoomstoDB();
    abstract void insertCustomerstoDB();
    abstract void updateCustomerstoDB();
    abstract void deletecustomersfromDB(int id);
}

class main extends CSV{

    ArrayList<room>rooms = new ArrayList<room>();
    @Override
    void insertRoomstoDB() {
            
        try {
            Connection con = connectiontoDB.getcon();

            BufferedReader reader = new BufferedReader(
                    new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\room.csv"));
            String roo;
            while ((roo = reader.readLine()) != null) {
                room r  = new room();
                String values[] = roo.split(",");
                String query = "insert into rooms values(?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, Integer.valueOf(values[0]));
                r.rno = Integer.valueOf(values[0]);
                stmt.setString(2, values[1]);
                r.ac = values[1];
                stmt.setString(3, values[2]);
                r.status = values[2];
                stmt.setInt(4, Integer.valueOf(values[3]));
                r.beds = Integer.valueOf(values[3]);
                stmt.executeUpdate();
                rooms.add(r);
            }
                
                PrintWriter writer  = new PrintWriter(new BufferedWriter(new FileWriter("roomsdata1.csv")));
                rooms.forEach(s-> writer.println(s));
                writer.close();
            reader.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    @Override
    void insertCustomerstoDB() {

        try {
            Connection con1 = connectiontoDB.getcon();
            BufferedReader reader1 = new BufferedReader(
                    new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\customers.csv"));
            String cust;

            while ((cust = reader1.readLine()) != null) {
                String values[] = cust.split(",");
                String query = "insert into customers values(?,?,?,?,?,?,?,?)";
                PreparedStatement stmt = con1.prepareStatement(query);
                stmt.setInt(1, Integer.valueOf(values[0]));
                stmt.setString(2, values[1]);
                stmt.setString(3, values[2]);
                stmt.setString(4, values[3]);
                stmt.setString(5, values[4]);
                stmt.setInt(6, Integer.valueOf(values[5]));
                stmt.setInt(7, Integer.valueOf(values[6]));
                stmt.setInt(8, Integer.valueOf(values[7]));
                stmt.executeUpdate();
            }
            reader1.close();
            Statement stmt2 = con1.createStatement();
            String vacancy = "update rooms, customers set room_status = 'occupied' where rooms.room_no = customers.room_no";
            stmt2.executeUpdate(vacancy);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    void updateCustomerstoDB() {

        try {

            Connection con2 = connectiontoDB.getcon();
            String update;
            BufferedReader reader3 = new BufferedReader(
                    new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\updatecus.csv"));
            while ((update = reader3.readLine()) != null) {
                String values[] = update.split(",");

                BufferedReader reader2 = new BufferedReader(
                        new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\customers.csv"));
                int counter = 0;
                String read;

                while ((read = reader2.readLine()) != null) {
                    String old[] = read.split(",");
                    int id = Integer.valueOf(old[0]);
                    if (id == Integer.valueOf(values[0])) {
                        counter++;
                        String query = "update customers set name = ? , checkin = ?,checkout = ?,phone_number = ?,room_no = ? ,rent = ? where id ="
                                + id;
                        PreparedStatement stmt = con2.prepareStatement(query);
                        stmt.setString(1, values[1]);
                        stmt.setString(2, values[2]);
                        stmt.setString(3, values[3]);
                        stmt.setString(4, values[4]);
                        stmt.setInt(5, Integer.valueOf(values[5]));
                        stmt.setInt(6, Integer.valueOf(values[6]));
                        stmt.executeUpdate();
                    }
                }
                if (counter == 0) {
                    String query = "insert into customers values(?,?,?,?,?,?,?,?)";
                    PreparedStatement stmt = con2.prepareStatement(query);
                    stmt.setInt(1, Integer.valueOf(values[0]));
                    stmt.setString(2, values[1]);
                    stmt.setString(3, values[2]);
                    stmt.setString(4, values[3]);
                    stmt.setString(5, values[4]);
                    stmt.setInt(6, Integer.valueOf(values[5]));
                    stmt.setInt(7, Integer.valueOf(values[6]));
                    stmt.setInt(8, Integer.valueOf(values[7]));
                    stmt.executeUpdate();
                }

                reader2.close();
            }
            reader3.close();

            Statement stmt3 = con2.createStatement();
            String updatecust = "update rooms, customers set room_status = 'unoccupied' where rooms.room_no != customers.room_no";
            stmt3.executeUpdate(updatecust);

            Statement stmt2 = con2.createStatement();
            String strUpdate = "update rooms, customers set room_status = 'occupied' where rooms.room_no = customers.room_no";
            stmt2.executeUpdate(strUpdate);

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public void CalculateBill(int id) {
        try {
            Connection con = connectiontoDB.getcon();
            String query = "select rent,checkin,checkout from customers where id = " + id;
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            int rent = 0;
            String checkin = "";
            String checkout = "";
            while (rs.next()) {
                rent = rs.getInt("rent");
                checkin = rs.getString("checkin");
                checkout = rs.getString("checkout");
            }
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(checkin);
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(checkout);

            long milliseconds = date2.getTime() - date1.getTime();

            long days = TimeUnit.MILLISECONDS.toDays(milliseconds) % 365;
            long bill = days * rent;
            int Bill = (int) bill;

            String query1 = "update customers set Bill=? where id=" + id;
            PreparedStatement stmt1 = con.prepareStatement(query1);
            stmt1.setInt(1, Bill);
            stmt1.executeUpdate();
            System.out.println("Bill of customer is :" + Bill);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    void deletecustomersfromDB(int id) {
        try {
            Connection con4 = connectiontoDB.getcon();
            Statement stmt1 = con4.createStatement();
            String vacancy = "update rooms, customers set room_status = 'unoccupied' where rooms.room_no = customers.room_no AND customers.id="+id;
            stmt1.executeUpdate(vacancy);
            String query = "delete from customers where id =" + id;
            PreparedStatement stmt = con4.prepareStatement(query);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
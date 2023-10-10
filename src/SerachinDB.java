import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

interface Search{

   void SearchthroughID(int id,Connection con);
   void SearchthroughName(String name,Connection con);
   void SearchthroughNumeric(int rent , Connection con);
}


class searchoperation implements Search{

    @Override
    public void SearchthroughID(int id, Connection con) {
       String query = "select * from customers where id = " + id;
       try {
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            System.out.print("CustomerID: " + rs.getInt("id")+" ");
            System.out.print("CustomerName: " + rs.getString("name")+" ");
            System.out.print("Customer checkin date : " + rs.getString("checkin")+" ");
            System.out.print("Customer checkout date : " + rs.getString("checkout")+" ");
            System.out.print("Customer phone number : " + rs.getString("phone_number")+" ");
            System.out.print("Customer room no : " + rs.getInt("room_no")+" ");
            System.out.println("Customer rent: " + rs.getInt("rent"));
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    @Override
    public void SearchthroughName(String name, Connection con) {
       String query = "select * from customers where name = ? ";
       try {
        
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            System.out.print("CustomerID: " + rs.getInt("id")+" ");
            System.out.print("CustomerName: " + rs.getString("name")+" ");
            System.out.print("Customer checkin date : " + rs.getString("checkin")+" ");
            System.out.print("Customer checkout date : " + rs.getString("checkout")+" ");
            System.out.print("Customer phone number : " + rs.getString("phone_number")+" ");
            System.out.print("Customer room no : " + rs.getInt("room_no")+" ");
            System.out.println("Customer rent: " + rs.getInt("rent"));
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void SearchthroughNumeric(int rent, Connection con) {
         
        String query = "select * from customers where rent = " + rent;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.print("CustomerID: " + rs.getInt("id")+" ");
                System.out.print("CustomerName: " + rs.getString("name")+" ");
                System.out.print("Customer checkin date : " + rs.getString("checkin")+" ");
                System.out.print("Customer checkout date : " + rs.getString("checkout")+" ");
                System.out.print("Customer phone number : " + rs.getString("phone_number")+" ");
                System.out.print("Customer room no : " + rs.getInt("room_no")+" ");
                System.out.println("Customer rent: " + rs.getInt("rent"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

interface Search{

   void SearchthroughID(int id);
   void SearchthroughName(String name);
   void SearchthroughNumeric(int rent);
}


class searchoperation implements Search{

    @Override
  
    public void SearchthroughID(int id) {
       String query = "select * from customers where id = " + id;
       try {
        Connection con3 = connectiontoDB.getcon();
        PreparedStatement stmt = con3.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            System.out.println("<----------------------------------------->");
            System.out.println("CustomerID: " + rs.getInt("id")+" ");
            System.out.println("CustomerName: " + rs.getString("name")+" ");
            System.out.println("Customer checkin date : " + rs.getString("checkin")+" ");
            System.out.println("Customer checkout date : " + rs.getString("checkout")+" ");
            System.out.println("Customer phone number : " + rs.getString("phone_number")+" ");
            System.out.println("Customer room no : " + rs.getInt("room_no")+" ");
            System.out.println("Customer rent: " + rs.getInt("rent"));
            System.out.println("<----------------------------------------->");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    @Override
    public void SearchthroughName(String name) {
       String query = "select * from customers where name = ? ";
       try {
        Connection con3 = connectiontoDB.getcon();
        PreparedStatement stmt = con3.prepareStatement(query);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            System.out.println("<----------------------------------------->");
            System.out.println("CustomerID: " + rs.getInt("id")+" ");
            System.out.println("CustomerName: " + rs.getString("name")+" ");
            System.out.println("Customer checkin date : " + rs.getString("checkin")+" ");
            System.out.println("Customer checkout date : " + rs.getString("checkout")+" ");
            System.out.println("Customer phone number : " + rs.getString("phone_number")+" ");
            System.out.println("Customer room no : " + rs.getInt("room_no")+" ");
            System.out.println("Customer rent: " + rs.getInt("rent"));
            System.out.println("<----------------------------------------->");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void SearchthroughNumeric(int rent) {
         
        String query = "select * from customers where rent = " + rent;
        try {
            Connection con3 = connectiontoDB.getcon();
            PreparedStatement stmt = con3.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println("<----------------------------------------->");
                System.out.println("CustomerID: " + rs.getInt("id")+" ");
                System.out.println("CustomerName: " + rs.getString("name")+" ");
                System.out.println("Customer checkin date : " + rs.getString("checkin")+" ");
                System.out.println("Customer checkout date : " + rs.getString("checkout")+" ");
                System.out.println("Customer phone number : " + rs.getString("phone_number")+" ");
                System.out.println("Customer room no : " + rs.getInt("room_no")+" ");
                System.out.println("Customer rent: " + rs.getInt("rent"));
                System.out.println("<----------------------------------------->");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SearchthroughPartialString(String n) {
        String query = "select*from customers where name like '%" + n + "%'";
        try {
            Connection con3 = connectiontoDB.getcon();
            PreparedStatement stmt = con3.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println("<----------------------------------------->");
                System.out.println("CustomerID: " + rs.getInt("id")+" ");
                System.out.println("CustomerName: " + rs.getString("name")+" ");
                System.out.println("Customer checkin date : " + rs.getString("checkin")+" ");
                System.out.println("Customer checkout date : " + rs.getString("checkout")+" ");
                System.out.println("Customer phone_number : " + rs.getString("phone_number")+" ");
                System.out.println("Customer room_no : " + rs.getInt("room_no")+" ");
                System.out.println("Customer rent: " + rs.getInt("rent"));
                System.out.println("<----------------------------------------->");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
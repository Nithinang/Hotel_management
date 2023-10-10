import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

abstract class CSV{
   abstract void insertRoomstoDB(String roo, Connection con);
   abstract void insertCustomerstoDB(String cust,Connection con);
   abstract void updateCustomerstoDB(String update,Connection con);
   abstract void deletecustomersfromDB(String roo,Connection con,int id); 
}

class main extends CSV{

    @Override
    void insertRoomstoDB(String roo, Connection con) {
         String values[] = roo.split(",");
         String query = "insert into rooms values(?,?,?,?)";
        try(PreparedStatement stmt = con.prepareStatement(query)){
           stmt.setInt(1, Integer.valueOf(values[0]));
           stmt.setString(2,values[1]);
           stmt.setString(3,values[2]);
           stmt.setInt(4,Integer.valueOf(values[3]));
           stmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    void insertCustomerstoDB(String cust, Connection con) {
        String values[] = cust.split(",");
         String query = "insert into customers values(?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = con.prepareStatement(query)){
           stmt.setInt(1,Integer.valueOf(values[0]) );
           stmt.setString(2, values[1]);
           stmt.setString(3,values[2]);
           stmt.setString(4,values[3]);
           stmt.setString(5,values[4]);
           stmt.setInt(6,Integer.valueOf(values[5]));
           stmt.setInt(7,Integer.valueOf(values[6]));
           stmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    void updateCustomerstoDB(String update, Connection con) {
       
       
     try {
        String values[] = update.split(",");
       
        BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Users\\nithi\\OneDrive\\Documents\\Monsoon 2021\\customers.csv"));
        int counter = 0;
        String read;
       
        while ((read = reader2.readLine()) != null){
            String old[] = read.split(",");
            int  id = Integer.valueOf(old[0]);
            if(id == Integer.valueOf(values[0])){
                counter++;
                String query = "update customers set name = ? , checkin = ?,checkout = ?,phone_number = ?,room_no = ? ,rent = ? where id ="+id;
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1,values[1]);
                stmt.setString(2,values[2]);
                stmt.setString(3,values[3]);
                stmt.setString(4,values[4]);
                stmt.setInt(5,Integer.valueOf(values[5]));
                stmt.setInt(6,Integer.valueOf(values[6]));
                stmt.executeUpdate();
            }
        }
        if(counter == 0){
            String query = "insert into customers values(?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1,Integer.valueOf(values[0]) );
            stmt.setString(2,values[1]);
            stmt.setString(3,values[2]);
            stmt.setString(4,values[3]);
            stmt.setString(5,values[4]);
            stmt.setInt(6,Integer.valueOf(values[5]));
            stmt.setInt(7,Integer.valueOf(values[6]));
            stmt.executeUpdate();
        }
        
        reader2.close();
    } catch (Exception e1){
        e1.printStackTrace();
    }
     
    }

    @Override
    void deletecustomersfromDB(String roo, Connection con,int id) {
       String query = "delete from customers where id ="+id;
       try(PreparedStatement stmt = con.prepareStatement(query)){
           stmt.executeUpdate();
       }catch(Exception e){
           e.printStackTrace();
       }
    }
    public void deleteCustomerstoDB(String update, Connection con) {
    } 
}
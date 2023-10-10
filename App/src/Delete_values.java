import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete_values {
    public  void delete(){
        try {
            Connection con = connectiontoDB.getcon();
            String query1 = "delete from customers";
            String query2 = "delete from rooms";
            try(PreparedStatement statement = con.prepareStatement(query1)) {
                statement.executeUpdate();
            }
            try(PreparedStatement statement2 = con.prepareStatement(query2)) {
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
}

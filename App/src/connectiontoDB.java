
import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectiontoDB {
    public static Connection getcon() throws SQLException{
        Properties connectionProps = new Properties();
        connectionProps.put("user","root");
        connectionProps.put("password","nithin@$2001");
        String URL = "jdbc:mysql://localhost:3306/oop";
       
        Connection con = DriverManager.getConnection(URL,connectionProps);
        return con;
    }
}

package app;

import java.sql.*;
import java.util.HashMap;

public class dbconnection {
    public static Connection getConn() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/erasmusdb","root","");
        return connection;
    }

    private static Connection conn;
    public static Statement st;
    private static ResultSet rs;
    private static String url="jdbc:mysql://localhost:3306/erasmusdb";
    private static String user="root",pass="";

    public dbconnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,pass);
            st=conn.createStatement();
            rs=st.executeQuery("select * from erasmusdb.users");

            String username,password,Role;
            HashMap<String, String> upcombo = new HashMap<>();
            HashMap<String, String> userRole = new HashMap<>();
            while(rs.next())
            {
                username=rs.getString("neptun");
                password=rs.getString("password");
                upcombo.put(username,password);
                Role=rs.getString("role");
                userRole.put(username,Role);
            }
            SampleController.userpass=upcombo;
            SampleController.userRole=userRole;
        }catch(Exception ex){
            System.out.println("Error"+ex);
        }
    }
}

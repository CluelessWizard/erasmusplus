package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class dbconnection {
    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;
    private static String url="jdbc:mysql://localhost:3306/erasmusdb";
    private static String user="root",pass="";

    public dbconnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,pass);
            st=conn.createStatement();
            rs=st.executeQuery("select * from erasmusdb.hallgato");

            String username,password;
            HashMap<String, String> upcombo = new HashMap<>();
            while(rs.next())
            {
                username=rs.getString("Neptun");
                password=rs.getString("Jelszo");
                upcombo.put(username,password);
            }
            SampleController.userpass=upcombo;
        }catch(Exception ex){
            System.out.println("Error"+ex);
        }
    }
}

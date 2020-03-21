package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class dbconnection {
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
            rs=st.executeQuery("select * from erasmusdb.hallgato");

            String username,password,oktazonosito;
            List<String> tmp=new ArrayList<String>();
            HashMap<String, String> upcombo = new HashMap<>();
            while(rs.next())
            {
                username=rs.getString("Neptun");
                password=rs.getString("Jelszo");
                upcombo.put(username,password);
                oktazonosito=rs.getString("OktAzon");
                tmp.add(oktazonosito);
            }
            SampleController.userpass=upcombo;
            SampleController.oktazon=tmp;
        }catch(Exception ex){
            System.out.println("Error"+ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Talaat
 */
public class Database {
    boolean iscloud;
    static boolean global_flag = false;
    Connection cn;
    Statement st;
    HttpURLConnection con;
    URL url;
    DatabaseMetaData dm;
    public Database() 
    {
        try
        {
        url = new URL("http://www.google.com");
        Class.forName("com.mysql.jdbc.Driver");
         cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_emb?zeroDateTimeBehavior=convertToNull","klydar_talaat","&?f2~PPhXwqd");
         iscloud = true;
         st = cn.createStatement();
        }
        catch(Exception e)
        {try{
       cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_emb?zeroDateTimeBehavior=convertToNull","root","");
       iscloud=false;
       st=cn.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Problem with local database");
        }
        }
    }//end connection
    public ResultSet select_query(String query) 
    {
        ResultSet rs = null;
        try
        {
            
        set_dbconnection(true);
        Statement ste= cn.createStatement();
        rs = ste.executeQuery(query);
        return rs;
        }
        catch(Exception e)
        {
            try{
          cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_emb?zeroDateTimeBehavior=convertToNull","root","");
          st=cn.createStatement(); 
           }catch(Exception ex){
               JOptionPane.showMessageDialog(null, "proplem with local database");
           }
        }
        return rs;
    }//end select query
     public void updata_query(String query) 
    {
        try{
        
            set_dbconnection(true);
            Statement ste= cn.createStatement();
            ste.executeUpdate(query);
        }
        catch(Exception e)
        {
            try{
          cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_emb?zeroDateTimeBehavior=convertToNull","root","");
          st=cn.createStatement(); 
           }catch(Exception ex){
               JOptionPane.showMessageDialog(null, "proplem with local database");
           }
        }   
    }//end update query
     public void close_db(){
        try {
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     public boolean isnet(){
         boolean flag = false;
         try{
                    con = (HttpURLConnection) url.openConnection();
                    con.connect();
                    if (con.getResponseCode() == 200){
                            System.out.println("Connection established!!");
                            flag = true;
                    }
            } catch (Exception exception) {
                    System.out.println("No Connection");
            } 
         return flag;
    }//end of function
   public void set_dbconnection(boolean cloud){
       if(cloud){
           try{
                dm=cn.getMetaData();
                if(dm.getUserName().equals("root@localhost")){
                cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_emb?zeroDateTimeBehavior=convertToNull","klydar_talaat","&?f2~PPhXwqd");
                System.out.println("Cloud");
                //st=cn.createStatement(); 
                }
                 }
                 catch(Exception e){
                     set_dbconnection(false);
           }
       }
       else{
           try{
          cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_emb?zeroDateTimeBehavior=convertToNull","root","");
          st=cn.createStatement(); 
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "proplem with local database");
           }
       }
   }
}
     


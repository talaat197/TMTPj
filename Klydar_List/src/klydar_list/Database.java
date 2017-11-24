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
    //HttpURLConnection con;
    //URL url;
    DatabaseMetaData dm;
    public Database() 
    {
        try
        {
         Class.forName("com.mysql.jdbc.Driver");
         this.cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","klydar","&?f2~PPhXwqd");
         iscloud=true;
        }
        catch(Exception e)
        {try{
       cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","root","");
       
       iscloud=false;
       st=cn.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Internet conncetion problem");
            }
        }
    }//end connection
    public ResultSet select_query(String query) 
    {
        ResultSet rs = null;
        try
        {
        if(!cn.isValid(2)){
            System.out.println("here");
           this.cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","klydar","&?f2~PPhXwqd");
        }
        Statement ste= cn.createStatement();
        rs = ste.executeQuery(query);
        return rs;
        }
        catch(Exception e)
        {
           JOptionPane.showMessageDialog(null,e); 
        }
        return rs;
    }//end select query
     public void updata_query(String query) 
    {
        try{
            if(cn.isValid(2));
            else{
               this.cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","klydar","&?f2~PPhXwqd");
                 }
            Statement ste= cn.createStatement();
            ste.executeUpdate(query);
        }
        catch(Exception e)
        {   
            JOptionPane.showMessageDialog(null,e);
        }   
    }//end update query
     public void switch_to_local(){
 try {
    if(iscloud){
    cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","root","");
    iscloud = false;
        }
    else{
       this.cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","klydar","&?f2~PPhXwqd"); 
       iscloud = true;
    }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Problem with local "+ex);
        }
     }
     public void close_db(){
        try {
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
    /*
   public void set_dbconnection(boolean cloud){
       if(cloud){
           try{
                dm=cn.getMetaData();
                if(dm.getUserName().equals("root@localhost")){
                cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","klydar","&?f2~PPhXwqd");
                
                //st=cn.createStatement(); 
                }
                 }
                 catch(Exception e){
                     System.out.println(e);
                     set_dbconnection(false);
           }
       }
       else{
           try{
          cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","root","");
          st=cn.createStatement(); 
           }catch(Exception e){
               JOptionPane.showMessageDialog(null, "proplem with local database");
           }
       }
   }
     */
}
     

/* public boolean isnet(){
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
    }//end of function */
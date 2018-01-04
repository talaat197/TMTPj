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
    static boolean iscloud;
    static boolean global_flag = false;
    Connection cn;
    Statement st;
    //specify IP->0 DB Name->1
    String [] LAN_info = {"192.168.1.107:3306","klydar_cm"};
    String LAN = "jdbc:mysql://"+LAN_info[0]+"/"+LAN_info[1]+"?zeroDateTimeBehavior=convertToNull";
    //Cloud info IP->0 , DB Name->1 , username->2 , password->3
    String[] CLOUD_info = {"192.185.13.161:3306","klydar_cm","klydar","&?f2~PPhXwqd"};
    String CLOUD ="jdbc:mysql://"+CLOUD_info[0]+"/"+CLOUD_info[1]+"?zeroDateTimeBehavior=convertToNull";
    String LOCAL = "jdbc:mysql://localhost:3306/klydar_cm?zeroDateTimeBehavior=convertToNull";
    //HttpURLConnection con;
    //URL url;
    DatabaseMetaData dm;
    public Database() 
    {
        try
        {
         Class.forName("com.mysql.jdbc.Driver");
         this.cn = DriverManager.getConnection(CLOUD,CLOUD_info[2],CLOUD_info[3]);
         iscloud=true;
        }
        catch(Exception e)
        {try{
       cn = DriverManager.getConnection(LOCAL,"root","");
       
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
           this.cn = DriverManager.getConnection(CLOUD,CLOUD_info[2],CLOUD_info[3]);
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
               this.cn = DriverManager.getConnection(CLOUD,CLOUD_info[2],CLOUD_info[3]);
                 }
            Statement ste= cn.createStatement();
            ste.executeUpdate(query);
        }
        catch(Exception e)
        {   
            JOptionPane.showMessageDialog(null,e);
        }   
    }//end update query
     public void switch_to_local(int choice){ // take the choice of the connection -> {Localhost , Cloud , LAN connection}
 try {
    if(choice == 0){//conncet to the localhost
    cn = DriverManager.getConnection(LOCAL,"root","");
    iscloud = false;
        }
    else if (choice == 1){ //connect to the cloud
       this.cn = DriverManager.getConnection(CLOUD,CLOUD_info[2],CLOUD_info[3]);
       iscloud = true;
    }
    else if(choice == 2){//lan connection
        System.out.println(LAN);
        this.cn = DriverManager.getConnection(LAN); 
            }
    } catch (Exception ex) {
    JOptionPane.showMessageDialog(null,"Problem with The connection you choosed "+ ex);
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
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
    static boolean global_flag = false;
    Connection cn;
    Statement st;
    //specify IP->0 DB Name->1
    public static String [] LAN_info = {"192.168.1.142:3306","klydar_cm"};
    public static String LAN;
    //Cloud info IP->0 , DB Name->1 , username->2 , password->3
    public static String[] CLOUD_info = {"192.185.78.63:3306","ab282_cm","ab282_admin","123qweasdzxc"};
    public static String CLOUD;
    public static String LOCAL = "jdbc:mysql://localhost:3306/klydar_cm?characterEncoding=utf8";
    public boolean isCloud = false;
    //HttpURLConnection con;
    //URL url;
    DatabaseMetaData dm;
    public Database() 
    {
        try
        {
         Class.forName("com.mysql.jdbc.Driver");
            connectToCloud();
        }
        catch(Exception e)
        {
            alert_frame obj = new alert_frame("Failure to connect to the server");
            obj.setVisible(true);
        }
    }//end connection
    public ResultSet select_query(String query) 
    {
                   

        ResultSet rs = null;
        try
        {
        if(!cn.isValid(2)){
           connectToCloud();
        }
        
        Statement ste= cn.createStatement();
        rs = ste.executeQuery(query);
        
        return rs;
        }
        catch(Exception e)
        {
            
           alert_frame obj = new alert_frame("Failure to Select Cell from server");
           obj.setVisible(true); 
        }
        return rs;
    }//end select query
     public void updata_query(String query) 
    {
        try{
            if(cn.isValid(2));
            else{
               connectToCloud();
                 }
            Statement ste= cn.createStatement();
            
            ste.executeUpdate(query);
        }
        catch(Exception e)
        {   
            //JOptionPane.showMessageDialog(null, e);
            alert_frame obj = new alert_frame("Failure to update Data in the server");
            obj.setVisible(true);
            
        }   
    }//end update query 
    //function to connect to speific database 
     
    public void connectToCloud(){
        try{
         CLOUD ="jdbc:mysql://"+CLOUD_info[0]+"/"+CLOUD_info[1]+"?characterEncoding=utf8";
         this.cn = DriverManager.getConnection(CLOUD,CLOUD_info[2],CLOUD_info[3]);
         isCloud = true; // used to know if it' success to access cloud 
        }catch(Exception ex){
            try{
            isCloud = false; 
            //System.out.println(ex);
            //alert_frame obj = new alert_frame("failure to Connect to CLOUD server");
            //obj.setVisible(true);
            
            connectToLan();
        }
        catch(Exception e){
            
            alert_frame obj = new alert_frame("Failure to connect to Localhost");
            obj.setVisible(true);
            }
        }
    }
    public void connectToLan(){
        try{
         LAN = "jdbc:mysql://"+LAN_info[0]+"/"+LAN_info[1]+"?characterEncoding=utf8";
         this.cn = DriverManager.getConnection(LAN);  
        }catch(Exception ex){
           // alert_frame obj = new alert_frame("Failure to connect to LAN Server");
            //obj.setVisible(true);
            try{cn = DriverManager.getConnection(LOCAL,"root","");
            //System.out.println(LAN);
            }
            catch(Exception e){
            Klydar_List.data_list.set_connection_type("No Internet");
            }//obj = new alert_frame("Failure to connect to LAN Server"); obj.setVisible(true);
        }
    }
    public char connection_type(){
        try {
            
            dm = cn.getMetaData();
            String cloudName ="klydar_cm@156.209.186.29";
            String networkName = "@";
            String localName = "root@localhost";
            String dbname = dm.getUserName();
            
            if (dbname.equals(networkName)) return 'N';
            else if (dbname.equals(localName)) return 'L';
            else return 'C';
        } catch (SQLException ex) {
            connectToCloud();
            connection_type();
            //System.out.println("Connection Type:"+ ex);
        }
    return 'F';
    }
     public void switch_to_local(int choice){ // take the choice of the connection -> {Localhost , Cloud , LAN connection}
        try {
            wait_thread.wf.setVisible(true);
            if(choice == 0){//conncet to the localhost
                
            cn = DriverManager.getConnection(LOCAL,"root","");
            
                }
            else if (choice == 1){ //connect to the cloud
               connectToCloud();
               
            }
            else if(choice == 2){//lan connection    
                connectToLan();
                
                    }
            wait_thread.wf.setVisible(false);
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(null,"Problem with The connection you choosed "+ ex);
    }

         }
     public void close_db(){
        try {
            cn.close();
        } catch (SQLException ex){
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
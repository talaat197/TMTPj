/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Talaat
 */
public class Klydar_List {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here
        /*Database obj = new Database();
        String query = ("insert into userdata (id,name, status, type, sponsor, AFF, mobile, email) VALUES(5,'talaat', 0,'committee', 'sponsor','hey', 123, 'maise_mardon@hotmail.com')");
        obj.updata_query(query);  */
        try{
        Data f = new Data();
        f.setVisible(true);
        } 
        catch(Exception e)
        {
            
        }
       
    }
    
}

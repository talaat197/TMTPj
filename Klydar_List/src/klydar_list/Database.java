/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Talaat
 */
public class Database {
    Connection cn;
    Statement st;
    public Database()
    {
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
         cn = DriverManager.getConnection("jdbc:mysql://192.185.13.161:3306/klydar_emb?zeroDateTimeBehavior=convertToNull","klydar_talaat","&?f2~PPhXwqd");
         st = cn.createStatement();
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, e);
        }
    }//end connection
    public ResultSet select_query(String query) 
    {
        ResultSet rs = null;
        try
        {
        rs = st.executeQuery(query);
        
        return rs;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Database Connection Error");
        }
        return rs;
    }//end select query
     public void updata_query(String query) 
    {
        try
        {
            st.executeUpdate(query);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//end update query
     public void close_db(){
        try {
            cn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
}

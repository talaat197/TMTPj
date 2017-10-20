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
         cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar?zeroDateTimeBehavior=convertToNull","root","");
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
            JOptionPane.showMessageDialog(null, "Database Connection Error");
        }
    }//end update query
}

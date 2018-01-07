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
//import java.util.Base64;
import java.util.concurrent.TimeUnit;
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
        String query = ("create table admin_setting (column_setting varchar(300) , id_setting int)");
        obj.updata_query(query);
        obj.updata_query("insert into `admin_setting` (`column_setting` , `id_setting`) values ('',1)");*/
        try
        {
            Data s = new Data();
            s.setVisible(true);
            
           /*Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","root","");
            Statement st=cn.createStatement();
            int v = st.executeUpdate("INSERT INTO `lists` (`vip`, `tag`, `conf_id`, `Payment`, `name`, `name_ar`, `type`, `attendees`, `on_site`, `mobile`, `email`, `position`, `address`, `sponsor`, `name_tag`, `bag`, `coffee`, `lunch`, `certificate`, `presence`, `create_time`, `name_tag_time`, `certificate_time`, `bag_time`, `coffee_time`, `lunch_time`, `update_time`, `Profession`, `Institution`, `note`, `country`, `Reg`, `Hotel`, `Session Title`)"
                    + " VALUES ('', '', 0, '', 'ashrf', '', '', 0, 0, '', '', '', '', '', 0, 0, 0, 0, 0, 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', '', '', '', '', '')");
            System.out.println(v);*/
        } 
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
       
    }
    
}

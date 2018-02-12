/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import User.Login;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.MOUSE_PRESSED;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import javax.swing.JOptionPane;
import static klydar_list.Klydar_List.data_list;

/**
 *
 * @author Talaat
 */
public class Klydar_List {
    public static Data data_list;
    /**
     * @param args the command line arguments
     */
    public static Thread waiting;
    public static void main(String[] args)  {
        
        try
        {
            checkIfRunning();
            wait_thread wt=new wait_thread();
            waiting = new Thread(wt);
            waiting.start();
            
            Login user = new Login();
            user.setVisible(true);

           /*Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/klydar_cm?zeroDateTimeBehavior=convertToNull","root","");
            Statement st=cn.createStatement();
            int v = st.executeUpdate("INSERT INTO `lists` (`vip`, `tag`, `conf_id`, `Payment`, `name`, `name_ar`, `type`, `attendees`, `on_site`, `mobile`, `email`, `position`, `address`, `sponsor`, `name_tag`, `bag`, `coffee`, `lunch`, `certificate`, `presence`, `create_time`, `name_tag_time`, `certificate_time`, `bag_time`, `coffee_time`, `lunch_time`, `update_time`, `Profession`, `Institution`, `note`, `country`, `Reg`, `Hotel`, `Session Title`)"
                    + " VALUES ('', '', 0, '', 'ashrf', '', '', 0, 0, '', '', '', '', '', 0, 0, 0, 0, 0, 0, '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', '', '', '', '', '')");
            System.out.println(v);*/
        } 
        catch(Exception e)
        {
            try {
                
                JOptionPane.showMessageDialog(null,"Main "+ e);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Exception "+ e);
            }
        }
        
    }//end main
    /********************************************************************/
    private static final int PORT = 9999;
    private static ServerSocket socket;    
    private static void checkIfRunning() {
      try {
        //Bind to localhost adapter with a zero connection queue 
        socket = new ServerSocket(PORT,0,InetAddress.getByAddress(new byte[] {127,0,0,1}));
      }
      catch (BindException e) {       
        System.exit(1);
      }
      catch (IOException e) {
        
        e.printStackTrace();
        System.exit(2);
      }
    }//end check if running
    /********************************************************************/
}//end class
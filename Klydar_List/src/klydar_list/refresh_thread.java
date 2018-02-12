/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import javax.swing.JOptionPane;
import static klydar_list.Klydar_List.data_list;

/**
 *
 * @author Klydar
 */
   
   public class refresh_thread implements Runnable
    {
       public boolean on = true;
        @Override
        public void run()
        { while (on)
            {
                try {       
                    Thread.sleep(DatabaseConstants.refresh_interval*60000);
                    data_list.refresh_action();
                }
            catch(Exception e)
            {
                data_list.refresh_action();
                //JOptionPane.showMessageDialog(null, e);
            }
        }//end of while 
    }//end thread 1
}
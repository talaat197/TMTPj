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
 public class data_thread implements Runnable
    {
        private Thread refresh;
        private refresh_thread ref_obj;
        public String UType;
        public data_thread(Thread refresh_th,refresh_thread ref_obj,String user_type){
            this.ref_obj = ref_obj;
            UType = user_type;
            refresh = refresh_th;
        }
        @Override
        public void run()
        {
            try
            {
            data_list = new Data(refresh,UType,ref_obj);
            data_list.setVisible(true);
            }
                catch (Exception ex) {
                    //JOptionPane.showMessageDialog(null, "Thread refresh "+ex);
                }            
        }//end run thread
    }//end thread data

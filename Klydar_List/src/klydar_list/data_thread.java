/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import Network.Server_Thread;
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
        private Server_Thread th_st;
        public data_thread(Thread refresh_th,refresh_thread ref_obj,String user_type ,Server_Thread th_st){
            this.th_st = th_st;
            this.ref_obj = ref_obj;
            UType = user_type;
            refresh = refresh_th;
        }
        @Override
        public void run()
        {
            try
            {
            data_list = new Data(refresh,UType,ref_obj,th_st);
            data_list.setVisible(true);
            }
                catch (Exception ex) {
                    //JOptionPane.showMessageDialog(null, "Thread refresh "+ex);
                }            
        }//end run thread
    }//end thread data

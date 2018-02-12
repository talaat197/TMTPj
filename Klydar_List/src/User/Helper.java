/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author Talaat
 */
public class Helper implements Runnable {
    
    @Override
    public void run() {
        
        
            try {
                
                klydar_list.Klydar_List.data_list.return_login();
            } catch (Exception e) {//interrupted
                
                
            }
        
    }

}

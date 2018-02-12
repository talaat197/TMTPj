/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

/**
 *
 * @author Klydar
 */
public class wait_thread implements Runnable{

    public static wait_frame wf ;
    @Override
    public void run() 
    {
        try{
        
        wf = new wait_frame();
        wf.setVisible(true);
        
        }
        catch(Exception e){
            
            wf.dispose();
        }
       //wf.setVisible(true);
    }//end rub 
}//end runnable class

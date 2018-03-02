/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import User.chat;
import java.awt.Color;

/**
 *
 * @author Talaat
 */
public class Server_Thread implements Runnable{
    
    public Server myserver;
    String messages="";
    String username="";
    public boolean on = true;
    public Server_Thread(int myport) {
        try{
        myserver = new Server(myport);//initilaize server
        }
        catch(Exception e){
            System.out.println("Constructor of server_thread ->"+e);
        }
    }
    
    @Override
    public void run() {
       
            //on = true;
            while(on){
                myserver.listen();
                username = myserver.get_input();//get the username who will send this data
                messages=myserver.get_input(); // get the input data
                chat.rec_message(messages, username);
                klydar_list.Data.jMenuBar1.setBackground(new Color(217, 83, 79));
            }
        
            
        
        
    }
    
}

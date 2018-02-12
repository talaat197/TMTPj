/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

/**
 *
 * @author Talaat
 */
public class VirtualDatabase implements Runnable{
    int choice;
    String query;
    public VirtualDatabase(int choice,String query){
        this.choice = choice;
        this.query = query;
    }
    @Override
    public void run() {
      if(choice == 1){
          Data.db.updata_query(query);
      }
      else{
          
          Data.db.updata_query(query);
      }
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;

/**
 *
 * @author Talaat
 */
public class Backup implements Runnable
    {
        public static Database obj;
        public static int Server_mood = 0;
        public  boolean ToServer = false;
        public static boolean first_time = true;
        @Override
        public void run() {     
            try
            {
                               
                while(true)
                {
                    if(first_time){//first time backup button pressed create object
                        obj = new Database();
                        first_time = false;}
                    //sleep 
                    Thread.sleep(DatabaseConstants.BACKUP_TIME*60000);
                    if(Server_mood == 1){// get backup 
                        if(Advanced_Settings.IsServer == true){
                            if(Data.db.connection_type() == 'C'){
                                ResultSet rs = Data.db.select_query(DatabaseConstants.selectAll); // select all the data from the db
                                ToServer = false;
                                insert_data(rs);
                                System.out.println("Backup from server");
                                }//end if
                            else if(Data.db.connection_type() == 'N'){
                                obj.switch_to_local(1);//switch to cloud

                                if(obj.isCloud == true) {
                                    System.out.println("Backup To server");
                                    ResultSet rs = Data.db.select_query(DatabaseConstants.selectAll); // select all the data from the db
                                    ToServer = true;
                                    insert_data(rs);
                                    rs.close();
                                }
                                else; // do nothing
                                    }// end else if 
                              }//end is server
                        else{
                            if(Data.db.connection_type() != 'L'){//if u r in the local db we don't need to take backup
                            
                            ResultSet rs = Data.db.select_query(DatabaseConstants.selectAll); // select all the data from the db
                            insert_data(rs);}
                                }//end else of the is server
                            }//end server mood
                }
               
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        // functionto export the local database
        public  void get_backup(String db_name,String db_pw,String db_user){
            try{
                String executeCmd ="C:\\xampp\\mysql\\bin\\mysqldump -u "+db_user+" "+db_name+" -r backup7.sql";           
                   Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
                   int processComplete = runtimeProcess.waitFor();
                   if(processComplete == 0){
                   System.out.println("success");
                   
                   } else {
                   System.out.println("restore failure");
                   }
            }//try
            catch(Exception e){System.out.println(e);}
           }
        //funtion to insert resultset data to another database
        public void insert_data(ResultSet rs){
            try {
                ResultSetMetaData rsMeta = rs.getMetaData();
                int ColumNo = rsMeta.getColumnCount();
                System.out.println("Success BACKUP");
                
                String query = create_insert(rsMeta,rs,ColumNo);
                
                if(Advanced_Settings.IsServer == true){
                    if(ToServer == true)  obj.switch_to_local(1); //switch to cloud to upload  your Data
                    else obj.switch_to_local(2); // switch to server (my local) to upload data 
                }//end if
                else {
                    obj.switch_to_local(0);
                } // switch to local         
                
                
                obj.updata_query("delete  from "+DatabaseConstants.tableName+"");
                obj.updata_query(query);
                query = null;
                    }catch(Exception ex){
                      alert_frame fram = new alert_frame("Baack up Issues: ->"+ex.toString());
                      JOptionPane.showMessageDialog(null,"Backup up issues ->"+ex);
            }
        }
        //funtion to create insert query
        public String create_insert(ResultSetMetaData rsMeta,ResultSet rs,int ColumNo){
            String query="insert into "+DatabaseConstants.tableName+" values(";
            String temp = "";
            try{
                    rs.next();
            while(true){
                for(int count = 1;count <= ColumNo;count++){
                    //check f=if the Colum to insert String or Integer
                    if(rsMeta.getColumnClassName(count).equals("java.lang.String")){
                        //the first time i don't need , for the insert
                        //System.out.println("Test1");
                        if(count ==1) {
                            temp = DatabaseConstants.clear_garapage_String(rs.getString(count));
                            query +="'"+temp+"'";
                        }
                        else {
                            temp = DatabaseConstants.clear_garapage_String(rs.getString(count));
                            query +=",'"+temp+"'";
                        }
                    }
                    else if(rsMeta.getColumnClassName(count).equals("java.lang.Integer")){
                        //System.out.println("Test2");
                        
                        if(count ==1) {query +=rs.getInt(count);}
                        else query +=","+rs.getInt(count);

                    }
                    else if (rsMeta.getColumnClassName(count).equals("java.sql.Timestamp")){ 
                        if(rs.getTimestamp(count) == null) query +=",'0000-00-00 00:00:00'";
                        else query +=",'"+rs.getTimestamp(count)+"'"; 
                    }// end else if
                    else{
                        //System.out.println("Test3");
                        if(count ==1) {
                            temp = DatabaseConstants.clear_garapage_String(rs.getString(count));
                            query +="'"+temp+"'";   }
                        else {
                            temp = DatabaseConstants.clear_garapage_String(rs.getString(count));
                            query +=",'"+temp+"'";
                        }
                    }
            }// end for
                if(rs.next() == false) {query +=")"; break;}
                else query += "),(";
               
            }//end while
            return  query;
        }//end try
        catch(Exception e){
            System.out.println(e);
        }//end of catch
        return query;
        }//end of func create_insert
    }//end of backup class

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
public class DatabaseConstants {
    public static String all_data ="select ID ,name,attendees,email,type,sponsor,mobile,Profession,Institution,country from lists"; 
    public static String select_name = "Select name from lists where id='";
    public static String set_attend = "update lists set attendees=1 where ID='";
    public static String clear_attend = "update lists set attendees=0 where ID='";
    
    public static String Update_table(String table_name,String colm_name,String Updated_value,int primary_key){
        return "update "+table_name+" set "+colm_name+"='"+Updated_value+"' where ID='"+primary_key+"'";
    }
    public static String Selectall_ByValue(String where_str,String value){
        return all_data +" where "+where_str+"='"+value+"'";
    }
    public static String Selectall_By2value(String where_1,String where_2,String value1,String value2){
        return all_data + " where "+where_1+"='"+value1+"' && "+ where_2 +"='"+value2+"'";
    }
    
}

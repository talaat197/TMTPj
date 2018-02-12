/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import User.Login;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Talaat
 */
public class DatabaseConstants {

    public static String colm_connection = "connection";
    public static String tableName = "lists";
    public static String global_stt_table = "global_settings";
    public static String tag_colm = "name_tag";
    public static String cert_colm = "certificate";
    public static String user_Settings = "user_settings";
    public static String selectAll = "select * from lists";
    public static int refresh_interval = 5;
    public static int DINNER_MODE = 0;
    public static int BACKUP_TIME = 10;
    public static String attend = "ON";
    public static String no_attend = "OFF";
    public static Color no_attend_col = new Color(217, 83, 79);
    public static Color attend_col = new Color(92, 184, 92);
    public static String all_data = "select ID ,name,attendees,email,type,sponsor,mobile,Profession,Institution,country from lists";
    public static String select_name = "Select name from lists where id='";
    public static String update_2tables = "Update "+tableName+","+user_Settings+" Set "+tableName+".";
    public static String clear_garapage_String(String garp) {
        if (garp == null) {
            return "";
        }
        garp = garp.replaceAll("'", "");
        garp = garp.replaceAll(",", "");
        garp = garp.replaceAll("\"", "");
        return garp;
    }
    public static String update_2tables(String colms1,String colms2,String value2,String where_value1,String where_value2){//take care it's attendees 
        
        String query ="UPDATE "+tableName+", "+user_Settings+" SET "+user_Settings+"."+colms1+" = "+colms1+"+1 ,"+tableName+"."+colms2+" ='"+value2+"' where ID = "+where_value1+" and username ='"+where_value2+"'";
        return query;
    }
    public static String update_2tables(String colms1,String colms2,String where_value1,String where_value2){
        
        String query ="UPDATE "+tableName+", "+user_Settings+" SET "+user_Settings+"."+colms1+" = "+colms1+"+1 ,"+tableName+"."+colms2+" ="+colms2+"+1  where ID = "+where_value1+" and username ='"+where_value2+"'";
        
        return query;
    }
    public static String update_two_tables(int choice, ArrayList<String> tables, HashMap<String, String> columns_values, HashMap<String, String> where_values) {
        String my_query = "UPDATE";
        for (int i = 0; i < tables.size(); i++) {
            if (i == 0) {
                my_query += " " + tables.get(i);
                continue;
            }
            my_query += " , " + tables.get(i);
        }
        my_query += " SET ";
        int i = 0;
        for (Map.Entry me : columns_values.entrySet()) {
            if (choice == 1) {
                if (i == (columns_values.size() - 1)) {

                    my_query += (me.getKey() + " = '" + me.getValue() + "'  ");
                } else {
                    my_query += (me.getKey() + " = '" + me.getValue() + "' , ");
                }
            } else {
                if (i == (columns_values.size() - 1)) {

                    my_query += (me.getKey() + " = " + me.getValue() + "  ");
                } else {
                    my_query += (me.getKey() + " = " + me.getValue() + " , ");
                }
            }

            i++;
        }
        int j = 0;
        for (Map.Entry me : where_values.entrySet()) {
            if (j == 0) {
                my_query += " where " + me.getKey() + " = '" + me.getValue() + "'";
            } else {
                my_query += " and " + me.getKey() + " = '" + me.getValue() + "'";
            }
            j++;
        }
        
        return my_query;
    }

    public static String update_user_Setting(String table_name, String updated_colm, String where_colm) {

        char conn_type = Data.db.connection_type();
        String query = "update " + table_name + " set " + updated_colm + "=" + updated_colm + "+1," + colm_connection + "='" + conn_type + "'  where " + where_colm + "='" + Login.Uname + "'";

        return query;
    }

    public static String Update_table(String table_name, String colm_name, String Updated_value, int primary_key, boolean string) {
        char conn_type = Data.db.connection_type();
        String query;
        if (string) {
            query = "update " + table_name + " set " + colm_name + "='" + Updated_value + "'," + colm_connection + "='" + conn_type + "'  where ID='" + primary_key + "'";
        } else {
            Updated_value = clear_garapage_String(Updated_value);
            query = "update " + table_name + " set " + colm_name + "=" + Updated_value + "," + colm_connection + "='" + conn_type + "'  where ID='" + primary_key + "'";
        }

        return query;
    }
     public static String Update_table(String table_name, String colm_name, String Updated_value,String where_colm, int primary_key, boolean string) {
        char conn_type = Data.db.connection_type();
        String query;
        if (string) {
            query = "update " + table_name + " set " + colm_name + "='" + Updated_value + "' where "+where_colm+"='" + primary_key + "'";
        } else {
            Updated_value = clear_garapage_String(Updated_value);
            query = "update " + table_name + " set " + colm_name + "=" + Updated_value + " where "+where_colm+"='" + primary_key + "'";
        }

        return query;
    }

    public static String Selectall_ByValue(String where_str, String value) {
        return all_data + " where " + where_str + "='" + value + "'";
    }

    public static String Selectall_By2value(String where_1, String where_2, String value1, String value2) {
        return all_data + " where " + where_1 + "='" + value1 + "' && " + where_2 + "='" + value2 + "'";
    }

    public static String update_attend(boolean clear, int id) {
        String query = "";
        try {
            String set_attend = "update lists set attendees=1," + colm_connection + "='" + Data.db.connection_type() + "' where ID='";
            String clear_attend = "update lists set attendees=0," + colm_connection + "='" + Data.db.connection_type() + "' where ID='";

            if (clear) {
                query = clear_attend + id + "'";
            } else {
                query = set_attend + id + "'";
            }

        } catch (Exception e) {
            System.out.println("update_attend " + e);
        }
        return query;
    }
}

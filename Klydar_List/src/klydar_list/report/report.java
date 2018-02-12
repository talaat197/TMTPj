/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list.report;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Klydar
 */
public class report 
{
    /**
     * @return ***************************************************/
    public static ArrayList get_all_types ()
    {
        ArrayList<String> all_type = new ArrayList<>();
        try
        {
            ResultSet get_type = klydar_list.Data.db.select_query("SELECT DISTINCT type from lists");
            while(get_type.next())
            {
                all_type.add(get_type.getString("type"));
            }
            return all_type;
        }//end try
        catch(Exception e)
        {

        }//end catch
        return all_type;
    }//end get_all_types
    /*****************************************************/
    public static ArrayList get_perc (String type)
    {
        float all=0;
        float specific_type=0;
        int perc = 0;
        ArrayList percs = new ArrayList();
        try
        {
            ResultSet get_count_specific_type = klydar_list.Data.db.select_query("select count(type) as c from lists where type='"+type+"' and attendees='1'");
            ResultSet get_all_count = klydar_list.Data.db.select_query("select count(type) as c from lists where type ='"+type+"'");
            if(get_count_specific_type.next())
                specific_type=get_count_specific_type.getInt("c");
            if(get_all_count.next())
                all=get_all_count.getInt("c");
            perc = (int) ((specific_type/all)*100);
            percs.add(perc);
            percs.add((int)specific_type);
            percs.add((int)all);
        }//end try
        catch(Exception e)
        {
            
        }//end catch
        return percs;
    }//end get_perc
     /*****************************************************/
     public static ArrayList get_all_statistics ()
    {
        ArrayList<String> all_Statistics = new ArrayList<>();
        try
        {
            all_Statistics.add("certificate");
            all_Statistics.add("name_tag");
            all_Statistics.add("attendees");
            all_Statistics.add("on_site");
            all_Statistics.add("dinner");
        }//end try
        catch(Exception e)
        {
            
        }//end catch
        return all_Statistics;
    }//end get_all_types
    /*****************************************************/
     public static ArrayList get_perc_actions (String col_name)
    {
         ArrayList percs = new ArrayList();
        float all=0;
        float specific_type=0;
        int perc = 0;
        try
        {
            ResultSet get_count_specific_type = klydar_list.Data.db.select_query("select count("+col_name+") as c from lists where "+col_name+"=1");
            ResultSet get_all_count = klydar_list.Data.db.select_query("select count("+col_name+") as c from lists");
            if(get_count_specific_type.next())
                specific_type=get_count_specific_type.getInt("c");
            if(get_all_count.next())
                all=get_all_count.getInt("c");
            perc = (int) ((specific_type/all)*100);
            percs.add(perc);
            percs.add((int)specific_type);
            percs.add((int)all);
        }//end try
        catch(Exception e)
        {
            System.out.println(e);
        }//end catch
        return percs;
    }//end get_perc
     /*****************************************************/
}//end class

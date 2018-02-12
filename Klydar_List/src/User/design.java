/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import klydar_list.Data;
import klydar_list.DatabaseConstants;

/**
 *
 * @author Klydar
 */
public class design {
    

    public static void set_alert_message(JTextField jt, String message) {
        jt.setBackground(new Color(248, 215, 218));
        jt.setForeground(new Color(144, 69, 76));
        jt.setFont(new Font("Tahoma", Font.BOLD, 18));
        jt.setText(message);
    }

    public static void set_correct_message(JTextField jt, String message) {
        jt.setBackground(new Color(212, 237, 218));
        jt.setForeground(new Color(14, 49, 22));
        jt.setFont(new Font("Tahoma", Font.BOLD, 18));
        jt.setText(message);
    }

    
}

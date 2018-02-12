/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import klydar_list.AddUser;
import klydar_list.Advanced_Settings;
import klydar_list.Backup;
import klydar_list.Data;
import static klydar_list.Data.db;
import static klydar_list.Data.filter1;
import static klydar_list.Data.filter2;
import static klydar_list.Data.system_password;
import klydar_list.Database;
import klydar_list.DatabaseConstants;
import klydar_list.Website_form;
import klydar_list.alert_frame;
import klydar_list.data_thread;
import klydar_list.refresh_thread;
import klydar_list.wait_thread;

/**
 *
 * @author Talaat
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Database conn;
    public String authorized;
    public static String Uname;
    public static int allow_add = 0;
    public static int allow_print_cme = 0;
    public static int allow_update = 0;

    public Login() throws InterruptedException {
        try{
        
        this.setUndecorated(true);
        
        initComponents();
        
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        get_configuration();
        conn = new Database();
        
        get_global_settings();

        wait_thread.wf.setVisible(false);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        login = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        login.setBackground(new java.awt.Color(26, 198, 255));
        login.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(26, 198, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        username.setBackground(new java.awt.Color(230, 230, 230));
        username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        username.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "UserName", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });

        password.setBackground(new java.awt.Color(230, 230, 230));
        password.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        password.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/User/log.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void get_configuration() {
        try {
            File f = new File("configration.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String newline = null;
            ArrayList<String> all_configuration = new ArrayList<>();
            while ((newline = br.readLine()) != null) {
                String splits[] = newline.split("=");
                all_configuration.add(splits[1].replace(" ", ""));
            }
            Database.CLOUD_info[0] = all_configuration.get(0);
            Database.CLOUD_info[1] = all_configuration.get(1);
            Database.CLOUD_info[2] = all_configuration.get(2);
            Database.CLOUD_info[3] = all_configuration.get(3);
            Database.LAN_info[0] = all_configuration.get(4);
            Database.LAN_info[1] = all_configuration.get(5);

        } catch (IOException e) {
            alert_frame obj = new alert_frame("configuration file not found");
            obj.setVisible(true);
        }
    }

    public void get_global_settings() {
        try {

            ResultSet ad_s = conn.select_query("select * from " + DatabaseConstants.global_stt_table + " where id_setting='1'");
            if (ad_s.next()) {
                Database.CLOUD_info[0] = ad_s.getString("cloud_ip");
                Database.CLOUD_info[1] = ad_s.getString("cloud_db");
                Database.CLOUD_info[2] = ad_s.getString("cloud_username");
                Database.CLOUD_info[3] = ad_s.getString("cloud_password");
                Database.LAN_info[0] = ad_s.getString("lan_ip");
                Database.LAN_info[1] = ad_s.getString("lan_db");
                DatabaseConstants.BACKUP_TIME = ad_s.getInt("backup_time");
                DatabaseConstants.refresh_interval = ad_s.getInt("refresh_time");
                Backup.Server_mood = ad_s.getInt("backup");
                Data.filter1 = ad_s.getString("filter1");
                Data.filter2 = ad_s.getString("filter2");
                Data.system_password = ad_s.getString("system_password");
                /*restore not building yet
                  validation
                  
                 */
                String direct = ad_s.getString("direct_conn");
                if (direct.equals("no")) {
                    Advanced_Settings.directcon = false;
                } else {
                    Advanced_Settings.directcon = true;
                }
                String setting = ad_s.getString("column_setting");
                DatabaseConstants.all_data = setting;
                conn.switch_to_local(1);//connect to cloud 

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Get global " + e);
        }

    }
    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
        loginActionPerformed(null);
    }//GEN-LAST:event_usernameActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        /**
         * **********************************
         */
        String name = username.getText();
        String pass = password.getText();
        String query = "select * from user_settings where username = '" + name + "' and password = '" + pass + "'";
        try {
            ResultSet result = conn.select_query(query);
            if (result.first()) {
                if (result.getInt("block") == 1) {
                    alert_frame obj = new alert_frame("You have been blocked");
                    obj.setVisible(true);
                    return;
                }
                authorized = result.getString("type");
                Uname = result.getString("username");
                allow_add = result.getInt("addUser");
                allow_print_cme = result.getInt("allow_cme");
                allow_update = result.getInt("update_users");
                //is he stand with cip area or not 
                if (result.getInt("vip") == 1) {
                    Advanced_Settings.isVip = true;
                } else {
                    Advanced_Settings.isVip = false;
                }
                //get validation
                if (result.getInt("validation") == 1) {
                    AddUser.valid = true;
                } else {
                    AddUser.valid = false;
                }
                if (!authorized.equals("hall")) {
                    start_frame();
                } else {
                    Website_form web = new Website_form("http://think-ds.com/projects/signing_io/public/admin/signing?hall_id=7", 0);
                    web.display();
                }
                conn.close_db();
                this.dispose();

            } else {
                alert_frame alert = new alert_frame("Invalid Username Or Password");
                alert.setVisible(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_loginActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
        loginActionPerformed(null);
    }//GEN-LAST:event_passwordActionPerformed

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFocusGained

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFocusLost

    private void start_frame() {
        refresh_thread rt = new refresh_thread();
        Thread refresh = new Thread((Runnable) rt);
        // wait_thread wt=new wait_thread();
        data_thread dt = new data_thread(refresh, rt, authorized);
        Thread data_list = new Thread((Runnable) dt);
        Backup ba = new Backup();
        Thread backup = new Thread((Runnable) ba);
        //Thread waiting = new Thread(wt);
        //waiting.start();
        refresh.start();
        data_list.start();
        backup.start();
        //wait_thread.wf.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}

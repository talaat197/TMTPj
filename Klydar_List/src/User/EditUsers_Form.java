/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import klydar_list.Data;
import klydar_list.alert_frame;
import klydar_list.correct_frame;
import klydar_list.wait_thread;

/**
 *
 * @author Talaat
 */
public class EditUsers_Form extends javax.swing.JFrame {

    /**
     * Creates new form EditUsers_Form
     */
    public String original_username;
    public String[] users;
    boolean array_string;  // this variable determine if array is passed or just one user
    public EditUsers_Form(String username) {
        initComponents();
        original_username = username;
        get_user_privilages(username);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        array_string = false;
    }
    public EditUsers_Form(String[] usernames) {
        initComponents();
        users = usernames;
        //let's disable the text fields
        user.setEnabled(false);
        pass.setEnabled(false);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        array_string = true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        print_cme = new javax.swing.JCheckBox();
        allow_update = new javax.swing.JCheckBox();
        typ = new javax.swing.JComboBox<>();
        user = new javax.swing.JTextField();
        pass = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        vip = new javax.swing.JCheckBox();
        valid = new javax.swing.JCheckBox();
        add_user = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Privilage");
        setResizable(false);

        print_cme.setText("Allow print CME");

        allow_update.setText("Allow Update users");

        typ.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "user" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Username");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Password");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        vip.setText("VIP");

        valid.setText("Add Form Validation");

        add_user.setText("Allow add users");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(valid)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(print_cme)
                                    .addComponent(vip))
                                .addGap(67, 67, 67)
                                .addComponent(allow_update))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(typ, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(37, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(typ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vip)
                    .addComponent(valid)
                    .addComponent(allow_update))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print_cme)
                    .addComponent(add_user))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //get the privilage of specific user
    private void get_user_privilages(String username){
        String get_query = "Select * from user_settings where username='"+username+"'";
        ResultSet result = Data.db.select_query(get_query);
        try {
            if(result.next()){
                user.setText(username);
                pass.setText(result.getString("password"));
                check_box(add_user,result.getInt("addUser"));
                check_box(allow_update,result.getInt("update_users"));
                check_box(print_cme,result.getInt("allow_cme"));
                check_box(valid,result.getInt("validation"));
                check_box(vip,result.getInt("vip"));
                typ.setSelectedItem(result.getString("type"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"get_user_privilages func ->" +ex);
        }
    }
    //select the check box depending on 1 or 0 value
    public void check_box(JCheckBox privilage,int value){
        if(value == 1)privilage.setSelected(true);
        else privilage.setSelected(false);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(array_string)//true mean more then one user
            update_multi_privilage(); 
        else
            update_one_privilage();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void update_one_privilage(){
        String username,password,type;
        int VIP,VALID,add,cme,update;
        username = user.getText();
        password = pass.getText();
        type = typ.getSelectedItem().toString();
        if(username.equals("") || password.equals(""))
        {
            alert_frame obj = new alert_frame("Empty field not allowed");
            obj.setVisible(true);
        }
        //
        if(vip.isSelected()) VIP = 1;
        else VIP = 0;
        if(valid.isSelected()) VALID = 1;
        else VALID = 0;
        if(add_user.isSelected()) add = 1;
        else add = 0;
        if(print_cme.isSelected()) cme = 1;
        else cme = 0;
        if(allow_update.isSelected())  update = 1;
        else update=0;
        String update_query = "update user_settings SET username='"+username+"',password='"+password+"',type='"+type.toLowerCase()+"',vip="+VIP+""
                + ",validation="+VALID+",addUser="+add+","
                + "allow_cme="+cme+",update_users="+update+" where username='"+original_username+"'";
        
         
        Data.db.updata_query(update_query);
         
        correct_frame mess = new correct_frame("Done");
        mess.setVisible(true);
    }
    public void update_multi_privilage(){
        
        String type;
        String whereusers = create_users_query();
        int VIP,VALID,add,cme,update;
        
        type = typ.getSelectedItem().toString();
        
        //
        if(vip.isSelected()) VIP = 1;
        else VIP = 0;
        if(valid.isSelected()) VALID = 1;
        else VALID = 0;
        if(add_user.isSelected()) add = 1;
        else add = 0;
        if(print_cme.isSelected()) cme = 1;
        else cme = 0;
        if(allow_update.isSelected())  update = 1;
        else update=0;
        String update_query = "update user_settings SET type='"+type.toLowerCase()+"',vip="+VIP+""
                + ",validation="+VALID+",addUser="+add+","
                + "allow_cme="+cme+",update_users="+update+" "+whereusers; 
        
         
        Data.db.updata_query(update_query);
         
        correct_frame mess = new correct_frame("Done");
        mess.setVisible(true);
    
    }
    //function to create the where query to multi users
    public String create_users_query(){
        String where_users=" where username ='";
        for(int count =0;count<users.length ;count++){
            if(count == 0)//first loop
                where_users +=users[count]+"'";
            else{
                where_users +=" || username='"+users[count]+"'";
            }
        }
        
        return where_users;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox add_user;
    private javax.swing.JCheckBox allow_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField pass;
    private javax.swing.JCheckBox print_cme;
    private javax.swing.JComboBox<String> typ;
    private javax.swing.JTextField user;
    private javax.swing.JCheckBox valid;
    private javax.swing.JCheckBox vip;
    // End of variables declaration//GEN-END:variables
}
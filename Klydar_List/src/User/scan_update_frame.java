/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import static java.awt.Color.BLACK;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import klydar_list.DatabaseConstants;
import klydar_list.alert_frame;

/**
 *
 * @author Talaat
 */
public class scan_update_frame extends javax.swing.JFrame {

    /**
     * Creates new form scan_update_frame
     */
    public scan_update_frame() {
        initComponents();
        
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        scan_code.requestFocus();
    }
    public scan_update_frame(String text){
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        design.set_correct_message(message, text);
        scan_code.requestFocus();
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
        scan_code = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        message = new javax.swing.JTextField();
        adver = new JLabel(" ",SwingConstants.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        scan_code.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        scan_code.setForeground(new java.awt.Color(41, 58, 74));
        scan_code.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        scan_code.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Scan Here ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 36), new java.awt.Color(41, 58, 74)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 58, 74), 2))); // NOI18N
        scan_code.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                scan_codeFocusGained(evt);
            }
        });
        scan_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scan_codeActionPerformed(evt);
            }
        });
        scan_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                scan_codeKeyReleased(evt);
            }
        });

        jTextField2.setBackground(new java.awt.Color(41, 58, 74));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("Update Your Data");

        message.setEditable(false);
        message.setBackground(new java.awt.Color(238, 238, 238));
        message.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        message.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        message.setBorder(null);

        adver.setBackground(java.awt.Color.black);
        adver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Advertising.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
            .addComponent(message, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scan_code)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adver, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(237, 237, 237))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adver, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(scan_code, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scan_codeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_scan_codeFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_scan_codeFocusGained

    private void scan_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scan_codeActionPerformed
        // TODO add your handling code here:
        try
        {
            String id = scan_code.getText();
            //clear , " / from the id
            id = DatabaseConstants.clear_garapage_String(id);
            ResultSet get_data = klydar_list.Data.db.select_query("select * from lists where id='"+id+"'");
            if(get_data.next())
            {
                dispose();
                update_Data ud = new update_Data(get_data);
                ud.setVisible(true);
            }
            else
            {
                design.set_alert_message(message, "Not Found");
                scan_code.setText("");
            }
        }//end try
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_scan_codeActionPerformed

    private void scan_codeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_scan_codeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_scan_codeKeyReleased

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField message;
    private javax.swing.JTextField scan_code;
    // End of variables declaration//GEN-END:variables
}

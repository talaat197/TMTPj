/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import klydar_list.Admin_Setting;
import klydar_list.Data;
import static klydar_list.Data.buildTableModel;
import static klydar_list.Data.db;
import klydar_list.Database;
import klydar_list.DatabaseConstants;
import klydar_list.Klydar_List;
import klydar_list.alert_frame;
import klydar_list.Data;
import klydar_list.correct_frame;

/**
 *
 * @author Talaat
 */
public class Admin extends javax.swing.JFrame {

    /**
     * Creates new form Admin
     */
    Database conn;
    ResultSet rs;
    String query = "Select `username`,`ip`,`block`,`type`, `no_attendees`, `no_tag`, `no_certificate`, `no_add`, `no_update` from " + DatabaseConstants.user_Settings + "";
    JTable users = new JTable();

    public Admin() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        conn = Data.db;
        display_data();
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                            delete_user();
                        }

                        return false;
                    }
                });
    }

    public void delete_user() {
        try {
            if (users.getSelectedRowCount() > 1 || users.getSelectedRowCount() == 0) {

                alert_frame af = new alert_frame("Choose One user");
                af.setVisible(true);
            } else {

                int rownum = users.getSelectedRow();

                // int id = Integer.parseInt(Userdata.getValueAt(rownum, 0).toString());
                Data obj = new Data();
                String query;
                String name = users.getValueAt(rownum, obj.getColumnIndex(users, "Username")).toString();
                if (name.equals("TMT") || name.equals("sheko")) {
                    alert_frame alert = new alert_frame("Can't delete the main Admin");
                    alert.setVisible(true);
                    return;
                }
                query = "Delete from " + DatabaseConstants.user_Settings + " where username='" + name + "'";
                conn.updata_query(query);
                correct_frame c = new correct_frame("Done Deleted");
                c.setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void display_data() {
        try {
            rs = conn.select_query(query);
            users = new JTable(buildTableModel(rs));
            jScrollPane2.setViewportView(users);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            for (int i = 0; i < users.getColumnCount(); i++) {
                users.getColumnModel().getColumn(i).setMinWidth(220);
                users.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            }
            Color white_gray = new Color(251, 251, 251);
            users.setBackground(white_gray);
            users.setFont((new Font("Arial Unicode MS", 0, 18)));
            users.setRowHeight(45);
            users.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 25));
            JTableHeader header = users.getTableHeader();
            Color white_gray_selection = new Color(199, 239, 252);
            Color ztone = new Color(0, 153, 153);
            Color white_blue = new Color(26, 198, 255);
            header.setBackground(white_blue);
            header.setForeground(Color.WHITE);
            users.setSelectionBackground(white_gray_selection);
            users.setSelectionForeground(Color.BLACK);
            users.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            users.setRowMargin(10);
            set_data();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * *******************************************************************
     */
    public void set_data() {
        users.getDefaultEditor(String.class).addCellEditorListener(
                new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                //System.out.println("editingCanceled");
            }

            public void editingStopped(ChangeEvent e) {
                try {
                    if (users.getSelectedRowCount() == 1) {
                        String sponsor = "";
                        String AFF = "";
                        String name = "";
                        int selected_row = users.getSelectedRow();
                        String id = users.getValueAt(selected_row, 0).toString();
                        String column_name = users.getColumnName(users.getSelectedColumn());
                        //column_name=column_name.toLowerCase();
                        String data_updated = users.getValueAt(selected_row, users.getSelectedColumn()).toString();
                        //clear ' , " from the string
                            data_updated = DatabaseConstants.clear_garapage_String(data_updated);
                            db.updata_query("update user_settings set " + column_name + "='" + data_updated + "' where username = '" + id + "'");
                            db.updata_query(DatabaseConstants.update_user_Setting(DatabaseConstants.user_Settings, "no_update", "username"));
                        
                    }//if select one row
                    else {
                        alert_frame af = new alert_frame("Select One");
                        af.setVisible(true);
                    }//if select more one row
                }//end try
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    //alert_frame af = new alert_frame("Invalid Data");
                    //af.setVisible(true);
                }//end catch
            }//end function

        });
    }

    /**
     * *******************************************************************
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        block = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        edit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        add = new javax.swing.JMenu();
        gui = new javax.swing.JMenu();
        refresh = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        block.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        block.setForeground(new java.awt.Color(51, 51, 51));
        block.setText("Un/Block");
        block.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                blockMouseMoved(evt);
            }
        });
        block.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockActionPerformed(evt);
            }
        });

        edit.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        edit.setForeground(new java.awt.Color(51, 51, 51));
        edit.setText("Edit");
        edit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                editMouseMoved(evt);
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(41, 58, 74));
        jMenuBar1.setForeground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setToolTipText("");

        add.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add New");
        add.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addMousePressed(evt);
            }
        });
        add.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                addMouseMoved(evt);
            }
        });
        add.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addFocusLost(evt);
            }
        });
        jMenuBar1.add(add);

        gui.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gui.setForeground(new java.awt.Color(255, 255, 255));
        gui.setText("Gui Settings");
        gui.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        gui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                guiMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                guiMousePressed(evt);
            }
        });
        gui.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                guiMouseMoved(evt);
            }
        });
        gui.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                guiFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                guiFocusLost(evt);
            }
        });
        jMenuBar1.add(gui);

        refresh.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("Refresh");
        refresh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        refresh.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                refreshMouseMoved(evt);
            }
        });
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                refreshMousePressed(evt);
            }
        });
        jMenuBar1.add(refresh);

        jMenu4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 50, 1, 1));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/klydar_list/loading.gif"))); // NOI18N
        jMenuBar1.add(jMenu4);

        jMenu5.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jMenu5.setForeground(new java.awt.Color(102, 102, 102));
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMenu5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenu5MouseMoved(evt);
            }
        });
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu5MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(600, Short.MAX_VALUE)
                .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(block, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseMoved
        // TODO add your handling code here:
        clear_selection();
        add.setSelected(true);
    }//GEN-LAST:event_addMouseMoved
    public void clear_selection() {
        gui.setSelected(false);
        refresh.setSelected(false);
        add.setSelected(false);

    }
    private void addFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addFocusGained
        // TODO add your handling code here
    }//GEN-LAST:event_addFocusGained

    private void addFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_addFocusLost

    private void addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_addMouseExited

    private void addMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMousePressed
        // TODO add your handling code here:
        try {
            Add_Users obj = new Add_Users();
            obj.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_addMousePressed

    private void refreshMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseMoved
        // TODO add your handling code here:
        clear_selection();
        refresh.setSelected(true);
    }//GEN-LAST:event_refreshMouseMoved

    private void refreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMousePressed
        // TODO add your handling code here:
        /*filter_sponsor.removeAllItems();
        filter_type.removeAllItems();
        display_data();
        insert_buttons();*/
        display_data();

    }//GEN-LAST:event_refreshMousePressed

    private void jMenu5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseMoved

    private void jMenu5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MousePressed

    private void guiMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guiMouseMoved
        // TODO add your handling code here:
        clear_selection();
        gui.setSelected(true);
    }//GEN-LAST:event_guiMouseMoved

    private void guiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_guiFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_guiFocusGained

    private void guiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_guiFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_guiFocusLost

    private void guiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guiMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_guiMouseExited

    private void guiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guiMousePressed
        // TODO add your handling code here:
        Admin_Setting obj = new Admin_Setting(Klydar_List.data_list);
        obj.setVisible(true);
    }//GEN-LAST:event_guiMousePressed

    private void blockMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blockMouseMoved
        // TODO add your handling code here:
        block.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_blockMouseMoved

    private void blockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockActionPerformed
        // TODO add your handling code here:
        try {
            if (users.getSelectedRowCount() > 1 || users.getSelectedRowCount() == 0) {
                alert_frame af = new alert_frame("Choose One");
                af.setVisible(true);
            } else {
                int rownum = users.getSelectedRow();
                // int id = Integer.parseInt(Userdata.getValueAt(rownum, 0).toString());
                Data obj = new Data();
                String query;
                String name = users.getValueAt(rownum, obj.getColumnIndex(users, "Username")).toString();
                if (name.equals("TMT") || name.equals("sheko")) {
                    alert_frame af = new alert_frame("You can't delete the main admin");
                    af.setVisible(true);
                    return;
                }
                String block_n = users.getValueAt(rownum, obj.getColumnIndex(users, "Block")).toString();
                if (!name.equals(Login.Uname)) {

                    if (block_n.equals("1")) {
                        query = "Update " + DatabaseConstants.user_Settings + " SET block = 0 where username ='" + name + "'";
                    } else {
                        query = "Update " + DatabaseConstants.user_Settings + " SET block = 1 where username ='" + name + "'";
                    }
                    conn.updata_query(query);
                } else {
                    alert_frame alert = new alert_frame("Aru you stupid ?");
                    alert.setVisible(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_blockActionPerformed

    private void editMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_editMouseMoved
    public int getColumnIndex(JTable table, String header) {
        try {
            for (int i = 0; i < table.getColumnCount(); i++) {
                if (table.getColumnName(i).equals(header)) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Get ColumIndex -> " + e);
        }
        return -1;
    }
    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        int row = users.getSelectedRow(), colum_index; // the first row selected
        int rownum = users.getSelectedRowCount();//number of rows selected
        String username;
        if(users.getSelectedRowCount() == 1){
            colum_index = getColumnIndex(users,"Username");
            username = users.getValueAt(row,colum_index).toString();
            EditUsers_Form edit_form = new EditUsers_Form(username);
            edit_form.setVisible(true);
        }
        else if(rownum > 1){
            colum_index = getColumnIndex(users,"Username");
            int[] rows = users.getSelectedRows();
            String[] usernames = new String[rownum];// number of users selected
            //fill the array with all the usernames selected
            for(int value=0;value<rownum; value++){
                usernames[value] = users.getValueAt(rows[value],colum_index).toString();
            }
            EditUsers_Form edit_form = new EditUsers_Form(usernames);
            edit_form.setVisible(true);
        }
        else{
            alert_frame alert = new alert_frame("Select at least on user");
            alert.setVisible(true);
        }
    }//GEN-LAST:event_editActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu add;
    private javax.swing.JButton block;
    private javax.swing.JButton edit;
    private javax.swing.JMenu gui;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu refresh;
    // End of variables declaration//GEN-END:variables
}

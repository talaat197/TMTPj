/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import com.github.sarxos.webcam.Webcam;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.MessageFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 
 * @author Talaat
 */
public class Data extends javax.swing.JFrame{
    JTable Userdata;
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
     Database db;
     JTextArea hide = new JTextArea();   // text area for printing 
    /**
     * Creates new form Data
     */
    public Data() {
         db = new Database();
        initComponents();
        
        display_data();
        
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        print_bt = new javax.swing.JButton();
        edit_bt = new javax.swing.JButton();
        Screenshot = new javax.swing.JButton();
        filter_type = new javax.swing.JComboBox<>();
        filter_sponsor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data of Users");

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Users Data");

        jScrollPane1.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Search");

        search.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        print_bt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        print_bt.setText("print");
        print_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_btActionPerformed(evt);
            }
        });

        edit_bt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        edit_bt.setText("Edit");
        edit_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_btActionPerformed(evt);
            }
        });

        Screenshot.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Screenshot.setText("Screenshot");
        Screenshot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScreenshotActionPerformed(evt);
            }
        });

        filter_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_typeActionPerformed(evt);
            }
        });

        filter_sponsor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_sponsorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Screenshot, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addGap(311, 311, 311))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edit_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(print_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(filter_type, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(filter_sponsor, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edit_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print_bt, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(Screenshot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filter_type)
                    .addComponent(filter_sponsor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        try
        {
            String data = search.getText();
            if(data.equals(""))
            {
                ResultSet rs = db.select_query("select ID,name,status,type,sponsor,AFF from userdata");
                Userdata = new JTable(buildTableModel(rs));
                set_style();
            }//if search field is empty
            else
            {
                ResultSet rs = db.select_query("select ID,name,status,type,sponsor,AFF from userdata where name LIKE '"+data+"%' or ID LIKE '"+data+"%'");
                Userdata = new JTable(buildTableModel(rs));
                set_style();
            }//if search not emptu
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_searchKeyReleased

    private void print_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_btActionPerformed
        // TODO add your handling code here:
        try{
            /*if(Userdata.getSelectedRowCount()>1)
            {
                JOptionPane.showMessageDialog(null,"choose only one user");
            }
            else
            {
            int rownum = Userdata.getSelectedRow();
            int id = Integer.parseInt(Userdata.getValueAt(rownum, 0).toString());
            String name = Userdata.getValueAt(rownum, 1).toString();
            hide.setText(name+"\n");
            hide.append(Integer.toString(id));
            
            MessageFormat header = new MessageFormat(name);
            MessageFormat footer = new MessageFormat("");
              JTable printer_temp = new JTable();  
         PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
         set.add(OrientationRequested.PORTRAIT);
         printer_temp.print(JTable.PrintMode.FIT_WIDTH, header, footer, false, set, false);
         JOptionPane.showMessageDialog(null, "\n" + "JTable was Successfully "
                + "\n" + "Printed on your Default Printer");
            }//only one row */
         Directprint lt = new Directprint();
        lt.printString("If this text gets printed, it will have worked! ;D");
        
        }catch(Exception e ){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_print_btActionPerformed
    //*******************************************
    
    
    //************************8
    private void edit_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_btActionPerformed
        // TODO add your handling code here:
        try{
             if(Userdata.getSelectedRowCount()>1)
            {
                JOptionPane.showMessageDialog(null,"choose only one user");
            }
            else
            {
            int rownum = Userdata.getSelectedRow();
           int id = Integer.parseInt(Userdata.getValueAt(rownum, 0).toString());
            String name = Userdata.getValueAt(rownum, 1).toString();
            Ubdate_data s = new Ubdate_data(name, id);
            s.setVisible(true); 
            dispose();
            } //end else
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_edit_btActionPerformed

    private void ScreenshotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScreenshotActionPerformed
        // TODO add your handling code here:
      
        try {
             Webcam webcam = Webcam.getDefault();
            webcam.open();
            ImageIO.write(webcam.getImage(), "PNG", new File("D:\\hello-world.png"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_ScreenshotActionPerformed

    private void filter_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_typeActionPerformed
       
        filtering();
    }//GEN-LAST:event_filter_typeActionPerformed
    public void filtering(){ // this func filtering data according to 2 combobox sponsor and type
         // TODO add your handling code here:
        try 
        {
            String type = filter_type.getSelectedItem().toString();
            String spon = filter_sponsor.getSelectedItem().toString();
            ResultSet rs;
            if(type.equals("Show all") && spon.equals("Show all"))
            {
                 rs = db.select_query("select ID,name,status,type,sponsor,AFF from userdata");
                 
            }//if search field is empty
            else if(!type.equals("Show all") && spon.equals("Show all")){
                 rs = db.select_query("select ID,name,status,type,sponsor,AFF from userdata where type='"+type+"'");
                 
            }
            else if(type.equals("Show all") && !spon.equals("Show all")){
                 rs = db.select_query("select ID,name,status,type,sponsor,AFF from userdata where sponsor='"+spon+"'");
                 
            }
            else
            {
                rs = db.select_query("select ID,name,status,type,sponsor,AFF from userdata where type='"+type+"' && sponsor ='"+spon+"'");
                
            }//if search not emptu
            
            Userdata = new JTable(buildTableModel(rs));
                set_style();
        }
        catch(Exception e)
        {
          // JOptionPane.showMessageDialog(null,e);
        }
    }
    private void filter_sponsorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_sponsorActionPerformed
        filtering();
    }//GEN-LAST:event_filter_sponsorActionPerformed
    public void display_data()
    {
       
        ResultSet rs = db.select_query("select ID,name,status,type,sponsor,AFF from userdata");
        Userdata = new JTable(buildTableModel(rs));
        set_style();
        filter_type();
        filter_sponsor();
    }
    // this func for display choices in the choice menue and 
    public void filter_type(){
         Database db2 = new Database();
           try
        {
                ResultSet ts = db2.select_query("select DISTINCT type from userdata");
                filter_type.addItem("Show all");
                while(ts.next()){
                    filter_type.addItem(ts.getString(1));
                }
               db2.close_db();
        }
            catch(Exception e){
                    JOptionPane.showMessageDialog(null,"filter type ");
                    }
        
        
    }
    public void filter_sponsor(){
        Database db3 = new Database();
        try
        {
                ResultSet rs = db3.select_query("select DISTINCT sponsor from userdata");
                filter_sponsor.addItem("Show all");
                while(rs.next()){
                    filter_sponsor.addItem(rs.getString(1));
                }
                db3.close_db();
        }
            catch(Exception e){
                    JOptionPane.showMessageDialog(null,"filter sponsor");
                    }
    }
    public void set_style()
    {
        jScrollPane1.setViewportView(Userdata);
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=0 ; i<6 ; i++)
        {
            Userdata.getColumnModel().getColumn(i).setMinWidth(120);
             Userdata.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        Userdata.setFont((new Font("Arial Unicode MS", 0, 25)));
        Userdata.setRowHeight(40);
        Userdata.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 30));
        jScrollPane1.getViewport().setBackground(Color.BLUE);
    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
       {
           Vector<String> columnNames = null;
           Vector<Vector<Object>> data = null;
           try
           {
    ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
             columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
             data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }
                data.add(vector);
            }
                   }
                   catch(Exception e)
                   {

                   }
            return new DefaultTableModel(data, columnNames);

        }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Data().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Screenshot;
    private javax.swing.JButton edit_bt;
    private javax.swing.JComboBox<String> filter_sponsor;
    private javax.swing.JComboBox<String> filter_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton print_bt;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables

    
}

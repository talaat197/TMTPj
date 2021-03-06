/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Talaat
 */
public class CertSetting extends javax.swing.JFrame {

    /**
     * Creates new form CertSetting
     */
    int x_dim=0;
    int y_dim=0;
    public CertSetting() {
        initComponents();
        jTextArea2.setPreferredSize(new Dimension((int)11.96*76,(int)8.27*76));
        jTextArea2.setMaximumSize(new Dimension((int)11.96*76,(int)8.27*76));
        jTextArea2.setMinimumSize(new Dimension((int)11.96*76,(int)8.27*76));
        format.add("LANDSCAPE");
        format.add("PORTARATE");
        font_size.setText(Integer.toString(Directprint.certificate_font_size) );
        NX.setText(Integer.toString(Directprint.Cname_coordinate[0]) );
        NY.setText(Integer.toString(Directprint.Cname_coordinate[1]) );
        cme_x.setText(Integer.toString(Directprint.CME_x ));
        cme_y.setText(Integer.toString(Directprint.CME_y ));
        cme_font_size.setText(Integer.toString(Directprint.CME_font_size ));
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        NXKeyReleased(null);
        NYKeyReleased(null);
        /********************************************************************************/
        float DPI =java.awt.Toolkit.getDefaultToolkit().getScreenResolution();
        float width=(297 * DPI)/25;
        float Height = (210 * DPI)/25;
        
        jTextArea2.setMinimumSize(new Dimension((int)width, (int)Height));
        jTextArea2.setMaximumSize(new Dimension((int)width, (int)Height));
        jTextArea2.setPreferredSize(new Dimension((int)width, (int)Height));
        /********************************************************************************/
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Done = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        format = new java.awt.Choice();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NX = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        NY = new javax.swing.JTextField();
        font_size = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cme_x = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cme_y = new javax.swing.JTextField();
        cme_font_size = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Certificate Setting");
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setForeground(java.awt.Color.white);

        jScrollPane1.setBackground(java.awt.Color.black);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(102, 102, 102));
        jTextArea1.setRows(5);
        jTextArea1.setText("Increase X lead to right\nIncrease Y lead to down");
        jScrollPane1.setViewportView(jTextArea1);

        Done.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Done.setText("Done!");
        Done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoneActionPerformed(evt);
            }
        });

        jScrollPane2.setMaximumSize(new java.awt.Dimension(404, 149));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(404, 149));

        jTextArea2.setBackground(new java.awt.Color(153, 153, 153));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setText("Test");
        jTextArea2.setMaximumSize(new java.awt.Dimension(404, 149));
        jTextArea2.setMinimumSize(new java.awt.Dimension(404, 149));
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Print Test");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Certificate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)))); // NOI18N

        format.setBackground(java.awt.Color.white);
        format.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Format");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("X (cm)");

        NX.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NX.setText("150");
        NX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NXActionPerformed(evt);
            }
        });
        NX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NXKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Y (cm)");

        NY.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NY.setText("200");
        NY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NYKeyReleased(evt);
            }
        });

        font_size.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        font_size.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        font_size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                font_sizeActionPerformed(evt);
            }
        });
        font_size.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                font_sizeKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Font Size (cm)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(format, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(font_size, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NX, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NY, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(format, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(font_size, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NX, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(NY, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "CME", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("X (cm)");

        cme_x.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cme_x.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cme_x.setText("150");
        cme_x.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cme_xActionPerformed(evt);
            }
        });
        cme_x.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cme_xKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Y (cm)");

        cme_y.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cme_y.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cme_y.setText("200");
        cme_y.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cme_yKeyReleased(evt);
            }
        });

        cme_font_size.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cme_font_size.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cme_font_size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cme_font_sizeActionPerformed(evt);
            }
        });
        cme_font_size.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cme_font_sizeKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Font Size (px)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cme_font_size, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cme_x, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cme_y, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cme_font_size, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cme_x, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cme_y, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(Done, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(Done))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void font_sizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_font_sizeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_font_sizeKeyReleased

    private void font_sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_font_sizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_font_sizeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Directprint lt = new Directprint(1); // Name tag
        lt.printString("Test");
    }//GEN-LAST:event_jButton1ActionPerformed
    public void add_certificate_settings() {
        String certificateX = NX.getText();
        String certificateY = NY.getText();
        String certificateFormat = (format.getSelectedIndex() == 0) ? "true" : "false";
        File f = new File("settings.txt");
        try {
            
            BufferedReader br = new BufferedReader(new FileReader(f));
            String newline = null;
            ArrayList<String> all_settings = new ArrayList<>();
            while ((newline = br.readLine()) != null) {
                String splits[] = newline.split("=");
                all_settings.add(splits[1].replace(" ", ""));
            }
            PrintWriter pr = new PrintWriter(f);
            pr.println("Name Tag Cordinate x=" +Directprint.Nname_coordinate[0]);
            pr.println("Name Tag Cordinate y=" +Directprint.Nname_coordinate[1]);
            pr.println("barcode Corrdinate X=" +Directprint.barcode_coordinate[0]);
            pr.println("barcode corrdinate Y=" +Directprint.barcode_coordinate[1]);
            pr.println("NameLengthThenBreak=" +Directprint.newline);
            pr.println("paperWidth=" +Directprint.paper_width);
            pr.println("paperHeight=" +Directprint.paper_height);
            pr.println("Name tag land_port=" +Directprint.Nland_port);
            pr.println("Certificate land_port=" +Directprint.Cland_port);
            pr.println("certificateX=" +Directprint.Cname_coordinate[0]);
            pr.println("certificateY=" +Directprint.Cname_coordinate[1]);
            pr.println("name tag font=" +Directprint.nametag_font);
            pr.println("barcode width =" +Directprint.barcode_width);
            pr.println("barcod height =" +Directprint.barcode_height);
            pr.println("cartificate font =" +Directprint.certificate_font_size);
            pr.println("CME font =" +Directprint.CME_font_size);
            pr.println("CME x =" +Directprint.CME_x);
            pr.println("CME y =" +Directprint.CME_y);
            pr.close();
        } catch (Exception e) {
            f.setExecutable(true,false);
            f.setReadable(true,false);
            f.setWritable(true,false);
            
            alert_frame alert = new alert_frame("Settings file not found");
            alert.setVisible(true);
        }
    }
//-------------------------------------------------------------
    private void DoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoneActionPerformed
        // TODO add your handling code here:
        try{
            int x = Integer.parseInt(NX.getText());
            int y = Integer.parseInt(NY.getText());
            Directprint.Cname_coordinate[0]=x;
            Directprint.Cname_coordinate[1]=y;
            Directprint.certificate_font_size = Integer.parseInt(font_size.getText());
            Directprint.CME_x = Integer.parseInt(cme_x.getText());
            Directprint.CME_y = Integer.parseInt(cme_y.getText());
            Directprint.CME_font_size = Integer.parseInt(cme_font_size.getText());
            x = format.getSelectedIndex();
            if(x == 0) Directprint.Cland_port=true;
            else if(x == 1) Directprint.Cland_port=false;
            add_certificate_settings();
            correct_frame cf = new correct_frame("Done Saving");
            cf.setVisible(true);
        }
        catch(Exception e){JOptionPane.showMessageDialog(null,"Enter Numbers!");}
    }//GEN-LAST:event_DoneActionPerformed

    private void NYKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NYKeyReleased
        // TODO add your handling code here:
        try
        {
            y_dim = Integer.parseInt(NY.getText());
            //empty border type -> top ->left ->down ->right
            jTextArea2.setBorder(new EmptyBorder(y_dim,x_dim,0,0));
            jTextArea2.setText("Test");
        }
        catch(Exception e)
        {
            y_dim=0;
            NY.setText("");
        }
    }//GEN-LAST:event_NYKeyReleased

    private void NXKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NXKeyReleased
        // TODO add your handling code here:
        try
        {
            x_dim = Integer.parseInt(NX.getText());
            //empty border type -> top ->left ->down ->right
            jTextArea2.setBorder(new EmptyBorder(y_dim,x_dim,0,0));
            jTextArea2.setText("Test");
        }
        catch(Exception e)
        {
            x_dim=0;
            NX.setText("");
        }
    }//GEN-LAST:event_NXKeyReleased

    private void NXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NXActionPerformed

    private void cme_xActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cme_xActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_xActionPerformed

    private void cme_xKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cme_xKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_xKeyReleased

    private void cme_yKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cme_yKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_yKeyReleased

    private void cme_font_sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cme_font_sizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_font_sizeActionPerformed

    private void cme_font_sizeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cme_font_sizeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_font_sizeKeyReleased

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
            java.util.logging.Logger.getLogger(CertSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CertSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CertSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CertSetting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CertSetting().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Done;
    private javax.swing.JTextField NX;
    private javax.swing.JTextField NY;
    private javax.swing.JTextField cme_font_size;
    private javax.swing.JTextField cme_x;
    private javax.swing.JTextField cme_y;
    private javax.swing.JTextField font_size;
    private java.awt.Choice format;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}

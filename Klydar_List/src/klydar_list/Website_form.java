/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Talaat
 */
public class Website_form extends javax.swing.JFrame {

    JFXPanel fxPanel;
    WebView wv;
    JFrame frame;
    public int come_from = 0;
    String url = "http://think-ds.com/cm/";

    /**
     * Creates new form Website_form
     */
    public Website_form(String url,int choice) {
        initComponents();
        come_from = choice;
        this.url = url;
        //display();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void display() {
        //create JavaFX scene
        fxPanel = new JFXPanel();

        com.sun.javafx.application.PlatformImpl.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    wv = new WebView();
                    wv.getEngine().load(url);
                    fxPanel.setScene(new Scene(wv, 1000, 750));
                    JScrollPane panel = new JScrollPane(fxPanel);
                    panel.setSize(800, 800);
                    frame = new JFrame("Browser");
                    frame.setSize(800, 800);
                    frame.setMinimumSize(new Dimension(1000, 1000));
                    frame.setPreferredSize(new Dimension(1000, 1000));
                    frame.setLocationRelativeTo(null);
                    frame.add(panel);
                    if (come_from == 0) {//COME FROM HALL PERSON
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } else {//COME FROM THE SYSTEM IT SELF
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    }
                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
                    setLocationRelativeTo(null);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

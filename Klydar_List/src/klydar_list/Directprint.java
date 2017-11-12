/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import com.onbarcode.barcode.Code39;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JOptionPane;

/**
 *
 * @author Talaat
 */
public class Directprint implements Printable {
 
    private PrintService[] printService;
    private String text;
    private BufferedImage bar_image;
    private int nametag_cirtificate; // 0 for nametag 1 for the other
    //settings attribute
    public static boolean Nland_port = true;  // Name tag Landescape or portairat
    public static boolean Cland_port = true;  // Critificate '''''''''''''''
    //name tag coordination
    public static int[] barcode_coordinate = {100,60};  // x,y corrdination 
    public static int[] Nname_coordinate   = {150,50}; // name x,y
    //cirtificate coordination
    public static int[] Cname_coordinate = {150,50};
    
    public static int Cname_x   = 200;
    public Directprint(int who) { //specify u want to print nametag or cirtification
        nametag_cirtificate=who;
        this.printService = PrinterJob.lookupPrintServices();
        
    }
 
    
 
    public void printString(String input) {
 
        this.text = input;
         
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new PageRanges(1, 1));
        aset.add(new Copies(1));
        if(nametag_cirtificate == 0){
            if(Nland_port == true){
                aset.add(OrientationRequested.LANDSCAPE);
            }
            else aset.add(OrientationRequested.PORTRAIT);
        }//end if
        else if(nametag_cirtificate == 1){
            if(Cland_port == true) aset.add(OrientationRequested.LANDSCAPE);
            else aset.add(OrientationRequested.PORTRAIT);
        }//end if
        
        
         
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        
        try {
            System.out.println( this.printService.length);
           
            printJob.setPrintService(printService[0]);
            //index of installed printers on you system
            //not sure if default-printer is always '0'
            printJob.print(aset);
            
        } catch (PrinterException err) {
            JOptionPane.showMessageDialog(null,err+"\n problem with printer");
        }
    }
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        if(nametag_cirtificate ==0){ // he want nametag then he need barcode
            g.drawImage(bar_image,barcode_coordinate[0],barcode_coordinate[1],null);
            g.drawString(String.valueOf(this.text), Nname_coordinate[0],Nname_coordinate[1] );  // increase x = go to the right increase y = go down
        }
        else if(nametag_cirtificate == 1){
             g.drawString(String.valueOf(this.text),Cname_coordinate[0],Cname_coordinate[1] );  // increase x = go to the right increase y = go down
        }
        //JOptionPane.showMessageDialog(null,"Done printing");
        return PAGE_EXISTS;
    }
    public void barcode_generate(String bar){
         try
        {
                
                Code39 barcode = new Code39();
                barcode.setData(bar);
                barcode.setX(2);
                barcode.setY(60);
                barcode.setBarcodeWidth(150);
                barcode.setBarcodeHeight(80);
                barcode.setLeftMargin(0);
                barcode.setRightMargin(0);
                bar_image = barcode.drawBarcode(); 
        }
                
       
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

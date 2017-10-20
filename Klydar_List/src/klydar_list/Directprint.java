/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PageRanges;

/**
 *
 * @author Talaat
 */
public class Directprint implements Printable {
 
    private PrintService[] printService;
    private String text;
 
    public Directprint() {
        
        this.printService = PrinterJob.lookupPrintServices();
        
    }
 
    
 
    public void printString(String input) {
 
        this.text = input;
         
        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new PageRanges(1, 1));
        aset.add(new Copies(1));
         
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        
        try {
            System.out.println( this.printService.length);
           
            printJob.setPrintService(printService[0]);
            //index of installed printers on you system
            //not sure if default-printer is always '0'
            printJob.print(aset);
            System.out.println("here");
        } catch (PrinterException err) {
            System.err.println(err);
        }
    }
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g.drawString(String.valueOf(this.text), 14, 14);
        
        return PAGE_EXISTS;
    }
}

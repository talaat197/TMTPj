/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

import com.onbarcode.barcode.Code39;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
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

    private PrintService printService;
    private String text;
    private String CME = ""; // credit hours
    public static int CME_font_size;
    public static int CME_x;
    public static int CME_y;
    private BufferedImage bar_image;
    private int nametag_cirtificate; // 0 for nametag 1 for the other
    //settings attribute
    public static boolean Nland_port = false;  // Name tag Landescape or portairat
    public static boolean Cland_port = true;  // Critificate '''''''''''''''
    //name tag coordination
    public static int[] barcode_coordinate = {120, 240};  // x,y corrdination 
    public static int[] Nname_coordinate = {120, 210}; // name x,y
    //cirtificate coordination
    public static int[] Cname_coordinate = {650, 250};
    public static int temp;
    public static int Cname_x = 200;
    //number of charcater before take new line in nametag
    public static int newline = 25;
    public static double paper_width = 8.27; // 4.1 inch
    public static double paper_height = 11.69; // 5.8 inch   whic is a6 size
    public static int nametag_font = 24;
    public static int barcode_width = 2;
    public static int barcode_height = 40;
    public static int certificate_font_size = 25;

    public Directprint(int who) { //specify u want to print nametag or cirtification
        nametag_cirtificate = who; //0 nametag 
        this.printService = PrintServiceLookup.lookupDefaultPrintService();

    }

    public void set_CME(String cme) {
        CME = cme;
    }

    public void printString(String input) {

        this.text = input;

        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
        aset.add(new PageRanges(1, 1));
        aset.add(new Copies(1));
        PrinterJob printJob = PrinterJob.getPrinterJob();

        if (nametag_cirtificate == 0) {
            if (Nland_port == true) {
                aset.add(OrientationRequested.LANDSCAPE);
            } else {
                aset.add(OrientationRequested.PORTRAIT);
            }
            //set page size in nametag
            Paper paper = new Paper();
            paper.setSize(paper_width * 72, paper_height * 72);
            PageFormat page = new PageFormat();
            page.setPaper(paper);

            printJob.setPrintable(this, page);
        }//end if
        else if (nametag_cirtificate == 1) {
            if (Cland_port == true) {
                aset.add(OrientationRequested.LANDSCAPE);
            } else {
                aset.add(OrientationRequested.PORTRAIT);
            }

            printJob.setPrintable(this);

        }//end if

        try {
            /* System.out.println(this.printService.length);
           for(int i=0;i<printService.length;i++){
               System.out.println(printService[i]);
           }*/
            printJob.setPrintService(printService);
            //index of installed printers on you system
            //not sure if default-printer is always '0'
            printJob.print(aset);

        } catch (PrinterException err) {
            //JOptionPane.showMessageDialog(null, err + "\n problem with printer");
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        temp = barcode_coordinate[1];
        Graphics2D g2 = (Graphics2D) g;

        g2.translate(pf.getImageableX(), pf.getImageableY());
        if (nametag_cirtificate == 0) { // he want nametag then he need barcode
            Rectangle rect = new Rectangle(Nname_coordinate[0], Nname_coordinate[1], 200, 30);

            Font myFont = new Font("Impact", Font.PLAIN, nametag_font);
            //g2.setFont(myFont);
            drawCenteredString(g2, text, rect, myFont, 23);

            g.drawImage(bar_image, barcode_coordinate[0], barcode_coordinate[1], null);
            barcode_coordinate[1] = temp;
            //g.drawString(String.valueOf(this.text), Nname_coordinate[0],Nname_coordinate[1] );  // increase x = go to the right increase y = go down

        } else if (nametag_cirtificate == 1) {
            Rectangle rect = new Rectangle(0, Cname_coordinate[1], Cname_coordinate[0], 50);

            Font myFont = new Font("Serif", Font.BOLD, certificate_font_size);
            drawCenteredString(g, this.text, rect, myFont, 10);
            myFont = new Font("Serif", Font.BOLD,CME_font_size);
            g2.setFont(myFont); // set font of the cme nuumber
            
            g2.drawString(CME, CME_x, CME_y);  // write CME 

        }
        //JOptionPane.showMessageDialog(null,"Done printing");
        return PAGE_EXISTS;
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, int plusy) {
        // Get the FontMetrics
        temp = barcode_coordinate[1];
        if (nametag_cirtificate == 0) { // he want nametag then he need barcode
            FontMetrics metrics = g.getFontMetrics(font);
            // Determine the X coordinate for the text
            int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
            // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
            int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
            // Set the font
            g.setFont(font);
            // Draw the String
            int newline_no = newline;
            if (text.length() > newline_no)//25
            {

                barcode_coordinate[1] += 25; // move down to make space for the newline
                String text_sub1 = text.substring(0, newline_no);
                if (text_sub1.charAt(newline_no - 1) == ' ') {
                    x = rect.x + (rect.width - metrics.stringWidth(text_sub1)) / 2;
                    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                    y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString(text_sub1, x, y);
                    String text_sub2 = text.substring(newline_no, text.length());
                    x = rect.x + (rect.width - metrics.stringWidth(text_sub2)) / 2;
                    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                    y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString(text_sub2, x, y + plusy);
                }//means space end of first file
                else {
                    String text_sub2 = "";
                    for (int i = newline_no - 1; i >= 0; i--) {
                        if (text_sub1.charAt(i) == ' ') {
                            text_sub1 = text.substring(0, i);
                            text_sub2 = text.substring(i + 1, text.length());
                            /*if(text_sub2.length()>19){
                            drawCenteredString(g, text_sub2, rect, font,20);
                        }*/
                            break;
                        }
                    }//end loop detect space
                    x = rect.x + (rect.width - metrics.stringWidth(text_sub1)) / 2;
                    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                    y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString(text_sub1, x, y);
                    x = rect.x + (rect.width - metrics.stringWidth(text_sub2)) / 2;
                    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                    y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
                    g.drawString(text_sub2, x, y + plusy);
                }//end Not found space
            }//end if 
            else {
                g.drawString(text, x, y);
            }//end else
        } else if (nametag_cirtificate == 1) {
            FontMetrics metrics = g.getFontMetrics(font);
            // Determine the X coordinate for the text
            int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
            // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
            int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
            // Set the font
            g.setFont(font);
            // Draw the String
            g.drawString(text, x, y);
        }

    }

    public void barcode_generate(String bar) {
        try {

            Code39 barcode = new Code39();
            barcode.setData(bar);
            barcode.setX(barcode_width);
            barcode.setY(barcode_height);
            barcode.setBarcodeWidth(100);
            barcode.setBarcodeHeight(50);
            barcode.setLeftMargin(0);
            barcode.setRightMargin(0);
            bar_image = barcode.drawBarcode();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klydar_list;

//import com.github.sarxos.webcam.Webcam;
import User.Admin;
import User.Check_in_out;
import User.Helper;
import User.Login;
import com.mysql.jdbc.log.Log;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.Highlighter;
import static klydar_list.Klydar_List.waiting;
import klydar_list.report.report_gui;
import User.Login;
import javax.swing.table.TableRowSorter;
import java.util.List;

/**
 *
 * @author Talaat
 */
public class Data extends javax.swing.JFrame {

    JTable Userdata;
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
    public static Database db;
    ResultSet global_data;
    private byte first_time = 0;
    static Color white_blue = new Color(26, 198, 255);
    public Thread refresh_th;
    public static String filter1 = "sponsor";
    public static String filter2 = "type";
    public static String UType;
    public static String system_password = "0000";
    public refresh_thread ref_obj;
    public Helper obj;
    public Thread th_helper;
    static ArrayList<String> search_items;
    //Color white_blue = new Color(129, 216, 250);

    /**
     * Creates new form Data
     */
    public Data() {

    }

    public Data(Thread refresh, String User_type, refresh_thread ref_obj) {
        try {
            //get configuration from file
            obj = new Helper();
            get_Print_settings();
            wait_thread.wf.setVisible(true);
            this.ref_obj = ref_obj;
            UType = User_type;

            refresh_th = refresh;
            set_theme();
            db = new Database();
            set_GlobalSettings();
            initComponents();
            display_data();
            users_no.setForeground(white_blue);
            search.requestFocus();
            setLocationRelativeTo(null);
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
            display_connection();
            
            username_label.setText(Login.Uname);
            for (int i = 0; i < search_items.size(); i++) {
                sort.addItem(search_items.get(i));
            }
            wait_thread.wf.setVisible(false);
            users_no.setText(Integer.toString(Userdata.getRowCount()));
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, e);
            wait_thread.wf.setVisible(false);
        }
        if (!UType.equals("admin")) {//hide admin menues
            AdvSett.setVisible(false);
            admin_SettingBt1.setVisible(false);
            att_menue.setVisible(false);
            report_menue.setVisible(false);
            url_menue.setVisible(false);
        }
    }//END constructor

    public void get_Print_settings() {
        try {
            File f = new File("settings.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String newline = null;
            ArrayList<String> all_settings = new ArrayList<>();
            while ((newline = br.readLine()) != null) {
                String splits[] = newline.split("=");
                all_settings.add(splits[1].replace(" ", ""));
            }
            Directprint.Nname_coordinate[0] = Integer.parseInt(all_settings.get(0));
            Directprint.Nname_coordinate[1] = Integer.parseInt(all_settings.get(1));
            Directprint.barcode_coordinate[0] = Integer.parseInt(all_settings.get(2));
            Directprint.barcode_coordinate[1] = Integer.parseInt(all_settings.get(3));
            Directprint.newline = Integer.parseInt(all_settings.get(4));
            Directprint.paper_width = Double.parseDouble(all_settings.get(5));
            Directprint.paper_height = Double.parseDouble(all_settings.get(6));
            Directprint.Nland_port = Boolean.valueOf(all_settings.get(7));
            Directprint.Cland_port = Boolean.valueOf(all_settings.get(8));
            Directprint.Cname_coordinate[0] = Integer.parseInt(all_settings.get(9));
            Directprint.Cname_coordinate[1] = Integer.parseInt(all_settings.get(10));
            Directprint.nametag_font = Integer.parseInt(all_settings.get(11));
            Directprint.barcode_width = Integer.parseInt(all_settings.get(12));
            Directprint.barcode_height = Integer.parseInt(all_settings.get(13));
            Directprint.certificate_font_size = Integer.parseInt(all_settings.get(14));
            Directprint.CME_font_size = Integer.parseInt(all_settings.get(15));
            Directprint.CME_x = Integer.parseInt(all_settings.get(16));
            Directprint.CME_y = Integer.parseInt(all_settings.get(17));
        } catch (Exception e){
            alert_frame obj = new alert_frame("Settings file not found");
            obj.setVisible(true);
        }

    }

    public void display_connection() {

        if (db.connection_type() == 'C') {
            jMenu5.setText("Cloud");
        } else if (db.connection_type() == 'N') {
            jMenu5.setText("Network");
        } else if (db.connection_type() == 'L') {
            jMenu5.setText("Local");
        }
    }

    public void set_connection_type(String cn) {
        jMenu5.setText(cn);
    }

    public static void set_GlobalSettings() {
        try {
            ResultSet ad_s = db.select_query("select * from " + DatabaseConstants.global_stt_table + " where id_setting='1'");
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
                filter1 = ad_s.getString("filter1");
                filter2 = ad_s.getString("filter2");
                system_password = ad_s.getString("system_password");
                String all_items = ad_s.getString("search");
                search_items
                        = new ArrayList<String>(Arrays.asList(all_items.split(",")));
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
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void set_theme() {
        UIManager.put("Menu.selectionBackground",
                new javax.swing.plaf.ColorUIResource(Color.WHITE));
        UIManager.put("Menu.selectionForeground",
                new javax.swing.plaf.ColorUIResource(Color.BLACK));
        UIManager.put("MenuItem.selectionBackground",
                new javax.swing.plaf.ColorUIResource(Color.WHITE));
        UIManager.put("MenuItem.selectionForeground",
                new javax.swing.plaf.ColorUIResource(Color.BLACK));
        UIManager.put("ComboBox.selectionBackground",
                new javax.swing.plaf.ColorUIResource(new Color(220, 220, 220)));
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
        search = new javax.swing.JTextField();
        print_bt = new javax.swing.JButton();
        filter = new javax.swing.JComboBox<>();
        secondFilter = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        Crtificate = new javax.swing.JButton();
        print_cert = new javax.swing.JTextField();
        sort = new javax.swing.JComboBox<>();
        printName = new javax.swing.JTextField();
        filter3 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Tagsettings = new javax.swing.JMenu();
        AdvSett = new javax.swing.JMenu();
        admin_SettingBt1 = new javax.swing.JMenu();
        Switch = new javax.swing.JMenu();
        online = new javax.swing.JMenuItem();
        offline = new javax.swing.JMenuItem();
        lan = new javax.swing.JMenuItem();
        certificatesettings = new javax.swing.JMenu();
        certificatesettings1 = new javax.swing.JMenu();
        att_menue = new javax.swing.JMenu();
        url_menue = new javax.swing.JMenu();
        report_menue = new javax.swing.JMenu();
        cme_menue = new javax.swing.JMenu();
        refresh = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        users_no = new javax.swing.JMenu();
        empty = new javax.swing.JMenu();
        logout = new javax.swing.JMenu();
        username_label = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Users Data");

        jPanel1.setBackground(new java.awt.Color(243, 243, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 20));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setToolTipText("");

        search.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });
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

        print_bt.setBackground(new java.awt.Color(26, 198, 255));
        print_bt.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        print_bt.setForeground(new java.awt.Color(255, 255, 255));
        print_bt.setText("Name Tag");
        print_bt.setBorder(null);
        print_bt.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                print_btMouseMoved(evt);
            }
        });
        print_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_btActionPerformed(evt);
            }
        });

        filter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filter.setForeground(new java.awt.Color(51, 51, 51));
        filter.setMaximumRowCount(1000);
        filter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });
        filter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                filterFocusGained(evt);
            }
        });

        secondFilter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        secondFilter.setForeground(new java.awt.Color(51, 51, 51));
        secondFilter.setMaximumRowCount(1000);
        secondFilter.setAutoscrolls(true);
        secondFilter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sponsor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        secondFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondFilterActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(26, 198, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Reset");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Crtificate.setBackground(new java.awt.Color(26, 198, 255));
        Crtificate.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        Crtificate.setForeground(new java.awt.Color(255, 255, 255));
        Crtificate.setText("Certificate");
        Crtificate.setBorder(null);
        Crtificate.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                CrtificateMouseMoved(evt);
            }
        });
        Crtificate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrtificateActionPerformed(evt);
            }
        });

        print_cert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        print_cert.setForeground(new java.awt.Color(102, 102, 102));
        print_cert.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        print_cert.setText("Certificate ID");
        print_cert.setToolTipText("Print Certificate");
        print_cert.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                print_certFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                print_certFocusLost(evt);
            }
        });
        print_cert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                print_certMouseReleased(evt);
            }
        });
        print_cert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_certActionPerformed(evt);
            }
        });
        print_cert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                print_certKeyReleased(evt);
            }
        });

        sort.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        sort.setForeground(new java.awt.Color(51, 51, 51));
        sort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));

        printName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        printName.setForeground(new java.awt.Color(102, 102, 102));
        printName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        printName.setText("Certificate Name");
        printName.setToolTipText("Print Certificate");
        printName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                printNameMouseReleased(evt);
            }
        });
        printName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printNameActionPerformed(evt);
            }
        });
        printName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                printNameFocusLost(evt);
            }
        });
        printName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                printNameKeyReleased(evt);
            }
        });

        filter3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        filter3.setForeground(new java.awt.Color(51, 51, 51));
        filter3.setMaximumRowCount(1000);
        filter3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ON", "OFF" }));
        filter3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attendees", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 51, 51))); // NOI18N
        filter3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter3ActionPerformed(evt);
            }
        });
        filter3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                filter3FocusGained(evt);
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
                        .addComponent(sort, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search)
                        .addGap(21, 21, 21)
                        .addComponent(Crtificate, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(print_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(print_cert, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(printName, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addComponent(filter3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(secondFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sort, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Crtificate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(print_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(secondFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(print_cert, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        jMenuBar1.setBackground(new java.awt.Color(41, 58, 74));
        jMenuBar1.setForeground(new java.awt.Color(41, 58, 74));
        jMenuBar1.setToolTipText("");

        jMenu1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Add New");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMenu1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenu1MouseMoved(evt);
            }
        });
        jMenu1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMenu1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jMenu1FocusLost(evt);
            }
        });
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenu1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        Tagsettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Tagsettings.setForeground(new java.awt.Color(255, 255, 255));
        Tagsettings.setText("Setting");
        Tagsettings.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        Tagsettings.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TagsettingsMouseMoved(evt);
            }
        });
        Tagsettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TagsettingsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TagsettingsMouseReleased(evt);
            }
        });

        AdvSett.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        AdvSett.setForeground(new java.awt.Color(102, 102, 102));
        AdvSett.setText("Advanced Setting");
        AdvSett.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        AdvSett.setName(""); // NOI18N
        AdvSett.setPreferredSize(new java.awt.Dimension(300, 42));
        AdvSett.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                AdvSettMouseMoved(evt);
            }
        });
        AdvSett.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AdvSettMousePressed(evt);
            }
        });
        Tagsettings.add(AdvSett);

        admin_SettingBt1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        admin_SettingBt1.setForeground(new java.awt.Color(102, 102, 102));
        admin_SettingBt1.setText("Admin");
        admin_SettingBt1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        admin_SettingBt1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                admin_SettingBt1MouseMoved(evt);
            }
        });
        admin_SettingBt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                admin_SettingBt1MousePressed(evt);
            }
        });
        Tagsettings.add(admin_SettingBt1);

        Switch.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        Switch.setForeground(new java.awt.Color(102, 102, 102));
        Switch.setText("Connection");
        Switch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Switch.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                SwitchMouseMoved(evt);
            }
        });
        Switch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SwitchMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                SwitchMouseReleased(evt);
            }
        });

        online.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        online.setForeground(new java.awt.Color(102, 102, 102));
        online.setText("Online");
        online.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                onlineMousePressed(evt);
            }
        });
        online.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onlineActionPerformed(evt);
            }
        });
        Switch.add(online);

        offline.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        offline.setForeground(new java.awt.Color(102, 102, 102));
        offline.setText("Local");
        offline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                offlineMousePressed(evt);
            }
        });
        Switch.add(offline);

        lan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lan.setForeground(new java.awt.Color(102, 102, 102));
        lan.setText("Network");
        lan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lanMousePressed(evt);
            }
        });
        lan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lanActionPerformed(evt);
            }
        });
        Switch.add(lan);

        Tagsettings.add(Switch);

        certificatesettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        certificatesettings.setForeground(new java.awt.Color(102, 102, 102));
        certificatesettings.setText("Certificate ");
        certificatesettings.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        certificatesettings.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                certificatesettingsMouseMoved(evt);
            }
        });
        certificatesettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                certificatesettingsMousePressed(evt);
            }
        });
        Tagsettings.add(certificatesettings);

        certificatesettings1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        certificatesettings1.setForeground(new java.awt.Color(102, 102, 102));
        certificatesettings1.setText("Name Tag");
        certificatesettings1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        certificatesettings1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                certificatesettings1MouseMoved(evt);
            }
        });
        certificatesettings1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                certificatesettings1MousePressed(evt);
            }
        });
        Tagsettings.add(certificatesettings1);

        att_menue.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        att_menue.setForeground(new java.awt.Color(102, 102, 102));
        att_menue.setText("Attendance");
        att_menue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        att_menue.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                att_menueMouseMoved(evt);
            }
        });
        att_menue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                att_menueMousePressed(evt);
            }
        });
        Tagsettings.add(att_menue);

        url_menue.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        url_menue.setForeground(new java.awt.Color(102, 102, 102));
        url_menue.setText("URL");
        url_menue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        url_menue.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                url_menueMouseMoved(evt);
            }
        });
        url_menue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                url_menueMousePressed(evt);
            }
        });
        Tagsettings.add(url_menue);

        report_menue.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        report_menue.setForeground(new java.awt.Color(102, 102, 102));
        report_menue.setText("Reports");
        report_menue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        report_menue.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                report_menueMouseMoved(evt);
            }
        });
        report_menue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                report_menueMousePressed(evt);
            }
        });
        Tagsettings.add(report_menue);

        jMenuBar1.add(Tagsettings);

        cme_menue.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cme_menue.setForeground(new java.awt.Color(255, 255, 255));
        cme_menue.setText("CME");
        cme_menue.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cme_menue.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                cme_menueMouseMoved(evt);
            }
        });
        cme_menue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cme_menueFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cme_menueFocusLost(evt);
            }
        });
        cme_menue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cme_menueMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cme_menueMousePressed(evt);
            }
        });
        jMenuBar1.add(cme_menue);

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
        jMenu5.setForeground(new java.awt.Color(255, 255, 255));
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu5MousePressed(evt);
            }
        });
        jMenu5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenu5MouseMoved(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        users_no.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        users_no.setForeground(new java.awt.Color(255, 255, 255));
        users_no.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jMenuBar1.add(users_no);

        empty.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 600, 10, 20));
        empty.setForeground(new java.awt.Color(102, 102, 102));
        empty.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empty.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuBar1.add(empty);

        logout.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setText("Logout");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        logout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        logout.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        logout.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        logout.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                logoutMouseMoved(evt);
            }
        });
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoutMousePressed(evt);
            }
        });
        jMenuBar1.add(logout);

        username_label.setBorder(null);
        username_label.setForeground(new java.awt.Color(255, 255, 255));
        username_label.setBorderPainted(true);
        username_label.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        username_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username_label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        username_label.setMaximumSize(new java.awt.Dimension(150, 32767));
        username_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                username_labelMousePressed(evt);
            }
        });
        username_label.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                username_labelMouseMoved(evt);
            }
        });
        jMenuBar1.add(username_label);

        setJMenuBar(jMenuBar1);

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

    private void print_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_btActionPerformed
        // TODO add your handling code here:

        try {
            int[] selected_rows = Userdata.getSelectedRows();
            if (Userdata.getSelectedRowCount() >= 1) {
                for (int sel = 0; sel < selected_rows.length; sel++) {
                    int rownum = selected_rows[sel];
                    int id = Integer.parseInt(Userdata.getValueAt(rownum, getColumnIndex(Userdata, "Id")).toString());
                    String name = Userdata.getValueAt(rownum, getColumnIndex(Userdata, "Name")).toString();
                    name = name.toLowerCase();
                    //****************************** change first letter to upper 

                    StringBuilder myName = new StringBuilder(name);
                    for (int i = 0; i < name.length(); i++) {
                        if (i == 0) {
                            myName.setCharAt(i, Character.toUpperCase(name.charAt(i)));
                        } else if (name.charAt(i) == ' ') {
                            for (int k = i; k < name.length(); k++) {
                                if (name.charAt(k) != ' ') {
                                    myName.setCharAt(k, Character.toUpperCase(name.charAt(k)));
                                    k = name.length();
                                }
                            }
                        }
                    }
                    name = myName.toString();
                    //****************************
                    Directprint lt = new Directprint(0); // Name tag 
                    lt.barcode_generate(Integer.toString(id));

                    lt.printString(name);
                    //db.updata_query(DatabaseConstants.update_attend(false,id));
                    VUpdate_Data(DatabaseConstants.update_2tables("no_tag", DatabaseConstants.tag_colm, Integer.toString(id), Login.Uname));

                    correct_frame af2 = new correct_frame("Done Printing");
                    af2.setVisible(true);
                }
            } else {
                alert_frame af = new alert_frame("At Least One User");
                af.setVisible(true);

            }
        } catch (Exception e) {

            alert_frame aler = new alert_frame("Failure Printing");
            aler.setVisible(true);
        }
    }//GEN-LAST:event_print_btActionPerformed

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed

        filtering();

    }//GEN-LAST:event_filterActionPerformed
    public void filtering() { // this func filtering data according to 2 combobox sponsor and type
        // TODO add your handling code here:
        try {

            if (first_time < 2) {
                first_time++;
            } else {
                String type = filter.getSelectedItem().toString();
                String spon = secondFilter.getSelectedItem().toString();
                ResultSet rs;
                if (type.equals("Show all") && spon.equals("Show all")) {
                    global_data = db.select_query(DatabaseConstants.all_data);

                }//if search field is empty
                else if (!type.equals("Show all") && spon.equals("Show all")) {

                    global_data = db.select_query(DatabaseConstants.Selectall_ByValue(Data.filter2, type));
                }//end else if
                else if (type.equals("Show all") && !spon.equals("Show all")) {
                    global_data = db.select_query(DatabaseConstants.Selectall_ByValue(Data.filter1, spon));

                } else {
                    global_data = db.select_query(DatabaseConstants.Selectall_By2value(filter2, filter1, type, spon));

                }//if search not emptu

                Userdata = new JTable(buildTableModel(global_data));
                global_data.beforeFirst();
                set_style();
                users_no.setText(Integer.toString(Userdata.getRowCount()));
            }
        }//else first time
        catch (Exception e) {
            // JOptionPane.showMessageDialog(null,e);
        }

    }
    private void secondFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondFilterActionPerformed
        filtering();
    }//GEN-LAST:event_secondFilterActionPerformed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        // TODO add your handling code here:
        try {
            if (Login.allow_add == 1) {
                AddUser s = new AddUser(this);
                s.setVisible(true);
            } else {
                alert_frame alert = new alert_frame("You don't have privilege to add user");
                alert.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jMenu1MousePressed

    private void refreshMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMousePressed
        // TODO add your handling code here:
        /*filter_sponsor.removeAllItems();
        filter_type.removeAllItems();
        display_data();
        insert_buttons();*/
        refresh_th.interrupt();

    }//GEN-LAST:event_refreshMousePressed

    public void refresh_action() {
        try {
            wait_thread.wf.setVisible(true);
            secondFilter.removeAllItems();
            filter.removeAllItems();
            display_data();
            set_GlobalSettings();
            searchKeyReleased(null);
            is_blocked();
            wait_thread.wf.setVisible(false);
        } catch (Exception e) {
            wait_thread.wf.setVisible(false);
            JOptionPane.showMessageDialog(null, "refresh action " + e);
        }
    }

    public static void close_frames() {
        Window[] window = Window.getWindows();
        for (Window wind : window) {
            wind.dispose();
        }
    }

    public static void return_login() {
        try {
            close_frames();
            //ref_obj.on = false; // stop the thread
            Login obj = new Login();
            obj.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void get_privilege(ResultSet result) {
        try {
            if (result.getInt("addUser") == 1) {
                Login.allow_add = 1;
            } else {
                Login.allow_add = 0;
            }
            if (result.getInt("allow_cme") == 1) {
                Login.allow_print_cme = 1;
            } else {
                Login.allow_print_cme = 0;
            }
            if (result.getInt("allow_cme") == 1) {
                Login.allow_print_cme = 1;
            } else {
                Login.allow_print_cme = 0;
            }
            if (result.getInt("vip") == 1) {
                Advanced_Settings.isVip = true;
            } else {
                Advanced_Settings.isVip = false;
            }
            if (result.getInt("update_users") == 1) {
                Login.allow_update = 1;
            } else {
                Login.allow_update = 0;
            }
            if (result.getInt("validation") == 1) {
                AddUser.valid = true;
            } else {
                AddUser.valid = false;
            }
            UType = result.getString("type");

        } catch (Exception e) {

        }
    }

    public void is_blocked() {
        try {

            ResultSet result = db.select_query("select * from " + DatabaseConstants.user_Settings + " where username ='" + Login.Uname + "'");

            if (result.next()) {
                if (result.getInt("block") == 1) {
                    ref_obj.on = false;
                    create_THhelper(); // create thread to call logout
                    alert_frame alert = new alert_frame("You are blocked");
                    alert.setVisible(true);
                } else {// No block happen update the privilage things
                    get_privilege(result);
                }
            } else {//mean he deleted from the user table
                ref_obj.on = false;
                create_THhelper(); // create thread to call logout
                alert_frame alert = new alert_frame("You are Deleted");
                alert.setVisible(true);
            }//END ELSE
        } catch (Exception e) {
            //System.out.println(e);
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        secondFilter.setSelectedIndex(0);
        filter.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SwitchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SwitchMousePressed
        // TODO add your handling code here:    
        /*db.switch_to_local();
        filter_sponsor.removeAllItems();
        filter_type.removeAllItems();
        display_data();
        insert_buttons();
        if(Database.iscloud==true)
        {
            Switch.setText("ONLINE");
        }
        else
        {
            Switch.setText("OFFLINE");
        }*/
    }//GEN-LAST:event_SwitchMousePressed
    public String get_cme_fromDB(int rownum) {
        String cme = "";
        try {

            String ID = Userdata.getValueAt(rownum, getColumnIndex(Userdata, "ID")).toString();
            String query = "select CME from " + DatabaseConstants.tableName + " where ID = " + ID + "";
            ResultSet result = Data.db.select_query(query);

            cme = result.getString("CME");
        } catch (SQLException ex) {

        }
        return cme;
    }

    public String get_cme_fromDB(String ID) {
        String cme = "";
        try {

            String query = "select CME from " + DatabaseConstants.tableName + " where ID = " + ID + "";
            ResultSet result = Data.db.select_query(query);

            cme = result.getString("CME");
        } catch (SQLException ex) {

        }
        return cme;
    }
    private void CrtificateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrtificateActionPerformed
        // TODO add your handling code here:
        try {
            String cme = "";
            if (Userdata.getSelectedRowCount() >= 1) {
                int[] rows_selected = Userdata.getSelectedRows();
                for (int v = 0; v < rows_selected.length; v++) {
                    int rownum = rows_selected[v];
                    // int id = Integer.parseInt(Userdata.getValueAt(rownum, 0).toString());
                    String name = Userdata.getValueAt(rownum, getColumnIndex(Userdata, "Name")).toString();
                    if (Login.allow_print_cme == 1) {//if your are privilege to print CME with certificate get it

                        int index = getColumnIndex(Userdata, "CME");
                        if (index != -1) {
                            cme = Userdata.getValueAt(rownum, index).toString();
                        } else {
                            cme = get_cme_fromDB(rownum);
                        }

                    }
                    int id = Integer.parseInt(Userdata.getValueAt(rownum, getColumnIndex(Userdata, "Id")).toString());
                    StringBuilder myName = new StringBuilder(name);
                    for (int i = 0; i < name.length(); i++) {
                        if (i == 0) {
                            myName.setCharAt(i, Character.toUpperCase(name.charAt(i)));
                        } else if (name.charAt(i) == ' ') {
                            for (int k = i; k < name.length(); k++) {
                                if (name.charAt(k) != ' ') {
                                    myName.setCharAt(k, Character.toUpperCase(name.charAt(k)));
                                    k = name.length();
                                }
                            }
                        }
                    }
                    name = myName.toString();
                    Directprint lt = new Directprint(1); // certificate
                    lt.set_CME(cme);
                    lt.printString(name);
                    VUpdate_Data(DatabaseConstants.update_2tables("no_certificate", DatabaseConstants.cert_colm, Integer.toString(id), Login.Uname));
                    correct_frame af = new correct_frame("Done Printing");
                    af.setVisible(true);
                }
            } else {
                alert_frame af = new alert_frame("At Least One User");
                af.setVisible(true);
            }
        } catch (Exception e) {
            alert_frame alert = new alert_frame("Failure Printing");
            alert.setVisible(true);

        }
    }//GEN-LAST:event_CrtificateActionPerformed

    private void TagsettingsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TagsettingsMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TagsettingsMousePressed

    private void certificatesettingsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_certificatesettingsMousePressed
        // TODO add your handling code here:
        JPasswordField pwd = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (action < 0 || pwd.getText().equals(""));//enter Exit X do nothing
        else if (pwd.getText().equals(system_password)) {
            CertSetting obj = new CertSetting();
            obj.setVisible(true);
        } else {
            alert_frame warn = new alert_frame("Wrong Password");
            warn.setVisible(true);
        }
    }//GEN-LAST:event_certificatesettingsMousePressed

    private void SwitchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SwitchMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_SwitchMouseReleased

    private void print_certActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_certActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_print_certActionPerformed

    private void print_certMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_certMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_print_certMouseReleased

    private void print_certKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_print_certKeyReleased
        // TODO add your handling code here:

        try {
            String name = "";
            String cme = "";
            String id = print_cert.getText();
            if (id.length() < 4) {
                //No Thing 
            } else {

                if (Advanced_Settings.directcon == false) {
                    name = get_name(id);
                    if (Login.allow_print_cme == 1) {
                        cme = get_cme(id);
                        System.out.println(cme);
                        if (cme.equals("")) {//if he didn't find it in the resultset select it from the db 
                            cme = get_cme_fromDB(id);
                            System.out.println("here");
                        }
                    }
                    global_data.beforeFirst();
                }// end if 
                else {
                    String query = "select name,cma from lists where id=" + id + "";
                    ResultSet rs = db.select_query(query);
                    rs.next();
                    name = rs.getString("name");
                    cme = rs.getString("CME");
                }
                StringBuilder myName = new StringBuilder(name);
                for (int i = 0; i < name.length(); i++) {
                    if (i == 0) {
                        myName.setCharAt(i, Character.toUpperCase(name.charAt(i)));
                    } else if (name.charAt(i) == ' ') {
                        for (int k = i; k < name.length(); k++) {
                            if (name.charAt(k) != ' ') {
                                myName.setCharAt(k, Character.toUpperCase(name.charAt(k)));
                                k = name.length();
                            }
                        }
                    }
                }
                name = myName.toString();
                if (!name.equals("")) {//mean if it found the name
                    Directprint lt = new Directprint(1); // certificate
                    lt.set_CME(cme);
                    lt.printString(name);
                    VUpdate_Data(DatabaseConstants.update_2tables("no_certificate", DatabaseConstants.cert_colm, id, Login.Uname));

                }//end if
                print_cert.setText("");
                print_cert.requestFocus();
            }
        } catch (Exception e) {
            alert_frame alert = new alert_frame("Failure Printing");
            alert.setVisible(true);
        }
    }//GEN-LAST:event_print_certKeyReleased
    //***************************************
    //get name with specific id from the resultset
    public String get_name(String Id) {
        String colum_name1 = "Id";
        String colum_name2 = "name";
        try {
            global_data.beforeFirst();
            while (global_data.next()) {
                //if you found the id u want get his name
                if (global_data.getString(colum_name1).equals(Id)) {
                    //global_data.beforeFirst();
                    return global_data.getString(colum_name2);
                }

            }//end while
        }//end try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Get name->" + e);
        }
        //global_data.beforeFirst();
        return "";
    }//end function
    //***************************************

    public String get_cme(String Id) {
        String colum_name1 = "Id";
        String colum_name2 = "CME";
        try {
            global_data.beforeFirst();
            while (global_data.next()) {
                //if you found the id u want get his name
                System.out.println(global_data.getString("Id") + " " + colum_name1);
                System.out.println(global_data.getString("CME") + " " + colum_name2);
                if (global_data.getString(colum_name1).equals(Id)) {
                    //global_data.beforeFirst();
                    return global_data.getString(colum_name2);
                }

            }//end while
        }//end try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Get CME->" + e);
        }
        //global_data.beforeFirst();
        return "";
    }//end function
    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        try {
            Font s_font = new Font("Tahoma", Font.BOLD, 24);
            search.setFont(s_font);
            String data = search.getText();
            Userdata = new JTable(buildTableModel(global_data)) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return true;
                }
            };
            //sort_table();
            global_data.beforeFirst();
            int mobile_index = getColumnIndex(Userdata, "Mobile"); //get mobile colum index to search in it 
            set_style();
            String sort_item = sort.getSelectedItem().toString();
            if (data.equals("")) {
                Userdata = new JTable(buildTableModel(global_data));
                global_data.beforeFirst();
                set_style();
            } else {
                for (int i = 0; i < Userdata.getRowCount(); i++) {
                    int validate = 0;
                    
                    if (sort_item.equals("All")) {
                        for (int k = 0; k < search_items.size(); k++) {
                            if (search_items.get(k).equals("All")); else if (Userdata.getValueAt(i, getColumnIndex(Userdata, search_items.get(k))) != null && !Userdata.getValueAt(i, getColumnIndex(Userdata, search_items.get(k))).equals("") && Userdata.getValueAt(i, getColumnIndex(Userdata, search_items.get(k))).toString().toLowerCase().contains(data.toLowerCase())) {
                                validate = 1;
                            }
                        }
                        //delete from table
                        if (validate == 0) {
                            TableModel model = Userdata.getModel();
                            if (model instanceof DefaultTableModel) {
                                ((DefaultTableModel) model).removeRow(i);
                                i--;
                            }
                        }
                    }//end all
                    else if (Userdata.getValueAt(i, getColumnIndex(Userdata, sort_item)) != null && Userdata.getValueAt(i, getColumnIndex(Userdata, sort_item)).toString().toLowerCase().contains(data.toLowerCase())); else {
                        TableModel model = Userdata.getModel();
                        if (model instanceof DefaultTableModel) {
                            ((DefaultTableModel) model).removeRow(i);
                            i--;
                        }
                    }//end any search item

                }//end loop searching in table
                users_no.setText(Integer.toString(Userdata.getRowCount()));
            }
            sort_table();
        } catch (Exception ex) {
            // JOptionPane.showMessageDialog(null, ex+" \nSearch Table");

        }
    }//GEN-LAST:event_searchKeyReleased
    public int getColumnIndex(JTable table, String header) {
        try {
            for (int i = 0; i < table.getColumnCount(); i++) {
                if (table.getColumnName(i).equals(header)) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Get ColumIndex" + e);
        }
        return -1;
    }
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_searchMouseClicked

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        // TODO add your handling code here:
        //search.setFont(new Font("Tahoma" , Font.PLAIN , 14));
        // search.setForeground(new Color(102,102,102));
        //search.setText("Name , Phone , ID");
        //search.setBorder(new LineBorder(Color.BLACK , 1));
    }//GEN-LAST:event_searchFocusLost

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        // TODO add your handling code here:
        clear_selection();

        search.setForeground(Color.BLACK);
        search.setFont(new Font("Tahoma", Font.BOLD, 24));
        search.setBorder(new LineBorder(white_blue, 2));
    }//GEN-LAST:event_searchFocusGained

    private void jMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseExited

    private void jMenu1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMenu1FocusGained
        // TODO add your handling code here
    }//GEN-LAST:event_jMenu1FocusGained

    private void jMenu1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMenu1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1FocusLost

    private void jMenu1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseMoved
        // TODO add your handling code here:
        clear_selection();
        jMenu1.setSelected(true);
    }//GEN-LAST:event_jMenu1MouseMoved

    private void TagsettingsMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TagsettingsMouseMoved
        // TODO add your handling code here:
        clear_selection();
        Tagsettings.setSelected(true);
    }//GEN-LAST:event_TagsettingsMouseMoved

    private void certificatesettingsMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_certificatesettingsMouseMoved
        // TODO add your handling code here:
        clear_selection();
        certificatesettings.setSelected(true);
    }//GEN-LAST:event_certificatesettingsMouseMoved

    private void SwitchMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SwitchMouseMoved
        // TODO add your handling code here:
        clear_selection();
        Switch.setSelected(true);
    }//GEN-LAST:event_SwitchMouseMoved

    private void refreshMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseMoved
        // TODO add your handling code here:
        clear_selection();
        refresh.setSelected(true);
    }//GEN-LAST:event_refreshMouseMoved

    private void print_certFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_print_certFocusLost
        // TODO add your handling code here:
        print_cert.setForeground(new Color(102, 102, 102));
        print_cert.setBorder(new LineBorder(Color.BLACK, 1));
        print_cert.setFont(new Font("Tahoma", Font.PLAIN, 14));
        print_cert.setText("Certificate ID");
    }//GEN-LAST:event_print_certFocusLost

    private void filterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_filterFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_filterFocusGained

    private void lanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lanActionPerformed

    private void onlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onlineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_onlineActionPerformed

    private void onlineMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onlineMousePressed
        // TODO add your handling code here:

        JPasswordField pwd = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (action < 0 || pwd.getText().equals(""));//enter Exit X do nothing
        else if (pwd.getText().equals(system_password)) {

            db.switch_to_local(1);//cloud
            set_GlobalSettings();
            display_data();
            display_connection();

        } else {
            alert_frame warn = new alert_frame("Wrong Password");
            warn.setVisible(true);
        }// end else
    }//GEN-LAST:event_onlineMousePressed

    private void offlineMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_offlineMousePressed
        // TODO add your handling code here:
        JPasswordField pwd = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (action < 0 || pwd.getText().equals(""));//enter Exit X do nothing
        else if (pwd.getText().equals(system_password)) {
            wait_thread.wf.setVisible(true);
            db.switch_to_local(0);//local
            set_GlobalSettings();
            display_data();
            //set_style();
            insert_buttons();
            display_connection();
            wait_thread.wf.setVisible(false);
        } else {
            alert_frame warn = new alert_frame("Wrong Password");
            warn.setVisible(true);
        }
    }//GEN-LAST:event_offlineMousePressed

    private void lanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lanMousePressed
        // TODO add your handling code here:
        JPasswordField pwd = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (action < 0 || pwd.getText().equals(""));//enter Exit X do nothing
        else if (pwd.getText().equals(system_password)) {
            wait_thread.wf.setVisible(true);
            db.switch_to_local(2);//LAN
            set_GlobalSettings();
            display_data();
            //set_style();
            display_connection();
            insert_buttons();
            wait_thread.wf.setVisible(false);
        } else {
            alert_frame warn = new alert_frame("Wrong Password");
            warn.setVisible(true);
        }

    }//GEN-LAST:event_lanMousePressed

    private void AdvSettMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdvSettMousePressed
        // TODO add your handling code here:

        if (UType.equals("admin")) {
            Advanced_Settings obj = new Advanced_Settings(db);
            obj.setVisible(true);
        }//end compare
        else {
            alert_frame warn = new alert_frame("Sorry you are not Admin");
            warn.setVisible(true);
        }

    }//GEN-LAST:event_AdvSettMousePressed

    private void AdvSettMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdvSettMouseMoved
        // TODO add your handling code here:
        clear_selection();
        AdvSett.setSelected(true);

    }//GEN-LAST:event_AdvSettMouseMoved

    private void TagsettingsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TagsettingsMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TagsettingsMouseReleased

    private void certificatesettings1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_certificatesettings1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_certificatesettings1MouseMoved

    private void certificatesettings1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_certificatesettings1MousePressed
        // TODO add your handling code here:
        JPasswordField pwd = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (action < 0 || pwd.getText().equals(""));//enter Exit X do nothing
        else if (pwd.getText().equals(system_password)) {
            TagSetting ts = new TagSetting();
            ts.setVisible(true);
        } else {
            alert_frame warn = new alert_frame("Wrong Password");
            warn.setVisible(true);
        }
    }//GEN-LAST:event_certificatesettings1MousePressed

    private void att_menueMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_att_menueMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_att_menueMouseMoved

    private void att_menueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_att_menueMousePressed
        // TODO add your handling code here:
        attendance_percent ts = new attendance_percent(this);
        ts.setVisible(true);
    }//GEN-LAST:event_att_menueMousePressed

    private void print_btMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_print_btMouseMoved
        // TODO add your handling code here:
        print_bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_print_btMouseMoved

    private void CrtificateMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CrtificateMouseMoved
        // TODO add your handling code here:
        Crtificate.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_CrtificateMouseMoved

    private void jMenu5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseMoved

    private void jMenu5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MousePressed

    private void printNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_printNameFocusLost
        // TODO add your handling code here:

    }//GEN-LAST:event_printNameFocusLost

    private void printNameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printNameMouseReleased
        // TODO add your handling code here:
        clear_selection();
        if (printName.getText().equals("Certificate Name")) {
            printName.setText("");
        }
        printName.setForeground(Color.BLACK);
        printName.setFont(new Font("Tahoma", Font.BOLD, 20));
        printName.setBorder(new LineBorder(white_blue, 2));

    }//GEN-LAST:event_printNameMouseReleased

    private void printNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printNameActionPerformed
        // TODO add your handling code here:
        try {
            String name = "";
            name = printName.getText();
            StringBuilder myName = new StringBuilder(name);
            for (int i = 0; i < name.length(); i++) {
                if (i == 0) {
                    myName.setCharAt(i, Character.toUpperCase(name.charAt(i)));
                } else if (name.charAt(i) == ' ') {
                    for (int k = i; k < name.length(); k++) {
                        if (name.charAt(k) != ' ') {
                            myName.setCharAt(k, Character.toUpperCase(name.charAt(k)));
                            k = name.length();
                        }
                    }
                }
            }
            name = myName.toString();
            Directprint lt = new Directprint(1); // Certificate 
            lt.printString(name);
            db.updata_query(DatabaseConstants.update_user_Setting(DatabaseConstants.user_Settings, "no_certificate", "username"));
            printName.setText("");
            printName.requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "PrintName ->" + e);
        }

    }//GEN-LAST:event_printNameActionPerformed

    private void printNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_printNameKeyReleased

    private void print_certFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_print_certFocusGained
        // TODO add your handling code here:
        clear_selection();
        print_cert.setText("");
        print_cert.setForeground(Color.BLACK);
        print_cert.setFont(new Font("Tahoma", Font.BOLD, 20));
        print_cert.setBorder(new LineBorder(white_blue, 2));
    }//GEN-LAST:event_print_certFocusGained

    private void report_menueMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_menueMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_report_menueMouseMoved

    private void report_menueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_menueMousePressed
        // TODO add your handling code here:
        JPasswordField pwd = new JPasswordField(10);
        int action = JOptionPane.showConfirmDialog(null, pwd, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
        if (action < 0 || pwd.getText().equals(""));//enter Exit X do nothing
        else if (pwd.getText().equals(system_password)) {
            klydar_list.report.report_gui s = new report_gui();
            s.setVisible(true);
        } else {
            alert_frame warn = new alert_frame("Wrong Password");
            warn.setVisible(true);
        }

    }//GEN-LAST:event_report_menueMousePressed

    private void admin_SettingBt1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_SettingBt1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_admin_SettingBt1MouseMoved

    private void admin_SettingBt1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admin_SettingBt1MousePressed
        // TODO add your handling code here:
        if (UType.equals("admin")) {
            Admin adm = new Admin();
            adm.setVisible(true);
        } else {
            alert_frame warn = new alert_frame("Sorry You are Not Admin");
            warn.setVisible(true);
        }

    }//GEN-LAST:event_admin_SettingBt1MousePressed

    private void logoutMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseMoved
        // TODO add your handling code here:
        clear_selection();
        logout.setSelected(true);
    }//GEN-LAST:event_logoutMouseMoved
    public void create_THhelper() { // 0 for logout 1 for connecion
        th_helper = new Thread(obj);
        th_helper.start();
    }
    private void logoutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMousePressed

        wait_thread.wf.setVisible(true);
        ref_obj.on = false; // stop the thread

        create_THhelper(); // create thread to call logout

        //return_login();
    }//GEN-LAST:event_logoutMousePressed

    private void users_noMouseMoved(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
    }                                   

    private void users_noMousePressed(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void username_labelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_username_labelMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_username_labelMouseMoved

    private void username_labelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_username_labelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_labelMousePressed

    private void url_menueMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_url_menueMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_url_menueMouseMoved

    private void url_menueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_url_menueMousePressed
        // TODO add your handling code here:
        try {
            JTextField pwd = new JTextField();
            pwd.setText("http://");
            int action = JOptionPane.showConfirmDialog(null, pwd, "Insert URL", JOptionPane.OK_CANCEL_OPTION);

            if (action < 0 || pwd.getText().equals("http://") || pwd.getText().equals(""));//enter Exit X do nothing
            else {
                Website_form web = new Website_form((pwd.getText()), 1);
                web.display();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_url_menueMousePressed

    private void filter3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter3ActionPerformed
        // TODO add your handling code here:
        try {
            Userdata = new JTable(buildTableModel(global_data));
            global_data.beforeFirst();
            set_style();
            for (int i = 0; i < Userdata.getRowCount(); i++) {
                if (Userdata.getValueAt(i, getColumnIndex(Userdata, "Attendees")).equals(filter3.getSelectedItem().toString())); else {
                    TableModel model = Userdata.getModel();
                    if (model instanceof DefaultTableModel) {
                        ((DefaultTableModel) model).removeRow(i);
                        i--;
                    }
                }//end delete
            }//end passing in table
            users_no.setText(Integer.toString(Userdata.getRowCount()));
        }//end try
        catch (Exception e) {

        }//end catch
    }//GEN-LAST:event_filter3ActionPerformed

    private void filter3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_filter3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_filter3FocusGained

    private void cme_menueMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cme_menueMouseMoved
        // TODO add your handling code here:
        clear_selection();
        cme_menue.setSelected(true);
    }//GEN-LAST:event_cme_menueMouseMoved

    private void cme_menueFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cme_menueFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_menueFocusGained

    private void cme_menueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cme_menueFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_menueFocusLost

    private void cme_menueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cme_menueMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_cme_menueMouseExited

    private void cme_menueMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cme_menueMousePressed
        // TODO add your handling code here:
//        Website_form web = new Website_form("http://think-ds.com/projects/signing_io/public/admin/signing?hall_id=7", 1);
//        web.display();
        Check_in_out check = new Check_in_out();
        check.setVisible(true);
    }//GEN-LAST:event_cme_menueMousePressed

    public void clear_selection() {
        jMenu1.setSelected(false);
        refresh.setSelected(false);
        admin_SettingBt1.setSelected(false);
        Switch.setSelected(false);
        Tagsettings.setSelected(false);
        certificatesettings.setSelected(false);
        AdvSett.setSelected(false);
        users_no.setEnabled(false);
        logout.setSelected(false);
        username_label.setSelected(false);
        url_menue.setSelected(false);
        cme_menue.setSelected(false);
    }

    public void display_data() {
        try {
            //* set filters name

            secondFilter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, filter1, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 17), new java.awt.Color(51, 51, 51)));
            filter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, filter2, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 17), new java.awt.Color(51, 51, 51)));
            //*
            if (Advanced_Settings.isVip == true) {
                ResultSet rs = db.select_query(DatabaseConstants.all_data + " where vip = '1'");
                Userdata = new JTable(buildTableModel(rs));
                rs.beforeFirst();
                Set type = remove_duplicate(rs, filter2);
                set_style();
                filter_type(type);
                rs.beforeFirst();
                type.clear();
                type = remove_duplicate(rs, filter1);
                filter_sponsor(type);
                rs.beforeFirst();
                Statement new_ste = db.cn.createStatement();
                global_data = new_ste.executeQuery(DatabaseConstants.all_data + " where vip = '1'");
                display_connection();
            } else {
                ResultSet rs = db.select_query(DatabaseConstants.all_data);
                Userdata = new JTable(buildTableModel(rs));
                rs.beforeFirst();
                Set type = remove_duplicate(rs, filter2);
                set_style();
                filter_type(type);
                rs.beforeFirst();
                type.clear();
                type = remove_duplicate(rs, filter1);
                filter_sponsor(type);
                rs.beforeFirst();
                global_data = db.select_query(DatabaseConstants.all_data);
                display_connection();
            }

        }//end try
        catch (Exception e) {
            System.out.println(e);
        }//end catch
    }

    // this func for display choices in the choice menue and 
    public Set remove_duplicate(ResultSet data, String colm) {
        Set<String> set = new HashSet<>(); // set because it prevents duplicate data
        try {
            while (data.next()) {
                set.add(data.getString(colm));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Filter Not Found In table Colums");
        }
        return set;
    }

    // this func for display choices in the choice menue and 
    public void filter_type(Set data) {

        try {
            filter.addItem("Show all");
            Enumeration e = Collections.enumeration(data);
            e.nextElement();
            while (e.hasMoreElements()) {
                filter.addItem((String) e.nextElement());
            }
            users_no.setText(Integer.toString(Userdata.getRowCount()));
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"filter type "+e);
        }
    }

    public void filter_sponsor(Set data) {
        try {

            secondFilter.addItem("Show all");
            Enumeration e = Collections.enumeration(data);
            e.nextElement();
            while (e.hasMoreElements()) {
                secondFilter.addItem((String) e.nextElement());
            }
            users_no.setText(Integer.toString(Userdata.getRowCount()));
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"filter type "+e);
        }
    }

    //update using thread :D 
    public void VUpdate_Data(String query) {
        VirtualDatabase rt = new VirtualDatabase(2, query);
        Thread d = new Thread((Runnable) rt);
        d.start();
    }

    public void set_style() {

        try {
            /**
             * ************************************************************************
             */
            DefaultTableCellRenderer at = new DefaultTableCellRenderer() {
                Border padding_on = BorderFactory.createEmptyBorder(0, 50, 0, 0);
                Border padding_off = BorderFactory.createEmptyBorder(0, 45, 0, 0);
                Border bt = new BasicBorders.ButtonBorder(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                            row, column);
                    String data = Userdata.getValueAt(row, getColumnIndex(Userdata, "Attendees")).toString();
                    //System.out.println(data);
                    if (data.equals(DatabaseConstants.attend) || data.equals("1")) {
                        setBackground(DatabaseConstants.attend_col);
                        setForeground(Color.WHITE);
                        setBorder(BorderFactory.createCompoundBorder(bt, padding_on));
                    } else {
                        setBackground(DatabaseConstants.no_attend_col);
                        setForeground(Color.WHITE);
                        setBorder(BorderFactory.createCompoundBorder(bt, padding_off));
                    }
                    return this;
                }
            };
            DefaultTableCellRenderer name_rend = new DefaultTableCellRenderer() {
                Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 0);

                @Override
                public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                            row, column);
                    setBorder(padding);
                    return this;
                }
            };
            Userdata.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent me) {
                    if (Userdata.getColumnName(Userdata.getSelectedColumn()).equals("Attendees") && Userdata.getSelectedRowCount() == 1) {
                        int selected_row = Userdata.getSelectedRow();
                        int id_cust = Integer.parseInt(Userdata.getValueAt(Userdata.getSelectedRow(), getColumnIndex(Userdata, "Id")).toString());
                        String status_value = Userdata.getValueAt(Userdata.getSelectedRow(), getColumnIndex(Userdata, "Attendees")).toString();
                        if (status_value.equals(DatabaseConstants.no_attend)) {

                            Userdata.setValueAt(DatabaseConstants.attend, Userdata.getSelectedRow(), Userdata.getSelectedColumn());//on
                            VUpdate_Data(DatabaseConstants.update_2tables("no_attendees", "attendees", "1", Integer.toString(id_cust), Login.Uname));
                        } else {
                            Userdata.setValueAt(DatabaseConstants.no_attend, Userdata.getSelectedRow(), Userdata.getSelectedColumn());//off
                            VUpdate_Data(DatabaseConstants.update_2tables("no_attendees", "attendees", "0", Integer.toString(id_cust), Login.Uname));
                        }
                        //check dinner available or not if avaliable print else doesnot
                        if (DatabaseConstants.DINNER_MODE == 1) {
                            String hasdinner = Userdata.getValueAt(selected_row, getColumnIndex(Userdata, "Dinner")).toString();
                            String name = Userdata.getValueAt(selected_row, getColumnIndex(Userdata, "Name")).toString();
                            if (hasdinner.equals("1")) {
                                String check_mode = Userdata.getValueAt(Userdata.getSelectedRow(), getColumnIndex(Userdata, "Attendees")).toString();
                                if (check_mode.equals(DatabaseConstants.attend)) {
                                    Directprint dinner = new Directprint(0);// want to print nametage
                                    dinner.printString(name);

                                }//end has dinner and mode on
                            }//end has dinner
                        }//end dinner mode
                    }
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent me) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    Userdata.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

            /**
             * ************************************************************************
             */
            jScrollPane1.setViewportView(Userdata);
            rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            int temp = getColumnIndex(Userdata, "Id");
            if (temp != -1) {
                Userdata.getColumnModel().getColumn(temp).setMinWidth(60);
                Userdata.getColumnModel().getColumn(temp).setCellRenderer(rightRenderer);
            }
            temp = getColumnIndex(Userdata, "Name");
            if (temp != -1) {
                Userdata.getColumnModel().getColumn(temp).setMinWidth(330);
                Userdata.getColumnModel().getColumn(temp).setCellRenderer(leftRenderer);
                Userdata.getColumnModel().getColumn(temp).setCellRenderer(name_rend);
            }
            temp = getColumnIndex(Userdata, "Attendees");
            if (temp != -1) {
                Userdata.getColumnModel().getColumn(temp).setMinWidth(130);
                //Userdata.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
                /**/
                Userdata.getColumnModel().getColumn(temp).setCellRenderer(at);
            }

            /**/
            temp = getColumnIndex(Userdata, "Mobile");
            if (temp != -1) {
                Userdata.getColumnModel().getColumn(temp).setMinWidth(200);
                Userdata.getColumnModel().getColumn(temp).setCellRenderer(rightRenderer);
            }
            temp = getColumnIndex(Userdata, "Name_ar");
            if (temp != -1) {
                Userdata.getColumnModel().getColumn(temp).setMinWidth(220);
                Userdata.getColumnModel().getColumn(temp).setCellRenderer(rightRenderer);
            }
            temp = getColumnIndex(Userdata, "Email");
            if (temp != -1) {
                Userdata.getColumnModel().getColumn(temp).setMinWidth(230);
                Userdata.getColumnModel().getColumn(temp).setCellRenderer(rightRenderer);
            }
            for (int i = 0; i < Userdata.getColumnCount(); i++) {
                if (getColumnIndex(Userdata, "Email") == i || getColumnIndex(Userdata, "Id") == i || getColumnIndex(Userdata, "Name_ar") == i
                        || getColumnIndex(Userdata, "Mobile") == i || getColumnIndex(Userdata, "Attendees") == i || getColumnIndex(Userdata, "Name") == i); else {
                    Userdata.getColumnModel().getColumn(i).setMinWidth(220);
                    Userdata.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
                }
            }
            Userdata.setGridColor(new Color(211, 211, 211));
            Color white = new Color(255, 255, 255);
            Userdata.setBackground(white);
            Userdata.setForeground(new Color(100, 100, 100));
            Userdata.setFont((new Font("Tahoma", Font.PLAIN, 18)));
            Userdata.setRowHeight(45);
            Userdata.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
            JTableHeader header = Userdata.getTableHeader();
            Color white_gray_selection = new Color(41, 58, 74);
            Color ztone = new Color(0, 153, 153);
            header.setBackground(new Color(230, 230, 230));
            header.setForeground(new Color(50, 50, 50));
            header.setBorder(new LineBorder(new Color(211, 211, 211)));
            Userdata.setSelectionBackground(white_gray_selection);
            Userdata.setSelectionForeground(Color.WHITE);
            Userdata.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            Userdata.setRowMargin(10);
            for (int i = 0; i < Userdata.getRowCount(); i++) {
                String status_value = Userdata.getValueAt(i, getColumnIndex(Userdata, "Attendees")).toString();
                if (status_value.equals("0") || status_value.equals(DatabaseConstants.no_attend)) {
                    Userdata.setValueAt(DatabaseConstants.no_attend, i, getColumnIndex(Userdata, "Attendees"));
                } else {
                    Userdata.setValueAt(DatabaseConstants.attend, i, getColumnIndex(Userdata, "Attendees"));

                }
                /*Calculate CME*/
                if(getColumnIndex(Userdata,"CME")!=-1)
                {
                    String CME=Userdata.getValueAt(i, getColumnIndex(Userdata, "CME")).toString();
                    Userdata.setValueAt(CME, i, getColumnIndex(Userdata, "CME_MINUTES"));
                    Userdata.setValueAt((int)(Math.ceil(Float.parseFloat(CME)/60)), i, getColumnIndex(Userdata,"CME"));
                }
                /***************/
            }
            Userdata.setDefaultEditor(Object.class, new MyEditor());
            users_no.setText(Integer.toString(Userdata.getRowCount()));
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Set stye ->" + e);
        }
        //sort_table();

    }
    public void sort_table(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(Userdata.getModel());
        Userdata.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        //sortKeys.add(new RowSorter.SortKey(6, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) {
        Vector<String> columnNames = null;
        Vector<Vector<Object>> data = null;
        try {
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                String column_name = metaData.getColumnName(column);
                StringBuilder myName = new StringBuilder(column_name);
                myName.setCharAt(0, Character.toUpperCase(column_name.charAt(0)));
                column_name = myName.toString();
                columnNames.add(column_name);
            }
            columnNames.add("CME_MINUTES");
            // data of the table
            data = new Vector<Vector<Object>>();

            while (rs.next()) {

                Vector<Object> vector = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(rs.getObject(columnIndex));
                }

                data.add(vector);
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"Default table model "+ e);
        }
        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }
        };

    }

    /**
     * @param args the command line arguments
     */
    class MyEditor extends DefaultCellEditor {

        public MyEditor() {
            super(new JTextField());
        }
        String old_value = "";

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped(); //To change body of generated methods, choose Tools | Templates.
            try {
                if (Userdata.getSelectedRowCount() == 1) {
                    String sponsor = "";
                    String AFF = "";
                    String name = "";
                    int selected_row = Userdata.getSelectedRow();
                    int id = Integer.parseInt(Userdata.getValueAt(selected_row, 0).toString());
                    String column_name = Userdata.getColumnName(Userdata.getSelectedColumn());
                    //column_name=column_name.toLowerCase();
                    String data_updated = Userdata.getValueAt(selected_row, Userdata.getSelectedColumn()).toString();
                    if (Login.allow_update != 1 || column_name.equals("Id") || column_name.equals("F_num") || column_name.equals("CME_MINUTES")) {
                        alert_frame af = new alert_frame("you are not privileged to update user");
                        af.setVisible(true);
                        Userdata.setValueAt(old_value, selected_row, Userdata.getSelectedColumn());
                    } 
                    else if (column_name.equals("CME")) {
                        //check for mob
                        int cme = Integer.parseInt(data_updated);
                        cme=cme*60;
                        VUpdate_Data(DatabaseConstants.update_2tables("no_update", column_name, Integer.toString(cme), Integer.toString(id), Login.Uname));
                    }else if (column_name.equals("Mobile")) {
                        //check for mob
                        long mob = Long.parseLong(data_updated);

                        VUpdate_Data(DatabaseConstants.update_2tables("no_update", column_name, data_updated, Integer.toString(id), Login.Uname));
                    } else if (column_name.equals("Attendees")) {

                        //no thing
                        ResultSet get_att = db.select_query("select attendees from lists where id =" + id + "");
                        if (get_att.next()) {
                            String att = get_att.getString("attendees");
                            if (att.equals("0")) {
                                Userdata.setValueAt(DatabaseConstants.no_attend, selected_row, getColumnIndex(Userdata, "Attendees"));
                            } else {
                                Userdata.setValueAt(DatabaseConstants.attend, selected_row, getColumnIndex(Userdata, "Attendees"));
                            }
                        }

                    } else {

                        VUpdate_Data(DatabaseConstants.update_2tables("no_update", column_name, data_updated, Integer.toString(id), Login.Uname));
//           
                    }
                }//if select one row
                else {
                    alert_frame af = new alert_frame("Select One");
                    af.setVisible(true);
                }//if select more one row
            }//end try
            catch (Exception ex) {
                alert_frame af = new alert_frame("Invalid Data");
                af.setVisible(true);
                Userdata.setValueAt(old_value, Userdata.getSelectedRow(), Userdata.getSelectedColumn());
            }//end catch
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                int row, int column) {
            JTextField editor = (JTextField) super.getTableCellEditorComponent(table, value, isSelected,
                    row, column);
            old_value = editor.getText();
            editor.setBackground(new Color(230, 230, 230));
            editor.setHorizontalAlignment(SwingConstants.CENTER);
            editor.setFont(new Font("Serif", Font.BOLD, 18));
            return editor;

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AdvSett;
    private javax.swing.JButton Crtificate;
    private javax.swing.JMenu Switch;
    private javax.swing.JMenu Tagsettings;
    private javax.swing.JMenu admin_SettingBt1;
    private javax.swing.JMenu att_menue;
    private javax.swing.JMenu certificatesettings;
    private javax.swing.JMenu certificatesettings1;
    private javax.swing.JMenu cme_menue;
    private javax.swing.JMenu empty;
    private javax.swing.JComboBox<String> filter;
    private javax.swing.JComboBox<String> filter3;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem lan;
    private javax.swing.JMenu logout;
    private javax.swing.JMenuItem offline;
    private javax.swing.JMenuItem online;
    private javax.swing.JTextField printName;
    private javax.swing.JButton print_bt;
    private javax.swing.JTextField print_cert;
    private javax.swing.JMenu refresh;
    private javax.swing.JMenu report_menue;
    private javax.swing.JTextField search;
    private javax.swing.JComboBox<String> secondFilter;
    private javax.swing.JComboBox<String> sort;
    private javax.swing.JMenu url_menue;
    private javax.swing.JMenu username_label;
    private javax.swing.JMenu users_no;
    // End of variables declaration//GEN-END:variables
//***************************************************** bar code and status button author: sheko 
    public void insert_buttons() {

    }//end function insert buttons
    /**
     * ******************************************************************
     */
}

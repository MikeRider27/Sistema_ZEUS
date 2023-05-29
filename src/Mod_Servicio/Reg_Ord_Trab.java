
package Mod_Servicio;

import threads.CboThreadGT;
import threads.CboThreadGT2;
import threads.TblThread;
import frames.Buscador;
import frames.Login;
import frames.Menu;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import config.LoadWithObjects2Data;
import config.conectarDB;

/**
 *
 * @author schafler92
 */
public class Reg_Ord_Trab extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    int bandera,ban_rep,ban_pza,ban_trab;
    String aop,cod,orden="1 DESC",bus="cli_ci",see="",codVeh,codSeg,year,mes,suc;
    
    public Reg_Ord_Trab() {
        initComponents();
        this.setFrameIcon(new ImageIcon(getClass().getResource("/img/Imagen2.png")));
        DB = new conectarDB();
        ManageButtons(1);
        cargarTabla(); 
        cargarAño();
    }
 private void cargarTabla(){
     if(!txtBuscar.getText().trim().isEmpty()){
            limpiarTabla(jTable3);
            int[] tipo_de_pst={0}; 
        String[] par={""}; 
 // new TblThread("SELECT * FROM v_prep WHERE MONTH(prep_date)=MONTH(CURDATE()) "+see+" "+suc+" ORDER BY "+orden,jTable3,tipo_de_pst,par,DB.con,this).start();
     }
 }
 
   private void cargarAño(){
      cboAño.removeAllItems();
      cboAño.addItem("Este Año");
      cboAño.addItem("Año Pasado");
      int current_year=Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
      while(current_year!=2014){
          cboAño.addItem(String.valueOf(current_year));
          current_year--;
      }
  }
 
 private void cargarEdicionRep( javax.swing.JTable tabla, String parametro){
        limpiarTabla(tabla);
        int[] tipo_de_pst={1}; 
        String[] par={parametro}; 
      // new TblThread("SELECT rep_des,det_org,det_pre FROM v_dprep1 WHERE prep_cod=?" , tabla, tipo_de_pst,par,DB.con,this).start();
       par=null;
 }
 
  private void cargarEdicionTrab( javax.swing.JTable tabla, String parametro){
        limpiarTabla(tabla);
        int[] tipo_de_pst={1}; 
        String[] par={parametro}; 
    //   new TblThread("SELECT trab_des,det_pre FROM v_dprep3 WHERE prep_cod=?" , tabla, tipo_de_pst,par,DB.con,this).start();
       par=null;
 }
       
 private void cargarEdicionPzas( javax.swing.JTable tabla, String parametro){
        limpiarTabla(tabla);
        int[] tipo_de_pst={1}; 
        String[] par={parametro}; 
       DB.FillTable("SELECT pie_des,dam_des,det_precio FROM v_dprep2 WHERE prep_cod=?" , tabla, tipo_de_pst,par);
       par=null;
 }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUpTrab = new javax.swing.JPopupMenu();
        menNullTrab = new javax.swing.JMenuItem();
        popUpRep = new javax.swing.JPopupMenu();
        menNullRep = new javax.swing.JMenuItem();
        popUpPzas = new javax.swing.JPopupMenu();
        menNullPzas = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCodCli = new javax.swing.JTextField();
        txtCli = new javax.swing.JTextField();
        btnCli = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSuc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSin = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        txtVeh = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cboPza = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cboDam = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cboTrab = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        txtPre2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        cboAño = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        cboSuc = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        menNullTrab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        menNullTrab.setText("Anular Registro");
        menNullTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menNullTrabActionPerformed(evt);
            }
        });
        popUpTrab.add(menNullTrab);

        menNullRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        menNullRep.setText("Anular Registro");
        menNullRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menNullRepActionPerformed(evt);
            }
        });
        popUpRep.add(menNullRep);

        menNullPzas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        menNullPzas.setText("Anular Registro");
        menNullPzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menNullPzasActionPerformed(evt);
            }
        });
        popUpPzas.add(menNullPzas);

        setTitle("Registrar Orden de Trabajo");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Orden de Trabajo"));

        jLabel2.setText("Vehiculo");

        jLabel1.setText("Codigo");

        txtCod.setEditable(false);
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("Presupuesto Nº");

        txtCodCli.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCodCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodCliKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodCliKeyTyped(evt);
            }
        });

        txtCli.setEditable(false);

        btnCli.setText("...");
        btnCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha");

        txtDate.setEnabled(false);

        jLabel4.setText("Sucursal");

        txtSuc.setEnabled(false);

        jLabel10.setText("Siniestro Nº");

        txtSin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSinKeyPressed(evt);
            }
        });

        jTextField1.setEnabled(false);

        txtVeh.setEnabled(false);

        jLabel16.setText("Asegurado");

        jLabel8.setText("Ord. Nº");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodCli, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtCli)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCli))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtSin, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtVeh))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Piezas a ser Reparadas"));

        jLabel15.setText("Pieza");

        cboPza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboPza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPzaActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Nivel de Daño", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(180);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        cboDam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboDam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDamActionPerformed(evt);
            }
        });

        jLabel9.setText("Daño");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(cboPza, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cboDam, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboPza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(cboDam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Trabajos de Terceros a ser Realizados"));

        jLabel23.setText("Trabajo");

        cboTrab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboTrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrabActionPerformed(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Trabajo", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setResizable(false);
            jTable6.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable6.getColumnModel().getColumn(1).setResizable(false);
            jTable6.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable6.getColumnModel().getColumn(2).setResizable(false);
            jTable6.getColumnModel().getColumn(2).setPreferredWidth(70);
        }

        jLabel25.setText("Precio");

        txtPre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPre2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPre2KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(cboTrab, 0, 173, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(txtPre2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cboTrab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtPre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(82, 82, 82))
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Cia. de Seguro", "Vehiculo", "Asegurado", "Sucursal", "Fecha", "Siniestro Nº"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(180);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(180);
            jTable3.getColumnModel().getColumn(4).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(5).setResizable(false);
            jTable3.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTable3.getColumnModel().getColumn(6).setResizable(false);
            jTable3.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Ordenar Por");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo Desc.", "Cia. de Seguro Asc.", "Asegurado", "Vehiculo" }));

        jLabel7.setText("Buscar Por");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asegurado" }));

        jLabel17.setText("Ver");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activos", "Anulados" }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("<ENTER>");

        jLabel28.setText("Filtrat Por");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Mes", "Mes Pasado", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel29.setText("del");

        cboAño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAñoActionPerformed(evt);
            }
        });

        jLabel30.setText("en");

        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listado", jPanel2);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_add.png"))); // NOI18N
        btnAdd.setText("Agregar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        btnNull.setText("Anular");
        btnNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNullActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png"))); // NOI18N
        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/door-in-icon.png"))); // NOI18N
        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit)
                .addGap(259, 259, 259))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtCodCliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodCliKeyPressed
if(evt.getKeyCode()==10){    
//DB.consultPstSimple("SELECT c.cli_ci,CONCAT(c.cli_nom,', ',c.cli_ape)AS cli FROM v_cli c WHERE c.cli_ci=?", txtCodCli.getText().trim(),txtCli,txtCodCli,this);
//cargarVehiculos();
}
    }//GEN-LAST:event_txtCodCliKeyPressed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
    dispose();   
    DB.shutdown();
}else{
   if (JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
        dispose();
        DB.shutdown();
        } 
}
Menu.menPrep.setEnabled(true);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            if(DB.con.getAutoCommit()==false){
                DB.con.rollback();
                System.out.print("\nROLLBACK");
            }   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),"Error al Cancelar", JOptionPane.ERROR_MESSAGE ); 
        }
        clean();
        ManageButtons(1);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtSinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSinKeyPressed
if(evt.getKeyCode()==10){
    if(!txtVeh.getText().trim().isEmpty() && !txtCodCli.getText().trim().isEmpty() && !txtCli.getText().trim().isEmpty()){
        if (JOptionPane.showConfirmDialog(this,"¿Desea Proceder?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
           // new CboThreadGT("SELECT trep_cod,trep_des FROM v_trep ORDER BY 2 ASC", cboTrep, DB.con,"Elegir").start();
            new CboThreadGT("SELECT * FROM v_dam ORDER BY 2 ASC", cboDam, DB.con,"Elegir").start();
            new CboThreadGT("SELECT * FROM v_trab ORDER BY 2 ASC", cboTrab, DB.con,"Elegir").start();
            new CboThreadGT2("SELECT * FROM v_pzas ORDER BY 2 ASC", cboPza, DB.con).start();
save(1);
            cboPza.requestFocus();
            txtCodCli.setEnabled(false);
            txtSin.setEnabled(false);
            btnCli.setEnabled(false);
        }
}
}
    }//GEN-LAST:event_txtSinKeyPressed

    private void cboPzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPzaActionPerformed
if(cboPza.getSelectedIndex()>0){
    cboDam.requestFocus();
}
    }//GEN-LAST:event_cboPzaActionPerformed

    private void cboDamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDamActionPerformed
if(cboDam.getSelectedIndex()>0 && cboPza.getSelectedIndex()>0){
//    //int precio=(int)((Integer.parseInt(txtPre.getText().trim())*Double.parseDouble(txtUti.getText()))+(Integer.parseInt(txtPre.getText().trim())));//multiplica por la ganancia y luego suma al costo( valor ingresado en el campo precio)
//            if(DB.validarDuplicados("v_dprep2 WHERE pie_des='"+cboPza.getSelectedItem()+"' AND prep_cod='"+cod+"'")){
//            JOptionPane.showMessageDialog(this, "Ya existe dicha Pieza en el Detalle", "Alerta", JOptionPane.WARNING_MESSAGE);
//        }else{
//                ban_pza=1;
//save(3);
//        }
//    cboPza.setSelectedIndex(0);
//    cboDam.setSelectedIndex(0);
//    cargarEdicionPzas(jTable1,cod);
//    totalizaciones();
//    cboPza.requestFocus();   
}
    }//GEN-LAST:event_cboDamActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
int rows=0;
//rows=rows+jTable2.getRowCount()+jTable1.getRowCount()+jTable6.getRowCount();
if(rows>0){
    try {
        DB.con.commit();
        System.out.print("\nCOMMIT");
        DB.EmitirMensaje(this, bandera);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(),"Error al Guardar", JOptionPane.ERROR_MESSAGE ); 
    }
    clean();
    ManageButtons(1);
    cargarTabla();
}else{
    JOptionPane.showMessageDialog(this,"No se puede Guardar un Presupuesto sin Detalles","Error al Guardar", JOptionPane.ERROR_MESSAGE );
   // cboTrep.requestFocus();
}
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
aop=jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString();
ManageButtons(2);
    }//GEN-LAST:event_jTable3MousePressed

    private void btnCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliActionPerformed
//JTextField[] txt= {txtCodCli,txtCli};
//    String[]columnas={"Cédula Nº","Asegurado"};
//        Buscador b=new Buscador(null, true, "SELECT cli_ci,CONCAT(cli_nom,', ',cli_ape)AS cli FROM v_cli WHERE CONCAT(cli_nom,', ',cli_ape) LIKE", columnas, txt,1,DB.con);
//        b.setTitle("Buscador de Asegurados");
//        b.setVisible(true);
//        btnCli.setEnabled(false);
    }//GEN-LAST:event_btnCliActionPerformed

    private void cboTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrabActionPerformed
if(cboTrab.getSelectedIndex()>0)txtPre2.requestFocus();
    }//GEN-LAST:event_cboTrabActionPerformed

    private void txtPre2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPre2KeyPressed
// if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//        if(cboTrab.getSelectedIndex()>0){
//            if(DB.validarDuplicados("v_dprep3 WHERE trab_des='"+cboTrab.getSelectedItem()+"' AND prep_cod='"+cod+"'")){
//            JOptionPane.showMessageDialog(this, "Ya existe dicho Trabajo de Tercero en el Detalle", "Alerta", JOptionPane.WARNING_MESSAGE);
//        }else{
//                ban_trab=1;
//               save(4);  
//        }
//    cboTrab.setSelectedIndex(0);
//    cargarEdicionTrab(jTable6,cod);
//    cboTrab.requestFocus(); 
//    txtPre2.setText("");
//    totalizaciones();
//} 
//    }   
    }//GEN-LAST:event_txtPre2KeyPressed

    private void txtPre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPre2KeyTyped
//if(DB.fieldControl(2, evt))getToolkit().beep();
    }//GEN-LAST:event_txtPre2KeyTyped

    private void txtCodCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodCliKeyTyped
//if(DB.fieldControl(2, evt))getToolkit().beep();
    }//GEN-LAST:event_txtCodCliKeyTyped

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0){//este mes
    mes="MONTH(ent_date)=MONTH(CURDATE())";
}else{
    if(cboFilter.getSelectedIndex()==1){//mes anterior
        mes="MONTH(ent_date)=MONTH(CURDATE())-1";
    }else{
     mes="MONTH(ent_date)="+String.valueOf(cboFilter.getSelectedIndex()-1);//mes por int 1/12  
    }
    }
txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla(); 
    }//GEN-LAST:event_cboFilterActionPerformed

    private void cboAñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAñoActionPerformed
if(cboAño.getSelectedIndex()==0)year="YEAR(prep_date)=YEAR(CURDATE())";
if(cboAño.getSelectedIndex()==1)year="YEAR(prep_date)=YEAR(CURDATE())-1";
if(cboAño.getSelectedIndex()>1)year="YEAR(prep_date)="+String.valueOf(cboAño.getSelectedIndex()-1);
txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla(); 
    }//GEN-LAST:event_cboAñoActionPerformed

    private void cboSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSucActionPerformed
if(cboSuc.getSelectedIndex()==0){
    suc="AND suc_des='"+Login.suc+"'";
}else{
    suc="AND suc_des='"+cboSuc.getSelectedItem()+"'";
}
cargarTabla();
    }//GEN-LAST:event_cboSucActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
if(Integer.parseInt(Login.sucCod)<=4){
    new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE suc_des!='"+Login.suc+"' ORDER BY 2 ASC", cboSuc, DB.con,"Esta Sucursal").start();
}else{
    cboSuc.removeAllItems();
    cboSuc.addItem("Esta Sucursal");
}
    }//GEN-LAST:event_formInternalFrameOpened

    private void menNullPzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menNullPzasActionPerformed
ban_pza=3;
        save(3);
        cargarEdicionPzas(jTable1,cod);
    totalizaciones();
    cboPza.requestFocus();
    }//GEN-LAST:event_menNullPzasActionPerformed

    private void menNullRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menNullRepActionPerformed
        ban_rep=3;
        save(2);
//        cargarEdicionRep(jTable2,cod);
    totalizaciones();
   // cboTrep.requestFocus(); 
    }//GEN-LAST:event_menNullRepActionPerformed

    private void menNullTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menNullTrabActionPerformed
        ban_trab=3;
        save(4);
        cargarEdicionTrab(jTable6,cod);
    cboTrab.requestFocus();
    totalizaciones();
    }//GEN-LAST:event_menNullTrabActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    public static javax.swing.JButton btnCli;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboAño;
    private javax.swing.JComboBox cboDam;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboPza;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JComboBox cboTrab;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JMenuItem menNullPzas;
    private javax.swing.JMenuItem menNullRep;
    private javax.swing.JMenuItem menNullTrab;
    private javax.swing.JPopupMenu popUpPzas;
    private javax.swing.JPopupMenu popUpRep;
    private javax.swing.JPopupMenu popUpTrab;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCli;
    private javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtCodCli;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtPre2;
    private javax.swing.JTextField txtSin;
    private javax.swing.JTextField txtSuc;
    private javax.swing.JTextField txtVeh;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
ManageButtons(3);
bandera=1;
cod=DB.getDBcod("prep_cod","v_prep");
txtDate.setText(DB.ClientDate(1));
txtSuc.setText(Login.suc);
txtCod.setText(cod);
txtCodCli.requestFocus(); 
//DB.cargarParametros(txtUti,txtPRE);
//----------------
txtCodCli.setEnabled(true);
txtSin.setEnabled(true);
btnCli.setEnabled(true);
}

private void al_nullear(){
        bandera=3;
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            save(1);
        }
clean();
cargarTabla();
ManageButtons(1);
}

       private void ManageButtons(int estado){
     if(estado==1){//boot
         btnAdd.setEnabled(true);
         btnAdd.requestFocus();
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancel.setEnabled(false);
            jTabbedPane1.setSelectedIndex(1);
     }
          if(estado==2){//pressed
         btnAdd.setEnabled(false);
         if(cboSee.getSelectedIndex()==0){
             btnNull.setEnabled(true);
         }else{
             btnNull.setEnabled(false);
         }
         btnSave.setEnabled(false);
         btnCancel.setEnabled(true);
     }
               if(estado==3){//editing
                  btnAdd.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(true);
         btnCancel.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
     }
 }

private void clean(){
aop=null;
    txtDate.setText("");
    txtCod.setText("");
    txtSuc.setText("");
    txtCli.setText("");
    txtCodCli.setText("");
    txtSin.setText("");
  //  txtPre.setText("");
    txtBuscar.setText("");
  //  txtTotalString.setText("");
 //   txtIva.setText("0");
//----------------------
    txtBuscar.setText(null);
cboPza.setSelectedIndex(0);
//cboRep.setSelectedIndex(0);
//cboTrep.setSelectedIndex(0//
//------------------------
limpiarTabla(jTable1);
//limpiarTabla(jTable2);
//----
codSeg=null;
codVeh=null;
}

  private void limpiarTabla(javax.swing.JTable tabla){
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount() != 0)
            modelo.removeRow(0);
}
  
  private void save(int tipo_de_save){
      switch(tipo_de_save){
          case 1 :{//cab
              if(bandera==3)cod=aop;
                  int[]tipo_de_cst={1,1,1,1,1,1,1};
 String[]parametros={String.valueOf(bandera),cod,codSeg,codVeh,txtCodCli.getText().trim(),Login.sucCod,txtSin.getText().trim()};
 DB.executeCst("CALL abm_prep(?,?,?,?,?,?,?)", tipo_de_cst, parametros, false);
 break;
          }
          case 2 :{//rep
             // LoadWithObjects obj=(LoadWithObjects) cboRep.getSelectedItem();
              int org=0;
              //if(chkOrg.isSelected())org=1;
                  int[]tipo_de_cst={1,1,1,1,4,1};
 String[]parametros={String.valueOf(ban_rep),codSeg,txtCod.getText(),null,String.valueOf(org),String.valueOf( (int)(Integer.parseInt(null)*0.3)+(Integer.parseInt(null)))};
 DB.executeCst("CALL abm_dprep1(?,?,?,?,?,?)", tipo_de_cst, parametros, false);
 break;
          }
          case 3 :{//pie
                LoadWithObjects dam=(LoadWithObjects) cboDam.getSelectedItem();
                LoadWithObjects2Data pza=(LoadWithObjects2Data) cboPza.getSelectedItem();
//DB.execute("CALL abm_dprep2('"+ban_pza+"','"+codSeg+"','"+cod+"','"+dam.getCod()+"','"+pza.getCod()+"','','"+pza.getCant()+"')", false);  
              break;
          }
          case 4 :{//trab
               LoadWithObjects trab=(LoadWithObjects) cboTrab.getSelectedItem();
           //  DB.execute("CALL abm_dprep3('"+ban_trab+"','"+codSeg+"','"+cod+"','"+trab.getCod()+"','"+txtPre2.getText()+"')", false); 
              break;
          } 
      }
  }

  private void totalizaciones(){
//piezas
              int pzas=0;int precio_por_pza=Integer.parseInt(null),n=jTable1.getRowCount(),daño=1;
              System.out.print("\n ** suma="+pzas+" precio * pieza="+precio_por_pza+" cantidad de rows="+n+" count "+jTable1.getRowCount());
              if(n>0){
                  while(n!=0){
//                      if(jTable1.getValueAt(jTable2.getSelectedRow(),1).toString().equals("LEVE")){
//                          daño=1;
//                      }else{
////                          if(jTable1.getValueAt(jTable2.getSelectedRow(),1).toString().equals("MODERADO")){
////                              daño=2;
////                          }else{
////                              daño=3;
////                          }
//                      }
               pzas=pzas+(precio_por_pza*daño);
               System.out.print("\n ** suma="+pzas+" precio * pieza="+precio_por_pza+" cantidad de rows="+n);
               n--;
                  }
                 // txtTotalChapPin.setText(String.valueOf(pzas));
              }
             //--------- 
          
          //repuestos
            //  int rep=0,n1=jTable2.getRowCount();
            //  double porcentaje_utilidad=0.3;
            //  if(n>0){
               //   while(n1!=0){
                //     rep=rep+ (int)(Integer.parseInt(jTable1.getValueAt(n-1,2).toString())*porcentaje_utilidad);
                 //     n--;
                // }
                 // txtTotalRep.setText(String.valueOf(rep));
            ///  }
//--------------
          //trabajos de terceros
          // int trab=0,n2=jTable6.getRowCount();
           //   if(n>0){
            //      while(n2!=0){
             //        trab=trab+ (int)(Integer.parseInt(jTable6.getValueAt(n-1,1).toString())*porcentaje_utilidad);
             //         n--;
             //     }
            //      txtTotalTrab.setText(String.valueOf(trab));
            //  }
         //-------------
         // txtTotal.setText(String.valueOf(pzas+rep+trab));
         // txtIva.setText(String.valueOf((int)(Integer.parseInt(txtTotal.getText())*0.1)));//iva 10
             // txtTotal.setText(String.valueOf(pzas));
}
}

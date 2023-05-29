
package Mod_Servicio;

import threads.CboThreadGT;
import threads.CboThreadGT2;
import threads.TblThread;
import frames.Buscador;
import frames.Login;
import frames.Menu;
import static frames.Menu.jDesktopPane1;
import frames.Tipo_Prep;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import config.LoadWithObjects2Data;
import config.conectarDB;
import javax.swing.JTable;
import javax.swing.JTextField;
import referenciales.Ref_Asegurados;

/**
 *
 * @author schafler92
 */
public class Gen_Presupuesto extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    int bandera,ban_rep,ban_pza,ban_trab;
    String aop,cod,orden="1 DESC",bus="cli_ci",see="",codVeh,codSeg,year,mes,suc;
    DefaultTableModel modeloRep, modeloPzas;
    boolean first;
    
    public Gen_Presupuesto() {
        initComponents();
        DB = new conectarDB();
        ManageButtons(1);
        cargarTabla(); 
        cargarAño();
        modeloPzas=(DefaultTableModel)jTable1.getModel();
        modeloRep=(DefaultTableModel)jTable6.getModel();
    }
 private void cargarTabla(){
     if(!txtBuscar.getText().trim().isEmpty()){
            limpiarTabla(jTable3);
            int[] tipo_de_pst={0}; 
        String[] par={""}; 
  new TblThread("SELECT * FROM v_prep WHERE MONTH(prep_date)=MONTH(CURDATE()) "+see+" "+suc+" ORDER BY "+orden,jTable3,tipo_de_pst,par,DB.con,this,new int[]{}).start();
     }
 }
 
   private void cargarAño(){
      cboAño.removeAllItems();
      cboAño.addItem("Este Año");
      cboAño.addItem("Año Pasado");
      int current_year=Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
      while(current_year!=2015){
          cboAño.addItem(String.valueOf(current_year));
          current_year--;
      }
  }
 
 private void cargarEdicionRep( javax.swing.JTable tabla, String parametro){
        limpiarTabla(tabla);
        int[] tipo_de_pst={1}; 
        String[] par={parametro}; 
       new TblThread("SELECT rep_des,det_org,det_pre FROM v_dprep1 WHERE prep_cod=?" , tabla, tipo_de_pst,par,DB.con,this,new int[]{}).start();
 }
 
  private void cargarEdicionTrab( javax.swing.JTable tabla, String parametro){
        limpiarTabla(tabla);
        int[] tipo_de_pst={1}; 
        String[] par={parametro}; 
       new TblThread("SELECT trab_des,det_pre FROM v_dprep3 WHERE prep_cod=?" , tabla, tipo_de_pst,par,DB.con,this,new int[]{}).start();
 }
       
 private void cargarEdicionPzas( javax.swing.JTable tabla, String parametro){
        limpiarTabla(tabla);
        int[] tipo_de_pst={1}; 
        String[] par={parametro}; 
       DB.FillTable("SELECT pie_des,dam_des,det_precio FROM v_dprep2 WHERE prep_cod=?" , tabla, tipo_de_pst,par);
 }

 /*if(cboVeh.getSelectedIndex()>0){
    LoadWithObjects2Data obj=(LoadWithObjects2Data) cboVeh.getSelectedItem();
            codVeh=obj.getCod();
            codSeg=obj.getCant();//codigo de seguro
    txtSin.requestFocus();
}*/
       
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
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSuc = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        txtVeh = new javax.swing.JTextField();
        btnSeeVeh = new javax.swing.JButton();
        txtCodVeh = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cboSeg = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        txtSin = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotalChapPin = new javax.swing.JTextField();
        txtIva = new javax.swing.JTextField();
        txtTotalString = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTotalTrab = new javax.swing.JTextField();
        txtPRE = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cboPza = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cboDam = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cboTrab = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        btnAproveDet = new javax.swing.JButton();
        btnNullDet = new javax.swing.JButton();
        btnEditDet = new javax.swing.JButton();
        txtCant2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btnAprobAllDet = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

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

        setTitle("Generar Presupuesto");
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Presupuesto"));

        jLabel2.setText("Vehiculo");

        jLabel1.setText("Pres. Nº");

        txtCod.setEditable(false);
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("Asegurado");

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
        txtCli.setEnabled(false);

        jLabel3.setText("Fecha");

        txtDate.setEnabled(false);

        jLabel4.setText("Sucursal");

        txtSuc.setEnabled(false);

        jLabel26.setText("-");

        txtTipo.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtTipo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTipo.setEnabled(false);

        txtVeh.setEnabled(false);

        btnSeeVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btnSeeVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeVehActionPerformed(evt);
            }
        });

        txtCodVeh.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCodVeh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodVehKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodVehKeyReleased(evt);
            }
        });

        jLabel22.setText("Cia. de Seguro");

        cboSeg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));

        jLabel10.setText("Siniestro Nº");

        txtSin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSinKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtSin, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCodCli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(txtCod, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodVeh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCli)
                                    .addComponent(txtVeh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeeVeh)
                                .addGap(47, 47, 47)))))
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
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtCodCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSeeVeh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cboSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtSin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Totalizacion"));

        jLabel13.setText("IVA 10%");

        jLabel14.setText("Son Gs.");

        txtTotalChapPin.setEditable(false);
        txtTotalChapPin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalChapPin.setText("0");
        txtTotalChapPin.setEnabled(false);

        txtIva.setEditable(false);
        txtIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIva.setText("0");
        txtIva.setEnabled(false);

        txtTotalString.setEditable(false);
        txtTotalString.setEnabled(false);

        jLabel8.setText("Total Chap. y Pin.");

        jLabel24.setText("Total Trab. Terceros");

        txtTotalTrab.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalTrab.setText("0");
        txtTotalTrab.setEnabled(false);

        txtPRE.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPRE.setText("385000 demo");
        txtPRE.setEnabled(false);

        jLabel21.setText("Precio por Pieza");

        txtTotal.setEditable(false);
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setEnabled(false);

        jLabel12.setText("Total General");

        jLabel18.setText("Gs. IVA incluido");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTotalChapPin, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel24))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtPRE, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTotalTrab, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtTotalString, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalChapPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel24)
                    .addComponent(txtTotalTrab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtPRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTotalString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel15.setText("Pieza");

        cboPza.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboPza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPzaActionPerformed(evt);
            }
        });

        jLabel9.setText("Daño");

        cboDam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboDam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDamActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(cboPza, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cboDam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cboPza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cboDam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Piezas a ser Reparadas", jPanel5);

        jLabel23.setText("Trabajo");

        cboTrab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trabajo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setResizable(false);
            jTable6.getColumnModel().getColumn(0).setPreferredWidth(250);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(cboTrab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cboTrab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Trabajos de Terceros", jPanel7);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Botones de Gestión"));

        btnAproveDet.setText("Aprobar");
        btnAproveDet.setEnabled(false);
        btnAproveDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAproveDetActionPerformed(evt);
            }
        });

        btnNullDet.setText("Rechazar");
        btnNullDet.setEnabled(false);
        btnNullDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNullDetActionPerformed(evt);
            }
        });

        btnEditDet.setText("Editar Cant.");
        btnEditDet.setEnabled(false);
        btnEditDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditDetActionPerformed(evt);
            }
        });

        txtCant2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCant2.setEnabled(false);
        txtCant2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCant2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCant2KeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("<ENTER>");

        btnAprobAllDet.setText("Aprob. Todos");
        btnAprobAllDet.setEnabled(false);
        btnAprobAllDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAprobAllDetActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtCant2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAproveDet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNullDet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditDet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAprobAllDet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnAproveDet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNullDet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditDet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCant2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAprobAllDet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Cia. de Seguro", "Asegurado", "Sucursal", "Fecha", "Siniestro Nº"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(4).setResizable(false);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTable3.getColumnModel().getColumn(5).setResizable(false);
            jTable3.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Ordenar Por");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo Desc.", "Cia. de Seguro Asc.", "Asegurado", "Vehiculo" }));

        jLabel7.setText("Buscar Por");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asegurado" }));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pieza", "Daño", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trabajo por parte de Terceros"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(623, 623, 623))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_edit.png"))); // NOI18N
        jButton1.setText("Actualizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnExit)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtCodCliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodCliKeyPressed
if(evt.getKeyCode()==10){    
//DB.consultPstSimple("SELECT cli_ci,cli FROM v_cli WHERE cli_ci=?", txtCodCli,txtCli,this);
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
    if(!txtSin.getText().isEmpty() && !txtCli.getText().isEmpty() && cboSeg.getSelectedIndex()>0 && !txtVeh.getText().isEmpty()){
            save(1);
//            new CboThreadGT("SELECT trep_cod,trep_des FROM v_trep ORDER BY 2 ASC", cboTrep, DB.con,"Elegir").start();
            new CboThreadGT("SELECT * FROM v_dam ORDER BY 2 ASC", cboDam, DB.con,"Elegir").start();
            new CboThreadGT("SELECT * FROM v_trab ORDER BY 2 ASC", cboTrab, DB.con,"Elegir").start();
            new CboThreadGT2("SELECT * FROM v_pzas ORDER BY 2 ASC", cboPza, DB.con).start();
            cboPza.requestFocus();
            txtCodCli.setEnabled(false);
            txtSin.setEnabled(false);
            btnNewCli.setEnabled(false);
            btnNewVeh.setEnabled(false);
            btnSeeCli.setEnabled(false);
            cboSeg.setEnabled(false);
            txtCodVeh.setEnabled(false);
}else{
        JOptionPane.showMessageDialog(this,"Debe completar los campos Obligatorios(*) para proceder","Atención", JOptionPane.WARNING_MESSAGE);
        
    }
}
    }//GEN-LAST:event_txtSinKeyPressed

    private void cboPzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPzaActionPerformed
if(cboPza.getSelectedIndex()>0)cboDam.requestFocus();

    }//GEN-LAST:event_cboPzaActionPerformed

    private void cboDamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDamActionPerformed
if(cboDam.getSelectedIndex()>0 && cboPza.getSelectedIndex()>0){
                if(first){//---------------wtf
                  limpiarTabla(jTable1);   
                  first=false;
                 }//------------------------fin wtf
         int resultado=validarDuplicados(cboPza.getSelectedItem().toString(),jTable1);
         if(resultado!=-1){//hay duplicados 
// jTable1.setValueAt(Double.parseDouble(jTable1.getValueAt(resultado, 0).toString())+
  //       Double.parseDouble(txtCant.getText().trim()),resultado, 0);
             JOptionPane.showMessageDialog(this,"Ya existe un detalle con dicha Pieza","Atención", JOptionPane.WARNING_MESSAGE ); 
         }else{//no hay duplicados
modeloPzas.addRow(new Object[]{});    
         }

    cboPza.setSelectedIndex(0);
    cboDam.setSelectedIndex(0);
    cargarEdicionPzas(jTable1,cod);
//    totalizaciones();
    cboPza.requestFocus();   
}
    }//GEN-LAST:event_cboDamActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
int rows=0;
rows=rows+jTable2.getRowCount()+jTable1.getRowCount()+jTable6.getRowCount();
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
}
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed
aop=jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString();
ManageButtons(2);
    }//GEN-LAST:event_jTable3MousePressed

    private void txtCodCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodCliKeyTyped
DB.fieldControl(2, evt);
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
new CboThreadGT("SELECT seg_cod,seg_des FROM v_seg ORDER BY 2 ASC", cboSeg, DB.con,"Particular").start();
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
//    totalizaciones();
    cboPza.requestFocus();
    }//GEN-LAST:event_menNullPzasActionPerformed

    private void menNullRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menNullRepActionPerformed
        ban_rep=3;
        save(2);
        cargarEdicionRep(jTable2,cod);
  //  totalizaciones();
    }//GEN-LAST:event_menNullRepActionPerformed

    private void menNullTrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menNullTrabActionPerformed
        ban_trab=3;
        save(4);
        cargarEdicionTrab(jTable6,cod);
    cboTrab.requestFocus();
    //totalizaciones();
    }//GEN-LAST:event_menNullTrabActionPerformed

    private void btnSeeVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeVehActionPerformed
  btnSeeVeh.setEnabled(false);
          Object[][] cbo= new Object[2][2];
cbo[0][0]="Nº de Matricula";cbo[0][1]="veh_cha";
cbo[1][0]="Nº de Chassis";cbo[1][1]="veh_chass";

        Buscador b = new Buscador(null,true,
        "SELECT veh_cod,veh_chass,CONCAT(mar_des,' ',mod_des,' - ',col_des)AS veh "
        + "FROM v_veh ",
        new String[]{"Codigo","Chassis Nº","Vehiculo"},
        new Object[]{txtCodCli,txtCli},DB.con,cbo," est_cod='VIG'");
b.setTitle("Buscador de Vehiculos");
b.setVisible(true);

    }//GEN-LAST:event_btnSeeVehActionPerformed

    private void txtCodVehKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodVehKeyPressed
if(evt.getKeyCode()==10){
        txtSuc.setEnabled(false);
     DB.consultPst("SELECT ped_cod,CONCAT(suc_cod,' - ',suc_des)AS suc,concat('Pedido realizado por ',emp,' en fecha ',date_) FROM v_ped WHERE ((est_cod='APR') OR (est_cod='ORD.P')) AND ped_cod=?",
            new int[]{1},new String[]{txtCodVeh.getText().trim()},new JTextField[]{txtCodVeh,txtSuc,txtVeh});
      if(!txtVeh.getText().isEmpty()){
 
        }else{

      }   
     
    }
    }//GEN-LAST:event_txtCodVehKeyPressed

    private void txtCodVehKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodVehKeyReleased
DB.toUpperCaseState(txtCodVeh, evt);
    }//GEN-LAST:event_txtCodVehKeyReleased

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10)cargarTabla();
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
       if(txtBuscar.getText().trim().isEmpty()){
           limpiarTabla(jTable3);
       }else{
           DB.toUpperCaseState(txtBuscar, evt);
       }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnAproveDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAproveDetActionPerformed
        if(jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog( this,"No seleccionó ningún Registro","Atención", JOptionPane.INFORMATION_MESSAGE);
        }else{
         //   det_ban[jTable1.getSelectedRow()]=5;

            jTable1.setValueAt("APR",jTable1.getSelectedRow(), 3);
            btnEditDet.setEnabled(false);
            btnAproveDet.setEnabled(false);
            btnNullDet.setEnabled(false);
            btnAprobAllDet.setEnabled(true);
        }
    }//GEN-LAST:event_btnAproveDetActionPerformed

    private void btnNullDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullDetActionPerformed
        if(jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog( this,"No seleccionó ningún Registro","Atención", JOptionPane.INFORMATION_MESSAGE);
        }else{
        //    det_ban[jTable1.getSelectedRow()]=4;
            jTable1.setValueAt("ANU",jTable1.getSelectedRow(), 3);
            btnEditDet.setEnabled(false);
            btnAproveDet.setEnabled(false);
            btnNullDet.setEnabled(false);
            btnAprobAllDet.setEnabled(true);
        }
    }//GEN-LAST:event_btnNullDetActionPerformed

    private void btnEditDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditDetActionPerformed
        //edit-aprove**
        if(jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog( this,"No seleccionó ningún Registro","Atención", JOptionPane.INFORMATION_MESSAGE);
        }else{
            txtCant2.setEnabled(true);
            txtCant2.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
            txtCant2.selectAll();
            txtCant2.requestFocus();
            btnEditDet.setEnabled(false);
            btnAproveDet.setEnabled(false);
            btnNullDet.setEnabled(false);
            btnAprobAllDet.setEnabled(true);
        }
    }//GEN-LAST:event_btnEditDetActionPerformed

    private void txtCant2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCant2KeyPressed
        if(evt.getKeyCode()==10 && bandera==2){//para prevenir un wtf que una vez me salio un det con ban 1 con el estado APROBADO
            Double cant=DB.getDouble(txtCant2.getText().trim());
            if(cant>0.0){
              //  det_ban[jTable1.getSelectedRow()]=7;
                jTable1.setValueAt("APR",jTable1.getSelectedRow(), 3);
                jTable1.setValueAt(cant,jTable1.getSelectedRow(), 0);
            }else{
                JOptionPane.showMessageDialog(this,"No ingresó un valor decimal válido","Atención", JOptionPane.WARNING_MESSAGE );
            }
            btnEditDet.setEnabled(false);
            btnAproveDet.setEnabled(false);
            btnNullDet.setEnabled(false);
            txtCant2.setEnabled(false);
            btnAprobAllDet.setEnabled(true);
            txtCant2.setText("");
        }
        if(evt.getKeyCode()==23){
            btnEditDet.setEnabled(false);
            btnAproveDet.setEnabled(false);
            btnNullDet.setEnabled(false);
            txtCant2.setText("");
            txtCant2.setEnabled(false);
            btnAprobAllDet.setEnabled(true);

        }
    }//GEN-LAST:event_txtCant2KeyPressed

    private void txtCant2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCant2KeyTyped
        DB.fieldControl(7, evt);
    }//GEN-LAST:event_txtCant2KeyTyped

    private void btnAprobAllDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAprobAllDetActionPerformed
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if(jTable1.getValueAt(i, 3).toString().equals("PEN")){
                jTable1.setValueAt("APR",i, 3);
               // det_ban[i]=5;
            }
        }
        btnEditDet.setEnabled(false);
        btnAproveDet.setEnabled(false);
        btnNullDet.setEnabled(false);
        btnAprobAllDet.setEnabled(false);
    }//GEN-LAST:event_btnAprobAllDetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAprobAllDet;
    private javax.swing.JButton btnAproveDet;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEditDet;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnNullDet;
    private javax.swing.JButton btnSave;
    public static javax.swing.JButton btnSeeVeh;
    private javax.swing.JComboBox cboAño;
    private javax.swing.JComboBox cboDam;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboPza;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboSeg;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JComboBox cboTrab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable6;
    private javax.swing.JMenuItem menNullPzas;
    private javax.swing.JMenuItem menNullRep;
    private javax.swing.JMenuItem menNullTrab;
    private javax.swing.JPopupMenu popUpPzas;
    private javax.swing.JPopupMenu popUpRep;
    private javax.swing.JPopupMenu popUpTrab;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCant2;
    public static javax.swing.JTextField txtCli;
    private javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtCodCli;
    private javax.swing.JTextField txtCodVeh;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtPRE;
    private javax.swing.JTextField txtSin;
    private javax.swing.JTextField txtSuc;
    public static javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalChapPin;
    private javax.swing.JTextField txtTotalString;
    private javax.swing.JTextField txtTotalTrab;
    private javax.swing.JTextField txtVeh;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
new Tipo_Prep(null,true).setVisible(true);
ManageButtons(3);
bandera=1;
//cod=DB.getDBcod("prep_cod","v_prep");
txtDate.setText(DB.ClientDate(1));
txtSuc.setText(Login.suc);
txtCod.setText(cod);
txtCodCli.requestFocus(); 
//DB.cargarParametros(txtUti,txtPRE);
//----------------
txtCodCli.setEnabled(true);
txtSin.setEnabled(true);
btnNewCli.setEnabled(true);
btnNewVeh.setEnabled(true);
//btnSeeCli.setEnabled(true);
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
    txtPRE.setText("");
    txtBuscar.setText("");
    txtTotal.setText("0");
    txtTotalChapPin.setText("0");
    txtTotalString.setText("");
    txtIva.setText("0");
    first=true;
//----------------------
    txtBuscar.setText(null);
cboPza.setSelectedIndex(0);
//------------------------
limpiarTabla(jTable1);
limpiarTabla(jTable2);
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
              LoadWithObjects seg=(LoadWithObjects) cboSeg.getSelectedItem();
              codSeg=seg.getCod();
              codVeh=txtCodVeh.getText().trim();
                  int[]tipo_de_cst={1,1,1,1,1,1,1};
 String[]parametros={String.valueOf(bandera),cod,codSeg,codVeh,txtCodCli.getText().trim(),Login.sucCod,txtSin.getText().trim()};
 DB.executeCst("CALL abm_prep(?,?,?,?,?,?,?)", tipo_de_cst, parametros, false);
 break;
          }
          case 2 :{//rep
//              LoadWithObjects obj=(LoadWithObjects) cboRep.getSelectedItem();
//              int org=0;
//              if(chkOrg.isSelected())org=1;
//                  int[]tipo_de_cst={1,1,1,1,4,1};
// String[]parametros={String.valueOf(ban_rep),codSeg,txtCod.getText(),obj.getCod(),String.valueOf(org),String.valueOf( (int)(Integer.parseInt(txtPre.getText().trim())*0.3)+(Integer.parseInt(txtPre.getText().trim())))};
// DB.executeCst("CALL abm_dprep1(?,?,?,?,?,?)", tipo_de_cst, parametros, false);
// break;
          }
          case 3 :{//pie
              LoadWithObjects dam=(LoadWithObjects) cboDam.getSelectedItem();
                LoadWithObjects2Data pza=(LoadWithObjects2Data) cboPza.getSelectedItem();
int[]tipo_de_cst={1,1,1,1,1,5};
String[]parametros={String.valueOf(ban_pza),codSeg,cod,dam.getCod(),pza.getCod(),txtPRE.getText(),pza.getCant()};
DB.executeCst("CALL abm_dprep2(?,?,?,?,?,?,?)", tipo_de_cst, parametros, false);
              break;
          }
          case 4 :{//trab
               LoadWithObjects trab=(LoadWithObjects) cboTrab.getSelectedItem();
int[]tipo_de_cst={1,1,1,1,1};
 String[]parametros={String.valueOf(ban_trab),codSeg,cod,trab.getCod()};
       DB.executeCst("CALL abm_dprep3(?,?,?,?,?)", tipo_de_cst, parametros, false); 
              break;
          } 
      }
  }
  
  private int validarDuplicados(String des, JTable tabla){
      for (int i = 0; i < tabla.getRowCount(); i++) {
          if(tabla.getValueAt(i, 2).toString().equals(des))return i;
             }
      return -1;
  }

 private void totalizaciones(){
//piezas
              int pzas=0;int precio_por_pza=Integer.parseInt(txtPRE.getText()),n=(jTable1.getRowCount())-1,daño=1;
              System.out.print("\n ** suma="+pzas+" precio * pieza="+precio_por_pza+" cantidad de rows="+n+" count "+jTable1.getRowCount());
           //   if(n>0){
                  while(n!=-1){
                      if(jTable1.getValueAt(n,1).toString().equals("LEVE")){
                          daño=1;
                      }else{
                          if(jTable1.getValueAt(n,1).toString().equals("MODERADO")){
                              daño=2;
                          }else{
                              daño=3;
                          }
                      }
               pzas=pzas+(precio_por_pza*daño);
               System.out.print("\n ** suma="+pzas+" precio * pieza="+precio_por_pza+" cantidad de rows="+n);
               n--;
                  }
                  txtTotalChapPin.setText(String.valueOf(pzas));
             // }
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


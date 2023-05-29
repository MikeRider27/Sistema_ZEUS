
package Mod_Compras;

import threads.CboThreadGT;
import threads.TblThread;
import frames.Buscador;
import frames.Login;
import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import config.conectarDB;
import config.PGa_v2;
import config.PGa_vStandard;

/**
 *
 * @author Kuroryuu
 */
public class Remision extends javax.swing.JInternalFrame {
 
     private final conectarDB DB;
    int bandera,isOk;
    String aop,cod,mes="extract(month from (rem_date))=extract(month from current_date)" ,
            year="extract(year from (rem_date))=extract(year from current_date)";
    DefaultTableModel modelo;
    boolean first=true;
    PGa_v2 pga=null;
    PGa_vStandard pga_s=null;
    
    public Remision() {
        initComponents();
        DB = new conectarDB();
        ManageButtons(1);
        DB.getYears(cboYear);
        modelo=(DefaultTableModel)jTable3.getModel();
        pga= new PGa_v2();
        pga_s=new PGa_vStandard();
    }
 
         private void cargarTabla(){
     limpiarTabla(jTable1);
     limpiarTabla(jTable2);
     if(cboSuc.getSelectedIndex()>0){
                    int[] tipo_de_pst={2,2,2}; 
                    String [] par={cboFilter.getSelectedItem().toString(),cboSuc.getSelectedItem().toString(),cboSuc.getSelectedItem().toString()};     
     new TblThread("SELECT distinct r.rem_cod,r.rem_date,r.suc,d.suc as suc_d,r.est_cod from v_rem r,v_drem d "
             + "WHERE r.est_des=? AND (r.suc_des=? OR d.suc_des=?) AND "+mes+" AND "+year+" ORDER BY 1 DESC",
             jTable2, tipo_de_pst,par,DB.con,this,new int[]{1,2,2,2,2}).start();   
     }
 }
     
 private void cargarDetalle(){
     limpiarTabla(jTable1);
     String [] par={aop}; 
     new TblThread("SELECT det_cant,mat,dep_o,dep FROM v_drem WHERE rem_cod=?",jTable1,new int[]{1},par,DB.con,this,new int[]{5,2,2,2}).start();
 }
 
 private boolean esLaSucDestino(){//:OOOOO no recuerdo cuando hice esto
     //System.out.print("TABLE -"+jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString()+"- LOGIN -"+Login.suc+"-");
         return jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString().equals(Login.sucCod+" - "+Login.suc);
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSuc_o = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSuc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmpCi = new javax.swing.JTextField();
        txtEmpDes = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        txtCant = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMat = new javax.swing.JTextField();
        cboDep = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtDep = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtExist = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtStockMin = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox();
        cboMes = new javax.swing.JComboBox();
        cboSuc = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setTitle("Registrar Remision de Materiales");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Imagen2.png"))); // NOI18N
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
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Remisión de Materiales"));

        jLabel1.setText("Codigo");

        txtCod.setEnabled(false);

        jLabel2.setText("Fecha");

        txtDate.setEnabled(false);

        jLabel3.setText("Suc. Origen");

        txtSuc_o.setEnabled(false);

        jLabel4.setText("Suc. Destino");

        txtSuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSucKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSucKeyReleased(evt);
            }
        });

        jLabel9.setText("Encargado");

        txtEmpCi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmpCiKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmpCiKeyTyped(evt);
            }
        });

        txtEmpDes.setEnabled(false);

        btnSearch.setText("...");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCod, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(txtEmpCi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtEmpDes, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch)))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSuc_o, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(txtSuc))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEmpCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(jLabel3)
                    .addComponent(txtSuc_o, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de la Remision de Materiales"));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Material", "Dep. Origen", "Dep. Destino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable3.getColumnModel().getColumn(2).setResizable(false);
        jTable3.getColumnModel().getColumn(3).setResizable(false);

        txtCant.setEnabled(false);
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        jLabel6.setText("Dep. Destino");

        jLabel7.setText("Material");

        jLabel8.setText("Cantidad");

        txtMat.setEnabled(false);
        txtMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKeyReleased(evt);
            }
        });

        cboDep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboDep.setEnabled(false);
        cboDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDepActionPerformed(evt);
            }
        });

        jLabel13.setText("Dep. Origen");

        txtDep.setEnabled(false);

        jLabel14.setText("Existencia");

        txtExist.setEnabled(false);

        jLabel15.setText("Exist. Min.");

        txtStockMin.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboDep, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMat, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtDep, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jLabel14)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtExist, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(txtStockMin))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtStockMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Fecha", "Sucursal Origen", "Sucursal Destino", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Material", "Deposito Origen", "Deposito Destino"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("del");

        cboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Mes", "Mes Pasado", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
            }
        });

        jLabel11.setText("Filtrar por");

        jLabel12.setText("en");

        jLabel5.setText("Ver");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VIG", "PROV", "ANU" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listado", jPanel1);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_add.png"))); // NOI18N
        btnAdd.setText("Agregar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/door-in-icon.png"))); // NOI18N
        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png"))); // NOI18N
        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        btnNull.setText("Anular");
        btnNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNullActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_edit.png"))); // NOI18N
        btnEdit.setText("Recibir");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnExit)
                    .addComponent(btnCancel)
                    .addComponent(btnSave)
                    .addComponent(btnNull)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
    ManageClosing();
}else{
   if (JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       ManageClosing();
   }  
}
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clean();
        ManageButtons(1);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
if(jTable3.getRowCount()>0){
    save();
    clean();
    ManageButtons(1);
    cargarTabla();
}else{
    JOptionPane.showMessageDialog(this,"No se puede Guardar una Remision de Materiales sin Detalles","Error al Guardar", JOptionPane.ERROR_MESSAGE );
}
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
aop=jTable2.getValueAt(jTable2.getSelectedRow(),0).toString();
        ManageButtons(2);
        cargarDatos();
    }//GEN-LAST:event_jTable2MousePressed

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        if(evt.getKeyCode()==10){
            Double cant = DB.getDouble(txtCant.getText().trim());
            LoadWithObjects dep =(LoadWithObjects)cboDep.getSelectedItem();
        if(cant>0.00 || !txtMat.getText().trim().isEmpty()){
            // if(!txtMat.getText().trim().isEmpty()){
                 if(first){//---------------wtf
                  limpiarTabla(jTable3);   
                  first=false;
                 }//------------------------fin wtf
         int resultado=validarDuplicados(txtMat.getText());
         if(resultado!=-1){//hay duplicados 
// jTable1.setValueAt(Double.parseDouble(jTable1.getValueAt(resultado, 0).toString())+
  //       Double.parseDouble(txtCant.getText().trim()),resultado, 0);
             JOptionPane.showMessageDialog(this,"Ya existe un detalle con dicho material","Atención", JOptionPane.WARNING_MESSAGE ); 
         }else{//no hay duplicados
if((Double.parseDouble(txtExist.getText())-Double.parseDouble(txtCant.getText()))>=Double.parseDouble(txtStockMin.getText())){
                 // (existencia-cant a remitir)debe ser > existencia minima
    modelo.addRow(new Object[]{Double.parseDouble(txtCant.getText().trim()),txtMat.getText(),txtDep.getText(),dep.getCod()+" - "+dep.getDes()});    
             }else{
    JOptionPane.showMessageDialog(this,"La cantidad a remitir sobrepasa la Existencia mínima del Material","Atención", JOptionPane.WARNING_MESSAGE );            
}
         }  
                txtCant.setText("");
                txtMat.setText("");
                txtExist.setText("");
                txtDep.setText("");
                txtStockMin.setText("");
                cboDep.setSelectedIndex(0);
                txtMat.requestFocus();
                
            }else{
                  JOptionPane.showMessageDialog(this,"No seleccionó un Material y/o no ingresó un valor entero válido","Atención", JOptionPane.WARNING_MESSAGE );            
                  txtMat.requestFocus();
             }  
        }
    }//GEN-LAST:event_txtCantKeyPressed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
al_editar();    
    }//GEN-LAST:event_btnEditActionPerformed

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void txtMatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKeyPressed
if(evt.getKeyCode()==10){
        if(txtMat.getText().length()>1){
        if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.load(new String[]{"mat_cod","mat_des","dep","cant"},"v_stock_m",null,DB.con);
    pga.autocomplete(txtMat,txtDep,txtExist,cboDep,this);
    }else{
        pga.ocultarauto();
    }
}
    }//GEN-LAST:event_txtMatKeyPressed

    private void txtSucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyPressed
        if(evt.getKeyCode()==10 ){
            if(txtSuc.getText().trim().length()>1){
             if(pga_s.show_jlist){
                pga_s.show_jlist=false;
                pga_s.refresh_();
            }
            pga_s.autocomplete(txtSuc,"suc_cod,' - ',suc_des","v_suc"," AND est_cod='VIG' AND suc_des!='"+txtSuc_o.getText()+"'",this,txtEmpCi,DB.con);   
            }   
        }
    }//GEN-LAST:event_txtSucKeyPressed

    private void txtEmpCiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpCiKeyTyped
DB.fieldControl(2, evt);
    }//GEN-LAST:event_txtEmpCiKeyTyped

    private void txtEmpCiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmpCiKeyPressed
if(evt.getKeyCode()==10){
       DB.consultPst("SELECT emp_ci,concat(emp_nom,', ',emp_ape)as emp FROM v_func WHERE est_cod='VIG' AND emp_ci=?",
            new int[]{1},new String[]{txtEmpCi.getText().trim()},new JTextField[]{txtEmpCi,txtEmpDes});
      if(!txtEmpDes.getText().isEmpty()){
           new CboThreadGT("SELECT dep_cod,dep_des FROM v_dep WHERE suc_des='"+DB.getDes(txtSuc.getText())+"' ORDER BY 2 ASC", cboDep, DB.con, "Elegir").start();
             txtSuc.setEnabled(false);
             txtEmpCi.setEnabled(false);
             btnSearch.setEnabled(false);
             txtMat.requestFocus();
        }else{
txtEmpCi.setText("");
txtEmpDes.setText(""); 
txtEmpCi.requestFocus();
      }   
            if(!txtEmpDes.getText().trim().isEmpty() && !txtSuc.getText().isEmpty()){
                txtEmpCi.setEnabled(false);
                txtSuc.setEnabled(false);
                txtMat.setEnabled(true);
                cboDep.setEnabled(true);
                txtCant.setEnabled(true);
                txtMat.requestFocus();
            }  
}
    }//GEN-LAST:event_txtEmpCiKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
Object[][] cbo= new Object[4][2];
    cbo[0][0]="Nº de Cedula";cbo[0][1]="cli_ci";
    cbo[1][0]="Nombres";cbo[1][1]="cli_nom";
    cbo[2][0]="Apellidos";cbo[2][1]="cli_ape";
    cbo[3][0]="Nombres y Apellidos";cbo[3][1]="";

        Buscador b = new Buscador(null, true,
        "SELECT emp_ci,concat(emp_nom,', ',emp_ape) FROM v_func", 
        new String[]{"Cédula Nº","Asegurado"},
        new Object[]{txtEmpCi,txtEmpDes}, DB.con,cbo,"est_cod='VIG'");
b.setTitle("Buscador de Funcionarios");
b.setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void cboDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDepActionPerformed
if(cboDep.getSelectedIndex()>0){
    DB.consultPstSimple("SELECT concat(mat_cod,' - ',mat_des)as mat,"
            + "mat_min FROM v_mat WHERE est_cod='VIG' and mat_cod=?",txtMat,txtStockMin);
}
    }//GEN-LAST:event_cboDepActionPerformed

    private void txtSucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyReleased
DB.toUpperCaseState(txtSuc, evt);
    }//GEN-LAST:event_txtSucKeyReleased

    private void txtMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKeyReleased
DB.toUpperCaseState(txtMat, evt);
    }//GEN-LAST:event_txtMatKeyReleased

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
DB.fieldControl(7, evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
if(Integer.parseInt(Login.sucCod)<=4){
    new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE est_cod='VIG' ORDER BY 2 ASC", cboSuc, DB.con,"Elegir").start();
}else{
    cboSuc.removeAllItems();
    cboSuc.addItem("Elegir");
    cboSuc.addItem("Esta Sucursal");
}
    }//GEN-LAST:event_formInternalFrameOpened

    private void cboSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSucActionPerformed
cargarTabla();
    }//GEN-LAST:event_cboSucActionPerformed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
     if(jTabbedPane1.getSelectedIndex()==0 ){
    pga.ocultarauto();
}
        if(jTabbedPane1.getSelectedIndex()==0){
     pga.ocultarauto();
}
    }//GEN-LAST:event_formComponentMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cboDep;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboMes;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JComboBox cboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDep;
    private javax.swing.JTextField txtEmpCi;
    private javax.swing.JTextField txtEmpDes;
    private javax.swing.JTextField txtExist;
    private javax.swing.JTextField txtMat;
    private javax.swing.JTextField txtStockMin;
    private javax.swing.JTextField txtSuc;
    private javax.swing.JTextField txtSuc_o;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
bandera=1;
cod=DB.getDBcod("rem_cod","v_rem");
txtCod.setText(cod);
txtSuc_o.setText(Login.suc);
txtDate.setText(DB.ClientDate(1));
ManageButtons(3);
txtSuc.requestFocus();
}

private void al_editar(){
    bandera=2;
    if (JOptionPane.showConfirmDialog(this,"¿Desea marcar como Recibido este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        save();
    }
clean();
cargarTabla();
ManageButtons(1);
}

private void al_nullear(){
        bandera=3;
if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){    
                save();         
        }
clean();
cargarTabla();
ManageButtons(1);
}

       private void ManageButtons(int estado){
     if(estado==1){//boot
         btnAdd.setEnabled(true);
         btnAdd.requestFocusInWindow();
         btnNull.setEnabled(false);
         btnEdit.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancel.setEnabled(false);
         jTabbedPane1.setSelectedIndex(1);
     }
          if(estado==2){//pressed
         btnAdd.setEnabled(false);
//         System.out.print("ww"+esLaSucDestino()+"ww "+jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
         if(esLaSucDestino() && jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString().equals("VIG")){//:OOOOOOOOOOOOOOOOOOO
             
             btnEdit.setEnabled(true);
         }else{
             btnEdit.setEnabled(false);
         }
         if(!jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString().equals("ANU")){             
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
         btnEdit.setEnabled(false);
         jTabbedPane1.setSelectedIndex(0);
     }
 }

private void clean(){
    cboSuc.setSelectedIndex(0);
    txtCod.setText("");
    txtSuc.setEnabled(true);
    txtSuc.setText("");
    txtEmpCi.setEnabled(true);
    txtEmpCi.setText("");
    txtEmpDes.setText("");
    btnSearch.setEnabled(true);
    cboDep.setEnabled(false);
    txtCant.setEnabled(false);
    txtMat.setEnabled(false);
    txtCant.setText("");
    txtSuc_o.setText("");
    txtDate.setText("");
    txtDep.setText("");
    txtStockMin.setText("");
    txtExist.setText("");
    aop=null;
    limpiarTabla(jTable3);
    limpiarTabla(jTable2);
    limpiarTabla(jTable1);
    first=true; 
        if(pga.popup!=null){
         pga.show_jlist=false;
         pga.refresh_();   
    }
}

  private int validarDuplicados(String des){
      for (int i = 0; i < jTable3.getRowCount(); i++) {
          if(jTable3.getValueAt(i, 1).toString().equals(des))return i;
             }
      return -1;
  }

  private void limpiarTabla(JTable tabla){
        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount()!=0)modelo.removeRow(0);
}
  
  private void cargarDatos(){
//txtDate.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
//txtSuc_o.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
//txtSuc.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
      aop=jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString();
      txtCod.setText(aop);
cargarDetalle();
}
  
   private void save(){
      String[][]par;
      String cod=this.cod;
      int[] cabecera= {1,1,1,1};
      int[] detalle=  {1,1,1,1,1,1,1,5,2};
      JTable tabla=jTable3;
if(bandera==2 || bandera==3){
    cod=aop;
    tabla=jTable1;
}
                par=gestionarDetalles(tabla);
                isOk=DB.executeCst("abm_rem(?,?,?,?)",cabecera,new String[]{String.valueOf(bandera),cod,Login.sucCod,Login.empCi},false); 
                isOk=isOk+DB.executeCstDetails("abm_drem(?,?,?,?,?,?,?,?::numeric,?)",detalle, par);           
if(isOk==0){
    DB.commit();
    DB.EmitirMensaje(this, bandera);
}     
      clean();
      cargarTabla();
      ManageButtons(1);
}
  
  private String[][] gestionarDetalles(JTable tabla){//inserta o anula registros
      int row;
      String[][] par;
      String cod,suc,suc_o,estado="8";
      row=tabla.getRowCount();
      par = new String [row][9];
      if(bandera==1){               
                 cod=txtCod.getText();
                 suc=DB.getCod(txtSuc.getText());
                 suc_o=Login.sucCod;
                 estado="1";//realmente no importa este, estado solo se usa para anulacion
      }else{
                 cod=aop;  
                 suc=DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());//suc
                 suc_o=DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString());//suc_o
                 estado=jTable2.getValueAt(jTable2.getSelectedRow(),4).toString();//obtiene el estado ANTES de anularse/modificarse
      }
              for (int i = 0; i < row; i++) {//extrae datos del jtable
          par[i][0]=String.valueOf(bandera);
          par[i][1]=cod;
          par[i][2]=DB.getCod(tabla.getValueAt(i, 1).toString());//mat
          par[i][3]=DB.getCod(tabla.getValueAt(i, 3).toString());//dep
          par[i][4]=suc;
          par[i][5]=suc_o;
          par[i][6]=DB.getCod(tabla.getValueAt(i, 2).toString());//dep_o
          par[i][7]=tabla.getValueAt(i, 0).toString();//cant_
          par[i][8]=estado;//cant_
             }
            return par;
  }
  
  private void ManageClosing(){
      pga.ocultarauto();
      DB.shutdown();
      dispose();
      Menu.menRem.setEnabled(true);
  }
}

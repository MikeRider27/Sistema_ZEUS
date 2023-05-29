

package Mod_Compras;

import threads.CboThreadGT;
import threads.CboThreadGT2;
import threads.TblThread;
import frames.Login;
import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects2Data;
import config.PGa_v2;
import config.conectarDB;

/**
 *
 * @author schafler92
 */
public class Ajustes extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    int ban,isOk=0;
    int[]det_ban=null;
    boolean first=true;
    PGa_v2 pga=null;
    DefaultTableModel modelo;
    String aop,cod,mes="extract( month from (aju_date))=extract( month from current_date )" ,
            year="extract( year from (aju_date))=extract(year from current_date )",suc="AND d.suc_des='"+Login.suc+"'",estate="a.est_cod='VIG'";
    
    
    public Ajustes() {
        initComponents();
        DB = new conectarDB();
        modelo=(DefaultTableModel)jTable2.getModel();
        ManageButtons(1);
        DB.getYears(cboYear);
        pga=new PGa_v2();
    }

     private void cargarTabla(){
     limpiarTabla(jTable1);
     limpiarTabla(jTable2);
     limpiarTabla(jTable3);
     if(cboSee.getSelectedIndex()==0){
         estate="a.est_cod='VIG'";
     }else{
         estate="a.est_cod='ANU'";
     }
     if(cboSuc.getSelectedIndex()>0){
           int[] tipo_de_pst={2}; 
     String [] par={cboSuc.getSelectedItem().toString()}; 
     new TblThread("SELECT DISTINCT a.aju_cod,a.aju_date,d.suc,a.est_cod FROM v_ajum a,v_dajum d"
             + " WHERE d.suc_des=? AND "+estate+" AND "+mes+" AND "+year+" ORDER BY 1 DESC", jTable1, tipo_de_pst,par,DB.con,this,new int[]{1,2,2,2}).start();   
     }
 }
     
 private void cargarDetalle(){
     limpiarTabla(jTable3);
     String [] par={aop}; 
     DB.FillTable("SELECT mat,suc,dep,mot,tmot_cod,cant FROM v_dajum WHERE aju_cod=? ORDER BY 1 ASC", jTable3,new int[]{1},par);
 }
 
 private boolean hayDetallesOpuestos(String tipo_de_ajuste){
    for (int i = 0; i < jTable2.getRowCount(); i++) {
         if(jTable2.getValueAt(i, 0).toString().equals(txtMat.getText())){
               if(!jTable2.getValueAt(i, 1).toString().equals(tipo_de_ajuste)){//pregunta si no es igual al t. de ajuste
                   return true;                                     //ingresado ahora, si no es igual, es un ajuste
               }                                                    //opuesto, lo que lleva a inconsistencia
            }                                                       //de datos en la bd
        }
return false;
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        cboMot = new javax.swing.JComboBox();
        txtDep = new javax.swing.JTextField();
        txtMat = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSuc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        cboSuc = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setTitle("Ajustes del Stock de Materiales");
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del Ajuste de Stock"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Sucursal", "Deposito", "Motivo de Ajuste", "T. de Ajuste", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setFocusable(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable2.getColumnModel().getColumn(4).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable2.getColumnModel().getColumn(5).setResizable(false);
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(50);

        jLabel8.setText("Material");

        jLabel4.setText("Motivo");

        jLabel6.setText("Cantidad");

        jLabel14.setText("Deposito");

        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        cboMot.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboMot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMotActionPerformed(evt);
            }
        });

        txtDep.setEnabled(false);

        txtMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMatKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboMot, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDep, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMat)
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboMot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel14)
                    .addComponent(txtDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Ajuste de Stock"));

        jLabel1.setText("Codigo");

        txtCod.setEnabled(false);

        jLabel2.setText("Fecha");

        txtDate.setEnabled(false);

        jLabel3.setText("Sucursal");

        txtSuc.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Fecha", "Sucursal", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(3).setResizable(false);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Sucursal", "Depósito", "Motivo de Ajuste", "T. de Ajuste", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setFocusable(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable3);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable3.getColumnModel().getColumn(1).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable3.getColumnModel().getColumn(2).setResizable(false);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable3.getColumnModel().getColumn(3).setResizable(false);
        jTable3.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTable3.getColumnModel().getColumn(4).setResizable(false);
        jTable3.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable3.getColumnModel().getColumn(5).setResizable(false);
        jTable3.getColumnModel().getColumn(5).setPreferredWidth(50);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Ver");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VIG", "ANU" }));
        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        jLabel11.setText("Filtrar Por");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Mes", "Mes Pasado", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel12.setText("de");

        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });

        jLabel13.setText("en");

        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
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
                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(btnAdd)
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
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
    ManageClosing();
}else{
   if (JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       ManageClosing();
   }  
}
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
DB.rollback();
        clean();
        ManageButtons(1);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
if(jTable2.getRowCount()>0){
    save();
    //if(DB.commit())DB.EmitirMensaje(this, ban);
    clean();
    ManageButtons(1);
    cargarTabla();
}else{
    JOptionPane.showMessageDialog(this,"No se puede guardar un Ajuste sin detalles","Atención", JOptionPane.WARNING_MESSAGE );
   // DB.rollback();
}
    }//GEN-LAST:event_btnSaveActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
txtCod.setText(aop);
ManageButtons(2);
cargarDatos();
    }//GEN-LAST:event_jTable1MousePressed

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
if(evt.getKeyCode()==10){
    if(cboMot.getSelectedIndex()!=0 && !txtMat.getText().trim().isEmpty()){
        LoadWithObjects2Data mot =(LoadWithObjects2Data)cboMot.getSelectedItem();
    if(hayDetallesOpuestos(mot.getCant())){
        JOptionPane.showMessageDialog(this,"No debe existir detalles contradictorios para un mismo Material", "Atención", JOptionPane.WARNING_MESSAGE);
        txtCant.setText("");
    txtMat.setText("");
    txtDep.setText("");
    cboMot.setSelectedIndex(0);
    cboMot.requestFocus();
    }else{
                 int resultado=validarDuplicados(txtMat.getText());
         if(resultado!=-1){//hay duplicados (parsea y suma los datos para resumirlos en un registro)
 //jTable1.setValueAt(Double.parseDouble(jTable2.getValueAt(resultado, 4).toString())+
     //    Double.parseDouble(txtCant.getText().trim()),resultado, 0);
         }else{//no hay duplicados
            // LoadWithObjects2Data tmot =(LoadWithObjects2Data)cboMot.getSelectedItem();
  if(mot.getCant().equals("1") && Double.parseDouble(DB.consultPst("SELECT cant FROM v_stock_m WHERE mat_cod='"+DB.getCod(txtMat.getText())+"'"))//comprueba 
          <Double.parseDouble(txtCant.getText().trim())){
      this.getToolkit().beep();
      JOptionPane.showMessageDialog(this,"La cantidad ingresada sobrepasa la existencia actual de dicho material", "Atención", JOptionPane.WARNING_MESSAGE);
  }else{
      //falta validar en el caso de positivo verificar si existio alguna vez en stock dicho mat
                 if(first){//---------------wtf
                  limpiarTabla(jTable2);   
                  first=false;
                 }//------------------------fin wtf
          modelo.addRow(new Object[]{txtMat.getText(),txtSuc.getText(),txtDep.getText(),mot.getCod()+" - "+mot.getDes(),mot.getCant(),Double.parseDouble(txtCant.getText().trim())});       
          txtCant.setText("");
    txtMat.setText("");
    txtDep.setText("");
    cboMot.setSelectedIndex(0);
    cboMot.requestFocus();
         }
         }
    }
    }else{
     JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder", "Atención", JOptionPane.WARNING_MESSAGE);
    }
}
    }//GEN-LAST:event_txtCantKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE est_cod='VIG' ORDER BY 2 ASC", cboSuc, DB.con,"Elegir").start();
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
DB.fieldControl(7,evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void cboSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSucActionPerformed
cargarTabla();
    }//GEN-LAST:event_cboSucActionPerformed

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
cargarTabla();
    }//GEN-LAST:event_cboSeeActionPerformed

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0){//este mes
    mes="extract(month from (aju_date))=extract(month from current_date)";
}else{
    if(cboFilter.getSelectedIndex()==1){//mes anterior
        mes="extract(month from (aju_date))=extract(month from current_date)-1";
    }else{
     mes="extract(month from (aju_date))="+String.valueOf(cboFilter.getSelectedIndex()-1);//mes por int 1/12  
    }
    }
cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
if(cboYear.getSelectedIndex()==0)year="extract( year from (aju_date))=extract(year from current_date )";
if(cboYear.getSelectedIndex()==1)year="extract( year from (aju_date))=extract(year from current_date )-1";
if(cboYear.getSelectedIndex()>1)year="extract( year from (aju_date))="+String.valueOf(cboYear.getSelectedIndex()-1);
cargarTabla();
    }//GEN-LAST:event_cboYearActionPerformed

    private void cboMotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMotActionPerformed
txtMat.requestFocus();
    }//GEN-LAST:event_cboMotActionPerformed

    private void txtMatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKeyPressed
if(evt.getKeyCode()==10){
    if(txtMat.getText().length()>1){
        if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.load(new String[]{"mat_cod","mat_des","dep"},"v_stock_m","suc_des='"+Login.suc+"'",DB.con);// :S
    pga.autocomplete(txtMat,txtDep,null,txtCant,this);
    }else{
        pga.ocultarauto();
    }
}
    }//GEN-LAST:event_txtMatKeyPressed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
if(jTabbedPane1.getSelectedIndex()==0){
    pga.ocultarauto();
//    PopupFactory factory = PopupFactory.getSharedInstance();
//popup = factory.getPopup(txtMat, PGa_v2.lista, txtMat.getLocationOnScreen().x, txtMat.getLocationOnScreen().y+txtMat.getHeight());
//if(PGa_v2.popup!=null)PGa_v2.popup.show();
}
    }//GEN-LAST:event_formComponentMoved

    private void txtMatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKeyReleased
DB.toUpperCaseState(txtMat, evt);
if(txtMat.getText().trim().isEmpty())txtDep.setText("");
    }//GEN-LAST:event_txtMatKeyReleased

    private void txtMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKeyTyped
DB.fieldControl(3, evt);
    }//GEN-LAST:event_txtMatKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboMot;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JComboBox cboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDep;
    private javax.swing.JTextField txtMat;
    private javax.swing.JTextField txtSuc;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
    new CboThreadGT2("SELECT mot_cod,CONCAT(mot_des,' - ',tmot_des),tmot_cod FROM v_mot WHERE est_cod='VIG' ORDER BY mot_des ASC", cboMot, DB.con).start();
ban=1;
cod=DB.getDBcod("aju_cod","v_ajum");
txtCod.setText(cod);
txtSuc.setText(Login.sucCod+" - "+Login.suc);
txtDate.setText(DB.ClientDate(1));
ManageButtons(3);
cboMot.requestFocus();
}

private void al_nullear(){
        ban=3;
if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",
        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)save();        
        
clean();
cargarTabla();
ManageButtons(1);
}

       private void ManageButtons(int estado){
     if(estado==1){//boot
         btnAdd.setEnabled(true);
         btnAdd.requestFocusInWindow();
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancel.setEnabled(false);
         jTabbedPane1.setSelectedIndex(1);
     }
          if(estado==2){//pressed
         btnAdd.setEnabled(false);
         btnNull.setEnabled(true);
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
    txtCod.setText("");
    txtDep.setText("");
    txtSuc.setText("");
    txtCant.setText("");
    txtDate.setText("");
    cboMot.setSelectedIndex(0);
  //  txtCant.setEnabled(false);
    aop=null;
    limpiarTabla(jTable3);
    limpiarTabla(jTable2);
    limpiarTabla(jTable1);
            if(pga.popup!=null){
         pga.show_jlist=false;
         pga.refresh_();   
    }
}

  private void limpiarTabla(JTable tabla){
        modelo=(DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount()!=0)modelo.removeRow(0);
}
  
  private void cargarDatos(){
txtDate.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
txtSuc.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
//txtMotDes.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
cargarDetalle();
}
  
    private void save(){
      String[][] par;
      int row;
      if(ban==1){
          //if(ban==2)cod=aop;
          row=jTable2.getRowCount();
          par = new String [row][8];
                    
             for (int i = 0; i < row; i++) {//extrae datos del jtable
          par[i][0]="1";//ban
          par[i][1]=txtCod.getText();//cod
          par[i][2]=DB.getCod(jTable2.getValueAt(i, 0).toString());//mat
          par[i][3]=DB.getCod(jTable2.getValueAt(i,2).toString());//dep
          par[i][4]=DB.getCod(txtSuc.getText());//suc
          par[i][5]=DB.getCod(jTable2.getValueAt(i, 3).toString());//mot
          par[i][6]=jTable2.getValueAt(i, 5).toString();//cant
          par[i][7]=jTable2.getValueAt(i, 4).toString();//tmot
          //par[i][8]=jTable2.getValueAt(i, 3).toString();
             }
//             System.out.print("\nTEST");
//             for (int i = 0; i < row; i++) {
//                 System.out.print("\n"+par[i][0]+" "+par[i][1]+" "+par[i][2]+" "+par[i][3]+" ban"+det_ban[i]);
//             }
//             System.out.print("\nTEST"+Login.sucCod);
          isOk=DB.executeCst("abm_ajum(?,?,?)",new int[]{1,1,1},new String[]{String.valueOf(ban),cod,Login.empCi},false); 
          isOk=isOk+DB.executeCstDetails("abm_dajum(?,?,?,?,?,?,?::numeric,?)", new int[]{1,1,1,1,1,1,5,1}, par); 
//          if(isOk==0)DB.commit();
      }else{//-----------------------------------ANULACION----------------------------------
          row=jTable3.getRowCount();
          par = new String [row][8];
                    
             for (int i = 0; i < row; i++) {//extrae datos del jtable
          par[i][0]="3";//ban
          par[i][1]=aop;//cod
          par[i][2]=DB.getCod(jTable3.getValueAt(i, 0).toString());//mat
          par[i][3]=DB.getCod(jTable3.getValueAt(i, 2).toString());//dep
          par[i][4]=DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());//suc
          par[i][5]=DB.getCod(jTable3.getValueAt(i, 3).toString());//mot
          par[i][6]=DB.getCod(jTable3.getValueAt(i, 5).toString());//cant
          par[i][7]=jTable3.getValueAt(i, 4).toString();//tmot
             }
          isOk=DB.executeCst("abm_ajum(?,?,?)",new int[]{1,1,1},new String[]{String.valueOf(ban),aop,Login.empCi},false);
          isOk=isOk+DB.executeCstDetails("abm_dajum(?,?,?,?,?,?,?::numeric,?)",new int[]{1,1,1,1,1,1,5,1}, par); 
//          if(isOk==0){
//              DB.commit();
//              DB.EmitirMensaje(this, ban);
//          }
      }
      if(isOk==0){
              DB.commit();
              DB.EmitirMensaje(this, ban);
          }
      clean();
      cargarTabla();
      ManageButtons(1);
}
  
  private void ManageClosing(){
      pga.ocultarauto();
      DB.shutdown();
      dispose();
      Menu.menAju.setEnabled(true);
  }
  
    private int validarDuplicados(String des){
      for (int i = 0; i < jTable2.getRowCount(); i++) {
          if(jTable2.getValueAt(i, 0).toString().equals(des))return i;
             }
      return -1;
  }

}

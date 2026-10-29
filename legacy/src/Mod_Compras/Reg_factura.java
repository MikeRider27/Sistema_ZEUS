
package Mod_Compras;

import threads.CboThreadGT;
import threads.TblThread;
import frames.Buscador;
import frames.Login;
import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import config.LoadWithObjects3Data;
import config.PGa_vStandard;
import config.PGa_v2;
import config.conectarDB;

public class Reg_factura extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    DefaultTableModel modelo;
    PGa_v2 pga=null;
    PGa_vStandard pga_s=null;
    int ban,isOk=0,mod;//mod=0 es en base a orden, mod=1 es sin orden
    String aop,cod,ped,prov,tip,
            mes="extract(month from (com_date))=extract(month from current_date)"
            ,year="extract(year from (com_date))=extract(year from current_date)";
    
    public Reg_factura() {
        initComponents();
        DB=new conectarDB(); 
        DB.getYears(cboYear);
        pga=new PGa_v2();
        pga_s=new PGa_vStandard();
        modelo=(DefaultTableModel)jTable2.getModel();
        ManageButtons(1);
       //cargarTabla();
    }
    
 private void cargarTabla(){
     limpiarTabla(jTable1);
     limpiarTabla(jTable3);
  //   if(txtBuscar.getText().trim().length()>1){
     String estado;
     int[] tipo_de_pst; 
     String [] par; 
     int n;
         if(cboSee.getSelectedItem().equals("Todos")){
             tipo_de_pst=new int[1];
             par=new String[1];
             estado="";
             par[0]="";
             n=0;
         }else{
             n=1;
             par=new String[2];
             tipo_de_pst=new int[2];
             estado="c.est_cod=? AND";
             tipo_de_pst[1]=2;
             par[0]=cboSee.getSelectedItem().toString();
         }
             if(cboSuc.getItemCount()==2){//elegir y su suc
             par[n]=Login.suc;   
             }else{
             par[n]=cboSuc.getSelectedItem().toString();   
             }
             tipo_de_pst[0]=2;
if(cboSuc.getSelectedIndex()>0){
     new TblThread("SELECT DISTINCT c.com_cod,c.prov,c.ped_cod,c.ord_nro,c.com_nrofact,c.com_date,c.est_cod,d.suc,1 "
             + "FROM v_com c,v_dcom d "
             + "WHERE "+estado+" "+mes+" AND "+year+" AND d.suc_des=? AND d.com_cod=c.com_cod ORDER BY 1 DESC", jTable1, tipo_de_pst,par,DB.con,this,new int[]{1,2,1,1,1,2,2,2,1}).start();
    }// }}
 }
     
 private void cargarEdicion(){
     limpiarTabla(jTable3);
        int[] tipo_de_pst={1}; 
        String[] par={aop};
        DB.FillTable("SELECT com_cant,mat,dep,com_precio,total FROM v_dcom WHERE com_cod=?", jTable3, tipo_de_pst, par);
 }
        
   private void totalizar(){

  }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cboDep = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        cboMat = new javax.swing.JComboBox();
        txtTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPre = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        txtCod = new javax.swing.JTextField();
        txtOrdCod = new javax.swing.JTextField();
        txtOrdDes = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPed = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtProv = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtSuc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNroFact = new javax.swing.JTextField();
        cboTpag = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cboSee = new javax.swing.JComboBox();
        cboSuc = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox();
        cboFilter = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setTitle("Registro de Facturas de Compra de Materiales");
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de la Factura de Compra de Materiales"));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Descripcion", "Deposito", "Precio Unit.", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setFocusable(false);
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
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(80);

        jLabel9.setText("A depositarce en");

        cboDep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboDep.setEnabled(false);
        cboDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDepActionPerformed(evt);
            }
        });

        jLabel15.setText("Material");

        cboMat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboMat.setEnabled(false);
        cboMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMatActionPerformed(evt);
            }
        });

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setEnabled(false);

        jLabel11.setText("Total Factura");

        jLabel12.setText("Cant.");

        txtCant.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCant.setEnabled(false);
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        jLabel13.setText("Precio Unitario");

        txtPre.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPre.setEnabled(false);
        txtPre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPreKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(cboDep, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 484, Short.MAX_VALUE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(cboMat, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPre, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(22, 22, 22)))))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cboMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Factura de Compra de Materiales"));

        jLabel4.setText("Codigo");

        jLabel5.setText("O. de Compra Nº");

        jLabel6.setText("Fecha");

        txtDate.setEnabled(false);

        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCod.setEnabled(false);

        txtOrdCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtOrdCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtOrdCodKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOrdCodKeyTyped(evt);
            }
        });

        txtOrdDes.setEnabled(false);

        jLabel16.setText("Pedido Nº");

        txtPed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPed.setEnabled(false);

        jLabel17.setText("Proveedor");

        txtProv.setEnabled(false);
        txtProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProvKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProvKeyReleased(evt);
            }
        });

        jLabel18.setText("Sucursal");

        txtSuc.setEnabled(false);
        txtSuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSucKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSucKeyReleased(evt);
            }
        });

        jLabel19.setText("F. de Pago");

        jLabel8.setText("Fact. Nº");

        txtNroFact.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNroFact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroFactKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroFactKeyTyped(evt);
            }
        });

        cboTpag.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboTpag.setEnabled(false);
        cboTpag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTpagActionPerformed(evt);
            }
        });

        btnSearch.setText("...");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel18))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtOrdCod, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOrdDes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(174, 174, 174))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtProv, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPed, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(txtNroFact)
                    .addComponent(cboTpag, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtOrdCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOrdDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cboTpag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtNroFact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Proveedor", "Pedido Nº", "Orden Nº", "Fact. Nº", "Fecha", "Estado", "Sucursal", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(6).setResizable(false);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(7).setResizable(false);
        jTable1.getColumnModel().getColumn(8).setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VIG", "PROV", "ANU", "Todos" }));
        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        cboSuc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
            }
        });

        jLabel2.setText("Filtrar Por");

        jLabel3.setText("Ver");

        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Mes", "Mes Pasado", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel1.setText("de");

        jLabel14.setText("en");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel14))
                .addContainerGap())
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Material", "Deposito", "Precio Unitario", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setFocusable(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getColumn(0).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setResizable(false);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(300);
        jTable3.getColumnModel().getColumn(2).setResizable(false);
        jTable3.getColumnModel().getColumn(3).setResizable(false);
        jTable3.getColumnModel().getColumn(4).setResizable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
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
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed

    }//GEN-LAST:event_jTable2MousePressed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        ManageButtons(2);
        cargarDatos();
        cargarEdicion();
        totalizar();
    }//GEN-LAST:event_jTable1MousePressed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
 al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
 if(jTable2.getRowCount()>0){
        save();
        ManageButtons(1);
        clean();
        if(cboSuc.getSelectedIndex()>0)cargarTabla();
}else{
   JOptionPane.showMessageDialog( this,"No se puede guardar una Factura sin detalles","Atención", JOptionPane.WARNING_MESSAGE);
   txtOrdCod.requestFocus();
}   
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        ManageButtons(1);
        clean();
        if(cboSuc.getSelectedIndex()>0)cargarTabla(); 
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
        limpiarTabla(jTable3);
        if(cboSuc.getSelectedIndex()>0)cargarTabla();
    }//GEN-LAST:event_cboSeeActionPerformed

    private void cboDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDepActionPerformed
    if(cboMat.getSelectedIndex()>0 && cboDep.getSelectedIndex()>0){
        LoadWithObjects dep=(LoadWithObjects)cboDep.getSelectedItem();
        int resultado=validarDuplicados(cboMat.getSelectedItem().toString());
        if(mod==1){//sin orden
                if(resultado!=-1){//hay duplicados 
                              JOptionPane.showMessageDialog(this,"Ya existe un detalle con dicho material","Atención", JOptionPane.WARNING_MESSAGE ); 
                 }else{
                              LoadWithObjects mat=(LoadWithObjects)cboMat.getSelectedItem();
                              double suma=Integer.parseInt(txtPre.getText().trim())*Double.parseDouble(txtCant.getText().trim());
                              modelo.addRow(new Object[]{txtCant.getText(),mat.getCod()+" - "+mat.getDes(),dep.getCod()+" - "+dep.getDes(),Integer.parseInt(txtPre.getText().trim()),suma});                
                              totalizar();  
                 } 
    cboMat.removeItem(cboMat.getSelectedItem());
    cboMat.setSelectedIndex(0);
    cboDep.setSelectedIndex(0);
    txtPre.setText("");
    txtCant.setText("");
    cboMat.requestFocus();
        }else{//con orden
            
                     if(resultado!=-1){//hay duplicados 
                        JOptionPane.showMessageDialog(this,"Ya existe un detalle con dicho material","Atención", JOptionPane.WARNING_MESSAGE ); 
                     }else{
                         LoadWithObjects3Data mat=(LoadWithObjects3Data)cboMat.getSelectedItem();
                        double suma=Integer.parseInt(txtPre.getText().trim())*Double.parseDouble(txtCant.getText().trim());
                        modelo.addRow(new Object[]{txtCant.getText(),mat.getCod()+" - "+mat.getDes(),dep.getCod()+" - "+dep.getDes(),Integer.parseInt(txtPre.getText().trim()),suma});                
                        totalizar();  
                      } 
    cboMat.removeItem(cboMat.getSelectedItem());
    cboMat.setSelectedIndex(0);
    cboDep.setSelectedIndex(0);
    cboMat.requestFocus();
        }
    }    
    }//GEN-LAST:event_cboDepActionPerformed

    private void txtOrdCodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrdCodKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtOrdCodKeyTyped

    private void txtOrdCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrdCodKeyPressed
if(evt.getKeyCode()==10){
    if(DB.getInt(txtOrdCod.getText().trim())==0){//ingreso 0 o nulo, pasa a registrar datos (sin ord)
        mod=1;//sin orden
        txtSuc.setEnabled(true);
        txtProv.setEnabled(true);
        cboTpag.setEnabled(true);
        txtSuc.requestFocus();
    }else{//ingreso un codigo valido, pasa a comprobar existencia de la ord
        mod=0;
     DB.consultPst("SELECT ord_nro,ped_cod,prov,suc,tip,concat('En fecha ,',ord_date,' realizado por ',emp_ci) FROM v_ordc WHERE est_cod='VIG' OR est_cod='COM.P' and ord_nro=?",
            new int[]{1},new String[]{txtOrdCod.getText().trim()},new Object[]{txtOrdCod,txtPed,txtProv,txtSuc,cboTpag,txtOrdDes});
      if(!txtOrdDes.getText().isEmpty()){
txtNroFact.requestFocus();
     }     
    } 
}
    }//GEN-LAST:event_txtOrdCodKeyPressed

    private void cboMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMatActionPerformed
if(cboMat.getSelectedIndex()>0){
    if(mod==1){//sin orden
        txtCant.requestFocus();
    }else{//con orden
        LoadWithObjects3Data mat=(LoadWithObjects3Data)cboMat.getSelectedItem();
        txtCant.setText(mat.getCant());
        txtPre.setText(mat.getAlgomas());
        cboDep.requestFocus();
    }
}else{
    txtCant.setText("");
    txtPre.setText("");
}
    }//GEN-LAST:event_cboMatActionPerformed

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
DB.fieldControl(6,evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void txtPreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtPreKeyTyped

    private void txtSucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyPressed
if(evt.getKeyCode()==10 && txtSuc.getText().trim().length()>1){
    if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.load(new String[]{"suc_cod","suc_des"}, "v_suc","est_cod='VIG'", DB.con);
    pga.autocomplete(txtSuc,null,null,txtProv,this);
}
    }//GEN-LAST:event_txtSucKeyPressed

    private void txtSucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyReleased
DB.toUpperCaseState(txtSuc, evt);
    }//GEN-LAST:event_txtSucKeyReleased

    private void txtProvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvKeyReleased
        DB.toUpperCaseState(txtProv, evt);
    }//GEN-LAST:event_txtProvKeyReleased

    private void txtProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvKeyPressed
        if(evt.getKeyCode()==10 && txtProv.getText().trim().length()>1){
            if(txtProv.getText().trim().length()>1){
             if(pga_s.show_jlist){
                pga_s.show_jlist=false;
                pga_s.refresh_();
            }
            pga_s.autocomplete(txtProv,"prov_cod,' - ',prov_des","v_prov"," AND est_cod='VIG'",this,cboTpag,DB.con);   
            }
        }
    }//GEN-LAST:event_txtProvKeyPressed


    private void txtNroFactKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFactKeyPressed
 if(evt.getKeyCode()==10){
  if(cboTpag.getSelectedIndex()>0 && !txtProv.getText().trim().isEmpty() && !txtSuc.getText().trim().isEmpty() && !txtNroFact.getText().trim().isEmpty()){
      if(mod==1){//sin orden
  DB.CargarCombo("SELECT mat_cod,mat_des FROM v_mat WHERE est_cod='VIG' ORDER BY 2 ASC", cboMat);
                cboTpag.setEnabled(false);
                txtProv.setEnabled(false);
                btnSearch.setEnabled(false);
                txtOrdCod.setEnabled(false);
                txtSuc.setEnabled(false);
                txtCant.setEnabled(true);
                txtPre.setEnabled(true);
      }else{//con orden
          DB.CargarCombo3Data("SELECT mat_cod,mat_des,det_cant,det_precio FROM v_dordc WHERE ord_nro='"+txtOrdCod.getText()+"' ORDER BY 2 ASC", cboMat);
          btnSearch.setEnabled(false);
          txtOrdCod.setEnabled(false);
      }
 DB.CargarCombo("SELECT dep_cod,dep_des FROM v_dep WHERE suc_des='"+DB.getDes(txtSuc.getText())+"' AND est_cod='VIG' ORDER BY 2 ASC", cboDep);
                txtNroFact.setEnabled(false);
                cboMat.setEnabled(true);
                cboDep.setEnabled(true);
                cboMat.requestFocus();
            }else{
                     JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder con el detalle", "Atención", JOptionPane.WARNING_MESSAGE);
                     isOk=1;
                 }
                 }
    }//GEN-LAST:event_txtNroFactKeyPressed

    private void txtNroFactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroFactKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtNroFactKeyTyped

    private void cboTpagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTpagActionPerformed
if(cboTpag.getSelectedIndex()>0){
    txtNroFact.requestFocus();
}
    }//GEN-LAST:event_cboTpagActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
if(cboYear.getSelectedIndex()==0)year="extract(year from (com_date))=extract(year from current_date)";
if(cboYear.getSelectedIndex()==1)year="extract(year from (com_date))=extract(year from current_date)-1";
if(cboYear.getSelectedIndex()>1)year="extract(year from (com_date))="+String.valueOf(cboYear.getSelectedIndex()-1);
if(cboSuc.getSelectedIndex()>0)cargarTabla();
    }//GEN-LAST:event_cboYearActionPerformed

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0){//este mes
    mes="extract(month from (com_date))=extract(month from current_date)";
}else{
    if(cboFilter.getSelectedIndex()==1){//mes anterior
        mes="extract(month from (com_date))=extract(month from current_date)-1";
    }else{
     mes="extract(month from (com_date))="+String.valueOf(cboFilter.getSelectedIndex()-1);//mes por int 1/12  
    }
    }
if(cboSuc.getSelectedIndex()>0)cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void cboSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSucActionPerformed
//if(cboSuc.getSelectedIndex()>0)suc="AND suc='"+cboSuc.getSelectedItem().toString()+"'";
    }//GEN-LAST:event_cboSucActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
if(Integer.parseInt(Login.sucCod)<=4){
    new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE est_cod='VIG' ORDER BY 2 ASC", cboSuc, DB.con,"Elegir").start();
}else{
    cboSuc.removeAllItems();
    cboSuc.addItem("Elegir");
    cboSuc.addItem("Esta Sucursal");
}
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
if(evt.getKeyCode()==10){
    if(!txtCant.getText().trim().isEmpty()){
        txtPre.requestFocus();
    }
}
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtPreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreKeyPressed
if(evt.getKeyCode()==10){
    if(!txtPre.getText().trim().isEmpty()){
        cboDep.requestFocus();
    }
}
    }//GEN-LAST:event_txtPreKeyPressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
Object[][] cbo= new Object[3][2];
cbo[0][0]="Codigo";cbo[0][1]="ord_nro";
cbo[1][0]="Sucursal";cbo[1][1]="suc";
cbo[2][0]="Proveedor";cbo[2][1]="prov";
        Buscador b = new Buscador(null,true,
        "SELECT ord_nro,ped_cod,prov,suc,tip,concat('En fecha ,',ord_date,' realizado por ',emp_ci) FROM v_ordc",
        new String[]{"Codigo","Pedido Nº","Proveedor","Sucursal","Tipo de Pago","Datos de la Ord."},
        new Object[]{txtOrdCod,txtPed,txtProv,txtSuc,cboTpag,txtOrdDes},DB.con,cbo,"((est_cod='VIG') OR (est_cod='COM.P'))");
b.setTitle("Buscador de Ord. de Compra de Materiales");
b.setVisible(true);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
        if(jTabbedPane1.getSelectedIndex()==0){
     pga_s.ocultarauto();   
}
        if(jTabbedPane1.getSelectedIndex()==0){
     pga.ocultarauto();
            
}
    }//GEN-LAST:event_formComponentMoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cboDep;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboMat;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JComboBox cboTpag;
    private javax.swing.JComboBox cboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JTextField txtNroFact;
    private javax.swing.JTextField txtOrdCod;
    private javax.swing.JTextField txtOrdDes;
    private javax.swing.JTextField txtPed;
    private javax.swing.JTextField txtPre;
    private javax.swing.JTextField txtProv;
    private javax.swing.JTextField txtSuc;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
ban=1;
cod=DB.getDBcod("com_cod","v_com");
DB.CargarCombo("SELECT tip_cod,tip_des FROM v_tpag WHERE est_cod='VIG'", cboTpag);
txtCod.setText(cod);
txtDate.setText(DB.ClientDate(1));
//txtSuc.setText(Login.suc);
ManageButtons(3);
txtOrdCod.requestFocus();
}

private void al_nullear(){
        ban=3;
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){                                                                                                                                                                                                         
            save();
        }
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
         if(!jTable1.getValueAt(jTable1.getSelectedRow(),6).toString().equals("PROV.T") || !jTable1.getValueAt(jTable1.getSelectedRow(),5).toString().equals("ANU")){
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
    txtDate.setText("");
    txtCod.setText("");
//    txtBuscar.setText("");
    txtTotal.setText("");
    cboTpag.setSelectedIndex(0);
    txtOrdCod.setText("");
    txtOrdDes.setText("");
    txtPed.setText("");
    txtProv.setText("");
    txtSuc.setText("");
    txtCant.setText("");
    txtPre.setText("");
    txtNroFact.setText("");
    txtCant.setEnabled(false);
    txtPre.setEnabled(false);
    txtSuc.setEnabled(false);
    txtProv.setEnabled(false);
    cboTpag.setEnabled(false);
    txtNroFact.setEnabled(true);
    btnSearch.setEnabled(true);
    txtOrdCod.setEnabled(true);
    aop=null;
    ped=null;
    tip=null;
    prov=null;
    isOk=0;
    limpiarTabla(jTable3);
    limpiarTabla(jTable2);
        if(pga.popup!=null){
         pga.show_jlist=false;
         pga.refresh_();   
    }
                if(pga_s.popup!=null){
         pga_s.show_jlist=false;
         pga_s.refresh_();   
    }
}

  private void limpiarTabla(JTable tabla){
        DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount()!=0)modelo.removeRow(0);
}
  
  private void cargarDatos(){
txtCod.setText(aop);
//DB.setDataFromJtable(jTable3, jTable2);
}
    
    private void save(){
        String[][]par=null;
        int[] tip;
        String ord = txtOrdCod.getText().trim();
        if(ord==null || ord.equals("") || ord.equals("0")){
            tip=new int[]{1,1,1,1,-1,1};
        }else{
            tip=new int[]{1,1,1,1,1,1};
        }
        if(ban==1){
          par=gestionarDetalles(ban,jTable2); 
          isOk=DB.executeCst("abm_com(?,?,?,?,?,?)",tip,
new String[]{String.valueOf(ban),cod,DB.getCod(txtSuc.getText()),Login.empCi,txtOrdCod.getText(),txtNroFact.getText().trim()},false); 
          isOk=isOk+DB.executeCstDetails("abm_dcom(?,?,?,?,?,?,?::numeric,?)", new int[]{1,1,1,1,1,1,5,1}, par);
          if(cboMat.getItemCount()==1){//para actualizar la bandera del pedido correspondiente (varias comprar por cada ord)
//              DB.executeCst("abm_ordc(?,?,?,?,?,?)",new int[]{1,1,1,1}, new String[]{"4",txtCodPed.getText(),"1","1"},false); 
          }else{
 //             DB.executeCst("abm_ordc(?,?,?,?,?,?)",new int[]{1,1,1,1}, new String[]{"5",txtCodPed.getText(),"1","1"},false);
          }
        }else{
            cod=aop;
                      par=gestionarDetalles(ban,jTable3);
          isOk=DB.executeCst("abm_com(?,?,?,?,?,?)",new int[]{1,1,1,-1,-1,-1},
                  new String[]{String.valueOf(ban),aop,DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString()),"0","0","0"},false); 
          isOk=isOk+DB.executeCstDetails("abm_dcom(?,?,?,?,?,?,?::numeric,?)", new int[]{1,1,1,1,1,1,5,1}, par);
        }
if(isOk==0){
    DB.commit();
    DB.EmitirMensaje(this, ban);
}
      clean();
      cargarTabla();
      ManageButtons(1);
    }
    
     private String[][] gestionarDetalles(int ban,JTable tabla){//inserta o anula registros
      int row;
      String[][] par;
      String cod,ord = null,suc;
      if(ban==1){
                 row=jTable2.getRowCount(); 
                 par = new String [row][8];
                 cod=txtCod.getText();
                 ord=txtOrdCod.getText().trim();
                 suc=DB.getCod(txtSuc.getText().trim());
      }else{
                 row=jTable3.getRowCount(); 
                 par = new String [row][8];
                 ord=jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
                 cod=aop;    
                 suc=DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(), 7).toString());
      }
              for (int i = 0; i < row; i++) {//extrae datos del jtable
          par[i][0]=String.valueOf(ban);
          par[i][1]=cod;
          par[i][2]=DB.getCod(tabla.getValueAt(i, 1).toString());//mat
          par[i][3]=DB.getCod(tabla.getValueAt(i, 2).toString());//dep
          par[i][4]=suc;//suc
          par[i][5]=tabla.getValueAt(i, 3).toString();//pre
          par[i][6]=tabla.getValueAt(i, 0).toString();//cant_
          par[i][7]=ord;//ord
                                            }
            return par;
  }
     
       private int validarDuplicados(String des){
      for (int i = 0; i < jTable2.getRowCount(); i++) {
          if(jTable2.getValueAt(i, 1).toString().equals(des))return i;
             }
      return -1;
  }
     
            private void ManageClosing(){
       dispose();
       DB.shutdown();
       Menu.menCompra.setEnabled(true);
     }
}


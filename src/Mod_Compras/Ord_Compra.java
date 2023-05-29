
package Mod_Compras;

import threads.CboThreadGT;
import threads.CboThreadGT2;
import threads.TblThread;
import frames.Buscador;
import frames.Login;
import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import config.LoadWithObjects2Data;
import config.PGa_vStandard;
import config.conectarDB;

public class Ord_Compra extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    DefaultTableModel modelo;
    boolean first=true;
    PGa_vStandard pga_s=null;
    int ban,isOk=0,mod;
    int[] det_ban;
    String aop,cod,mes="extract(month from (ord_date))=extract(month from current_date)",
            year="extract(year from (ord_date))=extract(year from current_date)",buscar="prov",suc=Login.suc;
    
    public Ord_Compra() {
        initComponents();
        DB=new conectarDB();
        pga_s=new PGa_vStandard();
        modelo=(DefaultTableModel)jTable3.getModel();
        ManageButtons(1);
        DB.getYears(cboYear);
        
    }

     private void cargarTabla(){    
     limpiarTabla(jTable1);
     limpiarTabla(jTable2);
     if(txtBuscar.getText().trim().length()>1){
         if(cboSuc.getSelectedIndex()==0){
   suc=Login.suc;
}else{
   suc=cboSuc.getSelectedItem().toString();
}
         buscar="prov LIKE '%"+txtBuscar.getText().trim().toUpperCase()+"%'";
         String estado=cboSee.getSelectedItem().toString();
       int[] tipo_de_pst; 
     String [] par; 
     if(estado.equals("Todos")){
             tipo_de_pst= new int[1];
             par=new String[1];
             estado="";
             tipo_de_pst[0]=2;
             par[0]=suc;
         }else{
         tipo_de_pst= new int[2];
             par=new String[2];
             estado="AND est_cod=?";
             tipo_de_pst[0]=2;
             tipo_de_pst[1]=2;
             par[0]=cboSee.getSelectedItem().toString();
             par[1]=suc;
         }
     new TblThread("SELECT ord_nro,ped_cod,prov,suc,tip_des,date_,est_cod FROM v_ordc WHERE "+buscar+" "+estado+" AND "+mes+" AND "
+year+" and suc_des=? ORDER BY 1 DESC", jTable1, tipo_de_pst,par,DB.con,this,new int[]{1,1,2,2,2,2,2}).start();      
     }
 }
     
      private void cargarEdicion(){
          String[]par=new String[1];
          par[0]=aop;
     limpiarTabla(jTable2);
     DB.FillTable("SELECT det_cant,mat,det_precio,total FROM v_dordc WHERE ord_nro=?", jTable2, new int[]{1},par);
 }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEst = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboFpago = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtDesPed = new javax.swing.JTextField();
        txtCodPed = new javax.swing.JTextField();
        cboProv = new javax.swing.JComboBox();
        txtSuc = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPre = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cboMat = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cboBuscar = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox();
        cboSuc = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setTitle("Generar Orden de Compra de Materiales");
        setToolTipText("");
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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Orden de Compra de Materiales"));

        jLabel1.setText("Codigo");

        txtCod.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCod.setEnabled(false);

        jLabel2.setText("Fecha");

        txtDate.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtDate.setEnabled(false);

        jLabel3.setText("A favor de la Suc.");

        jLabel9.setText("Proveedor");

        jLabel10.setText("Estado");

        txtEst.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtEst.setEnabled(false);

        jLabel11.setText("F. de Pago");

        cboFpago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Credito" }));
        cboFpago.setNextFocusableComponent(txtCodPed);

        jLabel4.setText("S/ Pedido Nº");

        txtDesPed.setEnabled(false);

        txtCodPed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCodPed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodPedKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodPedKeyTyped(evt);
            }
        });

        cboProv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProvActionPerformed(evt);
            }
        });

        txtSuc.setEnabled(false);
        txtSuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSucKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSucKeyTyped(evt);
            }
        });

        btnSearch.setText("...");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cboProv, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtCodPed, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDesPed))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel11)
                                .addGap(17, 17, 17)
                                .addComponent(cboFpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txtEst, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboFpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCodPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesPed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de la Orden de Compra de Materiales"));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Material", "Precio Unit.", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setFocusable(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable3MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setResizable(false);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(90);
        }

        jLabel12.setText("Material");

        jLabel13.setText("Precio Unit.");

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

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("<ENTER>");

        txtTotal.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");
        txtTotal.setEnabled(false);

        jLabel15.setText("Monto Total de la Orden");

        cboMat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboMat.setEnabled(false);
        cboMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMatActionPerformed(evt);
            }
        });
        cboMat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cboMatFocusGained(evt);
            }
        });

        jLabel5.setText("Cant.");

        txtCant.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCant.setEnabled(false);
        txtCant.setNextFocusableComponent(txtPre);
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(cboMat, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtCant)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txtPre, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cboMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap())
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "S/ Pedido Nº", "Proveedor", "Sucursal", "T. de pago", "Fecha", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(50);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Ver");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VIG", "COM.P", "COM.T", "ANU", "Todos" }));
        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        jLabel7.setText("Filtrar Por");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Mes", "Mes Pasado", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel8.setText("Buscar Por");

        cboBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Proveedor", "Pedido Nº" }));
        cboBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBuscarActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel18.setText("del");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("<ENTER>");

        cboYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });

        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
            }
        });

        jLabel20.setText("en");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboBuscar, 0, 89, Short.MAX_VALUE)
                    .addComponent(cboYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Material", "Precio Unit.", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setFocusable(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("Listado", jPanel3);

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
                .addGap(104, 104, 104)
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
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
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
       int msj=JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION);
   if (msj==JOptionPane.YES_OPTION){
       ManageClosing();
   }
}
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(jTable3.getRowCount()>0){
            save();
        clean();
        cargarTabla();
        ManageButtons(1);
}else{
    JOptionPane.showMessageDialog( this,"No se puede guardar una Orden sin Detalles","Atención", JOptionPane.WARNING_MESSAGE );
    txtCodPed.requestFocus();
}   
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clean();
        cargarTabla();
        ManageButtons(1);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
        ManageButtons(2);
        cargarDatos();
    }//GEN-LAST:event_jTable1MousePressed

    private void txtPreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreKeyPressed
if(evt.getKeyCode()==10){
    int pre=DB.getInt(txtPre.getText().trim());
            if(pre>0 && cboMat.getSelectedIndex()>0){
          if(first){//-----wtf
             limpiarTabla(jTable3);
             first=false;
          }//------fin del wtf               
int resultado=validarDuplicados(cboMat.getSelectedItem().toString());
if(mod==0){//normal   
    if(resultado!=-1){//hay duplicados 
        JOptionPane.showMessageDialog(this,"Ya existe un detalle con dicho material","Atención", JOptionPane.WARNING_MESSAGE ); 
    }else{
         LoadWithObjects2Data mat=(LoadWithObjects2Data)cboMat.getSelectedItem();
      double suma=Integer.parseInt(txtPre.getText().trim())*Double.parseDouble(txtCant.getText().trim());
        modelo.addRow(new Object[]{txtCant.getText(),mat.getCod()+" - "+mat.getDes(),Integer.parseInt(txtPre.getText().trim()),suma});     
    } 
}else{//sin ped
    if(resultado!=-1){//hay duplicados 
        JOptionPane.showMessageDialog(this,"Ya existe un detalle con dicho material","Atención", JOptionPane.WARNING_MESSAGE ); 
    }else{
         LoadWithObjects mat=(LoadWithObjects)cboMat.getSelectedItem();
      double suma=Integer.parseInt(txtPre.getText().trim())*Double.parseDouble(txtCant.getText().trim());
        modelo.addRow(new Object[]{txtCant.getText(),mat.getCod()+" - "+mat.getDes(),Integer.parseInt(txtPre.getText().trim()),suma});    
    }   
}        
totalizar();
            cboMat.removeItem(cboMat.getSelectedItem());
                txtPre.setText("");
                txtCant.setText("");
                cboMat.requestFocus();
                cboMat.setSelectedIndex(0);     
            }else{
                  JOptionPane.showMessageDialog(this,"No seleccionó un Material y/o no escribió una cantidad decimal válida","Error al Guardar", JOptionPane.WARNING_MESSAGE );            
                  cboMat.requestFocus();
             }
}       
    }//GEN-LAST:event_txtPreKeyPressed

    private void jTable3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MousePressed

    }//GEN-LAST:event_jTable3MousePressed

    private void txtPreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreKeyTyped
DB.fieldControl(2, evt);
    }//GEN-LAST:event_txtPreKeyTyped

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
new CboThreadGT("SELECT prov_cod,prov_des FROM v_prov WHERE est_cod='VIG' ORDER BY 2 ASC", cboProv, DB.con,"Elegir").start();
if(Integer.parseInt(Login.sucCod)<=4){
    new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE suc_des!='"+Login.suc+"' AND est_cod='VIG' ORDER BY 2 ASC", cboSuc, DB.con,"Esta Sucursal").start();
}else{
    cboSuc.removeAllItems();
    cboSuc.addItem("Esta Sucursal");
}
    }//GEN-LAST:event_formInternalFrameOpened

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0){//este mes
    mes="extract(month from (ord_date))=extract(month from current_date)";
}else{
    if(cboFilter.getSelectedIndex()==1){//mes anterior
        mes="extract(month from (ord_date))=extract(month from current_date)-1";
    }else{
     mes="extract(month from (ord_date))="+String.valueOf(cboFilter.getSelectedIndex()-1);//mes por int 1/12  
    }
    }
//txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla(); 
    }//GEN-LAST:event_cboFilterActionPerformed

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
//txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla();
    }//GEN-LAST:event_cboSeeActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
if(cboYear.getSelectedIndex()==0)year="extract(year from (ord_date))=extract(year from current_date)";
if(cboYear.getSelectedIndex()==1)year="extract(year from (ord_date))=extract(year from current_date)-1";
if(cboYear.getSelectedIndex()>1)year="extract(year from (ord_date))="+String.valueOf(cboYear.getSelectedIndex()-1);
//txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla(); 
    }//GEN-LAST:event_cboYearActionPerformed

    private void cboBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBuscarActionPerformed
if(cboBuscar.getSelectedIndex()==0){
    buscar="prov LIKE '%"+txtBuscar.getText().trim()+"%'";
}else{
    buscar="ped_cod="+txtBuscar.getText().trim();
}
txtBuscar.setText("");
txtBuscar.requestFocus(); 
    }//GEN-LAST:event_cboBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
if(txtBuscar.getText().trim().isEmpty()){
    limpiarTabla(jTable1);
    limpiarTabla(jTable2);
}else{
     DB.toUpperCaseState(txtBuscar, evt);   
}
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10){
    cargarTabla();
}
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void cboSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSucActionPerformed

cargarTabla();
    }//GEN-LAST:event_cboSucActionPerformed

    private void txtCodPedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPedKeyPressed
if(evt.getKeyCode()==10){
    if(DB.getInt(txtCodPed.getText().trim())==0){//ingreso 0 o nulo, pasa a registrar datos sin pedido
        txtCodPed.setText("0");
        txtDesPed.setText("");
        txtSuc.setEnabled(true);
txtSuc.requestFocus();
DB.CargarCombo("SELECT mat_cod,mat_des FROM v_mat WHERE est_cod='VIG'", cboMat);
        mod=1;
    }else{//con pedido
        txtSuc.setEnabled(false);
        mod=0;
     DB.consultPst("SELECT ped_cod,CONCAT(suc_cod,' - ',suc_des)AS suc,concat('Pedido realizado por ',emp,' en fecha ',date_) FROM v_ped WHERE ((est_cod='APR') OR (est_cod='ORD.P')) AND ped_cod=?",
            new int[]{1},new String[]{txtCodPed.getText().trim()},new JTextField[]{txtCodPed,txtSuc,txtDesPed});
      if(!txtDesPed.getText().isEmpty()){
             cboMat.requestFocus();
            new CboThreadGT2("select mat_cod,mat_des,det_cant from v_dped where ped_cod="+txtCodPed.getText().trim()+" and est_cod='APR' and det_ord=false",
                   cboMat,DB.con).start();
cboProv.requestFocus();
        }else{
txtCodPed.setText("");
cboSuc.setSelectedIndex(0);
txtDesPed.setText(""); 
txtCodPed.requestFocus();
      }   
    }  
    }
    }//GEN-LAST:event_txtCodPedKeyPressed

    private void txtCodPedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodPedKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtCodPedKeyTyped

    private void cboMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMatActionPerformed
if(cboMat.getSelectedIndex()>0){    
            if(mod==0){
            LoadWithObjects2Data mat=(LoadWithObjects2Data)cboMat.getSelectedItem();
            txtCant.setText(mat.getCant());
            txtPre.requestFocus();
            }else{
            txtCant.requestFocus();
            }     
}
    }//GEN-LAST:event_cboMatActionPerformed

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
DB.fieldControl(7, evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
if(jTabbedPane1.getSelectedIndex()==0){
     pga_s.ocultarauto();
}
    }//GEN-LAST:event_formComponentMoved

    private void cboMatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboMatFocusGained
//if(txtSuc.isEnabled())txtSuc.setEnabled(false);
    }//GEN-LAST:event_cboMatFocusGained

    private void cboProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProvActionPerformed
if(cboProv.getSelectedIndex()>0){
    if(mod==1){
        txtCant.setEnabled(true);
        
    }
    txtPre.setEnabled(true);
    cboMat.setEnabled(true);
    cboProv.setEnabled(false);
cboFpago.setEnabled(false);
txtCodPed.setEnabled(false);
txtSuc.setEnabled(false);
btnSearch.setEnabled(false);
cboMat.requestFocus();
}
    }//GEN-LAST:event_cboProvActionPerformed

    private void txtSucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyPressed
if(evt.getKeyCode()==10 ){
    if(pga_s.show_jlist){
        pga_s.show_jlist=false;
        pga_s.refresh_();
    }
    pga_s.autocomplete(txtSuc,"suc_cod,' - ',suc_des","v_suc"," AND est_cod='VIG'",this,cboProv,DB.con);
}else{
    //if(txtSuc.getText().equals("ESTA SUCURSAL"))txtSuc.setText("");
}
    }//GEN-LAST:event_txtSucKeyPressed

    private void txtSucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyReleased
DB.toUpperCaseState(txtSuc, evt);
    }//GEN-LAST:event_txtSucKeyReleased

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
Object[][] cbo= new Object[2][2];
cbo[0][0]="Sucursal";cbo[0][1]="suc_des";
        Buscador b = new Buscador(null,true,
        "SELECT ped_cod,CONCAT(suc_cod,' - ',suc_des)AS suc,concat('Pedido realizado por ',emp,' en fecha ',date_) "
        + "FROM v_ped ",
        new String[]{"Codigo","Sucursal","Datos del Pedido"},
        new Object[]{txtCodPed,txtSuc,txtDesPed},DB.con,cbo,"((est_cod='APR') OR (est_cod='ORD.P'))");
b.setTitle("Buscador de Pedidos de Materiales");
b.setVisible(true);
//-------------------------
    if(!txtDesPed.getText().isEmpty()){
             cboMat.requestFocus();
            new CboThreadGT2("select mat_cod,mat_des,det_cant from v_dped where ped_cod="+txtCodPed.getText().trim()+" and est_cod='APR' and det_ord=false",
                   cboMat,DB.con).start();
cboProv.requestFocus();
        }else{
txtCodPed.setText("");
cboSuc.setSelectedIndex(0);
txtDesPed.setText(""); 
txtCodPed.requestFocus();
      }  
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyTyped
DB.fieldControl(3, evt);
    }//GEN-LAST:event_txtSucKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cboBuscar;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboFpago;
    private javax.swing.JComboBox cboMat;
    private javax.swing.JComboBox cboProv;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JComboBox cboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtCodPed;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDesPed;
    private javax.swing.JTextField txtEst;
    private javax.swing.JTextField txtPre;
    private javax.swing.JTextField txtSuc;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
ban=1;
cod=DB.getDBcod("ord_nro","v_ordc");
txtCod.setText(cod);
txtDate.setText(DB.ClientDate(1));
txtEst.setText("EMI");
ManageButtons(3);
cboFpago.requestFocus();
}

private void al_nullear(){
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
ban=3;
save();
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
         if(jTable1.getValueAt(jTable1.getSelectedRow(), 6).equals("VIG")){
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
    cboMat.removeAllItems();
    cboMat.addItem("Elegir");
    txtDate.setText("");
    txtCod.setText("");
    cboProv.setSelectedIndex(0);
    cboMat.setSelectedIndex(0);
    txtSuc.setText("");
    txtPre.setText("");
    txtEst.setText("");
    txtBuscar.setText("");
    txtCodPed.setText("");
    txtCant.setText("");
    txtDesPed.setText("");
    txtCant.setEnabled(false);
    txtSuc.setEnabled(false);
    btnSearch.setEnabled(true);
    aop=null;
    isOk=0;
    cboProv.setEnabled(true);
    cboFpago.setEnabled(true);
    txtCodPed.setEnabled(true);
    cboMat.setEnabled(false);
    txtCant.setEnabled(false);
    limpiarTabla(jTable3);
    limpiarTabla(jTable2);
    limpiarTabla(jTable1);
    
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
cargarEdicion();
}
  
   private int validarDuplicados(String des){
      for (int i = 0; i < jTable3.getRowCount(); i++) {
          if(jTable3.getValueAt(i, 1).toString().equals(des))return i;
             }
      return -1;
  }
   
  private void save(){
      String[][]par=null;
      String suc = null;
      int[] detalle=  {1,1,1,1,1,5};
      if(ban==1){
          int[] cabecera= {1,1,1,1,1,1,1};
          
          par=gestionarDetalles(jTable3);
          LoadWithObjects obj=(LoadWithObjects)cboProv.getSelectedItem(); 
          if(mod==1){
              cabecera[3]=-1;
              detalle[3]=-1; //ped i guess
          }
//          if(txtSuc.getText().equals("ESTA SUCURSAL")){
//              suc=Login.sucCod;
        //  }else{
              suc=DB.getCod(txtSuc.getText());
        //  }
          isOk=DB.executeCst("abm_ordc(?,?,?,?,?,?,?)",cabecera,
    new String[]{String.valueOf(ban),cod,suc,txtCodPed.getText().trim(),Login.empCi,obj.getCod(),String.valueOf(cboFpago.getSelectedIndex()+1)},false); 
          isOk=isOk+DB.executeCstDetails("abm_dordc(?,?,?,?,?,?::numeric)",detalle, par);
          if(mod==0){
             if(cboMat.getItemCount()==1){//para actualizar la bandera del pedido correspondiente
              DB.executeCst("abm_ped(?,?,?,?)",new int[]{1,1,1,1}, new String[]{"4",txtCodPed.getText(),"1","1"},false); 
          }else{
              DB.executeCst("abm_ped(?,?,?,?)",new int[]{1,1,1,1}, new String[]{"5",txtCodPed.getText(),"1","1"},false);
           
          }
          }
if(isOk==0){
    DB.commit();
    DB.EmitirMensaje(this, ban);
}
      }else{//-------------------------------------------------------------------------------------------
          par=gestionarDetalles(jTable2);
          isOk=DB.executeCst("abm_ordc(?,?,?,?,?,?,?)",new int[]{1,1,1,1,-1,-1,1},
                  new String[]{String.valueOf(ban),aop,DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString()),
                      jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(),"0","0","0"},false); 
          isOk=isOk+DB.executeCstDetails("abm_dordc(?,?,?,?,?,?::numeric)", detalle, par);
          
if(isOk==0){
    DB.commit();
    DB.EmitirMensaje(this, ban);
}
      }
      clean();
      cargarTabla();
      ManageButtons(1);
}
  
  private String[][] gestionarDetalles(JTable tabla){//inserta o anula registros
      int row;
      String[][] par;
      String cod,ped;
      row=tabla.getRowCount();
      par = new String [row][6];
      if(ban==1){
                 cod=txtCod.getText();
                 ped=txtCodPed.getText().trim();
      }else{                
                 ped=jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
                 cod=aop;                 
      }
      
      if(ped.equals("") || ped.equals("0"))ped="0";
              for (int i = 0; i < row; i++) {//extrae datos del jtable
                 // System.out.print("\n ss "+par[i][0]);
          par[i][0]=String.valueOf(ban);
          par[i][1]=cod;
          par[i][2]=DB.getCod(tabla.getValueAt(i, 1).toString());//extrae el cod
          par[i][3]=ped;
          par[i][4]=tabla.getValueAt(i, 2).toString();//pre
          par[i][5]=tabla.getValueAt(i, 0).toString();//cant
             }
            return par;
  }
  
  private void totalizar(){//verificar--http://chuwiki.chuidiang.org/index.php?title=DecimalFormat
                           //http://www.yoelprogramador.com/puntos-decimales-y-separador-de-miles-en-java/
             Double total = 0.00;
     for (int i = 0; i < jTable3.getRowCount(); i++) {
               total= total+Double.parseDouble(jTable3.getValueAt(i, 3).toString());
             }    
     txtTotal.setText(DB.getPoint(total.toString()));
  }
  
       private void ManageClosing(){
           pga_s.ocultarauto();
       dispose();
       DB.shutdown();
       Menu.menOrdc.setEnabled(true);
     }
}


package referenciales;

import threads.TblThread;
import frames.Menu;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import config.conectarDB;

/**
 *
 * @author schafler92
 */
public class Ref_materiales extends javax.swing.JInternalFrame {
    
    private final conectarDB DB;
    int bandera,isOk=0,Nro_de_Reg=0;
    boolean isLoaded=false,first=true;
    String[][] CboDamageArray;//guarda datos del combo para no conectar  una y otra vez a la bd
    String aop,cod,filter="AND est_des='VIG'";
    ArrayList<Object> arrayListBAN = new ArrayList<>();
    ArrayList<Object> arrayListCOD = new ArrayList<>();
    ArrayList<Object> arrayListDAM = new ArrayList<>();
    ArrayList<Object> arrayListCANT = new ArrayList<>();
    DefaultTableModel modelo;

    public Ref_materiales() {
        initComponents();
        DB = new conectarDB();
        ManageButtons(1);   
        modelo=(DefaultTableModel)jTable2.getModel();
    }

        private void cargarTabla(){
            limpiarTabla(jTable1);
            if(txtBuscar.getText().trim().length()>1){
        new TblThread("SELECT * FROM v_mat WHERE mat_des LIKE '%"+txtBuscar.getText().trim().toUpperCase()+"%' "+filter
                + "ORDER BY mat_des ASC", jTable1,null,null,DB.con,this,new int[]{1,2,2,5,4}).start();   
            }
 }    
        
            private void cargarDetalle(){
            limpiarTabla(jTable3); 
            new TblThread("SELECT dam,cant,est_des FROM v_dmat WHERE mat_cod=?",
                    jTable3,new int[]{1},new String[]{aop},DB.con,this,new int[]{2,2,2}).start();  
 } 
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExit = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtCod = new javax.swing.JTextField();
        txtDes = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboUn = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cboDamage = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnEditCant = new javax.swing.JButton();
        txtEditCant = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtStckMin = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();

        setTitle("Mantener Materiales");
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

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/door-in-icon.png"))); // NOI18N
        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        btnNull.setText("Anular");
        btnNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNullActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_add.png"))); // NOI18N
        btnAdd.setText("Agregar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png"))); // NOI18N
        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_edit.png"))); // NOI18N
        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Materiales"));

        txtCod.setEditable(false);
        txtCod.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCod.setEnabled(false);

        txtDes.setNextFocusableComponent(cboUn);
        txtDes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDesFocusGained(evt);
            }
        });
        txtDes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDesKeyTyped(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");

        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("*");

        jLabel8.setText("Un. de Medida");

        jLabel1.setText("Descripcion");

        jLabel6.setText("Codigo");

        cboUn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- ELEGIR --" }));
        cboUn.setNextFocusableComponent(txtStckMin);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cboUn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboUn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Parametrización"));

        jLabel5.setText("Daño");

        cboDamage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- ELEGIR --" }));
        cboDamage.setEnabled(false);
        cboDamage.setNextFocusableComponent(txtCant);
        cboDamage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDamageActionPerformed(evt);
            }
        });

        jLabel7.setText("Cant. por pieza");

        txtCant.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCant.setToolTipText("La cantidad será multiplicada conforme aumente la cantidad de piezas y/o el daño de las mismas");
        txtCant.setEnabled(false);
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("<ENTER>");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Daño", "Cant. maxima a utilizar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Botones"));

        btnEditCant.setText("Editar Cant.");
        btnEditCant.setEnabled(false);
        btnEditCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCantActionPerformed(evt);
            }
        });

        txtEditCant.setEnabled(false);
        txtEditCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEditCantKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEditCantKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("<ENTER>");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditCant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtEditCant, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditCant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEditCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cboDamage, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboDamage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Existencia minima por Sucursal"));

        jLabel19.setText("Existencia Minima");

        txtStckMin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtStckMin.setNextFocusableComponent(cboDamage);
        txtStckMin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStckMinFocusGained(evt);
            }
        });
        txtStckMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStckMinKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStckMinKeyTyped(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");

        jLabel4.setText("Sucursal");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- ELEGIR --" }));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Exitencia minima"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setResizable(false);
            jTable4.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(txtStckMin, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel19)
                    .addComponent(txtStckMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Un. de Medida", "Descripcion", "Exist. Min."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setText("Ver");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigentes", "Anulados" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("<ENTER>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel22)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Daño", "Cantidad del material para 1 pieza", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setFocusable(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listado", jPanel2);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnCancelar)
                    .addComponent(btnExit))
                .addGap(30, 30, 30)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
al_editar();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_borrar();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    validar_blancos();
    if(isOk==0){
     clean();
     cargarTabla();
     ManageButtons(1); 
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
clean();
cargarTabla();
ManageButtons(1);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
    ManageClosing();
}else{
      if (JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       ManageClosing();
   }
}
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtDesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesKeyReleased
DB.toUpperCaseState(txtDes,evt);
    }//GEN-LAST:event_txtDesKeyReleased

    private void txtStckMinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStckMinKeyTyped
DB.fieldControl(7,evt);
    }//GEN-LAST:event_txtStckMinKeyTyped

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
txtCod.setText(aop);
ManageButtons(2);
cargarDatos();
cargarDetalle();
    }//GEN-LAST:event_jTable1MousePressed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
if(txtBuscar.getText().trim().isEmpty()){
    limpiarTabla(jTable1);
    limpiarTabla(jTable3);
}else{
   DB.toUpperCaseState(txtBuscar,evt); 
}
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0)filter="AND est_des='VIG'";
if(cboFilter.getSelectedIndex()==1)filter="AND est_des='ANU'";
txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void txtStckMinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStckMinKeyPressed
if(evt.getKeyCode()==10){
    if(!txtDes.getText().trim().isEmpty() && !txtStckMin.getText().trim().isEmpty()){
     txtDes.setEnabled(false);
txtStckMin.setEnabled(false);
cboUn.setEnabled(false);
cboDamage.setEnabled(true);
    txtCant.setEnabled(true);   
    cboDamage.requestFocus();
    }
}
    }//GEN-LAST:event_txtStckMinKeyPressed

    private void txtDesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtDesKeyTyped

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10)cargarTabla();
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
     DB.CargarComboSimple("SELECT un_cod FROM v_un WHERE est_des='VIG' ORDER BY 1 ASC", cboUn);
    }//GEN-LAST:event_formInternalFrameOpened

    private void cboDamageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDamageActionPerformed
if(cboDamage.getSelectedIndex()>0){
    txtCant.requestFocus();
}
    }//GEN-LAST:event_cboDamageActionPerformed

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
if(evt.getKeyCode()==10){
    if(cboDamage.getSelectedIndex()>0 && !txtCant.getText().trim().isEmpty()){
     LoadWithObjects dam=(LoadWithObjects)cboDamage.getSelectedItem();

 if(validarDuplicados(String.valueOf(dam.getCod()+" - "+dam.getDes()))==-1){//no hay duplicados
     if(bandera==2){
           modelo=(DefaultTableModel)jTable2.getModel();
           modelo.addRow(new Object[]{String.valueOf(dam.getCod()+" - "+dam.getDes()),txtCant.getText(),"VIG"}); 
           arrayListBAN.add(1);
           arrayListDAM.add(DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString()));
           arrayListCOD.add(txtCod.getText());           
           arrayListCANT.add(txtCant.getText().trim());
     }else{
         System.out.print("\ntro");
                 if(first){//---------------wtf
                  limpiarTabla(jTable2);   
                  first=false;
                 }//------------------------fin wtf
modelo.addRow(new Object[]{String.valueOf(dam.getCod()+" - "+dam.getDes()),
    Double.parseDouble(txtCant.getText().trim()),"VIG"}); 
txtCant.setText("");
cboDamage.requestFocus();
     }
    cboDamage.removeItem(cboDamage.getSelectedItem());
    cboDamage.setSelectedIndex(0);
         }else{
     JOptionPane.showMessageDialog(this, "errror duplicados");
              }
}else{
        JOptionPane.showMessageDialog(this, "errror validacion");
    }
}
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtDesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDesFocusGained
if(bandera==2)txtDes.selectAll();
    }//GEN-LAST:event_txtDesFocusGained

    private void txtStckMinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStckMinFocusGained
if(bandera==2)txtStckMin.selectAll();
    }//GEN-LAST:event_txtStckMinFocusGained

    private void btnEditCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCantActionPerformed
                if(jTable2.getSelectedRow()==-1){
            JOptionPane.showMessageDialog( this,"No seleccionó ningún Registro","Atención", JOptionPane.INFORMATION_MESSAGE); 
        }else{
                        txtEditCant.setEnabled(true);
                        txtEditCant.requestFocus();
                } 
    }//GEN-LAST:event_btnEditCantActionPerformed

    private void txtCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyTyped
DB.fieldControl(7,evt);
    }//GEN-LAST:event_txtCantKeyTyped

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        if(bandera!=1 && jTable2.getValueAt(jTable2.getSelectedRow(),2).toString().equals("VIG")){
        btnEditCant.setEnabled(true);
        }else{
        btnEditCant.setEnabled(false); 
        }
    }//GEN-LAST:event_jTable2MousePressed

    private void txtEditCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEditCantKeyTyped
DB.fieldControl(7, evt);
    }//GEN-LAST:event_txtEditCantKeyTyped

    private void txtEditCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEditCantKeyPressed
if(evt.getKeyCode()==10){
    if(!txtEditCant.getText().trim().isEmpty()){
        jTable2.setValueAt(txtEditCant.getText(), jTable2.getSelectedRow(),1);
        arrayListBAN.add("2");
        arrayListDAM.add(DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString()));
        arrayListCOD.add(txtCod.getText());
        arrayListCANT.add(txtEditCant.getText());
        
        txtEditCant.setEnabled(false);
        btnEditCant.setEnabled(false);
    }
}
    }//GEN-LAST:event_txtEditCantKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEditCant;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboDamage;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboUn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDes;
    private javax.swing.JTextField txtEditCant;
    private javax.swing.JTextField txtStckMin;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
    bandera=1;
    cod=DB.getDBcod("mat_cod","v_mat");
    txtCod.setText(cod);
    ManageButtons(3);
    manageCboDamage();
    txtDes.requestFocus();
}
private void al_editar(){
    ManageButtons(3);
    manageCboDamage();
    txtDes.requestFocus();
    DB.setDataFromJtable(jTable3, jTable2);
    modelo=(DefaultTableModel)jTable2.getModel();
    bandera=2;
}

private void al_borrar(){
        bandera=3;
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)save4EditableDetails();
       clean();
       cargarTabla();
       ManageButtons(1);
}

private void manageCboDamage(){
            if(!isLoaded){//-- :V , verifica si ya se cargo antes, o si faltan reg en el combo para cargar
    Nro_de_Reg=DB.CargarCombo("SELECT dam_cod,dam_des FROM v_dam WHERE est_des='VIG'", cboDamage);
    CboDamageArray=DB.getComboArray(cboDamage);
    isLoaded=true;
}
        if(Nro_de_Reg>(cboDamage.getItemCount()-1)){
            DB.setComboArray(cboDamage, CboDamageArray);
        }
}

private void clean(){
    txtDes.setText("");
    txtCod.setText("");
    txtBuscar.setText("");
    txtStckMin.setText("");
    txtBuscar.setText("");
    txtCant.setText("");
    cboDamage.setEnabled(false);
    txtCant.setEnabled(false);
    txtEditCant.setEnabled(false);
    cboUn.setSelectedIndex(0);
    cboDamage.setSelectedIndex(0);
    aop=null;
    isOk=0;
    limpiarTabla(jTable3);
}

private int validarDuplicados(String des){
      for (int i = 0; i < jTable2.getRowCount(); i++) {
          if(jTable2.getValueAt(i, 0).toString().equals(des) && jTable2.getValueAt(i, 2).toString().equals("VIG"))return i;
             }
      return -1;//no hay duplicados vigentes*
  }

  private void limpiarTabla(JTable tabla){
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount() != 0)modelo.removeRow(0);
}
  
      private void ManageButtons(int estado){
     if(estado==1){//boot
         btnAdd.setEnabled(true);
         btnAdd.requestFocusInWindow();
         btnEdit.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancelar.setEnabled(false);
         jTabbedPane1.setSelectedIndex(1);
     }
          if(estado==2){//pressed
         btnAdd.setEnabled(false);
         if(cboFilter.getSelectedIndex()==0){
             btnEdit.setEnabled(true);
         }else{
             btnEdit.setEnabled(false);
         }
         if(cboFilter.getSelectedIndex()==0){
             btnNull.setEnabled(true);
         }else{
              btnNull.setEnabled(false);
         }
         btnSave.setEnabled(false);
         btnCancelar.setEnabled(true);
     }
               if(estado==3){//editing
                  btnAdd.setEnabled(false);
         btnEdit.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(true);
         btnCancelar.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
         //----------------------
         txtDes.setEnabled(true);
         cboUn.setEnabled(true);
         txtStckMin.setEnabled(true);     }
 }
  
     private void validar_blancos(){ 
         int n=0;
         if(bandera==1){
                   if(txtDes.getText().trim().isEmpty() || txtStckMin.getText().trim().isEmpty() || jTable2.getRowCount()==0 || cboDamage.getItemCount()>1){
             JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder", "Atención", JOptionPane.WARNING_MESSAGE); 
             txtDes.setEnabled(true);
         cboUn.setEnabled(true);
         txtStckMin.setEnabled(true); 
             txtDes.requestFocus();
             isOk=1;
             }else{
        save4EditableDetails();
     }    
         }else{
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                if(!jTable2.getValueAt(i,2).toString().equals("VIG")){//pregunta si esta en anu
                    n++;
                }
            }
                if(n<jTable2.getRowCount() /*&& !det_banIsNull(det_ban)*/){//save
                     save4EditableDetails();
                }else{//
                     JOptionPane.showMessageDialog(this,"No se puede guardar un Material con todos los detalles anulados o nulos","Atención", JOptionPane.WARNING_MESSAGE);
                }
         }
} 
         
private void save4EditableDetails(){
         String[][] par = null;
         int row;
         int[] tipos_de_get = {1,1,2,2,5};
         Object[] parametros = {bandera,cod,cboUn.getSelectedItem().toString(),txtDes.getText().trim(),txtStckMin.getText().trim()};
         switch(bandera){
             case 1://-----------------------------------------------------------------------------------------------------------------------------------------
               row=jTable2.getRowCount();                         
             par = new String [row][4];
                    
             for (int i = 0; i < row; i++) {//------------
          par[i][0]="1";
          par[i][1]=DB.getCod(jTable2.getValueAt(i, 0).toString());
          par[i][2]=txtCod.getText();
          par[i][3]=jTable2.getValueAt(i, 1).toString();
             }//------------------------------------------    
    isOk=DB.executeCst("abm_mat(?,?,?,?,?::numeric)", tipos_de_get,parametros,false);
    if(isOk==0)isOk=DB.executeCstDetails("abm_dmat(?,?,?,?::numeric)", new int[]{1,1,1,5}, par);
    if(isOk==0){
        DB.EmitirMensaje(this, bandera);
        DB.commit();
    }
             break;
             case 2://-----------------------------------------------------------------------------------------------------------------------------------------
                 Object[]par1=arrayListBAN.toArray();
                 Object[]par2=arrayListDAM.toArray();
                 Object[]par3=arrayListCOD.toArray();      
                 Object[]par4=arrayListCANT.toArray();
                 par = new String [par1.length][4];
                                           for (int i = 0; i < arrayListBAN.size(); i++) {//meten en un array[][] los 3 array[]dinamicos
                                                par[i][0]=par1[i].toString();
                                                par[i][1]=par2[i].toString();
                                                par[i][2]=par3[i].toString();
                                                par[i][3]=par4[i].toString();
                                                          }
                 
    parametros[1]=aop; 
    isOk=DB.executeCst("abm_mat(?,?,?,?,?::numeric)", tipos_de_get,parametros,false);
    if(!arrayListBAN.isEmpty() && isOk==0)isOk=DB.executeCstDetails("abm_dmat(?,?,?,?::numeric)", new int[]{1,1,1,5}, par);
    if(isOk==0){
        DB.EmitirMensaje(this, bandera);
        DB.commit();
    }
             break;
             case 3://-----------------------------------------------------------------------------------------------------------------------------------------
                     isOk=DB.executeCst("abm_mat(?,?,?,?,?)",new int[] {1,1,-1,-1,-1},
            new Object[]{bandera,aop,null,null,null},true);
    if(isOk==0)DB.EmitirMensaje(this, bandera);
             break;
         }
     }
     
     private void cargarDatos(){
txtDes.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
txtStckMin.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
cboUn.setSelectedItem(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
     }
     
     private void ManageClosing(){
       dispose();
       DB.shutdown();
       Menu.menMat.setEnabled(true);
     }
}

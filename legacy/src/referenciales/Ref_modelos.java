
package referenciales;

import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import threads.CboThreadGT;
import javax.swing.JTable;
import threads.TblThread;
import java.util.ArrayList;
import config.LoadWithObjects;
import config.conectarDB;
import config.PGa_vStandard;

/**
 *
 * @author schafler92
 */
public class Ref_modelos extends javax.swing.JInternalFrame {
    
    private final conectarDB DB;
    int bandera,isOk=0,Nro_de_Reg;
    boolean first=true,isLoaded=false;
    ArrayList<Object> arrayListBAN = new ArrayList<>();
    ArrayList<Object> arrayListCOD = new ArrayList<>();
    ArrayList<Object> arrayListMAR = new ArrayList<>();
    ArrayList<Object> arrayListTVEH = new ArrayList<>();
    String[][]CboTvehArray;
    String aop,cod,filter="est_cod='VIG'";
    DefaultTableModel modelo;
    PGa_vStandard pga=null;

    public Ref_modelos() {
        initComponents();
        DB = new conectarDB();
        ManageButtons(1);
        modelo=(DefaultTableModel)jTable2.getModel();
        pga=new PGa_vStandard();
    }

        private void cargarTabla(){
            limpiarTabla(jTable1);
  if(cboSee.getSelectedIndex()>0 && txtBuscar.getText().trim().length()>1){         
        int[] tipo_de_pst={2}; 
        String [] par={cboSee.getSelectedItem().toString()}; 
       new TblThread("SELECT mod_cod,mod_des FROM v_mod "
               + "WHERE mar_des=? AND "+filter+" AND mod_des LIKE '%"+txtBuscar.getText().trim().toUpperCase()+"%' "
               + "ORDER BY 2 ASC", jTable1, tipo_de_pst,par,DB.con,this,new int[]{1,2}).start();   
            }
 }   
        
    private void cargarDetalle(){
            limpiarTabla(jTable3); 
            new TblThread("SELECT d.tveh,d.est_des FROM v_dmod d, v_tveh t "
                    + "WHERE t.tveh_cod=d.tveh_cod AND d.mod_cod=?",
                    jTable3,new int[]{1},new String[]{aop},DB.con,this,new int[]{2,2}).start();  
 } 
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnExit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtCod = new javax.swing.JTextField();
        txtDes = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        cboTveh = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        btnNullDet = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();

        setTitle("Mantener Modelos");
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

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/door-in-icon.png"))); // NOI18N
        btnExit.setText("Salir");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        btnDelete.setText("Anular");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Basicos"));

        txtCod.setEditable(false);
        txtCod.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCod.setEnabled(false);

        txtDes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDesFocusGained(evt);
            }
        });
        txtDes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDesKeyReleased(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");

        jLabel1.setText("Descripcion");

        jLabel6.setText("Codigo");

        jLabel2.setText("Marca");

        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*");

        txtMar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMarFocusGained(evt);
            }
        });
        txtMar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("<ENTER>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDes, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txtMar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txtMar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del Modelo"));

        jLabel3.setText("Tipo de Vehiculo");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de Vehiculo", "Estado"
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
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
        }

        cboTveh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- ELEGIR --" }));
        cboTveh.setEnabled(false);
        cboTveh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTvehActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Anulado del detalle"));

        btnNullDet.setText("Anular Detalle");
        btnNullDet.setEnabled(false);
        btnNullDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNullDetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(btnNullDet)
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNullDet)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cboTveh, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTveh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Filtrar Por");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("<ENTER>");

        jLabel9.setText("Ver");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigentes", "Anulados" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cboSee, 0, 139, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de Vehiculo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(200);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnSave)
                    .addComponent(btnCancelar)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
al_editar();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
al_borrar();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    validar_blancos();
        if(isOk==0){
          clean();
          cargarTabla();
    ManageButtons(1);  
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
//        DB.rollback();
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
        ManageButtons(2);
txtCod.setText(aop);
cargarDatos();
cargarDetalle();
    }//GEN-LAST:event_jTable1MousePressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10)cargarTabla();
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
if(txtBuscar.getText().trim().isEmpty()){
    limpiarTabla(jTable1);
    limpiarTabla(jTable2);
}else{
    DB.toUpperCaseState(txtBuscar,evt);
}
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
new CboThreadGT("SELECT mar_cod,mar_des FROM v_mar WHERE est_cod='VIG' ORDER BY 2 ASC", cboSee, DB.con,"-- ELEGIR --").start();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cboTvehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTvehActionPerformed
if(cboTveh.getSelectedIndex()>0){
     LoadWithObjects tveh=(LoadWithObjects)cboTveh.getSelectedItem();

 if(validarDuplicados(String.valueOf(tveh.getCod()+" - "+tveh.getDes()))==-1){//no hay duplicados
     if(bandera==2){
           modelo=(DefaultTableModel)jTable2.getModel();
           modelo.addRow(new Object[]{String.valueOf(tveh.getCod()+" - "+tveh.getDes()),"VIG"}); 
           arrayListBAN.add(1);
           arrayListCOD.add(txtCod.getText());
           arrayListMAR.add(DB.getCod(txtMar.getText()));
           arrayListTVEH.add(tveh.getCod());
     }else{
                 if(first){//---------------wtf
                  limpiarTabla(jTable2);   
                  first=false;
                 }//------------------------fin wtf
         modelo.addRow(new Object[]{String.valueOf(tveh.getCod()+" - "+tveh.getDes()),"VIG"});    
     }
    cboTveh.removeItem(cboTveh.getSelectedItem());
cboTveh.setSelectedIndex(0);
         }else{
     //error, ya existe dicho serv 
              }
}
    }//GEN-LAST:event_cboTvehActionPerformed

    private void txtDesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesKeyPressed
if(evt.getKeyCode()==10){
//      validar_blancos();
     // if(isOk==0){
//  new JoptionPaneContimeOut().visualizaDialogo(this,"Se guardó el registro con éxito.\nAhora seleccione los tipos de vehiculo que posee dicho Modelo,"
//          + "\nal finalizar presione el botón Guardar","Mensaje del Sistema",5500,2);
    if(!txtMar.getText().trim().isEmpty() && !txtDes.getText().trim().isEmpty()){
       txtMar.setEnabled(false);
  txtDes.setEnabled(false); 
  cboTveh.setEnabled(true);
  cboTveh.requestFocus();   
    }
   // }   
}
    }//GEN-LAST:event_txtDesKeyPressed

    private void txtMarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtMarKeyTyped

    private void txtMarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarKeyReleased
DB.toUpperCaseState(txtMar, evt);
    }//GEN-LAST:event_txtMarKeyReleased

    private void txtMarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarKeyPressed
if(evt.getKeyCode()==10 ){
    if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.autocomplete(txtMar,"mar_cod,' - ',mar_des","v_mar","AND est_cod='VIG'",this,txtDes,DB.con);
}
    }//GEN-LAST:event_txtMarKeyPressed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
if(jTabbedPane1.getSelectedIndex()==0){
    pga.ocultarauto();
//    PopupFactory factory = PopupFactory.getSharedInstance();
//PGa_vStandard.popup = factory.getPopup(txtMar, PGa_vStandard.lista, txtMar.getLocationOnScreen().x, txtMar.getLocationOnScreen().y+txtMar.getHeight());
//if(PGa_vStandard.popup!=null)PGa_vStandard.popup.show();
}
    }//GEN-LAST:event_formComponentMoved

    private void txtDesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDesFocusGained
if(bandera==2)txtDes.selectAll();
    }//GEN-LAST:event_txtDesFocusGained

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0)filter="est_cod='VIG'";
if(cboFilter.getSelectedIndex()==1)filter="est_cod='ANU'";
cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void txtMarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMarFocusGained
if(bandera==2)txtMar.selectAll();
    }//GEN-LAST:event_txtMarFocusGained

    private void btnNullDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullDetActionPerformed
                if(jTable2.getSelectedRow()==-1){
            JOptionPane.showMessageDialog( this,"No seleccionó ningún Registro","Atención", JOptionPane.INFORMATION_MESSAGE); 
        }else{
                        arrayListBAN.add(3);
                        arrayListMAR.add(DB.getCod(txtMar.getText()));
                        arrayListCOD.add(txtCod.getText());
                        arrayListTVEH.add(DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString()));
                                
                        jTable2.setValueAt("ANU",jTable2.getSelectedRow(), 1);
                        btnNullDet.setEnabled(false);
                } 
    }//GEN-LAST:event_btnNullDetActionPerformed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        if(bandera!=1 && jTable2.getValueAt(jTable2.getSelectedRow(),1).toString().equals("VIG")){
        btnNullDet.setEnabled(true);
        }else{
        btnNullDet.setEnabled(false); 
        }
    }//GEN-LAST:event_jTable2MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNullDet;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboTveh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDes;
    private javax.swing.JTextField txtMar;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
    bandera=1;
    ManageButtons(3);
    manageCboTveh();
cod=DB.getDBcod("mod_cod","v_mod");
txtCod.setText(cod);
txtMar.requestFocus();
}
private void al_editar(){
    ManageButtons(3);    
    manageCboTveh();
    bandera=2;
    DB.setDataFromJtable(jTable3, jTable2);
    modelo=(DefaultTableModel)jTable2.getModel();
    txtMar.requestFocus();
}

private void al_borrar(){
    bandera=3;
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)save4EditableDetails();
clean();
cargarTabla();
ManageButtons(1);
}

private void manageCboTveh(){
            if(!isLoaded){//-- :V , verifica si ya se cargo antes, o si faltan reg en el combo para cargar
    Nro_de_Reg=DB.CargarCombo("SELECT tveh_cod,tveh_des FROM v_tveh WHERE est_cod='VIG'", cboTveh);
    CboTvehArray=DB.getComboArray(cboTveh);
    isLoaded=true;
}
        if(Nro_de_Reg>(cboTveh.getItemCount()-1)){//carga de forma offline el combo
            DB.setComboArray(cboTveh, CboTvehArray);
        }
}

private void clean(){
    txtDes.setText("");
    txtCod.setText("");
    txtBuscar.setText("");
    cboTveh.setSelectedIndex(0);
    if(pga.popup!=null){
     pga.show_jlist=false;
    pga.refresh_();   
    }    
    cboTveh.setEnabled(false);
    txtMar.setText("");
    limpiarTabla(jTable2);
    limpiarTabla(jTable3);
    aop=null;
    isOk=0;
}

private int validarDuplicados(String des){
      for (int i = 0; i < jTable2.getRowCount(); i++) {
          if(jTable2.getValueAt(i, 0).toString().equals(des))return i;
             }
      return -1;//no hay duplicados
  }

  private void limpiarTabla(JTable tabla){
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        while(tabla.getRowCount() != 0)
            modelo.removeRow(0);
}
  
      private void ManageButtons(int estado){
     if(estado==1){//boot
         btnAdd.setEnabled(true);
         btnAdd.requestFocusInWindow();
         btnEdit.setEnabled(false);
         btnDelete.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancelar.setEnabled(false);
         jTabbedPane1.setSelectedIndex(1);
         //----------
         txtDes.setEnabled(true);
         txtMar.setEnabled(true);
         cboTveh.setEnabled(false);
     }
          if(estado==2){//pressed
         btnAdd.setEnabled(false);
         btnEdit.setEnabled(true);
         btnDelete.setEnabled(true);
         btnSave.setEnabled(false);
         btnCancelar.setEnabled(true);
         //--------------
         txtDes.setEnabled(true);
         txtMar.setEnabled(true);
         cboTveh.setEnabled(false);
     }
               if(estado==3){//editing
         btnAdd.setEnabled(false);
         btnEdit.setEnabled(false);
         btnDelete.setEnabled(false);
         btnSave.setEnabled(true);
         btnCancelar.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
     }
 }
      
     private void validar_blancos(){ 
         int n=0;
         if(bandera==1){
                   if(txtDes.getText().trim().isEmpty() || txtMar.getText().trim().isEmpty() || jTable2.getRowCount()==0){
             JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder", "Atención", JOptionPane.WARNING_MESSAGE); 
             txtMar.requestFocus();
             isOk=1;
             }else{
        save4EditableDetails();
     }    
         }else{
            for (int i = 0; i < jTable2.getRowCount(); i++) {
                if(!jTable2.getValueAt(i,1).toString().equals("VIG")){//pregunta si esta en anu
                    n++;
                }
            }
                if(n<jTable2.getRowCount() /*&& !det_banIsNull(det_ban)*/){//save
                     save4EditableDetails();
                }else{//
                     JOptionPane.showMessageDialog(this,"No se puede guardar un Modelo con todos los detalles anulados o nulos","Atención", JOptionPane.WARNING_MESSAGE);
                }
         }
}
     
private void save4EditableDetails(){
         String[][] par = null;
         int row;
         int[] tipos_de_get = {1,1,1,2};
         Object[] parametros = {bandera,cod,DB.getCod(txtMar.getText().trim()),txtDes.getText().trim()};
         switch(bandera){
             case 1://-----------------------------------------------------------------------------------------------------------------------------------------
               row=jTable2.getRowCount();                         
             par = new String [row][4];
                    
             for (int i = 0; i < row; i++) {//------------
          par[i][0]="1";
          par[i][1]=DB.getCod(txtMar.getText());
          par[i][2]=txtCod.getText();          
          par[i][3]=DB.getCod(jTable2.getValueAt(i, 0).toString());
             }//------------------------------------------    
    isOk=DB.executeCst("abm_mod(?,?,?,?)", tipos_de_get,parametros,false);
    if(isOk==0)isOk=DB.executeCstDetails("abm_mod2(?,?,?,?)", new int[]{1,1,1,1}, par);
    if(isOk==0){
        DB.EmitirMensaje(this, bandera);
        DB.commit();
    }
             break;
             case 2://-----------------------------------------------------------------------------------------------------------------------------------------
                 Object[]par1=arrayListBAN.toArray();
                 Object[]par2=arrayListMAR.toArray();
                 Object[]par3=arrayListCOD.toArray();
                 Object[]par4=arrayListTVEH.toArray();
                 par = new String [par1.length][4];
                        for (int i = 0; i < arrayListBAN.size(); i++) {//meten en un array[][] los 3 array[]dinamicos
                            par[i][0]=par1[i].toString();
                            par[i][1]=par2[i].toString();
                            par[i][2]=par3[i].toString();
                            par[i][3]=par4[i].toString();
                                                          }
                 
               parametros[1]=aop; 
    isOk=DB.executeCst("abm_mod(?,?,?,?)", tipos_de_get,parametros,false);
    if(!arrayListBAN.isEmpty() && isOk==0)isOk=DB.executeCstDetails("abm_mod2(?,?,?,?)", new int[]{1,1,1,1}, par);
    if(isOk==0){
        DB.EmitirMensaje(this, bandera);
        DB.commit();
    }
             break;
             case 3://-----------------------------------------------------------------------------------------------------------------------------------------
                     isOk=DB.executeCst("abm_mod(?,?,?,?)",new int[] {1,1,-1,-1},
            new Object[]{bandera,aop,null,null},true);
    if(isOk==0)DB.EmitirMensaje(this, bandera);
             break;
         }
     }
     
     private void cargarDatos(){
         LoadWithObjects mar=(LoadWithObjects) cboSee.getSelectedItem();
         //LoadWithObjects mar = new LoadWithObjects(mar_filter.getCod(),mar_filter.getDes());
txtDes.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
txtMar.setText(mar.getCod()+" - "+mar.getDes());
     }
     
     private void ManageClosing(){
         pga.ocultarauto();
       dispose();
       DB.shutdown();
       Menu.menMod.setEnabled(true);
     }
}
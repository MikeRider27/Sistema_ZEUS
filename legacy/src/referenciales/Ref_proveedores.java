
package referenciales;

import threads.TblThread;
import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import config.conectarDB;


/**
 *
 * @author schafler92
 */
public class Ref_proveedores extends javax.swing.JInternalFrame {
    
    private final conectarDB DB;
    int bandera,isOk=0;
    String aop,cod,see=" WHERE est_cod='VIG'";

    public Ref_proveedores() {
        initComponents();
          DB = new conectarDB();
          ManageButtons(1);
    }   
        
 private void cargarTabla(){
     limpiarTabla();
     if(txtBuscar.getText().trim().length()>1){
        new TblThread("SELECT prov_cod,prov_des,prov_dir,prov_tel,prov_mail,prov_ruc FROM v_prov "+see+" AND prov_des LIKE '%"+txtBuscar.getText().trim().toUpperCase()+"%'"
                + " ORDER BY 2 ASC", jTable1,null,null,DB.con,this,new int[]{1,2,2,1,2,2,4}).start(); 
     }
 }  
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        txtDes = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        jCheckBox3.setText("jCheckBox3");

        jLabel7.setText("jLabel7");

        setTitle("Mantener Proveedores");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Imagen2.png"))); // NOI18N

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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Codigo");

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDesKeyTyped(evt);
            }
        });

        jLabel1.setText("Descripcion");

        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("*");

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel3.setText("Telefono");

        txtTel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelFocusGained(evt);
            }
        });
        txtTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelKeyTyped(evt);
            }
        });

        jLabel5.setText("Direccion");

        txtDir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDirFocusGained(evt);
            }
        });
        txtDir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDirKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDirKeyTyped(evt);
            }
        });

        jLabel6.setText("Correo Electronico");

        txtMail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMailFocusGained(evt);
            }
        });
        txtMail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMailKeyPressed(evt);
            }
        });

        jLabel8.setText("RUC");

        txtRuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRucFocusGained(evt);
            }
        });
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("*");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(150, 150, 150))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Direccion", "Telefono", "Mail", "RUC"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(140);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(230);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Ver");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigentes", "Anulados" }));
        cboSee.setName("cboSee"); // NOI18N
        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
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

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("<ENTER>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(87, 87, 87)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnCancelar)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=(jTable1.getValueAt(jTable1.getSelectedRow(),0).toString());
txtCod.setText(aop);
ManageButtons(2);
cargarDatos();
    }//GEN-LAST:event_jTable1MousePressed

    private void txtDirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDirKeyReleased
DB.toUpperCaseState(txtDir,evt);
    }//GEN-LAST:event_txtDirKeyReleased

    private void txtMailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMailKeyPressed
if(evt.getKeyCode() == 10){
        validar_blancos();
        if(isOk==0){
          clean();
          cargarTabla();
    ManageButtons(1);  
        }
    }
    }//GEN-LAST:event_txtMailKeyPressed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
DB.fieldControl(4,evt);
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtTelKeyTyped

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
if(cboSee.getSelectedIndex()==0)see=" WHERE est_cod='VIG'";
if(cboSee.getSelectedIndex()==1)see=" WHERE est_cod='ANU'";
txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla(); 
    }//GEN-LAST:event_cboSeeActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
if(txtBuscar.getText().trim().isEmpty()){
    limpiarTabla();
}else{
    DB.toUpperCaseState(txtBuscar,evt);
}
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtDirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDirKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtDirKeyTyped

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtDesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesKeyTyped
DB.fieldControl(3, evt);
    }//GEN-LAST:event_txtDesKeyTyped

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10){
    cargarTabla();
}
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtRucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRucFocusGained
if(bandera==2)txtRuc.selectAll();
    }//GEN-LAST:event_txtRucFocusGained

    private void txtDesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDesFocusGained
if(bandera==2)txtDes.selectAll();
    }//GEN-LAST:event_txtDesFocusGained

    private void txtDirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDirFocusGained
if(bandera==2)txtDir.selectAll();
    }//GEN-LAST:event_txtDirFocusGained

    private void txtTelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusGained
if(bandera==2)txtTel.selectAll();
    }//GEN-LAST:event_txtTelFocusGained

    private void txtMailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMailFocusGained
if(bandera==2)txtMail.selectAll();
    }//GEN-LAST:event_txtMailFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDes;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtRuc;
    public static javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
        bandera=1;
ManageButtons(3);
cod=DB.getDBcod("prov_cod","v_prov");
txtCod.setText(cod);
txtRuc.requestFocus();
}
private void al_editar(){
    ManageButtons(3);
    txtRuc.requestFocus();
    bandera=2;
}

private void al_borrar(){
        bandera=3;
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)save();
clean();
cargarTabla();
ManageButtons(1);
}

private void clean(){
    txtDes.setText("");
    txtRuc.setText("");
    txtCod.setText("");
    txtDir.setText("");
    txtMail.setText("");
    txtTel.setText("");
    txtBuscar.setText("");
    aop=null;
}

  private void limpiarTabla(){
        DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
        while(this.jTable1.getRowCount() != 0)modelo.removeRow(0);
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
         if(cboSee.getSelectedIndex()==0){
             btnEdit.setEnabled(true);
         }else{
             btnEdit.setEnabled(false);
         }
         if(cboSee.getSelectedIndex()==0){
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
     }
 }
  
     private void validar_blancos(){      
         if(txtDes.getText().trim().isEmpty() || txtTel.getText().length()<8 || txtRuc.getText().length()<8){
             JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder", "Atención", JOptionPane.WARNING_MESSAGE); 
             txtRuc.requestFocus();
             isOk=1;
             }else{
        save();
     } 
}
     
     private void save(){
           if(bandera==2 || bandera==3)cod=aop;
          int[] tipos_de_get = {1,1,2,2,1,2,2};
    String[] parametros = {String.valueOf(bandera),cod,txtDes.getText(),txtDir.getText(),txtTel.getText(),txtMail.getText(),txtRuc.getText()};
    isOk=DB.executeCst("abm_prov(?,?,?,?,?,?,?)", tipos_de_get,parametros,true);
    if(isOk==0)DB.EmitirMensaje(this, bandera);
     }

     private void cargarDatos(){
txtDes.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
txtDir.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
txtRuc.setText(jTable1.getValueAt(jTable1.getSelectedRow(),5).toString());
txtTel.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
txtMail.setText(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString()); 
     }
     
     private void ManageClosing(){
       dispose();
       DB.shutdown();
       Menu.menProv.setEnabled(true);
     }
}


package referenciales;

import threads.CboThreadGT;
import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import threads.TblThread;
import config.LoadWithObjects;
import config.conectarDB;
import config.PGa_vStandard;

/**
 *
 * @author schafler92
 */
public class Ref_depositos extends javax.swing.JInternalFrame {
    
    private final conectarDB DB;
    int bandera,isOk=0;
    String aop,cod,filter="AND est_cod='VIG'";
    PGa_vStandard pga=null;
    
    public Ref_depositos() {
        initComponents();
        DB = new conectarDB();
        ManageButtons(1);
        pga=new PGa_vStandard();
    }

        private void cargarTabla(){
            limpiarTabla();
        if(cboSee.getSelectedIndex()>0){
    int[] tipo_de_pst={2}; 
        String [] par={cboSee.getSelectedItem().toString()}; 
        new TblThread("SELECT dep_cod,dep_des FROM v_dep WHERE suc_des=? "
                +filter+" ORDER BY 2 ASC", jTable1, tipo_de_pst,par,DB.con,this,new int[]{1,2,2}).start();  
}   
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
        jLabel13 = new javax.swing.JLabel();
        txtSuc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();

        setTitle("Mantener Depositos");
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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDesKeyTyped(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");

        jLabel1.setText("Descripcion");

        jLabel6.setText("Codigo");

        jLabel2.setText("Sucursal");

        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("*");

        txtSuc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSucFocusGained(evt);
            }
        });
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("<ENTER>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDes, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(txtSuc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))))
                .addGap(118, 118, 118))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel13)
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Ver");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        jLabel4.setText("Filtrar Por");

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
                .addGap(192, 192, 192)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cboFilter, 0, 100, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnSave)
                    .addComponent(btnCancelar)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }//GEN-LAST:event_jTable1MousePressed

    private void txtDesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesKeyPressed
if(evt.getKeyCode()==10){
    validar_blancos();
    if(isOk==0){
      clean();
      cargarTabla();
      ManageButtons(1);  
    }
}
    }//GEN-LAST:event_txtDesKeyPressed

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
        cargarTabla();
    }//GEN-LAST:event_cboSeeActionPerformed

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0)filter="AND est_cod='VIG'";
if(cboFilter.getSelectedIndex()==1)filter="AND est_cod='ANU'";
cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void txtDesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDesKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtDesKeyTyped

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
 new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE est_cod='VIG' ORDER BY 2 ASC",cboSee,DB.con,"-- ELEGIR --").start();
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtSucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyPressed
if(evt.getKeyCode()==10 ){
    if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.autocomplete(txtSuc,"suc_cod,' - ',suc_des","v_suc","AND est_cod='VIG'",this,txtDes,DB.con);
}
    }//GEN-LAST:event_txtSucKeyPressed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
if(jTabbedPane1.getSelectedIndex()==0){
    pga.ocultarauto();
//    PopupFactory factory = PopupFactory.getSharedInstance();
//PGa_vStandard.popup = factory.getPopup(txtSuc, PGa_vStandard.lista, txtSuc.getLocationOnScreen().x, txtSuc.getLocationOnScreen().y+txtSuc.getHeight());
//if(PGa_vStandard.popup!=null)PGa_vStandard.popup.show();
}
    }//GEN-LAST:event_formComponentMoved

    private void txtSucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtSucKeyTyped

    private void txtSucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSucKeyReleased
DB.toUpperCaseState(txtSuc, evt);
    }//GEN-LAST:event_txtSucKeyReleased

    private void txtDesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDesFocusGained
if(bandera==2)txtDes.selectAll();
    }//GEN-LAST:event_txtDesFocusGained

    private void txtSucFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSucFocusGained
if(bandera==2)txtSuc.selectAll();
    }//GEN-LAST:event_txtSucFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboFilter;
    public static javax.swing.JComboBox cboSee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtDes;
    private javax.swing.JTextField txtSuc;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
    bandera=1;
    ManageButtons(3);
cod=DB.getDBcod("dep_cod","v_dep");
txtCod.setText(cod);
txtSuc.requestFocus();
}
private void al_editar(){
    ManageButtons(3);
    txtSuc.requestFocus();
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
    txtCod.setText("");
    txtSuc.setText("");
    if(pga.popup!=null){
     pga.show_jlist=false;
    pga.refresh_();   
    }
    aop=null;
    isOk=0;
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
         btnDelete.setEnabled(false);
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
             btnDelete.setEnabled(true);
         }else{
             btnDelete.setEnabled(false);
         }
         btnSave.setEnabled(false);
         btnCancelar.setEnabled(true);
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
         if(txtDes.getText().trim().isEmpty() || txtSuc.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder", "Atención", JOptionPane.WARNING_MESSAGE);
             txtDes.requestFocus();
             isOk=1;
         }else{
               save();
         }  
     } 
     
     private void save(){
         if(bandera==2 || bandera==3)cod=aop;
    String[] parametros = {String.valueOf(bandera),cod,DB.getCod(txtSuc.getText()).trim(),txtDes.getText().trim()};
    isOk=DB.executeCst("abm_dep(?,?,?,?)", new int[]{1,1,1,2},parametros,true);
     if(isOk==0)DB.EmitirMensaje(this, bandera);
     }
     
     private void cargarDatos(){
         LoadWithObjects suc=(LoadWithObjects)cboSee.getSelectedItem();
txtDes.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
txtSuc.setText(String.valueOf(suc.getCod()+" "+suc.getDes()));
     }
     
     private void ManageClosing(){
         pga.ocultarauto();
       dispose();
       DB.shutdown();
       Menu.menDep.setEnabled(true);
     }
}

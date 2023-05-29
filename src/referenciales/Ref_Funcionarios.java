
package referenciales;

import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import threads.DoubleCboThreadGT;
import threads.TblThread;
import config.HashMd5;
import config.LoadWithObjects;
import config.LoadWithObjects2Data;
import config.PGa_vStandard;
import config.conectarDB;

/**
 *
 * @author schafler92
 */
public class Ref_Funcionarios extends javax.swing.JInternalFrame {
    
    private final conectarDB DB;
    int bandera,isOk=0;
    PGa_vStandard pga=null;
    boolean isLoaded;
    String aop,cod,buscar=" WHERE emp_nom LIKE ",filter=" AND est_cod='VIG'";

    public Ref_Funcionarios() {
        initComponents();
          DB = new conectarDB();
          ManageButtons(1);
          pga=new PGa_vStandard();
    }

        private void cargarTabla(){
                 limpiarTabla();
  if(txtBuscar.getText().trim().length()>1){
        new TblThread("SELECT emp_ci,emp_nom,emp_ape,emp_cel,car,suc,est_des FROM v_func "+buscar+" "
     + "'%"+txtBuscar.getText().trim().toUpperCase()+"%' AND suc LIKE '%"+cboSee.getSelectedItem()+"%' "+filter+" "
                + "ORDER BY 3 ASC", jTable1,null,null,DB.con,this,new int[]{1,2,2,1,2,2,4}).start();   
            }
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
        jPanel5 = new javax.swing.JPanel();
        txtApe = new javax.swing.JTextField();
        txtNme = new javax.swing.JTextField();
        txtCed = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtDir = new javax.swing.JTextField();
        txtCel = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        txtNac = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cboCargo = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboSuc = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIng = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEgr = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        cboBuscar = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnSee = new javax.swing.JButton();

        setTitle("Mantener Funcionarios");
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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Funcionario"));
        jPanel5.setNextFocusableComponent(jDateChooser1);

        txtApe.setNextFocusableComponent(txtCel);
        txtApe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApeFocusGained(evt);
            }
        });
        txtApe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApeKeyTyped(evt);
            }
        });

        txtNme.setNextFocusableComponent(txtApe);
        txtNme.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNmeFocusGained(evt);
            }
        });
        txtNme.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNmeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNmeKeyTyped(evt);
            }
        });

        txtCed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCed.setNextFocusableComponent(txtNme);
        txtCed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCedFocusGained(evt);
            }
        });
        txtCed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedKeyTyped(evt);
            }
        });

        jLabel2.setText("Nro de Cedula");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

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

        txtCel.setNextFocusableComponent(txtDir);
        txtCel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCelFocusGained(evt);
            }
        });
        txtCel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelKeyTyped(evt);
            }
        });

        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("*");

        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setText("*");

        jLabel9.setText("Nacionalidad");

        jLabel8.setText("Direccion");

        jLabel7.setText("Celular");

        jLabel4.setText("Apellidos");

        jLabel1.setText("Nombres");

        jLabel14.setText("F. de Nacimiento");

        jDateChooser1.setNextFocusableComponent(txtNac);

        jLabel25.setForeground(new java.awt.Color(255, 0, 0));
        jLabel25.setText("*");

        txtNac.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNacFocusGained(evt);
            }
        });
        txtNac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNacKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNacKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("<ENTER>");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDir, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19))
                            .addComponent(txtApe, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNme, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(233, 233, 233)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNac, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel14)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Usuario"));

        txtUser.setEnabled(false);
        txtUser.setNextFocusableComponent(txtPass);
        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUserFocusGained(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUserKeyTyped(evt);
            }
        });

        txtPass.setEnabled(false);
        txtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPassFocusGained(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        jLabel20.setText("Usuario");

        jLabel21.setText("Contraseña");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(txtUser))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Puesto Laboral"));

        cboCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- ELEGIR --" }));
        cboCargo.setNextFocusableComponent(txtUser);
        cboCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCargoActionPerformed(evt);
            }
        });

        jLabel13.setText("Cargo");

        jLabel10.setText("Sucursal");

        cboSuc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- ELEGIR --" }));
        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setText("*");

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        jLabel11.setText("F. de Ingreso");

        txtIng.setEnabled(false);

        jLabel12.setText("F. de Egreso");

        txtEgr.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIng)
                    .addComponent(txtEgr)
                    .addComponent(cboCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboSuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtIng, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtEgr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setText("Ver");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigentes", "Anulados" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel31.setText("Buscar Por");

        cboBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombres", "Apellidos", "Usuario" }));
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("<ENTER>");

        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtrar Por");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel6))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(460, 460, 460)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(cboFilter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombres", "Apellidos", "Celular", "Cargo", "Sucursal"
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        btnSee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/informes.png"))); // NOI18N
        btnSee.setText("Ver Datos");
        btnSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSee)
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
                    .addComponent(btnExit)
                    .addComponent(btnSee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
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
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
    validar_blancos();
    if(isOk==0){
     clean();
     ManageButtons(1);
    }    
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
clean();
ManageButtons(1);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
ManageClosing();
}else{
    if(txtCed.getText().trim().isEmpty()){
ManageClosing();
    }else{
        if(!btnSave.isEnabled()){
            ManageClosing();
        }else{
               if (JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       ManageClosing();
   }   
        }
    }
}
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtNmeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNmeKeyReleased
DB.toUpperCaseState(txtNme,evt);
    }//GEN-LAST:event_txtNmeKeyReleased

    private void txtNmeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNmeKeyTyped
DB.fieldControl(1,evt);
    }//GEN-LAST:event_txtNmeKeyTyped

    private void txtApeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeKeyTyped
DB.fieldControl(1,evt);
    }//GEN-LAST:event_txtApeKeyTyped

    private void txtCedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtCedKeyTyped

    private void txtCelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtCelKeyTyped

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=jTable1.getValueAt(jTable1.getSelectedRow(),0).toString();
ManageButtons(2);
txtCed.setText(aop);
    }//GEN-LAST:event_jTable1MousePressed

    private void txtApeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApeKeyReleased
DB.toUpperCaseState(txtApe,evt);
    }//GEN-LAST:event_txtApeKeyReleased

    private void txtDirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDirKeyReleased
DB.toUpperCaseState(txtDir,evt);
    }//GEN-LAST:event_txtDirKeyReleased

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0)filter=" AND est_cod='VIG'";
if(cboFilter.getSelectedIndex()==1)filter=" AND est_cod='ANU'";
txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void cboBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBuscarActionPerformed
if(cboBuscar.getSelectedIndex()==0)buscar=" WHERE emp_nom LIKE ";
if(cboBuscar.getSelectedIndex()==1)buscar=" WHERE emp_ape LIKE ";
if(cboBuscar.getSelectedIndex()==2)buscar=" WHERE emp_user LIKE ";
txtBuscar.setText("");
txtBuscar.requestFocus();
    }//GEN-LAST:event_cboBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
if(txtBuscar.getText().trim().isEmpty()){
    limpiarTabla();
}else{
DB.toUpperCaseState(txtBuscar,evt);    
}
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
DB.fieldControl(1,evt);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
DB.toUpperCaseState(txtUser,evt);
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
if(evt.getKeyCode()==10){
   validar_blancos();
    if(isOk==0){
     clean();
     ManageButtons(1);
    }    
}
    }//GEN-LAST:event_txtPassKeyPressed

    private void txtDirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDirKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtDirKeyTyped

    private void txtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyTyped
DB.fieldControl(1,evt);
    }//GEN-LAST:event_txtUserKeyTyped

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
new DoubleCboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE est_cod='VIG' ORDER BY 1 ASC", cboSee, cboSuc,DB.con).start();
    }//GEN-LAST:event_formInternalFrameOpened

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10)cargarTabla();
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSeeActionPerformed

    private void btnSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeActionPerformed
        ManageButtons(4);
        cargarDatos();
        if(!isLoaded)cargarCbos();
    }//GEN-LAST:event_btnSeeActionPerformed

    private void txtCedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCedFocusGained
if(bandera==2)txtCed.selectAll();
    }//GEN-LAST:event_txtCedFocusGained

    private void txtNmeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNmeFocusGained
if(bandera==2)txtNme.selectAll();
    }//GEN-LAST:event_txtNmeFocusGained

    private void txtApeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApeFocusGained
if(bandera==2)txtApe.selectAll();
    }//GEN-LAST:event_txtApeFocusGained

    private void txtCelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCelFocusGained
if(bandera==2)txtCel.selectAll();
    }//GEN-LAST:event_txtCelFocusGained

    private void txtDirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDirFocusGained
if(bandera==2)txtDir.selectAll();
    }//GEN-LAST:event_txtDirFocusGained

    private void txtNacKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacKeyPressed
if(evt.getKeyCode()==10 ){
    if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.autocomplete(txtNac,"nac_cod,' - ',nac_des","v_nac","AND est_cod='VIG'",this,cboSuc,DB.con);
}
    }//GEN-LAST:event_txtNacKeyPressed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
if(jTabbedPane1.getSelectedIndex()==0){
   pga.ocultarauto();
//    PopupFactory factory = PopupFactory.getSharedInstance();
//PGa_vStandard.popup = factory.getPopup(txtMar, PGa_vStandard.lista, txtMar.getLocationOnScreen().x, txtMar.getLocationOnScreen().y+txtMar.getHeight());
//if(PGa_vStandard.popup!=null)PGa_vStandard.popup.show();
}
    }//GEN-LAST:event_formComponentMoved

    private void txtNacFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNacFocusGained
if(bandera==2)txtNac.selectAll();
    }//GEN-LAST:event_txtNacFocusGained

    private void txtNacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacKeyTyped
DB.fieldControl(1, evt);
    }//GEN-LAST:event_txtNacKeyTyped

    private void txtNacKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacKeyReleased
DB.toUpperCaseState(txtNac, evt);
    }//GEN-LAST:event_txtNacKeyReleased

    private void cboCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCargoActionPerformed
if(cboCargo.getSelectedIndex()>0){
    LoadWithObjects2Data car=(LoadWithObjects2Data)cboCargo.getSelectedItem();
    if(String.valueOf(car.getCant()).trim().length()>0){
   txtUser.setEnabled(true);
   txtPass.setEnabled(true);
   txtUser.requestFocus();
}else{
   txtUser.setEnabled(false);
   txtPass.setEnabled(false);  
    }
}
    }//GEN-LAST:event_cboCargoActionPerformed

    private void cboSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSucActionPerformed

    private void txtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusGained
if(bandera==2)txtUser.selectAll();
    }//GEN-LAST:event_txtUserFocusGained

    private void txtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPassFocusGained
if(bandera==2)txtPass.selectAll();
    }//GEN-LAST:event_txtPassFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSee;
    private javax.swing.JComboBox cboBuscar;
    public static javax.swing.JComboBox cboCargo;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboSee;
    public static javax.swing.JComboBox cboSuc;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtApe;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCed;
    private javax.swing.JTextField txtCel;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtEgr;
    private javax.swing.JTextField txtIng;
    private javax.swing.JTextField txtNac;
    private javax.swing.JTextField txtNme;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
bandera=1;
ManageButtons(3);
txtCed.requestFocus();
if(!isLoaded)cargarCbos();
txtIng.setText(DB.ClientDate(1));
txtEgr.setText("¿Quién sabe?");//:V
}
private void al_editar(){
    if(!isLoaded)cargarCbos();
    ManageButtons(3);
    txtCed.requestFocus();
    bandera=2;
    cargarDatos();
}

private void al_nullear(){
    if(!isLoaded)cargarCbos();
    bandera=3;
    //cargarDatos();
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)save();
clean();
ManageButtons(1);
}

private void clean(){
    limpiarTabla();
    txtNme.setText("");
    txtCed.setText("");
    txtCel.setText("");
    txtApe.setText("");
    txtDir.setText("");
    txtPass.setText("");
    txtIng.setText("");
    txtEgr.setText("");
    txtUser.setText("");
    txtBuscar.setText("");
    cboCargo.setSelectedIndex(0);
    txtNac.setText("");
    cboSuc.setSelectedIndex(0);
    jDateChooser1.setDate(null);
    aop=null;    
    if(pga.popup!=null){
     pga.show_jlist=false;
    pga.refresh_();   
    }  
    txtPass.setEnabled(false);
    txtUser.setEnabled(false);
}

  private void limpiarTabla(){
        DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
        while(this.jTable1.getRowCount() != 0) modelo.removeRow(0);
  }
  
       private void ManageButtons(int estado){
     if(estado==1){//boot
         btnAdd.setEnabled(true);
         btnAdd.requestFocusInWindow();
         btnEdit.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnSee.setEnabled(false);
         btnCancelar.setEnabled(false);
         jTabbedPane1.setSelectedIndex(1);
     }
          if(estado==2){//pressed
         btnAdd.setEnabled(false);
         btnSee.setEnabled(true);
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
         btnSee.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(true);
         btnCancelar.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
     }
                if(estado==4){//ESPECIAL - Ver Detalles From jPopUpmenu
         btnAdd.setEnabled(false);
         btnEdit.setEnabled(false);
         btnNull.setEnabled(false);
         btnSee.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancelar.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
     }
 }
  
     private void validar_blancos(){      
         if(txtCed.getText().length()<4 || txtNme.getText().trim().isEmpty() || txtApe.getText().trim().isEmpty() || txtCel.getText().length()<8
    || txtNac.getText().trim().isEmpty() || cboSuc.getSelectedIndex() ==0 || cboCargo.getSelectedIndex() ==0 || !DB.is_A_valid_Date(jDateChooser1.getDate())){
             JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder", "Atención", JOptionPane.ERROR_MESSAGE);
             txtCed.requestFocus();
             isOk=1;
         }else{
               cod=txtCed.getText().trim();
               save();
         }         
     }
     
     private char[] getPass(){
    try {
        return txtPass.getPassword();
    } catch (Exception e) {
     JOptionPane.showMessageDialog(this,"No escribió ninguna Contraseña","Atención",JOptionPane.WARNING_MESSAGE);   
     return null;
    }finally{
        
    }
} 
     
     private void save(){
             int[] tipos_de_get = {1,1,1,1,2,2,1,2,2,2,3,1};
                 Object[] parametros ;
         if(bandera==1 || bandera==2){
             if(bandera==2)cod=aop;
                       LoadWithObjects2Data car=(LoadWithObjects2Data) cboCargo.getSelectedItem();         
          LoadWithObjects suc=(LoadWithObjects) cboSuc.getSelectedItem(); 
parametros=new Object[] {bandera,cod,DB.getCod(txtNac.getText()),car.getCod(),
        txtNme.getText(),txtApe.getText(),txtCel.getText(),txtDir.getText(),
        txtUser.getText(),new HashMd5().hash(getPass()),jDateChooser1.getDate(),suc.getCod()};
         }else{//--------------bandera3
    tipos_de_get=null;
    tipos_de_get = new int[]{1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1};
    parametros=null;
    parametros= new Object[] {bandera,aop,null,null,
        null,null,null,null,
        null,null,null,DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString())};
         }    
    isOk=DB.executeCst("abm_func(?,?,?,?,?,?,?,?,?,?,?,?)", tipos_de_get,parametros,true);
    if(isOk==0)DB.EmitirMensaje(this, bandera);
     }
     
     private void cargarDatos(){
         DB.consultPst("SELECT emp_dir,emp_user,nac,date_ing,date_egr,emp_fnac FROM v_func WHERE emp_ci=?",new int[]{1},new String[]{aop},
                 new Object[]{txtDir,txtUser,txtNac,txtIng,txtEgr,jDateChooser1});
                 LoadWithObjects suc = new LoadWithObjects(DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(),5).toString()),
    DB.getDes(jTable1.getValueAt(jTable1.getSelectedRow(),5).toString()));
                 LoadWithObjects car = new LoadWithObjects(DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString()),
    DB.getDes(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString()));
                 //--------------------
                 txtNme.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
                 txtApe.setText(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString());
                 txtCel.setText(jTable1.getValueAt(jTable1.getSelectedRow(),3).toString());
                 cboSuc.setSelectedItem(suc);
                 cboCargo.setSelectedItem(car);
                 if(!txtUser.getText().trim().isEmpty()){
                     txtUser.setEnabled(true);
                     txtPass.setEnabled(true);
                 }else{
                     txtUser.setEnabled(false);
                     txtPass.setEnabled(false);
                 }
                     
             }    
     
private void cargarCbos(){//se puede cargar cbos desde boton add o edit, con este metodo no se carga 2 veces
isLoaded=true;
DB.CargarCombo2Data("SELECT car_cod,car_des,car_user FROM v_car WHERE est_cod='VIG' ORDER BY 2 ASC", cboCargo);
     }
     
private void ManageClosing(){
              dispose();
              DB.shutdown();
              Menu.menEmp.setEnabled(true);
          }
}

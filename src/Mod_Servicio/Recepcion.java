
package Mod_Servicio;

import threads.CboThreadGT;
import threads.TblThread;
import frames.Buscador;
import frames.Login;
import frames.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import config.conectarDB;
/**
 *
 * @author schafler92
 */
public class Recepcion extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    private PreparedStatement pst;
    private ResultSet rs;
    int bandera,isOk;
    String aop,cod,seg,orden="1 DESC",bus="AND cli LIKE",see="",mes="MONTH(ent_date)=MONTH(CURDATE())",year="YEAR(ent_date)=YEAR(CURDATE())",suc="AND suc_des='"+Login.suc+"'";
    
    public Recepcion() {
        initComponents();
        DB = new conectarDB(); 
        ManageButtons(1);
        cargarAño();
   }

  private void cargarTabla(){
      limpiarTabla();
           if(!txtBuscar.getText().trim().isEmpty()){
        new TblThread("SELECT ent_cod,ent_date,seg,veh,cli,suc_des,ent_sin,ent_estado,ent_date "
                + "FROM v_ent "
                + "WHERE "+mes+" "+see+" "+bus+" '%"+txtBuscar.getText().trim()+"%' "
                + "AND "+year+" "+suc
                + "ORDER BY "+orden, jTable2,null,null,DB.con,this,new int[]{1,2,2,2,2,2,2,2,2}).start();   
        }
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popUpSeeDetails = new javax.swing.JMenuItem();
        jTextField1 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtCli = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSuc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCodCli = new javax.swing.JTextField();
        txtCodVeh = new javax.swing.JTextField();
        txtDesVeh = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrep = new javax.swing.JTextField();
        txtSeg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtEstado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        chkKey = new javax.swing.JCheckBox();
        chkRadio = new javax.swing.JCheckBox();
        chkHerram = new javax.swing.JCheckBox();
        chkTazas = new javax.swing.JCheckBox();
        chkExt = new javax.swing.JCheckBox();
        chkBaliza = new javax.swing.JCheckBox();
        chkAux = new javax.swing.JCheckBox();
        chkCat = new javax.swing.JCheckBox();
        chkEq = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        cboComb = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtKm = new javax.swing.JTextField();
        chkGar = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cboOrder = new javax.swing.JComboBox();
        cboFilter = new javax.swing.JComboBox();
        cboSee = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cboBuscar = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cboAño = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboSuc = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        popUpSeeDetails.setText("Ver detalles del Registro");
        popUpSeeDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popUpSeeDetailsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popUpSeeDetails);

        jTextField1.setText("jTextField1");

        setTitle("Registro Recepcion de Vehiculos");
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Principales"));

        txtCli.setEditable(false);
        txtCli.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtCli.setEnabled(false);

        jLabel16.setText("Asegurado");

        jLabel1.setText("Codigo");

        txtCod.setEditable(false);
        txtCod.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCod.setEnabled(false);

        jLabel17.setText("Sucursal");

        txtSuc.setEditable(false);
        txtSuc.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtSuc.setEnabled(false);

        jLabel3.setText("Vehiculo");

        txtCodCli.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtCodVeh.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCodVeh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodVehKeyTyped(evt);
            }
        });

        txtDesVeh.setEnabled(false);

        jLabel14.setText("Cia. de Seguro");

        jLabel4.setText("Presup. Nº");

        txtPrep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrepKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrepKeyTyped(evt);
            }
        });

        jLabel8.setText("Fecha");

        txtDate.setEditable(false);
        txtDate.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        txtDate.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel16)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCodCli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(txtCod, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodVeh))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCli)
                            .addComponent(txtDesVeh)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrep, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(txtSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado de Recepción"));

        txtEstado.setEnabled(false);

        jLabel5.setText("Estado");

        jLabel22.setText("Total Presup.");

        jTextField3.setText("jTextField3");

        jLabel6.setText("jLabel6");

        jTextField2.setText("jTextField2");

        jLabel9.setText("jLabel9");

        jTextField4.setText("jTextField4");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEstado)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(jTextField2))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del Presupuesto"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Piezas", "Daño"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("Pieza");

        jLabel20.setText("Daño");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Botones de Gestion"));

        jButton2.setText("jButton2");

        jButton3.setText("jButton3");

        jButton4.setText("jButton4");

        jButton5.setText("jButton5");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Detalle de piezas", jPanel11);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setNextFocusableComponent(cboComb);
        jTextArea2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea2KeyReleased(evt);
            }
        });
        jScrollPane8.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Secundarios"));

        chkKey.setText("Llavero");
        chkKey.setNextFocusableComponent(chkCat);
        chkKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkKeyKeyPressed(evt);
            }
        });

        chkRadio.setText("Radio");
        chkRadio.setNextFocusableComponent(chkHerram);
        chkRadio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkRadioKeyPressed(evt);
            }
        });

        chkHerram.setText("Herram.");
        chkHerram.setNextFocusableComponent(chkEq);
        chkHerram.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkHerramKeyPressed(evt);
            }
        });

        chkTazas.setText("Tazas");
        chkTazas.setNextFocusableComponent(chkBaliza);
        chkTazas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkTazasKeyPressed(evt);
            }
        });

        chkExt.setText("Extintor");
        chkExt.setNextFocusableComponent(txtKm);
        chkExt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkExtKeyPressed(evt);
            }
        });

        chkBaliza.setText("Baliza");
        chkBaliza.setNextFocusableComponent(chkRadio);
        chkBaliza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkBalizaKeyPressed(evt);
            }
        });

        chkAux.setText("R.de Aux");
        chkAux.setNextFocusableComponent(chkExt);
        chkAux.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkAuxKeyPressed(evt);
            }
        });

        chkCat.setText("Gato");
        chkCat.setNextFocusableComponent(chkTazas);
        chkCat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkCatKeyPressed(evt);
            }
        });

        chkEq.setText("E. de Sonido");
        chkEq.setName(""); // NOI18N
        chkEq.setNextFocusableComponent(chkAux);
        chkEq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkEqKeyPressed(evt);
            }
        });

        jLabel11.setText("Combust.");

        cboComb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Elegir" }));
        cboComb.setNextFocusableComponent(jTextArea2);

        jLabel7.setText("Km");

        txtKm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtKm.setNextFocusableComponent(cboComb);
        txtKm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKmKeyTyped(evt);
            }
        });

        chkGar.setText("Rec. en Garantia");
        chkGar.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAux)
                            .addComponent(chkRadio)
                            .addComponent(chkKey))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCat)
                            .addComponent(chkHerram)
                            .addComponent(chkExt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkGar)
                            .addComponent(chkEq)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(chkTazas)
                                .addGap(18, 18, 18)
                                .addComponent(chkBaliza))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboComb, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkKey)
                    .addComponent(chkCat)
                    .addComponent(chkTazas)
                    .addComponent(chkBaliza))
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkEq)
                    .addComponent(chkRadio)
                    .addComponent(chkHerram))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAux)
                    .addComponent(chkExt)
                    .addComponent(chkGar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboComb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane3.addTab("Otros datos", jPanel13);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Ordenar Por");

        jLabel18.setText("Filtrar Por");

        jLabel19.setText("Ver");

        cboOrder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo Desc.", "Cia. de Seguro Asc.", "Sucursal Asc.", "Asegurado Asc." }));
        cboOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOrderActionPerformed(evt);
            }
        });

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Mes", "Mes Pasado", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar Por");

        cboBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asegurado", "Vehiculo", "Cia. de Seguro" }));
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

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("<ENTER>");

        cboAño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAñoActionPerformed(evt);
            }
        });

        jLabel10.setText("del");

        jLabel12.setText("en");

        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel18)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Recibido el", "Cia.", "Vehiculo", "Asegurado", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        jTable2.setComponentPopupMenu(jPopupMenu1);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(240);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(240);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(45);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/informes.png"))); // NOI18N
        jButton1.setText("Ver Detalles");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnAdd)
                    .addComponent(btnCancel)
                    .addComponent(btnExit)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
ManageClosing();
 Menu.menEnt.setEnabled(true);
}else{
        if(!btnSave.isEnabled()){
            ManageClosing();
        }else{
 if (JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       ManageClosing();
       Menu.menEnt.setEnabled(true);
   }   
        }    
}
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        validar_blancos();
        if(isOk==0){
        clean();
        cargarTabla();
        ManageButtons(1);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clean();
        cargarTabla();
        ManageButtons(1);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtKmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKmKeyTyped
     DB.fieldControl(2, evt);
    }//GEN-LAST:event_txtKmKeyTyped

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
aop=(jTable2.getValueAt(jTable2.getSelectedRow(),0).toString());
        ManageButtons(2);
        cargarDatos();
    }//GEN-LAST:event_jTable2MousePressed

    private void popUpSeeDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popUpSeeDetailsActionPerformed
 ManageButtons(4);  
        aop=(jTable2.getValueAt(jTable2.getSelectedRow(),0).toString());
        txtCod.setText(aop);
        cargarDatos();
    }//GEN-LAST:event_popUpSeeDetailsActionPerformed

    private void jTextArea2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyPressed
if(evt.getKeyCode()==10){
    validar_blancos();
    if(isOk==0){
      clean();
        cargarTabla();
        ManageButtons(1);  
    }  
    }        
    }//GEN-LAST:event_jTextArea2KeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10)cargarTabla();
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void cboOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOrderActionPerformed
if(cboOrder.getSelectedIndex()==0)orden="1 DESC";
if(cboOrder.getSelectedIndex()==1)orden="2 ASC";
if(cboOrder.getSelectedIndex()==2)orden="5 ASC";
if(cboOrder.getSelectedIndex()==3)orden="4 ASC";
cargarTabla();
    }//GEN-LAST:event_cboOrderActionPerformed

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

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
if(cboSee.getSelectedIndex()==0){
    see="";
}else{
    see="AND ent_estado='"+cboSee.getSelectedItem()+"'";
}
cargarTabla();
    }//GEN-LAST:event_cboSeeActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
       if(txtBuscar.getText().trim().isEmpty()){
           limpiarTabla();
       }else{
           DB.toUpperCaseState(txtBuscar, evt);
       }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void jTextArea2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea2KeyReleased
 
    }//GEN-LAST:event_jTextArea2KeyReleased

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
new CboThreadGT("SELECT * FROM v_comb ORDER BY 2 ASC", cboComb, DB.con,"Elegir").start();
if(Integer.parseInt(Login.sucCod)<=4){
    new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE suc_des!='"+Login.suc+"' ORDER BY 2 ASC", cboSuc, DB.con,"Esta Sucursal").start();
}else{
    cboSuc.removeAllItems();
    cboSuc.addItem("Esta Sucursal");
}
    }//GEN-LAST:event_formInternalFrameOpened

    private void cboBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBuscarActionPerformed
if(cboBuscar.getSelectedIndex()==0)bus="AND cli LIKE";
if(cboBuscar.getSelectedIndex()==1)bus="AND cli LIKE";
if(cboBuscar.getSelectedIndex()==2)bus="AND cli LIKE";
txtBuscar.setText("");
txtBuscar.requestFocus();
cargarTabla();
    }//GEN-LAST:event_cboBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
ManageButtons(4);  
        aop=(jTable2.getValueAt(jTable2.getSelectedRow(),0).toString());
        txtCod.setText(aop);
        cargarDatos();       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chkKeyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkKeyKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkKey);
    }//GEN-LAST:event_chkKeyKeyPressed

    private void chkCatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkCatKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkCat);
    }//GEN-LAST:event_chkCatKeyPressed

    private void chkTazasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkTazasKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkTazas);
    }//GEN-LAST:event_chkTazasKeyPressed

    private void chkBalizaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkBalizaKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkBaliza);
    }//GEN-LAST:event_chkBalizaKeyPressed

    private void chkRadioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkRadioKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkRadio);
    }//GEN-LAST:event_chkRadioKeyPressed

    private void chkHerramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkHerramKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkHerram);
    }//GEN-LAST:event_chkHerramKeyPressed

    private void chkEqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkEqKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkEq);
    }//GEN-LAST:event_chkEqKeyPressed

    private void chkAuxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkAuxKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkAux);
    }//GEN-LAST:event_chkAuxKeyPressed

    private void chkExtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkExtKeyPressed
if(evt.getKeyCode()==10)ManageChkbox(chkExt);
    }//GEN-LAST:event_chkExtKeyPressed

    private void cboAñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAñoActionPerformed
if(cboAño.getSelectedIndex()==0)year="YEAR(ent_date)=YEAR(CURDATE())";
if(cboAño.getSelectedIndex()==1)year="YEAR(ent_date)=YEAR(CURDATE())-1";
if(cboAño.getSelectedIndex()>1)year="YEAR(ent_date)="+String.valueOf(cboAño.getSelectedIndex()-1);
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

    private void txtPrepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrepKeyPressed
if(evt.getKeyCode()==10){
 try {
         pst= DB.con.prepareStatement("SELECT prep_cod,cli_ci,cli,veh_cod,veh,seg_cod FROM v_prep WHERE prep_cod=? AND est_des='VIG'");
         pst.setInt(1, Integer.parseInt(txtPrep.getText()));
         rs= pst.executeQuery();
         if(rs.next()){
          txtPrep.setText(rs.getString(1));
          txtCodCli.setText(rs.getString(2));
          txtCli.setText(rs.getString(3));
          txtCodVeh.setText(rs.getString(4));
          txtDesVeh.setText(rs.getString(5));
          seg=rs.getString(6);
         }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "error cargando");
        }   
}else{
    if(evt.getKeyCode()==99){
          JTextField[] txt= {txtPrep};
  String[]columnas={"Codigo","Aseguradora","Nº Siniestro"};
//        Buscador b=new Buscador(null, true, "SELECT prep_cod,seg_des,prep_sin FROM v_prep WHERE cli LIKE", columnas, txt,DB.con);
   //     b.setTitle("Buscador de Presupuestos");
     //   b.setVisible(true);   
    }
}
    }//GEN-LAST:event_txtPrepKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
if(evt.getKeyCode()==27)dispose();
    }//GEN-LAST:event_formKeyPressed

    private void txtCodVehKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodVehKeyTyped
       DB.fieldControl(2, evt);
    }//GEN-LAST:event_txtCodVehKeyTyped

    private void txtPrepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrepKeyTyped
       DB.fieldControl(2, evt);
    }//GEN-LAST:event_txtPrepKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboAño;
    private javax.swing.JComboBox cboBuscar;
    private javax.swing.JComboBox cboComb;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboOrder;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JCheckBox chkAux;
    private javax.swing.JCheckBox chkBaliza;
    private javax.swing.JCheckBox chkCat;
    private javax.swing.JCheckBox chkEq;
    private javax.swing.JCheckBox chkExt;
    public static javax.swing.JCheckBox chkGar;
    private javax.swing.JCheckBox chkHerram;
    private javax.swing.JCheckBox chkKey;
    private javax.swing.JCheckBox chkRadio;
    private javax.swing.JCheckBox chkTazas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JMenuItem popUpSeeDetails;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtCli;
    private javax.swing.JTextField txtCod;
    public static javax.swing.JTextField txtCodCli;
    private javax.swing.JTextField txtCodVeh;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDesVeh;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtKm;
    private javax.swing.JTextField txtPrep;
    private javax.swing.JTextField txtSeg;
    private javax.swing.JTextField txtSuc;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
bandera=1;
ManageButtons(3);
cod=DB.getDBcod("ent_cod","v_ent");
txtDate.setText(DB.ClientDate(1));
txtSuc.setText(Login.suc);
txtCod.setText(cod);
txtPrep.requestFocus();}

private void al_nullear(){
        bandera=3;
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)save();
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
         if(jTable2.getValueAt(jTable2.getSelectedRow(),7).toString().equals("CANCELADA")){
             btnNull.setEnabled(false);
         }else{
             btnNull.setEnabled(true);
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
                 if(estado==4){//ESPECIAL - Ver Detalles From jPopUpmenu
         btnAdd.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancel.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
     }
 }

private void clean(){
aop=null;
isOk=0;
    txtDate.setText("");
    txtCod.setText("");
    txtSuc.setText("");
    txtCli.setText("");
    txtKm.setText("");
    txtSeg.setText("");
    txtCodCli.setText("");
    jTextArea2.setText("");
    txtBuscar.setText("");
    txtCodCli.setText("");
    txtCodVeh.setText("");
    txtDesVeh.setText("");
    txtPrep.setText("");
//----------------------
cboComb.setSelectedIndex(0);
//------------------------
chkAux.setSelected(false);
chkBaliza.setSelected(false);
chkCat.setSelected(false);
chkExt.setSelected(false);
chkHerram.setSelected(false);
chkKey.setSelected(false);
chkRadio.setSelected(false);
chkTazas.setSelected(false);
chkEq.setSelected(false);
}

  private void limpiarTabla(){
        DefaultTableModel modeloq = (DefaultTableModel)this.jTable2.getModel();
        while(this.jTable2.getRowCount() != 0)
            modeloq.removeRow(0);
}
  
     private void validar_blancos(){
if(txtCli.getText().isEmpty() || cboComb.getSelectedIndex()==0){
             JOptionPane.showMessageDialog(this, "Debe completar los campos obligatorios", "Atención", JOptionPane.ERROR_MESSAGE);  
         }else{
//    if(DB.validarDuplicados("v_ent WHERE (ent_estado!='CANCELADA' OR ent_estado!='ENTREGADO' OR ent_estado!='CON ORDEN, EN TALLER') AND veh_cod='"+txtCodVeh.getText()+"'")){
//        JOptionPane.showMessageDialog(this, "Ya se encuentra dicho vehiculo en el Taller", "Atención", JOptionPane.ERROR_MESSAGE);
//        ManageButtons(1);
//        clean();
//    }else{
       save();
  //  }
   }                            
}
     
     private void save(){
              if(bandera==3)cod=aop;
              LoadWithObjects comb=(LoadWithObjects) cboComb.getSelectedItem();
              //----------
    String[] parametros = {String.valueOf(bandera),cod,seg,txtCodVeh.getText(),txtCodCli.getText(),Login.sucCod,comb.getCod(),jTextArea2.getText(),
        txtKm.getText().trim(), String.valueOf(chkKey.isSelected()),String.valueOf(chkCat.isSelected()),String.valueOf(chkBaliza.isSelected()),String.valueOf(chkAux.isSelected()),
        String.valueOf(chkTazas.isSelected()),String.valueOf(chkHerram.isSelected()),String.valueOf(chkRadio.isSelected()),String.valueOf(chkEq.isSelected()),
        "0",String.valueOf(chkGar.isSelected()),String.valueOf(chkExt.isSelected())};
isOk=DB.executeCst("CALL abm_ent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
        new int[]{1,1,1,1,1,1,1,2,1,4,4,4,4,4,4,4,4,2,4,4},parametros,true); 
          if(isOk==0)DB.EmitirMensaje(this, bandera);
     }
     
     private void cargarDatos(){
         try {
            pst=DB.con.prepareStatement("SELECT comb,ent_otros,ent_date_give,ent_date_fin,ent_km,ent_key,ent_cat,ent_bal,ent_aux,ent_taz,ent_her,ent_rad,ent_eq,ent_gar,ent_ext,veh,veh_cod FROM v_ent WHERE ent_cod=?");
            pst.setInt(1, Integer.parseInt(aop));
            rs=pst.executeQuery();
            if(rs.next()){
                 txtCodCli.setText(DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(),4).toString()));
                 //txtBuscarCab.setText(DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(),4).toString()));
                 txtCli.setText(DB.getDes(jTable2.getValueAt(jTable2.getSelectedRow(),4).toString()));
                 txtSuc.setText(jTable2.getValueAt(jTable2.getSelectedRow(),5).toString());
                 //txtSin.setText(jTable2.getValueAt(jTable2.getSelectedRow(),6).toString());
                 txtEstado.setText(jTable2.getValueAt(jTable2.getSelectedRow(),7).toString());
                 txtDate.setText(jTable2.getValueAt(jTable2.getSelectedRow(),1).toString());
                 //txtSegCod.setText(DB.getCod(jTable2.getValueAt(jTable2.getSelectedRow(),2).toString()));
                 LoadWithObjects comb = new LoadWithObjects(DB.getCod(rs.getString(1)),DB.getDes(rs.getString(1)));
                 jTextArea2.setText(rs.getString(2));
                 txtCodVeh.setText(rs.getString(17));
                 txtDesVeh.setText(rs.getString(16));
                 txtKm.setText(rs.getString(5));
                 cboComb.setSelectedItem(comb); 
                 if(rs.getString(9).equals("1")){
                     chkAux.setSelected(true);
                 }else{
                     chkAux.setSelected(false);
                 }if(rs.getString(8).equals("1")){
                     chkBaliza.setSelected(true);
                 }else{
                     chkBaliza.setSelected(false);
                 }if(rs.getString(7).equals("1")){
                     chkCat.setSelected(true);
                 }else{
                     chkCat.setSelected(false);
                 }if(rs.getString(13).equals("1")){
                     chkEq.setSelected(true);
                 }else{
                     chkEq.setSelected(false);
                 }if(rs.getString(15).equals("1")){
                     chkExt.setSelected(true);
                 }else{
                     chkExt.setSelected(false);
                 }if(rs.getString(14).equals("1")){
                     chkGar.setSelected(true);
                 }else{
                     chkGar.setSelected(false);
                 }if(rs.getString(11).equals("1")){
                     chkHerram.setSelected(true);
                 }else{
                     chkHerram.setSelected(false);
                 }if(rs.getString(6).equals("1")){
                     chkKey.setSelected(true);
                 }else{
                     chkKey.setSelected(false);
                 }if(rs.getString(12).equals("1")){
                     chkRadio.setSelected(true);
                 }else{
                     chkRadio.setSelected(false);
                 }if(rs.getString(10).equals("1")){
                     chkTazas.setSelected(true);
                 }else{
                     chkTazas.setSelected(false);
                 }
            }
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(this,e.getMessage(), "Error al Cargar Datos", JOptionPane.ERROR_MESSAGE);
             DB.shutdown();
             System.exit(1);
         }
     }
     
     private void ManageClosing(){
        dispose();
        DB.shutdown();
     }
     
  private void cargarAño(){
      cboAño.removeAllItems();
      cboAño.addItem("Este Año");
      cboAño.addItem("Año Pasado");
      int current_year=Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
      while(current_year!=2014){
          cboAño.addItem(String.valueOf(current_year));
          current_year--;
      }
  }
  
  private void ManageChkbox(JCheckBox chk){
          if(chk.isSelected()){
        chk.setSelected(false);
    }else{
        chk.setSelected(true);
    }
  }
}


package referenciales;

import Mod_Compras.*;
import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import threads.CboThreadGT;
import threads.TblThread;
import frames.Login;
import config.PGa_v2;
import config.conectarDB;

/**
 *
 * @author schafler92
 * @web https://wiki.postgresql.org/wiki/Date_WeekOfMonth
 */
public class Ref_usuario extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    int ban,isOk;
    int[]det_ban=null;
    boolean first=true;
    PGa_v2 pga=null;
    String emp;
    DefaultTableModel modelo;
    String aop,cod,mes="extract(month from (ped_datetime))=extract(month from current_date)",
        year="extract(year from (ped_datetime))=extract(year from current_date)",suc="AND suc_des=?";
       
    public Ref_usuario() {
        initComponents();
        DB = new conectarDB();
        ManageButtons(1);
        pga=new PGa_v2();
        DB.getYears(cboYear);
        modelo=(DefaultTableModel)jTable1.getModel();
    }
    
 private void cargarTabla(){
     limpiarTabla(jTable2);
     limpiarTabla(jTable3);
     if(cboSuc.getSelectedIndex()>0){
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
             estado="est_des=? AND";
             tipo_de_pst[1]=2;
             par[0]=cboSee.getSelectedItem().toString();
         }
             if(cboSuc.getItemCount()==2){//elegir y su suc
             par[n]=Login.suc;   
             }else{
             par[n]=cboSuc.getSelectedItem().toString();   
             }
             tipo_de_pst[0]=2;
     new TblThread("SELECT ped_cod,suc_des,date_,emp,est_des FROM v_ped WHERE "
             +estado+" "+mes+" AND "+year+" "+suc+" ORDER BY 1 DESC", jTable2, tipo_de_pst,par,DB.con,this,new int[]{1,2,2,2,2}).start();
     }
 }
 
 private void cargarEdicion(){
    limpiarTabla(jTable3);
    new TblThread("SELECT det_cant,un_cod,mat,est_des FROM v_dped WHERE ped_cod=? ORDER BY 3 ASC",
            jTable3,new int[]{1},new String[]{aop},DB.con,this,new int[]{1,2,2,2}).start();
 }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUsr = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnAproveDet = new javax.swing.JButton();
        btnNullDet = new javax.swing.JButton();
        btnEditDet = new javax.swing.JButton();
        txtRol = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMedida = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cboFilter = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        cboSuc = new javax.swing.JComboBox();
        btnAprove = new javax.swing.JButton();

        setTitle("Mantener Usuarios");
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

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Usuario"));

        jLabel2.setText("Contraseña");

        jLabel1.setText("Codigo");

        txtCod.setEditable(false);
        txtCod.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCod.setEnabled(false);

        jLabel8.setText("Usuario");

        txtUsr.setNextFocusableComponent(txtPass);

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtUsr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del Usuario"));

        jLabel5.setText("Rol");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rol", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

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
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Botones de Gestión"));

        btnAproveDet.setText("Agregar");
        btnAproveDet.setEnabled(false);
        btnAproveDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAproveDetActionPerformed(evt);
            }
        });

        btnNullDet.setText("Anular");
        btnNullDet.setEnabled(false);
        btnNullDet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNullDetActionPerformed(evt);
            }
        });

        btnEditDet.setText("Editar");
        btnEditDet.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditDet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAproveDet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNullDet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnAproveDet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditDet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNullDet)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtRol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRolKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRolKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRolKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("<ENTER>");

        txtMedida.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Usuario", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(80);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rol", "Estado"
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
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Filtrar Por");

        cboFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Mes", "Mes Pasado", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cboFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterActionPerformed(evt);
            }
        });

        jLabel12.setText("Ver");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VIG", "APR", "ORD.P", "ORD.T", "ANU", "Todos" }));
        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        jLabel13.setText("de");

        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });

        jLabel15.setText("en");

        cboSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
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
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listado", jPanel2);

        btnAprove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_edit.png"))); // NOI18N
        btnAprove.setText("Editar");
        btnAprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAproveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAprove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnNull)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnExit)
                    .addComponent(btnAprove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
    ManageClosing();
}else{
   if (JOptionPane.showConfirmDialog(this,"Hay datos sin guardar, ¿Desea Salir de todas formas?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
       ManageClosing();
   }  
}
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        validarCamposEnBlanco();
        if(isOk==0){
          clean();
        ManageButtons(1);  
        }       
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clean();
        ManageButtons(1);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
aop=jTable2.getValueAt(jTable2.getSelectedRow(),0).toString();
        ManageButtons(2);
        cargarDatos();
    }//GEN-LAST:event_jTable2MousePressed

    private void btnAproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAproveActionPerformed
al_editar();
    }//GEN-LAST:event_btnAproveActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        if(ban!=1 && jTable1.getValueAt(jTable1.getSelectedRow(),3).toString().equals("PEN")){
        btnAproveDet.setEnabled(true);
        btnNullDet.setEnabled(true);
        btnEditDet.setEnabled(true); 
        //btnAprobAllDet.setEnabled(true);
        }else{
          btnAproveDet.setEnabled(false);
        btnNullDet.setEnabled(false);
        btnEditDet.setEnabled(false); 
       // btnAprobAllDet.setEnabled(false);  
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
cargarTabla();
    }//GEN-LAST:event_cboSeeActionPerformed

    private void cboFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterActionPerformed
if(cboFilter.getSelectedIndex()==0){//este mes
    mes="extract(month from (ped_datetime))=extract(month from current_date)";
}else{
    if(cboFilter.getSelectedIndex()==1){//mes anterior
        mes="extract(month from (ped_datetime))=extract(month from current_date)-1";
    }else{
     mes="extract(month from (ped_datetime))="+String.valueOf(cboFilter.getSelectedIndex()-1);//mes por int 1/12  
    }
    }
cargarTabla();
    }//GEN-LAST:event_cboFilterActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
if(cboYear.getSelectedIndex()==0)year="extract(year from (ped_datetime))=extract(year from current_date)";
if(cboYear.getSelectedIndex()==1)year="extract(year from (ped_datetime))=extract(year from current_date)-1";
if(cboYear.getSelectedIndex()>1)year="extract(year from (ped_datetime))="+String.valueOf(cboYear.getSelectedIndex()-1);
cargarTabla();
    }//GEN-LAST:event_cboYearActionPerformed

    private void cboSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSucActionPerformed
cargarTabla();
    }//GEN-LAST:event_cboSucActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
if(Integer.parseInt(Login.sucCod)<=4){
    new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE est_des='VIG' ORDER BY 2 ASC", cboSuc, DB.con,"-- ELEGIR --").start();
}else{
    cboSuc.removeAllItems();
    cboSuc.addItem("Elegir");
    cboSuc.addItem("Esta Sucursal");
}
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnAproveDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAproveDetActionPerformed
                if(jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog( this,"No seleccionó ningún Registro","Atención", JOptionPane.INFORMATION_MESSAGE); 
        }else{
                        det_ban[jTable1.getSelectedRow()]=5;
                        
                        jTable1.setValueAt("APR",jTable1.getSelectedRow(), 3);
                        btnEditDet.setEnabled(false);
                        btnAproveDet.setEnabled(false);
                        btnNullDet.setEnabled(false);
                } 
    }//GEN-LAST:event_btnAproveDetActionPerformed

    private void btnNullDetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullDetActionPerformed
     if(jTable1.getSelectedRow()==-1){
            JOptionPane.showMessageDialog( this,"No seleccionó ningún Registro","Atención", JOptionPane.INFORMATION_MESSAGE); 
        }else{
            det_ban[jTable1.getSelectedRow()]=4;
            jTable1.setValueAt("ANU",jTable1.getSelectedRow(), 3);
            btnEditDet.setEnabled(false);
            btnAproveDet.setEnabled(false);
            btnNullDet.setEnabled(false);  
     }
    }//GEN-LAST:event_btnNullDetActionPerformed

    private void txtRolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRolKeyPressed
if(evt.getKeyCode()==10){
    if(txtRol.getText().length()>1){
        if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.load(new String[]{"mat_cod","mat_des","un_cod"},"v_mat","est_des='VIG'",DB.con);
//    pga.autocomplete(txtRol,txtMedida,null,txtCant,this);
    }else{
        pga.ocultarauto();
    }
}
    }//GEN-LAST:event_txtRolKeyPressed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
if(jTabbedPane1.getSelectedIndex()==0){
    pga.ocultarauto();
}
    }//GEN-LAST:event_formComponentMoved

    private void txtRolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRolKeyReleased
DB.toUpperCaseState(txtRol, evt);
    }//GEN-LAST:event_txtRolKeyReleased

    private void txtRolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRolKeyTyped
DB.fieldControl(3, evt);
    }//GEN-LAST:event_txtRolKeyTyped

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
if(evt.getKeyCode()==10){
   txtUsr.setEnabled(false);
   txtPass.setEnabled(false);
   txtRol.requestFocus();
}
    }//GEN-LAST:event_txtPassKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAprove;
    private javax.swing.JButton btnAproveDet;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEditDet;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnNullDet;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboFilter;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboSuc;
    private javax.swing.JComboBox cboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtMedida;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtRol;
    private javax.swing.JTextField txtUsr;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
ban=1;
cod=DB.getDBcod("ped_cod","v_ped");
txtCod.setText(cod);
txtUsr.setText(DB.ClientDate(2));
ManageButtons(3);
txtUsr.requestFocus();
}

private void al_editar(){
    if(!jTable2.getValueAt(jTable2.getSelectedRow(),1).toString().equals(Login.emp) && Login.per!=0 || Login.per!=1){
            ban=2;
            ManageButtons(3);
            txtRol.setEnabled(false);  
            DB.setDataFromJtable(jTable3, jTable1);
            det_ban = new int[jTable1.getRowCount()];
     }else{
       JOptionPane.showMessageDialog( this,"No posee los permisos necesarios","Atención", JOptionPane.WARNING_MESSAGE); 
     }
}

private void al_nullear(){
        ban=3;
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
         btnAprove.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancel.setEnabled(false);
         jTabbedPane1.setSelectedIndex(1);
     }
          if(estado==2){//pressed
         btnAdd.setEnabled(false);
         if(jTable2.getValueAt(jTable2.getSelectedRow(),4).toString().equals("ORD.T") || jTable2.getValueAt(jTable2.getSelectedRow(),4).toString().equals("ANU")){
             btnNull.setEnabled(false);
         }else{
             btnNull.setEnabled(true);
         }
         btnSave.setEnabled(false);
         btnCancel.setEnabled(true);
         if(jTable2.getValueAt(jTable2.getSelectedRow(),4).toString().equals("VIG") && Login.per<4){
             btnAprove.setEnabled(true);
         }else{
             btnAprove.setEnabled(false);
         }
     }
               if(estado==3){//editing
         btnAdd.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(true);
         btnAprove.setEnabled(false);
         btnCancel.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
         if(ban==2){
             txtRol.setEnabled(false);
         }else{
            txtRol.setEnabled(true);
         }
     }
 }

private void clean(){
    cboSuc.setSelectedIndex(0);
    txtCod.setText("");
    txtRol.setText("");
    txtPass.setText("");
    txtUsr.setText("");
    txtMedida.setText("");
    btnEditDet.setEnabled(false);
    btnAproveDet.setEnabled(false);
    btnNullDet.setEnabled(false);
    aop=null;
    isOk=0;
    first=true; 
    ban=0;
    emp=null;
    det_ban=null;
    limpiarTabla(jTable3);
    limpiarTabla(jTable2);
    limpiarTabla(jTable1);
    //------------- 
    cboSuc.setSelectedIndex(0);
    //-------------
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
txtCod.setText(aop);
txtPass.setText(jTable2.getValueAt(jTable2.getSelectedRow(),1).toString());
txtUsr.setText(jTable2.getValueAt(jTable2.getSelectedRow(),2).toString());
emp=jTable2.getValueAt(jTable2.getSelectedRow(),3).toString();
cargarEdicion();
}
  
  private void save(){
      String[][] par;
      int row;
      if(ban==1 || ban==2){
          if(ban==2)cod=aop;
          row=jTable1.getRowCount();
          par = new String [row][4];
                    
             for (int i = 0; i < row; i++) {//extrae datos del jtable
                 if(ban==1){
                     par[i][0]="1";
                 }
                 if(ban==2){
                     par[i][0]=String.valueOf(det_ban[i]);
                 }
          par[i][1]=txtCod.getText();
          par[i][2]=DB.getCod(jTable1.getValueAt(i, 2).toString());//extrae el cod
          par[i][3]=jTable1.getValueAt(i, 0).toString();
             }
             
          isOk=DB.executeCst("abm_ped(?,?,?,?)",new int[]{1,1,1,1},new String[]{String.valueOf(ban),cod,Login.empCi,Login.sucCod},false);
          isOk=isOk+DB.executeCstDetails("abm_dped(?,?,?,?::numeric)", new int[]{1,1,1,5}, par);
          if(isOk==0){
              DB.commit();
              DB.EmitirMensaje(this, ban);
          }
      }else{
          isOk=DB.executeCst("abm_ped(?,?,?,?)",new int[]{1,1,1,1},new String[]{String.valueOf(ban),aop,"1","1"},true);
          if(isOk==0)DB.EmitirMensaje(this, ban);
      }
      clean();
      cargarTabla();
      ManageButtons(1);
}
  
  private void ManageClosing(){
      pga.ocultarauto();
      DB.shutdown();
      dispose();
      Menu.menPed.setEnabled(true);
  }
  
  private void validarCamposEnBlanco(){
               int n=jTable1.getRowCount(),i=0;
        switch(ban){
            case 1://----------------------------valida si el muy animal no guardo ningun detalle--
                if(n>0){
                    save();
                }else{
                    JOptionPane.showMessageDialog(this,"No se puede guardar un Pedido sin detalles", "Atención", JOptionPane.WARNING_MESSAGE);
                    isOk=1;
                }
            break;
            case 2://---------------------------valida si anulo todo el muy animal------------------
           while(n!=0 || i<=0){
                if(jTable1.getValueAt(n-1,3).toString().equals("APR")){
                  i++;    
                }
                n--;
           }
           if(i>0){//save
               save();
           }else{//
                    JOptionPane.showMessageDialog(this,"No se puede aprobar un Pedido con todos los detalles anulados","Atención", JOptionPane.WARNING_MESSAGE);
                    isOk=1;
           }
            break;
        
  }
        }
  
  private int validarDuplicados(String des){
      for (int i = 0; i < jTable1.getRowCount(); i++) {
          if(jTable1.getValueAt(i, 2).toString().equals(des))return i;
             }
      return -1;
  }
}

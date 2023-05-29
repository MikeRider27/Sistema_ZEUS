

package referenciales;

import frames.Menu;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import threads.TblThread;
import frames.Buscador;
import config.conectarDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.LoadWithObjects;
import config.PGa_vStandard;

/**
 *
 * @author Copypaster
 */
public class Ref_vehiculos extends javax.swing.JInternalFrame {

    private final conectarDB DB;
    private PreparedStatement pst;
    private ResultSet rs;
    PGa_vStandard pga=new PGa_vStandard();
    int bandera,isOk=0;
    boolean isLoaded;
    String aop,cod,see="est_des='VIG'";
    
    
    public Ref_vehiculos() {
        initComponents();
        DB = new conectarDB();
            ManageButtons(1);
    }
    
        private void cargarTabla(){
            limpiarTabla();
        if(txtBuscar.getText().trim().length()>1){
        new TblThread("SELECT veh_cod,seg,cli,mar,mod_,col,veh_cha,det_pol,veh_null "
 + "FROM v_veh WHERE cli LIKE '%"+txtBuscar.getText().trim().toUpperCase()+"%' AND "+see+" ORDER BY 1 ASC", 
                jTable1,null,null,DB.con,this,new int[]{1,2,2,2,2,2,2,1,4}).start();   
        }
 }
        
        private void cargarCli(){
DB.consultPstSimple("SELECT cli_ci,cli FROM v_cli WHERE cli_ci=?",txtCodCli,txtDesCli);
txtCha.requestFocus();
        }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menSeeDetails = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtCha = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCod = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCodCli = new javax.swing.JTextField();
        txtDesCli = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        chkTer = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtSeg = new javax.swing.JTextField();
        txtMar = new javax.swing.JTextField();
        txtMod = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCol = new javax.swing.JTextField();
        cboTveh = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtChass = new javax.swing.JTextField();
        txtPol = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAño = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboSee = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        cboBuscar = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnNull = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        menSeeDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/informes.png"))); // NOI18N
        menSeeDetails.setText("Ver Detalles");
        menSeeDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menSeeDetailsActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menSeeDetails);

        setTitle("Mantener Vehiculos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Imagen2.png"))); // NOI18N
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Principales"));

        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("*");

        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("*");

        txtCha.setNextFocusableComponent(txtMar);
        txtCha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtChaFocusGained(evt);
            }
        });
        txtCha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChaKeyTyped(evt);
            }
        });

        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("*");

        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("*");

        txtCod.setEditable(false);
        txtCod.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("*");

        jLabel4.setText("Codigo");

        jLabel5.setText("Asegurado");

        jLabel6.setText("Cia. de Seguro");

        jLabel12.setText("Matricula");

        jLabel7.setText("Marca");

        jLabel8.setText("Modelo");

        txtCodCli.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCodCli.setNextFocusableComponent(chkTer);
        txtCodCli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodCliFocusGained(evt);
            }
        });
        txtCodCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodCliKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodCliKeyTyped(evt);
            }
        });

        txtDesCli.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        txtDesCli.setEnabled(false);

        btnBuscar.setText("...");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("*");

        chkTer.setText("Vehiculo de Tercero");
        chkTer.setNextFocusableComponent(txtCha);
        chkTer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chkTerKeyPressed(evt);
            }
        });

        jLabel11.setText("Tipo de Vehiculo");

        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("*");

        jLabel19.setForeground(new java.awt.Color(204, 0, 0));
        jLabel19.setText("*");

        txtSeg.setNextFocusableComponent(txtCodCli);
        txtSeg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSegFocusGained(evt);
            }
        });
        txtSeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSegKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSegKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSegKeyTyped(evt);
            }
        });

        txtMar.setNextFocusableComponent(txtMod);
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

        txtMod.setNextFocusableComponent(cboTveh);
        txtMod.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtModFocusGained(evt);
            }
        });
        txtMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtModKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModKeyTyped(evt);
            }
        });

        jLabel10.setText("Color");

        txtCol.setNextFocusableComponent(txtAño);
        txtCol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtColFocusGained(evt);
            }
        });
        txtCol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtColKeyPressed(evt);
            }
        });

        cboTveh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- ELEGIR --" }));
        cboTveh.setNextFocusableComponent(txtCol);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCha, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMar, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(cboTveh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel17))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCol, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                    .addComponent(txtMod))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtCodCli, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDesCli, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)))
                                .addGap(18, 18, 18)
                                .addComponent(chkTer, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(41, 41, 41))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel6)
                    .addComponent(txtSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel5)
                    .addComponent(txtCodCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(chkTer)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel17)
                    .addComponent(jLabel8)
                    .addComponent(jLabel18)
                    .addComponent(txtMar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel20)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(txtCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTveh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Secundarios"));

        txtChass.setNextFocusableComponent(txtPol);
        txtChass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtChassFocusGained(evt);
            }
        });
        txtChass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChassKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChassKeyTyped(evt);
            }
        });

        txtPol.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPolFocusGained(evt);
            }
        });
        txtPol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPolKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPolKeyTyped(evt);
            }
        });

        jLabel2.setText("Nº de Poliza");

        jLabel13.setText("Chassis Nº");

        jLabel9.setText("Año");

        txtAño.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAño.setNextFocusableComponent(txtChass);
        txtAño.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAñoFocusGained(evt);
            }
        });
        txtAño.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAñoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtChass, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtPol, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtChass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtPol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Edicion", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Ver");

        cboSee.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vigentes", "Anulados" }));
        cboSee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSeeActionPerformed(evt);
            }
        });

        jLabel26.setText("Buscar Por");

        cboBuscar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asegurado", "Chapa Nº" }));
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

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("<ENTER>");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboSee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Asegurado", "Marca", "Modelo", "Color", "Chapa"
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
        jTable1.setComponentPopupMenu(jPopupMenu1);
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(240);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listado", jPanel3);

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

        btnNull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_delete.png"))); // NOI18N
        btnNull.setText("Anular");
        btnNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNullActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/document_edit.png"))); // NOI18N
        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnSave)
                    .addComponent(btnCancel)
                    .addComponent(btnExit)
                    .addComponent(btnNull)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
if(jTabbedPane1.getSelectedIndex()==1){
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
    }//GEN-LAST:event_btnExitActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
aop=(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
txtCod.setText(aop);
ManageButtons(2);
    }//GEN-LAST:event_jTable1MousePressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
validar_blancos();
if(isOk==0){
    clean();
    ManageButtons(1); 
            }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNullActionPerformed
al_nullear();
    }//GEN-LAST:event_btnNullActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
al_agregar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
clean();
ManageButtons(1);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cboSeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSeeActionPerformed
if(cboSee.getSelectedIndex()==0)see="est_des='VIG'";
if(cboSee.getSelectedIndex()==1)see="est_des='ANU'";
cargarTabla();
    }//GEN-LAST:event_cboSeeActionPerformed

    private void txtPolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPolKeyPressed
        if(evt.getKeyCode()==10){
            validar_blancos();
            if(isOk==0){
               clean();
            ManageButtons(1); 
            }
        }
    }//GEN-LAST:event_txtPolKeyPressed

    private void txtChaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChaKeyReleased
DB.toUpperCaseState(txtCha, evt);
    }//GEN-LAST:event_txtChaKeyReleased

    private void txtChassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChassKeyReleased
DB.toUpperCaseState(txtChass, evt);
    }//GEN-LAST:event_txtChassKeyReleased

    private void txtCodCliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodCliKeyPressed
if(evt.getKeyCode()==10){
cargarCli();
}
    }//GEN-LAST:event_txtCodCliKeyPressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
if(txtBuscar.getText().trim().isEmpty()){
    limpiarTabla();
}else{
    DB.toUpperCaseState(txtBuscar, evt);
}
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void cboBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBuscarActionPerformed
/*if(cboBuscar.getSelectedIndex()==0)buscar="";
if(cboBuscar.getSelectedIndex()==1)buscar="";
if(cboBuscar.getSelectedIndex()==2)buscar="";
if(cboBuscar.getSelectedIndex()==3)buscar="";
limpiarTabla();
txtBuscar.setText("");
txtBuscar.requestFocus();*/
    }//GEN-LAST:event_cboBuscarActionPerformed

    private void txtCodCliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodCliKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtCodCliKeyTyped

    private void txtAñoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAñoKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtAñoKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
Object[][] cbo= new Object[4][2];
    cbo[0][0]="Nº de Cedula";cbo[0][1]="cli_ci";
    cbo[1][0]="Nombres";cbo[1][1]="cli_nom";
    cbo[2][0]="Apellidos";cbo[2][1]="cli_ape";
    cbo[3][0]="Nombres y Apellidos";cbo[3][1]="";

        Buscador b = new Buscador(null, true,
        "SELECT cli_ci,concat(cli_nom,', ',cli_ape) FROM v_cli", 
        new String[]{"Cédula Nº","Asegurado"},
        new Object[]{txtCodCli,txtDesCli}, DB.con,cbo,"est_des='VIG'");
b.setTitle("Buscador de Asegurados");
b.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
al_editar();
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtPolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPolKeyTyped
DB.fieldControl(2,evt);
    }//GEN-LAST:event_txtPolKeyTyped

    private void txtChaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChaKeyTyped
DB.fieldControl(5,evt);
    }//GEN-LAST:event_txtChaKeyTyped

    private void txtChassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChassKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtChassKeyTyped

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10)cargarTabla(); 
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void menSeeDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menSeeDetailsActionPerformed
 ManageButtons(4);
        cargarDatos(); 
    }//GEN-LAST:event_menSeeDetailsActionPerformed

    private void chkTerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkTerKeyPressed
if(evt.getKeyCode()==10){
    if(chkTer.isSelected()){
        chkTer.setSelected(false);
    }else{
        chkTer.setSelected(true);
    }
}
    }//GEN-LAST:event_chkTerKeyPressed

    private void txtSegKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtSegKeyTyped

    private void txtSegKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegKeyReleased
DB.toUpperCaseState(txtSeg, evt);
    }//GEN-LAST:event_txtSegKeyReleased

    private void txtSegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSegKeyPressed
if(evt.getKeyCode()==10 ){
    if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.autocomplete(txtSeg,"seg_cod,' - ',seg_des","v_seg","AND est_des='VIG'",this,txtCodCli,DB.con);
}
    }//GEN-LAST:event_txtSegKeyPressed

    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
if(jTabbedPane1.getSelectedIndex()==0 && pga.popup!=null){
    pga.popup.hide();
}
    }//GEN-LAST:event_formComponentMoved

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
    pga.autocomplete(txtSeg,"mar_cod,' - ',mar_des","v_mar","AND est_des='VIG'",this,txtMod,DB.con);
}
    }//GEN-LAST:event_txtMarKeyPressed

    private void txtModKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModKeyTyped
DB.fieldControl(3,evt);
    }//GEN-LAST:event_txtModKeyTyped

    private void txtModKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModKeyReleased
        DB.toUpperCaseState(txtMod, evt);
    }//GEN-LAST:event_txtModKeyReleased

    private void txtModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModKeyPressed
        if(evt.getKeyCode()==10 ){
    if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.autocomplete(txtMod,"mod_cod,' - ',mod_des","v_mod","AND mar_des='"+DB.getDes(txtMar.getText())+"' "
            + "AND est_des='VIG'",this,cboTveh,DB.con);
}
    }//GEN-LAST:event_txtModKeyPressed

    private void txtColKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColKeyPressed
       if(evt.getKeyCode()==10 ){
    if(pga.show_jlist){
        pga.show_jlist=false;
        pga.refresh_();
    }
    pga.autocomplete(txtMod,"col_cod,' - ',col_des","v_col","AND est_des='VIG'",this,txtAño,DB.con);
}
    }//GEN-LAST:event_txtColKeyPressed

    private void txtCodCliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodCliFocusGained
if(bandera==2)txtCodCli.selectAll();
    }//GEN-LAST:event_txtCodCliFocusGained

    private void txtChaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChaFocusGained
if(bandera==2)txtCha.selectAll();
    }//GEN-LAST:event_txtChaFocusGained

    private void txtColFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtColFocusGained
if(bandera==2)txtCol.selectAll();
    }//GEN-LAST:event_txtColFocusGained

    private void txtAñoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAñoFocusGained
if(bandera==2)txtAño.selectAll();
    }//GEN-LAST:event_txtAñoFocusGained

    private void txtChassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChassFocusGained
if(bandera==2)txtChass.selectAll();
    }//GEN-LAST:event_txtChassFocusGained

    private void txtPolFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPolFocusGained
if(bandera==2)txtPol.selectAll();
    }//GEN-LAST:event_txtPolFocusGained

    private void txtSegFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSegFocusGained
if(bandera==2)txtSeg.selectAll();
    }//GEN-LAST:event_txtSegFocusGained

    private void txtMarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMarFocusGained
if(bandera==2)txtMar.selectAll();
    }//GEN-LAST:event_txtMarFocusGained

    private void txtModFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtModFocusGained
if(bandera==2)txtMod.selectAll();
    }//GEN-LAST:event_txtModFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNull;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cboBuscar;
    private javax.swing.JComboBox cboSee;
    private javax.swing.JComboBox cboTveh;
    private javax.swing.JCheckBox chkTer;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenuItem menSeeDetails;
    private javax.swing.JTextField txtAño;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCha;
    private javax.swing.JTextField txtChass;
    private javax.swing.JTextField txtCod;
    private javax.swing.JTextField txtCodCli;
    private javax.swing.JTextField txtCol;
    private javax.swing.JTextField txtDesCli;
    private javax.swing.JTextField txtMar;
    private javax.swing.JTextField txtMod;
    private javax.swing.JTextField txtPol;
    private javax.swing.JTextField txtSeg;
    // End of variables declaration//GEN-END:variables
private void al_agregar(){
bandera=1;
cod=DB.getDBcod("veh_cod","v_veh");
txtCod.setText(cod);
ManageButtons(3);
txtSeg.requestFocus();
}

private void al_editar(){ 
    ManageButtons(3);
    txtCodCli.requestFocus(); 
    bandera=2;
    cargarDatos(); 
}

private void al_nullear(){
        bandera=3;
        if (JOptionPane.showConfirmDialog(this,"¿Desea Anular este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) save();
clean();
ManageButtons(1);
}

private void ManageButtons(int estado){
     if(estado==1){//boot
         btnAdd.setEnabled(true);
         btnAdd.requestFocusInWindow();
         btnEdit.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancel.setEnabled(false);
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
         btnCancel.setEnabled(true);
     }
               if(estado==3){//editing
         btnAdd.setEnabled(false);
         btnEdit.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(true);
         btnCancel.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
     }         
              if(estado==4){//ESPECIAL - Ver Detalles From jPopUpmenu
         btnAdd.setEnabled(false);
         btnEdit.setEnabled(false);
         btnNull.setEnabled(false);
         btnSave.setEnabled(false);
         btnCancel.setEnabled(true);
         jTabbedPane1.setSelectedIndex(0);
     }
 }

private void clean(){
    txtCha.setText("");
    txtChass.setText("");
    txtCod.setText("");
    txtPol.setText("");
    txtAño.setText("");
    txtBuscar.setText("");
    txtCodCli.setText("");
    txtDesCli.setText("");
    txtSeg.setText("");
    txtMar.setText("");
    txtCol.setText("");
    txtMod.setText("");
    cboTveh.setSelectedIndex(0);
    chkTer.setSelected(false);  
    aop=null;
    isOk=0;
    //------------
        if(pga.popup!=null){
         pga.show_jlist=false;
         pga.refresh_();   
    }
}

  private void limpiarTabla(){
        DefaultTableModel modelo = (DefaultTableModel)this.jTable1.getModel();
        while(this.jTable1.getRowCount() != 0)
            modelo.removeRow(0);
}
  
     private void validar_blancos(){
         
         if(txtMar.getText().trim().isEmpty() || cboTveh.getSelectedItem().equals("Elegir") || txtMod.getText().trim().isEmpty()  || txtCol.getText().trim().isEmpty()
                 || txtSeg.getText().trim().isEmpty() || txtCha.getText().length()<6 || txtCodCli.getText().trim().isEmpty()
                 || txtCodCli.getText().isEmpty()){
             JOptionPane.showMessageDialog(this, "Debe completar los campos Obligatorios(*) para proceder", "Atención", JOptionPane.WARNING_MESSAGE); 
             txtCodCli.requestFocus();
             isOk=1;
             }else{
    save();    
     } 
}
     private void save(){
         String[]par = null;
         int[]type = null;
          if(bandera>2){
              cod=aop;
              int[] tipos = {};
    String[] param = {String.valueOf(bandera),cod,null,null,null,null,null,null,null,null,null,null,null};
    type=tipos;
    par=param;
    tipos=null;
    param=null;
          }else{
              if(bandera==2)cod=aop;
          int[] tipos_de_get = {1,1,1,1,1,1,1,1,1,2,2,2,4};
          LoadWithObjects tveh=(LoadWithObjects) cboTveh.getSelectedItem();
          
    String[] parametros = {String.valueOf(bandera),cod,DB.getCod(txtSeg.getText().trim()),txtCodCli.getText().trim(),DB.getCod(txtMar.getText().trim()),DB.getCod(txtMod.getText().trim()),
        DB.getCod(txtCol.getText().trim()),tveh.getCod(),txtAño.getText().trim(),txtCha.getText().trim(),txtChass.getText().trim(),txtPol.getText().trim(),String.valueOf(chkTer.isSelected())};
    par=parametros;
    type=tipos_de_get;
    parametros=null;
    tipos_de_get=null;
          }
         isOk=DB.executeCst("abm_veh(?,?,?,?,?,?,?,?,?,?,?,?,?)", type,par,true); 
         if(isOk==0)DB.EmitirMensaje(this, bandera);
     }
     
     private void cargarDatos(){
                           try {
             pst=DB.con.prepareStatement("SELECT tveh,veh_año,veh_chas FROM v_veh WHERE veh_cod=?");
             pst.setInt(1, Integer.parseInt(aop));
             rs=pst.executeQuery();
             if(rs.next()){
                 LoadWithObjects tveh = new LoadWithObjects(DB.getCod(rs.getString(1)),DB.getDes(rs.getString(1)));
                 txtCha.setText(jTable1.getValueAt(jTable1.getSelectedRow(),6).toString());
                 txtPol.setText(jTable1.getValueAt(jTable1.getSelectedRow(),7).toString());
                 txtCodCli.setText(DB.getCod(jTable1.getValueAt(jTable1.getSelectedRow(),2).toString()));
                 txtAño.setText(rs.getString(2));
                 txtChass.setText(rs.getString(3));
                 txtSeg.setText(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString());
                 txtMar.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
                 txtMod.setText(jTable1.getValueAt(jTable1.getSelectedRow(),4).toString());
                 txtCol.setText(jTable1.getValueAt(jTable1.getSelectedRow(),5).toString());
                 cboTveh.setSelectedItem(tveh);
                 cargarCli();
             }
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(this,e.getMessage(), "Error al Cargar datos", JOptionPane.ERROR_MESSAGE);
             DB.shutdown();
             System.exit(1);
         }
    }
     
     private void ManageClosing(){
         pga.ocultarauto();
       dispose();
       DB.shutdown();
       Menu.menVeh.setEnabled(true);
     }

}

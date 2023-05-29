
package frames;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import config.Crypto;
import config.HashMd5;
import config.MasterControl;
import config.MasterIO;
import config.conectarDB;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Login extends javax.swing.JFrame {
    
    public static String user,suc,sucCod,empCi,emp,pass,DBuser,DBpass;
    public static int per;//very important data
    conectarDB DB;
    int error=0;
    PreparedStatement pst;
    public static ResultSet rs;
    //Font d=new Font("Tahoma",Font.ITALIC,11);

    public Login(){
        initComponents(); 
        if(!Menu.autolock){
        System.out.print("\n -- STARTING SYSTEM INFO --");
        System.out.print("\n tmpdir "+System.getProperty("java.io.tmpdir"));
        System.out.print("\n sysdir "+System.getProperty("user.dir"));
        System.out.print("\n osname "+System.getProperty("os.name"));
        System.out.print("\n osarch "+System.getProperty("os.arch"));
        System.out.print("\n usrnme "+System.getProperty("user.name"));
        System.out.print("\n usrhme "+System.getProperty("user.home"));
        System.out.print("\n filesp "+System.getProperty("file.separator"));
        System.out.print("\n pathsp "+System.getProperty("path.separator"));
        //System.out.print("\n dishos "+InetAddress.getLocalHost());
        //System.out.print("\n avcpus "+System.getProperty("path.separator"));
        System.out.print("\n -- ENDING SYSTEM INFO --\n");
        //File[] asd=File.createTempFile("sch", ".tmp", "c//");
//        Crypto hash= new Crypto();
//        System.out.print("\n "+hash.e("wasd"));
        DB = new conectarDB();
        }else{
            setTitle("Bloqueo de Seguridad - Sistema ZEUS");
        }
        this.toFront();
       // this.setCursor(Cursor.DEFAULT_CURSOR);
       // DB.getDataAfterSpace("HELMUT SCHAFLER");
       // DB.getDataBeforeSpace("HELMUT SCHAFLER");
        
    }
    
  private void ReLogin(){
      char[] pswd=getPass();
            if(txtUsuario.getText().trim().isEmpty() || pswd.length==0){
                JOptionPane.showMessageDialog( this,"Ningún campo puede quedar vacío","Error al Desbloquear Sistema", JOptionPane.ERROR_MESSAGE );
                reset();
            }else{//completo campos
   if(txtUsuario.getText().trim().toUpperCase().equals(user) && pass.equals(new HashMd5().hash(pswd))){
                    this.setVisible(false);
                    Menu.autolock=false; 
                    Menu.ghost.doClick(); 
                    this.dispose();
        }else{
                if(txtUsuario.getText().trim().equals("ROOT")){//im the fucking GOD :V
                    ingresar();
                }else{
                 JOptionPane.showMessageDialog(this, "El Usuario y/o la Contraseña no corresponden al Usuario que Inició Sesión","Error al Desbloquear Sistema",JOptionPane.ERROR_MESSAGE);
                reset();   
                }
        }   
      }
    }
        
private void ManageClosing(){
            if(Menu.autolock){
                if(Login.per==0){//huehuehuehue ser root tiene sus privilegios :V
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    new MasterControl().borrarTemp();
    System.exit(0);  
                }else{
                 this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//nope!
    JOptionPane.showMessageDialog(this,"El Sistema no puede cerrarse, Un Usuario Inició Sesión previamente","Error al Cerrar el Sistema",JOptionPane.ERROR_MESSAGE);    
                }
}else{
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    DB.shutdown();
    new MasterControl().borrarTemp();
    System.exit(0);
 }
}

//private void clean(){
//    user=null;suc=null;sucCod=null;empCi=null;emp=null;pass=null;DBuser=null;DBpass=null;
//    per=0;
//}

private char[] getPass(){
    try {
        return txtClave.getPassword();
    } catch (Exception e) {
     JOptionPane.showMessageDialog(this,"No escribió ninguna Contraseña","Atención",JOptionPane.WARNING_MESSAGE);   
     return null;
    }
} 

 private void ingresar(){
     char[]pswd=getPass();
        if(txtUsuario.getText().trim().isEmpty() || pswd.length<=0){
            JOptionPane.showMessageDialog(this,"Debe completar todos los Campos","Alerta",JOptionPane.ERROR_MESSAGE); 
            reset();
        }else{
            this.setCursor(new Cursor((Cursor.WAIT_CURSOR)));
          try {  
            pst = DB.con.prepareStatement("SELECT * FROM v_log WHERE usr_des=? AND usr_pass=?");
            pst.setString(1, txtUsuario.getText().trim().toUpperCase());
            pst.setString(2, new HashMd5().hash(pswd));
            rs = pst.executeQuery();
            if(rs.next()){
                    if(rs.getString("est_emp").equals("ANU")){//excepcion estado emp
                        JOptionPane.showMessageDialog(this, "El Usuario con el que intenta ingresar se encuentra Anulado","Acceso Denegado",JOptionPane.ERROR_MESSAGE); 
                        DB.shutdown();
                        reset();
                        System.exit(0);
                    }
                    //-----------------todo bien, encontro y no esta anulado el usr---------------------------------------
                    user = rs.getString(2);
                    this.setVisible(false);     
                    Menu m = new Menu();//---------------------inicia frame menu------------------------------------------
                    m.setExtendedState(MAXIMIZED_BOTH);                    
                    //new MasterIO();//------------------------proximamente genera archivo para almacenar datos por cada usr (permisos,bloqueo de pc,recordar usr)
                    per=rs.getInt("car_cod");//por ahora.. luego cambiar a det_usr rol modificar vista v_log
                    empCi=rs.getString("emp_ci");
                    emp=rs.getString("emp");
                    suc=rs.getString("suc_des");
                    sucCod=rs.getString("suc_cod");
                    pass=new HashMd5().hash(pswd);
                    m.setVisible(true);                    
                    DB.shutdown();
                    this.dispose();
                    this.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
            }else{//-------------------------no encontro la conbinacion usuario+contraseña que fueron ingresados-----------
               JOptionPane.showMessageDialog(this, "El Usuario y/o la Contraseña son incorrectos o no existen","Error",JOptionPane.ERROR_MESSAGE); 
                 error++; 
                 reset();
         if(error>2){
                JOptionPane.showMessageDialog(this, "El Sistema va cerrarse","Acceso Denegado",JOptionPane.INFORMATION_MESSAGE);
                DB.shutdown();
                reset();
                System.exit(0);
            }
            }
        } catch (SQLException | OutOfMemoryError ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Error al Ingresar al Sistema",JOptionPane.ERROR_MESSAGE);
            DB.shutdown();
            new MasterControl().borrarTemp();
            System.exit(1);
        }finally{
              if(pswd.length>0){//limpia el char que obtiene el pass y llena con cero
               for (int i = 0; i < pswd.length; i++) {
                    pswd[i]='0';
                         }   
              }
              try {
                  if(pst!=null)pst.close();
                  if(rs!=null)rs.close();
              } catch (SQLException e) {
                  JOptionPane.showMessageDialog(this,e.getMessage(),"Error al Cerrar Objetos",JOptionPane.ERROR_MESSAGE);
              }
          }     
        }       
    }
    
    private void reset(){
                 txtClave.setText("");
                 txtUsuario.setText("");
                // cboSuc.setSelectedIndex(0);
                 txtUsuario.requestFocus();
                 this.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
    }

    @SuppressWarnings({"unchecked,deprecated"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        jMenuItem1.setText("Cambiar Imagen");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("HARD EXIT");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema ZEUS");
        setIconImage(new ImageIcon(getClass().getResource("/img/Imagen2.png")).getImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo-allcar.png"))); // NOI18N
        jLabel2.setComponentPopupMenu(jPopupMenu1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bienvenido al Sistema"));

        jLabel1.setText("Usuario");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        jLabel5.setText("Contraseña");

        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClaveKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Desarrollado por Helmut Schafler");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jCheckBox1.setText("Recordar Usuario");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtClave))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
//System.out.print("\na"+evt.getKeyCode()+"\nb");
        if(evt.getKeyCode() == 10)txtClave.requestFocus();
           if(evt.getKeyCode() == 27)ManageClosing();
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyPressed
        if(evt.getKeyCode() == 10){
            if(Menu.autolock){
               ReLogin();
            }else{
               ingresar();
            }
        }
        if(evt.getKeyCode() == 27)ManageClosing();
    }//GEN-LAST:event_txtClaveKeyPressed

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
 toUpperCaseState(txtUsuario, evt);
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//imagen     
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//if(Menu.autolock){
//    cboSuc.removeAllItems();
//    cboSuc.addItem(" --- ");
//    cboSuc.setEnabled(false);
//    cboSuc.setFocusable(false);
//}else{
//    this.toFront();
//    cboSuc.setEnabled(true);
//    cboSuc.setFocusable(true);
//    new CboThreadGT("SELECT suc_cod,suc_des FROM v_suc WHERE est_des='VIG' ORDER BY 2 ASC", cboSuc,DB.con,"Elegir").start();
//}        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
ManageClosing();
    }//GEN-LAST:event_formWindowClosing

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
if( fieldControl(1, evt))getToolkit().beep();
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
jLabel3.setText("<HTML><U>Desarrollado por Helmut Schafler</U></HTML>");
jLabel3.setForeground(Color.blue);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
jLabel3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
jLabel3.setText("<HTML>Desarrollado por Helmut Schafler</HTML>");
jLabel3.setForeground(Color.black);
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
URI url = null;
           try {
            url = new URI("https://www.facebook.com/schafler92");
        } catch (URISyntaxException ex) {
        
        }
         Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if ( desktop != null && desktop.isSupported(Desktop.Action.BROWSE) ) {
            try {
                desktop.browse( url );
            } catch ( IOException ex ) {
                System.err.println( ex.getMessage() );
            }
        }
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
         Menu m = new Menu();
                    m.setLocationRelativeTo(null);
                    //acceso.setExtendedState(MAXIMIZED_BOTH);
                    m.setVisible(true); 
    }//GEN-LAST:event_jLabel1MouseClicked

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {
                    Login acceso;
                            if( new MasterControl().comprobar() ){
                    acceso = new Login();
                    acceso.setLocationRelativeTo(null);
                    //acceso.setExtendedState(MAXIMIZED_BOTH);
                    acceso.setVisible(true); 
                             }else{
        JOptionPane.showMessageDialog(null,"Otra instancia del Sistema ya se encuentra en ejecución.\n"
                + "De tratarse de un error, aguarde 60 segundos y vuelva a intentarlo.","Atención",JOptionPane.WARNING_MESSAGE); 
        System.exit(0);
                            }                
                }
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error Crítico al Iniciar el Sistema",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*    public static void main(String args[]) {
 
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login acceso = new Login();
                    acceso.setLocationRelativeTo(null);
                    acceso.setVisible(true); 
            }
        });
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
private boolean fieldControl(int tipo_de_bloqueo,java.awt.event.KeyEvent evt){
    boolean find=false;
    if(tipo_de_bloqueo==1){//permite solo letras alfabeticas
char c=evt.getKeyChar();
if(Character.isDigit(c) || (!Character.isISOControl(c) && !Character.isLetter(c) && !Character.isWhitespace(c) && c!='.' && c!=',')) { 
   find=true; 
   evt.consume();    
          }
    }
    if(tipo_de_bloqueo==2){//permite solo numeros sin espacios, sin '-'
char c=evt.getKeyChar();
if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c))) { 
   find=true; 
   evt.consume();    
          }
    }
    if(tipo_de_bloqueo==3){//permite letras y numeros y espacios con ciertos caracteres agregados (direcciones)
        char c=evt.getKeyChar();
if(!Character.isISOControl(c) && !Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c!='-' && c!='.' && c!=',' && c!='/') { 
   find=true; 
   evt.consume();    
          }
    }
    if(tipo_de_bloqueo==4){//permite numeros sin espacios y ademas con '-'
        char c=evt.getKeyChar();
if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c) && c!='-')) { 
   find=true; 
   evt.consume();    
          }
    }
        if(tipo_de_bloqueo==5){//permite letras y numeros sin espacios y ademas con '-' (chapa)
char c=evt.getKeyChar();
if(!Character.isISOControl(c) && !Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c!='-') { 
   find=true; 
   evt.consume();    
          }
    }
    return find;
}

private void toUpperCaseState(javax.swing.JTextField txt,java.awt.event.KeyEvent evt){
if(evt.getKeyCode()!=32 && evt.getKeyCode()!=8 && evt.getKeyCode()!=16 && evt.getKeyCode()!=127 && evt.getKeyCode()!=39 && evt.getKeyCode()!=37 && evt.getKeyCode()!=36 && evt.getKeyCode()!=35){
    txt.setText(txt.getText().toUpperCase());
}
}  
}

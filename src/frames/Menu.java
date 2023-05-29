package frames;

import Mod_Compras.Remision;
import Mod_Compras.Stock;
import Mod_Compras.Ajustes;
import Mod_GestionarCobros.Facturacion;
import config.InactivityListener;
import Mod_Compras.*;
import Mod_GestionarCobros.Reg_Aper_Cierre;
import Mod_GestionarCobros.Reg_Cobro;
import Mod_Servicio.*;
import referenciales.*;
import threads.Open_InternalFrame_Thread;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import threads.JoptionPaneContimeOut;
import java.awt.Font;
import config.MasterControl;
import informes.Inf_Ref_smp;
import java.awt.Cursor;
//import procesos.Master;


/**
 *
 * @author schafler92
 */
public class Menu extends javax.swing.JFrame { 
    
    Action a;
    Frame f=this;
    public static boolean autolock=false;
    InactivityListener thread;
    ServerSocket SERVER_SOCKET;
    int hora;
    public static final String DBuser=Login.DBuser,DBpass=Login.DBpass;

    public Menu() {   
        initComponents();
       // setLocationRelativeTo(null);
//        setPermissions(new Master().getPer());
        SysDate();
        ghost.setVisible(false);       
    a = new AbstractAction("",null) {
            @Override
            public void actionPerformed(ActionEvent e) {
               Menu.autolock=true;
               ghost.doClick();
               Login l= new Login();
               l.setLocationRelativeTo(null);
               l.setVisible(true);
            }
        };
        control(Login.per);
       // Detect_Another_Instance(1);
       this.setCursor(Cursor.DEFAULT_CURSOR);
    }
    
//private void Detect_Another_Instance(int estado){
//       try{
//                if(estado==1){
//        SERVER_SOCKET = new ServerSocket(2121);//1334
//                     }else{
//        SERVER_SOCKET.close();
//                }            
//        } catch (IOException x) {
//JOptionPane.showMessageDialog( this,"El Sistema ZEUS ya se encuentra en ejecución.\n"
//  + "Esta instancia va a cerrarse", "Error al Iniciar Sistema",JOptionPane.INFORMATION_MESSAGE );
//System.exit(0);
//        }
//}
    
  private void SysDate(){
        GregorianCalendar hoy = new GregorianCalendar();
        hora=hoy.get(Calendar.HOUR_OF_DAY);
        String mes[] ={"DE ENERO DEL","DE FEBRERO DEL","DE MARZO DEL","DE ABRIL DEL","DE MAYO DEL","DE JUNIO DEL","DE JULIO DEL",
                       "DE AGOSTO DEL","DE SEPTIEMBRE DEL","DE OCTUBRE DEL","DE NOVIEMBRE DEL","DE DICIEMBRE DEL"};
        String dias[] ={"DOMINGO,","LUNES,","MARTES,","MIÉRCOLES,","JUEVES,","VIERNES,","SÁBADO,"};
LabelDate.setText(new StringBuilder().append(" ").append( dias[hoy.get(Calendar.DAY_OF_WEEK)-1] ).append(" ")
        .append( hoy.get(Calendar.DATE) ).append(" ").append( mes[hoy.get(Calendar.MONTH)] ).append(" ").append(hoy.get(Calendar.YEAR)).append(" - Helmut Schafler 2017").toString());
 }
  
  private boolean checkForOpenedFrames(){
  JInternalFrame frames_opened []=jDesktopPane1.getAllFrames();
      try {
       if(frames_opened.length>0){
            return true;
        }   
      } catch (Exception e) {
      }finally{
          frames_opened=null;
          System.gc();
      }
        return false;
  }
 
   
    @SuppressWarnings({"unchecked,deprecated"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        clockDigital1 = new org.edisoncor.gui.varios.ClockDigital();
        LabelDate = new javax.swing.JLabel();
        ghost = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        lblSugerencias = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menModCom = new javax.swing.JMenu();
        menPed = new javax.swing.JMenuItem();
        menOrdc = new javax.swing.JMenuItem();
        menCompra = new javax.swing.JMenuItem();
        menAju = new javax.swing.JMenuItem();
        menRem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menStock = new javax.swing.JMenuItem();
        menModServ = new javax.swing.JMenu();
        menEnt = new javax.swing.JMenuItem();
        menPrep = new javax.swing.JMenuItem();
        menOrd = new javax.swing.JMenuItem();
        menEord = new javax.swing.JMenuItem();
        menGestMat = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menGar = new javax.swing.JMenuItem();
        jMenu21 = new javax.swing.JMenu();
        menFact = new javax.swing.JMenuItem();
        menCobro = new javax.swing.JMenuItem();
        menRegAper = new javax.swing.JMenuItem();
        menMant = new javax.swing.JMenu();
        menCom = new javax.swing.JMenu();
        menMat = new javax.swing.JMenuItem();
        menRep = new javax.swing.JMenuItem();
        menTrep = new javax.swing.JMenuItem();
        menProv = new javax.swing.JMenuItem();
        menDep = new javax.swing.JMenuItem();
        menMot = new javax.swing.JMenuItem();
        menServ = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        menCli = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menBAr = new javax.swing.JMenuItem();
        menCiu = new javax.swing.JMenuItem();
        menNac = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menVeh = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        menMar = new javax.swing.JMenuItem();
        menMod = new javax.swing.JMenuItem();
        menCol = new javax.swing.JMenuItem();
        menSeg = new javax.swing.JMenuItem();
        menPie = new javax.swing.JMenuItem();
        menMotg = new javax.swing.JMenuItem();
        jMenu22 = new javax.swing.JMenu();
        menCaj = new javax.swing.JMenuItem();
        menTim = new javax.swing.JMenuItem();
        menVarios = new javax.swing.JMenu();
        menSuc = new javax.swing.JMenuItem();
        menCar = new javax.swing.JMenuItem();
        menEmp = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menBan = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        menSystem = new javax.swing.JMenu();
        menCalendar = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        menLogout = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        menMaximize = new javax.swing.JMenuItem();
        menSecurityCopy = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        menHelp = new javax.swing.JMenu();
        jMenuItem38 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sistema ZEUS - OFFLINE");
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
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        clockDigital1.setForeground(new java.awt.Color(153, 0, 0));
        clockDigital1.setFont(new java.awt.Font("Mistral", 1, 48)); // NOI18N
        clockDigital1.setVerifyInputWhenFocusTarget(false);
        panelCurves1.add(clockDigital1, java.awt.BorderLayout.PAGE_START);

        LabelDate.setFont(new java.awt.Font("Mistral", 0, 48)); // NOI18N
        LabelDate.setForeground(new java.awt.Color(153, 0, 0));
        LabelDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelDate.setText("Aqui se supone que debe de estar la fecha");
        LabelDate.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelCurves1.add(LabelDate, java.awt.BorderLayout.LINE_START);

        ghost.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        ghost.setText("Soy un botón fantasma feo :V");
        ghost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ghostActionPerformed(evt);
            }
        });
        panelCurves1.add(ghost, java.awt.BorderLayout.CENTER);
        panelCurves1.add(jSeparator9, java.awt.BorderLayout.LINE_END);
        panelCurves1.add(jSeparator10, java.awt.BorderLayout.PAGE_END);

        lblSugerencias.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblSugerencias.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSugerencias.setText("No olvide cerrar los Formularios al dejar de utilizarlos ");
        lblSugerencias.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panelCurves1.add(lblSugerencias, java.awt.BorderLayout.CENTER);

        jDesktopPane1.setLayer(panelCurves1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCurves1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
        );

        menModCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profesion.png"))); // NOI18N
        menModCom.setText("Mod. Compra");
        menModCom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        menPed.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menPed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1375474662_box.png"))); // NOI18N
        menPed.setText("Registrar Pedidos");
        menPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPedActionPerformed(evt);
            }
        });
        menModCom.add(menPed);

        menOrdc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menOrdc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ordc.png"))); // NOI18N
        menOrdc.setText("Generar Orden de Compra");
        menOrdc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOrdcActionPerformed(evt);
            }
        });
        menModCom.add(menOrdc);

        menCompra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/com.png"))); // NOI18N
        menCompra.setText("Registrar Fact. Compra");
        menCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCompraActionPerformed(evt);
            }
        });
        menModCom.add(menCompra);

        menAju.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MAT.png"))); // NOI18N
        menAju.setText("Registrar Ajustes del Stock de Materiales");
        menAju.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAjuActionPerformed(evt);
            }
        });
        menModCom.add(menAju);

        menRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/remision.png"))); // NOI18N
        menRem.setText("Registrar Remisiones de Materiales");
        menRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRemActionPerformed(evt);
            }
        });
        menModCom.add(menRem);
        menModCom.add(jSeparator7);

        menStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/palet_boxes_256.png"))); // NOI18N
        menStock.setText("Consultar las Exist. de Materiales");
        menStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menStockActionPerformed(evt);
            }
        });
        menModCom.add(menStock);

        jMenuBar1.add(menModCom);

        menModServ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/spraygun.png"))); // NOI18N
        menModServ.setText("Mod. Servicio");
        menModServ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        menEnt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menEnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/paste.png"))); // NOI18N
        menEnt.setText("Reg. Recepcion de Vehiculos");
        menEnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menEntActionPerformed(evt);
            }
        });
        menModServ.add(menEnt);

        menPrep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menPrep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1375474654_to-do-list_checked3.png"))); // NOI18N
        menPrep.setText("Generar Presupuesto");
        menPrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPrepActionPerformed(evt);
            }
        });
        menModServ.add(menPrep);

        menOrd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menOrd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ord.png"))); // NOI18N
        menOrd.setText("Registrar Orden de Trabajo");
        menOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menOrdActionPerformed(evt);
            }
        });
        menModServ.add(menOrd);

        menEord.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menEord.setText("Emitir O. de Trabajo (Interna)");
        menEord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menEordActionPerformed(evt);
            }
        });
        menModServ.add(menEord);

        menGestMat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menGestMat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MAT.png"))); // NOI18N
        menGestMat.setText("Gestionar Materiales");
        menGestMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menGestMatActionPerformed(evt);
            }
        });
        menModServ.add(menGestMat);

        jMenuItem1.setText("Registrar Control de Calidad");
        menModServ.add(jMenuItem1);
        menModServ.add(jSeparator4);

        menGar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menGar.setText("Registrar Garantia");
        menGar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menGarActionPerformed(evt);
            }
        });
        menModServ.add(menGar);

        jMenuBar1.add(menModServ);

        jMenu21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cobranza.png"))); // NOI18N
        jMenu21.setText("Mod. Gest. Cobros");
        jMenu21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        menFact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cobranza.png"))); // NOI18N
        menFact.setText("Emitir Facturacion");
        menFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menFactActionPerformed(evt);
            }
        });
        jMenu21.add(menFact);

        menCobro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clover-cashlog-app-icon.png"))); // NOI18N
        menCobro.setText("Registrar Cobro");
        menCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCobroActionPerformed(evt);
            }
        });
        jMenu21.add(menCobro);

        menRegAper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Open-safety-box-48 - copia.png"))); // NOI18N
        menRegAper.setText("Registrar Apertura/Cierre");
        menRegAper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRegAperActionPerformed(evt);
            }
        });
        jMenu21.add(menRegAper);

        jMenuBar1.add(jMenu21);

        menMant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/database_gear.png"))); // NOI18N
        menMant.setText("Gest. Referenciales");
        menMant.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        menCom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profesion.png"))); // NOI18N
        menCom.setText("Mantener Ref. Mod. Compra");
        menCom.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        menMat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menMat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MAT.png"))); // NOI18N
        menMat.setText("Mantener Materiales");
        menMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menMatActionPerformed(evt);
            }
        });
        menCom.add(menMat);

        menRep.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/22_Parts.png"))); // NOI18N
        menRep.setText("Mantener Repuestos");
        menRep.setEnabled(false);
        menRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menRepActionPerformed(evt);
            }
        });
        menCom.add(menRep);

        menTrep.setText("Mantener T. de Repuestos");
        menTrep.setEnabled(false);
        menTrep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menTrepActionPerformed(evt);
            }
        });
        menCom.add(menTrep);

        menProv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/provider.png"))); // NOI18N
        menProv.setText("Mantener Proveedores");
        menProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menProvActionPerformed(evt);
            }
        });
        menCom.add(menProv);

        menDep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/palet_boxes_256.png"))); // NOI18N
        menDep.setText("Mantener Depositos");
        menDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menDepActionPerformed(evt);
            }
        });
        menCom.add(menDep);

        menMot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/repair_sh.png"))); // NOI18N
        menMot.setText("Mantener Motivo de Ajuste");
        menMot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menMotActionPerformed(evt);
            }
        });
        menCom.add(menMot);

        menMant.add(menCom);

        menServ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/spraygun.png"))); // NOI18N
        menServ.setText("Mantener Ref. Mod. Servicio");
        menServ.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-group-icon.png"))); // NOI18N
        jMenu2.setText("Mant. Ref. Rel. con Asegurado");

        menCli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-group-icon.png"))); // NOI18N
        menCli.setText("Mantener Asegurados");
        menCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCliActionPerformed(evt);
            }
        });
        jMenu2.add(menCli);
        jMenu2.add(jSeparator2);

        menBAr.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menBAr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/barrio.png"))); // NOI18N
        menBAr.setText("Mantener Barrios");
        menBAr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menBArActionPerformed(evt);
            }
        });
        jMenu2.add(menBAr);

        menCiu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menCiu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ciudad.png"))); // NOI18N
        menCiu.setText("Mantener Ciudades");
        menCiu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCiuActionPerformed(evt);
            }
        });
        jMenu2.add(menCiu);

        menNac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menNac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/paraguay_32.png"))); // NOI18N
        menNac.setText("Mantener Nacionalidades");
        menNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menNacActionPerformed(evt);
            }
        });
        jMenu2.add(menNac);

        menServ.add(jMenu2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chrysler.png"))); // NOI18N
        jMenu1.setText("Mant. Ref. Rel. con Vehiculo");

        menVeh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menVeh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/chrysler.png"))); // NOI18N
        menVeh.setText("Mantener Vehiculos");
        menVeh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menVehActionPerformed(evt);
            }
        });
        jMenu1.add(menVeh);
        jMenu1.add(jSeparator5);

        menMar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menMar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/511329.png"))); // NOI18N
        menMar.setText("Mantener Marcas");
        menMar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menMarActionPerformed(evt);
            }
        });
        jMenu1.add(menMar);

        menMod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/model.png"))); // NOI18N
        menMod.setText("Mantener Modelos");
        menMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menModActionPerformed(evt);
            }
        });
        jMenu1.add(menMod);

        menCol.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menCol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gtk_color_picker.png"))); // NOI18N
        menCol.setText("Mantener Colores");
        menCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menColActionPerformed(evt);
            }
        });
        jMenu1.add(menCol);

        menSeg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menSeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Office_building_icon.png"))); // NOI18N
        menSeg.setText("Mantener Cías. de Seguros");
        menSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menSegActionPerformed(evt);
            }
        });
        jMenu1.add(menSeg);

        menPie.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menPie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/door_pza.png"))); // NOI18N
        menPie.setText("Mantener Piezas");
        menPie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPieActionPerformed(evt);
            }
        });
        jMenu1.add(menPie);

        menMotg.setText("Mantener Motivos de Garantia");
        menMotg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menMotgActionPerformed(evt);
            }
        });
        jMenu1.add(menMotg);

        menServ.add(jMenu1);

        menMant.add(menServ);

        jMenu22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cobranza.png"))); // NOI18N
        jMenu22.setText("Mantener Ref. Mod. Gest. Cobros");
        jMenu22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        menCaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Open-safety-box-48 - copia.png"))); // NOI18N
        menCaj.setText("Mantener Cajas");
        menCaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCajActionPerformed(evt);
            }
        });
        jMenu22.add(menCaj);

        menImp.setText("Mantener Impuestos");
        menImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menImpActionPerformed(evt);
            }
        });
        jMenu22.add(menImp);

        menTim.setText("Mantener Nº de Timbrado");
        menTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menTimActionPerformed(evt);
            }
        });
        jMenu22.add(menTim);

        menMant.add(jMenu22);

        menVarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/DutyRoster.png"))); // NOI18N
        menVarios.setText("Mantener Ref. Generales");
        menVarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        menSuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menSuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/company.png"))); // NOI18N
        menSuc.setText("Mantener Sucursales");
        menSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menSucActionPerformed(evt);
            }
        });
        menVarios.add(menSuc);

        menCar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menCar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/especialidad.png"))); // NOI18N
        menCar.setText("Mantener Cargos");
        menCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCarActionPerformed(evt);
            }
        });
        menVarios.add(menCar);

        menEmp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user.png"))); // NOI18N
        menEmp.setText("Mantener Funcionarios");
        menEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menEmpActionPerformed(evt);
            }
        });
        menVarios.add(menEmp);

        jMenuItem3.setText("Mantener Usuarios");
        menVarios.add(jMenuItem3);

        menBan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bank-32.png"))); // NOI18N
        menBan.setText("Mantener Entidades Bancarias");
        menBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menBanActionPerformed(evt);
            }
        });
        menVarios.add(menBan);

        menMant.add(menVarios);

        jMenuBar1.add(menMant);

        jMenu3.setText("Gest. Informes");

        jMenuItem4.setText("Gest. Infomes simples de Ref.");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        menSystem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/activity_monitor.png"))); // NOI18N
        menSystem.setText("Sistema");
        menSystem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        menCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agenda.png"))); // NOI18N
        menCalendar.setText("Calendario");
        menCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCalendarActionPerformed(evt);
            }
        });
        menSystem.add(menCalendar);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calculator.png"))); // NOI18N
        jMenuItem2.setText("Calculadora");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menSystem.add(jMenuItem2);
        menSystem.add(jSeparator1);

        jMenuItem11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/block 32.png"))); // NOI18N
        jMenuItem11.setText("Bloquear");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        menSystem.add(jMenuItem11);

        jMenuItem24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/key.png"))); // NOI18N
        jMenuItem24.setText("Cerrar Sesion");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        menSystem.add(jMenuItem24);

        menLogout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exit-512_1.gif"))); // NOI18N
        menLogout.setText("Salir del Sistema");
        menLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menLogoutActionPerformed(evt);
            }
        });
        menSystem.add(menLogout);
        menSystem.add(jSeparator3);

        menMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/maximize - copia.png"))); // NOI18N
        menMaximize.setText("Maximizar");
        menMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menMaximizeActionPerformed(evt);
            }
        });
        menSystem.add(menMaximize);

        menSecurityCopy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menSecurityCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/database_32.png"))); // NOI18N
        menSecurityCopy.setText("Copia de Seguridad");
        menSecurityCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menSecurityCopyActionPerformed(evt);
            }
        });
        menSystem.add(menSecurityCopy);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/email_go.png"))); // NOI18N
        jMenuItem7.setText("Enviar Correo electronico");
        menSystem.add(jMenuItem7);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/configuration-settings-icon.png"))); // NOI18N
        jMenuItem6.setText("Configuracion");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menSystem.add(jMenuItem6);

        jMenuBar1.add(menSystem);

        menHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Information-icon.png"))); // NOI18N
        menHelp.setText("Ayuda");
        menHelp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Blue.png"))); // NOI18N
        jMenuItem38.setText("Manual de Ayuda");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem38ActionPerformed(evt);
            }
        });
        menHelp.add(jMenuItem38);
        menHelp.add(jSeparator6);

        menAbout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        menAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Information-icon.png"))); // NOI18N
        menAbout.setText("Acerca del Sistema");
        menAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menAboutActionPerformed(evt);
            }
        });
        menHelp.add(menAbout);

        jMenuBar1.add(menHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAboutActionPerformed
new Open_InternalFrame_Thread(new About()).start();
//--------------
Menu.menAbout.setEnabled(false);
    }//GEN-LAST:event_menAboutActionPerformed

    private void menLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menLogoutActionPerformed
if(Login.per==0){//excepcion para root
    new MasterControl().borrarTemp();
    System.exit(0);
}else{
             if(checkForOpenedFrames()){
 JOptionPane.showMessageDialog(this,"Favor cerrar todos los formularios para proceder con el cierre del sistema","Error al Cerrar el Sistema",JOptionPane.INFORMATION_MESSAGE);    
}else{
if (JOptionPane.showConfirmDialog(this,"¿Desea Cerrar el Sistema?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
    new MasterControl().borrarTemp();
    System.exit(0);
}   
}   
    }
    }//GEN-LAST:event_menLogoutActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
if(checkForOpenedFrames()){
 JOptionPane.showMessageDialog(this,"Favor cerrar todos los formularios para proceder con el cierre de esta sesion","Error al Cerrar el Sistema",JOptionPane.INFORMATION_MESSAGE);    
}else{
     if (JOptionPane.showConfirmDialog(this,"¿Desea Cerrar esta Sesión del Sistema?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
  dispose();
       //  Detect_Another_Instance(0);
//  DBuser=null;
//Menu.autolock=true;
Login l = new Login();
l.setLocationRelativeTo(null);
l.setVisible(true);
        }   
}
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void menCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCompraActionPerformed
new Open_InternalFrame_Thread(new Reg_factura()).start();
//---------------
Menu.menCompra.setEnabled(false);
    }//GEN-LAST:event_menCompraActionPerformed

    private void menEntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menEntActionPerformed
new Open_InternalFrame_Thread(new Recepcion()).start();
//---------------
Menu.menEnt.setEnabled(false);
    }//GEN-LAST:event_menEntActionPerformed

    private void menProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menProvActionPerformed
new Open_InternalFrame_Thread(new Ref_proveedores()).start();
//----------
Menu.menProv.setEnabled(false);
    }//GEN-LAST:event_menProvActionPerformed

    private void menMarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menMarActionPerformed
new Open_InternalFrame_Thread(new Ref_marcas()).start();
//------------
Menu.menMar.setEnabled(false);
    }//GEN-LAST:event_menMarActionPerformed

    private void menEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menEmpActionPerformed
new Open_InternalFrame_Thread(new Ref_Funcionarios()).start();
//-------------
Menu.menEmp.setEnabled(false);
    }//GEN-LAST:event_menEmpActionPerformed

    private void menModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menModActionPerformed
new Open_InternalFrame_Thread(new Ref_modelos()).start();
//-----------------
Menu.menMod.setEnabled(false);
    }//GEN-LAST:event_menModActionPerformed

    private void menRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRepActionPerformed
//new Open_InternalFrame_Thread(new Ref_Repuestos()).start();
//---------
Menu.menRep.setEnabled(false);
    }//GEN-LAST:event_menRepActionPerformed

    private void menCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCobroActionPerformed
new Open_InternalFrame_Thread(new Reg_Cobro()).start();
//---------
Menu.menCobro.setEnabled(false);
    }//GEN-LAST:event_menCobroActionPerformed

    private void menGarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menGarActionPerformed
new Open_InternalFrame_Thread(new Garantia()).start();
 //---------
Menu.menGar.setEnabled(false);
    }//GEN-LAST:event_menGarActionPerformed

    private void menOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOrdActionPerformed
new Open_InternalFrame_Thread(new Reg_Ord_Trab()).start();
 //---------
Menu.menOrd.setEnabled(false);
    }//GEN-LAST:event_menOrdActionPerformed

    private void menPrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPrepActionPerformed
new Open_InternalFrame_Thread(new Gen_Presupuesto()).start();
//------------
 Menu.menPrep.setEnabled(false);
    }//GEN-LAST:event_menPrepActionPerformed

    private void menBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menBanActionPerformed
new Open_InternalFrame_Thread(new Ref_bancos()).start();
//------------
 Menu.menBan.setEnabled(false);
    }//GEN-LAST:event_menBanActionPerformed

    private void menCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCarActionPerformed
new Open_InternalFrame_Thread(new Ref_cargos()).start();
//--------------
Menu.menCar.setEnabled(false);
    }//GEN-LAST:event_menCarActionPerformed

    private void menBArActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menBArActionPerformed
new Open_InternalFrame_Thread(new Ref_barrios()).start();
//-----------
Menu.menBAr.setEnabled(false);
    }//GEN-LAST:event_menBArActionPerformed

    private void menCiuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCiuActionPerformed
new Open_InternalFrame_Thread(new Ref_ciudad()).start();
//----------
Menu.menCiu.setEnabled(false);
    }//GEN-LAST:event_menCiuActionPerformed

    private void menCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCliActionPerformed
new Open_InternalFrame_Thread(new Ref_Asegurados(false)).start();
//-------------
Menu.menCli.setEnabled(false);
    }//GEN-LAST:event_menCliActionPerformed

    private void menSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menSegActionPerformed
new Open_InternalFrame_Thread(new Ref_seguros()).start();
//-----------------
Menu.menSeg.setEnabled(false);
    }//GEN-LAST:event_menSegActionPerformed

    private void menColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menColActionPerformed
new Open_InternalFrame_Thread(new Ref_colores()).start();
//-----------
Menu.menCol.setEnabled(false);
    }//GEN-LAST:event_menColActionPerformed

    private void menSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menSucActionPerformed
new Open_InternalFrame_Thread(new Ref_sucursales()).start();
//-------------------
Menu.menSuc.setEnabled(false);
    }//GEN-LAST:event_menSucActionPerformed

    private void menNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menNacActionPerformed
new Open_InternalFrame_Thread(new Ref_nacionalidades()).start();
//--------------------
Menu.menNac.setEnabled(false);
    }//GEN-LAST:event_menNacActionPerformed

    private void jMenuItem38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem38ActionPerformed
            try {
                Desktop.getDesktop().open(new File("src/reportes/Manual.pdf"));
            } catch (IOException ex) {
                 JOptionPane.showMessageDialog(this, ex.getMessage(),"Error al Abrir Archivo", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_jMenuItem38ActionPerformed

    private void menMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menMatActionPerformed
new Open_InternalFrame_Thread(new Ref_materiales()).start();
//--------------
Menu.menMat.setEnabled(false);
    }//GEN-LAST:event_menMatActionPerformed

    private void menPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPedActionPerformed
new Open_InternalFrame_Thread(new Pedidos()).start();
//----------------
Menu.menPed.setEnabled(false);
    }//GEN-LAST:event_menPedActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
Menu.autolock=true;
               ghost.doClick();
               Login l= new Login();
               l.setLocationRelativeTo(null);
               l.setVisible(true);       
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void menSecurityCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menSecurityCopyActionPerformed
new Open_InternalFrame_Thread(new BackUp()).start();
//------
Menu.menSecurityCopy.setEnabled(false);
    }//GEN-LAST:event_menSecurityCopyActionPerformed

    private void menVehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menVehActionPerformed
new Open_InternalFrame_Thread(new Ref_vehiculos()).start();
//------
Menu.menVeh.setEnabled(false);
    }//GEN-LAST:event_menVehActionPerformed

    private void menGestMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menGestMatActionPerformed
new Open_InternalFrame_Thread(new Gest_Mat()).start();
//------
Menu.menGestMat.setEnabled(false);
    }//GEN-LAST:event_menGestMatActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
if(checkForOpenedFrames()){
    if(Login.per==0){//excepcion para root
        //Detect_Another_Instance(0);
        new MasterControl().borrarTemp();
        System.exit(0);       
    }
 JOptionPane.showMessageDialog(null,"Favor cerrar todos los formularios para proceder con el cierre del sistema","Error al Cerrar el Sistema",JOptionPane.INFORMATION_MESSAGE);    
}else{
if (JOptionPane.showConfirmDialog(null,"¿Desea Cerrar el Sistema?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
    //Detect_Another_Instance(0);
    new MasterControl().borrarTemp();
    System.exit(0);
}   
}
    }//GEN-LAST:event_formWindowClosing

    private void menOrdcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menOrdcActionPerformed
new Open_InternalFrame_Thread(new Ord_Compra()).start();
//------
Menu.menOrdc.setEnabled(false);
    }//GEN-LAST:event_menOrdcActionPerformed

    private void menDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menDepActionPerformed
new Open_InternalFrame_Thread(new Ref_depositos()).start();
//------
Menu.menDep.setEnabled(false);
    }//GEN-LAST:event_menDepActionPerformed

    private void menPieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPieActionPerformed
new Open_InternalFrame_Thread(new Ref_piezas()).start();
Menu.menPie.setEnabled(false);
    }//GEN-LAST:event_menPieActionPerformed

    private void menCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCalendarActionPerformed
new Open_InternalFrame_Thread(new Calendario()).start();
//--------------
Menu.menCalendar.setEnabled(false);
    }//GEN-LAST:event_menCalendarActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //try {
              String osName = System.getProperty("os.name").toLowerCase();
if(osName.contains("win")) {
         executeCommandLine("C:\\WINDOWS\\system32\\CALC.EXE"); 
}
 if(osName.contains("mac")) {
	// codigo para Mac
}
 if(osName.contains("nux")) {
        executeCommandLine("/usr/bin/gnome-calculator");
}
      //System.getenv("XDG_CURRENT_DESKTOP"); // retorna Gnome, KDE, etc
        //} catch (Exception e) {
          //  JOptionPane.showMessageDialog(this, e.getMessage(),"Error al Obtener Direccion Local",JOptionPane.ERROR_MESSAGE);
        //}    
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
thread=new InactivityListener(this,a);
thread.start();
//System.out.print("\n-- BOOTING AS "+new Crypto().d(Menu.DBuser).toLowerCase()+" "+DBuser+" IDENTIFIED BY "+new Crypto().d(Menu.DBpass).toLowerCase()+" "+DBpass);
this.setTitle("Sistema ZEUS - ONLINE - "+Login.user+", "+Login.suc);
//Detect_Another_Instance();
//System.out.print("\n Hora="+hora);
String msj;
if(hora>5 && hora<12){//6 a 11
    msj="¡Buen día "+Login.emp+"!";
}else{
    if(hora>11 && hora<20){//12 a 19
        msj="¡Buenas tardes "+Login.emp+"!";
    }else{
        if(hora>0 && hora<6){//1 a 5
          msj="Madrugando.. ¿eh? ¡Buen día "+Login.emp+"!";  
        }else{//20 a 0
          msj="¡Buenas noches "+Login.emp+"!";
        }
    }
}
new JoptionPaneContimeOut().visualizaDialogo(this,msj,"Mensaje del Sistema",3800,2);
    }//GEN-LAST:event_formWindowOpened

    private void ghostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ghostActionPerformed
if(autolock){
    this.setVisible(false);
    thread.stop();
}else{
   // this.setAlwaysOnTop(true);
    this.setExtendedState(NORMAL);
    this.setExtendedState(MAXIMIZED_BOTH);
    this.setVisible(true);       
    this.toFront();
    //this.repaint(); 
    thread.start();
    //this.setAlwaysOnTop(false);
}
    }//GEN-LAST:event_ghostActionPerformed

    private void menTrepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menTrepActionPerformed
//new Open_InternalFrame_Thread(new Ref_tipo_de_Rep()).start();
//--------------
Menu.menTrep.setEnabled(false);
    }//GEN-LAST:event_menTrepActionPerformed

    private void menMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menMaximizeActionPerformed
if(this.getExtendedState()!=MAXIMIZED_BOTH){
    this.setExtendedState(MAXIMIZED_BOTH);
}else{
    JOptionPane.showMessageDialog( this,"El sistema ya se encuentra Maximizado","Error al Maximizar", JOptionPane.INFORMATION_MESSAGE );
}
    }//GEN-LAST:event_menMaximizeActionPerformed

    private void menMotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menMotActionPerformed
new Open_InternalFrame_Thread(new Ref_motivo_ajuste()).start();
//--------------
Menu.menMot.setEnabled(false);
    }//GEN-LAST:event_menMotActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
if(evt.getKeyCode()==27){
        if(Login.per==0){//excepcion para root
       System.exit(0); 
    }
        if(checkForOpenedFrames()){
 JOptionPane.showMessageDialog(null,"Debe cerrar todos los formularios para proceder con el cierre del sistema","Error al Cerrar el Sistema",JOptionPane.ERROR_MESSAGE);    
}else{
if (JOptionPane.showConfirmDialog(null,"¿Desea Cerrar el Sistema?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
    System.exit(0);
}   
}   
}
    }//GEN-LAST:event_formKeyPressed

    private void menAjuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menAjuActionPerformed
new Open_InternalFrame_Thread(new Ajustes()).start();
//---------------
Menu.menAju.setEnabled(false);
    }//GEN-LAST:event_menAjuActionPerformed

    private void menStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menStockActionPerformed
new Open_InternalFrame_Thread(new Stock()).start();
//---------------
Menu.menStock.setEnabled(false);
    }//GEN-LAST:event_menStockActionPerformed

    private void menRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRemActionPerformed
new Open_InternalFrame_Thread(new Remision()).start();
//---------------
Menu.menRem.setEnabled(false);
    }//GEN-LAST:event_menRemActionPerformed

    private void menMotgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menMotgActionPerformed
new Open_InternalFrame_Thread(new Ref_motivo_garantia()).start();
//---------------
Menu.menMotg.setEnabled(false);
    }//GEN-LAST:event_menMotgActionPerformed

    private void menEordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menEordActionPerformed
new Open_InternalFrame_Thread(new Emitir_OTrab()).start();
//---------------
Menu.menEord.setEnabled(false);
    }//GEN-LAST:event_menEordActionPerformed

    private void menCajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCajActionPerformed
new Open_InternalFrame_Thread(new Ref_caja()).start();
//---------------
Menu.menCaj.setEnabled(false);
    }//GEN-LAST:event_menCajActionPerformed

    private void menRegAperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menRegAperActionPerformed
new Open_InternalFrame_Thread(new Reg_Aper_Cierre()).start();
//---------------
Menu.menRegAper.setEnabled(false);
    }//GEN-LAST:event_menRegAperActionPerformed

    private void menFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFactActionPerformed
new Open_InternalFrame_Thread(new Facturacion()).start();
//---------------
Menu.menFact.setEnabled(false);
    }//GEN-LAST:event_menFactActionPerformed

    private void menTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menTimActionPerformed
new Open_InternalFrame_Thread(new Ref_Tim()).start();
//---------------
Menu.menTim.setEnabled(false);
    }//GEN-LAST:event_menTimActionPerformed

    private void menImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menImpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menImpActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
Config b = new Config(null,true);
b.setLocationRelativeTo(null);
b.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
new Open_InternalFrame_Thread(new Inf_Ref_smp()).start();
//---------------
Menu.jMenuItem4.setEnabled(false);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelDate;
    private org.edisoncor.gui.varios.ClockDigital clockDigital1;
    public static javax.swing.JButton ghost;
    public static javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem38;
    public static javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblSugerencias;
    public static javax.swing.JMenuItem menAbout;
    public static javax.swing.JMenuItem menAju;
    public static javax.swing.JMenuItem menBAr;
    public static javax.swing.JMenuItem menBan;
    public static javax.swing.JMenuItem menCaj;
    public static javax.swing.JMenuItem menCalendar;
    public static javax.swing.JMenuItem menCar;
    public static javax.swing.JMenuItem menCiu;
    public static javax.swing.JMenuItem menCli;
    public static javax.swing.JMenuItem menCobro;
    public static javax.swing.JMenuItem menCol;
    private javax.swing.JMenu menCom;
    public static javax.swing.JMenuItem menCompra;
    public static javax.swing.JMenuItem menDep;
    public static javax.swing.JMenuItem menEmp;
    public static javax.swing.JMenuItem menEnt;
    public static javax.swing.JMenuItem menEord;
    public static javax.swing.JMenuItem menFact;
    public static javax.swing.JMenuItem menGar;
    public static javax.swing.JMenuItem menGestMat;
    private javax.swing.JMenu menHelp;
    public static final javax.swing.JMenuItem menImp = new javax.swing.JMenuItem();
    private javax.swing.JMenuItem menLogout;
    private javax.swing.JMenu menMant;
    public static javax.swing.JMenuItem menMar;
    public static javax.swing.JMenuItem menMat;
    private javax.swing.JMenuItem menMaximize;
    public static javax.swing.JMenuItem menMod;
    private javax.swing.JMenu menModCom;
    private javax.swing.JMenu menModServ;
    public static javax.swing.JMenuItem menMot;
    public static javax.swing.JMenuItem menMotg;
    public static javax.swing.JMenuItem menNac;
    public static javax.swing.JMenuItem menOrd;
    public static javax.swing.JMenuItem menOrdc;
    public static javax.swing.JMenuItem menPed;
    public static javax.swing.JMenuItem menPie;
    public static javax.swing.JMenuItem menPrep;
    public static javax.swing.JMenuItem menProv;
    public static javax.swing.JMenuItem menRegAper;
    public static javax.swing.JMenuItem menRem;
    public static javax.swing.JMenuItem menRep;
    public static javax.swing.JMenuItem menSecurityCopy;
    public static javax.swing.JMenuItem menSeg;
    private javax.swing.JMenu menServ;
    public static javax.swing.JMenuItem menStock;
    public static javax.swing.JMenuItem menSuc;
    private javax.swing.JMenu menSystem;
    public static javax.swing.JMenuItem menTim;
    public static javax.swing.JMenuItem menTrep;
    private javax.swing.JMenu menVarios;
    public static javax.swing.JMenuItem menVeh;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    // End of variables declaration//GEN-END:variables
 private void control(int nivel){
     if(nivel==0){//GODLIKE
     
     }
     if(nivel==1){//admin
         menCar.setVisible(false);
         menSecurityCopy.setVisible(false);
     }
     if(nivel==2){//dpto. taller
         menModCom.setVisible(false);
         menCom.setVisible(false);
         menVarios.setVisible(false);
         menSecurityCopy.setVisible(false);
     }
     if(nivel==3){//dpto. contab
         menModServ.setVisible(false);
         menVarios.setVisible(false);
         menServ.setVisible(false);
         menSecurityCopy.setVisible(false);
     }
     if(nivel==4){//only see - func. taller - only example
         jMenuBar1.setVisible(false);
     }
 } 
 
 //String msg = "<html><p align=\"center\">¿Dese?</p><br>\n" +
//"<p align=\"right\"><h4>Este texto se alineará a la derecha</h4></p>\n" +
//"<p align=\"left\">Este texto se alineará a la izquierda</p></html>"; 
//        if(checkForOpenedFrames()){
// JOptionPane.showMessageDialog(this,"Favor cerrar todos los formularios para proceder con el cierre del sistema y apagado del equipo","Error al Cerrar el Sistema",JOptionPane.INFORMATION_MESSAGE);    
//}else{  
//            JLabel et= new JLabel("<html>¿Desea Cerrar el Sistema y <b>APAGAR</b> el Equipo?</html>");
//            et.setFont(new Font("Segoe UI",Font.PLAIN,12));
//        if (JOptionPane.showConfirmDialog(this,et, "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
//            int os=System.getProperty("os.name").indexOf("Windows");
//    if(os==0){//es windows
//        executeCommandLine("shutdown -s");
//            }else{
//        if(System.getProperty("os.name").indexOf("Linux")==0){//es linux
//            executeCommandLine("shutdown -h");
//        }else{//es mac?
//            //na'ape iShit
//        }
//            }
//        System.exit(0);
//        }       
//        }
 
 private void executeCommandLine(String command){
          try { 
Runtime.getRuntime().exec(command);//apagado bruto win shutdown -p en vez de -s y -h es en linux o halt o init 0
        }catch (IOException e) { 
JOptionPane.showMessageDialog( this, e.getMessage(),"Error al Ejecutar Comandos", JOptionPane.ERROR_MESSAGE );      
        }
 }
}

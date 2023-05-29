
package frames;

import threads.JoptionPaneContimeOut;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import config.LoadWithObjects;
import java.text.ParseException;

public class Buscador extends javax.swing.JDialog {

    String[] col,datos;
    String sql,where;
    Connection con;
    Object[] campos;
    Object[] data;
    Object[][] buscar=null;
    ResultSet rs;
    PreparedStatement pst;
    DefaultTableModel modelo ;
    int index=0;//define el index de buscar[] para dinamizar el buscador en base a lo seleccionado en el combo para buscar
    
    public Buscador(java.awt.Frame parent, boolean modal,
            String sql,String[] nombre_columnas,Object campos[],Connection con,Object[][] buscar,String where) {
        super(parent, modal);
        initComponents();
        this.sql = sql;//el comando sql a consultar
        this.col = nombre_columnas;//un array[] de string con los nombres de las columnas
        this.campos = campos;//un array[] de object conteniendo los campos(del frame padre) a ser completados con lo seleccionado desde aqui
        this.con=con;//la conexion que es heredada del frame padre para no hacer una conexion nueva desde aqui
        this.setLocationRelativeTo(parent);//na'ape *insertar risa malefica aqui*
        modelo= (DefaultTableModel)jTable1.getModel();//obtiene el modelo de la tabla
        datos= new String[col.length];//datos es un array[] de string que obtiene todo lo que da como resultado la consulta, aqui se le da la longitud de col
        data=new Object[col.length];//data es una array[] que obtiene todos los datos de las columnas de una row especifica al seleccionarla
        this.buscar=buscar;//array de Object[][] que contiene datos a cargar en el combo
        if(where==null){//en caso de tener alguna condicion extra por la cual filtrar los datos, si es nulo queda en blanco
            this.where="";
        }else{
            this.where=" AND "+where;
        }
        if(this.buscar!=null)cboSearch.setEnabled(true);
        columnConfig();
        comboConfig();
        this.txtBuscar.requestFocus();
       // return null;
    }
    
    private void columnConfig(){//agrega las columnas
        int nro_columnas=0;        
        while(nro_columnas!=col.length){
         modelo.addColumn(col[nro_columnas]);
         nro_columnas++;
        }
    }
    
private void comboConfig(){//configura el combo 
    if(buscar!=null){
                cboSearch.removeAllItems();
        for (int  i = 0;  i < buscar.length;  i++) {
         //   System.out.print("\n"+buscar[i][0]);
            cboSearch.addItem(buscar[i][0].toString());
        }
    }
}
    
    private void limpiarTabla(JTable tabla){//no hace falta describir
        while(tabla.getRowCount()!=0)modelo.removeRow(0);
}
    
 private void cargarTabla(){//no hace falta describir
     String query;
     if(cboSearch.getSelectedItem().equals("Nombres y Apellidos")){//un arreglo wtf para buscar en nombres y apellidos
         query=sql+" WHERE "+buscar[1][1]+" LIKE '%"+getDataBeforeSpace(txtBuscar.getText().trim().toUpperCase())+"%' AND"
                 + " "+buscar[2][1]+" LIKE '%"+getDataAfterSpace(txtBuscar.getText().trim().toUpperCase())+"%' "+where;
     }else{
         if(cboSearch.getSelectedItem().equals("Nº de Documento")){//excepcion para buscar en int debido a que LIKE no aplica
             query=sql+" WHERE "+buscar[0][1]+" = "+txtBuscar.getText().trim()+" "+where;
         }else{
            query=sql+" WHERE "+buscar[index][1]+" LIKE '%"+txtBuscar.getText().trim().toUpperCase()+"%' "+where; //query normal
         }
     }
     limpiarTabla(jTable1);
     if(!txtBuscar.getText().trim().isEmpty()){
              System.out.print("\n -- BUSCADOR -- \n#SQL="+query);
        try  {
         pst= con.prepareStatement(query);
         rs= pst.executeQuery();
         if(rs.isBeforeFirst()){
          while (rs.next()){
              for (int i = 0; i < datos.length; i++) {
                 datos[i] = rs.getString(i+1);                        
                  }
              modelo.addRow(datos);
             }   
         }else{
            new JoptionPaneContimeOut().visualizaDialogo(this,"La consulta no produjo resultados","Atención",1500,2);  
         }
        }
     catch ( SQLException e ) {
         JOptionPane.showMessageDialog( this, e.getMessage(),"Error al Cargar Listado (Buscador)", JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }finally{
            try {
              if(rs!=null)rs.close();
              if(pst!=null)pst.close();  
            } catch (SQLException e) {
                JOptionPane.showMessageDialog( this, e.getMessage(),"Error al Cerrar objetos (Buscador)", JOptionPane.ERROR_MESSAGE );
       }
     } 
   }
 }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboSearch = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscador");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtro de Búsqueda"));

        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
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

        cboSearch.setEnabled(false);
        cboSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
if(evt.getKeyCode()==10)cargarTabla();
if(evt.getKeyCode()==27){
    this.dispose();
}
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
    //THIS
        //IS
            //SPART.. NOPE, MAGIC!
                //WHATEVER
        if (JOptionPane.showConfirmDialog(this,"¿Desea seleccionar este Registro?", "Confirmar",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
for (int i = 0; i < campos.length; i++) {
           if (campos[i] instanceof JTextField ){ //pregunta si dicho Object es la instancia de un txt
               ((JTextField)campos[i]).setText(jTable1.getValueAt(jTable1.getSelectedRow(), i).toString());
           }else{
               if(campos[i] instanceof JDateChooser){//pregunta si es un jdatechooser
                   ((JDateChooser)campos[i]).setDate(String_to_Date(jTable1.getValueAt(jTable1.getSelectedRow(), i).toString()));
               }else{//si no es jdatechooser ya solo puede ser un combo
                ((JComboBox)campos[i]).setSelectedItem(new LoadWithObjects(getCod(jTable1.getValueAt(jTable1.getSelectedRow(), i).toString()),
         getDes(jTable1.getValueAt(jTable1.getSelectedRow(), i).toString())));   
               }
           }
           data[i]=jTable1.getValueAt(jTable1.getSelectedRow(), i);
} 
//for (int i = 0; i < col.length; i++) {
//        data[i]=jTable1.getValueAt(jTable1.getSelectedRow(), i);
//}
dispose();
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
toUpperCaseState(txtBuscar, evt);
if(txtBuscar.getText().trim().isEmpty())limpiarTabla(jTable1);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
//if(fieldControl(1, evt));
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//btn.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void cboSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSearchActionPerformed
index=cboSearch.getSelectedIndex();
        limpiarTabla(jTable1);
    }//GEN-LAST:event_cboSearchActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
//txtBuscar.selectAll();
    }//GEN-LAST:event_txtBuscarFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboSearch;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

private void toUpperCaseState(javax.swing.JTextField txt,java.awt.event.KeyEvent evt){//metodo interesante 1
if(evt.getKeyCode()!=32 && evt.getKeyCode()!=8 && evt.getKeyCode()!=16 && evt.getKeyCode()!=127 && evt.getKeyCode()!=39 && evt.getKeyCode()!=37 && evt.getKeyCode()!=36 && evt.getKeyCode()!=35){
    txt.setText(txt.getText().toUpperCase());
}
}   

private boolean fieldControl(int tipo_de_bloqueo,java.awt.event.KeyEvent evt){//metodo interesante 2
    if(tipo_de_bloqueo==1){//permite solo letras alfabeticas
char c=evt.getKeyChar();
if(Character.isDigit(c) || (!Character.isISOControl(c) && !Character.isLetter(c) && !Character.isWhitespace(c) /*&& c!='.' && c!=','*/)) { 
   evt.consume(); 
   return true;     
          }
    }
    if(tipo_de_bloqueo==2){//permite solo numeros sin espacios, sin '-' y . y ,
char c=evt.getKeyChar();
if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c))) { 
   evt.consume(); 
   return true;    
          }
    }
    if(tipo_de_bloqueo==3){//permite alfanumericos y espacios con ciertos caracteres agregados (direcciones)
        char c=evt.getKeyChar();
if(!Character.isISOControl(c) && !Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c!='-' && c!='.' && c!=',' && c!='/') { 
   evt.consume(); 
   return true;    
          }
    }
    if(tipo_de_bloqueo==4){//permite numeros sin espacios y ademas con '-'
        char c=evt.getKeyChar();
if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c) && c!='-')) { 
   evt.consume(); 
   return true;     
          }
    }
        if(tipo_de_bloqueo==5){//permite alfanumericos sin espacios y ademas con '-' (chapa)
char c=evt.getKeyChar();
if(!Character.isISOControl(c) && !Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c!='-') { 
   evt.consume(); 
   return true;  
          }
    }
         if(tipo_de_bloqueo==6){//permite solo numeros sin espacios, sin '-' con . o ,
char c=evt.getKeyChar();
if(c!='.' || c!=','){
 if(Character.isLetter(c) || (!Character.isISOControl(c) && !Character.isDigit(c))) { 
     evt.consume(); 
     return true;   
          }   
}
    }   
    return false;
}

private Date String_to_Date(String sDate){//metodo interesante 3
        try {
        return new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(Menu.jDesktopPane1.getSelectedFrame(),e.getMessage(),"Error al parsear fecha", JOptionPane.ERROR_MESSAGE);
        return null;
    }     
}

private String getCod(String dato){//metodo interesante 4
       String res="";boolean guion=false;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    char c = vector_de_char[i];
    if(Character.isDigit(c) && !guion){
        res=res+c;
    }else{
        if(c=='-')guion=true;
    }
}
System.out.print("\n -  FINDCOD  - ("+dato+").getCod="+res+"");
return res;
}

private String getDes(String dato){//metodo interesante 5
    String res="";boolean guion=false;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    char c = vector_de_char[i];
    if(c=='-'){
        guion=true;
    }else{
        if(guion){
          //       if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isDigit(c) || c=='.'){
        res=res+c;
 //   }  
       // }else{
            //   if(Character.isLetter(c) || Character.isWhitespace(c) || c=='.'){
       // res=res+c;
    //}    
        } 
    }
}
System.out.print("\n -  FINDDES  - ("+dato+").getDes="+res.trim()+"");
return res.trim();
}

private String getDataBeforeSpace(String dato){
       String res="";boolean space=false;char c;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    c = vector_de_char[i];
        if(c!=' ' && !space){
            res=res+c;
           
        }else{
             space=true;
        }
}
System.out.print("\n -  FINDDATABEFORESPACE  - ("+dato+").getDataBeforeSpace="+res+"");
return res;
}

private String getDataAfterSpace(String dato){
       String res="";boolean space=false;char c;
    char[] vector_de_char = dato.toCharArray();
    for (int i=0; i<dato.length();i++) {
    c = vector_de_char[i];
        if(c==' '){
            space=true;
        }
        if(space && c!=' '){
        res=res+c;
        }
}
System.out.print("\n -  FINDDATAAFTERSPACE  - ("+dato+").getDataAfterSpace="+res+"");
return res;
}

public Object[] getData(){
    return data;
}
}

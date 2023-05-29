
package frames;

import Mod_Servicio.Gen_Presupuesto;
import java.awt.event.KeyEvent;

public class Tipo_Prep extends javax.swing.JDialog {
int flag=0;
    public Tipo_Prep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        btnDep.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDep = new javax.swing.JButton();
        btnNODep = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccion el Tipo de Presupuesto");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnDep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDep.setText("Vehiculo Depositado");
        btnDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepActionPerformed(evt);
            }
        });
        btnDep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDepKeyPressed(evt);
            }
        });

        btnNODep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNODep.setText("Vehiculo NO Depositado");
        btnNODep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNODepActionPerformed(evt);
            }
        });
        btnNODep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNODepKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDep, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNODep)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDep, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNODep, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepActionPerformed
flag=0;
Gen_Presupuesto.txtTipo.setText(String.valueOf(flag));
this.dispose();
    }//GEN-LAST:event_btnDepActionPerformed

    private void btnNODepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNODepActionPerformed
flag=1;
Gen_Presupuesto.txtTipo.setText(String.valueOf(flag));
this.dispose();
    }//GEN-LAST:event_btnNODepActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
Menu.menPrep.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void btnDepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDepKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)dispose();
    }//GEN-LAST:event_btnDepKeyPressed

    private void btnNODepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNODepKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ESCAPE)dispose();
    }//GEN-LAST:event_btnNODepKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDep;
    private javax.swing.JButton btnNODep;
    // End of variables declaration//GEN-END:variables
}

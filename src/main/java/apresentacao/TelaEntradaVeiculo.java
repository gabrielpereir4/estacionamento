/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apresentacao;

import controle.EstacionamentoController;
import controle.EstacionamentoException;
import controle.VeiculoException;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class TelaEntradaVeiculo extends javax.swing.JFrame implements ActionListener {
    private JFrame parent;
    
    /**
     * Creates new form TelaEntradaVeiculo
     */
    public TelaEntradaVeiculo(JFrame parent) {
        this.parent = parent;
        setSize(new Dimension(400, 312));
        initComponents();
        setLocationRelativeTo(null);
        btnOK.addActionListener(this);
        btnOK.setActionCommand("ok");
        btnCancelar.addActionListener(this);
        btnCancelar.setActionCommand("cancel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ok")){
            EstacionamentoController controle = new EstacionamentoController();
            try{
                controle.processarEntrada(txfPlaca.getText(),
                                          txtMarca.getText(), 
                                          txtModelo.getText(), 
                                          txtCor.getText());
                JOptionPane.showMessageDialog(null, "Veículo Registrado com Sucesso", "Entrada de Veículo", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (EstacionamentoException | VeiculoException error ) {
                JOptionPane.showMessageDialog(null, error.getMessage(), "Falha na Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
            this.parent.setVisible(true);
            this.dispose();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPlaca = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblCor = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txfPlaca = new javax.swing.JFormattedTextField();

        setTitle("Entrada Veículo");
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPlaca.setText("Placa:");
        jPanel1.add(lblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        lblMarca.setText("Marca:");
        jPanel1.add(lblMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        lblModelo.setText("Modelo:");
        jPanel1.add(lblModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        lblCor.setText("Cor:");
        jPanel1.add(lblCor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        txtMarca.setColumns(10);
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });
        jPanel1.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        txtModelo.setColumns(10);
        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });
        jPanel1.add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        txtCor.setColumns(10);
        jPanel1.add(txtCor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

        btnOK.setText("OK");
        jPanel1.add(btnOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 100, -1));

        btnCancelar.setText("Cancelar");
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 100, -1));

        txfPlaca.setColumns(10);
        try {
            txfPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU#A##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txfPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfPlacaActionPerformed(evt);
            }
        });
        jPanel1.add(txfPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void txfPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfPlacaActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCor;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JFormattedTextField txfPlaca;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    // End of variables declaration//GEN-END:variables
}

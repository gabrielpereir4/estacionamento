/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import negocio.Movimentacao;
import utilitario.EstacionamentoUtil;

/**
 *
 * @author gabri
 */
public class TelaResumoPagamento extends javax.swing.JFrame implements ActionListener {
    private JFrame parent;
    /**
     * Creates new form TelaResumoPagamento
     */
    public TelaResumoPagamento(Movimentacao movimentacao, JFrame parent) {
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
        
        lblValPlaca.setText(movimentacao.getVeiculo().getPlaca());
        lblValEntrada.setText(EstacionamentoUtil.getDisplayData(movimentacao.getEntrada()));
        lblValSaida.setText(EstacionamentoUtil.getDisplayData(movimentacao.getSaida()));
        String valor = "R$" + movimentacao.getValor();
        lblValValor.setText(valor);
        
        btnOK.addActionListener(this);
        btnOK.setActionCommand("ok");
        
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
        lblEntrada = new javax.swing.JLabel();
        lblSaida = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        lblValPlaca = new javax.swing.JLabel();
        lblValEntrada = new javax.swing.JLabel();
        lblValSaida = new javax.swing.JLabel();
        lblValValor = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Resumo de Pagamento");
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(400, 300));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPlaca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPlaca.setText("Placa:");
        jPanel1.add(lblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        lblEntrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblEntrada.setText("Entrada:");
        jPanel1.add(lblEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        lblSaida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSaida.setText("Saída:");
        jPanel1.add(lblSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        lblValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblValor.setText("Valor:");
        jPanel1.add(lblValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

        lblValPlaca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblValPlaca.setText("[placa]");
        jPanel1.add(lblValPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, -1, -1));

        lblValEntrada.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblValEntrada.setText("[entrada]");
        jPanel1.add(lblValEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        lblValSaida.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblValSaida.setText("[saida]");
        jPanel1.add(lblValSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        lblValValor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblValValor.setText("[valor]");
        jPanel1.add(lblValValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, -1));

        btnOK.setText("OK");
        jPanel1.add(btnOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

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



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEntrada;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblSaida;
    private javax.swing.JLabel lblValEntrada;
    private javax.swing.JLabel lblValPlaca;
    private javax.swing.JLabel lblValSaida;
    private javax.swing.JLabel lblValValor;
    private javax.swing.JLabel lblValor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.setVisible(true);
        this.dispose();
    }
}

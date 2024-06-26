/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package menu;
import javax.swing.JFrame;
import propiedades.Config;
/**
 *
 * @author Fernando Flores
 */
public class ConfiguracionDePartidaMultiPlayer extends javax.swing.JFrame {

    /**
     * Creates new form ConfiguracionDePartida
     */
    public ConfiguracionDePartidaMultiPlayer() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Comando para cerrar ventana cuando le das click a X.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ajustesPartida = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        nomJugador1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        nomJugador2 = new javax.swing.JLabel();
        jugar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 240, 201));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ajustesPartida.setFont(new java.awt.Font("Impact", 0, 19)); // NOI18N
        ajustesPartida.setForeground(new java.awt.Color(255, 255, 255));
        ajustesPartida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4 4.jpg"))); // NOI18N
        ajustesPartida.setText(Config.idioma.getProperty("valorAjustesPartida"));
        ajustesPartida.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(ajustesPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 70));

        jTextField1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jTextField1.setText("JordiPonmeUn10");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 110, -1));

        nomJugador1.setBackground(new java.awt.Color(51, 255, 204));
        nomJugador1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        nomJugador1.setForeground(new java.awt.Color(255, 255, 255));
        nomJugador1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4 2.jpg"))); // NOI18N
        nomJugador1.setText(Config.idioma.getProperty("valorNomJugador1"));
        nomJugador1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nomJugador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 50));

        jTextField2.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jTextField2.setText("PerePonmeUn10");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 110, -1));

        nomJugador2.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        nomJugador2.setForeground(new java.awt.Color(255, 255, 255));
        nomJugador2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4 2.jpg"))); // NOI18N
        nomJugador2.setText(Config.idioma.getProperty("valorNomJugador2"));
        nomJugador2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(nomJugador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 50));

        jugar.setFont(new java.awt.Font("Impact", 0, 16)); // NOI18N
        jugar.setForeground(new java.awt.Color(255, 255, 255));
        jugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4.jpg"))); // NOI18N
        jugar.setText("Jugar");
        jugar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });
        getContentPane().add(jugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 90, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/810f56ca-5930-4665-9ee1-a734667c9bef.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        //Guardamos los nombre de los jugadores
        Config.jugador1 = jTextField1.getText();
        Config.jugador2 = jTextField2.getText();
        Config.singlePlayer = false;
        Config.corazonesJ1 = 3;
        Config.corazonesJ2 = 3; // SE A AÑADIDO UNA NUEVA CONFIGURACION PARA CORAZONES J2.
        //Abrimos pantalla de las reglas
        ReglasPartida reglas = new ReglasPartida();
        reglas.setVisible(true);
        dispose();
    }//GEN-LAST:event_jugarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ajustesPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton jugar;
    private javax.swing.JLabel nomJugador1;
    private javax.swing.JLabel nomJugador2;
    // End of variables declaration//GEN-END:variables

}


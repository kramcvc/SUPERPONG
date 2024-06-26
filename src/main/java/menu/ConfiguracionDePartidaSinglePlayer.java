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
public class ConfiguracionDePartidaSinglePlayer extends javax.swing.JFrame {
    
private String nivel;
    public static ConfiguracionDePartidaSinglePlayer cSinglePlayer;

    /**
     * Creates new form ConfuguracionDePartidaSinglePlayer
     */
    public ConfiguracionDePartidaSinglePlayer() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Impact", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4 4.jpg"))); // NOI18N
        jLabel1.setText(Config.idioma.getProperty("valorAjustesPartida"));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 330, 80));

        jLabel2.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4 2.jpg"))); // NOI18N
        jLabel2.setText(Config.idioma.getProperty("valorNomJugador1"));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jTextField1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jTextField1.setText("Nombre");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 80, -1));

        jComboBox1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tortuga", "Liebre", "Mortal", "Imposible" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 80, -1));

        jLabel3.setFont(new java.awt.Font("Impact", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4 2.jpg"))); // NOI18N
        jLabel3.setText(Config.idioma.getProperty("valorNivelDificultad"));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 150, -1));

        jButton1.setFont(new java.awt.Font("Impact", 0, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OIG4.jpg"))); // NOI18N
        jButton1.setText("Jugar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 317, 90, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/810f56ca-5930-4665-9ee1-a734667c9bef.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Config.jugador1 = jTextField1.getText();
        Config.singlePlayer = true;
        System.out.println("Nombre: " + Config.jugador1);
        Config.corazonesJ1 = 3;
        Config.corazonesJ2 = -1; // SE AÑADIO LA CONFIGURACION DE CORAZONESJ2 A -1, PARA QUE EL JUEGO NO SE ACABE SI LA MAQUINA PIERDE
        ReglasPartida reglas = new ReglasPartida();
        reglas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
 if (jComboBox1.getSelectedItem() != null) {
        nivel = (jComboBox1.getSelectedItem().toString());
        }
        switch (nivel) {
            case "Tortuga":
                Config.nivelDificultad = 1;
                System.out.println("Has seleccionado la tortuga.");
                break;
            case "Liebre":
                Config.nivelDificultad = 2;
                System.out.println("Has seleccionado la liebre.");
                break;
            case "Mortal":
                Config.nivelDificultad = 3;
                System.out.println("Has seleccionado el mortal.");
                break;
            case "Imposible":
                Config.nivelDificultad = 4;
                System.out.println("Has seleccionado el imposible.");
                break;
            default:
                System.out.println("No se ha seleccionador el nivel correcto.");
                break;
        }
           
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

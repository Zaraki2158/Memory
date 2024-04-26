import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VisuPersonnagesDlg extends javax.swing.JDialog {
    private Joueur j;
    
    public VisuPersonnagesDlg(java.awt.Frame parent, boolean modal, Joueur jc) {
        super(parent, modal);
        initComponents();
        this.j = jc;
        initPanneau();
        Label1.setText("Carte du joueur : "+this.j.getPseudo());
        LScore.setText("Score : "+this.j.getScore());
    }
 
    public void initPanneau(){
        LesPersonnages lp = this.j.getPaquet();
        Panneau.setLayout(new GridLayout(lp.getTaille()-1, lp.getTaille()+1));
        for(int i=0; i<lp.getTaille(); i++){
            JButton jb = new JButton();
            Panneau.add(jb);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Label1 = new javax.swing.JLabel();
        Panneau = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        LScore = new javax.swing.JLabel();
        Afficher = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().add(Label1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout PanneauLayout = new javax.swing.GroupLayout(Panneau);
        Panneau.setLayout(PanneauLayout);
        PanneauLayout.setHorizontalGroup(
            PanneauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PanneauLayout.setVerticalGroup(
            PanneauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        getContentPane().add(Panneau, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(2, 1));
        jPanel2.add(LScore);

        Afficher.setText("Afficher");
        Afficher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AfficherActionPerformed(evt);
            }
        });
        jPanel2.add(Afficher);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AfficherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AfficherActionPerformed
        // Gestionnaire du bouton Afficher
        LesPersonnages lp = this.j.getPaquet();
        for(int i=0; i<lp.getTaille();i++){
            JButton jb = (JButton) Panneau.getComponent(i);     //Récupération du bouton puis ajout de l'image d'une carte
            /**
            Image img = lp.getPerso(i).getPhoto();
            Image imgScale = img.getScaledInstance(jb.getWidth(), jb.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(imgScale);
            jb.setIcon(scaledIcon);**/
            lp.getPerso(i).setImgBouton(jb);
        }
    }//GEN-LAST:event_AfficherActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VisuPersonnagesDlg dialog = new VisuPersonnagesDlg(new javax.swing.JFrame(), true, new Joueur());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Afficher;
    private javax.swing.JLabel LScore;
    private javax.swing.JLabel Label1;
    private javax.swing.JPanel Panneau;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}

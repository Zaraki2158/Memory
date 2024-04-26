import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VisuJoueursDlg extends javax.swing.JDialog {
    private LesJoueurs lj;
     
    public VisuJoueursDlg(java.awt.Frame parent, boolean modal, LesJoueurs tktt) {
        super(parent, modal);
        initComponents();
        this.lj=tktt;
        initTrombi();
    }

    private void initTrombi(){
        //Constructeur du panneau
        int nb = this.lj.getNbJoueurs();
        Panneau.setLayout(new GridLayout(1,nb));
        for (int i=0; i< nb; i++)
            { JButton jb= new JButton();
            Joueur j= lj.getJoueur(i);
            jb.setName(""+i);
            jb.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    AfficheInfosJoueur(evt);
                }
            });
            Panneau.add(jb);
            }
        this.pack();// pour ajuster correctement la taille des composants après les ajouts si besoin    
    }
    
    private void AfficheInfosJoueur(java.awt.event.ActionEvent evt){
        //Gestionnaire du clic sur les boutons dans le panneau
        Edition.setText("");
        JButton jb = (JButton) evt.getSource();
        int i = Integer.parseInt(jb.getName());
        String s = this.lj.getJoueur(i).toString();
        Edition.append(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Panneau = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Edition = new javax.swing.JTextArea();
        BAffiche = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choisissez un joueur pour voir ses  caracteristiques");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Panneau.setMinimumSize(new java.awt.Dimension(600, 250));
        Panneau.setPreferredSize(new java.awt.Dimension(600, 250));

        javax.swing.GroupLayout PanneauLayout = new javax.swing.GroupLayout(Panneau);
        Panneau.setLayout(PanneauLayout);
        PanneauLayout.setHorizontalGroup(
            PanneauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        PanneauLayout.setVerticalGroup(
            PanneauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jPanel1.add(Panneau);

        Edition.setColumns(20);
        Edition.setRows(5);
        Edition.setMinimumSize(new java.awt.Dimension(50, 80));
        jScrollPane2.setViewportView(Edition);

        jPanel1.add(jScrollPane2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        BAffiche.setText("Afficher");
        BAffiche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAfficheActionPerformed(evt);
            }
        });
        getContentPane().add(BAffiche, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BAfficheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAfficheActionPerformed
        // Gestionnaire du bouton Afficher
        for(int i=0; i<this.lj.getNbJoueurs();i++){
            JButton jc = (JButton) Panneau.getComponent(i);         //Récupère les boutons du Panneau
            jc.setIcon(this.lj.getJoueur(i).getPhoto());            //Affiche les images des joueurs sur les boutons
        }
    }//GEN-LAST:event_BAfficheActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VisuJoueursDlg dialog = new VisuJoueursDlg(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton BAffiche;
    private javax.swing.JTextArea Edition;
    private javax.swing.JPanel Panneau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}

import javax.swing.ImageIcon;

public class OptionDlg extends javax.swing.JDialog {
    private LesJoueurs mesJ;
    private LesJoueurs lj;
    private int niveaeu;
    private boolean Ok;

    public OptionDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.mesJ = new LesJoueurs();
        initJoueurs();
        this.niveaeu = 4;
        this.Ok = false;
        BRenfant.setSelected(true);
    }

    public LesJoueurs getLJ(){ return this.lj; }   
    public int getNiveau(){ return this.niveaeu; }  
    public boolean getOk(){ return this.Ok; }

    private void initJoueurs(){
        Joueur j = new Joueur("lara", "epiques");
        j.setPhoto(new ImageIcon(getClass().getResource("/img/lara.jpg")));
        this.mesJ.ajouteJoueur(j);
        j=new Joueur("Jack", "rares");
        j.setPhoto(new ImageIcon(getClass().getResource("/img/jack.png"))); 
        this.mesJ.ajouteJoueur(j);
        j=new Joueur("Jean-Sébastien", "alpins-femmes");
        j.setPhoto(new ImageIcon(getClass().getResource("/img/bach.jpg"))); 
        this.mesJ.ajouteJoueur(j);
        j=new Joueur("Amadeus", "communs");
        j.setPhoto(new ImageIcon(getClass().getResource("/img/mozart.jpg"))); 
        this.mesJ.ajouteJoueur(j);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Taillejeu = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        BRenfant = new javax.swing.JRadioButton();
        BRdebutant = new javax.swing.JRadioButton();
        BRavance = new javax.swing.JRadioButton();
        BRexpert = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        CBLara = new javax.swing.JCheckBox();
        CBJack = new javax.swing.JCheckBox();
        CBJS = new javax.swing.JCheckBox();
        CBAmadeus = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Edition = new javax.swing.JTextArea();
        BPhoto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Bannuler = new javax.swing.JButton();
        Bvalider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Choisissez la taille du jeu");
        jPanel3.add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel6.setLayout(new java.awt.GridLayout(4, 1));

        Taillejeu.add(BRenfant);
        BRenfant.setText("Enfant ( 4 personnages, 2 familles)");
        BRenfant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRenfantActionPerformed(evt);
            }
        });
        jPanel6.add(BRenfant);

        Taillejeu.add(BRdebutant);
        BRdebutant.setText("Débutant ( 10 personnages, 2 familles)");
        BRdebutant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRdebutantActionPerformed(evt);
            }
        });
        jPanel6.add(BRdebutant);

        Taillejeu.add(BRavance);
        BRavance.setText("Avancé ( 18 personnages, 4 familles)");
        BRavance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRavanceActionPerformed(evt);
            }
        });
        jPanel6.add(BRavance);

        Taillejeu.add(BRexpert);
        BRexpert.setText("Expert ( 32 personnages, 6 familles)");
        BRexpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRexpertActionPerformed(evt);
            }
        });
        jPanel6.add(BRexpert);

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Choisissez les joueurs créés par défaut");
        jPanel4.add(jLabel2, java.awt.BorderLayout.NORTH);

        jPanel7.setLayout(new java.awt.GridLayout(4, 1));

        CBLara.setText("Lara");
        CBLara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBLaraMouseClicked(evt);
            }
        });
        jPanel7.add(CBLara);

        CBJack.setText("Jack");
        CBJack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBJackMouseClicked(evt);
            }
        });
        jPanel7.add(CBJack);

        CBJS.setText("Jean-Sébastien");
        CBJS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBJSMouseClicked(evt);
            }
        });
        jPanel7.add(CBJS);

        CBAmadeus.setText("Amadeus");
        CBAmadeus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBAmadeusMouseClicked(evt);
            }
        });
        jPanel7.add(CBAmadeus);

        jPanel4.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jPanel5.setLayout(new java.awt.GridLayout(2, 1));

        Edition.setColumns(20);
        Edition.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Edition.setRows(5);
        Edition.setMinimumSize(new java.awt.Dimension(400, 300));
        Edition.setPreferredSize(new java.awt.Dimension(400, 300));
        jScrollPane1.setViewportView(Edition);

        jPanel5.add(jScrollPane1);
        jPanel5.add(BPhoto);

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        Bannuler.setText("Annuler");
        Bannuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BannulerActionPerformed(evt);
            }
        });
        jPanel2.add(Bannuler);

        Bvalider.setText("Valider");
        Bvalider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BvaliderActionPerformed(evt);
            }
        });
        jPanel2.add(Bvalider);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBLaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBLaraMouseClicked
        Edition.setText(mesJ.getJoueur(0).toString());
        BPhoto.setIcon(mesJ.getJoueur(0).getPhoto());
    }//GEN-LAST:event_CBLaraMouseClicked

    private void CBJackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBJackMouseClicked
        Edition.setText(mesJ.getJoueur(1).toString());
        BPhoto.setIcon(mesJ.getJoueur(1).getPhoto());
    }//GEN-LAST:event_CBJackMouseClicked

    private void CBJSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBJSMouseClicked
        Edition.setText(mesJ.getJoueur(2).toString());
        BPhoto.setIcon(mesJ.getJoueur(2).getPhoto());
    }//GEN-LAST:event_CBJSMouseClicked

    private void CBAmadeusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBAmadeusMouseClicked
        Edition.setText(mesJ.getJoueur(3).toString());
        BPhoto.setIcon(mesJ.getJoueur(3).getPhoto());
    }//GEN-LAST:event_CBAmadeusMouseClicked

    private void BvaliderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BvaliderActionPerformed
        this.lj = new LesJoueurs();      
        if (CBJack.isSelected())
            lj.ajouteJoueur(mesJ.getJoueur(1));        
        if (CBJS.isSelected())
            lj.ajouteJoueur(mesJ.getJoueur(2));     
        if (CBAmadeus.isSelected())
            lj.ajouteJoueur(mesJ.getJoueur(3));      
        if(CBLara.isSelected())
            lj.ajouteJoueur(mesJ.getJoueur(0));       
        this.Ok = true;
        this.setVisible(false);
        dispose();
       
    }//GEN-LAST:event_BvaliderActionPerformed

    private void BRenfantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRenfantActionPerformed
        // Gestionnaire du niveau du joueur
        this.niveaeu = 4;
    }//GEN-LAST:event_BRenfantActionPerformed

    private void BRdebutantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRdebutantActionPerformed
        // Gestionnaire du niveau du joueur
        this.niveaeu = 10;
    }//GEN-LAST:event_BRdebutantActionPerformed

    private void BannulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BannulerActionPerformed
        // Gestionnaire du bouton Annuler
        this.Ok = false;
        this.setVisible(false);
    }//GEN-LAST:event_BannulerActionPerformed

    private void BRavanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRavanceActionPerformed
        // Gestionnaire du niveau du joueur
        this.niveaeu = 18;
    }//GEN-LAST:event_BRavanceActionPerformed

    private void BRexpertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRexpertActionPerformed
        // Gestionnaire du niveau du joueur
        this.niveaeu = 32;
    }//GEN-LAST:event_BRexpertActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OptionDlg dialog = new OptionDlg(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BPhoto;
    private javax.swing.JRadioButton BRavance;
    private javax.swing.JRadioButton BRdebutant;
    private javax.swing.JRadioButton BRenfant;
    private javax.swing.JRadioButton BRexpert;
    private javax.swing.JButton Bannuler;
    private javax.swing.JButton Bvalider;
    private javax.swing.JCheckBox CBAmadeus;
    private javax.swing.JCheckBox CBJS;
    private javax.swing.JCheckBox CBJack;
    private javax.swing.JCheckBox CBLara;
    private javax.swing.JTextArea Edition;
    private javax.swing.ButtonGroup Taillejeu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

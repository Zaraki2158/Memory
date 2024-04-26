import javax.swing.DefaultListModel;

public class BatailleDlg extends javax.swing.JDialog {
    private LesJoueurs lj;  //pour remplir la JList, avec les pseudos
    private int indj;   //indice du joueur courant
    private Joueur adversaire;  //le pseudo sélectionnée dans la JList
    private boolean ok; // pour fermer la fenetre
    private Bataille b; // pour valider la bataille
    
    public BatailleDlg(java.awt.Frame parent, boolean modal, LesJoueurs lj, int indjc) {
        super(parent, modal);
        initComponents();
        this.lj = lj;
        this.indj = indjc;
        this.adversaire = null;
        this.ok = false;
        this.b = null;
        Annuler.setText("Annuler");
        Demarrer.setEnabled(false);
        Annuler.setVisible(false);
        MessageJ.setText(this.lj.getJoueur(this.indj).getPseudo()+" va effectuer une Bataille contre ");
        initJList(); //appel de la méthode pour remplir la JList
    }

    public boolean getOk(){ return ok; }
    public Joueur getAdversaire(){ return adversaire;  }  
    public Bataille getBataille(){ return b; }
    public String getDeroulement(){ return b.getDeroulement(); }

    private void initJList(){
        //met les pseudos de tout les joeurs dans la JList
        DefaultListModel mod = new DefaultListModel();
        ListJ. setModel(mod);
        for(int i=0; i< this.lj.getNbJoueurs(); i++){
            mod.addElement(this.lj.getJoueur(i).getPseudo());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        MessageJ = new javax.swing.JLabel();
        Joueurs = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        InfosCarte1 = new javax.swing.JTextArea();
        Carte1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ListJ = new javax.swing.JList<>();
        Adversaire = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Carte2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        InfosCarte2 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        Vainqueur = new javax.swing.JLabel();
        Annuler = new javax.swing.JButton();
        Demarrer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setLayout(new java.awt.GridLayout(2, 1));

        jPanel7.setLayout(new java.awt.BorderLayout());
        jPanel7.add(MessageJ, java.awt.BorderLayout.CENTER);
        jPanel7.add(Joueurs, java.awt.BorderLayout.SOUTH);

        jPanel3.add(jPanel7);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        InfosCarte1.setColumns(20);
        InfosCarte1.setRows(5);
        jScrollPane2.setViewportView(InfosCarte1);

        jPanel5.add(jScrollPane2);
        jPanel5.add(Carte1);

        jPanel3.add(jPanel5);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(2, 1));

        jPanel8.setLayout(new java.awt.BorderLayout());

        ListJ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListJMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(ListJ);

        jPanel8.add(jScrollPane4, java.awt.BorderLayout.CENTER);
        jPanel8.add(Adversaire, java.awt.BorderLayout.SOUTH);

        jPanel4.add(jPanel8);

        jPanel6.setLayout(new java.awt.GridLayout(1, 2));
        jPanel6.add(Carte2);

        InfosCarte2.setColumns(20);
        InfosCarte2.setRows(5);
        jScrollPane3.setViewportView(InfosCarte2);

        jPanel6.add(jScrollPane3);

        jPanel4.add(jPanel6);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridLayout(1, 3));
        jPanel2.add(Vainqueur);

        Annuler.setText("Annuler / Fermer");
        Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerActionPerformed(evt);
            }
        });
        jPanel2.add(Annuler);

        Demarrer.setText("Démarrer");
        Demarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemarrerActionPerformed(evt);
            }
        });
        jPanel2.add(Demarrer);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemarrerActionPerformed
        /**gestionnaire du clic sur le bouton "Demarrer", qui réalise "un tour"
        de la bataille ==> en utilisant la méthode execute() de la classe Bataille**/
        Joueur jo = this.lj.getJoueur(this.indj);
        InfosCarte1.append(jo.getPaquet().getPerso(0).toString());
        InfosCarte2.append(this.adversaire.getPaquet().getPerso(0).toString());
        //on execute un tour de bataille ==> retourne 0, 1, 2
        int res = this.b.execute();
        switch(res){
            case 1 : {
                InfosCarte1.append(jo.getPseudo()+" gagne la carte \n");
                InfosCarte2.append(this.adversaire.getPseudo()+" perd la carte \n");
            }
            break;
            
            case 2 : {
                InfosCarte1.append(jo.getPseudo()+" perd la carte \n");
                InfosCarte2.append(this.adversaire.getPseudo()+" gagne la carte \n");
            }
            break;
            
            case 0 : {
                InfosCarte1.append(" égalité \n");
                InfosCarte2.append(" égalité \n");
            }
            break;
        }
        //test si fin de partie de la Bataille
        if(jo.getPaquet().getTaille() == 0 && this.adversaire.getPaquet().getTaille() >0){
            //le joueur perd
            Vainqueur.setText(this.adversaire.getPseudo()+" a gagné la partie");
            Demarrer.setEnabled(false); //pour griser le composant (non cliquable)
            Annuler.setText("Fermer");
            this.ok = true;
        }
        else if (jo.getPaquet().getTaille() > 0 && this.adversaire.getPaquet().getTaille() == 0){
            //le joueur gagne
            Vainqueur.setText(jo.getPseudo()+" a gagné la partie");
            Demarrer.setEnabled(false); //pour griser le composant (non cliquable)
            Annuler.setText("Fermer");
            this.ok = true;
        }
        else{
            //les deux joueurs ont des cartes, on les affiches
            jo.getPaquet().getPerso(0).setImgBouton(Carte1);
            this.adversaire.getPaquet().getPerso(0).setImgBouton(Carte2);          
        }
    }//GEN-LAST:event_DemarrerActionPerformed

    private void ListJMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListJMouseClicked
        // Gestionnaire du clic dans la liste ListJ
        String psd = ListJ.getSelectedValue();
        if(psd.equals(this.lj.getJoueur(this.indj).getPseudo())){
            //Le joueur a sélectionné son propre pseudo dans la liste
            MessageJ.setText("Erreur : un joueur ne peut pas faire une bataille contre lui même !");
            this.adversaire = null;
            this.b = null;
            Joueurs.setText(this.lj.getJoueur(indj).getPseudo());
            Adversaire.setText("");
            InfosCarte1.setText(this.lj.getJoueur(indj).getPaquet().toString());
            InfosCarte2.setText("");
            Demarrer.setEnabled(false);
            Annuler.setVisible(false);
        }
            
        else{
            int inda = ListJ.getSelectedIndex(); // récupère l'indice de l'adversaire sélectionné
            if(this.lj.getJoueur(inda).getPaquet().getTaille() >0){
                //Vérifie si l'adversaire possede des cartes
                this.adversaire = lj.getJoueur(inda);
                this.b = new Bataille(this.lj.getJoueur(indj), this.adversaire);
                Demarrer.setEnabled(true);
                Annuler.setVisible(true);
                Joueurs.setText(this.lj.getJoueur(indj).getPseudo());
                Adversaire.setText(this.adversaire.getPseudo());
                InfosCarte1.setText(this.lj.getJoueur(indj).getPaquet().toString());
                InfosCarte2.setText(this.adversaire.getPaquet().toString());
            }
            else{
                //Si l'adversaire n'a pas de carte on demande au joueur d'en choisir un autre
                MessageJ.setText("Cet adversaire ne possède pas de carte veuillez en choisir un autre !");
                this.adversaire = null;
                this.b = null;
                Joueurs.setText(this.lj.getJoueur(indj).getPseudo());
                Adversaire.setText(this.lj.getJoueur(inda).getPseudo());
                InfosCarte1.setText(this.lj.getJoueur(indj).getPaquet().toString());
                InfosCarte2.setText(this.lj.getJoueur(inda).getPaquet().toString());
                Demarrer.setEnabled(false);
                Annuler.setVisible(false);
            }
        }
    }//GEN-LAST:event_ListJMouseClicked

    private void AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerActionPerformed
        //Gestionnaire du bouton Annuler en fonction de son  texte (Fermer ou Annuler)
        this.ok=false;
        if (Annuler.getText().equals("Annuler"))
            this.b.setDeroulement("Bataille interrompue en cours de partie");
        this.setVisible(false);
        this.dispose();

    }//GEN-LAST:event_AnnulerActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BatailleDlg dialog = new BatailleDlg(new javax.swing.JFrame(), true, new LesJoueurs(), 0);
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
    private javax.swing.JLabel Adversaire;
    private javax.swing.JButton Annuler;
    private javax.swing.JButton Carte1;
    private javax.swing.JButton Carte2;
    private javax.swing.JButton Demarrer;
    private javax.swing.JTextArea InfosCarte1;
    private javax.swing.JTextArea InfosCarte2;
    private javax.swing.JLabel Joueurs;
    private javax.swing.JList<String> ListJ;
    private javax.swing.JLabel MessageJ;
    private javax.swing.JLabel Vainqueur;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}

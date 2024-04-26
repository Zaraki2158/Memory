import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class JeuMemory extends javax.swing.JFrame {
    private LesPersonnages persos ; // Liste des personnages présent sur le plateau
    private LesJoueurs joueurs ; // Liste des joueurs de la partie
    private Jeu jeu; // s'occupe du déroulement de la partie et il lie le jeu au plateau
    private int l1, c1, l2, c2; // indices des lignes et des colonnes des cartes sélectionnées

    public JeuMemory() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.joueurs = new LesJoueurs(); // crée une instance vide du type LesJoueurs
        this.persos = new LesPersonnages(4); // crée une liste de 4 personnages
        this.jeu = new Jeu(this.persos, this.joueurs, 4); 
        this.l1 = -1;
        this.c1 = -1;
        this.l2 = -1;
        this.c2 = -1;
        Recommencer.setEnabled(false);
        Demarrer.setEnabled(true);
        Cartes.setEnabled(false);
        initPlateau();// pré-affichage du plateau
    }
    
    public PlateauJeu getPlateau(){
        return this.jeu.getPlateau();
    }

    public void initPlateau(){//initialise le plateau selon le niveau par défaut ici 4
        Panneau.removeAll();
        PlateauJeu PJ = getPlateau();
        Panneau.setLayout(new java.awt.GridLayout(PJ.getNblig(), PJ.getNbcol()));
        for(int i=0; i<PJ.getNblig();i++){
            for(int j=0; j<PJ.getNbcol();j++){
                JButton jb = new JButton();
                Panneau.add(jb);
            }
        }
    }
    
    private void boutonActionPerformed(java.awt.event.ActionEvent evt){
        JButton bt = (JButton) evt.getSource();
        String nm = bt.getName();
        int ligne = Integer.parseInt(nm)/this.getPlateau().getNbcol();
        int colonne = Integer.parseInt(nm)-(ligne*this.getPlateau().getNbcol());
        int val = getPlateau().getCase(ligne, colonne);
        this.persos.getPerso(val).setImgBouton(bt);
        if(this.l1 == -1 && this.c1 == -1){// affiche les actions des joueurs dans la zone d'edition
            Edition.setText("Carte 1 sélectionnées :\n"+this.persos.getPerso(val).toString());
            this.l1 = ligne;
            this.c1 = colonne;
        }
        else if((this.l2 == -1 && this.c2 == -1) && (this.l1 != ligne || this.c1 != colonne)){// on verifie si le joueur n'a pas clique deux fois sur la meme carte
            Edition.append("Carte 2 sélectionnées :\n"+this.persos.getPerso(val).toString());
            this.l2 = ligne;
            this.c2 = colonne;
            startTimer();
        }
    }
    
    public void startTimer(){//delai de verification entre deux cartes ici 500ms
        Timer t = new Timer(500, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                verifPersos();
            }
        });
        t.setRepeats(false);
        t.start();
    }

    public void verifPersos(){ // permet de verifie la valeur de deux cartes de personnages selectionnees
        int val1 = getPlateau().getCase(l1, c1);
        int val2 = getPlateau().getCase(l2, c2);
        int ind = this.jeu.getIndj(); // indice du joueur courant
        if(val1 == val2){
            String fam = this.persos.getPerso(val1).getFamille();
            int bonus = this.jeu.TraiterTour(this.jeu.getJoueurCourant(), val1);
            if(bonus>=0){
                Edition.setText("Le joueur "+this.jeu.getJoueurCourant().getPseudo()+" a gagné tous les personnages de la famille "+fam);
                switch(bonus){
                    case 0 :{ // le joueur courant posséde toutes les cartes de sa famille préférée, la partie s'arrete il a gagné
                        Edition.setText("FIN DE PARTIE !!! Bravo \n"+this.jeu.getJoueurCourant().getPseudo()+" a gagné la partie !");
                        int k=0;
                        for(int i=0; i<getPlateau().getNblig(); i++){
                            for(int j=0; j<getPlateau().getNbcol(); j++){
                                JButton b = (JButton) Panneau.getComponent(k);
                                b.setEnabled(false);
                                k++;
                            }
                        }
                    }break;
                    case 1 :{ // Le joueur a trouvé tous les personnage de la famille "rares" ou "communs"
                        TransfertDlg transf = new TransfertDlg(this, true, this.joueurs, this.jeu.getIndj());
                        transf.setVisible(true);
                        if(transf.getOk() == true)//cette boucle permet de vérifie si le transfert a été effectuée
                            Edition.append("\n"+ transf.getDeroulement());
                    }break;
                    case 2 :{
                        BatailleDlg bat = new BatailleDlg(this, true, this.joueurs, this.jeu.getIndj());
                        bat.setVisible(true);
                        if(bat.getOk() == true)
                            //cette boucle permet de vérifie si la bataille a été effectuée
                            Edition.append("\n"+ bat.getDeroulement());
                    }break;
                }
                this.jeu.setJoueurCourant(this.jeu.getIndSuivant(ind)); //modification du joueur courant, on passe le joueur suivant en joueur courant.
            }
            getPlateau().invalide(l1, c1, l2, c2);
            if(getPlateau().jeuVide() && bonus!=0){// verifie si le plateau est vide
                String s = "FIN DE PARTIE ! \n";
                s+="Les Gagnants sont : \n"+this.joueurs.getGagnants().toString();
                Edition.setText(s);
            }
            else{
                //Edition.append("C'est au tour de "+this.jeu.getJoueurSuivant(ind).getPseudo()+" de joueur !\n");
            }
            int nc = jeu.getPlateau().getNbc(); // nombres de cartes de personnages
            NbPersosT.setText("Nombre de personnages trouvés : "+(nc - jeu.getPlateau().getNbp()));
            NbPersosR.setText("Nombre de personnages restant : "+ jeu.getPlateau().getNbp());
            JC.setText("C'est à "+jeu.getJoueurCourant().getPseudo()+" de jouer");
            int v1 = (l1*getPlateau().getNbcol())+c1;
            int v2 = (l2*getPlateau().getNbcol())+c2;
            JButton bt1 = (JButton) Panneau.getComponent(v1);
            JButton bt2 = (JButton) Panneau.getComponent(v2);
            bt1.setEnabled(false);
            bt2.setEnabled(false);// les paires de cartes trouvees sont grisees
        }
        else{
            int v1 = (l1*getPlateau().getNbcol())+c1;
            int v2 = (l2*getPlateau().getNbcol())+c2;
            JButton b1 = (JButton) Panneau.getComponent(v1);
            JButton b2 = (JButton) Panneau.getComponent(v2);
            b1.setIcon(null);
            b2.setIcon(null);
            this.jeu.setJoueurCourant(this.jeu.getIndSuivant(ind));
            JC.setText("C'est à "+jeu.getJoueurCourant().getPseudo()+" de jouer");
        }
        this.l1 = -1;// les cartes sont remises a leurs indices de depart
        this.c1 = -1;
        this.l2 = -1;
        this.c2 = -1;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Edition = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        NbPersosT = new javax.swing.JLabel();
        NbPersosR = new javax.swing.JLabel();
        JC = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Demarrer = new javax.swing.JButton();
        Recommencer = new javax.swing.JButton();
        Panneau = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Option = new javax.swing.JMenuItem();
        AjoutJoueur = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Joueur = new javax.swing.JMenuItem();
        Cartes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        Edition.setColumns(20);
        Edition.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
        Edition.setRows(5);
        jScrollPane1.setViewportView(Edition);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new java.awt.GridLayout(3, 0));

        NbPersosT.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        NbPersosT.setText("Nombre de personnages trouvés");
        jPanel5.add(NbPersosT);

        NbPersosR.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        NbPersosR.setText("Nombre de personnages restant");
        jPanel5.add(NbPersosR);

        JC.setFont(new java.awt.Font("Cantarell", 0, 19)); // NOI18N
        JC.setText("C'est à ??? de jouer !");
        jPanel5.add(JC);

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        Demarrer.setText("Démarrer");
        Demarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemarrerActionPerformed(evt);
            }
        });
        jPanel6.add(Demarrer);

        Recommencer.setText("Recommancer");
        Recommencer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecommencerActionPerformed(evt);
            }
        });
        jPanel6.add(Recommencer);

        jPanel3.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        Panneau.setLayout(new java.awt.GridLayout(4, 5));
        getContentPane().add(Panneau, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Paramètres");

        Option.setText("Option");
        Option.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptionActionPerformed(evt);
            }
        });
        jMenu1.add(Option);

        AjoutJoueur.setText("Ajout Joueur");
        AjoutJoueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutJoueurActionPerformed(evt);
            }
        });
        jMenu1.add(AjoutJoueur);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Visualiser");

        Joueur.setText("Joueur");
        Joueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JoueurActionPerformed(evt);
            }
        });
        jMenu2.add(Joueur);

        Cartes.setText("Cartes");
        Cartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CartesActionPerformed(evt);
            }
        });
        jMenu2.add(Cartes);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptionActionPerformed
        // Gestionnaire du clic du menu option
        OptionDlg diag = new OptionDlg(this, true);
        diag.setVisible(true);
        String t="";
        if(diag.getOk()== true){
            this.persos = new LesPersonnages(diag.getNiveau());
            this.joueurs.ajouteJoueurs(diag.getLJ());
            this.jeu = new Jeu(this.persos, this.joueurs, diag.getNiveau());
            t+= "Nombre de personnages restant : "+diag.getNiveau();
            NbPersosR.setText(t);
            NbPersosT.setText("Nombre de personnages trouvés : "+0);
        }
        initPlateau();
    }//GEN-LAST:event_OptionActionPerformed

    private void JoueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoueurActionPerformed
        // Gestionnaire du clic du menu Joueur
        VisuJoueursDlg v = new VisuJoueursDlg(this, true, this.joueurs);
        v.setVisible(true);
    }//GEN-LAST:event_JoueurActionPerformed

    private void AjoutJoueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutJoueurActionPerformed
        // Gestionnaire du clic du menu Ajout Joueur
        SaisieJoueurDlg sjd = new SaisieJoueurDlg(this, true, this.persos);
        sjd.setVisible(true);
        if(sjd.getOk()){
            this.joueurs.ajouteJoueur(sjd.getJoueur());
        }
    }//GEN-LAST:event_AjoutJoueurActionPerformed

    private void CartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CartesActionPerformed
        // Gestionnaire du clic du menu Carte
        VisuPersonnagesDlg vpd = new VisuPersonnagesDlg(this, rootPaneCheckingEnabled, this.jeu.getJoueurCourant());
        vpd.setVisible(true);
    }//GEN-LAST:event_CartesActionPerformed

    private void DemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemarrerActionPerformed
        // Gestionnaire du bouton Démarrer
        if(this.joueurs.getNbJoueurs() <= 1)
            Edition.setText("Selectionner au moins 2 joueurs");
        else{
            Demarrer.setEnabled(false);
            Recommencer.setEnabled(true);
            Option.setEnabled(false);
            AjoutJoueur.setEnabled(false);
            Joueur.setEnabled(true);
            Cartes.setEnabled(true);
            int nc = jeu.getPlateau().getNbc(); // nombres de cartes de personnages
            NbPersosT.setText("Nombre de personnages trouvés : "+(nc - jeu.getPlateau().getNbp()));
            NbPersosR.setText("Nombre de personnages restant : "+ jeu.getPlateau().getNbp());
            JC.setText("C'est à "+jeu.getJoueurCourant().getPseudo()+" de jouer");
            int l = jeu.getPlateau().getNblig();
            int c = jeu.getPlateau().getNbcol();
            Panneau.removeAll(); // on supprime les boutons de départs qui servaient uniquement pour l'affichage
            Panneau.setLayout(new GridLayout(l,c));
            for(int i=0; i<2*nc; i++){
                JButton bt = new JButton();
                bt.setName(""+i);
                
                
                //█ le code si dessous permet de rendre visible le nom et la categorie des personnages sur les boutons pour faire les differents test
                String nm = bt.getName();
                int ligne = Integer.parseInt(nm)/this.getPlateau().getNbcol();
                int colonne = Integer.parseInt(nm)-(ligne*this.getPlateau().getNbcol());
                int val = getPlateau().getCase(ligne, colonne);
                bt.setText(this.persos.getPerso(val).getFamille()+" && "+this.persos.getPerso(val).getNom());
                 //█ fins du code pour rendre visible de nom et la categorie des personnages 
               
                bt.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent evt){
                        boutonActionPerformed(evt);
                    }
                });
                Panneau.add(bt);
            }
        }
    }//GEN-LAST:event_DemarrerActionPerformed

    private void RecommencerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecommencerActionPerformed
        // Gestionnaire du bouton Recommencer
        this.joueurs = new LesJoueurs(); // crée une instance vide du type LesJoueurs
        this.persos = new LesPersonnages(4); // crée une liste de 4 personnages
        this.jeu = new Jeu(this.persos, this.joueurs, 4); // voir constructeur classe Jeu
        this.l1 = -1;
        this.c1 = -1;
        this.l2 = -1;
        this.c2 = -1;
        initPlateau();// cette méthode ne permet qu'un simple affichage
        Recommencer.setEnabled(false);
        Demarrer.setEnabled(true);
        Option.setEnabled(true);
        AjoutJoueur.setEnabled(true);
        Joueur.setEnabled(true);
        Cartes.setEnabled(false);
        Edition.setText("");
        JC.setText("Vous avez Recommancé une nouvelle partie");
        NbPersosR.setText("Nombre de personnages restant");
        NbPersosT.setText("Nombre de personnages trouvés");
    }//GEN-LAST:event_RecommencerActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JeuMemory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AjoutJoueur;
    private javax.swing.JMenuItem Cartes;
    private javax.swing.JButton Demarrer;
    private javax.swing.JTextArea Edition;
    private javax.swing.JLabel JC;
    private javax.swing.JMenuItem Joueur;
    private javax.swing.JLabel NbPersosR;
    private javax.swing.JLabel NbPersosT;
    private javax.swing.JMenuItem Option;
    private javax.swing.JPanel Panneau;
    private javax.swing.JButton Recommencer;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

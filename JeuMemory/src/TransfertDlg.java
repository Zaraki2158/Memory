import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TransfertDlg extends javax.swing.JDialog {
    private LesJoueurs lj; //collection des joueurs, pour initialiser la liste déroulante avec les pseudos des joueurs
    private int indj; //indice joueur courant
    private Transfert tc; // cette classe sera étudiée ultérieurement – laisser l’attribut en commentaire
    private boolean ok; // indicateur pour savoir le transfert a bien été effectué.
    private int indjs; //indice du joueur sélectionné dans la liste déroulante
    private String fs; //famille du personnage sélectionné en cliquant sur un des personnages du joueur sélectionné

    public TransfertDlg(java.awt.Frame parent, boolean modal, LesJoueurs lj, int indj) {
        super(parent, modal);
        initComponents();
        this.setSize(this.getMaximumSize());
        this.lj = lj;
        this.indj = indj ;
        this.tc=null;
        this.ok=false;
        this.fs=null;
        initCombo(); // méthode pour remplir la liste déroulante
        indjs = 0;
        Message.setText("Le joueur "+lj.getJoueur(indj).getPseudo()+" a obtenu une famille complète");
        Message2.setText(""+lj.getJoueur(indj).getPseudo()+" sélectionnez un joueur dans la liste déroulante");
        Message3.setText("Le joueur sélectionner doit posséder des cartes");
        Infos.setText("Personnages de "+lj.getJoueur(indj).getPseudo()+" : \n"+lj.getJoueur(indj).getPaquet());
    }
 
    public boolean getOk(){ return this.ok; } 
    public Transfert getTransfert(){ return tc;}   
    public String getDeroulement(){  return this.tc.getDeroulement(); }

    public void initCombo(){
        for(int i=0; i<this.lj.getNbJoueurs(); i++){
            ComboJoueurs.addItem(this.lj.getJoueur(i).getPseudo());
        }
    }
    
    public void initPanneau(){
        PanneauG.removeAll();
        this.repaint();
        LesPersonnages lcs = this.lj.getJoueur(indjs).getPaquet();
        int t = lcs.getTaille();
        int n = 1+(t-1)/4;
        PanneauG.setLayout(new java.awt.GridLayout(4, n));
        for (int i=0; i< t; i++){
            JButton jb= new JButton();
            String s = lcs.getPerso(i).getFamille();
            jb.setName(""+s);
            jb.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    boutonActionPerformed(evt);
                }
            });
            PanneauG.add(jb);
            }
        this.pack();// pour ajuster correctement la taille des composants après les ajouts si besoin  
    }
    
    public void AffichePanneau(){
        LesPersonnages lcs = this.lj.getJoueur(indjs).getPaquet();
        for(int i=0; i<lcs.getTaille(); i++){
            JButton jc = (JButton) PanneauG.getComponent(i);         //Récupère les boutons du Panneau
            lcs.getPerso(i).setImgBouton(jc);          //Affiche les images des personnages sur les boutons
        }
    }
    
    private void boutonActionPerformed(ActionEvent evt){
        LesPersonnages lp = lj.getJoueur(indjs).getPaquet();
        int t = lp.getTaille();
        JButton bt=(JButton) evt.getSource();
        fs = bt.getName();
        for(int i = 0; i < t; i++) {
            JButton b = (JButton)(PanneauG.getComponent(i));
            if (b.getName().equals(fs))
                b.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(255, 0, 0)));
            else
                b.setBorder(null);
        }
        LesPersonnages lps = lp.getPersosFamille(fs);
        Infos.setText("Vous pouvez récupérer "+lps.getTaille()+" personnages : \n"+lps.toString());
    }
    
    public void creePanneau(JPanel jp, LesPersonnages lc){
        jp.removeAll();
        int t = lc.getTaille();
        int n = 1+(t-1)/4;
        jp.setLayout(new java.awt.GridLayout(4, n));
        for(int i=0; i<lc.getTaille(); i++){
            JButton jb = new JButton();
            jp.add(jb);
        }
        this.pack();
    }
    
    public void dessinePanneau(JPanel jp, LesPersonnages lc){
        for(int i=0; i<lc.getTaille(); i++){
            JButton jb = (JButton)(jp.getComponent(i));
            lc.getPerso(i).setImgBouton(jb);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauG = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Message = new javax.swing.JLabel();
        Message2 = new javax.swing.JLabel();
        Message3 = new javax.swing.JLabel();
        ComboJoueurs = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Infos = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        Transfert = new javax.swing.JButton();
        Fermer = new javax.swing.JButton();
        PanneauD = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 3));

        PanneauG.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(PanneauG);

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel2.setLayout(new java.awt.GridLayout(4, 1));
        jPanel2.add(Message);
        jPanel2.add(Message2);
        jPanel2.add(Message3);

        ComboJoueurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJoueursActionPerformed(evt);
            }
        });
        jPanel2.add(ComboJoueurs);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        Infos.setColumns(20);
        Infos.setRows(5);
        jScrollPane1.setViewportView(Infos);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2));

        Transfert.setText("Transfert");
        Transfert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransfertActionPerformed(evt);
            }
        });
        jPanel4.add(Transfert);

        Fermer.setText("Fermer");
        Fermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FermerActionPerformed(evt);
            }
        });
        jPanel4.add(Fermer);

        jPanel3.add(jPanel4, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1);

        PanneauD.setLayout(new java.awt.GridLayout(1, 1));
        getContentPane().add(PanneauD);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboJoueursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJoueursActionPerformed
        // Gestionnaire du clic sur la liste déroulante
        this.indjs = ComboJoueurs.getSelectedIndex();
        if (indjs != -1){
            if (this.indjs == this.indj) {
                //Le joueur a sélectionné son propre pseudo
                Infos.setText("Sélectionnez un joueur différent du joueur courant !");
                PanneauG.removeAll();
                PanneauG.repaint();
                fs = null; // cette ligne permet de bloquer le transfert
            }
            else {
                if(lj.getJoueur(indjs).getNbCartes()>0){
                    // La cible sélectionnée possède des cartes
                    Infos.setText("Joueur sélectionné: "+lj.getJoueur(indjs).toString());
                    initPanneau();
                    AffichePanneau();
                }
                else{
                    //La cible sélectionnée ne possède pas de carte
                    Infos.setText("Le Joueur sélectionner ne possèder pas de cartes !");
                    PanneauG.removeAll();
                    PanneauG.repaint();
                    fs =  null; // cette ligne permet de bloquer le transfert
                }
            }
        }
    }//GEN-LAST:event_ComboJoueursActionPerformed

    private void TransfertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransfertActionPerformed
        // Gestionnaire du bouton Transfert
        if(this.indjs != -1 && this.fs != null){          //création d'une insatnce Transfert puis appel de sa méthode execute()
            this.tc = new Transfert(lj.getJoueur(indj), lj.getJoueur(indjs), this.fs);
            int res = this.tc.execute();
            if(res>0){
                LesPersonnages Ct = this.tc.getCartesTransferees();
                creePanneau(PanneauD, Ct);
                dessinePanneau(PanneauD, Ct);
                LesPersonnages pjs = this.lj.getJoueur(indjs).getPaquet();
                creePanneau(PanneauG, pjs);
                dessinePanneau(PanneauG, pjs);
                this.ok = true; // permet de confirmer que le transfert à bien été effectué
                Transfert.setEnabled(false); // grise le bouton Transfert
                Infos.setText(this.tc.getDeroulement());
            }
        }
        else
            Infos.setText("Il est nécessaire de sélectionner un joueur qui a au moins une carte");
    }//GEN-LAST:event_TransfertActionPerformed

    private void FermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FermerActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_FermerActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TransfertDlg dialog = new TransfertDlg(new javax.swing.JFrame(), true, new LesJoueurs(), 0);
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
    private javax.swing.JComboBox<String> ComboJoueurs;
    private javax.swing.JButton Fermer;
    private javax.swing.JTextArea Infos;
    private javax.swing.JLabel Message;
    private javax.swing.JLabel Message2;
    private javax.swing.JLabel Message3;
    private javax.swing.JPanel PanneauD;
    private javax.swing.JPanel PanneauG;
    private javax.swing.JButton Transfert;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

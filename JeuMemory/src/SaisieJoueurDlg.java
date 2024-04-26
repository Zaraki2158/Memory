import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class SaisieJoueurDlg extends javax.swing.JDialog {
    private Joueur j;
    private boolean ok; //vrai si fermeture par Valider, faux sinon
    private ImageIcon photo;   // pour gére la photo du joueur y compris son affichage
    private LesPersonnages lp;

    public SaisieJoueurDlg(java.awt.Frame parent, boolean modal, LesPersonnages l) {
        super(parent, modal);
        initComponents();
        this.j = new Joueur();
        this.lp = l;
        initListeFamilles();
    }

    public Joueur getJoueur(){ return this.j; }
    public boolean getOk() { return ok; }

    private void initListeFamilles(){
        ArrayList<String> ls = lp.getFamille();
        DefaultListModel mod = new DefaultListModel();
        ListeFamilles.setModel(mod);
        for(int i=0; i<ls.size(); i++){
            mod.addElement(ls.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Pseudo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        LabelListe = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListeFamilles = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Parcourir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Photo = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        BAnnuler = new javax.swing.JButton();
        Valider = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Créez votre joueur !");
        getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Donnez votre pseudo :");
        jPanel4.add(jLabel2, java.awt.BorderLayout.NORTH);
        jPanel4.add(Pseudo, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        LabelListe.setText("Quelle est votre famille préférée de personnages ?");
        jPanel5.add(LabelListe, java.awt.BorderLayout.NORTH);

        jScrollPane1.setViewportView(ListeFamilles);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Choisissez une photo :");
        jPanel6.add(jLabel4, java.awt.BorderLayout.NORTH);

        Parcourir.setText("Parcourir");
        Parcourir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParcourirActionPerformed(evt);
            }
        });
        jPanel6.add(Parcourir, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(Photo, java.awt.BorderLayout.CENTER);

        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        BAnnuler.setText("Annuler");
        BAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAnnulerActionPerformed(evt);
            }
        });
        jPanel7.add(BAnnuler);

        Valider.setText("Valider");
        Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderActionPerformed(evt);
            }
        });
        jPanel7.add(Valider);

        jPanel3.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderActionPerformed
        // Gestionnaire du bouton Valider
        String s = "Veuillez choissir un pseudo !";
        if(Pseudo.getText().toString().equals("") || Pseudo.getText().toString().equals(s)){
            Pseudo.setText(s);      //On vérifie qu'il y ait bin un pseudo
        }
        else{
            if(ListeFamilles.isSelectionEmpty() == false){
                if(this.photo != null){
                    this.j.setPhoto(this.photo);
                }
                this.j.setPseudo(Pseudo.getText());
                this.j.setFamPref(ListeFamilles.getSelectedValue());
                this.ok = true;
                this.setVisible(false);
            }
            else{
                LabelListe.setForeground(Color.red);
                LabelListe.setText("CHOISISSEZ UNE FAMILLE !!!"); // On s'assure qu'une famille a été choisie
            }
        }       
    }//GEN-LAST:event_ValiderActionPerformed

    private void BAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAnnulerActionPerformed
        // Gestionnaire du bouton Annuler
        this.ok = false;
        this.setVisible(false);
    }//GEN-LAST:event_BAnnulerActionPerformed

    private void ParcourirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParcourirActionPerformed
        // Gestionnaire du bouton Parcourir
        JFileChooser jf= new JFileChooser();
        if (jf.showOpenDialog(this)== JFileChooser.APPROVE_OPTION){
            String path = jf.getSelectedFile().getPath();
            Image img = Toolkit.getDefaultToolkit().getImage(path);
            img = img.getScaledInstance(Photo.getWidth()-10, Photo.getHeight()-10, Image.SCALE_DEFAULT);
            this.photo = new ImageIcon(img);
            Photo.setIcon(photo);
        }
    }//GEN-LAST:event_ParcourirActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SaisieJoueurDlg dialog = new SaisieJoueurDlg(new javax.swing.JFrame(), true, new LesPersonnages() );
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
    private javax.swing.JButton BAnnuler;
    private javax.swing.JLabel LabelListe;
    private javax.swing.JList<String> ListeFamilles;
    private javax.swing.JButton Parcourir;
    private javax.swing.JButton Photo;
    private javax.swing.JTextField Pseudo;
    private javax.swing.JButton Valider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
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

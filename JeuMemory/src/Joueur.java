import javax.swing.ImageIcon;

public class Joueur {
    private String FamPref;
    private String Pseudo;
    private LesPersonnages paquet;
    private ImageIcon photo;

    public Joueur(){
        this.FamPref = "anonyme";
        this.Pseudo = "anonyme";
        this.photo = new ImageIcon("/img/anonyme.png");
        this.paquet = new LesPersonnages();
    }
    
    public Joueur(String Psd, String Fam){
        this.FamPref = Fam;
        this.Pseudo = Psd;
        this.photo = new ImageIcon("/img/"+Fam+".jpg");
        this.paquet = new LesPersonnages();
    }

    public String getFamPref(){ return this.FamPref; }    
    public String getPseudo(){ return this.Pseudo; }    
    public int getScore(){ return this.paquet.getScore();     }
    public LesPersonnages getPaquet() { return this.paquet; }  
    public ImageIcon getPhoto(){ return this.photo; } 
    public int getNbCartes(){ return this.paquet.getTaille(); }  
    public void setFamPref(String f){ this.FamPref = f; }   
    public void setPseudo(String p){ this.Pseudo = p; } 
    public void setPaquet(LesPersonnages paquet) { this.paquet = paquet; } 
    public void setPhoto(ImageIcon p){ this.photo = p; }
    public void ajoutePersoPaquet(Personnage p) { this.paquet.ajoutePerso(p); }
     
    public String toString(){
        String s ="";
        s += "Pseudo : "+this.Pseudo+"\n";
        s += "Famille préférée : "+this.FamPref+"\n";
        s += "Score : "+this.getScore()+"\n";
        s += "Paquet : \n";
        for(int i=0; i<this.paquet.getTaille(); i++){
            s += this.paquet.getPerso(i).toString();
        }
        return s;
    }
}


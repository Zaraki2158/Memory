import java.awt.*;
import javax.swing.*;

public class Personnage {

    private String Famille;
    private int Valeur;
    private String Nom;
    private Image Photo;

    public Personnage(){
        //constructeur par défaut
        this.Famille = "anonyme";
        this.Nom = "anonyme";
        this.Valeur = 0;
        this.Photo = new ImageIcon(getClass().getResource("/img/anonyme.png")).getImage();
    }
    
    public Personnage(String Fam, String Nm, int Val){
        this.Famille = Fam;
        this.Nom = Nm;
        this.Valeur = Val;
        this.Photo = new ImageIcon(getClass().getResource("/img/"+Nm+".jpg")).getImage();
    }

    public String getFamille(){ return this.Famille; }  //permet de récupérer la famille d'un personnage 
    public String getNom(){  return this.Nom; } //permet de récupérer le nom d'un personnage 
    public int getValeur(){  return this.Valeur; }   //permet de récupérer la valeur d'un personnage 
    public Image getPhoto() {  return this.Photo; } //permet de récupérer la photo d'un personnage 
    public void setFamille(String Famille) {  this.Famille = Famille; } //permet de modifié la famille d'un personnage 
    public void setValeur(int Valeur) {this.Valeur = Valeur; }  //permet de modifié la valeur d'un personnage
    public void setNom(String Nom) { this.Nom = Nom; } //permet de modifié la nom d'un personnage
    public void setPhoto(Image Photo) { this.Photo = Photo; }  //permet de modifié la photo d'un personnage
    
    public void setImgBouton(JButton jb){
        Image img = Photo.getScaledInstance(jb.getWidth(), jb.getHeight(), Image.SCALE_SMOOTH);
        jb.setIcon(new ImageIcon(img));
    }

    public String toString(){
        String s = "";
        s+= getNom()+" de la famille "+ getFamille()+", valeur : "+ getValeur()+"\n";
        return s;
        
    }
}

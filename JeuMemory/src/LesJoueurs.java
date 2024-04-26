import java.util.ArrayList;

public class LesJoueurs {
    private ArrayList<Joueur> lstJ;
 
    public LesJoueurs(){
      this.lstJ = new ArrayList<Joueur>();
    }
 
    public Joueur getJoueur(int i){
        if (i>=0 && i<this.lstJ.size())
             return this.lstJ.get(i);
        else return null;
    }
    
    public int getIndiceJoueur(Joueur j){ return this.lstJ.indexOf(j); } 
    public int getNbJoueurs(){ return this.lstJ.size(); }
    public void ajouteJoueur(Joueur j){ this.lstJ.add(j); }
    
    public void ajouteJoueurs(LesJoueurs lj){
        boolean trouve = false;
        for(int i=0; i<lj.getNbJoueurs();i++){
            String s =""+lj.getJoueur(i).getPseudo();
            for(int j=0; j<this.lstJ.size(); j++){
                if(this.lstJ.get(j).getPseudo().equals(s))
                    trouve = true;
            }
            if(trouve == false)
                this.lstJ.add(lj.getJoueur(i));
            trouve = false;
        }
    }
    
    public Joueur rechJoueur(String p){
        boolean trouve = false;
        int ind=0;
        for(int i=0; i<this.lstJ.size();i++){
            if(p.equals(this.lstJ.get(i).getPseudo())){
                trouve = true;
                ind = i;
            }
        }
        if(trouve == true)
            return this.lstJ.get(ind);
        else
            return null;
    }
    
    public void supprimeJoueur(Joueur j){
        int i = this.getIndiceJoueur(j);
        this.lstJ.remove(i);
    }
    
    public String toString(){
        String s ="";
        for(int i=0; i<this.lstJ.size(); i++){
            s+= this.lstJ.get(i).toString();
        }
        return s;
    }
    
    public LesJoueurs getGagnants(){  
        int max=getJoueur(0).getScore();        //récupère le score du Joueur d'indice 0
        for(int i=1; i<getNbJoueurs();i++){ 
            if (getJoueur(i).getScore()>max)
                max=this.getJoueur(i).getScore();   //affecte à max le score le plus élevé de la liste de Joueur
        }
        LesJoueurs lst=new LesJoueurs();    //créer une nouvelle instance LesJoueurs
        for(int i=0; i<getNbJoueurs();i++){
            if (getJoueur(i).getScore()==max)
                lst.ajouteJoueur(getJoueur(i));     //ajoute un Joueur dans lst
        }
        return lst; //créer une liste des Joueurs qui possède le meilleur score
    }
    
}

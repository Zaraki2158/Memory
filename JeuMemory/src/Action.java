public abstract class Action {
    protected Joueur j;   //joueur courant
    protected String desccriptif;
    protected String deroulement;
    
    public Action(Joueur j, String desccriptif) {
        this.j = j;
        this.desccriptif = desccriptif;
    }

    public Joueur getJoueurCourant() { return j; } // renvoie le joueur courant
    public String getDescriptif() { return desccriptif; }   // renvoie le descriptif qui à pour rôle de définir la nature de l'action effectuée
    public String getDeroulement() { return deroulement; } //renvoie l'historique des actions effectuées par le joueur courant            
    public void setJoueurCourant(Joueur j) { this.j = j; }// modifie le joueur ccourant
    public void setDesccriptif(String desccriptif) { this.desccriptif = desccriptif; }//modifie le descriptif du joueur
    public void setDeroulement(String deroulement) { this.deroulement = deroulement; } // modifie le déroulement du joueur
       
    public String toString(){
        //renvoie sous la forme d'un String, l'ensemble des détails de l'action effectuée par le joueur courant
        return "Action effectuée par "+j.getPseudo()+ " : " +getDescriptif()+"\n"+this.deroulement+"\n";
    }
   
    public abstract int execute();
    /*
    Méthode qui retourne un entier pour expliquer comment s'est passée l'action.
        0 => si égalité des cartes
        1 => si le joueur courant gagne la carte
        2 => si l'adversaire gagne la carte
        -1 => sinon 
    */
}

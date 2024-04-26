public class Jeu { 
    private LesPersonnages lp; //les personnages présents sur le Plateau
    private PlateauJeu pl;
    private LesJoueurs lj;
    private int indj;   //indice du joueur courant
    private Action act;     //action que le joueur va effectuer

    public Jeu(LesPersonnages lp, LesJoueurs lj, int nv) {
        this.lp = lp;
        this.lj = lj;
        this.pl = new PlateauJeu(nv);// créer un plateau en fonction du niveau du jeu indiqué
        int indj = 0;
    }

    public LesPersonnages getLp() { return lp; }
    public PlateauJeu getPlateau() { return pl; }
    public LesJoueurs getLj() { return lj; }
    public int getIndj() { return indj; }  
    public Joueur getJoueurCourant(){ return this.lj.getJoueur(indj); } //renvoie le joueur courant  
    public int getIndSuivant(int j){ return (j+1)%lj.getNbJoueurs(); }  // renvoie l'indice du joueur suivant dans l'ordre croissant en fin de liste elle renvoie le premier indice de la liste
    public Joueur getJoueurSuivant(int j){ return lj.getJoueur(getIndSuivant(j)); }// renvoie l'indice du joueur de la methode getIndSuivant 
    public void setJoueurCourant(int i){ this.indj = i; }  // Change le joueur courant via son numéro.
    public boolean finJeu(){ return this.pl.jeuVide(); }// Vérifie si le jeu est terminé
        
    public int nbPers(){ // Renvoie la somme totale des personnages que possèdes les joueurs adverses, sans compté le joueur courant
        int n = 0;
        for(int i = 0; i < lj.getNbJoueurs(); i++){
            if (i !=this.indj)
                n+=lj.getJoueur(i).getNbCartes();
        }
        return n;
    }


    public int TraiterTour(Joueur jo, int s){
        int bonus = -1;
        Personnage pers = this.lp.getPerso(s);
        jo.ajoutePersoPaquet(pers);
        String f = ""+pers.getFamille();
        int nbf = this.lp.getPersosFamille(f).getTaille();// nbf désigne le nombre de personnages de la famille "f" dans la partie
        int nbj = jo.getPaquet().getPersosFamille(f).getTaille();//nbj désigne le nombre de personnages de la famille "f" dans le paquet du joueur
        if(nbf == nbj){
            if(jo.getFamPref().equals(f)){
                bonus = 0;
                this.pl.termineJeu(); //    0 si le joueur a tous les personnages de sa famille préférée ce qui termine la partie
            }
            else if(nbPers()>0){
                if(f.equals("rares") || f.equals("communs"))
                    bonus = 1; //1 si un transfert de cartes peut être réalisé
                else if(f.equals("legendaires") || f.equals("epiques"))
                    bonus = 2;//2 si une bataille peut être faite
                else
                    bonus = 3;//3 si c’est un combat
            }
        }
        return bonus;
    }

  
    
    
}

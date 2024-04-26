public class Bataille extends Action{
    private Joueur adversaire;

    public Bataille(Joueur jc, Joueur a){
        super(jc, "Bataille");
        this.adversaire = a;
    }

    public Joueur getAdversaire() { return this.adversaire; } // retourne l'adversaire
       
    public int execute(){
        Personnage c1, c2;
        int res = -1;
        if(this.getJoueurCourant().getPaquet().getTaille() > 0 && this.adversaire.getPaquet().getTaille() > 0){ // verifie si le paquet du joueru est vide
            c1 = this.getJoueurCourant().getPaquet().getPerso(0); // Premier personnage du joueur courant
            c2 = this.adversaire.getPaquet().getPerso(0); // Premier personnage de l'adversaire
            //suppression des cartes aux joueurs
            this.getJoueurCourant().getPaquet().retirePerso(c1);
            this.adversaire.getPaquet().retirePerso(c2);
            int v1 = c1.getValeur(); //Joueur courant
            int v2 = c2.getValeur(); //adversaire           
            //comparaison des valeurs des cartes           
            if(v1 == v2){
                // les cartes ont la même valeur, il y a égalité
                res = 0;
                this.getJoueurCourant().ajoutePersoPaquet(c1);
                this.adversaire.ajoutePersoPaquet(c2);
            }
            else if (v1 > v2){
                //Le Joueur Courant gagne
                res = 1;
                this.getJoueurCourant().ajoutePersoPaquet(c1);
                this.getJoueurCourant().ajoutePersoPaquet(c2);
                }
                else{
                //L'adversaire gagne
                    res = 2;
                    this.adversaire.ajoutePersoPaquet(c1);
                    this.adversaire.ajoutePersoPaquet(c2);
                }            
            //test si l'un des paquets est vide           
            if(this.getJoueurCourant().getPaquet().getTaille() == 0 || this.adversaire.getPaquet().getTaille() == 0){
                String s = "Bataille "+this.getJoueurCourant().getPseudo();
                s += " contre "+this.adversaire.getPseudo()+" est ";
                if(this.getJoueurCourant().getPaquet().getTaille() > 0)
                    s += " gagnée \n";
                else
                    s += " perdue \n";
                this.setDeroulement(s);
            }
        }
        return res;
    }
}

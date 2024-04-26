public class Transfert extends Action{
    private Joueur cible;
    private String famille;
    private LesPersonnages cf;
      
    public Transfert(Joueur jc, Joueur cib, String fam){
        super(jc, "Transfert de cartes");
        this.cible = cib;
        this.famille = fam;
        this.cf = new LesPersonnages();
    }

    public Joueur getJoueurCible(){ return this.cible;}  
    public LesPersonnages getCartesTransferees(){return cf;}
    public void setCartesTransferees(LesPersonnages c) {this.cf = c;}

    public int execute(){  // cette méthode renvoie le nombre de cartes transférées
        int res = 0;
        if(this.famille != null){
            LesPersonnages cartestransferees = this.cible.getPaquet().getPersosFamille(famille);
            this.cible.getPaquet().retirePersosFamille(famille);
            this.j.getPaquet().ajoutePersos(cartestransferees);
            String s ="Le joueur "+this.j.getPseudo();
            s+= " a pris les cartes de la famille "+this.famille;
            s+= " au joueur "+this.cible.getPseudo();
            setDeroulement(s);
            setCartesTransferees(cartestransferees);
            res = cartestransferees.getTaille();
        }
        return res;
    }
    
}

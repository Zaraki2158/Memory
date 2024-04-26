public class PlateauJeu {
    private int tab [][];
    private int nbp; // nombre de personnages sur le plateau qui diminue au cours du jeu
    private int nblig;
    private int nbcol;
    
    public PlateauJeu(int n){
        this. nbp=n;
        this.nblig=(int)(Math.sqrt(nbp*2));
        this.nbcol=nbp*2/nblig;
        this.tab=new int [this.nblig][this.nbcol];
        initPlateauJeu();
    }
    
    public PlateauJeu(){
        this(4);
    }

    public int getNblig() {   return this.nblig;  }   
    public int getNbcol() {   return this.nbcol;  }    
    public int getNbp() {   return this.nbp;  }    
    public int getCase(int l, int c) {   return tab[l][c];  }    
    public int getNbc() {   return this.nblig*this.nbcol/2;  }
    
    public void initPlateauJeu(){   
        int k=0;
        for(int i=0; i<this.nblig; i++){
            for(int j=0; j<this.nbcol; j++){
                tab[i][j]=(k++)%this.nbp;
            }
        }
        melange();
    }
    
    public void invalide(int l1, int c1, int l2, int c2){
        tab[l1][c1]=-1;
        tab[l2][c2]=-1;
        nbp--;
    }
    
    public void melange(){
        int cpt = 0;
        int l1, c1, l2, c2;
        do{
            l1 = (int)(Math.random()*getNblig());
            c1 = (int)(Math.random()*getNbcol());
            l2 = (int)(Math.random()*getNblig());
            c2 = (int)(Math.random()*getNbcol());
            int i = tab[l1][c1];
            tab[l1][c1] = tab[l2][c2];
            tab[l2][c2] = i;
            cpt++;
            
        }while(cpt < 999);
    }
    
    public boolean estValide(int l, int c){
        if(this.tab[l][c] > -1)
            return true;
        else
            return false;
    }
    
    public boolean jeuVide(){
        
        return this.nbp <= 0;
    }
    
    public void termineJeu(){
        for(int i=0; i<this.getNblig(); i++){
            for(int j=0; j<this.getNbcol(); j++){       //Affecte à toutes les case du tableau la valeur -1
                this.tab[i][j] = -1;                            
            }
        }
    }
}

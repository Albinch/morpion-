public class Case{

    private int joueur;

    public Case(){
	    joueur = 0;
    }

    public Case(Case c){
	    joueur = c.joueur;
    }

    public Case(int joueur){
        this.joueur = joueur;
    }

    public void setJoueur(int joueur){
        this.joueur = joueur;
    }

    public int getJoueur(){
        return joueur;
    }

    public boolean equals(Case c){
	if(c.joueur==joueur)
	    return true;
	else
	    return false;
    }

    public String toString(){
        return(Integer.toString(joueur));
    }

}
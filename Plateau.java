public class Plateau{
	private Case plateau[][];

	//Constructeur qui permet de créer un Plateau de jeu 3x3 rempli de 0 (type Case)
	public Plateau(){
		plateau = new Case[3][3];
		for(int i=0; i<=plateau.length-1; i++){
			for(int j=0; j<=plateau.length-1; j++){
				plateau[i][j] = new Case();
			}
		}
	}

	//Méthode prenant deux entiers en paramètre et permettant d'accéder à une case du plateau
	public Case getXY(int i, int j){
		if(i>plateau.length-1 || j>plateau.length-1){
			System.out.println("Vous sortez des limites du plateau");
			System.exit(0);
		}
		return plateau[i][j];
	}

	//Méthode permettant de vérifier si le plateau est plein ou non, retourne true si il l'est, false sinon
	public boolean plein(){
		for(int i=0; i<=plateau.length-1; i++){
			for(int j=0; j<=plateau.length-1; j++){
				if(plateau[i][j].equals(new Case())){
					return false;
				}
			}
		}
		return true;
	}

	//J'ai décomposé la méthode gagne() en 4 "sous-méthodes":

	//Ici on vérifie les lignes du plateau
	public int verifLigne(){
		for(int cptLigne = 0;cptLigne<=plateau.length-1;cptLigne++){
			int cptJ1 = 0; //compteur qui compte le nombre de case contenant un 1
			int cptJ2 = 0; //compteur qui compte le nombre de case contenant un 2
			for(int i = 0;i<=plateau.length-1;i++){
				if(plateau[cptLigne][i].equals(new Case(1))){
					cptJ1++; //Si on tombe sur un 1 on incrémente ce compteur
					cptJ2=0; //Et on remet à 0 celui ci
				}else if(plateau[cptLigne][i].equals(new Case(2))){
					cptJ2++; //Si on tombe sur un 2 on incrémente ce compteur
					cptJ1=0; //Et on remet à 0 celui ci
				}
			}
			if(cptJ1 == 3){
				return 1; //si le cptJ1 arrive à 3, on retourne 1
			}else if(cptJ2 == 3){
				return 2; //si le cptJ2 arrive à 3, on retourne 2
			}
		}
		return 0; //si aucun des deux compteurs n'arrive à 3, on retourne 0
	}


	//Ici on vérifie les colonnes du plateau (on a gardé le même mécanisme pour les compteurs, cf verifLigne())
	public int verifColonne(){
		for(int cptCol = 0;cptCol<=plateau.length-1;cptCol++){
			int cptJ1 = 0;
			int cptJ2 = 0;
			for(int i=0;i<=plateau.length-1;i++){
				if(plateau[i][cptCol].equals(new Case(1))){
					cptJ1++;
					cptJ2 = 0;
				}else if(plateau[i][cptCol].equals(new Case(2))){
					cptJ2++;
					cptJ1 = 0;
				}
			}
			if(cptJ1 == 3){
				return 1;
			}else if(cptJ2 == 3){
				return 2;
			}
		}
		return 0;
	}

	//Ici on vérifie la diagonale qui part d'en haut à gauche et qui fini en bas à droite du plateau (on a gardé le même mécanisme pour les compteurs, cf verifLigne())
	public int verifDiagoHautGaucheBasDroite(){
		int cptJ1 = 0;
		int cptJ2 = 0;
		//Diagonale haut gauche, bas droite
		for(int m=0; m<=plateau.length-1; m++){
			if(plateau[m][m].equals(new Case(1))){
				cptJ1++;
				cptJ2 = 0;
				if(cptJ1==3){
					return 1;
				}else if(cptJ2==3){
					return 2;
				}
			}else if(plateau[m][m].equals(new Case(2))){
				cptJ2++;
				cptJ1 = 0;
				if(cptJ1==3){
					return 1;
				}else if(cptJ2==3){
					return 2;
				}
			}
		}
		return 0;
	}

	//Ici on vérifie la diagonale qui part d'en haut à droite et qui fini en bas à gauche du plateau (on a gardé le même mécanisme pour les compteurs, cf verifLigne())
	public int verifDiagoHautDroiteBasGauche(){
		int cptJ1 = 0;
		int cptJ2 = 0;
		//Diagonale haut droite, bas gauche
		for(int i=plateau.length-1; i>=0; i--){
			if(plateau[i][(plateau.length-1)-i].equals(new Case(1))){
				cptJ1++;
				cptJ2 = 0;
			}else if(plateau[i][(plateau.length-1)-i].equals(new Case(2))){
				cptJ2++;
				cptJ1 = 0;
				}
			}
			if(cptJ1 == 3){
				return 1;
			}else if(cptJ2 == 3){
				return 2;
			}
			return 0;
		}


	//Affichage du plateau
	public String toString(){
		return getXY(0,0)+" "+getXY(0,1)+" "+getXY(0,2)+"\n"+getXY(1,0)+" "+getXY(1,1)+" "+getXY(1,2)+"\n"+getXY(2,0)+" "+getXY(2,1)+" "+getXY(2,2);
	}


}

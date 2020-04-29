import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Plateau tabJeu = new Plateau();
        boolean placeOK = false;
        int coordX = 0;
        int coordY = 0;
        int tourParTour = 1;
        int gagnantLigne = 0;
        int gagnantColonne = 0;
        int gagnantDiago1 = 0; //La diago 1 est celle qui part d'ne haut à gauche
        int gagnantDiago2 = 0; //La diago 2 est celle qui part d'en haut à droite
        boolean grillePleine;

        System.out.println(tabJeu);
        while(gagnantLigne == 0 && gagnantColonne == 0 && gagnantDiago1 == 0 && gagnantDiago2 == 0){
            System.out.println("Veuillez entrer la coordonnée X"); //L'utilisateur rentre la coordonnée X de son pion
            coordX = sc.nextInt();

            System.out.println("Veuillez entrer la coordonnée Y"); //Puis la coordonnée Y
            coordY = sc.nextInt();

            //Dans cette boucle on vérifie si on peut placer le pion
            do{
                if(tabJeu.getXY(coordX,coordY).equals(new Case())){ //Si la case comporte un 0 on peut placer le pion
                    placeOK = true;
                }else{ //Sinon on redemande les corrdonnées à l'utilisateur
                    System.out.println("La case est déjà prise, entrez de nouvelles coord.");
                    System.out.println("Veuillez entrer la coordonnée X");
                    coordX = sc.nextInt();
    
                    System.out.println("Veuillez entrer la coordonnée Y");
                    coordY = sc.nextInt();
                }
            }while(!placeOK); //On execute cette boucle tant que le pion ne peut pas être placé
            
            if(placeOK){ //On place le pion si il peut être placé
                tabJeu.getXY(coordX,coordY).setJoueur(tourParTour);
            }

            System.out.println(tabJeu);

            //On change de joueur: si le joueur1 a joué, on fait passer la variable tourParTour à 2 pour donner la main au joueur 2 et inversement
            if(tourParTour == 1){
                tourParTour = 2;
            }else if(tourParTour == 2){
                tourParTour = 1;
            }

            //On affecte une valeur aux variables en appelant les sous-métohdes de la méthode gagne()
            gagnantLigne = tabJeu.verifLigne();
            gagnantColonne = tabJeu.verifColonne();
            gagnantDiago1 = tabJeu.verifDiagoHautGaucheBasDroite();
            gagnantDiago2 = tabJeu.verifDiagoHautDroiteBasGauche();
            grillePleine = tabJeu.plein(); //Et ici en appelant la méthode plein()
            if(gagnantLigne == 1 || gagnantColonne == 1 || gagnantDiago1 == 1 || gagnantDiago2 == 1){ //Si une des variable est à 1 cela veut dire que le joueur 1 a gagné
                System.out.println("Le joueur 1 a gagné");
                System.exit(0);
            }else if(gagnantLigne == 2 || gagnantColonne == 2 || gagnantDiago1 == 2 || gagnantDiago2 == 2){ //Si une des variable est à 1 cela veut dire que le joueur 1 a gagné
                System.out.println("Le joueur 2 a gagné");
                System.exit(0);
            }else{
                System.out.println("Au tour du joueur suivant");
            }

            if(grillePleine && gagnantLigne == 0 && gagnantColonne == 0 && gagnantDiago1 == 0 & gagnantDiago2 == 0){ //Si le tableau est plein et que toutes les variables sont à 0, alors il y a match nul
                System.out.println("Match nul");
                System.exit(0);
            }
        }
    }
}
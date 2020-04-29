import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;

public class InterfaceGraphique {
    
    //Méthode qui permet de créer le visuel du plateau
    public static void creaTableau(Fenetre f,int TAILLEX,int TAILLEY){
        for(int i=1;i<=3;i++){
            Ligne ligneHoriz = new Ligne(new Point(0,i*100),new Point(TAILLEX,i*100));
            f.ajouter(ligneHoriz);
        }
        for(int j=1;j<=3;j++){
            Ligne ligneVert = new Ligne(new Point(j*100,TAILLEX),new Point(j*100,0));
            f.ajouter(ligneVert);
        }
    }

    //Méthode qui permet de placer les pions dns le main et sur la fenêtre graphique
    public static Plateau cliqueSouris(Souris s,Fenetre f,Plateau tabJeuIG,int tourParTour){
        Point posSouris = new Point();
        boolean placeOK;
            do{
                posSouris = new Point(s.getPosition()); //Ici on récupère les coordonnées de la souris
                try{
                    Thread.sleep(1);
                }catch(Exception e){}
            }while(!s.getClicGauche()); //Ceci nous permet d'avoir le dernier endroit où était la souris avant le clic

        //On divise par 100 pour permettre de placer les pions dans le terminal car les cases de l'interface graphique font 100 de côté
        float xSouris = posSouris.getX()/100;
        float ySouris = posSouris.getY()/100;

        //Et on récupère la partie entière
        int xPlateau = (int) xSouris;
        int yPlateau = (int) ySouris;

        if(!tabJeuIG.getXY(xPlateau,yPlateau).equals(new Case())){ //Si l'utilisateur clique sur une case qui était déjà remplie, il passe son tour
            if(tourParTour == 1){
                tourParTour = 2;
            }else if(tourParTour == 2){
                tourParTour = 1;
            }
        }else{ //Sinon on place le pion dans le terminal
            tabJeuIG.getXY(xPlateau,yPlateau).setJoueur(tourParTour);
        }

        if(tourParTour == 1){
            Cercle c1 = new Cercle(Couleur.BLEU,new Point(xPlateau*100+50,yPlateau*100+50),50,true); //Si le joueur 1 joue on crée un cercle bleu (*100+50 pour que les cercles soient bien centrés dans les cases)
            f.ajouter(c1);
        }else if(tourParTour == 2){
            Cercle c2 = new Cercle(Couleur.ROUGE,new Point(xPlateau*100+50,yPlateau*100+50),50,true); //Si le joueur 2 joue on crée un cercle rouge (*100+50 pour que les cercles soient bien centrés dans les cases)
            f.ajouter(c2);
        }
         return tabJeuIG;
    }

    public static void main(String[] args){
        Plateau tabJeuIG = new Plateau();
        Plateau tabJeuNouveau = new Plateau();
        int TAILLEX = 300;
        int TAILLEY = 500;
        int gagnantLigne = 0;
        int gagnantColonne = 0;
        int gagnantDiago1 = 0;
        int gagnantDiago2 = 0;
        boolean grillePleine = false;
        int tourParTour = 1;
        Fenetre f = new Fenetre("MORPION",TAILLEX,TAILLEY);
        Souris s = f.getSouris();
        creaTableau(f,TAILLEX,TAILLEY);
        Texte texteJoueur1 = new Texte(Couleur.ROUGE,"rouge joue",new Font("Arial",Font.TYPE1_FONT,40),new Point(100,400));
        Texte texteJoueur2 = new Texte(Couleur.BLEU,"bleu joue",new Font("Arial",Font.TYPE1_FONT,40),new Point(100,400));

       //On reprend le même mécanisme que dans la classe Main sauf qu'on y ajoute quelques textes sur le graphique
        while(gagnantLigne == 0 && gagnantColonne == 0 && gagnantDiago1 == 0 && gagnantDiago2 == 0){
            tabJeuNouveau = cliqueSouris(s,f,tabJeuIG,tourParTour);
            System.out.println(tabJeuNouveau);

            gagnantLigne = tabJeuNouveau.verifLigne();
            gagnantColonne = tabJeuNouveau.verifColonne();
            gagnantDiago1 = tabJeuNouveau.verifDiagoHautGaucheBasDroite();
            gagnantDiago2 = tabJeuNouveau.verifDiagoHautDroiteBasGauche();
            grillePleine = tabJeuNouveau.plein();

            if(tourParTour == 1){
                f.supprimer(texteJoueur2);
                f.ajouter(texteJoueur1);
                tourParTour = 2;
            }else if(tourParTour == 2){
                f.supprimer(texteJoueur1);
                f.ajouter(texteJoueur2);
                tourParTour = 1;
            }

            if(gagnantLigne == 1 || gagnantColonne == 1 || gagnantDiago1 == 1 || gagnantDiago2 == 1){
                try{
                    Thread.sleep(500);
                }catch(Exception e){}
                f.effacer();
                Texte gagnantJoueur1 = new Texte(Couleur.BLEU,"joueur bleu vainqueur!!",new Font("Arial",Font.TYPE1_FONT,20),new Point(150,250));
                f.ajouter(gagnantJoueur1);
            }else if(gagnantLigne == 2 || gagnantColonne == 2 || gagnantDiago1 == 2 || gagnantDiago2 == 2){
                try{
                    Thread.sleep(500);
                }catch(Exception e){}
                f.effacer();
                Texte gagnantJoueur2 = new Texte(Couleur.ROUGE,"joueur rouge vainqueur!!",new Font("Arial",Font.TYPE1_FONT,20),new Point(150,250));
                f.ajouter(gagnantJoueur2);
            }
    
            if(grillePleine && gagnantLigne == 0 && gagnantColonne == 0 && gagnantDiago1 == 0 & gagnantDiago2 == 0){
                try{
                    Thread.sleep(500);
                }catch(Exception e){}
                f.effacer();
                Texte egalite = new Texte(Couleur.NOIR,"match nul!",new Font("Arial",Font.TYPE1_FONT,20),new Point(150,250));
                f.ajouter(egalite);
            }
            f.rafraichir();
        }
        f.rafraichir();
    }
}

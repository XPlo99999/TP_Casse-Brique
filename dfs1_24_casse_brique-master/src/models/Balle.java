package models;

import application.Fenetre;

import java.awt.*;
import java.util.List;

public class Balle extends Sprite {
    protected int vitesseX = 10;
    protected int vitesseY = 10;
    protected int diametre;

    public Balle(int x, int y, Color couleur, int diametre) {
        super(x, y , couleur);
        this.diametre = diametre;
    }

    private boolean collisionBrique(Brique brique) {
        return x + diametre >= brique.getX() && x <= brique.getX() + brique.getLargeur() && y + diametre >= brique.getY() && y <= brique.getY() + brique.getHauteur();
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x, y, diametre, diametre);
    }

    public void deplacement(Barre barre, List<Brique> briques) {

        for (int b = 0; b < briques.size(); b++) {
            Brique brique = briques.get(b);
            if (this.collisionBrique(brique)) {
                briques.remove(b);
                vitesseY = -vitesseY;
                break;
            }
        }

        if(x > Fenetre.LARGEUR - diametre || x < 0) {
            vitesseX = - vitesseX;
        }

        if(y < 0){
            vitesseY = -vitesseY;
        }

        if(y > Fenetre.HAUTEUR - diametre) {
            vitesseY = - vitesseY;
        }

        if (y + diametre >= barre.getY() && y <= barre.getY() + barre.getHauteur()&& x + diametre >= barre.getX() && x <= barre.getX() + barre.getLargeur()) {
            y = barre.getY() - diametre;
            vitesseY = -vitesseY;
        }

        x += vitesseX;
        x += vitesseX;
        y += vitesseY;
        y += vitesseY;
    }

}

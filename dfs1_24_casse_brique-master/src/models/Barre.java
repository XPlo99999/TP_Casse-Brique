package models;

import application.Fenetre;
import java.awt.*;

public class Barre extends Rectangle {
    private static final int DEPLACEMENT = 20;

    public Barre() {
        super(Fenetre.LARGEUR / 2 - 75, Fenetre.HAUTEUR - 100, Color.RED, 150, 20);
        this.largeur = 150;
        this.hauteur = 20;
    }

    public void deplacerDroite() {
        x = Math.min(Fenetre.LARGEUR - largeur, x + DEPLACEMENT);
    }

    public void deplacerGauche() {
        x = Math.max(0, x - DEPLACEMENT);
    }

    public void dessiner(Graphics2D dessin) {
        super.dessiner(dessin);
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public int getLargeur() {
        return super.getLargeur();
    }

    public int getHauteur() {
        return super.getHauteur();
    }
}

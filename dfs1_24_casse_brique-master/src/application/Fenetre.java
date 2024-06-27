package application;

import models.Balle;
import models.Barre;
import models.Brique;
import models.Sprite;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends Canvas implements KeyListener {
    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;
    protected boolean toucheEspace = false;
    protected boolean toucheFlecheGauche = false;
    protected boolean toucheFlecheDroite = false;
    ArrayList<Sprite> listeSprites = new ArrayList<>();
    ArrayList<Balle> listeBalles = new ArrayList<>();
    Barre barre;
    ArrayList<Brique> listeBriques = new ArrayList<>();


    Fenetre()  throws InterruptedException {
        JFrame fenetre = new JFrame();
        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0, 0, LARGEUR, HAUTEUR);
        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        fenetre.pack();
        fenetre.setSize(LARGEUR, HAUTEUR );
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setResizable(false);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);
        Container panneau = fenetre.getContentPane();
        panneau.add(this);
        fenetre.setVisible(true);
        this.createBufferStrategy(2);
        this.demarrer();
    }

    public void demarrer() throws InterruptedException {
        int largeurBrique = 40;
        int hauteurBrique = 20;
        Balle balle = new Balle(100, 200 , Color.ORANGE, 30);
        barre = new Barre();
        listeSprites.add(barre);
        listeBalles.add(balle);
        listeSprites.add(balle);
        int marge = 10;
        int lignes = 5;
        int colonnes = 10;

        for (int ligne = 0; ligne < lignes; ligne++) {
            for (int colonne = 0; colonne < colonnes; colonne++) {
                int briqueY = ligne * (hauteurBrique + marge);
                int briqueX = colonne * (largeurBrique + marge);
                Brique brique = new Brique(briqueX, briqueY, Color.GRAY, largeurBrique, hauteurBrique);
                listeSprites.add(brique);
                listeBriques.add(brique);
            }
        }

        while(true) {
            Graphics2D dessin = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0,0,LARGEUR,HAUTEUR);

            for(Balle b : listeBalles) {
                b.deplacement(barre, listeBriques);
            }

            List<Sprite> spritesToDraw = new ArrayList<>(listeSprites);
            for (Sprite t : spritesToDraw) {
                if (t instanceof Brique && !listeBriques.contains(t)) {
                    continue;
                }
                t.dessiner(dessin);
            }

            if(toucheEspace) {
                listeBalles.add( new Balle(200, 400 , Color.ORANGE, 50));
            }

            if(toucheFlecheDroite){
                barre.deplacerDroite();
            }

            if(toucheFlecheGauche){
                barre.deplacerGauche();
            }

            dessin.dispose();
            this.getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            toucheEspace = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            toucheFlecheDroite = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            toucheFlecheGauche = true;
        }
    }

    @Override
    public void  keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            toucheEspace = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            toucheFlecheDroite = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            toucheFlecheGauche = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

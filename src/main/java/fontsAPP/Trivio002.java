package fontsAPP;

import processing.core.PApplet;

public class Trivio002 extends PApplet {

    Tipus_font fontsA;

    public static void main(String[]args){
        PApplet.main("fontsAPP.Trivio002", args);
    }

    public void settings(){
       size(1440, 1200);
        smooth(8);
    }

    public void setup(){
        fontsA = new Tipus_font(this);
    }

    public void draw(){
        background(255);

    textFont(fontsA.getFontOne());
    text("Titol de la solucio informatica", 50, 200);

    fill(55, 0, 0);
    textFont(fontsA.getFontTwo());
    text("Paràgrafs de la solució informàtica", 50, 400);

        textFont(fontsA.getFontThree());
        text("Nom de les pantalles", 50, 300);

    //mostra totes les fonts de l'App
    fontsA.displayFonts(this, 100, 400, 50);
    }

    //Keyboard interaction
    public void keyPressed(){
        println("THE KEY IS BEING PRESSED");
    }

    //Mouse interaction
    public void mousePressed(){
        println("THE MOUSE IS BEING PRESSED");
    }

    public void mouseDragged(){
        println("THE MOUSE IS BEING DRAGGED");
    }

    public void mouseReleased(){
        println("THE MOUSE IS BEING RELEASED");
    }
}

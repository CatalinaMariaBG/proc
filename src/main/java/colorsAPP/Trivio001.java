package colorsAPP;
import processing.core.PApplet;
public class Trivio001 extends PApplet{
    //Colors de l'aplicació
    Colors colorsA;

    //Cercles que s'han de dibuixar
Cercles c1, c2, c3;

    public static void main(String[] args){
        PApplet.main("colorsAPP.Trivio001", args);
    }

    public void settings(){
        size(800, 800);
        smooth(10);
    }
    public void setup(){
colorsA = new Colors (this);
c1 = new Cercles(width/4, height/2, Mides.midaCercles);
c1.setColorC(colorsA.getColorsFirst());

c2 = new Cercles(width/4, height/2, Mides.midaCercles);
c2.setColorC(colorsA.getColorsSecond());

c3 = new Cercles(width/4, height/2, Mides.midaCercles);
c3.setColorC(colorsA.getColorsThird());
    }

    public void draw(){
        background(255);

        c1.display(this);
        c2.display(this);
        c3.display(this);

        fill(colorsA.getColorsChoose(4)); noStroke();
        rect(0, 3*height/4, width, height/4);

        colorsA.displayColors(this, 100, 100, width-200);

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

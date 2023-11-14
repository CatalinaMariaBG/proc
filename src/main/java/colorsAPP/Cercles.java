package colorsAPP;
import processing.core.PApplet;
public class Cercles {

    float x, y, rad;
    int colorC;

    public Cercles(float x, float y, float rad){
        this.x = x;
        this.y = y;
        this.rad = rad;
    }

    public void setColorC(int colorC){
        this.colorC = colorC;
    }

    public void display(PApplet processing){
        processing.fill(colorC);
        processing.stroke(0);
        processing.strokeWeight(4);
        processing.ellipse(x, y, rad, rad);
    }
}

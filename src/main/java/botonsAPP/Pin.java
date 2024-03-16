package botonsAPP;

import processing.core.PApplet;
import processing.core.PFont;
import setupAPP.Setup;

public class Pin {
    float x, y;

    public Pin(PApplet processing, float x, float y){
        this.x = x;
        this.y = y;
    }

    public void display(PApplet processing){
        processing.fill(0);
        processing.ellipse(this.x, this.y, 10,10);
    }
}

package botonsAPP;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import setupAPP.Setup;

public class Pin {
    public float x, y;
    public String text;

    public Pin(float x, float y, String t){
        this.x = x;
        this.y = y;
        this.text = t;
    }

    public void display(PGraphics dibuix){
        dibuix.fill(0);
        dibuix.ellipse(this.x, this.y, 10,10);
        dibuix.fill(219, 217, 209);
        dibuix.rect(this.x + (Setup.wButtonMap / 2) / 2 + 10, this.y - Setup.hButtonsMap / 2,Setup.wButtonMap / 2, Setup.hButtonsMap, 10);
        dibuix.fill(0);
        dibuix.text(text,this.x + (Setup.wButtonMap / 2) / 2 + 10, this.y - Setup.hButtonsMap / 2);
    }
}
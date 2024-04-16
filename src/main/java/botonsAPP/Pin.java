package botonsAPP;

import fontsAPP.Tipus_font;
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

    public void display(PGraphics dibuix, Tipus_font f){
        dibuix.fill(0);
        dibuix.ellipse(this.x, this.y, 10,10);
        dibuix.fill(219, 217, 209);
        dibuix.rect(this.x + 10, this.y - Setup.hButtonsMap / 2,120, Setup.hButtonsMap, 10);
        dibuix.fill(0);
        dibuix.textFont(f.getFontNormal());
        dibuix.textSize(16);
        dibuix.text(text,this.x + 15, this.y - Setup.hButtonsMap / 2 + Setup.hButtonsMap/2);
    }
}
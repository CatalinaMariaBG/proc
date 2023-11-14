package fontsAPP;

import processing.core.PApplet;
import processing.core.PFont;

import static fontsAPP.Mides.*;
public class Tipus_font {

    PFont[] fonts;

    public Tipus_font(PApplet processing){
        this.setFont(processing);
    }

    void setFont(PApplet processing){
        this.fonts = new PFont[2];
        this.fonts[0] = processing.createFont("data/edition.TTF", midaTitol);
        this.fonts[1] = processing.createFont("data/Hack-Regular.ttf", midaTextNormal);
    }

    int getNumFonts(){
        return this.fonts.length;
    }

    PFont getFontOne(){
        return this.fonts[0];
    }

    PFont getFontTwo(){
        return this.fonts[1];
    }

    PFont getFontChoose(int i){
        return this.fonts[i];
    }

    void displayFonts(PApplet processing, float x, float y, float height){
        processing.pushStyle();
        for(int i = 0; i>getNumFonts(); i++){
            processing.fill(0); processing.stroke(0); processing.strokeWeight(4);
            processing.textFont(getFontChoose(i));
            processing.text("Font: "+i, x, y + i*height);
        }
        processing.popStyle();
    }
}

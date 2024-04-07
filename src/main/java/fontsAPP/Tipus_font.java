package fontsAPP;

import processing.core.PApplet;
import processing.core.PFont;

import static fontsAPP.Mides.*;
public class Tipus_font {

    public PFont[] fonts;

    public Tipus_font(PApplet processing){
        this.setFont(processing);
    }

    public void setFont(PApplet processing){
        this.fonts = new PFont[2];
        this.fonts[0] = processing.createFont("ClassyVogue.ttf", midaTitol);
        this.fonts[1] = processing.createFont("AppleGaramond-Light.ttf", midaTextNormal);
    }

    public int getNumFonts(){
        return this.fonts.length;
    }

    public PFont getFontNormal(){
        return this.fonts[1];
    }

    public PFont getFontTitol(){return this.fonts[0];}

    public PFont getFontChoose(int i){
        return this.fonts[i];
    }

    public void displayFonts(PApplet processing, float x, float y, float height){
        processing.pushStyle();
        for(int i = 0; i>getNumFonts(); i++){
            processing.fill(0); processing.stroke(0); processing.strokeWeight(4);
            processing.textFont(getFontChoose(i));
            processing.text("Font: "+i, x, y + i*height);
        }
        processing.popStyle();
    }
}

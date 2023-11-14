package botonsAPP;

import processing.core.PApplet;
public class ButtonWords {

    //Propietats del botó

    float x, y, w, h;
    int fillColor;
    int fillColorOver, fillColorOff;
    String textButton;
    boolean enabled; //estat del botó, encés o apagat

    public ButtonWords(PApplet processing, String text, float x, float y, float w, float h){
        this.textButton = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColor = processing.color(0xFFF4562A);
        this.fillColorOver = processing.color(0xFF8E8E90);
        this.fillColorOff = processing.color(0xFF435360);
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public void setTextButton(String text){
        this.textButton = text;
    }

    public void setColors(int fillColor, int fillColorOn, int fillColorOff){
        this.fillColor = fillColor;
        this.fillColorOver = fillColorOn;
        this.fillColorOff = fillColorOff;
    }

    public void display(PApplet processing){
        processing.pushStyle();
        if(!enabled){
            processing.fill(fillColorOff);
        } else if(mouseIntoButton(processing)){
            processing.fill(fillColorOver);
            cursorHandMode(processing);
        } else{
            processing.fill(fillColor);
        }

        processing.strokeWeight(2);
        processing.rect(this.x, this.y, this.w, this.h, 10);

        //Text
        processing.fill(0); processing.textAlign(processing.CENTER);
        processing.textSize(16);
        processing.text(textButton, this.x + this.w/2, this.y + this.h/2);
        processing.popStyle();
    }

    public boolean mouseIntoButton(PApplet processing){
        return(processing.mouseX >= this.x) && (processing.mouseX <= this.x + this.w) &&
                (processing.mouseY >= this.y) && (processing.mouseY <= this.y + this.h);
    }

    //Ens diu si cal posar el cursor a HAND
public void cursorHandMode(PApplet processing){
        if(mouseIntoButton(processing) && enabled){
            processing.cursor(processing.HAND);
        }
}

}

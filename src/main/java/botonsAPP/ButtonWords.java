package botonsAPP;

import processing.core.PApplet;
public class ButtonWords {

    //Propietats del botó

    float x, y, w, h, angle;
    int fillColor;
    int fillColorOver, fillColorOff;
    String textButton;
    boolean ences; //estat del botó, encés o apagat

    public ButtonWords(PApplet processing, String text, float x, float y, float w, float h, float angle){
        this.textButton = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.angle = angle;
        this.ences = true;
        this.fillColor = processing.color(244, 93, 1);
        this.fillColorOver = processing.color(67, 83, 96);
        this.fillColorOff = processing.color(244, 93, 1);
    }

    public void setEnces(boolean ences){
        this.ences = ences;
    }

    public void setTextButton(String text){
        this.textButton = text;
    }

    public void setFillColor(int c){this.fillColor = c;}
    public void setFillColorOver(int c){this.fillColorOver = c;}

    public void display(PApplet processing){
        processing.pushStyle();
        if(!ences){
            processing.fill(fillColorOff);
        } else if(mouseIntoButton(processing)){
            processing.fill(fillColorOver);
        } else{
            processing.fill(fillColor);
        }

        processing.strokeWeight(2);
        processing.rect(this.x, this.y, this.w, this.h, angle);

        //Text
        processing.fill(0); processing.textAlign(processing.CENTER);
        processing.textSize(16);
        processing.text(textButton, this.x + this.w / 2, this.y + this.h / 2);
        processing.popStyle();
    }

    public boolean mouseIntoButton(PApplet processing){
        if(processing.mouseX >= this.x && processing.mouseX <= this.x + this.w &&
                processing.mouseY >= this.y && processing.mouseY <= this.y + this.h && ences){
            return true;
        } else {
            return false;
        }
    }

    //Ens diu si cal posar el cursor a HAND


public void cursorArrowMode(PApplet processing){
        processing.cursor(processing.ARROW);
}

}



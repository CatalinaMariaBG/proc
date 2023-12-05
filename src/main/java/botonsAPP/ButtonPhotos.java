package botonsAPP;

import processing.core.PApplet;
import processing.core.PImage;

public class ButtonPhotos {

    float x, y, r;

    int fillColor, strokeColor, fillColorInto;

    PImage imatge;

    public boolean ences;

    public ButtonPhotos(PApplet processing, PImage imatge, float x, float y, float r) {
        this.imatge = imatge;
        this.x = x;
        this.y = y;
        this.r = r;
        this.fillColor = processing.color(67, 83, 96);
        this.strokeColor = processing.color(0);
        this.fillColorInto = processing.color(0xFFDBD9D1);
        this.ences = true;
    }

    public void setColors(int cFill, int cStroke, int cOver) {
        this.fillColor = cFill;
        this.strokeColor = cStroke;
        this.fillColorInto = cOver;
    }

    public void display(PApplet processing) {
        processing.pushStyle();
        if (mouseIntoButton(processing)) {
                processing.fill(fillColorInto);
                cursorHandMode(processing);
            } else {
                processing.fill(fillColor);
            }
            processing.stroke(strokeColor);
            processing.strokeWeight(3);
            processing.imageMode(processing.CENTER);
            processing.ellipseMode(processing.CENTER);
            processing.ellipse(this.x, this.y, this.r, this.r);
            processing.imageMode(processing.CENTER);
            processing.image(imatge, this.x, this.y, this.r, this.r);
        processing.popStyle();
    }

    public void setEnces(boolean b) {
        this.ences = b;
    }

    public boolean mouseIntoButton(PApplet processing) {
        return processing.dist(processing.mouseX, processing.mouseY, this.x, this.y) <= this.r && ences;
    }

    public void cursorHandMode(PApplet processing) {
        if(mouseIntoButton(processing) && ences){
            processing.cursor(processing.HAND);
        }
    }
}


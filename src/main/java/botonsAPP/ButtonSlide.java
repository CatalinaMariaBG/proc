package botonsAPP;

import processing.core.PApplet;

public class ButtonSlide {
    String s;
    float x, y, w, h;
    float minV, maxV, value;
    int color;

    public ButtonSlide(PApplet processing, String s, float x, float y, float w, float h, float minV, float maxV, float v){
        this.s = s;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.minV = minV;
        this.maxV = maxV;
        this.value = v;
        this.color = processing.color(0xFFDBD9D1);
    }

    public float getValue(){
        return this.value;
    }

    public void setcolor(int c){
        this.color = c;
    }

    public boolean mouseIntoSlide(PApplet processing){
        return((processing.mouseX>x) && (processing.mouseX<x+w) && (processing.mouseY>=y) && (processing.mouseY<=y+h));
    }

    public void updateSlide(PApplet processing){
        value = PApplet.map(processing.mouseX, x, x+w, minV, maxV);
        value = PApplet.constrain(value, minV, maxV);
    }

    public void checkSlider(PApplet processing){
        if(mouseIntoSlide(processing)){
            this.updateSlide(processing);
        }
    }

    public void display(PApplet processing){
        processing.pushStyle();
        processing.fill(color);
        processing.rect(x, y, w, h, 10);
        processing.fill(67, 83, 96);
        processing.stroke(0); processing.strokeWeight(2);
        processing.rect(x + PApplet.map(value, minV, maxV, 0, w - 15), y + 20, 15, h - 20);
        processing.fill(0); processing.textAlign(processing.CENTER);
        processing.text(s, x + w/2, y + 15);
        processing.line(x, y+20, x+w, y+20);
        processing.popStyle();
    }
}

package funcionsAPP;

import processing.core.PApplet;
import sun.swing.StringUIClientPropertyKey;

public class SelectedColorRect {
    float x, y, w, h;
    int titleColor;
    boolean selected;

    String[] colors;

    public SelectedColorRect(PApplet processing, int title, float x, float y, float w, float h){
        this.x = x; this.y = y; this.w=w; this.h=h;
        this.titleColor = title;
        this.selected = false;
    }

    public void setSelected(boolean b){
        this.selected = b;
    }

    public boolean isSelected(){
        return this.selected;
    }

    public int getTitleColor(){
        return this.titleColor;
    }

    public boolean mouseIntoRect(PApplet processing){
        return processing.mouseX>= this.x && processing.mouseX<= this.x+this.w && processing.mouseY>=this.y && processing.mouseY<=this.y+this.h;
    }

    public void display(PApplet processing){
        processing.pushStyle();
        processing.fill(titleColor);
        processing.rect(x, y, w, h);
        if(selected){
processing.noFill(); processing.stroke(0); processing.strokeWeight(3);
processing.rect(x+2, y+2, w - 4, h - 4);

processing.fill(67, 83, 96); processing.noStroke();
processing.ellipse(x+w/2, y+h/2, 10, 10);
        }
    }
}

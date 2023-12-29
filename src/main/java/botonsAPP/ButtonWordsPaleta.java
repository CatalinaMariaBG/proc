package botonsAPP;

import processing.core.PApplet;

public class ButtonWordsPaleta extends ButtonWords{
    boolean selected; //per la paleta de colors
    public ButtonWordsPaleta(PApplet processing, String text, float x, float y, float w, float h, float angle, String mode) {
        super(processing, text, x, y, w, h, angle, mode);
        this.selected = false;
    }

    public void setSelected(boolean b){
        this.selected = b;
    }

    public boolean isSelected(){
        return this.selected;
    }

    /*public void display(PApplet processing){
        if(selected){
            processing.noFill(); processing.stroke(200, 100, 100); processing.strokeWeight(4);
            processing.rect(x+2, y+2, w-4, h-4);
            processing.fill(0); processing.ellipseMode(processing.CENTER);
            processing.ellipse(x+w/2, y+h/2,10, 10);
        }
    }*/
}
package botonsAPP;

import processing.core.PApplet;

public class ButtonOptions {

    float x, y, w, h;
    String[] texts;
    String selectedValue;

    boolean collapsed;

    float lineSpace = 15;

    public ButtonOptions(String[] texts, float x, float y, float w, float h){
        this.texts = texts;
        this.selectedValue = " ";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.collapsed = true;
    }

    public boolean isCollapsed(){
        return this.collapsed;
    }

    public String getSelectedValue(){
        return this.selectedValue;
    }

    public void display(PApplet processing){
        
    }
}

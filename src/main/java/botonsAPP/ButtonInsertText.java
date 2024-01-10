package botonsAPP;

import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

public class ButtonInsertText {
    int x, y, w, h;
    int colorRect, colorText, colorSelected, colorBorder;
    int weightBorder = 1;
    public String text = "";
    public String textoEstatico;
    int textSize;
    boolean selected = false;

    public ButtonInsertText(PApplet processing, int x, int y, int width, int height, String t, int textSize){
        this.x = x; this.y = y; this.w = width; this.h = height;
        this.colorRect = processing.color(219, 217, 209);
        this.colorText = processing.color(0);
        this.colorSelected = processing.color(255, 255, 255);
        this.colorBorder = processing.color(0);
        this.text = t;
        this.textoEstatico= t;
        this.textSize = textSize;
    }

    public void display(PApplet processing){
    processing.pushStyle();
        if(mouseIntoTextRect(processing)){
            processing.fill(colorSelected);
        } else{
            processing.fill(colorRect);
        }

        processing.rectMode(processing.CENTER);
        processing.strokeWeight(weightBorder);
        processing.stroke(colorBorder);
        processing.rect(x, y, w, h, 10);

        processing.fill(colorText);
        processing.textSize(textSize);
        //el primer per la x i el CENTER per la y
        processing.textAlign(processing.LEFT, processing.CENTER);
        processing.text(text, x - w /2 + 5, y);
    processing.popStyle();
    }

    public void keyPressed(char key, int keyCode){
        if(selected){
            if(keyCode == (int)BACKSPACE && text.length()>textoEstatico.length()) {
                removeText();
            } else if (keyCode == 32){ //32 és l'espai
                addText(' '); // espai
            } else {
                addText(key);
            }
        }
    }

    public void removeText(){
        if (text.length() > 0){
            text = text.substring(0, text.length() - 1);
        }
    }

    public void addText(char c){
if(this.text.length() < w){
    this.text += c;
    // si la llargada del text no supera l'amplada del
    //botó hi afegim la lletra que la lletra que hem passat
}
    }

    public boolean mouseIntoTextRect(PApplet processing){
        return (processing.mouseX >= this.x-this.w && processing.mouseX <= this.x + this.w
        && processing.mouseY >= this.y- this.h && processing.mouseY <= this.y + this.h);
    }

    public void pressedTrue(PApplet processing){
        if(mouseIntoTextRect(processing)){
            selected = true;
        } else{
            selected = false;
        }
    }

}
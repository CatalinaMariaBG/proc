package botonsAPP;

import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

public class ButtonInsertText {
    int x, y, width, height;

    int colorRect, colorText, colorSelected, colorBorder;
    int weightBorder = 2;

   public String text = "";
    int textSize = 16;

    boolean selected = false;

    public ButtonInsertText(PApplet processing, int x, int y, int width, int height){
        this.x = x; this.y = y; this.width = width; this.height = height;
        this.colorRect = processing.color(219, 217, 209);
        this.colorText = processing.color(0);
        this.colorSelected = processing.color(255, 255, 255);
        this.colorBorder = processing.color(0);
    }

    public void display(PApplet processing){
        processing.pushStyle();
        processing.rectMode(processing.CENTER);
        if(selected){
            processing.fill(colorSelected);
        } else{
            processing.fill(colorRect);
        }
        processing.strokeWeight(weightBorder);
        processing.stroke(colorBorder);
        processing.rect(x, y, width, height, 10);

        processing.fill(colorText);
        processing.textSize(textSize);
        //el primer per la x i el CENTER per la y
        processing.textAlign(processing.CENTER);
        processing.text(text, x - 150, y);
        processing.popStyle();
    }

    public void keyPressed(char key, int keyCode){
        if(selected){
            if(keyCode == (int)BACKSPACE) {
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
if(this.text.length() + 1 < width){
    this.text += c;
    // si la llargada del text no supera l'amplada del
    //botó hi afegim la lletra que la lletra que hem passat
}
    }

    public boolean mouseIntoTextRect(PApplet processing){
        return (processing.mouseX >= this.x && processing.mouseX <= this.x + this.width
        && processing.mouseY >= this.y && processing.mouseY <= this.y + this.height);
    }

    public void pressedTrue(PApplet processing){
        if(mouseIntoTextRect(processing)){
            selected = true;
        } else{
            selected = false;
        }
    }

}

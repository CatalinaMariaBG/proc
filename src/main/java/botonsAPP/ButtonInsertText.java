package botonsAPP;
import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;
public class ButtonInsertText {

        // Propietats del camp de text
        int x, y, h, w;

        // Colors
        int bgColor, fgColor, selectedColor, borderColor;
        int borderWeight = 1;

        // Text del camp
        public String text = "";
        int textSize = 24;

        boolean selected = false;

        // Constructor
        public ButtonInsertText(PApplet processing, int x, int y, int w, int h) {
            this.x = x; this.y = y; this.w = w; this.h = h;
            this.bgColor = processing.color(140, 140, 140);
            this.fgColor = processing.color(0, 0, 0);
            this.selectedColor = processing.color(190, 190, 60);
            this.borderColor = processing.color(30, 30, 30);
            this.borderWeight = 1;
        }

        // Dibuixa el Camp de Text
        public void display(PApplet processing) {
            processing.pushStyle();
            if (selected) {
                processing.fill(selectedColor);
            } else {
                processing.fill(bgColor);
            }

            processing.strokeWeight(borderWeight);
            processing.stroke(borderColor);
            processing.rect(x, y, w, h, 5);

            processing.fill(fgColor);
            processing.textSize(textSize); processing.textAlign(processing.LEFT, processing.CENTER);
            processing.text(text, x + 5, y + h - textSize);
            processing.popStyle();
        }

        // Afegeix, lleva el text que es tecleja
        public void keyPressed(char key, int keyCode) {
            if (selected) {
                if (keyCode == (int)BACKSPACE) {
                    removeText();
                } else if (keyCode == 32) {
                    addText(' '); // SPACE
                } else {

                    boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                    boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                    boolean isKeyNumber = (key >= '0' && key <= '9');

                    if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                        addText(key);
                    }
                }
            }
        }

        // Afegeix la lletra c al final del text
        public void addText(char c) {
            if (this.text.length() + 1 < w) {
                this.text += c;
            }
        }

        // Lleva la darrera lletra del text
        public void removeText() {
            if (text.length() > 0) {
                text = text.substring(0, text.length() - 1);
            }
        }

        // Lleva tot el text
        public void removeAllText(){
            this.text = "";
        }

        // Retorna el text
        public String getText(){
            return this.text;
        }

        // Setter del text
        public void setText(String t){
            this.text= t;
        }

        // Indica si el ratolí està sobre el camp de text
        public boolean mouseIntoTextRect(PApplet processing) {
            return (processing.mouseX >= this.x && processing.mouseX <= this.x + this.w && processing.mouseY >= this.y && processing.mouseY <= this.y + this.h);
        }

        // Selecciona el camp de text si pitjam a sobre
        // Deselecciona el camp de text si pitjam a fora
        public void pressedTrue(PApplet processing) {
            if (mouseIntoTextRect(processing)) {
                selected = true;
            } else {
                selected = false;
            }
        }
}

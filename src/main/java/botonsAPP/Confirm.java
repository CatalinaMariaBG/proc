package botonsAPP;

import processing.core.PApplet;

public class Confirm {

    float x, y, w, h;
    String title, message;

    public ButtonWords bAceptar, bCancelar;
    int buttonW = 200; int buttonH = 40;
    boolean visible = false;

    public Confirm(PApplet processing, String title, String message, float x, float y, float w, float h){
        this.title = title;
        this.message = message;
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.bAceptar = new ButtonWords(processing, "Aceptar", (x + w/4 - buttonW/2), (y + h - buttonH*1.5f), buttonW, buttonH, 10, "CORNER");
        this.bCancelar = new ButtonWords(processing, "Cancelar", (x + 3*w/4 - buttonW/2), (y + h - buttonH*1.5f), buttonW, buttonH, 10, "CORNER");
    }
    public void setTextButtons(String txt1, String txt2){
        this.bAceptar.textButton = txt1;
        this.bCancelar.textButton = txt2;
    }

    public void setTexts(String title, String message){
        this.title = title;
        this.message = message;
    }

    public void setVisible(boolean b){
        this.visible = b;
        if(!this.visible){
            this.bAceptar.setEnces(false);
            this.bCancelar.setEnces(false);
        }
        else {
            this.bAceptar.setEnces(true);
           this.bCancelar.setEnces(true);
        }
    }

    public void display(PApplet processing){

        if(this.visible){
            float b = 40;

            processing.pushStyle();

            // Rectangle
            processing.fill(0xFF8E8E90);
            processing.rect(x, y, w, h, b/2);
            processing.stroke(0); processing.strokeWeight(3);
            processing.line(x, y + 2*b , x+w, y + 2*b);

            // Títol
            processing.fill(0); processing.textSize(20); processing.textAlign(processing.LEFT);
            processing.text(title, x + b, y + 1.4f*b);

            // Missatge
            processing.fill(0); processing.textSize(20); processing.textAlign(processing.CENTER);
            processing.text(message, x + w/2, y + 4*b);

            // Botons d'Acceptar i Cancelar
            bAceptar.display(processing);
            bCancelar.display(processing);
            processing.popStyle();
        }
    }



}

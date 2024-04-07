package botonsAPP;

import processing.core.PApplet;

public class ButtonSelect {
    public float x, y, w, h;
    String[] texts;
    public String valorSelected, textDefault;

    public boolean plegat;
    public boolean ences;

    float lineSpace = 15;
    int fillColor;

   public ButtonSelect(String[] texts, float x, float y, float w, float h, String tDefault){
        this.texts = texts;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.ences = true;
        this.plegat = true;
        this.textDefault = tDefault;
        this.valorSelected= tDefault;
        this.fillColor = 0xFFDBD9D1;
    }

    public void setPlegat(boolean p){
        this.plegat = p;
    }

    public void setFillColor(int c){
       fillColor = c;
    }

    public void setEnces(boolean b){this.ences = b;}

    public void conmutar(){
this.plegat = !this.plegat;
    }

   public int clickedOption(PApplet processing){
        int i = (int)processing.map(processing.mouseY, y + h, y+h+(h+lineSpace)* texts.length, 0, texts.length);
        return i;
   }

   public void update(PApplet processing){
        int option = clickedOption(processing);
        if(option!=-1) {
            valorSelected = texts[option];
        }
   }

  public void display(PApplet processing){
        processing.pushStyle();
        processing.fill(fillColor);
        processing.rect(x, y, w, h, 10);

        processing.fill(67, 83, 96);
        processing.rect(x + w - 30, y, 30, h);

        processing.fill(0xFFDBD9D1); processing.stroke(0); processing.strokeWeight(2);
        processing.triangle(x + w - 25, y+5, x + w - 15, y + 25, x + w - 5 , y+5);

      processing.fill(0); processing.textSize(20); processing.textAlign(processing.CENTER); processing.noStroke();
        if(valorSelected!=null){
            processing.text(valorSelected, x + w/2, y + h/2);
        } else{
            processing.text(textDefault, x + w/2, y + h/2);
        }

        if(!this.plegat){
            processing.fill(67, 83, 96); processing.stroke(0);
            processing.rect(x, y+h, w, (h+lineSpace)*texts.length);

            for(int i = 0; i < texts.length; i++){
                if(i == clickedOption(processing)){
                    processing.fill(0xFF8E8E90); processing.noStroke();
                    processing.rect(x+4, y+4 + h + (h + lineSpace)*i - 2, w -8, h + lineSpace - 8);
                }
                processing.fill(0);
                processing.text(texts[i], x + w/2, y + h + 25 + (h + lineSpace)*i);
            }
        }
        processing.popStyle();
   }

   public boolean mouseIntoSelect(PApplet processing){
       if(this.plegat){
           return (processing.mouseX >= x) &&
                   (processing.mouseX <= x + w) &&
                   (processing.mouseY >= y) &&
                   (processing.mouseY <= y + h);
       } else{
           return (processing.mouseX >= x) &&
                   (processing.mouseX <= x + w) &&
                   (processing.mouseY >= y) &&
                   (processing.mouseY <= y + h + (h + lineSpace)* texts.length);
       }
   }
}
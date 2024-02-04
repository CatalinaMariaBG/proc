package botonsAPP;

import java.util.ArrayList;
import processing.core.PApplet;

public class TextList {
    float x, y, w, h;
    String [][] texts;
    ButtonInsertText textField;
    int filaSelected;
    String idSelected;
    String valueSelected;
    boolean ences;
    int numMatchs = 0;
    ArrayList<ButtonWords> buttons;
    public TextList(PApplet processing, String[][] texts, float x, float y, float w, float h){
        this.texts = texts;
        this.idSelected = "";
        this.valueSelected = "";
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.ences = true;

        this.textField = new ButtonInsertText(processing, (int) x, (int) y, (int) w, (int) h, "", 16);
        this.buttons = new ArrayList<ButtonWords>();
    }

    public String getValueSelected(){
        return this.valueSelected;
    }
    public ButtonInsertText getTextField(){
        return this.textField;
    }
    public boolean mouseIntoButton(PApplet processing){
        for(ButtonWords b : buttons){
            if(b.mouseIntoButton(processing)){
                return true;
            }
        }
        return false;
    }

    public void buttonPressed(PApplet processing){
        boolean pressed = false;
        for(ButtonWords b : buttons){
            if(b.mouseIntoButton(processing)){
                textField.text = b.textButton;
                this.valueSelected = b.textButton;
                pressed = true;
            }
        }
        if(pressed){
            buttons.clear();
        }
    }

    public void display(PApplet processing){
        processing.pushStyle();
        textField.display(processing);

        for(ButtonWords b : buttons){
            b.display(processing);
        }
        processing.popStyle();
    }

    public void update(PApplet processing){
        String searchFor = this.textField.text;
        this.numMatchs = 0;
        this.buttons = new ArrayList<ButtonWords>();

        if(searchFor.length() > 0){
            for(int i = 0; i<texts.length; i++){
                if(texts[i][1].startsWith(searchFor)){
                    ButtonWords b = new ButtonWords(processing, texts[i][1], x + 10, y + h + 50 + (h + 50)*numMatchs, w, h, 10, "CENTER");
                    buttons.add(b);
                    this.numMatchs++;
                    if(this.numMatchs == 5){
                        break;
                    }
                }
            }
        }
    }
}

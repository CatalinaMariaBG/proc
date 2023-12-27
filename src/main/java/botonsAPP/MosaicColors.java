package botonsAPP;

import funcionsAPP.SelectedColorRect;
import processing.core.PApplet;

public class MosaicColors{

    int x, y, w, h;

    int numFiles, numColumnes;
    boolean colorSelected = false;
    int selectedColor;

    SelectedColorRect[] selColor;

    int[] colors;

    public MosaicColors(int x, int y, int w, int h, int numFiles, int numColumnes){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.numFiles = numFiles;
        this.numColumnes = numColumnes;
    }

    public void setColors(PApplet processing, int[] colors){
        this.colors = colors;
selColor = new SelectedColorRect[this.colors.length];

    }

    public void createSelectColor(PApplet processing){
        float rectWidth = w / numColumnes;
        float rectHeight = h / numFiles;

        int numRect = 0;
        for(int nf = 0; nf<numFiles; nf ++){
            for(int nc =0; nc<numColumnes; nc++){
                if(numRect>=colors.length){
                    break;
                } else {
                    int titleColor = colors[numRect];
                    float xRect = x + rectWidth*nc;
                    float yRect = y + rectHeight*nf;

                    selColor[numRect] = new SelectedColorRect(processing, titleColor, xRect, yRect, rectWidth, rectHeight);

                    numRect++;
                }
            }
        }
    }

    public void deselectALL(){
        for(SelectedColorRect s : selColor){
            if(s!=null){
                s.setSelected(false);
            }
        }
    }

    public void checkColor(PApplet processing){
        for(SelectedColorRect s : selColor){
            if((s!=null)&& s.mouseIntoRect(processing)){
                boolean prevState = s.isSelected();
                deselectALL();
                s.setSelected(!prevState);
                if(s.isSelected()){
                    colorSelected = true;
                    selectedColor = s.getTitleColor();
                } else {
                    colorSelected = false;
                }
            }
        }
    }

    public void display(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF8E8E90); processing.noStroke(); processing.rectMode(processing.CENTER);
        processing.rect(x, y, w, h, 10);

        for(SelectedColorRect s : selColor){
            if(s!= null){
                s.display(processing);
            }

            if(colorSelected){
                processing.fill(0); processing.textAlign(processing.CENTER); processing.textSize(14);
                processing.text("Color: "+selectedColor, x+w/2, y + h + 30);
            }
        }
        processing.popStyle();
    }
}

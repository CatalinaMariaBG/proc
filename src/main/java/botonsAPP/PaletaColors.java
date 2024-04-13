package botonsAPP;

import processing.core.PApplet;

import setupAPP.Setup;

public class PaletaColors {

    int x, y, w, h, numColumns, numFiles, colorW, colorH, colorX, colorY;
    ButtonWordsPaleta b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
    ButtonWordsPaleta[] b;

    int selectedColor;
    boolean selected;
    public PaletaColors(PApplet processing, int x, int y, int w, int h, int nc, int nf){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.numColumns = nc;
        this.numFiles = nf;
        colorX = x + 10;
        colorY = y + 10;
        b = new ButtonWordsPaleta[]{b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20};
        createSelectColorRect(processing);
    }

    public void createSelectColorRect(PApplet processing){
        colorW = (w)/numColumns;
        colorH = (h)/numFiles;

        int numColor = 0;
        for(int nf = 0; nf<numFiles; nf++){
            for(int nc=0; nc<numColumns; nc++){
                if(numColor>=b.length){
                    break;
                }
                else {
                    float xColorRect = x + colorW*nc;
                    float yColorRect = y + colorH*nf;
                    b[numColor]  = new ButtonWordsPaleta(processing, "", xColorRect, yColorRect, colorW, colorH, 0, "CORNER");

                    numColor ++;
                }
            }
        }
    }
    public void select(PApplet processing){
        for(ButtonWordsPaleta bw : b){
            if((bw!= null) && (bw.mouseIntoButton(processing)) && processing.mousePressed){
boolean prevState = bw.isSelected();
deselectAll();
bw.setSelected(!prevState);
                if(bw.isSelected()){
                    selected = true;
                    selectedColor = bw.getFillColor(processing);
                } else {
                    selected = false;
                }
            }
        }
    }

    public void deselectAll(){
        for(ButtonWordsPaleta bw :b){
            if(bw!=null){
                bw.setSelected(false);
            }
        }
    }

public void display(PApplet processing){
        processing.rectMode(processing.CORNER); processing.stroke(0); processing.strokeWeight(2); processing.fill(219, 217, 209);
        processing.rect(x, y, w, h);
    for (int numColor = 0; numColor<b.length; numColor++) {
        if (b[numColor] != null) {
            if (numColor == 0) {
                b[numColor].setFillColor(processing.color(192, 57, 43));
            } else if (numColor == 1) {
                b[numColor].setFillColor(processing.color(231, 76, 60));
            } else if (numColor == 2) {
                b[numColor].setFillColor(processing.color(155, 89, 182));
            } else if (numColor == 3) {
                b[numColor].setFillColor(processing.color(142, 68, 173));
            } else if (numColor == 4) {
                b[numColor].setFillColor(processing.color(41, 128, 185));
            } else if (numColor == 5) {
                b[numColor].setFillColor(processing.color(52, 152, 219));
            } else if (numColor == 6) {
                b[numColor].setFillColor(processing.color(26, 188, 156));
            } else if (numColor == 7) {
                b[numColor].setFillColor(processing.color(22, 160, 133));
            } else if (numColor == 8) {
                b[numColor].setFillColor(processing.color(39, 174, 96));
            } else if (numColor == 9) {
                b[numColor].setFillColor(processing.color(46, 204, 113));
            } else if (numColor == 10) {
                b[numColor].setFillColor(processing.color(241, 196, 15));
            } else if (numColor == 11) {
                b[numColor].setFillColor(processing.color(243, 156, 18));
            } else if (numColor == 12) {
                b[numColor].setFillColor(processing.color(230, 126, 34));
            } else if (numColor == 13) {
                b[numColor].setFillColor(processing.color(211, 84, 0));
            } else if (numColor == 14) {
                b[numColor].setFillColor(processing.color(236, 240, 241));
            } else if (numColor == 15) {
                b[numColor].setFillColor(processing.color(189, 195, 199));
            } else if (numColor == 16) {
                b[numColor].setFillColor(processing.color(149, 165, 166));
            } else if (numColor == 17) {
                b[numColor].setFillColor(processing.color(127, 140, 141));
            } else if (numColor == 18) {
                b[numColor].setFillColor(processing.color(52, 73, 94));
            } else if (numColor == 19) {
                b[numColor].setFillColor(processing.color(44, 62, 80));
            }
            b[numColor].setFillColorOver(b[numColor].fillColor);
            b[numColor].display(processing);

        if (selected) {
            Setup.colour = selectedColor;
        }
        }
    }
    select(processing);
}
}

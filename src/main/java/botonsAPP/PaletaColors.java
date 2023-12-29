package botonsAPP;

import processing.core.PApplet;

public class PaletaColors {

    int x, y, w, h, numColumns, numFiles, colorW, colorH, colorX, colorY;
    ButtonWordsPaleta b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
    ButtonWordsPaleta[] b;

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
        b = new ButtonWordsPaleta[]{b0, b1, b2, b3, b4, b5, b6/*, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20*/};
        createSelectColorRect(processing);
    }

    public void createSelectColorRect(PApplet processing){
        colorW = (w)/numColumns;
        colorH = (h-60)/numFiles;

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
                    b[numColor]  = new ButtonWordsPaleta(processing, "", xColorRect, yColorRect, colorW, colorH, 0, "CORNER");

                    numColor ++;
                }
            }
        }
    }
    public void select(PApplet processing){
        for(ButtonWordsPaleta bw : b){
            if((bw!= null) && (bw.mouseIntoButton(processing))){

                if(processing.mousePressed){
                    selected = true;
                }
            }
        }
    }

public void display(PApplet processing){
        processing.rectMode(processing.CORNER); processing.stroke(0); processing.strokeWeight(2); processing.fill(219, 217, 209);
        processing.rect(x, y, w, h);
    for(int i = 0; i<b.length; i++){
        if(b[i]!= null) {
            if(i== 0){
                b[i].setFillColor(processing.color(192, 57, 43));
            } else if(i == 1){
                b[i].setFillColor(processing.color(231, 76, 60));
            } else if(i == 2){
                b[i].setFillColor(processing.color(155, 89, 182));
            } else if(i == 3){
                b[i].setFillColor(processing.color(142, 68, 173));
            } else if(i == 4){
                b[i].setFillColor(processing.color(41, 128, 185));
            } else if(i == 5){
                b[i].setFillColor(processing.color(52, 152, 219));
            } else if(i == 6){
                b[i].setFillColor(processing.color(26, 188, 156));
            } else if(i == 7){
                b[i].setFillColor(processing.color(22, 160, 133));
            } else if(i == 8){
                b[i].setFillColor(processing.color(39, 174, 96));
            } else if(i == 9){
                b[i].setFillColor(processing.color(46, 204, 113));
            } else if(i == 10){
                b[i].setFillColor(processing.color(241, 196, 15));
            } else if(i == 11){
                b[i].setFillColor(processing.color(243, 156, 18));
            } else if(i == 12){
                b[i].setFillColor(processing.color(230, 126, 34));
            } else if(i == 13){
                b[i].setFillColor(processing.color(211, 84, 0));
            } else if(i == 14){
                b[i].setFillColor(processing.color(236, 240, 241));
            } else if(i == 15){
                b[i].setFillColor(processing.color(189, 195, 199));
            } else if(i == 16){
                b[i].setFillColor(processing.color(149, 165, 166));
            } else if(i == 17){
                b[i].setFillColor(processing.color(127, 140, 141));
            } else if(i == 18){
                b[i].setFillColor(processing.color(52, 73, 94));
            } else if(i == 19){
                b[i].setFillColor(processing.color(44, 62, 80));
            }
            b[i].setFillColorOver(b[i].fillColor);
            b[i].display(processing);
        }
    }
    select(processing);
}
}

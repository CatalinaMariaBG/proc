package botonsAPP;

import processing.core.PApplet;

public class PaletaColors {

    int x, y, w, h, numColumns, numFiles, colorW, colorH, colorX, colorY;
    ButtonWords b0, b1, b2, b3, b4, b5, b6/*, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20*/;
    ButtonWords[] b;
    public PaletaColors(PApplet processing, int x, int y, int w, int h, int nc, int nf){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.numColumns = nc;
        this.numFiles = nf;
        colorX = x + 10;
        colorY = y + 10;
        b = new ButtonWords[]{b0, b1, b2, b3, b4, b5, b6/*, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20*/};
        createSelectColorRect(processing);
    }

    public void createSelectColorRect(PApplet processing){
        colorW = w/numColumns;
        colorH = h/numFiles;

        int numColor = 0;
        for(int nf = 0; nf<numFiles; nf++){
            for(int nc=0; nc<numColumns; nc++){
                if(numColor>=b.length){
                    break;
                }
                else {
                    float xColorRect = x + colorW*nc;
                    float yColorRect = y + colorH*nf;

                    b[numColor]  = new ButtonWords(processing, "", xColorRect, yColorRect, colorW, colorH, 0, "CORNER");

                    numColor ++;
                }
            }
        }
    }

public void display(PApplet processing){
        processing.rectMode(processing.CORNER); processing.stroke(0); processing.strokeWeight(2); processing.fill(219, 217, 209);
        processing.rect(x, y, w, h, 10);
    for(int i = 0; i<b.length; i++){
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
        } else {
            b[i].setFillColor(processing.color(26, 188, 156));
        }
        b[i].setFillColorOver(b[i].fillColor);
        for(ButtonWords bw : b){
            if(bw != null){
                b[i].display(processing);
            }
        }
    }
}
}
/*else if(i == 7){
            processing.fill(22, 160, 133);
        } else if(i == 9){
            processing.fill(39, 174, 96);
        } else if(i == 10){
            processing.fill(46, 204, 113);
        } else if(i == 11){
            processing.fill(241, 196, 15);
        } else if(i == 12){
            processing.fill(243, 156, 18);
        } else if(i == 13){
            processing.fill(230, 126, 34);
        } else if(i == 14){
            processing.fill(211, 84, 0);
        } else if(i == 15){
            processing.fill(236, 240, 241);
        } else if(i == 16){
            processing.fill(189, 195, 199);
        } else if(i == 17){
            processing.fill(149, 165, 166);
        } else if(i == 18){
            processing.fill(127, 140, 141);
        } else if(i == 19){
            processing.fill(52, 73, 94);
        } else if(i == 20){
            processing.fill(44, 62, 80);
        } */
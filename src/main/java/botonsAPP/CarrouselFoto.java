package botonsAPP;

import funcionsAPP.Timer;
import processing.core.PApplet;
import processing.core.PImage;
public class CarrouselFoto {

    float x, y, w, h;
    float imgW;
    float margeH = 15;

    float numImatgesTotal, numImatgesVisible;

    int imatgeActual;

    String[] noms;

    PImage[] imatges;

    ButtonPhotos bAnterior, bPost;

    Timer timer;

    public CarrouselFoto(int x, int y, float w, int h, int visibles){
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.numImatgesVisible = visibles;
        this.imatgeActual = 0;
        this.imgW = (w - margeH*(visibles-1)) / (float) visibles;
    }

    public void setImatges(PApplet processing, String[] noms){
        this.noms = noms;
        this.numImatgesTotal = noms.length;
        this.imatges = new PImage[noms.length];
        for(int i = 0; i<imatges.length; i++){
            this.imatges[i] = processing.loadImage(noms[i]);
        }
    }

    public void setButtons(PApplet processing, String img1, String img2){
        PImage imgAnt = processing.loadImage(img1);
        bAnterior = new ButtonPhotos(processing, imgAnt, x, y + h/2, 40);

        PImage imgPost = processing.loadImage(img2);
        bPost = new ButtonPhotos(processing, imgPost, x + w, y + h/2, 40);
    }

    public void setTimer(PApplet processing, int numberSecondsToChange){
        this.timer = new Timer(processing, numberSecondsToChange);
    }

    public void setStart(PApplet processing){
        this.timer.start(processing);
    }

    public void seguent(){
        if(this.imatgeActual < this.numImatgesTotal - this.numImatgesVisible){
            this.imatgeActual++;
        }
    }

    public void anterior(){
        if(this.imatgeActual > 0){
            this.imatgeActual--;
        }
    }

    public boolean chekButtons(PApplet processing){
        if(bPost.mouseIntoButton(processing) && bPost.ences){
            this.seguent();
            return true;
        } else if( bAnterior.mouseIntoButton(processing) && bAnterior.ences){
            this.anterior();
            return true;
        } else {
            return false;
        }
    }

    public boolean checkCursor(PApplet processing) {
        if (bPost.mouseIntoButton(processing) && bPost.ences) {
            return true;
        } else if (bAnterior.mouseIntoButton(processing) && bAnterior.ences) {
            return true;
        }
        return false;
    }

    public boolean checkKey(PApplet processing){
        if(processing.keyCode == processing.RIGHT){
            seguent();
            return true;
        } else if(processing.keyCode == processing.LEFT){
            anterior();
            return true;
        } else {
            return false;
        }
    }

    public void checkTimer(PApplet processing){
        if(timer.timeOver()){
            seguent();
        }
        timer.update(processing);
    }

    public void display(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF8E8E90); processing.noStroke();
        processing.rect(x-5, y-5, w + 10, h + 10, 10);
        for(int i = 0; i<this.numImatgesVisible; i++){
            if(imatgeActual == noms.length-1){
                i = 0;
                imatgeActual = 0;
            }
            int contador = i + this.imatgeActual;
            float xPos = x + i*(this.imgW + this.margeH);

            //Imatge a mostrar
            PImage imatge = imatges[contador];
            processing.image(imatge, xPos, y, this.imgW, h);

            //Títol de la imatge
            processing.textAlign(processing.CENTER); processing.textSize(24);
            processing.text(noms[contador], xPos + this.imgW/2, y + h + 50);

            if(bPost != null){
                bPost.display(processing);
            }
            if(bAnterior != null){
                bAnterior.display(processing);
            }
        }
        processing.popStyle();
    }

}

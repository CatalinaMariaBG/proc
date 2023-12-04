package botonsAPP;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.concurrent.TimeUnit;
public class CarrouselFoto {

    float x, y, w, h;
    float imgW;
    float margeH = 15;

    float numImatgesTotal, numImatgesVisible;

    int imatgeActual;

    String[] noms;

    PImage[] imatges;

    ButtonPhotos bAnterior, bPost;

    public CarrouselFoto(int x, int y, float w, int h, int visibles){
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.numImatgesVisible = visibles;
        this.imatgeActual = 0;
        this.imgW = (w - margeH*(visibles-1)) / (float) visibles;
    }

    public void setImatges(PApplet processing, String[] n){
        this.noms = n;
        this.numImatgesTotal = this.noms.length;
        this.imatges = new PImage[this.noms.length];
        for(int i = 0; i<imatges.length; i++){
            imatges[i] = processing.loadImage(this.noms[i]);
        }
    }

    public void setButtons(PApplet processing, String img1, String img2){
        PImage imgAnt = processing.loadImage(img1);
        bAnterior = new ButtonPhotos(processing, imgAnt, x, y + h/2, 30);

        PImage imgPost = processing.loadImage(img2);
        bPost = new ButtonPhotos(processing, imgPost, x + w, y + h/2, 30);
    }

    public void seguent(){
        if(this.imatgeActual<this.numImatgesTotal - this.numImatgesVisible){
            this.imatgeActual++;
        }
    }

    public void anterior(){
        if(this.imatgeActual>0){
            this.imatgeActual--;
        }
    }

    public void chekButtons(PApplet processing){
        if(bPost.mouseIntoButton(processing)&& bPost.ences){
            this.seguent();
        } else if(bAnterior.mouseIntoButton(processing) && bAnterior.ences){
            this.anterior();
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
    public void display(PApplet processing){
        processing.pushStyle();
        processing.fill(0); processing.noStroke();
        processing.rect(x-5, y-5, w + 10, h + 10);

        for(int i = 0; i<this.numImatgesVisible; i++){
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

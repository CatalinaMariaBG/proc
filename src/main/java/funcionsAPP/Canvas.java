package funcionsAPP;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Canvas {

    int x, y, w, h; //Posición y dimensiones
    PImage[] imgs;//Array de imágenes
    int numImatge = 0;//Contador de imágenes cargadas

    //Variable de las distribuciones para añadir imágenes
    public enum DISTRIBUCIO{UNXUN, UNXDOS, DOSXDOS, TRESXDOS};

    //Distribución por defecto
    DISTRIBUCIO distribucio = DISTRIBUCIO.TRESXDOS;
    PGraphics canvas;//Clase de Processing para dibujar fuera de una area común

    //Constructor
    public Canvas(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.imgs = new PImage[6];
    }

    //Setters
    public void setDistribucio (DISTRIBUCIO d){
        this.distribucio = d;
    }

    //Getters
    public String getDistribucio(){
        return this.distribucio.toString();
    }

    //Añadir Imagen
    public void addImage(PApplet processing, PImage img){
        if(numImatge<imgs.length){
            imgs[numImatge] = img;
            numImatge ++;
updateCanvas(processing, imgs, distribucio);
        }
    }

    //Cursor dentro de Canvas
    public boolean mouseOver(PApplet processing){
        return processing.mouseX>= x && processing.mouseY>= y && processing.mouseX<= x+w && processing.mouseY<=y+h;
    }

    //Donde cargar las imágenes
    public void updateCanvas(PApplet processing, PImage[] imgs, DISTRIBUCIO dist){
        canvas = processing.createGraphics(w, h);
        canvas.beginDraw();
        canvas.background(0xFFDBD9D1);
        for(int i = 0; i<imgs.length; i++) {
            if (imgs[i] != null){
                if (dist == DISTRIBUCIO.UNXUN) {
                    int f = 0;
                    int c = 0;
                    canvas.image(imgs[i], c, f, w, h);
                } else if(dist == DISTRIBUCIO.UNXDOS){
                    int f = 0;
                    int c = i;
                    float wh = w/2;
                    canvas.image(imgs[i], wh*c, f, wh, h);
                } else if(dist == DISTRIBUCIO.DOSXDOS){
                    int f = i/2;
                    int c = i%2;
                    float wh = w/2;
                    float hw = h/2;
                    canvas.image(imgs[i], wh*c, hw*f, wh, hw);

                } else if(dist == DISTRIBUCIO.TRESXDOS){
                    int f = i/3;
                    int c = i%2;
                    float wh = w/3;
                    float hw = h/2;
                    canvas.image(imgs[i], wh*c, hw*f, wh, hw);
                }
        }
        }
        canvas.endDraw();
    }

    //Borrar el Canvas
    public void resetCanvas(){
        imgs = new PImage[6];
        canvas = null;
        numImatge = 0;
    }

    //Dibujar el Canvas
    public void display(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFFDBD9D1);
        processing.rect(x, y, w, h, 10);
        processing.imageMode(processing.CORNER);
        if(canvas!= null){
            processing.image(canvas, x, y, w, h);
        }
        processing.popStyle();
    }

}
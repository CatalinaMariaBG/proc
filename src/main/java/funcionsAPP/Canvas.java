package funcionsAPP;

import botonsAPP.ButtonInsertText;
import botonsAPP.ButtonWords;
import botonsAPP.Pin;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;

public class Canvas {
    int x, y, w, h; //Posición y dimensiones
    PImage[] imgs;//Array de imágenes
    int numImatge = 0;//Contador de imágenes cargadas

    //Variable de las distribuciones para añadir imágenes
    public enum DISTRIBUCIO{UNXUN, UNXDOS, DOSXDOS, TRESXDOS};

    //Distribución por defecto
    DISTRIBUCIO distribucio = DISTRIBUCIO.TRESXDOS;
    public PGraphics canvas;//Clase de Processing para dibujar fuera de una area común
    int c;

    //Constructor
    public Canvas(PApplet processing, int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.imgs = new PImage[6];
        this.c = processing.color(0xFFDBD9D1);
    }

    //Setters
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setW(int w){
        this.w = w;
    }
    public void setH(int h){
        this.h = h;
    }
    public void setDistribucio (DISTRIBUCIO d){
        this.distribucio = d;
    }
    public void setColor(int color){
        this.c = color;
    }

    //Getters
    public String getDistribucio(){
        return this.distribucio.toString();
    }
    public PGraphics getCanvas(){
        return this.canvas;
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
        canvas.background(this.c);
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
        processing.fill(this.c);
        processing.rect(x, y, w, h, 10);
        processing.imageMode(processing.CORNER);
        if(canvas!= null){
            processing.image(canvas, x, y, w, h);
        }
        processing.popStyle();
    }

}
package botonsAPP;

import processing.core.PApplet;
import processing.core.PImage;
import setupAPP.Setup;

import static java.lang.Float.parseFloat;

public class Edifici {
public ButtonWords b;
    PImage img;
    public String nom, estil, material, tipologia;
    float lat, lng;
    float longMin, longMax, latMin, latMax;
    float r = 10;

    public Edifici(PApplet processing, String n, float lat, float lng, String img) {
        this.nom = n;
        this.lat = lat;
        this.lng = lng;
        this.img = processing.loadImage(img);
    }

    public Edifici(PApplet processing, String[] info, String[] img){
        this.nom = info[1];
        Setup.edificio = nom;
        this.lng = parseFloat(info[2]);
        this.lat = parseFloat(info[3]);
        this.img = processing.loadImage(img[0]);
        this.longMin = 0.49761425F;
        this.longMax = 4.89452897F;
        this.latMin = 38.54582099F;
        this.latMax = 40.167229F;
    }

    public Edifici(PApplet processing, String[] info){
        this.nom = info[1];
        Setup.edificio = nom;
        this.lng = parseFloat(info[2]);
        this.lat = parseFloat(info[3]);
        this.img = processing.loadImage(info[4]);
        this.longMin = 0.49761425F;
        this.longMax = 4.89452897F;
        this.latMin = 38.54582099F;
        this.latMax = 40.167229F;
    }

    public void setImg(PImage img){
        this.img = img;
    }

    public void displayLloc(PApplet processing, float x, float y, float w, float h){
        processing.pushStyle();
        float posLong = PApplet.map(this.lng, longMin, longMax, x,x+w);
        float posLat = PApplet.map(this.lat, latMax, latMin, y, y+h);

        processing.fill(0xFFF4562A);
        if(this.mouseIntoLloc(processing, x, y, w, h)){
            processing.fill(0xFFFFA21F);
        }

        processing.ellipse(posLong, posLat, r, r);
        processing.popStyle();
    }

    public void displayInfo(PApplet processing, float x, float y, float w, float h){
        processing.pushStyle();
        processing.fill(0xFF8E8E90);
        processing.rect(x, y, w, h, 5);
        processing.image(this.img, x+5, y+5, w-10, w-10);

        processing.fill(0); processing.textSize(18); processing.textAlign(processing.CENTER);
        b = new ButtonWords(processing, this.nom, x + w/2, y + w + 35, w, 30, 10, "CENTER");
        b.setFillColor(processing.color(0xFF8E8E90));
        b.setFillColorOver(processing.color(67, 83, 96));
        b.display(processing);
        processing.popStyle();
    }

    public boolean mouseIntoLloc(PApplet processing, float x, float y, float w, float h){
        float posLong = PApplet.map(this.lng, longMin, longMax, x,x+w);
        float posLat = PApplet.map(this.lat, latMax, latMin, y, y + h);
        return PApplet.dist(processing.mouseX, processing.mouseY, posLong, posLat) < r/2;
    }


}

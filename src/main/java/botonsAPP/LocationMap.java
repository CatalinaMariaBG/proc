package botonsAPP;

import processing.core.PApplet;
import processing.core.PImage;

import static java.lang.Float.parseFloat;

public class LocationMap {

    PImage img;
    String nom;
    String localitat;
    float lat, lng;

    float r = 5;

    public LocationMap(PApplet processing, String n, String l, float lat, float lng, String img){
        this.nom = n;
        this.localitat = l;
        this.lat = lat;
        this.lng = lng;
        this.img = processing.loadImage(img);
    }

    public LocationMap(PApplet processing, String[] info){
        this.nom = info[0];
        this.localitat = info[1];
        this.lng = parseFloat(info[2]);
        this.lat = parseFloat(info[3]);
        this.img = processing.loadImage(info[4]);
    }

    public void displayLloc(PApplet processing, float x, float y, float w, float h){
        processing.pushStyle();
        float posLong = PApplet.map(this.lng, -180, 180, x,x+w);
        float posLat = PApplet.map(this.lat, 90, -90, y, y+h);

        processing.fill(0xFFF4562A);
        if(this.mouseIntoLloc(processing, x, y, w, h)){
            processing.fill(0xFFFFA21F);
        }

        processing.ellipse(posLong, posLat, r, r);
        processing.fill(0); processing.textSize(16); processing.textAlign(processing.CENTER);
        processing.text(this.nom, posLong, posLat - r);
        processing.popStyle();
    }

    public void displayInfo(PApplet processing, float x, float y, float w, float h){
        processing.pushStyle();
        processing.fill(0xFF8E8E90);
        processing.rect(x, y, w, h, 5);
        processing.image(this.img, x+5, y+5, w-10, w-10);

        processing.fill(0); processing.textSize(18); processing.textAlign(processing.CENTER);
        processing.text(this.nom+" ("+this.localitat+")", x + w/2, y + w + 50);
        processing.popStyle();
    }

    public boolean mouseIntoLloc(PApplet processing, float x, float y, float w, float h){
        float posLong = processing.map(this.lng, -180, 180, x,x+w);
        float posLat = processing.map(this.lat, 90, -90, y, y + h);
        return processing.dist(processing.mouseX, processing.mouseY, posLong, posLat) < r/2;
    }


}

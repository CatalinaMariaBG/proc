package botonsAPP;

import processing.core.PApplet;
import setupAPP.Setup;

public class LocationSetter {

    Edifici[] edificis;

    public LocationSetter(PApplet processing, String[][] info, String[][] img){
        edificis = new Edifici[info.length];
        for(int i = 0; i<info.length; i++){
            edificis[i] = new Edifici(processing, info[i], img[i]);
        }
    }

    public LocationSetter(PApplet processing, String[][] info){
        edificis = new Edifici[info.length];
        for(int i = 0; i<info.length; i++){
            edificis[i] = new Edifici(processing, info[i], Setup.xSecondMiddle + 10, Setup.ySecondMiddle + 10, 200, 260);
        }
        processing.println("EDIFICIS CREATS "+edificis.length);
    }

    public Edifici getLlocAt(int index){
        return this.edificis[index];
    }

    public boolean mouseIntoLocations(PApplet processing, float x, float y, float w, float h){
        for(int i= 0; i<edificis.length; i++){
            if(edificis[i].mouseIntoLloc(processing, x, y, w, h)){
                return true;
            }
        }
        return false;
    }

    public int getSelect(PApplet processing, float x, float y, float w, float h){
        for(int i = 0; i< edificis.length; i++){
            if(edificis[i].mouseIntoLloc(processing, x, y, w, h)){
                return i;
            }
        }
        return -1;
    }

    public void display(PApplet processing, float x, float y, float w, float h){
        for(int i = 0; i<edificis.length; i++){
            edificis[i].displayLloc(processing, x, y, w, h);
        }
    }

    public boolean BuildInfo(PApplet processing) {
        if (mouseIntoLocations(processing, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500)) {
            for (int i = 0; i < edificis.length; i++) {
                if (edificis[i].b.mouseIntoButton(processing)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}

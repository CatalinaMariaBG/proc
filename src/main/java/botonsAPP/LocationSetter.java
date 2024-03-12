package botonsAPP;

import processing.core.PApplet;
import setupAPP.Setup;

public class LocationSetter {

    LocationMap[] edificis;

    public LocationSetter(PApplet processing, String[][] info){
        edificis = new LocationMap[Setup.numLlocsMapa];
        for(int i = 0; i<Setup.numLlocsMapa; i++){
            edificis[i] = new LocationMap(processing, info[i]);
        }
    }

    public LocationMap getLlocAt(int index){
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
}

package funcionsAPP;

import processing.core.PApplet;
import zonesAPP.GUI;

public class Timer {
    int numSecondsToChange;
    int numSeconds;
    int lastSecond;

    boolean start;
    int stop;

    public Timer(PApplet processing, int numberS){
        this.numSecondsToChange = numberS;
        this.numSeconds = 0;
        this.lastSecond = processing.millis();
        this.start = false;
    }

    public void setStart(boolean s){
        this.start = s;
    }

    public void start (PApplet processing){
        this.lastSecond = processing.millis();
        this.start = true;
    }
    public void update(PApplet processing){
        if(start) {
            int now = processing.millis();
            numSeconds = (now - lastSecond) / 1000;
            if (timeOver()) {
                resetTimer(processing);
            }
        }
    }

    public void resetTimer(PApplet processing){
        lastSecond = processing.millis();
    }

    public boolean timeOver(){
        return (numSeconds >= numSecondsToChange);
    }
}
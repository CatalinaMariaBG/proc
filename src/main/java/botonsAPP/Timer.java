package botonsAPP;

import processing.core.PApplet;
import zonesAPP.GUI;

public class Timer {

    GUI gui;
    int numSecondsToChange;
    int numSeconds;
    int lastSecond;

    public Timer(PApplet processing, int numberS){
        this.numSecondsToChange = numberS;
        this.numSeconds = 0;
        this.lastSecond = processing.millis();
    }

    public void update(PApplet processing){
        int now = processing.millis();
        numSeconds = (now - lastSecond) /1000;
        if(timeOver()){
            resetTimer(processing);
        }
    }

    public void resetTimer(PApplet processing){
        lastSecond = processing.millis();
    }

    public boolean timeOver(){
        return (numSeconds >= numSecondsToChange);
    }

    public void stopTimer(PApplet processing) {
processing.delay(1000);
    }
}


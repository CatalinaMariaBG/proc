package botonsAPP;

import processing.core.PApplet;
import zonesAPP.GUI;

public class Timer {

    GUI gui;
    int numSecondsToChange;
    int numSeconds;
    int lastSecond;

    int numberSecondsToChangeFirst;

    public Timer(PApplet processing, int numberS, int numberSF){
        this.numSecondsToChange = numberS;
        this.numSeconds = 0;
        this.lastSecond = processing.millis();
        this.numberSecondsToChangeFirst = numberSF;
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
}

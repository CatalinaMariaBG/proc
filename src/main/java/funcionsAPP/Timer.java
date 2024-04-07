package funcionsAPP;

import processing.core.PApplet;
import zonesAPP.GUI;

public class Timer {
    int numSecondsToChange; //Cada cuando se debe cambiar la imagen
    int numSeconds;//Contador del tiempo transcurrido
    int lastSecond;//Tiempo transcurrido en total
    boolean start;//Saber si se debe empezar a contar
    int stop;

    //Constructor
    public Timer(PApplet processing, int numberS){
        this.numSecondsToChange = numberS;
        this.numSeconds = 0;
        this.lastSecond = processing.millis();
        this.start = false;
    }

    //Setter
    public void setStart(boolean s){
        this.start = s;
    }

    //Inicializar el contador
    public void start (PApplet processing){
        this.lastSecond = processing.millis();
        this.start = true;
    }

    //Actualizar el contador
    public void update(PApplet processing){
        if(start) {
            int now = processing.millis();
            numSeconds = (now - lastSecond) / 1000;
            if (timeOver()) {
                resetTimer(processing);
            }
        }
    }

    //Resetear el tiempo para que vuelva a 0
    public void resetTimer(PApplet processing){
        lastSecond = processing.millis();
    }

    //Se ha llegado al tiempo del cambio
    public boolean timeOver(){
        return (numSeconds >= numSecondsToChange);
    }
}




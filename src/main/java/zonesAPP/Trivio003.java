package zonesAPP;

import processing.core.PApplet;
import setupAPP.Setup;

public class Trivio003 extends PApplet {

    GUI gui;

    public static void main(String[] args){
        PApplet.main("zonesAPP.Trivio003", args);
    }

    public void settings(){
       fullScreen();
        smooth(10);
        System.out.println(width);
    }

    public void setup(){
        noStroke();
        textAlign(CENTER); textSize(16);
        gui = new GUI();
    }

    public void draw() {

        switch (gui.screenActual) {
            case INICIAL:  gui.drawInicial(this);
            break;
            case LOGIN: gui.drawLogin(this);
            break;
            case MYACCOUNT: gui.drawMyAccount(this);
            break;
            case MAP: gui.drawMap(this);
                break;
            case BUILDING: gui.drawBuilding(this);
                break;
            case NEWBUILDING: gui.drawNewBuilding(this);
                break;
            case ARCHIVE: gui.drawArchive(this);
                break;
            case CREATE: gui.drawCreate(this);
                break;
            case SAVECREATION: gui.drawSaveCreation(this);
                break;
        }
    }

    //Keyboard interaction

    public void keyPressed(){
        if(key=='0'){
            gui.screenActual = GUI.SCREEN.INICIAL;
        } else if (key == '1'){
            gui.screenActual = GUI.SCREEN.LOGIN;
        } else if(key == '2'){
            gui.screenActual = GUI.SCREEN.MYACCOUNT;
        }else if(key == '3'){
            gui.screenActual = GUI.SCREEN.MAP;
        }else if(key == '4'){
            gui.screenActual = GUI.SCREEN.BUILDING;
        }else if(key == '5'){
            gui.screenActual = GUI.SCREEN.NEWBUILDING;
        }else if(key == '6'){
            gui.screenActual = GUI.SCREEN.CREATE;
        }else if(key == '7'){
            gui.screenActual = GUI.SCREEN.SAVECREATION;
        }else if(key == '8'){
            gui.screenActual = GUI.SCREEN.ARCHIVE;
        }
    }

    //MOUSE INTERACTION
    public void mousePressed(){
        println("X: "+mouseX+", Y:"+mouseY);
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }
}

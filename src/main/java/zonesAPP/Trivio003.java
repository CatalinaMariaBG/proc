package zonesAPP;

import processing.core.PApplet;
import botonsAPP.CarrouselFoto;

public class Trivio003 extends PApplet {

    GUI gui;
    CarrouselFoto c;


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
        gui = new GUI(this);
    }

    public void draw() {

        switch (gui.screenActual) {
            case LOGIN:  gui.drawLogin(this);
            break;
            case INICIAL: gui.drawInicial(this);
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

        //Botons TextField LogIn
        /*gui.bName.text = "USER NAME: ";
        gui.bPassword.text = "PASSWORD: ";*/
    }

    //Keyboard interaction

    public void keyPressed(){
        if(key=='0'){
            gui.screenActual = GUI.SCREEN.LOGIN;
        } else if (key == '1'){
            gui.screenActual = GUI.SCREEN.INICIAL;
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
        //Botons TextField
        if(gui.screenActual == GUI.SCREEN.LOGIN) {
            gui.bName.keyPressed(key, (int) keyCode);
            gui.bPassword.keyPressed(key, keyCode);
        }
    }

    //MOUSE INTERACTION
    public void mousePressed(){

        if(gui.screenActual == GUI.SCREEN.LOGIN){
           if(gui.bName.mouseIntoTextRect(this)){
                gui.bName.pressedTrue(this);
            } else if(gui.bPassword.mouseIntoTextRect(this)){
                gui.bPassword.pressedTrue(this);
            }
        } else if(gui.screenActual == GUI.SCREEN.INICIAL){
            if(gui.b1.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.CREATE;
            } else if(gui.b2.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MAP;
            } else if(gui.b3.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.ARCHIVE;
            } else if(gui.b4.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.NEWBUILDING;
            } else if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.MYACCOUNT){
             if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.MAP){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.BUILDING){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.NEWBUILDING){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.CREATE){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.SAVECREATION){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.CREATEFULLSCREEN){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        } else if(gui.screenActual == GUI.SCREEN.ARCHIVE){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        }

            if(gui.bLogo.mouseIntoButton(this)){
                gui.menuOpen = true;
            } else if(gui.bLateralBar.mouseIntoButton(this)){
                gui.menuOpen = false;
            }

            cursorHandMode(this);
            comprovaLogin();

            gui.bName.pressedTrue(this);
        }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void cursorHandMode(PApplet processing){
        processing.cursor(processing.HAND);
    }

    public void comprovaLogin(){
if(gui.bEnterAccount.mouseIntoButton(this) && gui.bName.text.equals("Name: joan") && gui.bPassword.text.equals("Password: joan")){
    gui.screenActual = GUI.SCREEN.INICIAL;
    gui.bName.text = gui.bName.textoEstatico;
    gui.bPassword.text = gui.bPassword.textoEstatico;
}
    }



    }


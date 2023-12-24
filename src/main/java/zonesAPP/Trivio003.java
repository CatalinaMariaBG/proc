package zonesAPP;

import botonsAPP.ButtonSelect;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public class Trivio003 extends PApplet {

    GUI gui;
    int standardSize = 10;
    int colour = 0;
    int plus, minus, xLine, yLine;

    PImage imageAddedCreate;
    String titolFoto = "";


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
            case CREATEFULLSCREEN: gui.drawCreateFullScreen(this);
                break;
        }

        if(gui.screenActual == GUI.SCREEN.INICIAL){
            if(!gui.c.checkKey(this) || !gui.c.chekButtons(this)){
                gui.c.checkTimer(this);
            }

        } else if(gui.screenActual == GUI.SCREEN.CREATE){
            if(mousePressed){
                updateDraw(gui.selectDraw);
            } else if(imageAddedCreate !=null){
                //image(imageAddedCreate)
            }
        }
    }

    //Keyboard interaction

    public void keyPressed(){
        //Botons TextField
        if(gui.screenActual == GUI.SCREEN.LOGIN) {
            gui.bName.keyPressed(key, (int) keyCode);
            gui.bPassword.keyPressed(key, keyCode);
        } else if(gui.screenActual == GUI.SCREEN.INICIAL){
            gui.c.checkKey(this);
        }
        if(gui.screenActual == GUI.SCREEN.LOGIN && keyCode == '1'){
            gui.screenActual = GUI.SCREEN.INICIAL;
        }
        if(gui.screenActual == GUI.SCREEN.MAP){

        }
    }

    //MOUSE INTERACTION
    public void mousePressed(){

        if(gui.menuOpen){
            if(gui.bLateralBar.mouseIntoButton(this)){
                gui.menuOpen = false;
            } else if(gui.bCreate.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.CREATE;
                gui.menuOpen = false;
            } else if(gui.bMap.mouseIntoButton(this)) {
                gui.screenActual = GUI.SCREEN.MAP;
                gui.menuOpen = false;
            } else if(gui.bArchive.mouseIntoButton(this)) {
                gui.screenActual = GUI.SCREEN.ARCHIVE;
                gui.menuOpen = false;
            } else if(gui.bNewBuilding.mouseIntoButton(this)) {
                gui.screenActual = GUI.SCREEN.NEWBUILDING;
                gui.menuOpen = false;
            } else if(gui.bInici.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.INICIAL;
                gui.menuOpen = false;
                gui.c.setStart(this);
            }
        }

        if(gui.screenActual == GUI.SCREEN.LOGIN){
            comprovaLogin();
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
            gui.c.chekButtons(this);
            gui.c.checkCursor(this);

        } else if(gui.screenActual == GUI.SCREEN.MYACCOUNT){
             if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            } else if(gui.bNProjects.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.ARCHIVE;
            } else if(gui.bLogOut.mouseIntoButton(this)){
               gui.screenActual = GUI.SCREEN.LOGIN;
               gui.bName.text = gui.bName.textoEstatico;
               gui.bPassword.text = gui.bPassword.textoEstatico;
            }

        } else if(gui.screenActual == GUI.SCREEN.MAP){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            } else if(gui.bAddBuild.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.NEWBUILDING;
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
            } else if(gui.bFullCreate.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.CREATEFULLSCREEN;
            } else if(gui.bSaveC.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.SAVECREATION;
            } else if(gui.selectDraw.mouseIntoSelect(this) && gui.selectDraw.ences){
                if(!gui.selectDraw.plegat){
                    gui.selectDraw.update(this);
                }
                gui.selectDraw.conmutar();
            } else if(gui.bAddImage.mouseIntoButton(this)){
                selectInput("Selecciona una imatge...", "fileSelected");
            } else if(gui.selectPlantilla.mouseIntoSelect(this) && gui.selectPlantilla.ences){
                if(!gui.selectPlantilla.plegat){
                    gui.selectPlantilla.update(this);
                }
                gui.selectPlantilla.conmutar();
            }

        } else if(gui.screenActual == GUI.SCREEN.SAVECREATION){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }

        } else if(gui.screenActual == GUI.SCREEN.CREATEFULLSCREEN){
            if(gui.bMenosCreate.mouseIntoButton(this)) {
                gui.screenActual = GUI.SCREEN.CREATE;
            } else if(gui.bSaveCfull.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.SAVECREATION;
            }

        } else if(gui.screenActual == GUI.SCREEN.ARCHIVE){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            }
        }

            if(gui.bLogo.mouseIntoButton(this)){
                gui.menuOpen = true;
            }
            if(gui.bLateralBar.mouseIntoButton(this)){
                gui.menuOpen = false;
            }

            cursorHandMode(this);
            //BOTONS TEXTFIELD
            gui.bName.pressedTrue(this);
            gui.bPassword.pressedTrue(this);

        }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseClicked(){
xLine = mouseX;
yLine = mouseY;
    }

    public void cursorHandMode(PApplet processing){
        processing.cursor(processing.HAND);
    }

    public void comprovaLogin(){
if(gui.bEnterAccount.mouseIntoButton(this) && gui.bName.text.equals("Name: catalina maria") && gui.bPassword.text.equals("Password: cccc")){
    gui.screenActual = GUI.SCREEN.INICIAL;
    gui.menuOpen = false;
    gui.c.setStart(this);
}
    }

    //CREATE
    public void updateDraw(ButtonSelect b){
        if(mouseIntoCreate(570, 350, 770, 500)){ //Coordenades del SecondMiddle
            if(b.valorSelected.equals("CERCLE")){
                cercle();
            }  else if(b.valorSelected.equals("QUADRAT")){
                quadrat();
            } else if(b.valorSelected.equals("LÍNIA")){
                line();
            }
        }
    }

    public void cercle(){
        fill(colour);
        ellipse(mouseX, mouseY, standardSize + plus - minus, standardSize + plus - minus);
    }
    public void quadrat(){
        fill(colour);
        rect(mouseX, mouseY, standardSize + plus - minus, standardSize + plus - minus);
    }
    public void line(){
        pushStyle();
        fill(colour);
        stroke(colour);
        strokeWeight(3 + plus - minus);
        line(xLine, yLine, mouseX, mouseY);
        popStyle();
    }

    public boolean mouseIntoCreate(float x, float y, float w, float h){
        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }

    //Carregar imatges
    public void fileSelected(File selection){
        if(selection == null){
            println("No s'ha seleccionat cap fitxer.");
        } else {
            String imageRuta = selection.getAbsolutePath();

            imageAddedCreate = loadImage(imageRuta);
            titolFoto = selection.getName();
        }
    }
    }
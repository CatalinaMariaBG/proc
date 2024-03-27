package zonesAPP;

import botonsAPP.ButtonTextoEstatico;
import botonsAPP.LocationSetter;
import processing.core.PApplet;
import setupAPP.Setup;
import bbdd.DataBase;

import java.io.File;
import java.util.Objects;

import botonsAPP.Pin;

public class Trivio003 extends PApplet {

    GUI gui;
    DataBase db;

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
        db = new DataBase("admin", "12345", "edificis");
        db.connect();
        gui = new GUI(this, db);

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
            gui.dibuix.beginDraw();
            if(gui.pinText !=null){
                for(int i = 0; i<gui.pinText.length; i++){
                    if(gui.pinText[i]!=null){
                        gui.pins[i].display(this);
                        gui.pinText[i].display(this);
                    }
                }
            }
            gui.dibuix.endDraw();
        }

    }

    //Keyboard interaction

    public void keyPressed(){
        //Botons TextField
        if(gui.screenActual == GUI.SCREEN.LOGIN) {
            gui.bName.keyPressed(key, keyCode);
            gui.bPassword.keyPressed(key, keyCode);
        } else if(gui.screenActual == GUI.SCREEN.INICIAL){
            gui.c.checkKey(this);
        }
        if(gui.screenActual == GUI.SCREEN.LOGIN && keyCode == '1'){
            gui.screenActual = GUI.SCREEN.INICIAL;
        }
        if(gui.screenActual == GUI.SCREEN.MAP){
            if(gui.listEstil.getTextField().mouseIntoTextRect(this)) {
                gui.listEstil.getTextField().keyPressed(key, (int) keyCode);
                gui.listEstil.update(this);
            } else if(gui.listTipologia.getTextField().mouseIntoTextRect(this)) {
                gui.listTipologia.getTextField().keyPressed(key, (int) keyCode);
                gui.listTipologia.update(this);
            } else if(gui.listMaterial.getTextField().mouseIntoTextRect(this)) {
                gui.listMaterial.getTextField().keyPressed(key, (int) keyCode);
                gui.listMaterial.update(this);
            }
        }
        if(gui.screenActual == GUI.SCREEN.NEWBUILDING){
            gui.bNameBuilding.keyPressed(key, keyCode);
            gui.bPosXBuilding.keyPressed(key, keyCode);
            gui.bPosYBuilding.keyPressed(key, keyCode);
            if(gui.buildEstil.getTextField().mouseIntoTextRect(this)) {
                gui.buildEstil.getTextField().keyPressed(key, (int) keyCode);
                gui.buildEstil.update(this);
            }else if(gui.buildTipologia.getTextField().mouseIntoTextRect(this)) {
                gui.buildTipologia.getTextField().keyPressed(key, (int) keyCode);
                gui.buildTipologia.update(this);
            } else if(gui.buildMaterial.getTextField().mouseIntoTextRect(this)) {
                gui.buildMaterial.getTextField().keyPressed(key, (int) keyCode);
                gui.buildMaterial.update(this);
            }
        }

        if(gui.screenActual == GUI.SCREEN.CREATE){
            for(int i = 0; i<gui.pinText.length; i++){
                if(gui.pinText[i]!=null){
                    gui.pinText[i].keyPressed(key, keyCode);
                }
            }
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
                resetMap();
            } else if(gui.bAddBuild.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.NEWBUILDING;
                resetMap();
            } else if(gui.menuOpen && gui.bInici.mouseIntoButton(this)){
                resetMap();
            } else if(gui.menuOpen && gui.bCreate.mouseIntoButton(this)){
                resetMap();
            } else if(gui.menuOpen && gui.bArchive.mouseIntoButton(this)){
                resetMap();
            } else if(gui.menuOpen && gui.bNewBuilding.mouseIntoButton(this)){
                resetMap();
            }
            if(gui.selectedLloc!=null && gui.selectedLloc.b.mouseIntoButton(this)){
                gui.nomBuildingInto = gui.selectedLloc.nom;
                gui.infoBuilding = db.getInfoTotalEdificios(gui.nomBuildingInto);
                for(int i = 0; i<gui.infoBuilding.length; i++) {
                    System.out.println(gui.infoBuilding[i]);
                }
                gui.nomImgBuildInto = db.getImagenEdificio(db.getIDEdificio(gui.nomBuildingInto));
                gui.screenActual = GUI.SCREEN.BUILDING;
            }
            int nl = gui.llocsMap.getSelect(this, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500);
            if(nl!=-1){
                gui.selectedLloc = gui.llocsMap.getLlocAt(nl);
            } else {
                gui.selectedLloc = null;
            }
            if(gui.bAplicaMap.mouseIntoButton(this)){
                String estilSelected = gui.listEstil.getValueSelected();
                String tipologiaSelected = gui.listTipologia.getValueSelected();
                String materialSelected = gui.listMaterial.getValueSelected();
                String[][] info = db.getMapaQualitats();
                for(int f = 0; f<info.length; f++){
                    if(!Objects.equals(estilSelected, "") && !Objects.equals(info[f][1], estilSelected)){
                        db.updateinfoMapa(Integer.parseInt(info[f][0]));
                    } else if(!Objects.equals(tipologiaSelected, "") && !Objects.equals(info[f][3], tipologiaSelected)){
                        db.updateinfoMapa(Integer.parseInt(info[f][0]));
                    } else if(!Objects.equals(materialSelected, "") && !Objects.equals(info[f][2], materialSelected)){
                        db.updateinfoMapa(Integer.parseInt(info[f][0]));
                    }
                }
                gui.info = db.getInfoMapaEdificios();
                gui.llocsMap = new LocationSetter(this, gui.info);
            }
            gui.listEstil.getTextField().pressedTrue(this);
            gui.listEstil.buttonPressed(this);

            gui.listTipologia.getTextField().pressedTrue(this);
            gui.listTipologia.buttonPressed(this);

            gui.listMaterial.getTextField().pressedTrue(this);
            gui.listMaterial.buttonPressed(this);

        } else if(gui.screenActual == GUI.SCREEN.BUILDING){
            if(gui.bAccount.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            } else if(gui.bReturnMap.mouseIntoButton(this)){
                gui.screenActual = GUI.SCREEN.MAP;
            }
        } else if(gui.screenActual == GUI.SCREEN.NEWBUILDING){
            if(gui.bAccount.mouseIntoButton(this) && gui.bAccount.ences){
                gui.screenActual = GUI.SCREEN.MYACCOUNT;
            } else if(gui.bNameBuilding.mouseIntoTextRect(this)){
                gui.bNameBuilding.pressedTrue(this);
            } else if(gui.bPosXBuilding.mouseIntoTextRect(this)){
                gui.bPosXBuilding.pressedTrue(this);
            }else if(gui.bPosYBuilding.mouseIntoTextRect(this)){
                gui.bPosYBuilding.pressedTrue(this);
            } else if(gui.bSaveBuild.mouseIntoButton(this) && gui.bSaveBuild.ences){
                gui.cNewBuild.setVisible(true);
            } else if(gui.cNewBuild.bAceptar.mouseIntoButton(this) && gui.cNewBuild.bAceptar.ences){
                String name = gui.bNameBuilding.getText();
                float x = Float.parseFloat(gui.bPosXBuilding.getText());
                float y = Float.parseFloat(gui.bPosYBuilding.getText());
                String user = gui.bName.getTextoEspecial2();
                String estil = gui.buildEstil.getValueSelected();
                String tipologia = gui.buildTipologia.getValueSelected();
                String material = gui.buildMaterial.getValueSelected();
                db.insertInfoTaulaEdifici(name, x, y, user, estil, material, tipologia);
                if(gui.imgNewBuild!=null) {
                    db.insertImageEdificio(name, gui.titolFoto);
                }
                gui.info = db.getInfoMapaEdificios();
                gui.llocsMap = new LocationSetter(this, gui.info);
                gui.cNewBuild.setVisible(false);
                gui.screenActual = GUI.SCREEN.MAP;
            } else if(gui.cNewBuild.bCancelar.mouseIntoButton(this) && gui.cNewBuild.bCancelar.ences){
                gui.cNewBuild.setVisible(false);
            } else if(gui.bAddImgNewBuild.mouseIntoButton(this)){
                selectInput("Seleccions una imatge...", "imageSelected");
            }
            gui.buildEstil.getTextField().pressedTrue(this);
            gui.buildEstil.buttonPressed(this);

            gui.buildTipologia.getTextField().pressedTrue(this);
            gui.buildTipologia.buttonPressed(this);

            gui.buildMaterial.getTextField().pressedTrue(this);
            gui.buildMaterial.buttonPressed(this);

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
            } else if(gui.bAddImage.mouseIntoButton(this) && gui.bAddImage.ences){
                selectInput("Selecciona una imatge ...", "fileSelected");
            } else if(gui.bErraseCreate.mouseIntoButton(this) && gui.bErraseCreate.ences){
                gui.dibuix = createGraphics(770, 500);
                gui.canvas.resetCanvas();
                // esborrar dibuix
                gui.pinText = new ButtonTextoEstatico[5];
            }
            else if(gui.selectPlantilla.mouseIntoSelect(this) && gui.selectPlantilla.ences){
                if(!gui.selectPlantilla.plegat){
                    gui.selectPlantilla.update(this);
                }
                gui.selectPlantilla.conmutar();
            } else if(gui.bSizeDraw.mouseIntoSlide(this)){
                gui.bSizeDraw.checkSlider(this);
            } else if(gui.bColorCreate.mouseIntoButton(this)){
                gui.paletaOpen = !gui.paletaOpen;
            } else if(gui.paletaOpen){
                if(gui.bColorPersonal.mouseIntoButton(this)){

                }
            } else if(gui.bRed.mouseIntoSlide(this) && gui.establishPersonalC){
                gui.bRed.checkSlider(this);
            } else if(gui.bPinCreate.mouseIntoButton(this) && gui.bPinCreate.ences){
                    for (int i = 0; i < gui.pinText.length; i++) {
                        if (gui.pinText[i] == null) {
                            gui.pins[i] = new Pin(this, gui.xPin, gui.yPin);
                            gui.pinText[i] = new ButtonTextoEstatico(this, (int) gui.xPin + (Setup.wButtonMap/2)/2 + 10, (int) gui.yPin - Setup.hButtonsMap/2, Setup.wButtonMap / 2, Setup.hButtonsMap, "", 10);
                            break;
                        }
                    }
            }
            for(int i = 0; i<gui.pinText.length; i++){
                if(gui.pinText[i] != null){
                    if(gui.pinText[i].mouseIntoTextRect(this)){
                        gui.pinText[i].pressedTrue(this);
                    }
                }
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
            gui.bNameBuilding.pressedTrue(this);
            gui.bPosXBuilding.pressedTrue(this);
            gui.bPosYBuilding.pressedTrue(this);
            gui.saveCreationName.pressedTrue(this);
            for(int i = 0; i<gui.pinText.length; i++){
                if(gui.pinText[i] != null){
                    gui.pinText[i].pressedTrue(this);
                }
            }
        }

        public void mouseDragged(){
        if(gui.screenActual == GUI.SCREEN.CREATE ){
            if(gui.bSizeDraw.mouseIntoSlide(this)){
                gui.bSizeDraw.checkSlider(this);
            } else if(gui.bRed.mouseIntoSlide(this) && gui.establishPersonalC){
                gui.bRed.checkSlider(this);
            } else if(gui.canvas.mouseOver(this)){
                gui.updateDraw(this, gui.selectDraw);
            }
        }
        }
    public void mouseReleased(){
        if(gui.canvas.mouseOver(this) && gui.screenActual == GUI.SCREEN.CREATE && gui.selectDraw.valorSelected == "LÍNIA"){
            if(gui.numPoints<2) {
                gui.xPoints[gui.numPoints] = mouseX;
                gui.yPoints[gui.numPoints] = mouseY;
                gui.numPoints++;
                System.out.println(gui.numPoints);
            }
            else {
                for(int i=0; i<2; i++) {
                    System.out.println(gui.xPoints[i]+" , " +gui.yPoints[i]);
                }
            }

        }
    }

    public void mouseClicked(){
        if(gui.canvas.mouseOver(this) && gui.screenActual == GUI.SCREEN.CREATE){
            gui.xPin = mouseX;
            gui.yPin = mouseY;
        }
    }

    public void cursorHandMode(PApplet processing){
        processing.cursor(processing.HAND);
    }

    public void comprovaLogin(){
        boolean entrar = false;
        String[][] usuaris= db.getUsuaris();
        for(int f = 0; f< usuaris.length; f++){
                if(gui.bName.text.equals("Name: "+usuaris[f][0]) && gui.bPassword.text.equals("Password: "+usuaris[f][1])){
                    entrar = true;
                    break;
                }
        }
if(gui.bEnterAccount.mouseIntoButton(this) && entrar){
    gui.screenActual = GUI.SCREEN.INICIAL;
    gui.menuOpen = false;
    gui.c.setStart(this);
}
    }


    //Carregar imatges
    public void fileSelected(File selection){
        if(selection == null){
            println("No s'ha seleccionat cap fitxer.");
        } else {
            String imageRuta = selection.getAbsolutePath();
            gui.lastImage = loadImage(imageRuta);
            gui.canvas.addImage(this, gui.lastImage);
        }
    }

    public void imageSelected(File selection){
        if(selection == null){
            println("No s'ha seleccionat cap fitxer.");
        } else {
            String imageRuta = selection.getAbsolutePath();
            gui.imgNewBuild = loadImage(imageRuta);
            gui.titolFoto = selection.getName();
        }
    }

    public void resetMap(){
        db.resetInfoMapa();
        gui.listEstil.setValueSelected("");
        gui.listTipologia.setValueSelected("");
        gui.listMaterial.setValueSelected("");
    }

    }
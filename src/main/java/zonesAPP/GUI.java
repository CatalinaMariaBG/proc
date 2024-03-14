package zonesAPP;

import bbdd.DataBase;
import botonsAPP.*;
import processing.core.PApplet;

import processing.core.PImage;

import setupAPP.Setup;

import java.io.File;
import java.util.Objects;

//mesures: 1440, 900
public class GUI {

    PImage imgAccount, imgFullCreate, imgMenosCreate, imageAddedCreate;

    ButtonWords b1, b2, b3, b4, bLogo, bEnterAccount, bLateralBar, bCreate, bMap, bArchive, bNewBuilding,
    bInici, bNProjects, bLogOut, bTlist, bAddBuild, bSaveC, bSaveCfull, bAddImage, bColorCreate, bColorPersonal, bPinCreate,
            bErraseCreate, bSaveBuild, bCopyImageBuild, bReturnMap, bImportImage, bCreateBuilding, bNewProject, bSaveCreation,
    bColorCreateFull, bErraseCreateFull, bAddImageFull, bPinFull, bCercarImage;

    ButtonPhotos bAccount, bFullCreate, bMenosCreate;

    ButtonInsertText bPassword, bName, bNameBuilding, bLocationBuilding, saveCreationName;

    public enum SCREEN{LOGIN, INICIAL, MYACCOUNT, MAP, BUILDING, NEWBUILDING, ARCHIVE, CREATE, SAVECREATION, CREATEFULLSCREEN};

    public SCREEN screenActual;

    CarrouselFoto c;
    String[] nomsCarrousel, tipusDibuix, tipusPlantilla, nombreQuadrat;

    TextList listEstil, listUse, listTipologia, listMaterial, buildEstil, buildUse, buildTipologia, buildMaterial;
    String[][] valuesEstil = {{"0", "tipografia"}, {"1", "ladrillo"}, {"2", "lalala"}, {"3", "berta guapaa"}, {"4", "titita"}};

    ButtonSelect selectDraw, selectPlantilla, selectArxiu, selectDrawFull, selectPlantillaFull, selectQuadratImage;

    LocationSetter llocsMap;
    LocationMap selectedLloc;
    String[][] info;

    int standardSize = 5;
    float xLine, yLine;

    String titolFoto;
    String[] imageEdificio;

    ButtonSlide bSizeDraw, bRed, bGreen, bBlue, bSizeDrawFull;

    PaletaColors colorsCreate;

boolean menuOpen = false;
boolean paletaOpen = false;
boolean establishPersonalC = false;
boolean selectQuadrat = false;

    public GUI(PApplet processing){

        screenActual = SCREEN.LOGIN;
        //INICIAL
        b1 = new ButtonWords(processing, "CREAR", 190, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b2 = new ButtonWords(processing, "MAPA", 490, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b3 = new ButtonWords(processing , "ARXIUS", 790, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b4 = new ButtonWords(processing, "NOU EDIFICI", 1090, Setup.yButtonInicial, 180, 80, 10, "CORNER");

       c = new CarrouselFoto(80, 310, processing.width - 2*Setup.logoW, 580, 2);
        nomsCarrousel = new String[]{ "auditori-de-manacor.jpg", "edificiCasasayas.jpeg","casalBalaguer.jpg", "MACE.jpg", "ciudadBlanca.jpg"};
        c.setImatges(processing, nomsCarrousel);
        c.setButtons(processing, "2048px-Back_Arrow.svg.png", "Next_Arrow.svg.png");
        c.setTimer(processing, 10);
        //GENERAL
        bLogo = new ButtonWords(processing, "LOGO", Setup.logoDistH, Setup.logoDistV, Setup.logoW, Setup.logoH, 0, "CORNER");
            bLogo.setFillColor(0xFFF4562A);
        imgAccount = processing.loadImage("data/userLogo.png");
        bAccount = new ButtonPhotos(processing, imgAccount, processing.width - Setup.logoDistH - Setup.logoW/2, Setup.logoDistV + Setup.logoH/2, Setup.logoW);

        //BOTONS LOGIN
        bEnterAccount = new ButtonWords(processing, "LOG IN", processing.width/2 - 150, processing.height/2 + 225, 300, 60, 10, "CORNER");
            bEnterAccount.setFillColor(0xFFF4562A);
            bEnterAccount.mouseIntoButton(processing);
        bName = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 50, 450, 60, "Name: ", 16);
        bPassword = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 150, 450, 60, "Password: ", 16);

        //BOTONS BARRA LATERAL
        bLateralBar = new ButtonWords(processing, "LOGO", 160 - Setup.logoW/2, Setup.logoDistV, Setup.logoW, Setup.logoH, 0, "CORNER");
            bLateralBar.mouseIntoButton(processing);
            bInici = new ButtonWords(processing, "INICI", Setup.edgeH, Setup.edgeV + Setup.logoW + 100, 280, 80, 10, "CORNER");
        bCreate = new ButtonWords(processing, "CREAR", Setup.edgeH, Setup.edgeV + Setup.logoW + 250 , 280, 80, 10, "CORNER");
        bMap = new ButtonWords(processing, "MAPA", Setup.edgeH, Setup.edgeV + Setup.logoW + 400, 280, 80, 10, "CORNER");
        bArchive = new ButtonWords(processing, "ARXIUS", Setup.edgeH, Setup.edgeV + Setup.logoW + 550, 280, 80, 10, "CORNER");
        bNewBuilding = new ButtonWords(processing, "NOU EDIFICI", Setup.edgeH, Setup.edgeV + Setup.logoW + 700, 280, 80, 10, "CORNER");

        //BOTONS CREATE
        imgFullCreate = processing.loadImage("data/fullScreen.png");
        bFullCreate = new ButtonPhotos(processing, imgFullCreate, Setup.fullScreenX, Setup.fullScreenY, Setup.fullScreenW);
        bFullCreate.setColors(255, 0,0xFFDBD9D1);
        imgMenosCreate = processing.loadImage("data/menosScreen.png");
        bMenosCreate = new ButtonPhotos(processing, imgMenosCreate, processing.width - 3* Setup.edgeH, processing.height - 3* Setup.edgeV, Setup.fullScreenW);
        bMenosCreate.setColors(255, 0,0xFFDBD9D1);
        bSaveC = new ButtonWords(processing, "GUARDAR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 820, 300, 30, 10, "CORNER");
        tipusDibuix = new String[]{"DIBUIXA","CERCLE", "QUADRAT", "LÍNIA"};
        selectDraw = new ButtonSelect(tipusDibuix, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, (float)192.5, 60, "DIBUIXA");
        bAddImage = new ButtonWords(processing, "AFEGEIX IMATGE", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 632, Setup.wButtonsNewBuild, 60, 10, "CORNER");
        bAddImage.setFillColor(processing.color(219, 217, 209));
        bErraseCreate = new ButtonWords(processing, "BORRAR",(float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), 444F, (float)192.5, 60, 10, "CORNER");
        bErraseCreate.setFillColor(processing.color(219, 217, 209));
        bPinCreate = new ButtonWords(processing, "PIN",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 726, 400, 60, 10, "CORNER");
        bPinCreate.setFillColor(processing.color(219, 217, 209));
        tipusPlantilla = new String[]{"UNA CASELLA","DUES CASELLES", "QUATRE CASELLES", "SIS CASELLES"};
        selectPlantilla = new ButtonSelect(tipusPlantilla, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 538, Setup.wButtonsNewBuild, 60, "PLANTILLA");
        bSizeDraw = new ButtonSlide(processing, "MIDA", (float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), Setup.ySecondMiddle, (float)192.5, 60F, standardSize, 50,10);
        colorsCreate = new PaletaColors(processing, Setup.xPaletaColors, (int)Setup.logoDistV, Setup.sizePaletaColors, Setup.sizePaletaColors, 5, 4);
        bColorCreate = new ButtonWords(processing, "COLOR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 444, (float)192.5, 60, 10, "CORNER");
        bColorCreate.setFillColor(processing.color(219, 217, 209));
        bColorPersonal = new ButtonWords(processing, "PERSONALITZAR", Setup.xPaletaColors + Setup.sizePaletaColors/2, Setup.logoDistV + Setup.sizePaletaColors - 30, 400, 40, 10, "CENTER");
        bColorPersonal.setFillColor(0xFF8E8E90);
        bRed = new ButtonSlide(processing, "RED", Setup.xPaletaColors+100+Setup.edgeH, Setup.logoDistV+100+Setup.edgeV, 200, 80, 0, 255, 0);
        nombreQuadrat = new String[]{"1", "2", "3", "4", "5", "6"};
        selectQuadratImage = new ButtonSelect(nombreQuadrat, Setup.xSecondMiddle + 385, Setup.ySecondMiddle + 100, Setup.wButtonsNewBuild, 60, "QUADRAT ON INSERIR");
        bCercarImage = new ButtonWords(processing, "BUSCAR IMAGEN", Setup.xSecondMiddle + 385, Setup.ySecondMiddle + 250, Setup.wButtonsNewBuild, 60, 10, "CENTER");

        //BOTONS CREATE FULL SCREEN
        bSaveCfull = new ButtonWords(processing, "SAVE", Setup.edgeH, 818, Setup.ySecondMiddle, 60, 10, "CORNER");
        selectDrawFull = new ButtonSelect(tipusDibuix, Setup.edgeH, Setup.edgeV, Setup.ySecondMiddle, 60, "DIBUIXA");
        selectDrawFull.setFillColor(processing.color(255, 255, 255));
        bSizeDrawFull = new ButtonSlide(processing, "MIDA",Setup.edgeH, 134, Setup.ySecondMiddle, 60, standardSize, 50, 10);
        bSizeDrawFull.setcolor(processing.color(255, 255, 255));
        bColorCreateFull = new ButtonWords(processing, "COLOR", Setup.edgeH, 248, Setup.ySecondMiddle, 60, 10, "CORNER");
        bColorCreateFull.setFillColor(0xFF8E8E90);
        bErraseCreateFull = new ButtonWords(processing, "BORRAR",Setup.edgeH, 362, Setup.ySecondMiddle, 60, 10, "CORNER");
        bErraseCreateFull.setFillColor(0xFF8E8E90);
        selectPlantillaFull = new ButtonSelect(tipusPlantilla, Setup.edgeH, 476, Setup.ySecondMiddle, 60, "PLANTILLA");
        selectPlantillaFull.setFillColor(processing.color(255, 255, 255));
        bAddImageFull = new ButtonWords(processing, "AFEGEIX IMATGE", Setup.edgeH, 590, Setup.ySecondMiddle, 60, 10, "CORNER");
        bAddImageFull.setFillColor(0xFF8E8E90);
        bPinFull = new ButtonWords(processing, "PIN", Setup.edgeH, 704, Setup.ySecondMiddle, 60, 10, "CORNER");
        bPinFull.setFillColor(0xFF8E8E90);

        // BOTONS ACCOUNT
        bNProjects = new ButtonWords(processing, "SEE PROJECTS", processing.width/2 - 150, processing.height/2 + 250, 180, 60, 10, "CENTER");
        bLogOut = new ButtonWords(processing, "LOG OUT", processing.width/2 + 150, processing.height/2 + 250, 180, 60, 10, "CENTER");

        // BOTONS MAP
        bTlist = new ButtonWords(processing, "APLICA", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 750, 300, 60, 10,"CORNER");
        listMaterial = new TextList(processing, valuesEstil, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, 650 + 30, Setup.wButtonMap, Setup.hButtonsMap);
        listTipologia = new TextList(processing, valuesEstil,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, 550 + 30, Setup.wButtonMap, Setup.hButtonsMap);
        listUse = new TextList(processing, valuesEstil, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, 450 + 30, Setup.wButtonMap, Setup.hButtonsMap);
        listEstil = new TextList(processing, valuesEstil, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, Setup.ySecondMiddle + 30,Setup.wButtonMap, Setup.hButtonsMap);

        bAddBuild = new ButtonWords(processing, "ADD BUILDING", 1260, 375, 140, 30, 10, "CENTER");
        llocsMap = new LocationSetter(processing, info, imageEdificio);
        selectedLloc = null;
        Setup.mapaIlles = processing.loadImage("mapaBalears.svg.png");


        //BOTONS NEW BUILDING
        bNameBuilding = new ButtonInsertText(processing, (int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2), Setup.ySecondMiddle + Setup.hButtonsMap/2, Setup.wButtonsNewBuild, Setup.hButtonsMap, "Name: ", 16);
        bLocationBuilding = new ButtonInsertText(processing, (int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2), Setup.ySecondMiddle + Setup.hButtonsMap/2 + 75, Setup.wButtonsNewBuild, Setup.hButtonsMap, "Location: ", 16);
        buildEstil = new TextList(processing, valuesEstil,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 150, Setup.wButtonsNewBuild, Setup.hButtonsMap);
        buildUse = new TextList(processing, valuesEstil,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 225, Setup.wButtonsNewBuild, Setup.hButtonsMap);
        buildTipologia = new TextList(processing, valuesEstil,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 300, Setup.wButtonsNewBuild, Setup.hButtonsMap);
        buildMaterial = new TextList(processing, valuesEstil,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 375, Setup.wButtonsNewBuild, Setup.hButtonsMap);
        bSaveBuild = new ButtonWords(processing, "SAVE", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 450, 300, 30, 10,"CENTER");

        //BOTONS BUILDING INFO
        bCopyImageBuild = new ButtonWords(processing, "COPY THE IMAGE", 100, Setup.ySecondMiddle + 450, 190, 30, 10,"CORNER");
        bReturnMap = new ButtonWords(processing, "RETURN TO MAP", 310, Setup.ySecondMiddle + 450, 190, 30, 10,"CORNER");

        //BOTONS ARXIU
        selectArxiu = new ButtonSelect(tipusPlantilla, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, 400,60,"TIPUS D'ARXIU");
        bImportImage = new ButtonWords(processing,"IMPORT IMAGE",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 500, 400, 60, 10, "CORNER");
        bCreateBuilding = new ButtonWords(processing, "NEW BUILDING",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 600, 400, 60, 10, "CORNER");
        bNewProject = new ButtonWords(processing, "NEW CREATION", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 700, 400, 60, 10, "CORNER");

        //BOTONS SAVE CREATION
        saveCreationName = new ButtonInsertText(processing, (int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2), Setup.ySecondMiddle + Setup.hButtonsMap/2 + 80, Setup.wButtonsNewBuild, Setup.hButtonsMap, "Name: ", 16);
        bSaveCreation = new ButtonWords(processing, "SAVE", (int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2), Setup.ySecondMiddle + Setup.hButtonsMap/2 + 160, Setup.wButtonsNewBuild, Setup.hButtonsMap, 10, "CENTER");
    }


    //DIFERENTS ZONES
    public void drawLateralBar(PApplet processing){
        processing.pushStyle();
        processing.fill(219, 217, 209);
        processing.rectMode(processing.CORNER);
        processing.rect(0, 0, 4*Setup.logoW, processing.height, 10);
        bLateralBar.display(processing);
        bLateralBar.setFillColor(0xFFF4562A);
        processing.stroke(0);processing.strokeWeight(3);
        processing.line(Setup.edgeH, Setup.edgeV + Setup.logoW + 50 , 4*Setup.logoW - Setup.edgeH, Setup.edgeV + Setup.logoW + 50);
        processing.noStroke();
        bInici.display(processing);
        bCreate.display(processing);
        bMap.display(processing);
        bArchive.display(processing);
        bNewBuilding.display(processing);
        processing.popStyle();
    }
    public void drawAccount(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF435360);
        processing.ellipseMode(processing.CORNER);
        processing.ellipse(processing.width - Setup.logoDistH - Setup.logoW, Setup.logoDistV, Setup.logoW, Setup.logoH);
        processing.image(this.imgAccount, processing.width - Setup.logoDistH - Setup.logoW , Setup.logoDistV, Setup.logoW, Setup.logoH);
        processing.popStyle();
    }

    public void drawAccountSymbol(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF435360);
        processing.ellipseMode(processing.CENTER);
        processing.ellipse((float) processing.width /2, (float)processing.height/2 - 50, Setup.logoW, Setup.logoH);
        imgAccount = processing.loadImage("data/userLogo.png");
        processing.imageMode(processing.CENTER);
        processing.image(imgAccount, (float)processing.width/2, (float)processing.height/2 -50, Setup.logoW, Setup.logoH);
        processing.popStyle();
    }

    public void drawBanner(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF8E8E90);
        processing.rect(Setup.edgeH, Setup.edgeV, processing.width - 2*Setup.edgeH, Setup.bannerH, 10);
        processing.fill(0);
        processing.textSize(30);
        processing.textMode(processing.CORNER);
        processing.text("NAME OF THE COMPANY", Setup.lletresBannerW, Setup.logoDistV + Setup.bannerH/2);
        processing.popStyle();
    }

    public void drawNom(PApplet processing, String x){
        processing.pushStyle();
        processing.rectMode(processing.CENTER);
        processing.fill(0xFF8E8E90);
        processing.rect((float)processing.width/2, Setup.edgeV + Setup.bannerH + Setup.nomH, Setup.nomW, Setup.nomH, 10);
        processing.fill(0);
        processing.textSize(18);
        processing.textMode(processing.CENTER);
        processing.text(x, processing.width/2, Setup.edgeV + Setup.bannerH + Setup.nomH);
        processing.popStyle();
    }

    public void drawLines(PApplet processing){
        processing.pushStyle();
        processing.stroke(0);processing.strokeWeight(3);
        processing.line((float)processing.width/2 + Setup.nomW/2 + Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH, processing.width - Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH);
        processing.line(Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH, processing.width/2 - Setup.nomW/2 - Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH);
        processing.popStyle();
    }

    public void drawMiddle(PApplet processing, float amplada, String text){
        processing.pushStyle();
        processing.rectMode(processing.CENTER);
        processing.fill(0xFF8E8E90);
        processing.rect(processing.width/2, processing.height/2 + 150, amplada, 580, 10);
        processing.textMode(processing.CENTER);
        processing.textSize(30);
        processing.fill(0);
        processing.text(text, processing.width/2, processing.height/2 + 150);
        processing.popStyle();
    }

    public void drawSecondMiddle(PApplet processing, String text){
processing.pushStyle();
processing.rectMode(processing.CORNER);
processing.fill(0xFFDBD9D1);
processing.rect(Setup.xSecondMiddle, Setup.ySecondMiddle,770, 500, 10);
processing.fill(0);
processing.text(text, 570+(770/2), (float) processing.height /2 + 150);
processing.popStyle();
    }

    public void drawLogo(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFFF4562A);
        processing.rectMode(processing.CORNER);
        processing.rect(Setup.logoDistH, Setup.logoDistV, Setup.logoW, Setup.logoH);
        processing.textAlign(processing.CENTER); processing.textSize(16); processing.fill(0);
        processing.text("LOGO", Setup.logoDistH + Setup.logoW/2, Setup.logoDistV + Setup.logoH/2);
        processing.popStyle();
    }

    public void drawAccountInfo(PApplet processing) {
        processing.pushStyle();
        //Name
        processing.rectMode(processing.CENTER);
        processing.fill(219, 217, 209);
        processing.rect(processing.width / 2, processing.height / 2 + 50, 450, 60, 10);

        processing.textAlign(processing.CORNER);
        processing.textSize(18);
        processing.fill(0);
        processing.text(bName.text, processing.width / 2 - 220, processing.height / 2 + 50);

        //Number of projects
        processing.fill(219, 217, 209);
        processing.rect(processing.width / 2, processing.height / 2 + 150, 450, 60, 10);

        processing.fill(0);
        processing.text("Number of projects: ", processing.width / 2 - 220, processing.height / 2 + 150);
        processing.popStyle();
    }

    public void drawPersonalColor(PApplet processing){
        processing.pushStyle();
        processing.fill(219, 217, 209);
        processing.rect(Setup.xPaletaColors+100, Setup.logoDistV+100, Setup.sizePaletaColors/2, Setup.sizePaletaColors/2, 10);
        bRed.display(processing);
        processing.fill(Setup.red, Setup.green, Setup.blue);
        processing.rect(900, 160, 150, 150, 10);
        processing.popStyle();
        // Setup.xPaletaColors, (int)Setup.logoDistV, Setup.sizePaletaColors, Setup.sizePaletaColors
    }

    //CREATE FUNCIÓ DE DIBUIXAR LÍNIES...
    public void updateDraw(PApplet processing, ButtonSelect b){
        if(mouseIntoCreate(processing, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500) && processing.mousePressed){ //Coordenades del SecondMiddle
            if(b.valorSelected.equals("CERCLE")){
                cercle(processing);
                bFullCreate.setEnces(false);
            }  else if(b.valorSelected.equals("QUADRAT")){
                quadrat(processing);
                bFullCreate.setEnces(false);
            } else if(b.valorSelected.equals("LÍNIA")){
                line(processing);
                bFullCreate.setEnces(false);
            } else{
                bFullCreate.setEnces(true);
            }
        }
    }
    public void cercle(PApplet processing){
        processing.pushStyle();
        processing.fill(Setup.colour);
        processing.ellipse(processing.mouseX, processing.mouseY, Setup.size, Setup.size);
        processing.popStyle();
    }
    public void quadrat(PApplet processing){
        processing.pushStyle();
        processing.fill(Setup.colour);
        processing.rect(processing.mouseX, processing.mouseY, Setup.size, Setup.size);
        processing.popStyle();
    }
    public void line(PApplet processing){
        processing.pushStyle();
        processing.fill(Setup.colour);
        processing.stroke(Setup.colour);
        processing.strokeWeight(Setup.size);
        processing.line(xLine, yLine, processing.mouseX, processing.mouseY);
        processing.popStyle();
    }

    public boolean mouseIntoCreate(PApplet processing, float x, float y, float w, float h){
        return processing.mouseX >= x && processing.mouseX <= x + w && processing.mouseY >= y && processing.mouseY <= y + h;
    }

    //DIBUIXAR LES PLANTILLES
    public void updatePlantilla(PApplet processing, ButtonSelect b){
        processing.pushStyle();
        processing.stroke(0); processing.strokeWeight(2);
        if(b.valorSelected == "DUES CASELLES"){
            processing.line(955, Setup.ySecondMiddle, 955, 850);
        } else if(b.valorSelected == "QUATRE CASELLES"){
            processing.line(955,Setup.ySecondMiddle, 955, 850);
            processing.line(Setup.xSecondMiddle, 600, 1340, 600);
        } else if(b.valorSelected == "SIS CASELLES"){
            processing.line(955,Setup.ySecondMiddle, 955, 850);
            processing.line(Setup.xSecondMiddle, 350 + Setup.divHsis, 1340, 350 + Setup.divHsis);
            processing.line(Setup.xSecondMiddle, 350 + 2*Setup.divHsis, 1340, 350 + 2*Setup.divHsis);
        }
        processing.popStyle();
    }

    //CANVIAR MIDA DIBUIXOS
    public void updateSizeDraw(){
        Setup.size = bSizeDraw.getValue();
    }

    public void updateRed(){
        Setup.red = bRed.getValue();
    }

    //On carregar la imatge
    public void fileSelected(PApplet processing, File selection){
        if(selection == null){
            processing.println("No s'ha seleccionat cap fitxer.");
        } else {
            String imageRuta = selection.getAbsolutePath();

            imageAddedCreate = processing.loadImage(imageRuta);
            titolFoto = selection.getName();
        }
    }
    public void updateQuadrat(PApplet processing, ButtonSelect b){
        if(selectPlantilla.valorSelected == "QUATRE CASELLES"){
            if(Objects.equals(b.valorSelected, "1") && imageAddedCreate!=null) {
                processing.image(imageAddedCreate, Setup.xSecondMiddle, Setup.ySecondMiddle, 385, 250);
            } else if(Objects.equals(b.valorSelected, "2") && imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle + 385, Setup.ySecondMiddle, 385, 250);
            } else if(Objects.equals(b.valorSelected, "3") && imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle, Setup.ySecondMiddle + 250, 385, 250);
            } else if(Objects.equals(b.valorSelected, "4") && imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle + 385, Setup.ySecondMiddle + 250, 385, 250);
            }
        } else if(selectPlantilla.valorSelected == "DUES CASELLES"){
            if(Objects.equals(b.valorSelected, "1") && imageAddedCreate!=null) {
                processing.image(imageAddedCreate, Setup.xSecondMiddle, Setup.ySecondMiddle, 385, 500);
            } else if(Objects.equals(b.valorSelected, "2") && imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle + 385, Setup.ySecondMiddle, 385, 500);
            }
        } else if(selectPlantilla.valorSelected == "SIS CASELLES"){
            if(Objects.equals(b.valorSelected, "1") && imageAddedCreate!=null) {
                processing.image(imageAddedCreate, Setup.xSecondMiddle, Setup.ySecondMiddle, 385, (float) 500 /3);
            } else if(Objects.equals(b.valorSelected, "2") && imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle + 385, Setup.ySecondMiddle, 385, (float) 500 /3);
            } else if(Objects.equals(b.valorSelected, "3") && imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle, Setup.ySecondMiddle + (float) 500 /3, 385, (float) 500 /3);
            } else if(Objects.equals(b.valorSelected, "4") && imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle + 385, Setup.ySecondMiddle + (float) 500 /3, 385, (float) 500 /3);
            } else if(Objects.equals(b.valorSelected, "5")&& imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle, Setup.ySecondMiddle + (float) 2*((float) 500 /3), 385, (float) 500 /3);
            }else if(Objects.equals(b.valorSelected, "6")&& imageAddedCreate!=null){
                processing.image(imageAddedCreate, Setup.xSecondMiddle + 385, Setup.ySecondMiddle + (float) 2*((float) 500 /3), 385, (float) 500 /3);
            }
        } else if(selectPlantilla.valorSelected == "UNA CASELLA") {
            processing.image(imageAddedCreate, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500);
        }
    }
    public void drawSelectQuadrat(PApplet processing){
        processing.pushStyle();
        processing.rectMode(processing.CENTER);
        processing.fill(255);
        processing.rect(Setup.xSecondMiddle+385, Setup.ySecondMiddle+250, 500, 400, 10);
        selectQuadratImage.display(processing);
        updateQuadrat(processing, selectQuadratImage);
        bCercarImage.display(processing);
        processing.popStyle();
    }

    //PANTALLES
    public void drawInicial(PApplet processing){
        processing.pushStyle();
        processing.background(219, 217, 209);
        drawBanner(processing);
        bAccount.display(processing);
       b1.display(processing);
        b2.display(processing);
        b3.display(processing);
        b4.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "Imatges");
        c.display(processing);
        bLogo.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bAccount.setEnces(false);
            bLogo.setEnces(false);
            b1.setEnces(false);
            b2.setEnces(false);
            b3.setEnces(false);
            b4.setEnces(false);
        } else{
            bAccount.setEnces(true);
            bLogo.setEnces(true);
            b1.setEnces(true);
            b2.setEnces(true);
            b3.setEnces(true);
            b4.setEnces(true);
        }
        processing.popStyle();
    }

    public void drawLogin(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawNom(processing, "LOG IN");
        drawAccountSymbol(processing);
        bName.display(processing);
        bPassword.display(processing);
        bEnterAccount.display(processing);
        bAccount.setEnces(false);
        bAccount.display(processing);
        processing.popStyle();
    }

    public void drawMyAccount(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "My Account");
        drawNom(processing, "MY ACCOUNT");
        drawAccountSymbol(processing);
        drawAccountInfo(processing);
        processing.rectMode(processing.CENTER);
        processing.textAlign(processing.CENTER);
        bNProjects.display(processing);
        bLogOut.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else{
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        processing.popStyle();
    }

    public void drawMap(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "");
        drawNom(processing, "MAP");
        bAddBuild.display(processing);

        processing.rectMode(processing.CORNER);
        processing.fill(67, 83, 96);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 450,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 550,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 650,100, Setup.hButtonsMap, 10);
        processing.fill(0);
        processing.text("Estil: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 30);
        processing.text("Ús: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 450 + 30);
        processing.text("Tipologia: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 550 + 30);
        processing.text("Material: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 650 + 30);
        bTlist.display(processing);
        listMaterial.display(processing);
        listTipologia.display(processing);
        listUse.display(processing);
        listEstil.display(processing);
        processing.rectMode(processing.CORNER);
        processing.fill(0xFFDBD9D1);
        processing.image(Setup.mapaIlles, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500);
        llocsMap.display(processing, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        if(selectedLloc != null){
            selectedLloc.displayInfo(processing, Setup.xSecondMiddle + 10, Setup.ySecondMiddle + 10, 200, 260);
        }
        processing.popStyle();
    }

    public void drawBuilding(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "BUILDING IMAGE");
        drawNom(processing, "BUILDING INFORMATION");
        processing.rectMode(processing.CORNER);
        processing.fill(0xFFDBD9D1);
        bNameBuilding.display(processing);
        bLocationBuilding.display(processing);
        buildEstil.display(processing);
        buildUse.display(processing);
        buildTipologia.display(processing);
        buildMaterial.display(processing);
        bCopyImageBuild.display(processing);
        bReturnMap.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        processing.popStyle();
    }

    public void drawNewBuilding(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "BUILDING IMAGE");
        drawNom(processing, "NEW BUILDING");
        processing.rectMode(processing.CORNER);
        processing.fill(0xFFDBD9D1);
        bNameBuilding.display(processing);
        bLocationBuilding.display(processing);
        buildEstil.display(processing);
        buildUse.display(processing);
        buildTipologia.display(processing);
        buildMaterial.display(processing);
        bSaveBuild.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        processing.popStyle();
    }

    public void drawArchive(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "ELEMENTS OF THE ARCHIVE");
        drawNom(processing, "ARCHIVE");
        selectArxiu.display(processing);
        bImportImage.display(processing);
        bCreateBuilding.display(processing);
        bNewProject.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        processing.popStyle();
    }

    public void drawCreate(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "");
        drawNom(processing, "CREATE");
        processing.imageMode(processing.CENTER);
        bFullCreate.display(processing);
        bSaveC.display(processing);
        processing.rectMode(processing.CORNER);
        processing.fill(0xFFDBD9D1);
        processing.rect((float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), 444F, (float)192.5, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 538, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 632, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 726, 400, 60, 10);
        bPinCreate.display(processing);
        bColorCreate.display(processing);
        bErraseCreate.display(processing);
        bAddImage.display(processing);
        selectPlantilla.display(processing);
        bSizeDraw.display(processing);
        selectDraw.display(processing);
        updatePlantilla(processing, selectPlantilla);
        updateDraw(processing, selectDraw);
        updateSizeDraw();
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
            bFullCreate.setEnces(false);
            bSaveC.setEnces(false);
            bAddImage.setEnces(false);
            selectPlantilla.setEnces(false);
            selectDraw.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
            bFullCreate.setEnces(true);
            bSaveC.setEnces(true);
            bAddImage.setEnces(true);
            selectPlantilla.setEnces(true);
            selectDraw.setEnces(true);
        }

        /*if(!selectDraw.plegat){
            bSaveC.setEnces(false);
            bAddImage.setEnces(false);
        } else {
            bSaveC.setEnces(true);
            bAddImage.setEnces(true);
        }
        if(!selectPlantilla.plegat) {
selectPlantilla.setEnces(false);
        } else {
            selectPlantilla.setEnces(true);
        }*/

        if(paletaOpen){
            colorsCreate.display(processing);
            bColorPersonal.display(processing);
        }

        if(selectQuadrat){
            drawSelectQuadrat(processing);
        }
        processing.popStyle();
    }

    public void drawSaveCreation(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "PREVIEW OF THE INSPIRATIONAL WALL");
        drawNom(processing, "NEW INSPIRATIONAL WALL");
        saveCreationName.display(processing);
        bSaveCreation.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        processing.popStyle();
    }
    public void drawCreateFullScreen(PApplet processing) {
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        processing.rectMode(processing.CORNER);
        selectDrawFull.display(processing);
        bSizeDrawFull.display(processing);
        bColorCreateFull.display(processing);
        bErraseCreateFull.display(processing);
        selectPlantillaFull.display(processing);
        bAddImageFull.display(processing);
        bPinFull.display(processing);
        processing.rect(410, Setup.edgeV, 1010, 860, 10);
        bSaveCfull.display(processing);
        bMenosCreate.display(processing);
        processing.popStyle();
    }
}
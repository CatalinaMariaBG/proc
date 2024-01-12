package zonesAPP;

import botonsAPP.*;
import processing.core.PApplet;

import processing.core.PImage;

import setupAPP.Setup;

//mesures: 1440, 900
public class GUI {

    PImage imgAccount, imgFullCreate, imgMenosCreate, x;

    ButtonWords b1, b2, b3, b4, bLogo, bEnterAccount, bLateralBar, bCreate, bMap, bArchive, bNewBuilding,
    bInici, bNProjects, bLogOut, bTlist, bAddBuild, bSaveC, bSaveCfull, bAddImage, bColorCreate, bColorPersonal, bPinCreate;

    ButtonPhotos bAccount, bFullCreate, bMenosCreate;

    ButtonInsertText bPassword, bName;

    public enum SCREEN{LOGIN, INICIAL, MYACCOUNT, MAP, BUILDING, NEWBUILDING, ARCHIVE, CREATE, SAVECREATION, CREATEFULLSCREEN};

    public SCREEN screenActual;

    CarrouselFoto c;
    String[] nomsCarrousel, tipusDibuix, tipusPlantilla;

    TextList listEstil;
    String[][] valuesEstil = {{"0", "tipografia"}, {"1", "ladrillo"}, {"2", "lalala"}, {"3", "berta guapaa"}, {"4", "titita"}};

    ButtonSelect selectDraw, selectPlantilla;

    LocationSetter llocsMap;
    LocationMap selectedLloc;

    int standardSize = 5;
    float xLine, yLine;

    ButtonSlide bSizeDraw, bRed, bGreen, bBlue;

    PaletaColors colorsCreate;

boolean menuOpen = false;
boolean paletaOpen = false;
boolean establishPersonalC = false;

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
        bName = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 50, 450, 60, "Name: ", "Name: ", 16);
        bPassword = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 150, 450, 60, "Password: ","Password: ", 16);

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
        bAddImage = new ButtonWords(processing, "AFEGEIX IMATGE", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 632, Setup.wButtonsMap, 60, 10, "CORNER");
        bAddImage.setFillColor(processing.color(219, 217, 209));
        bPinCreate = new ButtonWords(processing, "PIN",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 726, 400, 60, 10, "CORNER");
        bPinCreate.setFillColor(processing.color(219, 217, 209));
        tipusPlantilla = new String[]{"DUES CASELLES", "QUATRE CASELLES", "SIS CASELLES", "LLIURE"};
        selectPlantilla = new ButtonSelect(tipusPlantilla, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 538, Setup.wButtonsMap, 60, "PLANTILLA");
        bSizeDraw = new ButtonSlide(processing, "MIDA", (float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), Setup.ySecondMiddle, (float)192.5, 60F, standardSize, 50,10);
        colorsCreate = new PaletaColors(processing, Setup.xPaletaColors, (int)Setup.logoDistV, Setup.sizePaletaColors, Setup.sizePaletaColors, 5, 4);
        bColorCreate = new ButtonWords(processing, "COLOR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 444, (float)192.5, 60, 10, "CORNER");
        bColorCreate.setFillColor(processing.color(219, 217, 209));
        bColorPersonal = new ButtonWords(processing, "PERSONALITZAR", Setup.xPaletaColors + Setup.sizePaletaColors/2, Setup.logoDistV + Setup.sizePaletaColors - 30, 400, 40, 10, "CENTER");
        bColorPersonal.setFillColor(0xFF8E8E90);
        bRed = new ButtonSlide(processing, "RED", Setup.xPaletaColors+100+Setup.edgeH, Setup.logoDistV+100+Setup.edgeV, 200, 80, 0, 255, 0);

        //BOTONS CREATE FULL SCREEN
        bSaveCfull = new ButtonWords(processing, "SAVE", Setup.edgeH + 50, 800, 250, 80, 10, "CORNER");

        // BOTONS ACCOUNT
        bNProjects = new ButtonWords(processing, "SEE PROJECTS", processing.width/2 - 150, processing.height/2 + 250, 180, 60, 10, "CENTER");
        bLogOut = new ButtonWords(processing, "LOG OUT", processing.width/2 + 150, processing.height/2 + 250, 180, 60, 10, "CENTER");

        // BOTONS MAP
        listEstil = new TextList(processing, valuesEstil, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 200, Setup.ySecondMiddle + 30, Setup.wButtonsMap, 60, "Estil: ");
        bTlist = new ButtonWords(processing, "APLICA", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 750, 300, 60, 10,"CORNER");

        bAddBuild = new ButtonWords(processing, "ADD BUILDING", 1260, 375, 140, 30, 10, "CENTER");
        llocsMap = new LocationSetter(processing, Setup.info);
        selectedLloc = null;
        Setup.mapaIlles = processing.loadImage("mapaBalears.svg.png");
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
        listEstil.display(processing);
        bTlist.display(processing);
        if(Setup.selectedTextTipografia!=null){
            processing.pushStyle();
            processing.textAlign(processing.CENTER); processing.fill(0);
            //processing.text(Setup.selectedTextTipografia, );
            processing.popStyle();
        }
        //processing.width/2 - 50, processing.height/2, 100, 80, 10
        //mesures: 1440, 900
        //1040, processing.height/2 + 150, 600, 500, 10
        processing.rectMode(processing.CORNER);
        processing.fill(0xFFDBD9D1);
        //processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 450, Setup.wButtonsMap, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 550, Setup.wButtonsMap, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 650, Setup.wButtonsMap, 60, 10);
        //processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 750, 300, 60, 10);
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
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, Setup.wButtonsMap, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 450, Setup.wButtonsMap, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 550, Setup.wButtonsMap, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 650, Setup.wButtonsMap, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 750, 300, 60, 10);
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
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 450, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 550, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 650, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 750, 300, 60, 10);
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
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 500, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 600, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 700, 400, 60, 10);
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
        //processing.rect((float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), 350F, (float)192.5, 60, 10);
        //processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 444, (float)192.5, 60, 10);
        processing.rect((float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), 444F, (float)192.5, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 538, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 632, 400, 60, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 726, 400, 60, 10);
        bPinCreate.display(processing);
        bColorCreate.display(processing);
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
    public void drawCreateFullScreen(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        processing.rectMode(processing.CORNER);
        processing.rect(Setup.edgeH, Setup.edgeV, Setup.ySecondMiddle, 80, 10);
        processing.rect(Setup.edgeH, 176, Setup.ySecondMiddle, 80, 10);
        processing.rect(Setup.edgeH, 332, Setup.ySecondMiddle, 80, 10);
        processing.rect(Setup.edgeH, 488, Setup.ySecondMiddle, 80, 10);
        processing.rect(Setup.edgeH, 644, Setup.ySecondMiddle, 80, 10);
        processing.rect(410, Setup.edgeV, 1010, 860, 10);
        bSaveCfull.display(processing);
        bMenosCreate.display(processing);
        processing.popStyle();
    }
}
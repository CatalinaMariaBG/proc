package zonesAPP;

import bbdd.DataBase;
import botonsAPP.*;
import fontsAPP.Tipus_font;
import processing.core.PApplet;

import processing.core.PGraphics;
import processing.core.PImage;

import setupAPP.Setup;

import funcionsAPP.Canvas;

//mesures: 1440, 900
public class GUI {
    Tipus_font lletres;
    PImage imgAccount, imgFullCreate, imgMenosCreate, imgBuildInto, imgNextArxiu, imgAtrasArxiu, imgCreationInfo;

    ButtonWords b1, b2, b3, b4, bLogo, bEnterAccount, bLateralBar, bCreate, bMap, bArchive, bNewBuilding,
    bInici, bNProjects, bLogOut, bAplicaMap, bAddBuild, bSaveC, bSaveCfull, bAddImage, bColorCreate, bColorPersonal, bPinCreate,
    bErraseCreate, bSaveBuild, bDeleteBuild, bReturnMap, bBorrarMuro,
    bColorCreateFull, bErraseCreateFull, bAddImageFull, bPinFull, bAddImgNewBuild, bResetMap, bAceptarP, bNoP, bVolverArchivo, bSaveCreation, bCreateProject;

    ButtonPhotos bAccount, bFullCreate, bMenosCreate, bNextArxiu, bAtrasArxiu;

    ButtonInsertText bPassword, bName, bNameBuilding, bPosXBuilding, bPosYBuilding, saveCreationName, nomProject, dataProject;

    public enum SCREEN{LOGIN, INICIAL, MYACCOUNT, MAP, BUILDING, NEWBUILDING, ARCHIVE, CREATE, CREATIONINFO, CREATEFULLSCREEN};

    public SCREEN screenActual;

    CarrouselFoto carrouselFoto;
    String[] nomsCarrousel, tipusDibuix, tipusPlantilla, infoBuilding, tipusCreate, columnesArxiu;

    TextList listEstil, listTipologia, listMaterial, buildEstil, buildTipologia, buildMaterial, listProyecto;
    ButtonSelect selectDraw, selectPlantilla, selectCreate, selectDrawFull, selectPlantillaFull;

    LocationSetter llocsMap;
    Edifici selectedLloc;
    String[][] info, imageEdificio;

    int standardSize = 5;
    float xPin, yPin;

    String titolFoto, nomBuildingInto, nomImgBuildInto, nombreProyectoInfo, nombreMuroInfo;

    ButtonSlide bSizeDraw, bRed, bGreen, bBlue, bSizeDrawFull;
    PaletaColors colorsCreate;
    Canvas canvas;
    PGraphics dibuix;
    PImage lastImage, imgNewBuild, imgSaved;
    ButtonInsertText[] pinText;
    Pin[] pins;
    Confirm cNewBuild;
    Arxiu archivo;
    float[] xPoints, yPoints, xPointsFull, yPointsFull, columnesArxiuAmplada;
    int numPoints = 0;
    int numPointsFull = 0;

boolean menuOpen = false;
boolean paletaOpen = false;
boolean establishPersonalC = false;
boolean newProject = false;
boolean newCreate = true;
boolean personalColor = false;
boolean infoProyecto = false;
DataBase db;

    public GUI(PApplet processing, DataBase db){

        this.db = db;

        xPoints = new float[2];
        yPoints = new float[2];
        lletres = new Tipus_font(processing);
        screenActual = SCREEN.LOGIN;
        //INICIAL
        b1 = new ButtonWords(processing, "CREAR", 190, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b2 = new ButtonWords(processing, "MAPA", 490, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b3 = new ButtonWords(processing , "ARCHIVO", 790, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b4 = new ButtonWords(processing, "NUEVO EDIFICIO", 1090, Setup.yButtonInicial, 180, 80, 10, "CORNER");

       carrouselFoto = new CarrouselFoto(80, 310, processing.width - 2*Setup.logoW, 580, 2);
        nomsCarrousel = new String[]{ "auditori-de-manacor.jpg", "edificiCasasayas.jpeg","casalBalaguer.jpg", "MACE.jpg", "ciudadBlanca.jpg"};
        carrouselFoto.setImatges(processing, nomsCarrousel);
        carrouselFoto.setButtons(processing, "2048px-Back_Arrow.svg.png", "Next_Arrow.svg.png");
        carrouselFoto.setTimer(processing, 10);
        //GENERAL
        bLogo = new ButtonWords(processing, "MENÚ", Setup.logoDistH, Setup.logoDistV, Setup.logoW, Setup.logoH, 0, "CORNER");
            bLogo.setFillColor(0xFFF4562A);
            bLogo.setFont(lletres.getFontNormal());
        imgAccount = processing.loadImage("data/userLogo.png");
        bAccount = new ButtonPhotos(processing, imgAccount, processing.width - Setup.logoDistH - Setup.logoW/2, Setup.logoDistV + Setup.logoH/2, Setup.logoW);

        //BOTONS LOGIN
        bEnterAccount = new ButtonWords(processing, "LOG IN", processing.width/2 - 150, processing.height/2 + 225, 300, 60, 10, "CORNER");
            bEnterAccount.setFillColor(0xFFF4562A);
            bEnterAccount.mouseIntoButton(processing);
            // processing.rectMode(processing.CORNER);
        //        processing.fill(67, 83, 96);
        //        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle,100, Setup.hButtonsMap, 10);
        bName = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 50, 450, Setup.hButtonsMap, "Nombre: ", 16);
        bPassword = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 150, 450, Setup.hButtonsMap, "Clave: ", 16);

        //BOTONS BARRA LATERAL
        bLateralBar = new ButtonWords(processing, "MENÚ", 160 - Setup.logoW/2, Setup.logoDistV, Setup.logoW, Setup.logoH, 0, "CORNER");
            bLateralBar.mouseIntoButton(processing);
            bInici = new ButtonWords(processing, "INICIO", Setup.edgeH, Setup.edgeV + Setup.logoW + 100, 280, 80, 10, "CORNER");
        bCreate = new ButtonWords(processing, "CREAR", Setup.edgeH, Setup.edgeV + Setup.logoW + 250 , 280, 80, 10, "CORNER");
        bMap = new ButtonWords(processing, "MAPA", Setup.edgeH, Setup.edgeV + Setup.logoW + 400, 280, 80, 10, "CORNER");
        bArchive = new ButtonWords(processing, "ARCHIVO", Setup.edgeH, Setup.edgeV + Setup.logoW + 550, 280, 80, 10, "CORNER");
        bNewBuilding = new ButtonWords(processing, "NUEVO EDIFICIO", Setup.edgeH, Setup.edgeV + Setup.logoW + 700, 280, 80, 10, "CORNER");

        //BOTONS CREATE
        imgFullCreate = processing.loadImage("data/fullScreen.png");
        bFullCreate = new ButtonPhotos(processing, imgFullCreate, Setup.fullScreenX, Setup.fullScreenY, Setup.fullScreenW);
        bFullCreate.setColors(255, 0,0xFFDBD9D1);
        imgMenosCreate = processing.loadImage("data/menosScreen.png");
        bMenosCreate = new ButtonPhotos(processing, imgMenosCreate, processing.width - 3* Setup.edgeH, processing.height - 3* Setup.edgeV, Setup.fullScreenW);
        bMenosCreate.setColors(255, 0,0xFFDBD9D1);
        bSaveC = new ButtonWords(processing, "GUARDAR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 820, 300, 30, 10, "CORNER");
        tipusDibuix = new String[]{"DIBUJAR","CÍRCULO", "CUADRADO", "LÍNEA"};
        selectDraw = new ButtonSelect(tipusDibuix, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, (float)192.5, 60, "DIBUJA");
        bAddImage = new ButtonWords(processing, "AÑADIR IMAGEN", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 632, Setup.wButtonCreate, 60, 10, "CORNER");
        bAddImage.setFillColor(processing.color(219, 217, 209));
        bErraseCreate = new ButtonWords(processing, "BORRAR",(float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), 444F, (float)192.5, 60, 10, "CORNER");
        bErraseCreate.setFillColor(processing.color(219, 217, 209));
        bPinCreate = new ButtonWords(processing, "COMENTARIO",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 726, Setup.wButtonCreate, 60, 10, "CORNER");
        bPinCreate.setFillColor(processing.color(219, 217, 209));
        tipusPlantilla = new String[]{"UNA CASILLA","DOS CASILLAS", "CUATRO CASILLAS", "SEIS CASILLAS"};
        selectPlantilla = new ButtonSelect(tipusPlantilla, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 538, Setup.wButtonCreate, 60, "PLANTILLA");
        bSizeDraw = new ButtonSlide(processing, "MEDIDA", (float) (Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 207.5), Setup.ySecondMiddle, (float)192.5, 60F, standardSize, 50,10);
        colorsCreate = new PaletaColors(processing, Setup.xPaletaColors, (int)Setup.logoDistV, Setup.sizePaletaColors, Setup.sizePaletaColors, 5, 4);
        bColorCreate = new ButtonWords(processing, "COLOR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 444, (float)192.5, 60, 10, "CORNER");
        bColorCreate.setFillColor(processing.color(219, 217, 209));
        bColorPersonal = new ButtonWords(processing, "PERSONALIZAR", Setup.xPaletaColors + Setup.sizePaletaColors/2, Setup.logoDistV + Setup.sizePaletaColors - 30, 400, 40, 10, "CENTER");
        bColorPersonal.setFillColor(0xFF8E8E90);
        bRed = new ButtonSlide(processing, "ROJO", Setup.xPaletaColors+100+Setup.edgeH, Setup.logoDistV+100+Setup.edgeV, 200, 80, 0, 255, 0);
        pinText = new ButtonInsertText[5];
        pins = new Pin[5];
        canvas = new Canvas(processing, Setup.xSecondMiddle, Setup.ySecondMiddle,770, 500);
        dibuix = processing.createGraphics(770, 500);
        bSaveCreation = new ButtonWords(processing, "GUARDAR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50 , 660, 300, 60, 10,"CORNER");
        bCreateProject = new ButtonWords(processing, "NUEVO PROYECTO",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50 , 750, 300, 60, 10,"CORNER");

        //BOTONS CREATE FULL SCREEN
        bSaveCfull = new ButtonWords(processing, "GUARDAR", Setup.edgeH, 818, Setup.ySecondMiddle, 60, 10, "CORNER");
        selectDrawFull = new ButtonSelect(tipusDibuix, Setup.edgeH, Setup.edgeV, Setup.ySecondMiddle, 60, "DIBUIXA");
        bSizeDrawFull = new ButtonSlide(processing, "MEDIDA",Setup.edgeH, 134, Setup.ySecondMiddle, 60, standardSize, 50, 10);
        bColorCreateFull = new ButtonWords(processing, "COLOR", Setup.edgeH, 248, Setup.ySecondMiddle, 60, 10, "CORNER");
        bColorCreateFull.setFillColor(processing.color(219, 217, 209));
        bErraseCreateFull = new ButtonWords(processing, "BORRAR",Setup.edgeH, 362, Setup.ySecondMiddle, 60, 10, "CORNER");
        bErraseCreateFull.setFillColor(processing.color(219, 217, 209));
        selectPlantillaFull = new ButtonSelect(tipusPlantilla, Setup.edgeH, 476, Setup.ySecondMiddle, 60, "PLANTILLA");
        bAddImageFull = new ButtonWords(processing, "AÑADIR IMAGEN", Setup.edgeH, 590, Setup.ySecondMiddle, 60, 10, "CORNER");
        bAddImageFull.setFillColor(processing.color(219, 217, 209));
        bPinFull = new ButtonWords(processing, "PIN", Setup.edgeH, 704, Setup.ySecondMiddle, 60, 10, "CORNER");
        bPinFull.setFillColor(processing.color(219, 217, 209));
        //canvasFull = new Canvas(410, (int)Setup.edgeV, 1010, 860);

        // BOTONS ACCOUNT
        bNProjects = new ButtonWords(processing, "VER PROYECTOS", processing.width/2 - 150, processing.height/2 + 250, 180, 60, 10, "CENTER");
        bLogOut = new ButtonWords(processing, "LOG OUT", processing.width/2 + 150, processing.height/2 + 250, 180, 60, 10, "CENTER");

        // BOTONS MAP
        String[][] valuesEstil = db.getEstilosEdificios();
        String[][] valuesMaterial = db.getMaterialesEdificios();
        String[][] valuesTipologia = db.getTipologiaEdificios();

        bResetMap = new ButtonWords(processing, "BORRAR FILTROS",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50 , 750, 300, 60, 10,"CORNER" );
        bAplicaMap = new ButtonWords(processing, "APLICAR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50 , 660, 300, 60, 10,"CORNER");
        listMaterial = new TextList(processing, valuesMaterial, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, 550 + 30, Setup.wButtonMap, Setup.hButtonsMap);
        listTipologia = new TextList(processing, valuesTipologia, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, 450 + 30, Setup.wButtonMap, Setup.hButtonsMap);
        listEstil = new TextList(processing, valuesEstil, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, Setup.ySecondMiddle + 30,Setup.wButtonMap, Setup.hButtonsMap);

        bAddBuild = new ButtonWords(processing, "AÑADIR EDIFICIO", 1260, 375, 140, 30, 10, "CENTER");
        info = db.getInfoMapaEdificios();
        llocsMap = new LocationSetter(processing, info);
        selectedLloc = null;
        Setup.mapaIlles = processing.loadImage("mapaBalears.svg.png");


        //BOTONS NEW BUILDING
        bNameBuilding = new ButtonInsertText(processing, (int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2) + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2, Setup.wButtonsNewBuild, Setup.hButtonsMap, "", 16);
        bPosXBuilding = new ButtonInsertText(processing, (int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2) + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 75, Setup.wButtonsNewBuild, Setup.hButtonsMap, "", 16);
        bPosYBuilding = new ButtonInsertText(processing,(int)(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100), Setup.ySecondMiddle + Setup.hButtonsMap/2 + 150, Setup.wButtonsNewBuild, Setup.hButtonsMap, "", 16);
        buildEstil = new TextList(processing, valuesEstil,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 225, Setup.wButtonsNewBuild, Setup.hButtonsMap);
        buildTipologia = new TextList(processing, valuesTipologia,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 300, Setup.wButtonsNewBuild, Setup.hButtonsMap);
        buildMaterial = new TextList(processing, valuesMaterial,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 375, Setup.wButtonsNewBuild, Setup.hButtonsMap);
        bSaveBuild = new ButtonWords(processing, "GUARDAR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 +50, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 450, 300, 30, 10,"CENTER");
        cNewBuild = new Confirm(processing, Setup.titolConfirmNewBuild, Setup.messageConfirmNewBuild, processing.width/2, processing.height/2, 420, 300);
        bAddImgNewBuild = new ButtonWords(processing, "Añadir imagen", 570+(770/2), (float) processing.height /2 + 150, Setup.wButtonsNewBuild, Setup.hButtonsMap, 10, "CENTER");

        //BOTONS BUILDING INFO
        bDeleteBuild = new ButtonWords(processing, "ELIMINAR EDIFICIO", 100, Setup.ySecondMiddle + 450, 190, 30, 10,"CORNER");
        bReturnMap = new ButtonWords(processing, "MAPA", 310, Setup.ySecondMiddle + 450, 190, 30, 10,"CORNER");

        //BOTONS ARCHIVO
        int p = db.getNumProyectos();
        archivo = new Arxiu(7, 4);
        imgNextArxiu = processing.loadImage("Next_Arrow.svg.png");
        imgAtrasArxiu = processing.loadImage("2048px-Back_Arrow.svg.png");
        bNextArxiu = new ButtonPhotos(processing, imgNextArxiu, Setup.xSecondMiddle + 770, Setup.ySecondMiddle+250, 40);
        bAtrasArxiu = new ButtonPhotos(processing, imgAtrasArxiu, Setup.xSecondMiddle,Setup.ySecondMiddle+250, 40);
        columnesArxiu = new String[]{"Nombre","Fecha final", "Número de muros de inspiración", "Ver contenido"};
        columnesArxiuAmplada = new float[]{25, 15, 40, 20};
        archivo.setHeaders(columnesArxiu);
        archivo.setData(db.getProyectos());
        archivo.setColumnWidths(columnesArxiuAmplada);
        tipusCreate = new String[]{"CREAR", "NUEVO EDIFICIO", "NUEVO PROYECTO", "NUEVO MURO DE INSPIRACIÓN"};
        selectCreate = new ButtonSelect(tipusCreate, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle, 400, 60, "CREAR");
        nomProject = new ButtonInsertText(processing, Setup.xSecondMiddle + 193, Setup.ySecondMiddle + 110, 346, Setup.hButtonsMap,"Nombre: ",20);
        bAceptarP = new ButtonWords(processing,"CREAR",Setup.xSecondMiddle + 20, Setup.ySecondMiddle + 260, 163, 40, 10, "CORNER");
        bNoP = new ButtonWords(processing, "CANCELAR", Setup.xSecondMiddle + 203, Setup.ySecondMiddle + 260, 163, 40, 10, "CORNER");
        dataProject = new ButtonInsertText(processing, Setup.xSecondMiddle + 193, Setup.ySecondMiddle + 210, 346, Setup.hButtonsMap, "Finalización: ", 20);

        //BOTONS SAVE CREATION
        bBorrarMuro = new ButtonWords(processing, "ELIMINAR", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50 , 660, 300, 60, 10,"CORNER");
        bVolverArchivo = new ButtonWords(processing, "ARCHIVO",Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50 , 750, 300, 60, 10,"CORNER");
        String[][] valuesProyecto = db.getSelectProyectos();
        listProyecto = new TextList(processing, valuesProyecto, Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, Setup.ySecondMiddle + 30,Setup.wButtonMap, Setup.hButtonsMap);
        saveCreationName = new ButtonInsertText(processing, (int)(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250), 450 + 30, Setup.wButtonMap, Setup.hButtonsMap, "", 20);
    }

    //DIFERENTS ZONES
    public void drawNewProject(PApplet processing){
        processing.pushStyle();
        processing.rectMode(processing.CORNER);
        processing.fill(0xFFDBD9D1);
        processing.rect(Setup.xSecondMiddle, Setup.ySecondMiddle,386, 320, 10);
        processing.fill(67, 83, 96);
        processing.rect(Setup.xSecondMiddle, Setup.ySecondMiddle, 386, 60, 10);
        processing.fill(0);
        processing.textFont(lletres.getFontNormal());
        processing.text("CREAR PROYECTO", Setup.xSecondMiddle + 193, Setup.ySecondMiddle + 30);
        nomProject.display(processing);
        dataProject.display(processing);
        bAceptarP.display(processing);
        bNoP.display(processing);
        processing.popStyle();
    }

    public void canviarArchive(){
        if(selectCreate.valorSelected.equals("NUEVO EDIFICIO")){
            screenActual = GUI.SCREEN.NEWBUILDING;
        } else if(selectCreate.valorSelected.equals("NUEVO PROYECTO")){
            newProject = true;
        }else if(selectCreate.valorSelected.equals("NUEVO MURO DE INSPIRACIÓN")){
            screenActual = GUI.SCREEN.CREATE;
        }
    }
    public void drawLateralBar(PApplet processing){
        processing.pushStyle();
        processing.fill(219, 217, 209);
        processing.rectMode(processing.CORNER);
        processing.rect(0, 0, 4*Setup.logoW, processing.height, 10);
        processing.textFont(lletres.getFontNormal());
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
        processing.textFont(lletres.getFontTitol());
        processing.textMode(processing.CORNER);
        processing.text("INSP-ARCH", Setup.lletresBannerW, Setup.logoDistV + Setup.bannerH/2);
        processing.popStyle();
    }

    public void drawNom(PApplet processing, String x){
        processing.pushStyle();
        processing.rectMode(processing.CENTER);
        processing.fill(0xFF8E8E90);
        processing.rect((float)processing.width/2, Setup.edgeV + Setup.bannerH + Setup.nomH, Setup.nomW, Setup.nomH, 10);
        processing.fill(0);
        processing.textFont(lletres.getFontTitol());
        processing.textMode(processing.CENTER);
        processing.textSize(40);
        processing.text(x, processing.width/2, Setup.edgeV + Setup.bannerH + Setup.nomH + 10);
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
        processing.textFont(lletres.getFontNormal());processing.textAlign(processing.CENTER); processing.textSize(16); processing.fill(0);
        processing.text("MENÚ", Setup.logoDistH + Setup.logoW/2, Setup.logoDistV + Setup.logoH/2);
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
        processing.textFont(lletres.getFontNormal());
        processing.fill(0);
        int numProyecto = db.getNumProyectosUser(bName.getTextoEspecial2());
        processing.text("Número de proyectos: "+numProyecto, processing.width / 2 - 220, processing.height / 2 + 150);
        processing.popStyle();
    }

    public void drawPersonalColor(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF8E8E90);
        processing.rectMode(processing.CENTER);
        processing.rect(Setup.xSecondMiddle+770/2, Setup.ySecondMiddle +250, Setup.sizePaletaColors/2, 490, 10);
        bRed.display(processing);
        processing.fill(Setup.red, Setup.green, Setup.blue);
        processing.rect(Setup.xSecondMiddle+770, Setup.ySecondMiddle + 250, 150, 150, 10);
        processing.popStyle();
        // Setup.xPaletaColors, (int)Setup.logoDistV, Setup.sizePaletaColors, Setup.sizePaletaColors
    }

    //CREATE FUNCIÓ DE DIBUIXAR LÍNIES...
    public void updateDraw(PApplet processing, ButtonSelect b){
        dibuix.beginDraw();
        if(mouseIntoCreate(processing, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500) && processing.mousePressed && screenActual == SCREEN.CREATE){ //Coordenades del SecondMiddle
            if(b.valorSelected.equals("CÍRCULO")){
                cercle(dibuix, processing);
                bFullCreate.setEnces(false);
            }  else if(b.valorSelected.equals("CUADRADO")){
                quadrat(dibuix, processing);
                bFullCreate.setEnces(false);
            } else if(b.valorSelected.equals("LÍNEA")){
                line(dibuix);
                bFullCreate.setEnces(false);
            } else{
                bFullCreate.setEnces(true);
            }
        }
        dibuix.endDraw();
    }

   /* public void updateDrawFull(PApplet processing, ButtonSelect b){
        dibuixFull.beginDraw();
        if(mouseIntoCreate(processing, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500) && processing.mousePressed && screenActual == SCREEN.CREATE){ //Coordenades del SecondMiddle
            if(b.valorSelected.equals("CÍRCULO")){
                cercle(dibuixFull, processing);
                bFullCreate.setEnces(false);
            }  else if(b.valorSelected.equals("CUADRADO")){
                quadrat(dibuixFull, processing);
                bFullCreate.setEnces(false);
            } else if(b.valorSelected.equals("LÍNEA")){
                line(dibuixFull);
                bFullCreate.setEnces(false);
            } else{
                bFullCreate.setEnces(true);
            }
        }
        dibuixFull.endDraw();
    }*/
    public void cercle(PGraphics dibuix, PApplet processing){
        dibuix.pushStyle();
        dibuix.noStroke();
        if(screenActual == SCREEN.CREATE) {
            dibuix.fill(Setup.colour);
            dibuix.ellipse(processing.mouseX - Setup.xSecondMiddle, processing.mouseY - Setup.ySecondMiddle, Setup.size, Setup.size);
        }else if(screenActual == SCREEN.CREATEFULLSCREEN){
            //410, Setup.edgeV, 1010, 860, 10
            dibuix.fill(Setup.colourFull);
            dibuix.ellipse(processing.mouseX - 410, processing.mouseY - Setup.edgeV, Setup.sizeFull, Setup.sizeFull);
        }
        dibuix.popStyle();
    }
    public void quadrat(PGraphics dibuix, PApplet processing){
        dibuix.pushStyle();
        dibuix.noStroke();
        if(screenActual == SCREEN.CREATE) {
            dibuix.fill(Setup.colour);
            dibuix.rect(processing.mouseX - Setup.xSecondMiddle, processing.mouseY - Setup.ySecondMiddle, Setup.size, Setup.size);
        } else if(screenActual == SCREEN.CREATEFULLSCREEN){
            dibuix.fill(Setup.colour);
            dibuix.rect(processing.mouseX - 410, processing.mouseY - Setup.edgeV, Setup.sizeFull, Setup.sizeFull);
        }
        dibuix.popStyle();
    }
    public void line(PGraphics dibuix){
        if(numPoints==2 && screenActual == SCREEN.CREATE) {
            dibuix.pushStyle();
            dibuix.fill(Setup.colour);
            dibuix.stroke(Setup.colour);
            dibuix.strokeWeight(Setup.size);
            dibuix.line(xPoints[0] - Setup.xSecondMiddle, yPoints[0] - Setup.ySecondMiddle, xPoints[1] - Setup.xSecondMiddle, yPoints[1] - Setup.ySecondMiddle);
            dibuix.popStyle();
        } else if(numPointsFull == 2 && screenActual == SCREEN.CREATEFULLSCREEN){
            dibuix.pushStyle();
            dibuix.fill(Setup.colourFull);
            dibuix.stroke(Setup.colourFull);
            dibuix.strokeWeight(Setup.sizeFull);
            dibuix.line(xPointsFull[0] - 410, yPointsFull[0] - Setup.edgeV, xPointsFull[1] - 410, yPointsFull[1] - Setup.edgeV);
            dibuix.popStyle();
        }
    }

    public boolean mouseIntoCreate(PApplet processing, float x, float y, float w, float h){
        return processing.mouseX >= x && processing.mouseX <= x + w && processing.mouseY >= y && processing.mouseY <= y + h;
    }

    //DIBUIXAR LES PLANTILLES
    public void updatePlantilla(PApplet processing, ButtonSelect b){
        processing.pushStyle();
        processing.stroke(0); processing.strokeWeight(2);
        if(b.valorSelected == "UNA CASILLA"){
            canvas.setDistribucio(Canvas.DISTRIBUCIO.UNXUN);
            //canvasFull.setDistribucio(Canvas.DISTRIBUCIO.UNXUN);
        } else if(b.valorSelected == "DOS CASILLAS"){
            if(screenActual == SCREEN.CREATE) {
                processing.line(955, Setup.ySecondMiddle, 955, 850);
            } else if(screenActual == SCREEN.CREATEFULLSCREEN){
                processing.line(915, (int)Setup.edgeV, 915, 880);
            }
            canvas.setDistribucio(Canvas.DISTRIBUCIO.UNXDOS);
            //canvasFull.setDistribucio(Canvas.DISTRIBUCIO.UNXDOS);
        } else if(b.valorSelected == "CUATRO CASILLAS"){
            if(screenActual == SCREEN.CREATE) {
                processing.line(955, Setup.ySecondMiddle, 955, 850);
                processing.line(Setup.xSecondMiddle, 600, 1340, 600);
            } else if(screenActual == SCREEN.CREATEFULLSCREEN){
                processing.line(915, (int)Setup.edgeV, 915, 880);
                //410, (int)Setup.edgeV, 1010, 860
                processing.line(410, 450, 1420, 450);
            }
            canvas.setDistribucio(Canvas.DISTRIBUCIO.DOSXDOS);
            //canvasFull.setDistribucio(Canvas.DISTRIBUCIO.DOSXDOS);
        } else if(b.valorSelected == "SEIS CASILLAS"){
            if(screenActual == SCREEN.CREATE) {
                processing.line(955, Setup.ySecondMiddle, 955, 850);
                processing.line(Setup.xSecondMiddle, 350 + Setup.divHsis, 1340, 350 + Setup.divHsis);
                processing.line(Setup.xSecondMiddle, 350 + 2 * Setup.divHsis, 1340, 350 + 2 * Setup.divHsis);
            } else if(screenActual == SCREEN.CREATEFULLSCREEN){
                processing.line(915, Setup.edgeV, 915, 880);
                processing.line(410, Setup.edgeV + 860/3, 1420,Setup.edgeV + 860/3);
                processing.line(410, Setup.edgeV + (float) (2 * 860) /3, 1420,Setup.edgeV + (float) (2 * 860) /3);
            }
            canvas.setDistribucio(Canvas.DISTRIBUCIO.TRESXDOS);
            //canvasFull.setDistribucio(Canvas.DISTRIBUCIO.TRESXDOS);
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
        processing.textFont(lletres.getFontNormal());
        bAccount.display(processing);
        b1.display(processing);
        b2.display(processing);
        b3.display(processing);
        b4.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "Imatges");
        carrouselFoto.display(processing);
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
        processing.textFont(lletres.getFontNormal());
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
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "Mi Cuenta");
        drawNom(processing, "MI CUENTA");
        processing.textFont(lletres.getFontNormal());
        bLogo.display(processing);
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
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "");
        drawNom(processing, "MAPA");
        processing.textFont(lletres.getFontNormal());
        bLogo.display(processing);
        bAddBuild.display(processing);
        processing.rectMode(processing.CORNER);
        processing.fill(67, 83, 96);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 450,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 550,100, Setup.hButtonsMap, 10);
        processing.fill(0);
        processing.text("Estilo: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 30);
        processing.text("Tipología: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 450 + 30);
        processing.text("Material: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 550 + 30);
        bResetMap.display(processing);
        bAplicaMap.display(processing);
        listMaterial.display(processing);
        listTipologia.display(processing);
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
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "AÑADIR IMAGEN");
        drawNom(processing, "INFO. EDIFICIO");
        processing.textFont(lletres.getFontNormal());
        bLogo.display(processing);
        processing.rectMode(processing.CORNER);
        processing.fill(67, 83, 96);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 75,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 150,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 225,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 300,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 375,100, Setup.hButtonsMap, 10);
        processing.fill(0);
        processing.text("Nombre: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 30);
        processing.text("Longitud: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 105);
        processing.text("Latitud: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 180);
        processing.text("Estilo: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 255);
        processing.text("Tipología: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 330);
        processing.text("Material: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 405);
        processing.fill(0xFFDBD9D1);
        processing.rectMode(processing.CENTER);
        processing.rect((int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2) + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2, Setup.wButtonsNewBuild, Setup.hButtonsMap, 10);
        processing.rect((int) ((int) Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2) + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 75, Setup.wButtonsNewBuild, Setup.hButtonsMap, 10);
        processing.rect((int)(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100), Setup.ySecondMiddle + Setup.hButtonsMap/2 + 150, Setup.wButtonsNewBuild, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 225, Setup.wButtonsNewBuild, Setup.hButtonsMap,10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 300, Setup.wButtonsNewBuild, Setup.hButtonsMap,10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + Setup.wButtonsNewBuild /2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap/2 + 375, Setup.wButtonsNewBuild, Setup.hButtonsMap,10);
        processing.textSize(16);
        processing.fill(0);
        if(nomBuildingInto!=null && infoBuilding!=null) {
            processing.text(nomBuildingInto, (int) ((int) Setup.logoDistH + Setup.logoW / 2 + Setup.edgeH + Setup.wButtonsNewBuild / 2) + 100, Setup.ySecondMiddle + Setup.hButtonsMap / 2);
            processing.text(infoBuilding[2], (int) ((int) Setup.logoDistH + Setup.logoW / 2 + Setup.edgeH + Setup.wButtonsNewBuild / 2) + 100, Setup.ySecondMiddle + Setup.hButtonsMap / 2 + 75);
            processing.text(infoBuilding[3], (int) (Setup.logoDistH + Setup.logoW / 2 + Setup.edgeH + Setup.wButtonsNewBuild / 2 + 100), Setup.ySecondMiddle + Setup.hButtonsMap / 2 + 150);
            processing.text(infoBuilding[5], Setup.logoDistH + Setup.logoW / 2 + Setup.edgeH + Setup.wButtonsNewBuild / 2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap / 2 + 225);
            processing.text(infoBuilding[6], Setup.logoDistH + Setup.logoW / 2 + Setup.edgeH + Setup.wButtonsNewBuild / 2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap / 2 + 300);
            processing.text(infoBuilding[7], Setup.logoDistH + Setup.logoW / 2 + Setup.edgeH + Setup.wButtonsNewBuild / 2 + 100, Setup.ySecondMiddle + Setup.hButtonsMap / 2 + 375);
        }
        if(nomImgBuildInto!= null){
            imgBuildInto = processing.loadImage(nomImgBuildInto);
            processing.image(imgBuildInto,Setup.xSecondMiddle, Setup.ySecondMiddle,770, 500);
        }
        bDeleteBuild.display(processing);
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
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "BUILDING IMAGE");
        drawNom(processing, "NUEVO EDIFICIO");
        processing.textFont(lletres.getFontNormal());
        bLogo.display(processing);
        processing.rectMode(processing.CORNER);
        processing.fill(67, 83, 96);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 75,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 150,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 225,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 300,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle + 375,100, Setup.hButtonsMap, 10);
        processing.fill(0);
        processing.text("Nombre: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 30);
        processing.text("Longitud: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 105);
        processing.text("Latitud: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 180);
        processing.text("Estilo: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 255);
        processing.text("Tipología: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 330);
        processing.text("Material: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 405);
        processing.fill(0xFFDBD9D1);
        bNameBuilding.display(processing);
        bPosXBuilding.display(processing);
        bPosYBuilding.display(processing);
        bSaveBuild.display(processing);
        buildMaterial.display(processing);
        buildTipologia.display(processing);
        buildEstil.display(processing);
        bAddImgNewBuild.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        if(imgNewBuild != null){
            processing.image(imgNewBuild,Setup.xSecondMiddle, Setup.ySecondMiddle,770, 500);
        }
        cNewBuild.display(processing);
        processing.popStyle();
    }

    public void drawArchive(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "");
        drawNom(processing, "ARCHIVO");
        processing.textFont(lletres.getFontNormal());
        bLogo.display(processing);
        selectCreate.display(processing);
        archivo.display(processing,Setup.xSecondMiddle, Setup.ySecondMiddle,770, 500);
        archivo.setButtons(processing,Setup.xSecondMiddle, Setup.ySecondMiddle,770, 500);
        bNextArxiu.display(processing);
        bAtrasArxiu.display(processing);
        if(menuOpen){
            drawLateralBar(processing);
            bLogo.setEnces(false);
            bAccount.setEnces(false);
        } else {
            bLogo.setEnces(true);
            bAccount.setEnces(true);
        }
        if(newProject){
            drawNewProject(processing);
        }
        processing.popStyle();
    }

    public void drawCreate(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "");
        drawNom(processing, "CREAR");
        processing.textFont(lletres.getFontNormal());
        bLogo.display(processing);
        processing.imageMode(processing.CENTER);
        canvas.display(processing);
        if(dibuix!=null){
            processing.imageMode(processing.CORNER);
            processing.image(dibuix, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500);
        }
        if(newCreate) {
            bFullCreate.display(processing);
            bSaveC.display(processing);
            processing.rectMode(processing.CORNER);
            processing.fill(0xFFDBD9D1);
            bPinCreate.display(processing);
            bColorCreate.display(processing);
            bErraseCreate.display(processing);
            bAddImage.display(processing);
            selectPlantilla.display(processing);
            bSizeDraw.display(processing);
            selectDraw.display(processing);
            updatePlantilla(processing, selectPlantilla);
            updateSizeDraw();
        } else if(!newCreate){
            processing.rectMode(processing.CORNER);
            processing.fill(67, 83, 96);
            processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle,100, Setup.hButtonsMap, 10);
            processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 450,100, Setup.hButtonsMap, 10);
            processing.fill(0);
            processing.text("Proyecto: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 30);
            processing.text("Nombre: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 450 + 30);
            bSaveCreation.display(processing);
            saveCreationName.display(processing);
            bCreateProject.display(processing);
            listProyecto.display(processing);
            if(newProject){
                drawNewProject(processing);
            }
            /*if(imgSaved!=null){
                processing.image(imgSaved, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500);
            }*/
        }
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
        } else if(personalColor){
            drawPersonalColor(processing);
        }
        processing.popStyle();
    }

    public void drawCreationInfo(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "");
        drawNom(processing, "NUEVA INSPO");
        processing.textFont(lletres.getFontNormal());
        bLogo.display(processing);
        processing.rectMode(processing.CORNER);
        processing.fill(67, 83, 96);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, Setup.ySecondMiddle,100, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH, 450,100, Setup.hButtonsMap, 10);
        processing.fill(0);
        processing.text("Proyecto: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, Setup.ySecondMiddle + 30);
        processing.text("Nombre: ", Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 50, 450 + 30);
        processing.fill(0xFFDBD9D1);
        processing.rectMode(processing.CENTER);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, Setup.ySecondMiddle + 30,Setup.wButtonMap, Setup.hButtonsMap, 10);
        processing.rect(Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, 450 + 30, Setup.wButtonMap, Setup.hButtonsMap, 10);
        processing.textAlign(processing.CENTER);
        processing.fill(0);
        processing.text(nombreProyectoInfo,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, Setup.ySecondMiddle + 30);
        processing.text(nombreMuroInfo,Setup.logoDistH + Setup.logoW/2 + Setup.edgeH + 250, 450 + 30);
        bVolverArchivo.display(processing);
        bBorrarMuro.display(processing);
        if(imgCreationInfo!=null){
            processing.image(imgCreationInfo, Setup.xSecondMiddle, Setup.ySecondMiddle, 770, 500);
        }
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
        processing.textFont(lletres.getFontNormal());
        processing.background(0xFF8E8E90);
        processing.rectMode(processing.CORNER);
        processing.rect(410, Setup.edgeV, 1010, 860, 10);
        canvas.display(processing);
        if(dibuix!=null){
            processing.imageMode(processing.CORNER);
            processing.image(dibuix, 410, Setup.edgeV, 1010, 860);
        }
        bSizeDrawFull.display(processing);
        bColorCreateFull.display(processing);
        bErraseCreateFull.display(processing);
        bAddImageFull.display(processing);
        bPinFull.display(processing);
        bSaveCfull.display(processing);
        bMenosCreate.display(processing);
        selectPlantillaFull.display(processing);
        updatePlantilla(processing,selectPlantillaFull);
        selectDrawFull.display(processing);
        processing.popStyle();
    }

}
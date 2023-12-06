package zonesAPP;

import botonsAPP.CarrouselFoto;
import processing.core.PApplet;

import processing.core.PImage;

import setupAPP.Setup;

import botonsAPP.ButtonWords;

import botonsAPP.ButtonPhotos;

import botonsAPP.ButtonInsertText;

import botonsAPP.Timer;

//mesures: 1440, 900
public class GUI {

    PImage imgAccount, imgFullCreate, imgMenosCreate, x;

    ButtonWords b1, b2, b3, b4, bLogo, bEnterAccount, bLateralBar, bCreate, bMap, bArchive, bNewBuilding, bInici, bNProjects, bLogOut;

    ButtonPhotos bAccount, bFullCreate, bMenosCreate;

    ButtonInsertText bPassword, bName;

    public enum SCREEN{LOGIN, INICIAL, MYACCOUNT, MAP, BUILDING, NEWBUILDING, ARCHIVE, CREATE, SAVECREATION, CREATEFULLSCREEN};

    public SCREEN screenActual;

    CarrouselFoto c;
    String[] nomsCarrousel;

    Timer timer;

boolean menuOpen = false;
    public GUI(PApplet processing){

        screenActual = SCREEN.LOGIN;

        //INICIAL
        b1 = new ButtonWords(processing, "CREATE", 190, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b2 = new ButtonWords(processing, "MAP", 490, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b3 = new ButtonWords(processing , "ARCHIVE", 790, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b4 = new ButtonWords(processing, "NEW BUILDING", 1090, Setup.yButtonInicial, 180, 80, 10, "CORNER");
        b1.mouseIntoButton(processing);
        b2.mouseIntoButton(processing);
        b3.mouseIntoButton(processing);
        b4.mouseIntoButton(processing);

       c = new CarrouselFoto(80, 310, processing.width - 2*Setup.logoW, 580, 2);
        nomsCarrousel = new String[]{ "auditori-de-manacor.jpg", "edificiCasasayas.jpeg","casalBalaguer.jpg", "MACE.jpg", "ciudadBlanca.jpg"};
        //, "fundacioMiro-tallerSert.jpg.webp", "canLis.jpg.webp", "clubNauticFormentera.jpg.webp"
        c.setImatges(processing, nomsCarrousel);
        c.setButtons(processing, "2048px-Back_Arrow.svg.png", "Next_Arrow.svg.png");
        c.setTimer(processing, 7, 70);

        //GENERAL
        bLogo = new ButtonWords(processing, "LOGO", Setup.logoDistH, Setup.logoDistV, Setup.logoW, Setup.logoH, 0, "CORNER");
            bLogo.setFillColor(0xFFF4562A);
        imgAccount = processing.loadImage("data/userLogo.png");
        bAccount = new ButtonPhotos(processing, imgAccount, processing.width - Setup.logoDistH - Setup.logoW/2, Setup.logoDistV + Setup.logoH/2, Setup.logoW);

        //BOTONS LOGIN
        bEnterAccount = new ButtonWords(processing, "ENTER", processing.width/2 - 150, processing.height/2 + 225, 300, 60, 10, "CORNER");
            bEnterAccount.setFillColor(0xFFF4562A);
            bEnterAccount.mouseIntoButton(processing);
        bName = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 50, 450, 60, "Name: ", 16);
        bPassword = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 150, 450, 60, "Password: ", 16);

        //BOTONS BARRA LATERAL
        bLateralBar = new ButtonWords(processing, "LOGO", 160 - Setup.logoW/2, Setup.logoDistV, Setup.logoW, Setup.logoH, 0, "CORNER");
            bLateralBar.mouseIntoButton(processing);
            bInici = new ButtonWords(processing, "INICI", Setup.edgeH, Setup.edgeV + Setup.logoW + 100, 280, 80, 10, "CORNER");
        bCreate = new ButtonWords(processing, "CREATE", Setup.edgeH, Setup.edgeV + Setup.logoW + 250 , 280, 80, 10, "CORNER");
        bMap = new ButtonWords(processing, "MAP", Setup.edgeH, Setup.edgeV + Setup.logoW + 400, 280, 80, 10, "CORNER");
        bArchive = new ButtonWords(processing, "ARCHIVE", Setup.edgeH, Setup.edgeV + Setup.logoW + 550, 280, 80, 10, "CORNER");
        bNewBuilding = new ButtonWords(processing, "NEW BUILDING", Setup.edgeH, Setup.edgeV + Setup.logoW + 700, 280, 80, 10, "CORNER");

        //BOTONS CREATE
        imgFullCreate = processing.loadImage("data/fullScreen.png");
        bFullCreate = new ButtonPhotos(processing, imgFullCreate, Setup.fullScreenX, Setup.fullScreenY, Setup.fullScreenW);
        bFullCreate.setColors(255, 0,0xFFDBD9D1);
        imgMenosCreate = processing.loadImage("data/menosScreen.png");
        bMenosCreate = new ButtonPhotos(processing, imgMenosCreate, processing.width - 2* Setup.edgeH, processing.height - 2* Setup.edgeV, Setup.fullScreenW);
        bMenosCreate.setColors(255, 0,0xFFDBD9D1);

        // BOTONS ACCOUNT
        bNProjects = new ButtonWords(processing, "SEE PROJECTS", processing.width/2 - 150, processing.height/2 + 250, 180, 60, 10, "CENTER");
        bLogOut = new ButtonWords(processing, "LOG OUT", processing.width/2 + 150, processing.height/2 + 250, 180, 60, 10, "CENTER");
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
processing.fill(0xFFDBD9D1);
processing.rectMode(processing.CENTER);
processing.rect(1040, processing.height/2 + 150, 600, 500, 10);
processing.fill(0);
processing.text(text, 1040, processing.height/2 + 150);
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

    public void drawAccountInfo(PApplet processing){
        //Name
        processing.rectMode(processing.CENTER); processing.fill(219, 217, 209);
        processing.rect(processing.width/2, processing.height/2 + 50, 450, 60, 10);

        processing.textAlign(processing.CORNER); processing.textSize(18); processing.fill(0);
        processing.text(bName.text, processing.width/2 -220,processing.height/2 + 50);

        //Number of projects
        processing.fill(219, 217, 209);
        processing.rect(processing.width/2, processing.height/2 + 150, 450, 60, 10);

        processing.fill(0);
        processing.text("Number of projects: ", processing.width/2 -220, processing.height/2 + 150);
    }

    //PANTALLES

    public void drawInicial(PApplet processing){
        processing.background(219, 217, 209);
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
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
            b1.setEnces(false);
            b2.setEnces(false);
            b3.setEnces(false);
            b4.setEnces(false);
        } else{
            bAccount.setEnces(true);
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
        drawSecondMiddle(processing, "MAP");
        drawNom(processing, "MAP");
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
        drawSecondMiddle(processing, "INSPIRATIONAL WALL");
        drawNom(processing, "CREATE");
        processing.imageMode(processing.CENTER);
        bFullCreate.display(processing);
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
        processing.rect(Setup.edgeH, Setup.edgeV, 670, processing.height - 2*Setup.edgeV, 10);
        bMenosCreate.display(processing);
        processing.popStyle();
    }
}

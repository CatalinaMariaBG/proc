package zonesAPP;

import processing.core.PApplet;

import processing.core.PImage;

import setupAPP.Setup;

import botonsAPP.ButtonWords;

import botonsAPP.ButtonPhotos;

import botonsAPP.ButtonInsertText;

import zonesAPP.Trivio003;
//mesures: 1440, 900
public class GUI {

    PImage img;

    ButtonWords b1, b2, b3, b4, bLogo, bEnterAccount;

    ButtonPhotos bAccount;

    ButtonInsertText bPassword, bName;

    public enum SCREEN{LOGIN, INICIAL, MYACCOUNT, MAP, BUILDING, NEWBUILDING, ARCHIVE, CREATE, SAVECREATION, CREATEFULLSCREEN};

    public SCREEN screenActual;

boolean menuOpen = false;
    public GUI(PApplet processing){

        screenActual = SCREEN.LOGIN;
//INICIAL
        b1 = new ButtonWords(processing, "CREATE", 190, Setup.yButtonInicial, 180, 80, 10);
        b2 = new ButtonWords(processing, "MAP", 490, Setup.yButtonInicial, 180, 80, 10);
        b3 = new ButtonWords(processing , "ARCHIVE", 790, Setup.yButtonInicial, 180, 80, 10);
        b4 = new ButtonWords(processing, "NEW BUILDING", 1090, Setup.yButtonInicial, 180, 80, 10);
        bLogo = new ButtonWords(processing, "LOGO", Setup.logoDistW, Setup.logoDistH, Setup.logoW, Setup.logoH, 0);
                bLogo.setFillColor(0xFFF4562A);
        //bEnterAccount.setFillColor(0xFFF4562A);
                bEnterAccount = new ButtonWords(processing, "ENTER", processing.width/2, processing.height/2 - 650, 300, 100, 10);
                bName = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 50, 450, 60);
                bPassword = new ButtonInsertText(processing, processing.width/2, processing.height/2 + 150, 450, 60 );
        img = processing.loadImage("data/userLogo.png");
        bAccount = new ButtonPhotos(processing, img, processing.width - Setup.logoDistW - Setup.logoW/2, Setup.logoDistH + Setup.logoH/2, Setup.logoW);

        b1.mouseIntoButton(processing);
        b2.mouseIntoButton(processing);
        b3.mouseIntoButton(processing);
        b4.mouseIntoButton(processing);
        bEnterAccount.mouseIntoButton(processing);
        bPassword.mouseIntoTextRect(processing);
        bName.mouseIntoTextRect(processing);
    }

    // BOTONS


    //DIFERENTS ZONES

    public void drawLateralBar(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFFDBD9D1);
        processing.rectMode(processing.CORNER);
        processing.rect(0, 0, 4*Setup.logoW, processing.height, 10);
        processing.popStyle();
    }
    public void drawAccount(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF435360);
        processing.ellipseMode(processing.CORNER);
        processing.ellipse(processing.width - Setup.logoDistW - Setup.logoW, Setup.logoDistH, Setup.logoW, Setup.logoH);
        processing.image(this.img, processing.width - Setup.logoDistW - Setup.logoW , Setup.logoDistH, Setup.logoW, Setup.logoH);
        processing.popStyle();
    }

    public void drawLoginPage(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF435360);
        processing.ellipseMode(processing.CENTER);
        processing.ellipse((float) processing.width /2, (float)processing.height/2 - 50, Setup.logoW, Setup.logoH);
        img = processing.loadImage("data/userLogo.png");
        processing.imageMode(processing.CENTER);
        processing.image(img, (float)processing.width/2, (float)processing.height/2 -50, Setup.logoW, Setup.logoH);
        processing.popStyle();
    }

    public void drawBanner(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF8E8E90);
        processing.rect(Setup.edgeH, Setup.edgeV, processing.width - 2*Setup.edgeH, Setup.bannerH, 10);
        processing.fill(0);
        processing.textSize(30);
        processing.textMode(processing.CORNER);
        processing.text("NAME OF THE COMPANY", Setup.lletresBannerW, Setup.logoDistH + Setup.bannerH/2);
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

    public void drawLinesInicial(PApplet processing){
        processing.pushStyle();
        processing.stroke(0);processing.strokeWeight(3);
        processing.line(1090 + 180 + 10, Setup.yButtonInicial + 50/*height del botó /2*/, processing.width - Setup.edgeH, Setup.yButtonInicial + 50);
        processing.line(Setup.edgeH, Setup.yButtonInicial + 50, 180, Setup.yButtonInicial + 50);
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

    public void drawFullScreenSymbol(PApplet processing){
        processing.pushStyle();
        img = processing.loadImage("data/fullScreenSymbol.png");
        processing.imageMode(processing.CENTER);
        processing.image(img, Setup.fullScreenX, Setup.fullScreenY, Setup.fullScreenW, Setup.fullScreenH);
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

    public void drawBuildingInfo(PApplet processing){
        processing.pushStyle();
        processing.rectMode(processing.CORNER);
        //processing.rect();

        processing.popStyle();
    }

    public void drawLogo(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFFF4562A);
        processing.rectMode(processing.CORNER);
        processing.rect(Setup.logoDistW, Setup.logoDistH, Setup.logoW, Setup.logoH);
        processing.textAlign(processing.CENTER); processing.textSize(16); processing.fill(0);
        processing.text("LOGO", Setup.logoDistW + Setup.logoW/2, Setup.logoDistH + Setup.logoH/2);
        processing.popStyle();
    }

    //PANTALLES

    public void drawInicial(PApplet processing){
        processing.background(219, 217, 209);
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
                drawBanner(processing);
        //drawLogo(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
       b1.display(processing);
        b2.display(processing);
        b3.display(processing);
        b4.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "Imatges");
        bLogo.display(processing);
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
        processing.popStyle();
        //drawLinesInicial(processing);
    }

    public void drawLogin(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawNom(processing, "LOG IN");
        drawLoginPage(processing);
        bName.display(processing);
        bPassword.display(processing);
        bEnterAccount.display(processing);
        bAccount.setEnces(false);
        bAccount.display(processing);
        processing.popStyle();
    }

    public void drawMyAccount(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "My Account");
        drawNom(processing, "MY ACCOUNT");
        drawLoginPage(processing);
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
    }

    public void drawMap(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "MAP");
        drawNom(processing, "MAP");
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
    }

    public void drawBuilding(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "BUILDING IMAGE");
        drawNom(processing, "BUILDING INFORMATION");
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
    }

    public void drawNewBuilding(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "BUILDING IMAGE");
        drawNom(processing, "NEW BUILDING");
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
    }

    public void drawArchive(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "ELEMENTS OF THE ARCHIVE");
        drawNom(processing, "ARCHIVE");
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
    }

    public void drawCreate(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "INSPIRATIONAL WALL");
        drawNom(processing, "CREATE");
        drawFullScreenSymbol(processing);
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
    }

    public void drawSaveCreation(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        bLogo.display(processing);
        bAccount.setEnces(true);
        bAccount.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "PREVIEW OF THE INSPIRATIONAL WALL");
        drawNom(processing, "NEW INSPIRATIONAL WALL");
        if(isMenuOpen(processing)){
            drawLateralBar(processing);
        }
    }

    public void createFullScreen(PApplet processing){

    }

    public boolean isMenuOpen(PApplet processing){
        if(bLogo.mouseIntoButton(processing) && processing.mousePressed){
            menuOpen = !menuOpen;
        }
        return menuOpen;
    }

}

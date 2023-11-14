package zonesAPP;

import processing.core.PApplet;

import processing.core.PImage;

import setupAPP.Setup;

import botonsAPP.ButtonWords;
public class GUI {

    PImage img;

    ButtonWords b1, b2, b3, b4;

    public enum SCREEN{INICIAL, LOGIN, MYACCOUNT, MAP, BUILDING, NEWBUILDING, ARCHIVE, CREATE, SAVECREATION };

    public SCREEN screenActual;


    public GUI(){

        screenActual = SCREEN.INICIAL;

        b1 = new ButtonWords(new PApplet(), "CREATE", 200, 200, 180, 100);
        b2 = new ButtonWords(new PApplet(), "MAP", 250, 200, 180, 100);
        b3 = new ButtonWords(new PApplet(), "ARCHIVE", 300, 200, 180, 100);
        b4 = new ButtonWords(new PApplet(), "NEW BUILDING", 350, 200, 180, 100);

        b1.mouseIntoButton(new PApplet());
        b2.mouseIntoButton(new PApplet());
        b3.mouseIntoButton(new PApplet());
        b4.mouseIntoButton(new PApplet());

    }

    //DIFERENTS ZONES

    public void drawLogo(PApplet processing){
        processing.pushStyle();
processing.fill(0xFFF4562A);
processing.rect(Setup.logoDistW, Setup.logoDistH, Setup.logoW, Setup.logoH);
processing.fill(0);
        processing.textSize(16);
        processing.text("LOGO", Setup.logoDistW + Setup.logoW/2, Setup.logoDistH + Setup.logoH/2 );
        processing.popStyle();

    }


    public void drawAccount(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF435360);
        processing.ellipseMode(processing.CORNER);
        processing.ellipse(processing.width - Setup.logoDistW - Setup.logoW, Setup.logoDistH, Setup.logoW, Setup.logoH);
        img = processing.loadImage("data/user_1144760.png");
        processing.image(img, processing.width - Setup.logoDistW - Setup.logoW , Setup.logoDistH, Setup.logoW, Setup.logoH);
        processing.popStyle();
    }

    public void drawLoginPage(PApplet processing){
        processing.pushStyle();
        processing.fill(0xFF435360);
        processing.ellipseMode(processing.CENTER);
        processing.ellipse(processing.width/2, processing.height/2 - 50, Setup.logoW, Setup.logoH);
        img = processing.loadImage("data/user_1144760.png");
        processing.imageMode(processing.CENTER);
        processing.image(img, processing.width/2, processing.height/2 -50, Setup.logoW, Setup.logoH);
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
processing.rect(processing.width/2, Setup.edgeV + Setup.bannerH + Setup.nomH, Setup.nomW, Setup.nomH, 10);
        processing.fill(0);
        processing.textSize(16);
        processing.textMode(processing.CENTER);
        processing.text(x, processing.width/2, Setup.edgeV + Setup.bannerH + Setup.nomH);
processing.popStyle();
    }

    public void drawLines(PApplet processing){
        processing.pushStyle();
        processing.stroke(0);processing.strokeWeight(3);
        processing.line(processing.width/2 + Setup.nomW/2 + Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH, processing.width - Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH);
        processing.line(Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH, processing.width/2 - Setup.nomW/2 - Setup.edgeH, Setup.edgeV + Setup.bannerH + Setup.nomH);
        processing.popStyle();
    }

    public void drawMiddle(PApplet processing, float amplada, String text){
        processing.pushStyle();
        processing.rectMode(processing.CENTER);
        processing.fill(0xFF8E8E90);
        processing.rect(processing.width/2, processing.height/2 + 150, amplada, 560, 10);
        processing.textMode(processing.CENTER);
        processing.textSize(30);
        processing.fill(0);
        processing.text(text, processing.width/2, processing.height/2 + 100);
        processing.popStyle();
    }

    public void drawSecondMiddle(PApplet processing, String text){
        processing.pushStyle();
processing.fill(0xFFDBD9D1);
processing.rectMode(processing.CENTER);
processing.rect(processing.width/2 + (processing.width - 3*Setup.logoW), processing.height/2 + 150, 800, 500, 10);
processing.fill(0);
processing.text(text, processing.width/2 + (processing.width - 2*Setup.logoW)/8, processing.height/2 + 150);
        processing.popStyle();
    }

    public void drawInicial(PApplet processing){
        processing.pushStyle();
        processing.background(0xFFDBD9D1);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
       b1.display(processing);
        b2.display(processing);
        b3.display(processing);
        b4.display(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "Imatges");
        processing.popStyle();
    }

    public void drawLogin(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "Login");
        drawNom(processing, "LOG IN");
        drawLoginPage(processing);
    }

    public void drawMyAccount(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, "My Account");
        drawNom(processing, "MY ACCOUNT");
        drawLoginPage(processing);
    }

    public void drawMap(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "MAP");
        drawNom(processing, "MAP");
    }

    public void drawBuilding(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "BUILDING IMAGE");
        drawNom(processing, "BUILDING INFORMATION");
    }

    public void drawNewBuilding(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "BUILDING IMAGE");
        drawNom(processing, "NEW BUILDING");
    }

    public void drawArchive(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "ELEMENTS OF THE ARCHIVE");
        drawNom(processing, "ARCHIVE");
    }

    public void drawCreate(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "INSPIRATIONAL WALL");
        drawNom(processing, "CREATE");
    }

    public void drawSaveCreation(PApplet processing){
        processing.background(0xFFDBD9D1);
        drawLines(processing);
        drawBanner(processing);
        drawLogo(processing);
        drawAccount(processing);
        drawMiddle(processing, processing.width - 2*Setup.logoW, " ");
        drawSecondMiddle(processing, "PREVIEW OF THE INSPIRATIONAL WALL");
        drawNom(processing, "NEW INSPIRATIONAL WALL");
    }

}

package setupAPP;

import processing.core.PApplet;
import static setupAPP.Setup.*;

public class Trivio003 extends PApplet{

    public static void main(String[] args){
        PApplet.main("setupAPP.Trivio003", args);
    }

    public void settings(){
        fullScreen();
        smooth(10);
    }

    public void setup(){
        noStroke();
        textAlign(CENTER); textSize(18);
    }

    public void draw(){
        background(0xFF8E8E90);

        fill(0xFFDBD9D1);
        rect(edgeH, edgeV, width-edgeH*2, height-edgeV*2);


    }
}

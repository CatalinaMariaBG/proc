package colorsAPP;
import processing.core.PApplet;
public class Colors {
    int[] colors;

    public Colors(PApplet processing){
        this.setColors(processing);
    }

    void setColors (PApplet processing){
        this.colors = new int[5];
        this.colors[0] = processing.color(0xFFDBD9D1);
        this.colors[1] = processing.color(0xFF435360);
        this.colors[2] = processing.color(0xFF8E8E90);
        this.colors[3] = processing.color(0xFFFFA21F);
        this.colors[4] = processing.color(0xFFF4562A);
    }

    int getColorsNum(){
        return this.colors.length;
    }

    int getColorsFirst(){
        return this.colors[0];
    }
    int getColorsSecond(){
        return this.colors[1];
    }
    int getColorsThird(){
        return this.colors[2];
    }
    int getColorsFourth(){
        return this.colors[3];
    }
    int getColorsLast(){
        return this.colors[4];
    }

    int getColorsChoose(int i){
        return this.colors[i];
    }

    // El draw de la paleta de colors
    void displayColors(PApplet processing, float x, float y, float z){
        processing.pushStyle();
        //Llegenda
        processing.fill(0);processing.textAlign(processing.LEFT); processing.textSize(36);
        processing.text("Colors:", x, y -12);

        //La paleta dels colors
        float zc = z / getColorsNum();
        for(int i = 0; i<getColorsNum(); i++){
            processing.fill(getColorsChoose(i)); processing.stroke(0); processing.strokeWeight(4);
            processing.rect(x+i*zc, y, zc, zc);
        }
        processing.popStyle();
    }

}

package botonsAPP;

import processing.core.PApplet;

import java.util.ArrayList;

public class Arxiu {
    String[] titolColumn;   // Títols de les columnes
    public String[][] tableData;    // Dades de la taula
    float[] columnWidths;    // Amplades de les columnes
    public ArrayList<ButtonWords> bProject;
    int numCols, numFiles;  // Número de files i columnes

    int numPage;
    int numTotalPages;

    // Constructor
    public Arxiu(int nr, int nc){
        this.numFiles = nr;
        this.numCols = nc;
        this.numPage = 0;
        bProject = new ArrayList<ButtonWords>();
    }

    // Setters
    public void setHeaders(String[] h){
        this.titolColumn = h;
    }

    public void setData(String[][] d){
        this.tableData = d;
        if(d.length % (this.numFiles -1)==0){
            this.numTotalPages = (d.length / (this.numFiles -1)) -1;
        }
        else {
            this.numTotalPages = (d.length / (this.numFiles -1)) ;
        }
    }

    public void setValueAt(String value, int nr, int nc){
        this.tableData[nr][nc] = value;
    }

    public void setColumnWidths(float[] w){
        this.columnWidths = w;
    }

    public void setButtons(PApplet processing, float x, float y, float w, float h){
        float rowHeight = h / numFiles;
        float xCol = x;
        for(int c = 0; c<numCols-1; c++) {
            xCol += w * columnWidths[c] / 100;
        }
        for(int r = 0; r < tableData.length; r++) {
                ButtonWords b = new ButtonWords(processing, "+", xCol, y + (r + 1) * rowHeight, (float) (w * columnWidths[3] / 100.0), rowHeight, 0, "CORNER");
                b.setFillColor(0xFFDBD9D1);
                bProject.add(b);
        }
    }

    public void nextPage(){
        if(this.numPage<this.numTotalPages){
            this.numPage++;
        }
    }
    public void prevPage(){
        if(this.numPage>0){
            this.numPage--;
        }
    }
    public void display(PApplet processing, float x, float y, float w, float h){

        processing.pushStyle();
        processing.fill(200, 50); processing.stroke(0); processing.strokeWeight(3);
        processing.rect(x, y, w, h);

        float rowHeight = h / numFiles;
        processing.fill(0xFFF4562A); processing.stroke(0); processing.strokeWeight(3);
        processing.rect(x, y, w, rowHeight);

        // Dibuixa files
        processing.stroke(0);
        for(int r = 1; r < numFiles; r++){
            if(r==1){ processing.strokeWeight(3); }
            else {    processing.strokeWeight(1); }
            processing.line(x, y + r*rowHeight, x + w, y + r*rowHeight);
        }

        // Dibuixa Columnes
        float xCol = x;
        for(int c = 0; c<numCols; c++){
            xCol += (float) (w*columnWidths[c]/100.0);
            processing.line(xCol, y, xCol, y + h);
        }
        // Dibuixa textos
        processing.textAlign(processing.CORNER);
        processing.fill(0); processing.textSize(24);
        for(int r = 0; r < numFiles; r++){
            xCol = x;
            for(int c = 0; c< numCols; c++){
                if(r==0){
                    processing.text(titolColumn[c], xCol + 10, y + (r+1)*rowHeight - 10);
                } else if(c!= 3){
                    int k = (numFiles -1)*numPage + (r-1);
                    if(k<tableData.length){
                        processing.text(tableData[k][c], xCol + 10, y + (r+1)*rowHeight - 10);
                    }
                }
                xCol += (float) (w*columnWidths[c]/100.0);
            }
        }
        //Dibuixa els botons
        for(ButtonWords b : bProject){
            b.display(processing);
        }
        // Informació de la Pàgina
        processing.fill(0);
        processing.textAlign(processing.CENTER);
        processing.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x + w/2, y + h + 20);
        processing.popStyle();
    }

    }

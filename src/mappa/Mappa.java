package mappa;

import pezzo.Pezzo;

public class Mappa {

    final public int rows;
    final public int cols;
    final public int xDimBorder;
    final public int yDimBorder;
    final public int borderOffset = 3;

    public short[][] caselle;

    public Mappa(int rows, int cols){

        this.rows = rows;
        this.cols = cols;
        this.xDimBorder = rows + borderOffset * 2;
        this.yDimBorder = cols + borderOffset * 2;


        this.caselle = new short[xDimBorder][yDimBorder];

        for(int i = 0; i < xDimBorder; i++){
            for(int j = 0; j < yDimBorder; j++)
                if((j >= 3) && (j <= cols + borderOffset))
                    this.caselle[i][j] = 0;
                else
                    this.caselle[i][j] = 1;
        }


    };

    public int valutaCollisione(Pezzo pezzo){
        return 0; //SISTEMA
        //Se rileva un blocco sotto di lui si ferma;
        //Se tocca il limite superiore della mappa è gameover;
    };

    public boolean valutaRiga(){
        return false;
    };

    public void scendiBlocchi(){

    }







}

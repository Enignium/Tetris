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
                if((j >= 3) && (j < this.cols + borderOffset) && (i < this.rows))
                    this.caselle[i][j] = 0;
                else
                    this.caselle[i][j] = 1;
        }


    };

    public boolean valutaCollisione(Pezzo pezzo, int direzione){
        //SISTEMA
        //Se rileva un blocco sotto di lui si ferma;
        //Se tocca il limite superiore della mappa è gameover;

        int ColOffset = 0;
        int RowOffset = 0;
        switch (direzione) {
            case 1 : ColOffset = -1;
                break;
            case 2 : ColOffset = 1;
                break;
            case 3 : RowOffset = 1;
                break;
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (pezzo.forma[i][j] == 1 && this.caselle[pezzo.row + i + RowOffset][pezzo.column + j + ColOffset] == 1){
                    return true;
                }
            }
        }
        return false;
    };

    //Overloading! yeeee
    public boolean valutaCollisione(Pezzo pezzo, short[][] forma){


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (forma[i][j] == 1 && this.caselle[pezzo.row + i][pezzo.column + j] == 1){
                    return true;
                }
            }
        }
        return false;
    };

    public boolean valutaRiga(){
        return false;
    };

    public void scendiBlocchi(){

    }







}

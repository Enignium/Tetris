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

    public int valutaRiga() {
        boolean piena;
        int counter = 0;

        int i = this.rows - 1;
        while (i >= 0) {
            piena = true;
            for (int j = this.borderOffset; j < this.cols + this.borderOffset; j++) {
                if (this.caselle[i][j] == 0) {
                    piena = false;
                    break;
                }
            }
            if (piena) {
                counter++;
                eliminaRiga(i);
                scendiBlocchi(i);
                // Rivaluta la stessa riga i dopo la discesa dei blocchi
            } else {
                i--; // Passa alla riga successiva solo se non era piena
            }
        }
        return counter;
    }

    public void eliminaRiga(int riga){
        for(int j = this.borderOffset; j < this.cols + borderOffset; j++)
            this.caselle[riga][j] = 0;
    }

    public void scendiBlocchi(int riga){
        for(int i = riga; i > 0; i--){
            for(int j = this.borderOffset; j < this.cols + borderOffset; j++){
                this.caselle[i][j] = this.caselle[i-1][j];
            }
        }
        for(int j = this.borderOffset; j < this.cols + borderOffset; j++){
            this.caselle[0][j] = 0;
        }
    }
}
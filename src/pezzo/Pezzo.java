package pezzo;

import mappa.Mappa;

public abstract class Pezzo {

    public int row;
    public int column;
    public short[][] forma; //array di array

    Mappa mappa;

    public Pezzo(Mappa mappa) {

        this.forma = new short[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                this.forma[i][j] = 0;
        }

        this.mappa = mappa;
        this.row = 1;
        this.column = 4;
        setOffset();
    }

    public abstract void daiForma();

    public void piazzaPezzo() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.forma[i][j] != 0) {
                    mappa.caselle[this.row + i][this.column + j] = this.forma[i][j];
                }
            }
        }
    }

    public void rimuoviPezzo() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.forma[i][j] == 1) {
                    mappa.caselle[row + i][column + j] = 0;
                }
            }
        }
    }

    public boolean valutaSpawn() {
        short[][] formaRuotata = this.forma;
        int startingColumn = this.column;

        if (!mappa.valutaCollisione(this, this.forma)) {
            return true;
        }

        for (int offset = 1; offset < mappa.cols; offset++) {
            // Controlla a destra
            this.column = startingColumn + offset;
            if (this.column < mappa.cols + mappa.borderOffset && !mappa.valutaCollisione(this, this.forma)) {
                return true;
            }
            for (int i = 0; i < 4 && this.column < mappa.cols + mappa.borderOffset; i++) {
                formaRuotata = ruotaForma(formaRuotata);
                if (!mappa.valutaCollisione(this, formaRuotata)) {
                    this.forma = formaRuotata;
                    return true;
                }
            }

            // Controlla a sinistra
            this.column = startingColumn - offset;
            if (this.column >= mappa.borderOffset && !mappa.valutaCollisione(this, this.forma)) {
                return true;
            }
            for (int i = 0; i < 4 && this.column >= mappa.borderOffset; i++) {
                formaRuotata = ruotaForma(formaRuotata);
                if (!mappa.valutaCollisione(this, formaRuotata)) {
                    this.forma = formaRuotata;
                    return true;
                }
            }
        }

        // Ripristina la posizione di partenza se non si trova spazio
        this.column = startingColumn;
        return false;
    }

    public boolean muovi(int direzione) {
        rimuoviPezzo();

        if (mappa.valutaCollisione(this, direzione)) {
            piazzaPezzo();
            return false;
        }

        switch (direzione) {
            case 1: this.column--;
                break;
            case 2: this.column++;
                break;
        }

        piazzaPezzo();
        return true;
    }

    public boolean scendi() {
        rimuoviPezzo();
        if (mappa.valutaCollisione(this, 3)) {
            piazzaPezzo();
            return false;
        }
        this.row++;
        piazzaPezzo();
        return true;
    }

    public short[][] ruotaForma(short[][] forma) {
        short[][] nuovaForma = new short[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nuovaForma[j][3 - i] = forma[i][j];
            }
        }
        return nuovaForma;
    }

    public boolean ruota() {
        rimuoviPezzo();

        short[][] nuovaForma = ruotaForma(this.forma);
        if (mappa.valutaCollisione(this, nuovaForma)) {
            piazzaPezzo();
            return false;
        }

        this.forma = nuovaForma;
        piazzaPezzo();
        return true;
    }

    public void setOffset() {
        this.column += mappa.borderOffset;
    }
}

package pezzo;
import mappa.Mappa;

public abstract class Pezzo {

    int row;
    int column;
    short[][] forma; //array di array

    Mappa mappa;

    public Pezzo(Mappa mappa){

        this.forma = new short[4][4];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++)
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

    /*Chiamata dopo aver valutato lo spostamento ma prima di avelro effetuato!*/
    public void rimuoviPezzo(){

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (this.forma[i][j] == 1) {
                    mappa.caselle[row + i][column + j] = 0;
                }
            }
        }


    }

    public void muovi(){
        //valuta lo spostamento richiesto, se è consentito allora chiama rimuovi pezzo ,
        //piazza il pezzo nuovo dopo aver aggiroanto la posizione! vabbè hai capito!
    }

    public void ruota(){

    }

    public void setOffset(){
        this.column += mappa.borderOffset;
    }

}

package pezzo;
import mappa.Mappa;

public class PezzoSr extends Pezzo{

    public PezzoSr(Mappa mappa){
        super(mappa);
        this.column = 4;

        setOffset();
        daiForma();
    }

    @Override
    public void daiForma() {

        this.forma[0][0] = 0;
        this.forma[0][1] = 0;
        this.forma[0][2] = 1;
        this.forma[0][3] = 0;
        this.forma[1][0] = 0;
        this.forma[1][1] = 1;
        this.forma[1][2] = 1;
        this.forma[1][3] = 0;
        this.forma[2][0] = 0;
        this.forma[2][1] = 1;
        this.forma[2][2] = 0;
        this.forma[2][3] = 0;
        this.forma[3][0] = 0;
        this.forma[3][1] = 0;
        this.forma[3][2] = 0;
        this.forma[3][3] = 0;

    }
}

package client.pezzo;
import client.Mappa;

public class PezzoLr extends Pezzo{

    public PezzoLr(Mappa mappa){
        super(mappa);
        this.idTipo = 2;
        this.row = 1;
        this.column = 4;
        daiForma();
        setOffset();
    }

    @Override
    public void daiForma() {

        this.forma[0][0] = 0;
        this.forma[0][1] = idTipo;
        this.forma[0][2] = 0;
        this.forma[0][3] = 0;
        this.forma[1][0] = 0;
        this.forma[1][1] = idTipo;
        this.forma[1][2] = 0;
        this.forma[1][3] = 0;
        this.forma[2][0] = 0;
        this.forma[2][1] = idTipo;
        this.forma[2][2] = idTipo;
        this.forma[2][3] = 0;
        this.forma[3][0] = 0;
        this.forma[3][1] = 0;
        this.forma[3][2] = 0;
        this.forma[3][3] = 0;

    }
}

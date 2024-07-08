package client.pezzo;
import client.Mappa;

public class PezzoLs extends Pezzo{

    public PezzoLs(Mappa mappa){
        super(mappa);
        this.idTipo = 3;
        this.column = 4;
        setOffset();
        daiForma();
    }

    @Override
    public void daiForma() {

        this.forma[0][0] = 0;
        this.forma[0][1] = 0;
        this.forma[0][2] = idTipo;
        this.forma[0][3] = 0;
        this.forma[1][0] = 0;
        this.forma[1][1] = 0;
        this.forma[1][2] = idTipo;
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

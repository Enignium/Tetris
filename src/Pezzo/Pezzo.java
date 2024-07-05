package Pezzo;//ricorda di importare raylib
import Mappa.Mappa;

public abstract class Pezzo {

    int posx;
    int posy;
    short[][] matrice; //array di array
    Mappa mappa;

    public Pezzo(Mappa mappa){
        this.mappa = mappa;
        this.posx = 0;
        this.posy = 0;
    }

    public abstract void daiForma();

    public void muovi(){
        //prende gli input da raylib
    };



}

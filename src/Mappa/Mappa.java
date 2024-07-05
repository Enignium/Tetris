package Mappa;

import Pezzo.Pezzo;

public class Mappa {

    short[][] caselle;

    public Mappa(){
        caselle = new short[10][20];
    };

    public int valutaCollisione(Pezzo pezzo){
        return 0; //SISTEMA
        //Se rileva un blocco sotto di lui si ferma;
        //Se tocca il limite superiore della mappa è gameover;
    };

    public boolean valutaRiga(){};

    public void scendiBlocchi(){};






}

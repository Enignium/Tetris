package shared;

import java.io.Serializable;

public class Punteggio implements Serializable {

    private int punteggio;
    public String nome;

    public Punteggio(){
        this.punteggio = 0;
        nome = "";
    }
    //Overloading
    public Punteggio(int punteggio, String nome){
        this.punteggio = punteggio;
        this.nome = nome;
    }

    public int getPunteggio(){
        return this.punteggio;
    }

    public void punteggioAumentaPunteggioPiazza(float gameSpeed){
        this.punteggio += gameSpeed*100;
    }

    public void punteggioAumentaPunteggioTetris(float gameSpeed,int countRighe){
        this.punteggio += (int) (gameSpeed*500 * countRighe);
    }


    public void setPunteggio(int score) {
        this.punteggio = score;
    }
}

public class Punteggio {

    int punteggio;

    public Punteggio(){
        this.punteggio = 0;
    }

    int getPunteggio(){
        return this.punteggio;
    }

    public void punteggioUp(int gameSpeed){
        this.punteggio += gameSpeed*500;
    }

}


import mappa.Mappa;
import pezzo.*;
import java.util.Random;

public class GameManager{

    Mappa mappa;
    Punteggio punteggio;
    Pezzo pezzo;
    InputManager input;
    Render render;
    boolean gameOver = false;



    public GameManager(Render render, Mappa mappa){
        this.render = render;
        this.input = new InputManager(render.renderer);
        this.mappa = mappa;
    }

    public void start(){}

    public void game(){}

    public Pezzo nuovoPezzo(){
        Random random = new Random();
        Pezzo pezzo;
        int tipo = random.nextInt(7);
        pezzo = switch (tipo) {
            case 0 -> new PezzoC(this.mappa);
            case 1 -> new PezzoLr(this.mappa);
            case 2 -> new PezzoLs(this.mappa);
            case 3 -> new PezzoR(this.mappa);
            case 4 -> new PezzoSr(this.mappa);
            case 5 -> new PezzoSs(this.mappa);
            case 6 -> new PezzoT(this.mappa);
            default -> throw new IllegalStateException("Unexpected value: " + tipo);
        };
        if(!pezzo.valutaSpawn()) {
            System.out.println("GameOver");
            gameOver = true;
        }
        return pezzo;
    }

    public boolean manageInput(Pezzo pezzo){

        boolean ret = false;


//  Ritorna true se detecta un movimento, quindi su pezzo,
//  se ritorna true allora renderizza la mappa, così renderizza ad ogni movimento avvenuto e non ad ogni frame

        switch(input.inputListen()){
            case 0:
                break;
            case 1:
                ret = pezzo.muovi(1);
                break;
            case 2:
                ret = pezzo.muovi(2);
                break;
            case 3:
                ret = pezzo.ruota();
                break;
            case 4:
                ret = pezzo.scendi();
        }
        return  ret;
    }

    public void gameOver(){

    }





}
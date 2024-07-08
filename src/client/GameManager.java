package client;

import client.pezzo.*;
import shared.Punteggio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameManager{

    Mappa mappa;
    Punteggio punteggio;
    Pezzo pezzo;
    InputManager input;
    Render render;
    Punteggio score;
    Client client;
    ArrayList<Punteggio> classifica;

    private boolean gameOver = false;
    private float gameSpeed;
    private boolean update;
    private float timeAccumulator;
    private int lineErased;
    private boolean placed = false;
    private int gameSpeedUpCounter = 1;
    private int gameState = 1;
    private boolean mostraClassifica = false;

    public GameManager(Render render){
        this.render = render;
        this.input = new InputManager(render.renderer);
        this.score = new Punteggio();
        this.gameSpeed = 1.5F;
        this.update = true;
        this.timeAccumulator = 0;
        this.mappa = new Mappa(20, 12);
    }

    public void playGameState(){
        switch (gameState){
            case 0:
                game();
                break;
            case 1:
                menu();
                break;
            case 2:
                gameOver();
                break;
            case 3:
                classifica();
                break;
        }
    }

    private void classifica() {


        if(!mostraClassifica) {

            mostraClassifica = true;
            client = new Client();

            try {
                classifica = client.getPunteggio();

            } catch (IOException e) {
                System.out.println(e.getMessage());
                mostraClassifica = false;
            }
        }
        render.renderClassifica(classifica);
        manageInput();

    }

    public void game() {
        float delta = render.renderer.core.GetFrameTime();
        this.accumulateTime(delta);
        this.manageInput();
        this.scendiPezzo();
        this.manageScore();
        this.manageGameSpeed();
        if (this.isUpdate()) {
            render.renderMap(this.mappa);
        }
        render.renderPunteggio(this.score);
    }

    public void menu() {
        render.renderMenu();
        manageInput();
    }

    public void nuovoPezzo() {
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
        if (!pezzo.valutaSpawn()) {
            gameState = 2;
        }
        System.out.println("Nuovo pezzo");
        this.pezzo = pezzo;
    }

    public void scendiPezzo() {
        if (timeAccumulator > 1 / this.gameSpeed) {
            timeAccumulator = 0;
            if (pezzo.scendi()) {
                update = true;
            } else {
                this.lineErased = this.mappa.valutaRiga();
                placed = true;
                gameSpeedUpCounterUp();
                nuovoPezzo();
            }
        }
    }

    public void manageScore() {
        if (this.lineErased > 0) {
            this.score.punteggioAumentaPunteggioTetris(this.gameSpeed, this.lineErased);
            lineErased = 0;
        } else if (placed) {
            this.score.punteggioAumentaPunteggioPiazza(this.gameSpeed);
            placed = false;
        }
    }

    public void accumulateTime(float delta) {
        this.timeAccumulator += delta;
    }

    public boolean manageInput() {
        boolean ret = false;

        if (gameState == 0) {
            switch (input.inputListen()) {
                case 0:
                    break;
                case 1:
                    ret = this.pezzo.muovi(1);
                    break;
                case 2:
                    ret = this.pezzo.muovi(2);
                    break;
                case 3:
                    ret = this.pezzo.ruota();
                    break;
                case 4:
                    ret = this.pezzo.scendi();
                default:
                    break;
            }
            return ret;
        }

        if (gameState == 1) {
            int listenInput = input.inputListen();
            if (listenInput == 3) {
                gameState = 0;
                nuovoPezzo();
            } else if (listenInput == 1) {
                gameState = 3;
                mostraClassifica = false;
            }
        }

        // stato di classifica
        if (gameState == 3) {
            int listenInput = input.inputListen();
            if (listenInput == 3) {
                gameState = 1;
                mostraClassifica = false;
            }
        }

        return ret;
    }

    public void gameOver() {
        client = new Client();
        render.renderGameOver(score, mappa);
        input.getPlayerName(score);
        if (input.enterPressed) {
            input.enterPressed = false;
            try {
                client.addPunteggio(score);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
            resetGame();
        }
    }

    private void gameSpeedUpCounterUp() {
        gameSpeedUpCounter++;
    }

    private void gameSpeedUpCounterReset() {
        gameSpeedUpCounter = 1;
    }

    public void manageGameSpeed() {
        if (gameSpeedUpCounter % 5 == 0) {
            gameSpeed = (float) Math.pow(gameSpeed, 1.08);
            gameSpeedUpCounterReset();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isUpdate() {
        return update;
    }

    private void resetGame() {
        this.gameOver = false;
        this.gameSpeed = 1.1F;
        this.update = true;
        this.timeAccumulator = 0;
        this.lineErased = 0;
        this.placed = false;
        this.gameSpeedUpCounter = 1;
        this.gameState = 1;
        this.score = new Punteggio();
        this.mappa = new Mappa(20, 12);
        this.pezzo = null;
        this.input = new InputManager(render.renderer);
    }
}

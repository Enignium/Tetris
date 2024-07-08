package client;


import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import shared.Punteggio;

import java.util.ArrayList;

public class Render {

    Mappa mappa;
    Raylib renderer = new Raylib();

    public void renderMap(Mappa mappa) {

        int rectSize = 80;
        int borderSize = 70;
        int offset = (rectSize - borderSize) / 2;

        for (int i = 0; i < mappa.rows; i++) {

            for (int j = 0; j < mappa.cols; j++) {

                if (mappa.caselle[i][j + mappa.borderOffset] == 0) {
                    renderer.shapes.DrawRectangle(j * rectSize, i * rectSize, rectSize, rectSize, Color.BLACK);
                } else {
                    Color blockColor;
                    switch(mappa.caselle[i][j + mappa.borderOffset]){
                        case 1: blockColor = Color.MAROON;
                            break;
                        case 2: blockColor = Color.PURPLE;
                            break;
                        case 3: blockColor = Color.GOLD;
                            break;
                        case 4: blockColor = Color.PINK;
                            break;
                        case 5: blockColor = Color.LIME;
                            break;
                        case 6: blockColor = Color.BROWN;
                            break;
                        case 7: blockColor = Color.SKYBLUE;
                            break;
                        default: blockColor = Color.GRAY;
                            break;
                    }

                    renderer.shapes.DrawRectangle(j * rectSize, i * rectSize, rectSize, rectSize, Color.BLACK);
                    renderer.shapes.DrawRectangle(j * rectSize + offset, i * rectSize + offset, borderSize, borderSize, blockColor);

                }
            }
        }
    }

    public void renderPunteggio(Punteggio punteggio) {


        renderer.text.DrawText(Integer.toString(punteggio.getPunteggio()),
                renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(Integer.toString(punteggio.getPunteggio()), 80) / 2,
                80, 86, Color.RAYWHITE);

    }

    public void renderMenu() {

        Color titleColor = Color.GOLD;
        Color optionColor = new Color(200, 200, 200, 255);

        String title = "TETRIS";
        String start = "PREMI R PER INIZIARE";
        String leaderboard = "PREMI <- PER VISUALIZZARE LA CLASSIFICA";
        String exit = "PREMI -> PER USCIRE";

        int titleX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(title, 100) / 2;
        int titleY = 350;
        int startX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(start, 40) / 2;
        int startY = 700;
        int leaderboardX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(leaderboard, 40) / 2;
        int leaderboardY = 800;
        int exitX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(exit, 40) / 2;
        int exitY = 850;

        renderer.core.ClearBackground(Color.BLACK);
        renderer.text.DrawText(title, titleX, titleY, 100, titleColor);
        renderer.text.DrawText(start, startX, startY, 40, optionColor);
        renderer.text.DrawText(leaderboard, leaderboardX, leaderboardY, 40, optionColor);
        renderer.text.DrawText(exit, exitX, exitY, 40, optionColor);
    }

    public void renderGameOver(Punteggio score, Mappa map) {
        // Colori
        Color gameOverColor = Color.RED;
        Color optionColor = Color.RAYWHITE;
        Color inputColor = Color.RAYWHITE;

        String gameOver = "GAME OVER";
        String promptName = "INSERISCI IL TUO NOME:";
        String restart = "PREMI ENTER PER RICOMINCIARE";


        int gameOverX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(gameOver, 100) / 2;
        int gameOverY = 150;
        int promptNameX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(promptName, 40) / 2;
        int promptNameY = 300;
        int inputX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(score.nome, 40) / 2;
        int inputY = 350;
        int restartX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText(restart, 40) / 2;
        int restartY = 500;

        renderer.core.ClearBackground(Color.RAYWHITE);
        renderMap(map);
        renderPunteggio(score);
        renderer.text.DrawText(gameOver, gameOverX, gameOverY, 100, gameOverColor);
        renderer.text.DrawText(promptName, promptNameX, promptNameY, 40, optionColor);
        renderer.text.DrawText(score.nome, inputX, inputY, 40, inputColor);
        renderer.text.DrawText(restart, restartX, restartY, 40, optionColor);
    }

    public void renderClassifica(ArrayList<Punteggio> classifica){


        int startX = renderer.core.GetScreenWidth() / 2;
        int startY = 300;
        int gameOverX = renderer.core.GetScreenWidth() / 2 - renderer.text.MeasureText("CLASSIFICA", 100) / 2;
        int gameOverY = 150;
        try {
            int size = classifica.size();
            renderer.core.ClearBackground(Color.BLACK);
            renderer.text.DrawText("CLASSIFICA", gameOverX, gameOverY, 100, Color.RAYWHITE);
            for (int i = 0; i < size; i++) {
                String Nome = classifica.get(i).nome;
                String Score = Integer.toString(classifica.get(i).getPunteggio());
                renderer.text.DrawText(Nome  +": " +  Score, startX - renderer.text.MeasureText(Nome + Score, 40) / 2, startY, 40, Color.GOLD);
                startY += 60;

            }

        }
        catch (NullPointerException a){
            renderer.core.ClearBackground(Color.RAYWHITE);
            renderer.text.DrawText("Impossibile ottenere la classifica dal Server", startX - renderer.text.MeasureText("Impossibile ottenere la classifica dal Server", 40) / 2, startY, 40, Color.BLACK);
        }


    }


}
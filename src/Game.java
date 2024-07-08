import mappa.Mappa;
import pezzo.*;



public class Game {

    public static void main(String[] args) {


        Render render = new Render();
        GameManager gameManager = new GameManager(render);

        render.renderer.core.InitWindow(gameManager.mappa.cols * 80, gameManager.mappa.rows * 80, "TETRIS");
        render.renderer.core.SetTargetFPS(60);
        render.renderMap(gameManager.mappa);

        while (!render.renderer.core.WindowShouldClose() && (!gameManager.isGameOver())) {

            render.renderer.core.BeginDrawing();

            gameManager.playGameState();

            render.renderer.core.EndDrawing();
        }
    }

}


import mappa.Mappa;
import pezzo.*;



public class Game {

    public static void main(String[] args) {

        Mappa map = new Mappa(20, 12);
        Render render = new Render(map);
        Pezzo pezzo;
        GameManager gameManager = new GameManager(render,map);
        boolean update = true;

        float gameSpeed = 4F;
        float delta;
        float timeAccumulator = 0;
        pezzo = gameManager.nuovoPezzo();

        render.renderer.core.InitWindow(map.cols * 80, map.rows * 80, "TETRIS");
        render.renderer.core.SetTargetFPS(60);

        while (!render.renderer.core.WindowShouldClose()) {

            delta = render.renderer.core.GetFrameTime();
            timeAccumulator+=delta;

            render.renderer.core.BeginDrawing();


            update = gameManager.manageInput(pezzo);

            if(timeAccumulator > 2/gameSpeed) {
                timeAccumulator = 0;
                if(pezzo.scendi()){
                    update = true;
                }
                else{
                    pezzo = gameManager.nuovoPezzo();
                }
            }

            if(update)
                render.renderMap();
            update = false;

            render.renderer.core.EndDrawing();
        }
    }

}


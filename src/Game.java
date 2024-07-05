import mappa.Mappa;
import pezzo.*;



public class Game {

    public static void main(String[] args) {

        Mappa map = new Mappa(20, 12);
        Render render = new Render(map);
        PezzoT c  = new PezzoT(map);
        c.piazzaPezzo();

        render.renderer.core.InitWindow(map.cols * 80, map.rows * 80, "TETRIS");

        while (!render.renderer.core.WindowShouldClose()) {

            render.renderer.core.BeginDrawing();
            render.renderMap();


            render.renderer.core.EndDrawing();
        }
    }

}


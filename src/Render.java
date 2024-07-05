import mappa.Mappa;
import pezzo.Pezzo;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;


public class Render {

    Mappa mappa;
    Raylib renderer = new Raylib();

    public Render(Mappa mappa) {
        this.mappa = mappa;
    }

    public void renderMap() {
        for (int i = 0; i < mappa.rows; i++) {
            for (int j = 0; j < mappa.cols; j++) {
                if (mappa.caselle[i][j + mappa.borderOffset] == 0)
                    renderer.shapes.DrawRectangle(j * 80, i * 80, 80, 80, Color.GRAY);
                else
                    renderer.shapes.DrawRectangle(j * 80, i * 80, 80, 80, Color.MAROON);
            }
        }
    }

}





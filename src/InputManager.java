import com.raylib.java.Raylib;
import com.raylib.java.core.input.Keyboard;

import java.security.Key;

public class InputManager {
    Raylib rlj;
    boolean enterPressed = false;

    public InputManager(Raylib rlj){
        this.rlj = rlj;
    }

    public int inputListen(){

        if(rlj.core.IsKeyPressed(Keyboard.KEY_LEFT))
            return 1;
        if(rlj.core.IsKeyPressed(Keyboard.KEY_RIGHT))
            return 2;
        if(rlj.core.IsKeyPressed(Keyboard.KEY_R))
            return 3;
        if(rlj.core.IsKeyDown(Keyboard.KEY_DOWN))
            return 4;
        if(rlj.core.IsKeyDown(Keyboard.KEY_ENTER))
            return 5;

        return 0;

    }

    public void getPlayerName(Punteggio score){

        int key = rlj.core.GetKeyPressed();
        if (key >= 32 && key <= 125) {
            score.nome += (char) key;
        } else if (key == Keyboard.KEY_BACKSPACE && !score.nome.isEmpty()) {
            score.nome = score.nome.substring(0, score.nome.length() - 1);
        } else if (key == Keyboard.KEY_ENTER) {
            enterPressed = true;
        }
    }


}


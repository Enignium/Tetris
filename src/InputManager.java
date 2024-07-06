import com.raylib.java.Raylib;
import com.raylib.java.core.input.Keyboard;

public class InputManager {
    Raylib rlj;

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
        return 0;

    }

}

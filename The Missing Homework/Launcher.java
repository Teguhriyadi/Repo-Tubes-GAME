import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Launcher
{
    public static void main (String[] args)
    {
        Game myGame = new HomeworkGame();
        LwjglApplication launcher = new LwjglApplication( myGame, "Pekerjaan rumah yang hilang", 800, 600 );
    }
}
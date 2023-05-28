import engine.launcher.Launcher;
import engine.launcher.Parameters;
import engine.launcher.SpawnMode;
import engine.window.Window;
import org.joml.Vector4f;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        //launcher.run();
        launcher.runWithoutLauncher();
    }
}

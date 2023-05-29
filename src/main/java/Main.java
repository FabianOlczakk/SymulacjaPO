import engine.launcher.Launcher;
import engine.launcher.Parameters;
import engine.launcher.SpawnMode;
import org.joml.Vector4f;

public class Main {

    public static void main(String[] args) {
        Launcher launcher;

        Parameters simulationParameters = new Parameters(
                2560,
                1440,
                new Vector4f(0.0f, 1.0f, 0.7f, 1.0f),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                0.004f,
                0.65f,
                5_000_000,
                0.0f,
                SpawnMode.RANDOM_CIRCLE,
                80.0f,
                -10.0f,
                20.0f,
                1,
                120.0f,
                -3.0f,
                60.0f,
                1
        );

        // Run with settings window
        launcher = new Launcher();

        //Run without settings window
        //launcher = new Launcher(2560, 1440, simulationParameters);

    }
}

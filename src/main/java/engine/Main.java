package engine;

import engine.launcher.Launcher;
import engine.launcher.Parameters;
import engine.launcher.SpawnMode;
import org.joml.Vector4f;

/**
 * The main class responsible for launching the simulation.
 */
public class Main {

    /**
     * The main method that launches the simulation.
     *
     * @param args Arguments passed to the program.
     */
    public static void main(String[] args) {
        Launcher launcher;

        // Configure simulation parameters
        Parameters simulationParameters = new Parameters(
                2560,
                1440,
                new Vector4f(0.4f, 0.3f, 0.3f, 1.0f),
                new Vector4f(0.3f, 0.6f, 0.5f, 1.0f),
                0.001f,
                0.30f,
                15_000_000,
                0.2f,
                SpawnMode.RANDOM_CIRCLE,
                50.0f,
                -15.0f,
                50.0f,
                1,
                110.0f,
                -15.0f,
                10.0f,
                1
        );

        // Launch the simulation with a settings window
        launcher = new Launcher();

        // Alternative option: launch the simulation without a settings window
        //launcher = new Launcher(1920, 1080, simulationParameters);
    }
}
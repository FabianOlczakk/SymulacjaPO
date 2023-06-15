package engine.launcher;

import org.joml.Vector4f;

/**
 * The Parameters class represents the various settings and parameters
 * required for the simulation.
 */
public class Parameters {
    // Main settings
    private final int WIDTH;               // Width of the simulation window
    private final int HEIGHT;              // Height of the simulation window
    private final int WINDOW_WIDTH;        // Width of the window
    private final int WINDOW_HEIGHT;       // Height of the window
    private final SpawnMode SPAWN_MODE;    // Spawn mode for the simulation
    private final float EVAPORATE_SPEED;   // Evaporation speed setting
    private final float DIFFUSE_SPEED;     // Diffusion speed setting
    private final int AGENTS_COUNT;        // Number of agents in the simulation

    // A cells settings
    private final Vector4f CELL_COLOR_A;          // Color of A cells
    private final float SENSOR_ANGLE_SPACING_A;   // Sensor angle spacing for A cells
    private final float TURN_SPEED_A;             // Turn speed for A cells
    private final float SENSOR_OFFSET_DISTANCE_A; // Sensor offset distance for A cells
    private final int SENSOR_SIZE_A;              // Sensor size for A cells

    // B cells settings
    private final float CELL_B_PROBABILITY;       // Probability of spawning a B cell
    private final Vector4f CELL_COLOR_B;          // Color of B cells
    private final float SENSOR_ANGLE_SPACING_B;   // Sensor angle spacing for B cells
    private final float TURN_SPEED_B;             // Turn speed for B cells
    private final float SENSOR_OFFSET_DISTANCE_B; // Sensor offset distance for B cells
    private final int SENSOR_SIZE_B;              // Sensor size for B cells

    /**
     * Constructor that initializes the parameters with the provided values.
     *
     * @param WIDTH                    The width of the simulation window.
     * @param HEIGHT                   The height of the simulation window.
     * @param WINDOW_WIDTH             The width of the window.
     * @param WINDOW_HEIGHT            The height of the window.
     * @param CELL_COLOR_A             The color of A cells.
     * @param CELL_COLOR_B             The color of B cells.
     * @param EVAPORATE_SPEED          The evaporation speed setting.
     * @param DIFFUSE_SPEED            The diffusion speed setting.
     * @param AGENTS_COUNT             The number of agents in the simulation.
     * @param CELL_B_PROBABILITY       The probability of spawning a B cell.
     * @param SPAWN_MODE               The spawn mode setting.
     * @param SENSOR_ANGLE_SPACING_A   The sensor angle spacing for A cells.
     * @param TURN_SPEED_A             The turn speed for A cells.
     * @param SENSOR_OFFSET_DISTANCE_A The sensor offset distance for A cells.
     * @param SENSOR_SIZE_A            The sensor size for A cells.
     * @param SENSOR_ANGLE_SPACING_B   The sensor angle spacing for B cells.
     * @param TURN_SPEED_B             The turn speed for B cells.
     * @param SENSOR_OFFSET_DISTANCE_B The sensor offset distance for B cells.
     * @param SENSOR_SIZE_B            The sensor size for B cells.
     */
    public Parameters(int WIDTH, int HEIGHT, int WINDOW_WIDTH, int WINDOW_HEIGHT, Vector4f CELL_COLOR_A, Vector4f CELL_COLOR_B, float EVAPORATE_SPEED, float DIFFUSE_SPEED, int AGENTS_COUNT, float CELL_B_PROBABILITY, SpawnMode SPAWN_MODE, float SENSOR_ANGLE_SPACING_A, float TURN_SPEED_A, float SENSOR_OFFSET_DISTANCE_A, int SENSOR_SIZE_A, float SENSOR_ANGLE_SPACING_B, float TURN_SPEED_B, float SENSOR_OFFSET_DISTANCE_B, int SENSOR_SIZE_B) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.WINDOW_WIDTH = WINDOW_WIDTH;
        this.WINDOW_HEIGHT = WINDOW_HEIGHT;
        this.CELL_COLOR_A = CELL_COLOR_A;
        this.CELL_COLOR_B = CELL_COLOR_B;
        this.EVAPORATE_SPEED = EVAPORATE_SPEED;
        this.DIFFUSE_SPEED = DIFFUSE_SPEED;
        this.AGENTS_COUNT = AGENTS_COUNT;
        this.CELL_B_PROBABILITY = CELL_B_PROBABILITY;
        this.SPAWN_MODE = SPAWN_MODE;
        this.SENSOR_ANGLE_SPACING_A = SENSOR_ANGLE_SPACING_A;
        this.TURN_SPEED_A = TURN_SPEED_A;
        this.SENSOR_OFFSET_DISTANCE_A = SENSOR_OFFSET_DISTANCE_A;
        this.SENSOR_SIZE_A = SENSOR_SIZE_A;
        this.SENSOR_ANGLE_SPACING_B = SENSOR_ANGLE_SPACING_B;
        this.TURN_SPEED_B = TURN_SPEED_B;
        this.SENSOR_OFFSET_DISTANCE_B = SENSOR_OFFSET_DISTANCE_B;
        this.SENSOR_SIZE_B = SENSOR_SIZE_B;
    }
    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public Vector4f getCELL_COLOR_A() {
        return CELL_COLOR_A;
    }

    public Vector4f getCELL_COLOR_B() {
        return CELL_COLOR_B;
    }

    public float getEVAPORATE_SPEED() {
        return EVAPORATE_SPEED;
    }

    public float getDIFFUSE_SPEED() {
        return DIFFUSE_SPEED;
    }

    public int getAGENTS_COUNT() {
        return AGENTS_COUNT;
    }

    public float getCELL_B_PROBABILITY() {
        return CELL_B_PROBABILITY;
    }

    public SpawnMode getSPAWN_MODE() {
        return SPAWN_MODE;
    }

    public float getSENSOR_ANGLE_SPACING_A() {
        return SENSOR_ANGLE_SPACING_A;
    }

    public float getTURN_SPEED_A() {
        return TURN_SPEED_A;
    }

    public float getSENSOR_OFFSET_DISTANCE_A() {
        return SENSOR_OFFSET_DISTANCE_A;
    }

    public int getSENSOR_SIZE_A() {
        return SENSOR_SIZE_A;
    }

    public float getSENSOR_ANGLE_SPACING_B() {
        return SENSOR_ANGLE_SPACING_B;
    }

    public float getTURN_SPEED_B() {
        return TURN_SPEED_B;
    }

    public float getSENSOR_OFFSET_DISTANCE_B() {
        return SENSOR_OFFSET_DISTANCE_B;
    }

    public int getSENSOR_SIZE_B() {
        return SENSOR_SIZE_B;
    }

    public int getWINDOW_WIDTH() {
        return WINDOW_WIDTH;
    }

    public int getWINDOW_HEIGHT() {
        return WINDOW_HEIGHT;
    }
}

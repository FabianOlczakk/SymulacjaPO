package engine.launcher;

import org.joml.Vector4f;

public class Parameters {
    // MAIN SETTINGS
    private final int WIDTH;
    private final int HEIGHT;
    private final SpawnMode SPAWN_MODE;
    private final float EVAPORATE_SPEED;
    private final float DIFFUSE_SPEED;
    private final int AGENTS_COUNT;

    // A CELLS SETTINGS
    private final Vector4f CELL_COLOR_A;
    private final float SENSOR_ANGLE_SPACING_A;
    private final float TURN_SPEED_A;
    private final float SENSOR_OFFSET_DISTANCE_A;
    private final int SENSOR_SIZE_A;

    // B CELLS SETTINGS
    private final float CELL_B_PROBABILITY;
    private final Vector4f CELL_COLOR_B;
    private final float SENSOR_ANGLE_SPACING_B;
    private final float TURN_SPEED_B;
    private final float SENSOR_OFFSET_DISTANCE_B;
    private final int SENSOR_SIZE_B;

    public Parameters(int WIDTH, int HEIGHT, Vector4f CELL_COLOR_A, Vector4f CELL_COLOR_B, float EVAPORATE_SPEED, float DIFFUSE_SPEED, int AGENTS_COUNT, float CELL_B_PROBABILITY, SpawnMode SPAWN_MODE, float SENSOR_ANGLE_SPACING_A, float TURN_SPEED_A, float SENSOR_OFFSET_DISTANCE_A, int SENSOR_SIZE_A, float SENSOR_ANGLE_SPACING_B, float TURN_SPEED_B, float SENSOR_OFFSET_DISTANCE_B, int SENSOR_SIZE_B) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
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
}

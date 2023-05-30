package engine.launcher;

/**
 * The SpawnMode enumeration represents different spawning modes for the simulation.
 */
public enum SpawnMode {
    POINT,          // Agents spawn at a single point
    RANDOM,         // Agents spawn randomly throughout the simulation area
    INWARD_CIRCLE,  // Agents spawn in a circle, moving towards the center
    RANDOM_CIRCLE   // Agents spawn randomly within a circular area
}
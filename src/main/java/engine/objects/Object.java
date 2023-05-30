package engine.objects;

import engine.components.Mesh;
import engine.components.Texture;
import org.joml.Vector3f;

/**
 * The Object class represents a 3D object in the simulation.
 * It defines the object's position, rotation, scale, mesh, and texture.
 */
public class Object {

    private Vector3f position; // The position of the object in 3D space
    private Vector3f rotation; // The rotation of the object around each axis (x, y, z)
    private Vector3f scale;    // The scale of the object along each axis (x, y, z)
    private Mesh mesh;         // The mesh that defines the object's geometry
    private Texture texture;   // The texture that defines the object's appearance

    /**
     * Gets the position of the object.
     *
     * @return The position as a Vector3f.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Sets the position of the object.
     *
     * @param position The new position as a Vector3f.
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * Gets the rotation of the object.
     *
     * @return The rotation as a Vector3f.
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * Sets the rotation of the object.
     *
     * @param rotation The new rotation as a Vector3f.
     */
    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    /**
     * Gets the scale of the object.
     *
     * @return The scale as a Vector3f.
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * Sets the scale of the object.
     *
     * @param scale The new scale as a Vector3f.
     */
    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    /**
     * Gets the mesh of the object.
     *
     * @return The mesh as a Mesh.
     */
    public Mesh getMesh() {
        return mesh;
    }

    /**
     * Sets the mesh of the object.
     *
     * @param mesh The new mesh as a Mesh.
     */
    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    /**
     * Gets the texture of the object.
     *
     * @return The texture as a Texture.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Sets the texture of the object.
     *
     * @param texture The new texture as a Texture.
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
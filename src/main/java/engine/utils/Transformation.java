package engine.utils;

import engine.objects.PlaneObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {

    private final Matrix4f orthoMatrix;
    private final Matrix4f orthographic2DMatrix;

    /**
     * Constructs a Transformation object and initializes the matrices.
     */
    public Transformation() {
        orthoMatrix = new Matrix4f();
        orthographic2DMatrix = new Matrix4f();
    }

    /**
     * Retrieves the orthographic 2D projection matrix.
     *
     * @param left   The left coordinate of the view frustum.
     * @param right  The right coordinate of the view frustum.
     * @param bottom The bottom coordinate of the view frustum.
     * @param top    The top coordinate of the view frustum.
     * @param zNear  The near clipping plane of the view frustum.
     * @param zFar   The far clipping plane of the view frustum.
     * @return The orthographic 2D projection matrix.
     */
    public final Matrix4f getOrtho2DProjectionMatrix(float left, float right, float bottom, float top, float zNear, float zFar) {
        orthoMatrix.identity();
        orthoMatrix.setOrtho(left, right, bottom, top, zNear, zFar);
        return orthoMatrix;
    }

    /**
     * Builds the orthographic projection and model matrix for a plane object.
     *
     * @param plane       The plane object.
     * @param orthoMatrix The orthographic projection matrix.
     * @return The resulting orthographic projection and model matrix.
     */
    public Matrix4f buildOrtoProjModelMatrix(PlaneObject plane, Matrix4f orthoMatrix) {
        Vector3f rotation = plane.getRotation();
        Matrix4f modelMatrix = new Matrix4f();
        modelMatrix.identity()
                .translate(plane.getPosition())
                .rotateX((float) Math.toRadians(-rotation.x))
                .rotateY((float) Math.toRadians(-rotation.y))
                .rotateZ((float) Math.toRadians(-rotation.z))
                .scale(plane.getScale().x, plane.getScale().y, plane.getScale().z);
        orthographic2DMatrix.set(orthoMatrix);
        orthographic2DMatrix.mul(modelMatrix);
        return orthographic2DMatrix;
    }
}

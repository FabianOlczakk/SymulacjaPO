package engine.utils;

import engine.objects.PlaneObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {

    private final Matrix4f orthoMatrix;
    private final Matrix4f orthographic2DMatrix;

    public Transformation() {
        orthoMatrix = new Matrix4f();
        orthographic2DMatrix = new Matrix4f();
    }

    public final Matrix4f getOrtho2DProjectionMatrix(float left, float right, float bottom, float top, float zNear, float zFar) {
        orthoMatrix.identity();
        orthoMatrix.setOrtho(left, right, bottom, top,zNear,zFar);
        return orthoMatrix;
    }

    public Matrix4f buildOrtoProjModelMatrix(PlaneObject plane, Matrix4f orthoMatrix) {
        Vector3f rotation = plane.getRotation();
        Matrix4f modelMatrix = new Matrix4f();
        modelMatrix.identity().translate(plane.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(plane.getScale().x,plane.getScale().y,plane.getScale().z);
        orthographic2DMatrix.set(orthoMatrix);
        orthographic2DMatrix.mul(modelMatrix);
        return orthographic2DMatrix;
    }
}

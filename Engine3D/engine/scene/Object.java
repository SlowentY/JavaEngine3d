package Engine3D.engine.scene;

import Engine3D.engine.graphics.Mesh;
import org.joml.Vector3f;

public class Object {
    private Mesh mesh;
    private Vector3f velocity;
    private Vector3f acceleration;
    private float mass;
    private Vector3f position;

    public Object(Mesh mesh, float mass, Vector3f position, Vector3f velocity)
    {
        this.acceleration = new Vector3f(0.0f, 0.0f, 0.0f);
        this.velocity = velocity;
        this.mass = mass;
        this.mesh = mesh;
        this.position = position;

        this.mesh.setPosition(position);
    }

    public void simulate(long diffTimeMillis, Vector3f forceVec) {
        velocity.x = velocity.x+acceleration.x*(float)(diffTimeMillis)/1000;
        velocity.y = velocity.y+acceleration.y*(float)(diffTimeMillis)/1000;
        velocity.z = velocity.z+acceleration.z*(float)(diffTimeMillis)/1000;

        if(position.y <= -10.0f)
        {
            position.y = -10.0f;
            velocity.x = 0.0f;
            velocity.y = 0.0f;
            velocity.z = 0.0f;
        }

        position.x = position.x+velocity.x*(float)(diffTimeMillis)/1000;
        position.y = position.y+velocity.y*(float)(diffTimeMillis)/1000;
        position.z = position.z+velocity.z*(float)(diffTimeMillis)/1000;

        mesh.setPosition(position);
    }
    public void setAcceleration(Vector3f acceleration)
    {
        this.acceleration = acceleration;
    }
    public Mesh getMesh()
    {
        return mesh;
    }

    public void cleanup()
    {
        mesh.cleanup();
    }
}

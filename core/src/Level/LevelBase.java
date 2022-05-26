package Level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.SkeletonMeshRenderer;

public abstract class LevelBase
{
    protected OrthographicCamera camera;
    protected LevelManager levelManager;

    public LevelBase(LevelManager levelManager)
    {
        this.levelManager = levelManager;
        camera = new OrthographicCamera();
    }

    protected abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch sp, PolygonSpriteBatch psp, SkeletonMeshRenderer renderer);
    public abstract void dispose();

}



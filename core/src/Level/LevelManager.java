package Level;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.SkeletonMeshRenderer;

import java.util.Stack;

public class LevelManager
{
    private Stack<LevelBase> levels;

    public LevelManager()
    {
        levels = new Stack<>();
    }

    public void push(LevelBase level)
    {
        levels.push(level);
    }

    public void pop()
    {
        levels.pop().dispose();
    }

    public void update(float deltaTime)
    {
        levels.peek().update(deltaTime);
    }

    public void render(SpriteBatch sp, PolygonSpriteBatch psp, SkeletonMeshRenderer renderer)
    {
        levels.peek().render(sp, psp, renderer);
    }


}

package Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

import java.util.Stack;

public class LevelManager
{
    private static LevelManager instance = new LevelManager();
    private Stack<LevelBase> levels;

    private LevelManager()
    {
        levels = new Stack<>();
    }

    public static LevelManager getInstance()
    {
        return instance;
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

    public void render()
    {
        levels.peek().render();
    }

    public void resize(int width, int height)
    {
        levels.peek().resize(width, height);
    }

}

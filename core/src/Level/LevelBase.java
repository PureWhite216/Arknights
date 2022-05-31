package Level;

import Audio.AudioManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class LevelBase
{
    protected OrthographicCamera camera;
    protected boolean isLevelEnd = false;
    protected boolean isLevelEnter = false;
    protected float timer = 0;
    protected float enterTime = 1f;
    protected float leaveTime = 1f;
    protected Image blackMask;
    protected Stage stage;
    protected SpriteBatch spriteBatch;
    protected TwoColorPolygonBatch twoColorPolygonBatch;
    protected SkeletonRenderer renderer;

    public LevelBase()
    {
        spriteBatch = new SpriteBatch();
        twoColorPolygonBatch = new TwoColorPolygonBatch();
        renderer = new SkeletonRenderer();

        camera = new OrthographicCamera();
        stage = new Stage(new StretchViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        stage.getViewport().setCamera(camera);
        camera.viewportWidth = 1920;
        camera.viewportHeight = 1080;
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        blackMask = new Image(new Texture(Gdx.files.internal("assets/Images/BlackMask.png")));
        blackMask.setColor(1, 1, 1, 1);
        blackMask.setTouchable(Touchable.disabled);
//        System.out.println("LevelBase: " + stage.getActors().size);

    }
    protected void enterLevel(float deltaTime)
    {
        stage.addActor(blackMask);
        if(!isLevelEnter)
        {
            timer += deltaTime;
            if(enterTime - timer > 0)
            {
                this.blackMask.setColor(1, 1, 1, 1 - timer / enterTime);
            }
            else
            {
                isLevelEnter = true;
                timer = 0;
            }
//            System.out.println("LevelBase: " + stage.getActors().size);
        }
    }
    protected void leaveLevel(float deltaTime)
    {
        if(isLevelEnd)
        {
            timer += deltaTime;
            if(leaveTime - timer > 0)
            {
                AudioManager.getInstance().getBgm().setVolume(AudioManager.defaultVolume * ((leaveTime - timer) / leaveTime));
                this.blackMask.setColor(1, 1, 1, timer / leaveTime);
            }
            else
            {
                LevelManager.getInstance().pop();
                LevelManager.getInstance().push(loadNextLevel());
            }
        }
    }
    protected abstract LevelBase loadNextLevel();
    protected abstract void handleInput();
    public void update(float deltaTime)
    {
        enterLevel(deltaTime);
        leaveLevel(deltaTime);
        stage.act();
        camera.update();
        twoColorPolygonBatch.setProjectionMatrix(camera.combined);
        spriteBatch.setProjectionMatrix(camera.combined);
        stage.getBatch().setProjectionMatrix(camera.combined);
    }
    public abstract void render();
    public abstract void dispose();
    public abstract void resize(int width, int height);
}



package Level;

import Audio.AudioManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

import java.lang.annotation.Target;

public class StartLevel extends LevelBase
{
    private Table table = new Table();
    private Image background;
    private Button start;
    private Texture startTexture;
    private Texture downTexture;


    public StartLevel()
    {
        super();
//        table.setFillParent(true);
//        stage.addActor(table);
//        table.setDebug(true);

        background = new Image(new Texture(Gdx.files.internal("assets/BackGround/MainMenuBG.png")));
        background.setPosition(0, 0);
        stage.addActor(background);

        startTexture = new Texture(Gdx.files.internal("assets/Button/GameStart.png"));
        downTexture = new Texture(Gdx.files.internal("assets/Button/GameStart.png"));
        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(startTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));
        start = new Button(style);
        start.setPosition(stage.getWidth() / 2 - start.getWidth() / 2, stage.getHeight() * 0.55f);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Start");
                isLevelEnd = true;
            }
        });
        stage.addActor(start);

        AudioManager.getInstance().playBGM(1);

        System.out.println("Game Start");
        System.out.printf("%f %f %f", start.getWidth(), start.getHeight(), start.getX());

    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new TestLevel();
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }


}

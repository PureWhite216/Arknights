package Level;

import Audio.AudioManager;
import UI.OperatorFormationTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class StoryLevel extends LevelBase
{
    protected OperatorFormationTable operatorFormationTable;
    private static Texture Texture_background = new Texture(Gdx.files.internal("assets/BackGround/StoryLevelBG.png"));
    private Image Image_background;

    public StoryLevel()
    {
        Texture_background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image_background = new Image(Texture_background);
        stage.addActor(Image_background);

        operatorFormationTable = new OperatorFormationTable();
        operatorFormationTable.addToLevel(stage, this);
        AudioManager.getInstance().playBGM(2);
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new TestLevel();
    }

    @Override
    public void render()
    {
        stage.draw();
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }
}

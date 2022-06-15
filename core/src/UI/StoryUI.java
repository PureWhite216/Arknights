package UI;

import Level.BattleLevelBase;
import Level.StoryLevel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class StoryUI
{
    protected Group group;
    protected StoryLevel level;
    protected static final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font/skill.fnt"));

    public StoryUI()
    {
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void addToLevel(Stage stage, StoryLevel level)
    {
        stage.addActor(group);
        this.level = level;
    }

    public void switchShow()
    {
        group.setVisible(!group.isVisible());
    }

    public void show()
    {
        group.setVisible(true);
    }

    public void hide()
    {
        group.setVisible(false);
    }

    public void setPosition(float x)
    {
        group.setX(x);
    }
}

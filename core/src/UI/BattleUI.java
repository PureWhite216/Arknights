package UI;

import Level.BattleLevelBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BattleUI
{
    protected Group group;
    protected int index;
    protected BattleLevelBase level;
    protected static final BitmapFont bitmapFont = new BitmapFont(Gdx.files.internal("assets/font/skill.fnt"));
    protected static final BitmapFont bitmapFontText = new BitmapFont(Gdx.files.internal("assets/font/text.fnt"));
    protected static final BitmapFont bitmapFontName = new BitmapFont(Gdx.files.internal("assets/font/name.fnt"));

    public BattleUI()
    {
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bitmapFontText.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bitmapFontName.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void addToLevel(Stage stage, BattleLevelBase level, int index)
    {
        stage.addActor(group);
        this.level = level;
        this.index = index;
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

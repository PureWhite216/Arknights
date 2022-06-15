package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class OperatorFormationTable extends StoryUI
{
    private static Texture Texture_operatorFormation = new Texture(Gdx.files.internal("assets/Images/OperatorFormationBackground.png"));
    private static Texture Texture_backUp = new Texture(Gdx.files.internal("assets/Button/Back.png"));
    private static Texture Texture_backHover = new Texture(Gdx.files.internal("assets/Button/BackHover.png"));
    private Image Image_operatorFormation;
    private Button Button_back;
    private Button.ButtonStyle Style_back;

    public OperatorFormationTable()
    {
        Texture_operatorFormation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        group= new Group();
        group.setVisible(false);

        Image_operatorFormation = new Image(Texture_operatorFormation);
        group.addActor(Image_operatorFormation);

        Style_back = new Button.ButtonStyle();
        Style_back.up = new TextureRegionDrawable(new TextureRegion(Texture_backUp));
        Style_back.over = new TextureRegionDrawable(new TextureRegion(Texture_backHover));

        Button_back = new Button(Style_back);
        Button_back.setPosition(70, 910);
        group.addActor(Button_back);
    }
}

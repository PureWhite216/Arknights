package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class DialogueButton extends StoryUI
{
    private static Texture Texture_Up = new Texture(Gdx.files.internal("assets/Button/DialogueButton.png"));
    private static Texture Texture_over = new Texture(Gdx.files.internal("assets/Button/DialogueButton_Over.png"));

    private Button button;
    private String text;

    public DialogueButton(String text)
    {
        this.text = text;
        Texture_Up.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture_over.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        group= new Group();

        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(Texture_Up));
        style.over = new TextureRegionDrawable(new TextureRegion(Texture_over));

        button = new Button(style);
        group.addActor(button);
    }

    public Button getButton()
    {
        return button;
    }
}

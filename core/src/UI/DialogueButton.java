package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class DialogueButton extends StoryUI
{
    private static Texture Texture_Up = new Texture(Gdx.files.internal("assets/Button/DialogueButton.png"));
    private static Texture Texture_over = new Texture(Gdx.files.internal("assets/Button/DialogueButton_Over.png"));

    private Button button;
    private String text;

    private Label Label_text;
    private Label.LabelStyle labelStyle;

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

        labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = new Color(1, 1, 1, 0.9f);

        Label_text = new Label(text, labelStyle);
        Label_text.setPosition(button.getWidth() / 2, button.getHeight() / 2 + 6, Align.center);
        Label_text.setAlignment(Align.center);
        Label_text.setTouchable(Touchable.disabled);
        group.addActor(Label_text);

    }


    public Button getButton()
    {
        return button;
    }
}

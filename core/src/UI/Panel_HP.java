package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Panel_HP extends BattleUI
{
    private static final Texture Texture_HP = new Texture(Gdx.files.internal("assets/Images/HP_Panel.png"));
    private Image hpImage;
    private Label hpLabel;
    private Label.LabelStyle labelStyle;
    private int currentHP;
    private int maxHP;

    public Panel_HP()
    {
        super();
        Texture_HP.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);

        group = new Group();
        group.setVisible(true);
        group.setY(140);
        hpImage = new Image(Texture_HP);
        group.addActor(hpImage);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = new Color(1, 1, 1, 1);
        hpLabel = new Label("100/100", labelStyle);
        hpLabel.setPosition(70, -18);
        hpLabel.setFontScale(0.45f);
        group.addActor(hpLabel);
    }

    public Image getHpImage()
    {
        return hpImage;
    }

    public void initHP(int currentHP, int maxHP)
    {
        this.currentHP = currentHP;
        this.maxHP = maxHP;
        hpLabel.setText(currentHP + "/" + maxHP);
    }

    public void updateHP(int currentHP)
    {
        if(currentHP < 0) currentHP = 0;
        this.currentHP = currentHP;
        hpLabel.setText(currentHP + "/" + maxHP);
    }

}

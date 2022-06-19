package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BattleEndTable extends BattleUI
{
    
    private Image endImage;
    private Button chooseButton;
    private Button nextButton;
    private static Texture Texture_choose = new Texture(Gdx.files.internal("assets/Button/changeOperator.png"));
    private static Texture Texture_choose_over = new Texture(Gdx.files.internal("assets/Button/changeOperator_over.png"));
    public BattleEndTable()
    {
        group = new Group();

        endImage = new Image(new Texture(Gdx.files.internal("assets/Images/BattleEnd.png")));
        endImage.setPosition(0, 0);
        group.addActor(endImage);

        Texture next_Texture = new Texture(Gdx.files.internal("assets/Button/next.png"));
        Texture next_pressed_Texture = new Texture(Gdx.files.internal("assets/Button/next_pressed.png"));
        Button.ButtonStyle next_Style = new Button.ButtonStyle();
        next_Style.up = new TextureRegionDrawable(new TextureRegion(next_Texture));
        next_Style.over = new TextureRegionDrawable(new TextureRegion(next_pressed_Texture));
        nextButton = new Button(next_Style);
        nextButton.setPosition(960 - nextButton.getWidth() / 2, 1080 * 0.15f);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("next");
                level.setLevelEnd();
            }
        });
        group.addActor(nextButton);


        Button.ButtonStyle Style_choose = new Button.ButtonStyle();
        Style_choose.up = new TextureRegionDrawable(Texture_choose);
        Style_choose.over = new TextureRegionDrawable(Texture_choose_over);
        chooseButton = new Button(Style_choose);
        chooseButton.setPosition(960 - chooseButton.getWidth() / 2, 1080 * 0.3f);
        chooseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                level.getChangeOperatorTable().show();
            }
        });
        group.addActor(chooseButton);
    }
}

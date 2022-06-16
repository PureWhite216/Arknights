package Level;

import Audio.AudioManager;
import UI.DialogueButton;
import UI.DialogueTable;
import UI.OperatorFormationTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StoryLevel extends LevelBase
{
    private OperatorFormationTable operatorFormationTable;
    private DialogueButton testButton;
    private DialogueTable dialogueTable;

    private static Texture Texture_background = new Texture(Gdx.files.internal("assets/BackGround/StoryLevelBG.png"));
    private static Texture Texture_operatorManage = new Texture(Gdx.files.internal("assets/Button/OperatorManage.png"));
    private static Texture Texture_operatorManage_Over = new Texture(Gdx.files.internal("assets/Button/OperatorManage_Over.png"));
    private Image Image_background;
    private Button Button_operatorManage;

    private String textPath = "assets/Text/start.txt";

    public StoryLevel()
    {
        Texture_background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image_background = new Image(Texture_background);
        stage.addActor(Image_background);

        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(Texture_operatorManage));
        style.over = new TextureRegionDrawable(new TextureRegion(Texture_operatorManage_Over));
        Button_operatorManage = new Button(style);
        Button_operatorManage.setPosition(1780, 330);
        Button_operatorManage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                operatorFormationTable.show();
            }
        });
        stage.addActor(Button_operatorManage);

        testButton = new DialogueButton(" 行 动 开 始");
        testButton.hide();
        testButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isLevelEnd = true;
            }
        });

        dialogueTable = new DialogueTable(textPath);
        dialogueTable.addToLevel(stage, this);

        testButton.addToLevel(stage, this);
        testButton.setPosition(stage.getWidth() / 2 - testButton.getButton().getWidth() / 2, 500);

        operatorFormationTable = new OperatorFormationTable();
        operatorFormationTable.addToLevel(stage, this);
        AudioManager.getInstance().playBGM(2);
    }

    public void activateButton()
    {
        testButton.show();
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

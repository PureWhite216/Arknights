package Level;

import Audio.AudioManager;
import Level.BattleLevels.BattleLevel_01;
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

import java.util.ArrayList;

public class StoryLevelBase extends LevelBase
{
    protected OperatorFormationTable operatorFormationTable;
    protected DialogueButton testButton;
    protected ArrayList<DialogueButton> buttons = new ArrayList<>();
    protected DialogueTable dialogueTable;

    protected static Texture Texture_background = new Texture(Gdx.files.internal("assets/BackGround/StoryLevelBG.png"));
    protected static Texture Texture_operatorManage = new Texture(Gdx.files.internal("assets/Button/OperatorManage.png"));
    protected static Texture Texture_operatorManage_Over = new Texture(Gdx.files.internal("assets/Button/OperatorManage_Over.png"));
    protected Image Image_background;
    protected Button Button_operatorManage;

    protected String textPath = "assets/Text/base.txt";

    public StoryLevelBase()
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

        initDialogue();

        operatorFormationTable = new OperatorFormationTable();
        operatorFormationTable.addToLevel(stage, this);
        AudioManager.getInstance().playBGM(2);
    }
    
    protected void initDialogue()
    {
        testButton = new DialogueButton(" 行 动 开 始");
        testButton.hide();
        testButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isLevelEnd = true;
            }
        });
        buttons.add(testButton);

        dialogueTable = new DialogueTable(textPath);
        dialogueTable.addToLevel(stage, this);

        testButton.addToLevel(stage, this);
        testButton.setPosition(stage.getWidth() / 2 - testButton.getButton().getWidth() / 2, 500);
    }

    public void activateButton()
    {
        for(DialogueButton dialogueButton : buttons)
        {
            dialogueButton.show();
        }
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new BattleLevel_01();
    }

    public ArrayList<DialogueButton> getButtons()
    {
        return buttons;
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

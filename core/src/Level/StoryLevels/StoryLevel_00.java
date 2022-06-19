package Level.StoryLevels;

import Audio.AudioManager;
import Level.BattleLevels.BattleLevel_01;
import Level.LevelBase;
import Level.StoryLevelBase;
import UI.DialogueButton;
import UI.DialogueTable;
import UI.OperatorFormationTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class StoryLevel_00 extends StoryLevelBase
{

    @Override
    protected void initDialogue()
    {
        textPath = "assets/Text/start.txt";

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

    @Override
    protected LevelBase loadNextLevel()
    {
        return new BattleLevel_01();
    }
}
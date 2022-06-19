package Level.StoryLevels;

import Level.BattleLevelBase;
import Level.BattleLevels.BattleLevel_01;
import Level.BattleLevels.BattleLevel_02;
import Level.BattleLevels.BattleLevel_03;
import Level.BattleLevels.BattleLevel_03_2;
import Level.LevelBase;
import Level.StoryLevelBase;
import UI.DialogueButton;
import UI.DialogueTable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StoryLevel_02 extends StoryLevelBase
{
    private DialogueButton testButton2;
    private int levelChoose = 0;
    @Override
    protected void initDialogue()
    {
        textPath = "assets/Text/02.txt";

        testButton = new DialogueButton("向左");
        testButton.hide();
        testButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelChoose = 0;
                isLevelEnd = true;
            }
        });
        buttons.add(testButton);


        testButton2 = new DialogueButton("向右");
        testButton2.hide();
        testButton2.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelChoose = 1;
                isLevelEnd = true;
            }
        });
        buttons.add(testButton2);


        dialogueTable = new DialogueTable(textPath);
        dialogueTable.addToLevel(stage, this);

        testButton.addToLevel(stage, this);
        testButton.setPosition(stage.getWidth() / 2 - testButton.getButton().getWidth() / 2, 700);
        testButton2.addToLevel(stage, this);
        testButton2.setPosition(stage.getWidth() / 2 - testButton2.getButton().getWidth() / 2, 400);
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        if(levelChoose == 0)
        {
            return new BattleLevel_03();
        }
        else
        {
            return new BattleLevel_03_2();
        }
    }
}

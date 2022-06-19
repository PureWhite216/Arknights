package Level.StoryLevels;

import Level.BattleLevels.BattleLevel_05;
import Level.BattleLevels.BattleLevel_06;
import Level.LevelBase;
import Level.StoryLevelBase;
import UI.DialogueButton;
import UI.DialogueTable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StoryLevel_05 extends StoryLevelBase
{
    private DialogueButton testButton2;
    private int levelChoose = 0;
    @Override
    protected void initDialogue()
    {
        textPath = "assets/Text/05.txt";

        testButton = new DialogueButton("迎接战斗");
        testButton.hide();
        testButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelChoose = 0;
                isLevelEnd = true;
            }
        });
        buttons.add(testButton);


        dialogueTable = new DialogueTable(textPath);
        dialogueTable.addToLevel(stage, this);

        testButton.addToLevel(stage, this);
        testButton.setPosition(stage.getWidth() / 2 - testButton.getButton().getWidth() / 2, 700);
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new BattleLevel_06();
    }
}

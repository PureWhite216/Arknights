package Level.StoryLevels;

import Level.BattleLevels.BattleLevel_03;
import Level.BattleLevels.BattleLevel_03_2;
import Level.BattleLevels.BattleLevel_04;
import Level.BattleLevels.BattleLevel_05;
import Level.LevelBase;
import Level.StoryLevelBase;
import UI.DialogueButton;
import UI.DialogueTable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class StoryLevel_03 extends StoryLevelBase
{
    private DialogueButton testButton2;
    private int levelChoose = 0;
    @Override
    protected void initDialogue()
    {
        textPath = "assets/Text/02.txt";

        testButton = new DialogueButton("向西");
        testButton.hide();
        testButton.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelChoose = 0;
                isLevelEnd = true;
            }
        });
        buttons.add(testButton);


        testButton2 = new DialogueButton("向北");
        testButton2.hide();
        testButton2.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelChoose = 1;
                isLevelEnd = true;
            }
        });
        buttons.add(testButton2);

//        testButton3 = new DialogueButton("向东");
//        testButton3.hide();
//        testButton3.getButton().addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                levelChoose = 2;
//                isLevelEnd = true;
//            }
//        });
//        buttons.add(testButton3);


        dialogueTable = new DialogueTable(textPath);
        dialogueTable.addToLevel(stage, this);

        testButton.addToLevel(stage, this);
        testButton.setPosition(stage.getWidth() / 2 - testButton.getButton().getWidth() / 2, 700);
        testButton2.addToLevel(stage, this);
        testButton2.setPosition(stage.getWidth() / 2 - testButton2.getButton().getWidth() / 2, 400);
//        testButton3.addToLevel(stage, this);
//        testButton3.setPosition(stage.getWidth() / 2 - testButton2.getButton().getWidth() / 2, 100);
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        if(levelChoose == 0)
        {
            return new BattleLevel_04();
        }
        else
        {
            return new BattleLevel_04();
        }
    }
}

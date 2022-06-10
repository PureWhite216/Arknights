package Battle;

import Level.BattleLevelBase;
import Character.Operator;
import Character.Enemy;
import Character.CharacterBase;

public class BattleHandler
{
    private BattleLevelBase level;
    private static float duration = 1.5f;
    private float timer = duration;
    private int currentIndex = 0;
    private boolean isEnd = true;
    private boolean isEndReady = false;
    public BattleHandler(BattleLevelBase level)
    {
        this.level = level;
    }

    public void update(float deltaTime)
    {
        if(isEnd) return; // Not Start
        timer += deltaTime;
        if(isEndReady && timer >= duration) // End the Round
        {
            timer = duration;
            isEnd = true;
            level.getReadyButton().setVisible(false);
            level.getPreImage().setVisible(true);
            level.getImage_Battling().setVisible(false);
            isEndReady = false;

            Operator[] operators = level.getOperators();
            for(int i = 0; i <= 3; i++)
            {
                if(operators[i] == null) break;
                operators[i].setTarget(null);
            }

            /*Check Is Battle End*/
            for(Enemy enemy : level.getEnemies())
            {
                if(enemy == null) continue;
                if(!enemy.isDied())
                {
                    return;
                }
            }
            level.battleEnd();

        }
        else if(!isEndReady && timer >= duration)
        {
            while(currentIndex < level.getCharacters().size() && level.getCharacters().get(currentIndex).isDied())
            {
                currentIndex++;
            }
            if(level.getCharacters().size() == currentIndex) // Ready to End the Round
            {
                isEndReady = true;
                currentIndex = 0;
            }
            else
            {
                CharacterBase tmp;
                tmp = level.getCharacters().get(currentIndex);
                tmp.getSkills().get(tmp.chosenSkillIndex).callSkill();
                currentIndex++;
            }
            timer = 0;
        }
    }

    public boolean isEnd()
    {
        return isEnd;
    }

    public void execute()
    {
        isEnd = false;
    }

}

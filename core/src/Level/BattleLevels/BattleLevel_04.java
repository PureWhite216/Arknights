package Level.BattleLevels;

import Battle.TeamManager;
import Level.BattleLevelBase;
import Character.*;
import Level.LevelBase;
import Level.StoryLevel;

public class BattleLevel_04 extends BattleLevelBase
{
    public BattleLevel_04()
    {
        super();
        Operator[] teamMembers = TeamManager.getInstance().teamMembers;
        for(int i = 0; i <= 3; i++)
        {
            if(teamMembers[i] != null)
            {
                operators[i] = teamMembers[i];
                operators[i].setPosition(operatorPos[i], defaultY);
            }
        }
        enemies[0] = new Enemy_GoPro(enemyPos[0], defaultY);
        enemies[1] = new Enemy_1002(enemyPos[1], defaultY);
        enemies[2] = new Enemy_Wizard(enemyPos[2], defaultY);
        enemies[3] = new Enemy_Crossbowman(enemyPos[3], defaultY);

        initLevel();
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new StoryLevel();
    }
}

package Level.BattleLevels;

import Battle.TeamManager;
import Level.BattleLevelBase;
import Character.*;
import Level.LevelBase;
import Level.StoryLevel;

public class BattleLevel_07 extends BattleLevelBase
{
    public BattleLevel_07()
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
        enemies[1] = new Enemy_Revenger(enemyPos[1], defaultY);
        enemies[2] = new Enemy_Crossbowman(enemyPos[2], defaultY);

        initLevel();
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new StoryLevel();
    }
}

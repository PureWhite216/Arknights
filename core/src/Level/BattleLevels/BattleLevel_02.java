package Level.BattleLevels;

import Battle.TeamManager;
import Level.LevelBase;
import Level.StoryLevels.StoryLevel_00;
import Level.BattleLevelBase;
import Character.*;
import Level.StoryLevels.StoryLevel_02;

public class BattleLevel_02 extends BattleLevelBase
{
    public BattleLevel_02()
    {
        super();
        Operator[] teamMembers = TeamManager.getInstance().teamMembers;
        for(int i = 0; i <= 3; i++)
        {
            if(teamMembers[i] != null)
            {
                teamMembers[i].getBattleComponent().HP = teamMembers[i].getBattleComponent().maxHP;
                teamMembers[i].isDied = false;
                operators[i] = teamMembers[i];
                operators[i].setPosition(operatorPos[i], defaultY);
            }
        }
        enemies[0] = new Enemy_1002(enemyPos[0], defaultY);
        enemies[1] = new Enemy_Crossbowman(enemyPos[1], defaultY);
        enemies[2] = new Enemy_Crossbowman(enemyPos[2], defaultY);
//        enemies[2] = new Enemy_1002(enemyPos[2], defaultY);

        initLevel();
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new StoryLevel_02();
    }
}

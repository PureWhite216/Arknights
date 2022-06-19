package Level.BattleLevels;

import Battle.TeamManager;
import Level.BattleLevelBase;
import Character.*;
import Level.LevelBase;
import Level.StoryLevels.StoryLevel_00;
import Level.StoryLevels.StoryLevel_07;

public class BattleLevel_09 extends BattleLevelBase
{
    public BattleLevel_09()
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
        enemies[0] = new Enemy_GoPro(enemyPos[0], defaultY);
        enemies[1] = new Enemy_Mrogue(enemyPos[1], defaultY);
        enemies[2] = new Enemy_Mrogue(enemyPos[2], defaultY);
        enemies[3] = new Enemy_Mrogue(enemyPos[3], defaultY);

        initLevel();
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new StoryLevel_07();
    }
}

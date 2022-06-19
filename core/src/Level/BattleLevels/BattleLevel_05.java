package Level.BattleLevels;

import Battle.TeamManager;
import Level.BattleLevelBase;
import Character.*;
import Level.LevelBase;
import Level.StoryLevel;

public class BattleLevel_05 extends BattleLevelBase
{
    public BattleLevel_05()
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
        enemies[0] = new Enemy_Wizard(enemyPos[0], defaultY);
        enemies[1] = new Enemy_Wizard(enemyPos[1], defaultY);
        enemies[2] = new Enemy_WizardPro(enemyPos[2], defaultY);

        initLevel();
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new StoryLevel();
    }
}

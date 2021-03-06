package Level;

import Character.*;


public class TestLevel extends BattleLevelBase
{
    public TestLevel()
    {
        super();
        operators[0] = new Surtr(operatorPos[0],defaultY);
        operators[1] = new Texas(operatorPos[1], defaultY);
        operators[2] = new Warfarin(operatorPos[2], defaultY);
        enemies[0] = new Enemy_1002(enemyPos[0], defaultY);
        enemies[1] = new Enemy_1002(enemyPos[1], defaultY);
        enemies[2] = new Enemy_ShieldDefender(enemyPos[2], defaultY);

        initLevel();
    }
}

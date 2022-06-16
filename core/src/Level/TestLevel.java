package Level;

import Character.*;


public class TestLevel extends BattleLevelBase
{
    public TestLevel()
    {
        super();
        operators[0] = new Nightmare(operatorPos[0],defaultY);
        operators[1] = new Amiya(operatorPos[1], defaultY);
        operators[2] = new Surtr(operatorPos[2], defaultY);
        enemies[0] = new Enemy_Wizard(enemyPos[0], defaultY);
        enemies[1] = new Enemy_Wizard(enemyPos[1], defaultY);
        enemies[2] = new Enemy_WizardPro(enemyPos[2], defaultY);

        initLevel();
    }
}

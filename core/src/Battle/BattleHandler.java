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
            /*End the Round*/
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
                operators[i].getBattleComponent().apPlusPlus();
                level.getApPanels()[i].updateAP(operators[i].getBattleComponent().getAp());
                level.setChoosingTarget(false);
                operators[i].chosenSkillIndex = -1;
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
                CharacterBase tmpC;
                tmpC = level.getCharacters().get(currentIndex);
                if(!tmpC.getTarget().isDied() || findAliveTarget(tmpC))
                {
                    tmpC.getSkills().get(tmpC.chosenSkillIndex).callSkill();
                }
                currentIndex++;
            }
            timer = 0;
        }
    }
    
    private boolean findAliveTarget(CharacterBase character)
    {
        if(character instanceof Operator)
        {
            for(Enemy enemy : level.getEnemies())
            {
                if(enemy == null || enemy.isDied()) continue;
                character.setTarget(enemy);
                return true;
            }
        }
        else
        {
            for(Operator operator : level.getOperators())
            {
                if(operator == null || operator.isDied()) continue;
                character.setTarget(operator);
                return true;
            }
        }
        return false;
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

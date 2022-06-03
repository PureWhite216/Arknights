package Battle;

import Level.BattleLevelBase;
import Character.SkillName;
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
        if(isEnd) return;
        timer += deltaTime;
        if(isEndReady && timer >= duration)
        {
            timer = duration;
            isEnd = true;
            level.getReadyButton().setVisible(true);
            return;
        }
        else if(!isEndReady && timer >= duration)
        {
            level.getCharacters().get(currentIndex).callSkill(SkillName.Attack);
            currentIndex++;
            timer = 0;
            if(level.getCharacters().size() == currentIndex)
            {
                isEndReady = true;
                currentIndex = 0;
                timer = 0;
            }
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

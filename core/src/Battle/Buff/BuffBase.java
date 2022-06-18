package Battle.Buff;

import Component.BattleComponent;

public abstract class BuffBase
{
    int remainRounds = 0;

    public BuffBase(int remainRounds)
    {
        this.remainRounds = remainRounds;
    }

    public boolean roundUpdate()
    {
        if(remainRounds > 0)
        {
            remainRounds--;
            if(remainRounds == 0) return true;
        }
        return false;
    }

    public abstract void buffEnable(BattleComponent battleComponent);
    public abstract void buffDisable(BattleComponent battleComponent);
}

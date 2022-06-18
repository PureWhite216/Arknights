package Battle.Buff;

import Component.BattleComponent;

public class MissBuff extends BuffBase
{
    float missChange;

    public MissBuff(int remainRounds, float missChange)
    {
        super(remainRounds);
        this.missChange = missChange;
    }

    @Override
    public void buffEnable(BattleComponent battleComponent)
    {
        battleComponent.buff_Miss += missChange;
    }

    @Override
    public void buffDisable(BattleComponent battleComponent)
    {
        battleComponent.buff_Miss -= missChange;
    }
}

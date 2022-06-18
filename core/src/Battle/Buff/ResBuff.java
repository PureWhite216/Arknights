package Battle.Buff;

import Component.BattleComponent;

public class ResBuff extends BuffBase
{
    int resChange;

    public ResBuff(int remainRounds, int resChange)
    {
        super(remainRounds);
        this.resChange = resChange;
    }

    @Override
    public void buffEnable(BattleComponent battleComponent)
    {
        battleComponent.tmpRes += resChange;
    }

    @Override
    public void buffDisable(BattleComponent battleComponent)
    {
        battleComponent.tmpRes -= resChange;
    }
}

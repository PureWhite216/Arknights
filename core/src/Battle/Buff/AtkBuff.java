package Battle.Buff;

import Component.BattleComponent;

public class AtkBuff extends BuffBase
{
    float atkChange;

    public AtkBuff(int remainRounds, float atkChange)
    {
        super(remainRounds);
        this.atkChange = atkChange;
    }

    @Override
    public void buffEnable(BattleComponent battleComponent)
    {
        battleComponent.tmpAtk += atkChange * battleComponent.getAtk();
    }

    @Override
    public void buffDisable(BattleComponent battleComponent)
    {
        battleComponent.tmpAtk -= atkChange * battleComponent.getAtk();
    }
}

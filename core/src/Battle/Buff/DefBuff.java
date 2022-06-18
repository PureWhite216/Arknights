package Battle.Buff;

import Component.BattleComponent;

public class DefBuff extends BuffBase
{
    float defChange;

    public DefBuff(int remainRounds, float defChange)
    {
        super(remainRounds);
        this.defChange = defChange;
    }

    @Override
    public void buffEnable(BattleComponent battleComponent)
    {
        battleComponent.tmpDef += defChange * battleComponent.getDef();
    }

    @Override
    public void buffDisable(BattleComponent battleComponent)
    {
        battleComponent.tmpDef -= defChange * battleComponent.getDef();
    }
}

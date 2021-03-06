package Component.Skill;

import Component.Animation.AnimationBase;
import Component.AnimationComponent;
import Component.BattleComponent;
import Character.CharacterBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public abstract class SkillBase
{
    protected String skillName;
    protected CharacterBase character;
    protected BattleComponent battleComponent;
    protected AnimationComponent animationComponent;
    protected int apCost;
    protected boolean needChoose = true; //该技能是否需要选择目标
    protected String skillInfo = "";

    public SkillBase(CharacterBase character)
    {
        this.character = character;
        battleComponent = character.getBattleComponent();
        animationComponent = character.getAnimationComponent();
    }

    protected abstract void callEffect();

    public abstract void callSkill();

    protected void callSound()
    {

    }

    protected RunnableAction getEffectAction()
    {
        return Actions.run(new Runnable() {
            @Override
            public void run() {
                callEffect();
            }
        });
    }

    public boolean isNeedChoose()
    {
        return needChoose;
    }

    public String getSkillName()
    {
        return skillName;
    }

    public String getSkillInfo()
    {
        return skillInfo;
    }

    public int getApCost()
    {
        return apCost;
    }
}

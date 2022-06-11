package Component.Skill;

import Component.Animation.AnimationBase;
import Component.AnimationComponent;
import Component.BattleComponent;
import Character.CharacterBase;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public abstract class SkillBase
{
    protected String skillName;
    protected CharacterBase character;
    protected BattleComponent battleComponent;
    protected AnimationComponent animationComponent;
    protected int apCost;

    public SkillBase(CharacterBase character)
    {
        this.character = character;
        battleComponent = character.getBattleComponent();
        animationComponent = character.getAnimationComponent();
    }

    protected abstract RunnableAction getEffectAction();
    protected abstract void callEffect();

    public abstract void callSkill();

    public String getSkillName()
    {
        return skillName;
    }

    public int getApCost()
    {
        return apCost;
    }
}

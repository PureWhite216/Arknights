package Component.Skill.Texas;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Texas_Attack extends Skill_Attack
{
    public Skill_Texas_Attack(CharacterBase character)
    {
        super(character);
        skillName = "斩击";
        skillInfo = "对敌人造成一倍攻击力的物理伤害";
    }

    @Override
    protected void callEffect()
    {
        super.callEffect();
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.4f);
    }

    @Override
    public void callSkill()
    {
        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Attack_Start", false);
        animationComponent.getAnimationState().addAnimation(0, "Attack_Loop", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Attack_End", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.4f),
                Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.55f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
    }
}

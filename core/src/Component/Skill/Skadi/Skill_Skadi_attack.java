package Component.Skill.Skadi;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Operator;

public class Skill_Skadi_attack extends Skill_Attack{
    public Skill_Skadi_attack(CharacterBase character)
    {
        super(character);
        skillName = "迅捷打击";
        skillInfo="造成自身攻击力1倍的物理伤害\n若击杀敌人恢复1点AP";
        apCost=2;
    }

    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        character.getTarget().getBattleComponent().getDamage((int)((float)battleComponent.getAtk()), DamageType.Physical);
        if(character.getTarget().getBattleComponent().getAp()<=0)
            character.getBattleComponent().addAP(1);
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Skill_1_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Stun", false, 0f);
        //animationComponent.getAnimationState().addAnimation(0, "Attack_End", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.6f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut)
                )
        );
    }
}

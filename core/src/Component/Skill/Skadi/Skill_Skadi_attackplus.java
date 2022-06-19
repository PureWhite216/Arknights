package Component.Skill.Skadi;

import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Battle.Buff.DefBuff;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Operator;

public class Skill_Skadi_attackplus extends Skill_Attack{
    public Skill_Skadi_attackplus(CharacterBase character)
    {
        super(character);
        skillName = "跃浪击";
        skillInfo="造成自身攻击力1.5倍的物理伤害；\n自身攻击力提升30%，持续5回合";
        apCost=4;
    }

    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        character.getTarget().getBattleComponent().getDamage((int)((float)battleComponent.getAtk()*1.5f), DamageType.Physical);
        character.getBattleComponent().addBuff(new AtkBuff(5,0.3f));
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Skill_1_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Stun", false, 0f);
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

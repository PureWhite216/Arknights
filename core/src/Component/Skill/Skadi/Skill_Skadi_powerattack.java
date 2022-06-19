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

public class Skill_Skadi_powerattack extends Skill_Attack{
    public Skill_Skadi_powerattack(CharacterBase character)
    {
        super(character);
        skillName = "涌潮悲歌";
        skillInfo="对敌人造成自身攻击力2倍的物理伤害；\n自身攻击力提升90%，防御力提升70%, 持续3回合";
        apCost=6;
    }

    protected void callSound()
    {
        character.getSkillSounds()[3].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        character.getTarget().getBattleComponent().getDamage((int)((float)battleComponent.getAtk()*2f), DamageType.Physical);
        character.getBattleComponent().addBuff(new AtkBuff(3,0.9f));
        character.getBattleComponent().addBuff(new DefBuff(3,0.7f));
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Attack_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Attack", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Attack_End", false, 0f);
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

package Component.Skill.SilverAsh;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_SilverAsh_powerattack extends Skill_Attack {
    public Skill_SilverAsh_powerattack(CharacterBase character)
    {
        super(character);
        skillName = "强力击";
        skillInfo="造成自身攻击力2倍的物理伤害";
        apCost = 2;
    }

    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        character.getTarget().getBattleComponent().getDamage((int)((float)battleComponent.getAtk()*2.0), DamageType.Physical);
        AudioManager.getInstance().getSFX().get(SFXName.pistol).play(0.6f);
        apCost=2;
    }

    @Override
    public void callSkill()
    {
        callSound();
        //System.out.println("test");
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        //System.out.println("test1");
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

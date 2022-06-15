package Component.Skill.SilverAsh;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_SilverAsh_attack extends Skill_Attack{
    public Skill_SilverAsh_attack(CharacterBase character)
    {
        super(character);
        skillName = "刺击";
    }

    protected void callSound()
    {
        character.getSkillSounds()[0].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        character.getTarget().getBattleComponent().getDamage((int)((float)battleComponent.getAtk()), DamageType.Physical);
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();
        //System.out.println("test");
        animationComponent.getAnimationState().setAnimation(0, "Combat", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        //System.out.println("test1");
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.6f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))

        );
    }
}

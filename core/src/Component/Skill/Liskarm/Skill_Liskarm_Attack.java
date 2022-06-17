package Component.Skill.Liskarm;

import Audio.AudioManager;
import Audio.SFXName;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class Skill_Liskarm_Attack extends Skill_Attack{
    public Skill_Liskarm_Attack(CharacterBase character)
    {
        super(character);
        skillName = "射击";
        skillInfo = "造成1倍物理伤害";
    }

    protected void callSound()
    {
        character.getSkillSounds()[0].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        super.callEffect();
        AudioManager.getInstance().getSFX().get(SFXName.pistol).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();
        //System.out.println("test");
        animationComponent.getAnimationState().setAnimation(0, "Attack_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Attack_Loop", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Attack_End", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        //System.out.println("test1");
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

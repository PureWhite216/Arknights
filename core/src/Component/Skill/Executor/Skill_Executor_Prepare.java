package Component.Skill.Executor;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Executor_Prepare extends Skill_Attack
{
    public Skill_Executor_Prepare(CharacterBase character)
    {
        super(character);
        skillName = "预备";
        needChoose = false;
        skillInfo = "AP加1";
    }

    @Override
    protected void callSound()
    {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        battleComponent.addAP(1);
        AudioManager.getInstance().getSFX().get(SFXName.atkBoost).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Skill_Right_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Skill_Right_End", false, 1f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 2f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}


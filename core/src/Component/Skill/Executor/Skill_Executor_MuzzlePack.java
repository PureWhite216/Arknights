package Component.Skill.Executor;

import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Character.CharacterBase;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Executor_MuzzlePack extends Skill_Attack {
    public Skill_Executor_MuzzlePack(CharacterBase character) {
        super(character);
        skillName = "铳口收束";
        needChoose = false;
        skillInfo = "使自身攻击力提高50%，持续4回合";
        apCost = 3;
    }

    @Override
    protected void callSound() {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect() {
        battleComponent.addBuff(new AtkBuff(4, 0.5f));
        AudioManager.getInstance().getSFX().get(SFXName.atkBoost).play(0.6f);
    }

    @Override
    public void callSkill() {
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


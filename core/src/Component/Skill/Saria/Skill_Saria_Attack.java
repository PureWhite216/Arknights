package Component.Skill.Saria;

import Audio.AudioManager;
import Audio.SFXName;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.CharacterBase;

public class Skill_Saria_Attack extends Skill_Attack {
    public Skill_Saria_Attack(CharacterBase character){
        super(character);
        skillName = "盾击";
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[0].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        super.callEffect();
        AudioManager.getInstance().getSFX().get(SFXName.shield).play(0.6f);
    }

    @Override
    public void callSkill(){
        // Call Skeleton Animation
        callSound();
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.55f),
                Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.6f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
    }
}

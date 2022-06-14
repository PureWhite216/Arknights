package Component.Skill.Platnm;

import Audio.AudioManager;
import Audio.SFXName;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Platnm_Attack extends Skill_Attack
{

    public Skill_Platnm_Attack(CharacterBase character)
    {
        super(character);
        skillName = "射击";
    }

    @Override
    protected void callEffect()
    {
        super.callEffect();
        AudioManager.getInstance().getSFX().get(SFXName.arrow).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

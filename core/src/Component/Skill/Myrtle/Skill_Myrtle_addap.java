package Component.Skill.Myrtle;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Operator;

public class Skill_Myrtle_addap extends Skill_Attack{
    public Skill_Myrtle_addap(CharacterBase character)
    {
        super(character);
        skillName = "支援号令";
        apCost=2;
        needChoose = false;
    }

    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        for(Operator operator : character.getCurrentLevel().getOperators()){
            if(operator == null)
                continue;
            operator.getBattleComponent().addAP(1);
        }
        AudioManager.getInstance().getSFX().get(SFXName.healing).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Skill_Begin", false);
        animationComponent.getAnimationState().setAnimation(0, "Skill_End", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.7f),
                getEffectAction()
                )
        );
    }
}

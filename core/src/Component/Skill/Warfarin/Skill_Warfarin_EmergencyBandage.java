package Component.Skill.Warfarin;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Character.Operator;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class Skill_Warfarin_EmergencyBandage extends Skill_Attack
{
    public Skill_Warfarin_EmergencyBandage(CharacterBase character)
    {
        super(character);
        skillName = "紧急包扎";
        needChoose = false;
        apCost = 3;
    }

    @Override
    protected void callSound()
    {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        Operator minHPOperator = character.getCurrentLevel().getMinHPOperator();
        minHPOperator.getBattleComponent().getHealing((int)(minHPOperator.getBattleComponent().getMaxHP()*0.15)+battleComponent.getAtk());
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}


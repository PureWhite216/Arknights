package Component.Skill.Nightmare;

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

public class Skill_Nightmare_Attack extends Skill_Attack
{
    public Skill_Nightmare_Attack(CharacterBase character)
    {
        super(character);
        skillName = "法术攻击";
        skillInfo = "对敌人1倍法术伤害";
    }

    @Override
    protected void callSound()
    {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk(), DamageType.Magical);
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


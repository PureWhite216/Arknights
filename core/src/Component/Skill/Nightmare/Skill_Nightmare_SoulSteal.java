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

public class Skill_Nightmare_SoulSteal extends Skill_Attack {
    public Skill_Nightmare_SoulSteal(CharacterBase character) {
        super(character);
        skillName = "灵魂汲取";
        apCost = 3;
    }

    @Override
    protected void callSound() {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect() {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk() * 2, DamageType.Magical);
        double minHP = 2;
        Operator minOperator = null;
        for (Operator operator : character.getCurrentLevel().getOperators()) {
            if (operator == null || operator.isDied()) continue;
            if ((double) operator.getBattleComponent().getHP() / (double) operator.getBattleComponent().getMaxHP() < minHP) {
                minHP = (double) operator.getBattleComponent().getHP() / (double) operator.getBattleComponent().getMaxHP();
                minOperator = operator;
            }
        }
        assert minOperator != null;
        minOperator.getBattleComponent().getHealing(battleComponent.getAtk());
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill() {
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


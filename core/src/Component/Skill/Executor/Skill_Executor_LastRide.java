package Component.Skill.Executor;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Character.Enemy;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Executor_LastRide extends Skill_Attack {
    public Skill_Executor_LastRide(CharacterBase character) {
        super(character);
        skillName = "最终旅程";
        needChoose = false;
        skillInfo = "射击两次，每次对所有敌人造成伤害，\n每位敌人受到的伤害大小为送葬人攻击力的0.7~1.7倍";
        apCost = 5;
    }

    @Override
    protected void callSound() {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect() {
        for (Enemy enemy : character.getCurrentLevel().getEnemies()) {
            if (enemy != null && !enemy.isDied()) {
                double r = Math.random() + 0.7;
                enemy.getBattleComponent().getDamage((int) (battleComponent.getAtk() * r), DamageType.Magical);
            }
        }
        for (Enemy enemy : character.getCurrentLevel().getEnemies()) {
            if (enemy != null && !enemy.isDied()) {
                double r = Math.random() + 0.7;
                enemy.getBattleComponent().getDamage((int) (battleComponent.getAtk() * r), DamageType.Magical);
            }
        }
        AudioManager.getInstance().getSFX().get(SFXName.pistol).play(0.6f);
    }

    @Override
    public void callSkill() {
        callSound();

        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Skill_Right_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Skill_Right_Loop", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Skill_Right_End", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}


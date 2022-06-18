package Component.Skill.Executor;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Enemy;

public class Skill_Executor_GrapeShot extends Skill_Attack {
    public Skill_Executor_GrapeShot(CharacterBase character) {
        super(character);
        skillName = "霰弹射击";
        needChoose = false;
        skillInfo = "对所有敌人造成伤害，\n每位敌人受到的伤害大小为送葬人攻击力的0.3~1.3倍";
        apCost = 2;
    }

    @Override
    protected void callSound() {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect() {
        for (Enemy enemy : character.getCurrentLevel().getEnemies()) {
            if (enemy != null && !enemy.isDied()) {
                double r = Math.random() + 0.3;
                enemy.getBattleComponent().getDamage((int) (battleComponent.getAtk() * r), DamageType.Magical);
            }
        }
        AudioManager.getInstance().getSFX().get(SFXName.pistol).play(0.6f);
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


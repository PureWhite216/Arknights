package Component.Skill.Surtr;


import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Character.Enemy;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Surtr_Nightfall extends Skill_Attack {
    public Skill_Surtr_Nightfall(CharacterBase character) {
        super(character);
        skillName = "黄昏";
        skillInfo = "对所有敌人造成5倍法术伤害，\n自身损失30%上限的生命值";
        apCost = 6;
        needChoose = false;
    }

    @Override
    protected void callSound() {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect() {
        for (Enemy enemy : character.getCurrentLevel().getEnemies()) {
            if (enemy != null && !enemy.isDied()) {
                enemy.getBattleComponent().getDamage(battleComponent.getAtk() * 5, DamageType.Magical);
            }
        }
        battleComponent.getDamage((int) (battleComponent.getMaxHP() * 0.3), DamageType.Real);
        AudioManager.getInstance().getSFX().get(SFXName.pistol).play(0.6f);
    }

    @Override
    public void callSkill() {
        // Call Skeleton Animation
        callSound();
        animationComponent.getAnimationState().setAnimation(0, "Skill_3_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Skill_3_Loop", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}

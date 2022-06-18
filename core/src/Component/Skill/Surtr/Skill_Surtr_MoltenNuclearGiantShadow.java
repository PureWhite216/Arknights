package Component.Skill.Surtr;


import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Enemy;

public class Skill_Surtr_MoltenNuclearGiantShadow extends Skill_Attack {
    public Skill_Surtr_MoltenNuclearGiantShadow(CharacterBase character) {
        super(character);
        skillName = "熔核巨影";
        skillInfo = "对所有敌人造成2.5倍法术伤害";
        apCost = 4;
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
                enemy.getBattleComponent().getDamage((int) (battleComponent.getAtk() * 2.5), DamageType.Magical);
            }
        }
        AudioManager.getInstance().getSFX().get(SFXName.swordMagic).play(0.6f);
    }

    @Override
    public void callSkill() {
        // Call Skeleton Animation
        callSound();
        animationComponent.getAnimationState().setAnimation(0, "Skill_2", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}

package Component.Skill.Surtr;


import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Surtr_Laevatein extends Skill_Attack {
    public Skill_Surtr_Laevatein(CharacterBase character) {
        super(character);
        skillName = "烈焰魔剑";
        skillInfo = "对敌人造成造成1.7倍攻击力法术伤害，\n20%使目标获得2回合易伤";
        apCost = 2;
    }

    @Override
    protected void callSound() {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect() {
        character.getTarget().getBattleComponent().getDamage((int) (battleComponent.getAtk() * 1.7), DamageType.Magical);
        int r = (int)(Math.random()*5);
        if(r == 0){
            character.getTarget().getBattleComponent().buff_fragile = 2;
        }
        AudioManager.getInstance().getSFX().get(SFXName.swordMagic).play(0.6f);
    }

    @Override
    public void callSkill() {
        // Call Skeleton Animation
        callSound();
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.05f),
                Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.6f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut)
        ));
    }
}

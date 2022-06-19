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

public class Skill_Nightmare_TheShadow extends Skill_Attack {
    public Skill_Nightmare_TheShadow(CharacterBase character) {
        super(character);
        skillName = "夜魇魔影";
        apCost = 5;
        skillInfo = "对敌人3倍法术伤害,\n并使目标眩晕2回合";
    }

    @Override
    protected void callSound() {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect() {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk() * 3, DamageType.Magical);
        character.getTarget().getBattleComponent().buff_Dizzy += 2;
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill() {
        callSound();

        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Attack", false,0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(1.5f),
                getEffectAction()
        ));
    }
}


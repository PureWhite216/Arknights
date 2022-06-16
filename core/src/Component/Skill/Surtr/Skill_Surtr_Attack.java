package Component.Skill.Surtr;


import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Surtr_Attack extends Skill_Attack {
    public Skill_Surtr_Attack(CharacterBase character){
        super(character);
        skillName = "斩击";
        skillInfo = "对敌人造成一倍攻击力的法术伤害";
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
        AudioManager.getInstance().getSFX().get(SFXName.pistol).play(0.6f);
    }

    @Override
    public void callSkill(){
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

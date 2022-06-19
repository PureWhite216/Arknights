package Component.Skill.Texas;
import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Franka.Skill_Franka_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Texas_PowerStrike extends Skill_Texas_Attack {
    public Skill_Texas_PowerStrike(CharacterBase character){
        super(character);
        skillName = "强力击";
        skillInfo = "对敌人造成2倍攻击力的物理伤害\n若击杀敌人，返还1点AP";
        apCost = 2;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk() * 2, DamageType.Physical);
        if(character.getTarget().getBattleComponent().isDied()){
            character.getBattleComponent().addAP(1);
        }
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Attack_Start", false);
        animationComponent.getAnimationState().addAnimation(0, "Attack_Loop", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Attack_End", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.4f),
                Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.55f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
    }
}

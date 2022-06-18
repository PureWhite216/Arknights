package Component.Skill.Myrtle;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Operator;

public class Skill_Myrtle_addapplus extends Skill_Attack{
    public Skill_Myrtle_addapplus(CharacterBase character)
    {
        super(character);
        skillName = "治愈之翼";
        skillInfo="为所有友方恢复1点AP\n并治愈桃金娘自身攻击力1倍的血量";
        apCost=4;
        needChoose = false;
    }

    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        character.getBattleComponent().getHealing(character.getBattleComponent().getAtk());
        for(Operator operator : character.getCurrentLevel().getOperators()){
            if(operator == null)
                continue;
            operator.getBattleComponent().addAP(1);
        }

        AudioManager.getInstance().getSFX().get(SFXName.healing).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Skill_Begin", false);
        animationComponent.getAnimationState().setAnimation(0, "Skill_Loop", false);
        animationComponent.getAnimationState().setAnimation(0, "Skill_End", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                        Actions.delay(0.5f),
                        //Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                        getEffectAction()
                        //Actions.delay(0.6f),
                )

        );
    }
}

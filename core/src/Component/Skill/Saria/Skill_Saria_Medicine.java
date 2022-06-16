package Component.Skill.Saria;

import Audio.AudioManager;
import Audio.SFXName;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Character.Operator;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Saria_Medicine extends Skill_Attack {
    public Skill_Saria_Medicine(CharacterBase character){
        super(character);
        skillName = "药物配置";
        needChoose = false;
        apCost = 3;
        skillInfo = "消耗3点能量，使全体友方恢复自身1倍攻击力的血量";
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        for(Operator operator : character.getCurrentLevel().getOperators()){
            if(operator == null)
                continue;
            operator.getBattleComponent().getHealing(character.getBattleComponent().getAtk());
        }

        AudioManager.getInstance().getSFX().get(SFXName.healing).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Skill_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Skill_Loop", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Skill_End", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

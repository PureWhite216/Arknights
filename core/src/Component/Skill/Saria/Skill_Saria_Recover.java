package Component.Skill.Saria;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Operator;

public class Skill_Saria_Recover extends Skill_Attack {
    public Skill_Saria_Recover(CharacterBase character){
        super(character);
        skillName = "急救";
        needChoose = false;
        apCost = 3;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        Operator need_operator = null;
        double min_hp = 1.0;
        double tmp_hp = 0;
        for(Operator operator : character.getCurrentLevel().getOperators()){
            if(operator == null)
                continue;
            tmp_hp = ((double)(operator.getBattleComponent().getHP()))/((double)(operator.getBattleComponent().getMaxHP()));
            if(tmp_hp <= min_hp){
                min_hp = tmp_hp;
                need_operator = operator;
            }
        }
        if(need_operator != null)
            need_operator.getBattleComponent().getHealing(character.getBattleComponent().getAtk() * 2);
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

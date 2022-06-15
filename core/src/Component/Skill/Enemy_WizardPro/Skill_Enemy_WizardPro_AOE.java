package Component.Skill.Enemy_WizardPro;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Character.Operator;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Enemy_WizardPro_AOE extends Skill_Attack
{

    public Skill_Enemy_WizardPro_AOE(CharacterBase character)
    {
        super(character);
        apCost = 4;
    }

    @Override
    protected void callEffect()
    {
        for (Operator operator:character.getCurrentLevel().getOperators()
             ) {
            if(operator != null && !operator.isDied()){
                operator.getBattleComponent().getDamage(battleComponent.getAtk(), DamageType.Magical);
            }
        }
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}

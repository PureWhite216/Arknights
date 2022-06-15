package Component.Skill.Enemy_Crossbowman;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Character.Operator;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Enemy_Crossbowman_Attack extends Skill_Attack
{

    public Skill_Enemy_Crossbowman_Attack(CharacterBase character)
    {
        super(character);
    }

    @Override
    protected void callEffect()
    {
        Operator randomOperator = character.getCurrentLevel().getRandomOperator();
        randomOperator.getBattleComponent().getDamage(battleComponent.getAtk(), DamageType.Physical);
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

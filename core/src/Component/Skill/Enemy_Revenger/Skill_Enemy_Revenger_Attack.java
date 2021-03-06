package Component.Skill.Enemy_Revenger;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Enemy_Revenger_Attack extends Skill_Attack
{

    public Skill_Enemy_Revenger_Attack(CharacterBase character)
    {
        super(character);
    }

    @Override
    protected void callEffect()
    {
        if(character.getBattleComponent().getHP()*2<character.getBattleComponent().getMaxHP()){
            character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk()*2, DamageType.Physical);
        }else{
            super.callEffect();
        }
        AudioManager.getInstance().getSFX().get(SFXName.swordMagic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.05f),
                Actions.moveTo(character.getTarget().getX() + 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.6f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
    }
}

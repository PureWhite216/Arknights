package Component.Skill.Platnm;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.SkillBase;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


public class Skill_Platnm_QuickShoot extends Skill_Attack
{

    public Skill_Platnm_QuickShoot(CharacterBase character)
    {
        super(character);
        skillName = "快速射击";
        apCost = 2;
    }

    @Override
    protected void callEffect()
    {
        character.getTarget().getBattleComponent().getDamage((int)((float)battleComponent.getAtk() * 0.8f), DamageType.Physical);
        AudioManager.getInstance().getSFX().get(SFXName.arrow).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Attack", false, 0f);

        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.4f),
                getEffectAction(),
                Actions.delay(0.8f),
                getEffectAction()
                )
        );
    }
}

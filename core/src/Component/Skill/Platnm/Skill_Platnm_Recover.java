package Component.Skill.Platnm;

import Audio.AudioManager;
import Audio.SFXName;
import Component.Skill.SkillBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.CharacterBase;

public class Skill_Platnm_Recover extends SkillBase {
    public Skill_Platnm_Recover(CharacterBase character){
        super(character);
        skillName = "恢复";
        needChoose = false;
        skillInfo = "使自己恢复自身1倍攻击力的血量";
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        character.getBattleComponent().getHealing(character.getBattleComponent().getAtk());
        AudioManager.getInstance().getSFX().get(SFXName.healing).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

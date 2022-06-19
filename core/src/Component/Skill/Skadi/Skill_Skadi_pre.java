package Component.Skill.Skadi;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Operator;

public class Skill_Skadi_pre extends Skill_Attack{
    public Skill_Skadi_pre(CharacterBase character)
    {
        super(character);
        skillName = "预备";
        skillInfo="增加1ap";
        needChoose = false;
    }

    protected void callSound()
    {
        character.getSkillSounds()[0].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        character.getBattleComponent().addAP(1);
        AudioManager.getInstance().getSFX().get(SFXName.healing).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();


        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                        Actions.delay(0.7f),
                        getEffectAction()
                )
        );
    }
}

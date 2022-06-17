package Component.Skill.SilverAsh;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_SilverAsh_strong extends Skill_Attack{
    public Skill_SilverAsh_strong(CharacterBase character)
    {
        super(character);
        skillName = "雪境生存";
        skillInfo="自身获得2回合坚硬";
        apCost=3;
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
        character.getBattleComponent().buff_hard=2;
        AudioManager.getInstance().getSFX().get(SFXName.shield).play(0.6f);

    }

    @Override
    public void callSkill()
    {
        callSound();
        //System.out.println("test");
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        //System.out.println("test1");
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

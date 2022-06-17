package Component.Skill.Liskarm;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Liskarm_defense extends Skill_Attack {
    public Skill_Liskarm_defense(CharacterBase character){
        super(character);
        skillName ="战术防御";
        skillInfo="自身免疫下一次伤害";
        apCost = 2;
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
        character.getBattleComponent().buff_NoDamage=1;
        AudioManager.getInstance().getSFX().get(SFXName.defBoost).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();
        //System.out.println("test");
        animationComponent.getAnimationState().setAnimation(0, "Skill", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        //System.out.println("test1");
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

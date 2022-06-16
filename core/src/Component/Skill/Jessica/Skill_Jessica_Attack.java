package Component.Skill.Jessica;


import Audio.AudioManager;
import Audio.SFXName;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Jessica_Attack extends Skill_Attack {
    public Skill_Jessica_Attack(CharacterBase character){
        super(character);
        skillName = "射击";
        skillInfo = "对敌人造成一倍攻击力的物理伤害";
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[0].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        super.callEffect();
        AudioManager.getInstance().getSFX().get(SFXName.pistol).play(0.6f);
    }

    @Override
    public void callSkill(){
        // Call Skeleton Animation
        callSound();
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}

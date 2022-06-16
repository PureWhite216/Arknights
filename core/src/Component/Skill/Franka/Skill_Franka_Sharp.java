package Component.Skill.Franka;

import Audio.AudioManager;
import Audio.SFXName;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Franka_Sharp extends Skill_Attack {
    public Skill_Franka_Sharp(CharacterBase character){
        super(character);
        skillName = "极致锋度";
        skillInfo = "减少3点能量，自身获得3回合的锋利，3回合的易伤";
        apCost = 3;
        needChoose = false;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect(){
        character.getBattleComponent().buff_sharp += 3;
        character.getBattleComponent().buff_fragile +=3;
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill(){
        callSound();
        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Skill", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}

package Component.Skill.Jessica;

import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.Skill.SkillBase;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class Skill_Jessica_Smoking extends SkillBase {
    public Skill_Jessica_Smoking(CharacterBase character){
        super(character);
        skillName = "掩护烟幕";
        apCost = 4;
        needChoose = false;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        if(character.getBattleComponent().buff_Miss + 0.2 <=0.8){
            character.getBattleComponent().buff_Miss += 0.2;
        }else{
            character.getBattleComponent().buff_Miss = (float)0.8;
        }
        AudioManager.getInstance().getSFX().get(SFXName.defBoost).play(0.6f);
    }

    @Override
    public void callSkill(){
        callSound();
        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }

    @Override
    protected RunnableAction getEffectAction()
    {
        return Actions.run(new Runnable() {
            @Override
            public void run() {
                System.out.println("Promote buff_miss");
                callEffect();
            }
        });
    }
}

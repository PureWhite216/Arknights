package Component.Skill.Iris;
import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Character.CharacterBase;
import Component.Skill.SkillBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Iris_Guard extends SkillBase {
    public Skill_Iris_Guard(CharacterBase character){
        super(character);
        skillName = "童话守卫者";
        skillInfo = "获得4点AP，自身攻击力下降60%，持续2回合";
        apCost = 1;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect(){
        character.getBattleComponent().addAP(4);
        character.getBattleComponent().addBuff(new AtkBuff(3,-0.6f));
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Attack_Charge", false);
        animationComponent.getAnimationState().addAnimation(0,"Idle",true,0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

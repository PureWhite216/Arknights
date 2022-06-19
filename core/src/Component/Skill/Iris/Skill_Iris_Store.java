package Component.Skill.Iris;
import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Character.CharacterBase;
import Component.Skill.SkillBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Iris_Store extends SkillBase {
    public Skill_Iris_Store(CharacterBase character){
        super(character);
        skillName = "蓄能";
        skillInfo = "增加1点AP；自身攻击力提升15%，持续3回合";
        needChoose = false;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[0].play(0.5f);
    }

    @Override
    protected void callEffect(){
        character.getBattleComponent().addAP(1);
        character.getBattleComponent().addBuff(new AtkBuff(3,0.15f));
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

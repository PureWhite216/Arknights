package Component.Skill.Texas;
import Character.CharacterBase;
import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Battle.Buff.DefBuff;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Texas_Sharp extends Skill_Texas_Attack{
    public Skill_Texas_Sharp(CharacterBase character){
        super(character);
        skillName = "锋度";
        skillInfo = "自身的攻击力提升100%，防御力下降70%\n持续3回合";
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
        character.getBattleComponent().addBuff(new AtkBuff(3,1.0f));
        character.getBattleComponent().addBuff(new DefBuff(3, -0.7f));
        AudioManager.getInstance().getSFX().get(SFXName.atkBoost).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Skill", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}

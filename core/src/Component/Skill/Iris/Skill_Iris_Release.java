package Component.Skill.Iris;
import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Iris_Release extends Skill_Attack {
    public Skill_Iris_Release(CharacterBase charcater){
        super(charcater);
        skillName = "法术释放";
        skillInfo = "造成自身攻击力2.5倍的法术伤害";
        apCost = 4;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect(){
        character.getTarget().getBattleComponent().getDamage((int)(battleComponent.getAtk() * 2.5), DamageType.Magical);
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0,"Idle",true,0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

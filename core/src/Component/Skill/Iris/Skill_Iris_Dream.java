package Component.Skill.Iris;
import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Iris_Dream extends Skill_Attack {
    public Skill_Iris_Dream(CharacterBase character){
        super(character);
        skillName = "梦乡摇篮";
        skillInfo = "使目标敌人眩晕3回合\n并造成自身攻击力1.5倍的法术伤害";
        apCost = 6;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[3].play(0.5f);
    }

    @Override
    protected void callEffect(){
        character.getTarget().getBattleComponent().buff_Dizzy+=3;
        character.getTarget().getBattleComponent().getDamage((int)(battleComponent.getAtk() * 1.5), DamageType.Magical);
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Skill_2", false);
        animationComponent.getAnimationState().addAnimation(0,"Idle",true,0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

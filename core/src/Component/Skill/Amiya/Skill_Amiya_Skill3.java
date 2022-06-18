package Component.Skill.Amiya;

import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Battle.Buff.BuffName;
import Battle.Buff.MissBuff;
import Component.Skill.SkillBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import Character.CharacterBase;

public class Skill_Amiya_Skill3 extends SkillBase
{

    public Skill_Amiya_Skill3(CharacterBase character)
    {
        super(character);
        skillName = "攻击强化";
        skillInfo = "提示2倍攻击力";
        needChoose = false;
        apCost = 2;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[0].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        character.getBattleComponent().addBuff(new AtkBuff(3, 0.5f)); //攻击力提升50%，持续3回合
        //        character.getBattleComponent().addBuff(new DefBuff(3, 0.5f)); //防御力提升50%，持续3回合
        //        character.getBattleComponent().addBuff(new DefBuff(3, -0.5f)); //防御力下降50%，持续3回合
        //        character.getBattleComponent().addBuff(new ResBuff(3, 10)); //魔抗提升10，持续3回合
//                character.getBattleComponent().addBuff(new MissBuff(3, 0.1f)); //获得10%的闪避，持续3回合
        AudioManager.getInstance().getSFX().get(SFXName.atkBoost).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();
        character.addAction(Actions.sequence(
                Actions.delay(0.2f),
                getEffectAction())
        );
    }
}

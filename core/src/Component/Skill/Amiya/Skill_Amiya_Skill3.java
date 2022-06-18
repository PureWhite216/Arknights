package Component.Skill.Amiya;

import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Battle.Buff.BuffName;
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
        character.getBattleComponent().addBuff(new AtkBuff(3, 0.5f));
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

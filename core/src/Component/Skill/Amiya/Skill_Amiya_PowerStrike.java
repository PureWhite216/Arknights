package Component.Skill.Amiya;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Amiya_PowerStrike extends Skill_Amiya_Attack
{

    public Skill_Amiya_PowerStrike(CharacterBase character)
    {
        super(character);
        skillName = "强力击";
        skillInfo = "对敌人造成2倍攻击力的法术伤害";
        apCost = 2;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk() * 10, DamageType.Magical);
        AudioManager.getInstance().getSFX().get(SFXName.swordMagic).play(0.6f);
    }


}

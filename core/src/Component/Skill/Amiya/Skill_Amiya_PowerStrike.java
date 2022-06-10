package Component.Skill.Amiya;

import Audio.AudioManager;
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
    }

    @Override
    protected void callEffect()
    {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk() * 2);
        AudioManager.getInstance().getHitSFX()[0].play(0.4f);
    }
}
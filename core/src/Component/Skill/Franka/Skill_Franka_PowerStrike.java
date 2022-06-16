package Component.Skill.Franka;
import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;

public class Skill_Franka_PowerStrike extends Skill_Franka_Attack {
    public Skill_Franka_PowerStrike(CharacterBase character){
        super(character);
        skillName = "迅捷打击";
        apCost = 3;
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[1].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk() * 2, DamageType.Physical);
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);
    }
}

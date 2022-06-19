package Component.Skill.Texas;
import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;
import Component.Skill.Franka.Skill_Franka_Attack;

public class Skill_Texas_PowerStrike extends Skill_Texas_Attack {
    public Skill_Texas_PowerStrike(CharacterBase character){
        super(character);
        skillName = "重击";
        skillInfo = "对敌人造成2倍攻击力的物理伤害\n若击杀敌人，返还1点AP";
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
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk() * 2, DamageType.Physical);
        if(character.getTarget().getBattleComponent().isDied()){
            character.getBattleComponent().addAP(1);
        }
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);
    }
}

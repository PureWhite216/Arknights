package Component.Skill.Franka;
import Audio.AudioManager;
import Audio.SFXName;
import Character.CharacterBase;
import Component.DamageType;

public class Skill_Franka_PowerStrike extends Skill_Franka_Attack {
    public Skill_Franka_PowerStrike(CharacterBase character){
        super(character);
        skillName = "迅捷打击";
        skillInfo = "对敌人造成2倍攻击力的物理伤害\n若击杀敌人，返还2点AP";
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
        if(character.getTarget().getBattleComponent().isDied()){
            character.getBattleComponent().addAP(2);
        }
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);
    }
}

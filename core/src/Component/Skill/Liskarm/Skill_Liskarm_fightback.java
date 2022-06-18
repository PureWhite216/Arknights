package Component.Skill.Liskarm;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Enemy;

public class Skill_Liskarm_fightback extends Skill_Attack{
    public Skill_Liskarm_fightback(CharacterBase character){
        super(character);
        skillName ="反击电弧";
        skillInfo = "对所有敌人造成自身攻击力2倍的法术伤害\n每一个敌人有50%的概率被眩晕";
        apCost = 4;
        needChoose = false;
    }

    protected void callSound()
    {
        character.getSkillSounds()[3].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        //super.callEffect();
        for(Enemy enemy: character.getCurrentLevel().getEnemies()){
            if(enemy == null) continue;
            if(Math.random()<0.5f){
                enemy.getBattleComponent().buff_Dizzy=1;
            }
            enemy.getBattleComponent().getDamage((int)((float)battleComponent.getAtk() * 2.0f), DamageType.Magical);
        }
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();
        //System.out.println("test");
        animationComponent.getAnimationState().setAnimation(0, "Skill", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        //System.out.println("test1");
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

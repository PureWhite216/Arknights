package Component.Skill.SilverAsh;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import Character.Enemy;

public class Skill_SilverAsh_silverattack extends Skill_Attack{
    public Skill_SilverAsh_silverattack(CharacterBase character)
    {
        super(character);
        skillName = "真银斩";
        skillInfo="对所有敌人造成自身攻击力3倍的物理伤害\n自身获得2回合的易伤";
        apCost=5;
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
        character.getBattleComponent().buff_fragile=2;
        for(Enemy enemy: character.getCurrentLevel().getEnemies()){
            if(enemy == null) continue;
            enemy.getBattleComponent().getDamage((int)((float)battleComponent.getAtk() * 3.0f), DamageType.Physical);
        }
        AudioManager.getInstance().getSFX().get(SFXName.sword).play(0.6f);

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

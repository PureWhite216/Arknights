package Component.Skill.Saria;

import Audio.AudioManager;
import Audio.SFXName;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import Character.Enemy;
import Character.Operator;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Saria_Ca extends Skill_Attack {
    public Skill_Saria_Ca(CharacterBase character){
        super(character);
        skillName = "钙质化";
        apCost = 6;
        needChoose = false;
        skillInfo = "对所有敌人施加3回合的易伤\n使全体友方获得3回合的坚硬效果";
    }

    @Override
    protected void callSound()
    {
        character.getSkillSounds()[2].play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        for(Enemy enemy: character.getCurrentLevel().getEnemies()){
            if(enemy==null)
                continue;
            enemy.getBattleComponent().buff_fragile += 3;
        }
        for(Operator operator : character.getCurrentLevel().getOperators()){
            if(operator == null)
                continue;
            operator.getBattleComponent().buff_hard += 3;
        }
        AudioManager.getInstance().getSFX().get(SFXName.healing).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        animationComponent.getAnimationState().setAnimation(0, "Skill_Begin", false);
        animationComponent.getAnimationState().addAnimation(0, "Skill_Loop", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Skill_End", false, 0f);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction())
        );
    }
}

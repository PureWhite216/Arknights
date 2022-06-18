package Component.Skill.Warfarin;

import Audio.AudioManager;
import Audio.SFXName;
import Battle.Buff.AtkBuff;
import Character.CharacterBase;
import Character.Operator;
import Component.DamageType;
import Component.Skill.Skill_Attack;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Warfarin_UnstableBlood extends Skill_Attack
{
    public Skill_Warfarin_UnstableBlood(CharacterBase character)
    {
        super(character);
        skillName = "不稳定血";
        needChoose = false;
        apCost = 3;
        skillInfo = "随机选择一名友方，\n使其获得持续2回合的“锋利”效果（攻击力变为2倍)\n且损失15%的最大生命值的血量";
    }

    @Override
    protected void callSound()
    {
        character.getRandomSkillSounds(4).play(0.5f);
    }

    @Override
    protected void callEffect()
    {
        Operator randomOperator = character.getCurrentLevel().getRandomOperator();
        randomOperator.getBattleComponent().addBuff(new AtkBuff(2,1f));
        randomOperator.getBattleComponent().getDamage((int)(randomOperator.getBattleComponent().getMaxHP()*0.15), DamageType.Real);
        AudioManager.getInstance().getSFX().get(SFXName.magic).play(0.6f);
    }

    @Override
    public void callSkill()
    {
        callSound();

        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}


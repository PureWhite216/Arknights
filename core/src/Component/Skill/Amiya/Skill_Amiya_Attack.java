package Component.Skill.Amiya;
import Character.CharacterBase;
import Component.Skill.Skill_Attack;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class Skill_Amiya_Attack extends Skill_Attack
{
    public Skill_Amiya_Attack(CharacterBase character)
    {
        super(character);
        skillName = "斩击";
    }

    @Override
    public void callSkill()
    {
        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Skill_2_Loop", false);
        animationComponent.getAnimationState().addAnimation(0, "Skill_2_Idle", true, 0f);
        // Call Action
        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.05f),
                Actions.moveTo(character.getTarget().getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                getEffectAction(),
                Actions.delay(0.6f),
                Actions.moveTo(character.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
    }
}

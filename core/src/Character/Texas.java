package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Texas extends Operator
{
    static final String atlasPath = "assets/Texas/char_102_texas.atlas";
    static final String skelPath = "assets/Texas/char_102_texas.skel";

    public Texas(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(500, 150, 70);
    }
    @Override
    protected void setAnimationComponent(float scale)
    {
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Idle", "Attack_Loop", 0.5f);
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
        /*Set Animation Mix*/
    }

    @Override
    public void callSkill(SkillName skillName)
    {
        switch(skillName)
        {
            case Attack:
                skillAction_Attack();
        }
    }

    public void skillAction_Attack()
    {
        this.clearActions();
        this.addAction(Actions.sequence(
                Actions.delay(0.4f),
                Actions.moveTo(target.getX() - 100, BattleLevelBase.defaultY, 0.35f, Interpolation.circleIn),
                Actions.delay(0.55f),
                Actions.moveTo(this.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
        callSkillAnimation(SkillAnimation.attack);
    }

    @Override
    public void callSkillAnimation(SkillAnimation skillAnimation)
    {
        switch(skillAnimation)
        {
            case levelStart:
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
            case attack:
                animationComponent.getAnimationState().setAnimation(0, "Attack_Start", false);
                animationComponent.getAnimationState().addAnimation(0, "Attack_Loop", false, 0f);
                animationComponent.getAnimationState().addAnimation(0, "Attack_End", false, 0f);
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
        }
    }
}

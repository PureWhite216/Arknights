package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Level.BattleLevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Enemy_1002 extends Enemy
{
    static final String atlasPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.atlas";
    static final String skelPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.skel";

    public Enemy_1002(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(300, 100, 30);
    }

    @Override
    protected void setAnimationComponent(float scale)
    {
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Idle", "Attack", 0.3f);
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
        callSkillAnimation(SkillAnimation.attack);
        this.clearActions();
        this.addAction(Actions.sequence(
                Actions.moveTo(target.getX() + 100, BattleLevelBase.defaultY, 0.3f, Interpolation.circleIn),
                Actions.delay(0.6f),
                Actions.moveTo(this.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
    }

    @Override
    public void callSkillAnimation(SkillAnimation skillAnimation)
    {
        /*Set the Animation of the skill*/
        switch(skillAnimation)
        {
            case levelStart:
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
            case attack:
                animationComponent.getAnimationState().setAnimation(0, "Attack", false);
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
        }
    }
}

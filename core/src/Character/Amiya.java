package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Level.BattleLevelBase;
import Level.LevelBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.esotericsoftware.spine.SkeletonRenderer;

public class Amiya extends Operator
{
    static final String atlasPath = "assets/Amiya2_Front/char_1001_amiya2.atlas";
    static final String skelPath = "assets/Amiya2_Front/char_1001_amiya2.skel";
    //private AnimationComponent animationComponent;
    //private float health;

    public Amiya(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(500, 200, 50);
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
                Actions.moveTo(target.getX() - 100, BattleLevelBase.defaultY, 0.3f, Interpolation.circleIn),
                Actions.delay(0.6f),
                Actions.moveTo(this.getX(), BattleLevelBase.defaultY, 0.35f, Interpolation.circleOut))
        );
    }

    @Override
    protected void setAnimationComponent(float scale)
    {
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Skill_2_Idle", "Skill_2_Loop", 0.3f);
        /*Set Animation Mix*/
    }


    @Override
    public void callSkillAnimation(SkillAnimation skillAnimation)
    {
        /*Set the Animation of the skill*/
        switch(skillAnimation)
        {
            case levelStart:
                animationComponent.getAnimationState().addAnimation(0, "Skill_2_Idle", true, 0f);
                break;
            case attack:
                animationComponent.getAnimationState().setAnimation(0, "Skill_2_Loop", false);
                animationComponent.getAnimationState().addAnimation(0, "Skill_2_Idle", true, 0f);
                break;
        }
    }


}


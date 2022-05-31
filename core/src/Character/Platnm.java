package Character;

import Component.AnimationComponent;
import Component.BattleComponent;

public class Platnm extends Operator
{
    static final String atlasPath = "assets/Platnm_Front/char_204_platnm.atlas";
    static final String skelPath = "assets/Platnm_Front/char_204_platnm.skel";

    public Platnm(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(300, 150, 45);
    }
    @Override
    protected void setAnimationComponent(float scale)
    {
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Idle", "Attack", 0.3f);
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
                animationComponent.getAnimationState().setAnimation(0, "Attack", false);
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
        }
    }
}

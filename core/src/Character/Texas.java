package Character;

import Component.AnimationComponent;

public class Texas extends CharacterBase
{
    static final String atlasPath = "assets/Texas_Winter_Front/char_102_texas_winter#1.atlas";
    static final String skelPath = "assets/Texas_Winter_Front/char_102_texas_winter#1.skel";

    public Texas(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        health = 1000; // Set Default Health
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
    public void callSkillAnimation(SkillAnimation skillAnimation)
    {
        switch(skillAnimation)
        {
            case levelStart:
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
            case attack:
                animationComponent.getAnimationState().addAnimation(0, "Attack_Start", false, 0f);
                animationComponent.getAnimationState().addAnimation(0, "Attack_Loop", false, 0f);
                animationComponent.getAnimationState().addAnimation(0, "Attack_End", false, 0f);
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
        }
    }
}

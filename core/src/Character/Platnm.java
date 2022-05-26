package Character;

import Component.AnimationComponent;

public class Platnm extends CharacterBase
{
    static final String atlasPath = "assets/Platnm_Front/char_204_platnm.atlas";
    static final String skelPath = "assets/Platnm_Front/char_204_platnm.skel";

    public Platnm(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        health = 1000; // Set Default Health
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
    public void callSkillAnimation(SkillAnimation skillAnimation)
    {
        switch(skillAnimation)
        {
            case levelStart:
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
            case attack:
                animationComponent.getAnimationState().addAnimation(0, "Attack", false, 0f);
                animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
                break;
        }
    }
}

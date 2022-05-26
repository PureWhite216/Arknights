package Character;

import Component.AnimationComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.esotericsoftware.spine.SkeletonMeshRenderer;

public class Amiya extends CharacterBase
{
    static final String atlasPath = "assets/Amiya2_Front/char_1001_amiya2.atlas";
    static final String skelPath = "assets/Amiya2_Front/char_1001_amiya2.skel";
    //private AnimationComponent animationComponent;
    //private float health;

    public Amiya(float posX, float posY)
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
        /*Set the Animation of the skill*/
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


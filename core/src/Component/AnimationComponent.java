package Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.*;
import Character.CharacterBase;

public class AnimationComponent
{
    private float timer = 0f;
    /*Spine*/
    private SkeletonBinary json;
    private TextureAtlas atlas;
    private Skeleton skeleton;
    private SkeletonData skeletonData;
    private AnimationStateData animationStateData;
    private Vector2 screenPos;
    private AnimationState animationState;
    /*Spine*/

    public AnimationComponent(String atlasPath, String skelPath, float scale)
    {
        /*Initialize Variables*/
        System.out.println(atlasPath);
        atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
        json = new SkeletonBinary(atlas);
        json.setScale(scale);
        skeletonData = json.readSkeletonData(Gdx.files.internal(skelPath));
        skeleton = new Skeleton(skeletonData);
        animationStateData = new AnimationStateData(skeletonData);
        animationState = new AnimationState(animationStateData);
        animationState.setTimeScale(1.1f); // Default Time Scale

    }


    public void setScale(float scale)
    {
//        System.out.println(0.6f);
        json.setScale(scale);
//        System.out.println(json.getScale());
    }

    public void setTimeScale(float scale)
    {
        if(scale < 0) scale = 0;
        animationState.setTimeScale(scale);
    }

    public AnimationStateData getAnimationStateData()
    {
        return animationStateData;
    }

    public AnimationState getAnimationState()
    {
        return animationState;
    }

    public Skeleton getSkeleton()
    {
        return skeleton;
    }

    public SkeletonData getSkeletonData()
    {
        return skeletonData;
    }

    public float getWidth()
    {
        return skeletonData.getWidth() * CharacterBase.defaultScale;
    }

    public float getHeight()
    {
        return skeletonData.getHeight() * CharacterBase.defaultScale;
    }
}

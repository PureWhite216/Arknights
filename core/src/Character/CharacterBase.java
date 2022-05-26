package Character;

import Component.AnimationComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.esotericsoftware.spine.SkeletonMeshRenderer;

public abstract class CharacterBase
{
    protected static final float defaultScale = 0.6f;
    protected AnimationComponent animationComponent;
    protected float health;

    protected abstract void setAnimationComponent(float scale);
    public abstract void callSkillAnimation(SkillAnimation skillAnimation);

    public CharacterBase(float posX, float posY, float scale)
    {
        setAnimationComponent(scale);
        setPosition(posX, posY);
        animationComponent.setScale(defaultScale);
    }

    public AnimationComponent getAnimationComponent()
    {
        return animationComponent;
    }

    public float getHealth()
    {
        return health;
    }

    public void update()
    {
        /*Update Spine Animation*/
        animationComponent.getAnimationState().update(Gdx.graphics.getDeltaTime());
        animationComponent.getSkeleton().updateWorldTransform();
        animationComponent.getAnimationState().apply(animationComponent.getSkeleton());
        /*Update Spine Animation*/
    }
    public void render(PolygonSpriteBatch batch, SkeletonMeshRenderer renderer)
    {
        renderer.draw(batch, animationComponent.getSkeleton());
    }
    public void setPosition(float posX, float posY)
    {
        animationComponent.getSkeleton().setPosition(posX, posY);
    }
    public void getDamage(float damage)
    {
        health -= damage;
        if(health <= 0) die();
    }

    public void die()
    {

    }

}


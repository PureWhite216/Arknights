package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

public abstract class CharacterBase extends Actor implements Comparable
{
    public static final float defaultScale = 0.4f;
    protected AnimationComponent animationComponent;
    protected BattleComponent battleComponent;
    protected CharacterBase target;

//    public Button button = new Button();

    protected abstract void setAnimationComponent(float scale);

    public abstract void callSkillAnimation(SkillAnimation skillAnimation);

    public CharacterBase(float posX, float posY, float scale)
    {
        super();
        setAnimationComponent(scale);
        animationComponent.setScale(defaultScale);
//        button.setWidth(animationComponent.getWidth() * defaultScale * 0.8f);
//        button.setHeight(animationComponent.getHeight() * defaultScale);
        setOrigin(animationComponent.getWidth() / 2, 0);
        setSize(animationComponent.getWidth(), animationComponent.getHeight());
        setPosition(posX, posY);
//        button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Click");
//                System.out.printf("%f %f\n", button.getWidth(), button.getHeight());
//                System.out.printf("%f %f\n", button.getX(), button.getY());
//                System.out.printf("Spine: %f %f\n", animationComponent.getSkeleton().getX(), animationComponent.getSkeleton().getY());
//                System.out.printf("Cursor: %f %f\n", x, y);
//                callSkillAnimation(SkillAnimation.attack);
//            }
//        });
    }

    public abstract void callSkill(SkillName skillName);

    public void setTarget(CharacterBase target)
    {
        this.target = target;
    }

    public AnimationComponent getAnimationComponent()
    {
        return animationComponent;
    }

    public BattleComponent getBattleComponent()
    {
        return battleComponent;
    }

    public void update()
    {
        /*Update Spine Animation*/
        animationComponent.getAnimationState().update(Gdx.graphics.getDeltaTime());
        animationComponent.getSkeleton().updateWorldTransform();
        animationComponent.getAnimationState().apply(animationComponent.getSkeleton());
        animationComponent.getSkeleton().setPosition(getX(), getY());
        /*Update Spine Animation*/
    }


    public void render(TwoColorPolygonBatch batch, SkeletonRenderer renderer)
    {
        renderer.draw(batch, animationComponent.getSkeleton());
    }

    @Override
    public void setPosition(float posX, float posY)
    {
        super.setPosition(posX, posY);
//        System.out.println(posX);
        animationComponent.getSkeleton().setPosition(posX, posY);
//        button.setPosition(posX - button.getWidth() / 2 , posY);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha)
    {

    }

    public void die()
    {

    }

    @Override
    public int compareTo(Object o)
    {
        CharacterBase c = (CharacterBase)o;
        if(this.getBattleComponent().getSpeed() > c.getBattleComponent().getSpeed())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}


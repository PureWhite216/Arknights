package Character;

import Audio.AudioManager;
import Audio.SFXName;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.SkillBase;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.Panel_HP;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

import java.util.ArrayList;

public abstract class CharacterBase extends Actor
{
    public static final float defaultScale = 0.4f;
    protected AnimationComponent animationComponent;
    protected BattleComponent battleComponent;
    protected CharacterBase target;
    protected boolean isDied = false;
    protected ArrayList<SkillBase> skills = new ArrayList<>();
    protected BattleLevelBase currentLevel;
    protected Sound[] skillSounds = new Sound[4];
    protected int index;
    public int chosenSkillIndex;

    protected abstract void setAnimationComponent(float scale);

    public CharacterBase(float posX, float posY, float scale)
    {
        super();
        setAnimationComponent(scale);
        animationComponent.setScale(defaultScale);
        setOrigin(animationComponent.getWidth() / 2, 0);
        setSize(animationComponent.getWidth(), animationComponent.getHeight());
        setPosition(posX, posY);
    }

    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        this.currentLevel = currentLevel;
        target = null;
        this.index = index;
        battleComponent.setAp(2);
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

    public void setTarget(CharacterBase target)
    {
        this.target = target;
    }

    @Override
    public void setPosition(float posX, float posY)
    {
        super.setPosition(posX, posY);
//        System.out.println(posX);
        animationComponent.getSkeleton().setPosition(posX, posY);
//        button.setPosition(posX - button.getWidth() / 2 , posY);
    }


    public void die()
    {
        if(!isDied)
        {
            System.out.println("Died");
            animationComponent.getAnimationState().setAnimation(0, "Die", false);
            isDied = true;
            AudioManager.getInstance().getSFX().get(SFXName.die).play(0.4f);
        }
    }

    public boolean isDied()
    {
        return isDied;
    }

    public ArrayList<SkillBase> getSkills()
    {
        return skills;
    }

    public AnimationComponent getAnimationComponent()
    {
        return animationComponent;
    }

    public BattleComponent getBattleComponent()
    {
        return battleComponent;
    }

    public Sound[] getSkillSounds()
    {
        return skillSounds;
    }

    public CharacterBase getTarget()
    {
        return target;
    }

    public Panel_HP getHPPanel()
    {
        if(this instanceof Operator)
        {
            return currentLevel.getHpPanels()[index];
        }
        else
        {
            return currentLevel.getHpPanels()[index + 4];
        }
    }
}


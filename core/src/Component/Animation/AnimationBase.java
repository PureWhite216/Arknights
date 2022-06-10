package Component.Animation;

import Component.AnimationComponent;
import Character.CharacterBase;
public abstract class AnimationBase
{
    protected CharacterBase character;
    protected AnimationComponent animationComponent;
    protected String animName;

    public AnimationBase(CharacterBase character)
    {
        this.character = character;
        animationComponent = character.getAnimationComponent();
    }

    public abstract void callAnimation();
}

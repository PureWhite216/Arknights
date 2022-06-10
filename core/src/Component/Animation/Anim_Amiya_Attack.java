package Component.Animation;

import Component.AnimationComponent;
import Character.CharacterBase;

public class Anim_Amiya_Attack extends AnimationBase
{
    public Anim_Amiya_Attack(CharacterBase character)
    {
        super(character);
        animName = "Attack";
    }

    @Override
    public void callAnimation()
    {
        animationComponent.getAnimationState().setAnimation(0, "Skill_2_Loop", false);
        animationComponent.getAnimationState().addAnimation(0, "Skill_2_Idle", true, 0f);

    }
}

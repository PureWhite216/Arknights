package Component.Skill.Jessica;


import Component.Skill.Skill_Attack;
import Character.CharacterBase;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Skill_Jessica_Attack extends Skill_Attack {
    public Skill_Jessica_Attack(CharacterBase character){
        super(character);
        skillName = "射击";
    }

    @Override
    public void callSkill(){
        // Call Skeleton Animation
        animationComponent.getAnimationState().setAnimation(0, "Attack", false);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

        character.clearActions();
        character.addAction(Actions.sequence(
                Actions.delay(0.5f),
                getEffectAction()
        ));
    }
}

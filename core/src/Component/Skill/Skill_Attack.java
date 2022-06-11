package Component.Skill;

import Audio.AudioManager;
import Character.CharacterBase;
import Component.DamageType;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public abstract class Skill_Attack extends SkillBase
{
    public Skill_Attack(CharacterBase character)
    {
        super(character);
        skillName = "攻击";
    }

    @Override
    protected void callEffect()
    {
        character.getTarget().getBattleComponent().getDamage(battleComponent.getAtk(), DamageType.Physical);
        AudioManager.getInstance().getHitSFX()[0].play(0.4f);
    }

    @Override
    protected RunnableAction getEffectAction()
    {
        return Actions.run(new Runnable() {
            @Override
            public void run() {
                System.out.println("Cause Damage");
                callEffect();
            }
        });
    }

}

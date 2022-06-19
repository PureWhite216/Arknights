package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy_GoPro.Skill_Enemy_GoPro_Attack;
import Component.Skill.Enemy_Slime.Skill_Enemy_Slime_Attack;
import Level.BattleLevelBase;

public class Enemy_Slime extends Enemy
{
    static final String atlasPath = "assets/Enemies/Enemy_Slime/enemy_1007_slime.atlas";
    static final String skelPath = "assets/Enemies/Enemy_Slime/enemy_1007_slime.skel";

    public Enemy_Slime(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(220, 70, 40, 30, this);
        skills.add(new Skill_Enemy_Slime_Attack(this));
        chosenSkillIndex = 0;
    }

    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        super.enterLevel(currentLevel, index);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
    }

    @Override
    protected void setAnimationComponent(float scale)
    {
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Idle", "Attack", 0.3f);
        /*Set Animation Mix*/
    }

}

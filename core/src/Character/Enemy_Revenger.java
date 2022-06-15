package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy1002.Skill_Enemy1002_Attack;
import Component.Skill.Enemy_Revenger.Skill_Enemy_Revenger_Attack;
import Level.BattleLevelBase;

public class Enemy_Revenger extends Enemy
{
    static final String atlasPath = "assets/Enemies/Enemy_Revenger/enemy_1025_reveng.atlas";
    static final String skelPath = "assets/Enemies/Enemy_Revenger/enemy_1025_reveng.skel";

    public Enemy_Revenger(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(350, 80, 50, 0, this);
        skills.add(new Skill_Enemy_Revenger_Attack(this));
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

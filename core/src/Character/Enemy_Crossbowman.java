package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy1002.Skill_Enemy1002_Attack;
import Component.Skill.Enemy_Crossbowman.Skill_Enemy_Crossbowman_Attack;
import Level.BattleLevelBase;

public class Enemy_Crossbowman extends Enemy
{
    static final String atlasPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.atlas";
    static final String skelPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.skel";

    public Enemy_Crossbowman(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(180, 55, 35, 0, this);
        skills.add(new Skill_Enemy_Crossbowman_Attack(this));
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

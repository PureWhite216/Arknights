package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy1002.Skill_Enemy1002_Attack;
import Component.Skill.Enemy_GoPro.Skill_Enemy_GoPro_Attack;
import Level.BattleLevelBase;

public class Enemy_GoPro extends Enemy
{
    static final String atlasPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.atlas";
    static final String skelPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.skel";

    public Enemy_GoPro(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(220, 70, 30, 0, this);
        skills.add(new Skill_Enemy_GoPro_Attack(this));
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

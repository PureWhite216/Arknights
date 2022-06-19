package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy_Demon.Skill_Enemy_Demon_Attack;
import Component.Skill.Enemy_GoPro.Skill_Enemy_GoPro_Attack;
import Component.Skill.Enemy_Slime.Skill_Enemy_Slime_Attack;
import Level.BattleLevelBase;

public class Enemy_Demon extends Enemy
{
    static final String atlasPath = "assets/Enemies/Enemy_Demon/enemy_1010_demon.atlas";
    static final String skelPath = "assets/Enemies/Enemy_Demon/enemy_1010_demon.skel";

    public Enemy_Demon(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(420, 90, 55, 30, this);
        skills.add(new Skill_Enemy_Demon_Attack(this));
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

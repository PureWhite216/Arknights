package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy1002.Skill_Enemy1002_Attack;
import Component.Skill.Enemy_Wizard.Skill_Enemy_Wizard_Attack;
import Level.BattleLevelBase;

public class Enemy_Wizard extends Enemy
{
    static final String atlasPath = "assets/Enemies/Enemy_Wizard/enemy_1011_wizard.atlas";
    static final String skelPath = "assets/Enemies/Enemy_Wizard/enemy_1011_wizard.skel";

    public Enemy_Wizard(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(180, 60, 25, 30, this);
        skills.add(new Skill_Enemy_Wizard_Attack(this));
        chosenSkillIndex = 0;
        animationComponent.getSkeleton().setSkin("Wizard");
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

package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Platnm.Skill_Platnm_Attack;
import Component.Skill.Platnm.Skill_Platnm_QuickShoot;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;

public class Platnm extends Operator
{
    static final String atlasPath = "assets/Platnm_Front/char_204_platnm.atlas";
    static final String skelPath = "assets/Platnm_Front/char_204_platnm.skel";
    static final String soundPath = "assets/Platnm_Front/白金_选中干员1.wav";
    static String[] skillSoundsPath;

    public Platnm(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        battleComponent = new BattleComponent(100, 80, 20, 0, this);
        skills.add(new Skill_Platnm_Attack(this));
        skills.add(new Skill_Platnm_QuickShoot(this));

        skillChooseTable = new SkillChooseTable(this); // Set up skillChooseTable
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
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
        /*Set Animation Mix*/
    }








}

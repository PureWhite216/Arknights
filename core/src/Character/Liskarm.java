package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Amiya.Skill_Amiya_PowerStrike;
import Component.Skill.Liskarm.Skill_Liskarm_Attack;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

public class Liskarm extends Operator{
    /*Set up Assets Path*/
    static final String atlasPath = "assets/Liskarm_Front/liskarm.atlas";
    static final String skelPath = "assets/Liskarm_Front/liskarm.skel";
    static final String soundPath = "assets/Liskarm_Front/雷蛇_选中干员2.wav";
    static String[] skillSoundsPath;

    public Liskarm(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        /*Set Battle Info*/
        battleComponent = new BattleComponent(300, 55, 60, 20, this);
        /*Init Skill*/
        skills.add(new Skill_Liskarm_Attack(this));


        /*Set up skillChooseTable*/
        skillChooseTable = new SkillChooseTable(this);
    }

    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        super.enterLevel(currentLevel, index);
        /*Init Animation*/
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);

    }

    @Override
    protected void setAnimationComponent(float scale)
    {
        /*Set up Animation Component*/
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Idle", "Attack_Loop", 0.5f);
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
    }
}


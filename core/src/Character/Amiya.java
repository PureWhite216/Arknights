package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Amiya.Skill_Amiya_PowerStrike;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class Amiya extends Operator
{
    static final String atlasPath = "assets/Amiya2_Front/char_1001_amiya2.atlas";
    static final String skelPath = "assets/Amiya2_Front/char_1001_amiya2.skel";
    static final String soundPath = "assets/Amiya2_Front/阿米娅_选中干员1.wav";

    public Amiya(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath);
        battleComponent = new BattleComponent(200, 70, 50, this); // Set Battle Info
        /*Init Skill*/
        skills.add(new Skill_Amiya_Attack(this));
        skills.add(new Skill_Amiya_PowerStrike(this));

        skillChooseTable = new SkillChooseTable(this); // Set up skillChooseTable
    }

    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        super.enterLevel(currentLevel, index);
        animationComponent.getAnimationState().addAnimation(0, "Skill_2_Idle", true, 0f);
    }

    @Override
    protected void setAnimationComponent(float scale)
    {
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Skill_2_Idle", "Skill_2_Loop", 0.3f);
        /*Set Animation Mix*/
    }


}


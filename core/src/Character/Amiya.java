package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Amiya.Skill_Amiya_PowerStrike;
import Component.Skill.Amiya.Skill_Amiya_Skill3;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class Amiya extends Operator
{
    /*Set up Assets Path*/
    static final String atlasPath = "assets/Amiya2_Front/char_1001_amiya2.atlas";
    static final String skelPath = "assets/Amiya2_Front/char_1001_amiya2.skel";
    static final String soundPath = "assets/Amiya2_Front/阿米娅_选中干员1.wav";
    static String[] skillSoundsPath = {"assets/Amiya2_Front/阿米娅_作战中1.wav", "assets/Amiya2_Front/阿米娅_作战中4.wav"};

    public Amiya(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        operatorName = "阿米娅";
        imagePath = "assets/Amiya2_Front/Amiya.png";
        /*Set Battle Info*/
        battleComponent = new BattleComponent(200, 70, 25, 20, this);
        /*Init Skill*/
        skills.add(new Skill_Amiya_Attack(this));
        skills.add(new Skill_Amiya_PowerStrike(this));
        skills.add(new Skill_Amiya_Skill3(this));
        /*Set up skillChooseTable*/
        skillChooseTable = new SkillChooseTable(this);
    }

    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        super.enterLevel(currentLevel, index);
        /*Init Animation*/
        animationComponent.getAnimationState().addAnimation(0, "Skill_2_Idle", true, 0f);
    }

    @Override
    protected void setAnimationComponent(float scale)
    {
        /*Set up Animation Component*/
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Skill_2_Idle", "Skill_2_Loop", 0.3f);
    }


}


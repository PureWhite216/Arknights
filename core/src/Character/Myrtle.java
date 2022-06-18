package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Amiya.Skill_Amiya_PowerStrike;
import Component.Skill.Liskarm.Skill_Liskarm_Attack;
import Component.Skill.Liskarm.Skill_Liskarm_PowerAttack;
import Component.Skill.Liskarm.Skill_Liskarm_defense;
import Component.Skill.Liskarm.Skill_Liskarm_fightback;
import Component.Skill.Myrtle.Skill_Myrtle_addap;
import Component.Skill.Myrtle.Skill_Myrtle_addapplus;
import Component.Skill.Myrtle.Skill_Myrtle_attack;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

public class Myrtle extends Operator{
    static final String atlasPath = "assets/Operators/Myrtle/char_151_myrtle.atlas";
    static final String skelPath = "assets/Operators/Myrtle/char_151_myrtle.skel";
    static final String soundPath = "assets/Operators/Myrtle/桃金娘_选中干员1.wav";
    static String[] skillSoundsPath = {"assets/Operators/Myrtle/桃金娘_作战中1.wav","assets/Operators/Myrtle/桃金娘_作战中2.wav","assets/Operators/Myrtle/桃金娘_作战中3.wav"};

    public Myrtle(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        imagePath = "assets/Operators/Myrtle/Myrtle.png";
        operatorName = "桃金娘";
        /*Set Battle Info*/
        battleComponent = new BattleComponent(180, 55, 35, 10, this);
        /*Init Skill*/
        skills.add(new Skill_Myrtle_attack(this));
        skills.add(new Skill_Myrtle_addap(this));
        skills.add(new Skill_Myrtle_addapplus(this));

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
        animationComponent.getAnimationStateData().setMix("Idle", "Skill_Begin", 1f);
        animationComponent.getAnimationStateData().setMix("Skill_Begin", "Skill_Loop", 1f);
        animationComponent.getAnimationStateData().setMix("Skill_Begin", "Skill_End", 0.5f);
    }
}

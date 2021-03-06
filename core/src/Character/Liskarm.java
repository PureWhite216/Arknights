package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Amiya.Skill_Amiya_PowerStrike;
import Component.Skill.Liskarm.Skill_Liskarm_Attack;
import Component.Skill.Liskarm.Skill_Liskarm_PowerAttack;
import Component.Skill.Liskarm.Skill_Liskarm_defense;
import Component.Skill.Liskarm.Skill_Liskarm_fightback;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

public class Liskarm extends Operator{
    /*Set up Assets Path*/
    static final String atlasPath = "assets/Operators/Liskarm/liskarm.atlas";
    static final String skelPath = "assets/Operators/Liskarm/liskarm.skel";
    static final String soundPath = "assets/Operators/Liskarm/雷蛇_选中干员2.wav";
    static String[] skillSoundsPath = {"assets/Operators/Liskarm/雷蛇_作战中1.wav","assets/Operators/Liskarm/雷蛇_作战中2.wav","assets/Operators/Liskarm/雷蛇_作战中3.wav","assets/Operators/Liskarm/雷蛇_作战中4.wav"};
    public static boolean isCreated = false;

    public Liskarm(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        imagePath = "assets/Operators/Liskarm/liskarm.png";
        operatorName = "雷蛇";
        /*Set Battle Info*/
        battleComponent = new BattleComponent(300, 55, 60, 20, this);
        /*Init Skill*/
        skills.add(new Skill_Liskarm_Attack(this));
        skills.add(new Skill_Liskarm_PowerAttack(this));
        skills.add(new Skill_Liskarm_defense(this));
        skills.add(new Skill_Liskarm_fightback(this));
        /*Set up skillChooseTable*/
        skillChooseTable = new SkillChooseTable(this);
    }

    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        super.enterLevel(currentLevel, index);
        /*Init Animation*/
        animationComponent.getAnimationState().addAnimation(0, "Start", false, 0f);
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


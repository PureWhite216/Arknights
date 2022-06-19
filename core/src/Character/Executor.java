package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Executor.Skill_Executor_GrapeShot;
import Component.Skill.Executor.Skill_Executor_LastRide;
import Component.Skill.Executor.Skill_Executor_MuzzlePack;
import Component.Skill.Executor.Skill_Executor_Prepare;
import Component.Skill.Nightmare.Skill_Nightmare_Attack;
import Component.Skill.Nightmare.Skill_Nightmare_PowerfulMagic;
import Component.Skill.Nightmare.Skill_Nightmare_SoulSteal;
import Component.Skill.Nightmare.Skill_Nightmare_TheShadow;
import Level.BattleLevelBase;
import UI.SkillChooseTable;

public class Executor extends Operator{
    /*Set up Assets Path*/
    static final String atlasPath = "assets/Operators/Executor/char_279_excu.atlas";
    static final String skelPath = "assets/Operators/Executor/char_279_excu.skel";
    static final String soundPath = "assets/Operators/Executor/送葬人_选中干员2.wav";
    static String[] skillSoundsPath = {"assets/Operators/Executor/送葬人_作战中1.wav","assets/Operators/Executor/送葬人_作战中2.wav","assets/Operators/Executor/送葬人_作战中3.wav", "assets/Operators/Executor/送葬人_作战中4.wav"};

    public Executor(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        imagePath = "assets/Operators/Executor/executor.png";
        operatorName = "送葬人";
        /*Set Battle Info*/
        battleComponent = new BattleComponent(120, 110, 30, 0, this);
        /*Init Skill*/
        skills.add(new Skill_Executor_Prepare(this));
        skills.add(new Skill_Executor_GrapeShot(this));
        skills.add(new Skill_Executor_MuzzlePack(this));
        skills.add(new Skill_Executor_LastRide(this));

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
        animationComponent.getAnimationStateData().setMix("Idle", "Attack", 0.5f);
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
    }
}



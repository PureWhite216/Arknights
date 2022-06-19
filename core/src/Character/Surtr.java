package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Liskarm.Skill_Liskarm_Attack;
import Component.Skill.Liskarm.Skill_Liskarm_PowerAttack;
import Component.Skill.Liskarm.Skill_Liskarm_defense;
import Component.Skill.Liskarm.Skill_Liskarm_fightback;
import Component.Skill.Surtr.Skill_Surtr_Attack;
import Component.Skill.Surtr.Skill_Surtr_Laevatein;
import Component.Skill.Surtr.Skill_Surtr_MoltenNuclearGiantShadow;
import Component.Skill.Surtr.Skill_Surtr_Nightfall;
import Level.BattleLevelBase;
import UI.SkillChooseTable;

public class Surtr extends Operator{
    /*Set up Assets Path*/
    static final String atlasPath = "assets/Operators/Surtr/char_350_surtr.atlas";
    static final String skelPath = "assets/Operators/Surtr/char_350_surtr.skel";
    static final String soundPath = "assets/Operators/Surtr/史尔特尔_选中干员2.wav";
    static String[] skillSoundsPath = {"assets/Operators/Surtr/史尔特尔_作战中1.wav","assets/Operators/Surtr/史尔特尔_作战中2.wav","assets/Operators/Surtr/史尔特尔_作战中3.wav","assets/Operators/Surtr/史尔特尔_作战中4.wav"};
    public static boolean isCreated = false;

    public Surtr(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        imagePath = "assets/Operators/Surtr/surtr.png";
        operatorName = "史尔特尔";
        /*Set Battle Info*/
        battleComponent = new BattleComponent(250, 50, 40, 20, this);
        /*Init Skill*/
        skills.add(new Skill_Surtr_Attack(this));
        skills.add(new Skill_Surtr_Laevatein(this));
        skills.add(new Skill_Surtr_MoltenNuclearGiantShadow(this));
        skills.add(new Skill_Surtr_Nightfall(this));
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


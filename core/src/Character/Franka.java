package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Franka.Skill_Franka_Attack;
import Component.Skill.Franka.Skill_Franka_PowerStrike;
import Component.Skill.Franka.Skill_Franka_Sharp;
import Component.Skill.Jessica.Skill_Jessica_Attack;
import Component.Skill.Jessica.Skill_Jessica_PowerStrike;
import Component.Skill.Jessica.Skill_Jessica_Smoking;
import Level.BattleLevelBase;
import UI.SkillChooseTable;

public class Franka extends Operator{
    static final String atlasPath = "assets/Operators/Franka/char_106_franka.atlas";
    static final String skelPath = "assets/Operators/Franka/char_106_franka.skel";
    static final String soundPath = "assets/Operators/Franka/芙兰卡_选中干员1.wav";
    static final String[] skillPath = {"assets/Operators/Franka/芙兰卡_作战中1.wav","assets/Operators/Franka/芙兰卡_作战中2.wav"
            , "assets/Operators/Franka/芙兰卡_作战中4.wav"};

    public Franka(float posX, float posY){
        super(posX, posY, defaultScale, soundPath, skillPath);
        /* set battle info */
        battleComponent = new BattleComponent(200, 90, 40, 10, this);
        /* init skill*/
        skills.add(new Skill_Franka_Attack(this));
        skills.add(new Skill_Franka_PowerStrike(this));
        skills.add(new Skill_Franka_Sharp(this));
        skillChooseTable = new SkillChooseTable(this);
    }

    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index) {
        super.enterLevel(currentLevel, index);
        /*Init Animation*/
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
    }

    @Override
    protected void setAnimationComponent(float scale){
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Idle", "Attack", 0.3f);
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
    }
}

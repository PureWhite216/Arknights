package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Iris.Skill_Iris_Dream;
import Component.Skill.Iris.Skill_Iris_Guard;
import Component.Skill.Iris.Skill_Iris_Release;
import Component.Skill.Iris.Skill_Iris_Store;
import Component.Skill.Jessica.Skill_Jessica_Attack;
import Component.Skill.Jessica.Skill_Jessica_PowerStrike;
import Component.Skill.Jessica.Skill_Jessica_Smoking;
import Level.BattleLevelBase;
import UI.SkillChooseTable;

public class Iris extends Operator{
    static final String atlasPath = "assets/Operators/Iris/char_338_iris.atlas";
    static final String skelPath = "assets/Operators/Iris/char_338_iris.skel";
    static final String soundPath = "assets/Operators/Iris/爱丽丝_选中干员1.wav";
    static final String[] skillPath = {"assets/Operators/Iris/爱丽丝_作战中1.wav","assets/Operators/Iris/爱丽丝_作战中2.wav"
            ,"assets/Operators/Iris/爱丽丝_作战中3.wav","assets/Operators/Iris/爱丽丝_作战中4.wav"};
    public Iris(float posX, float posY){
        super(posX, posY, defaultScale, soundPath, skillPath);
        operatorName = "爱丽丝";
        imagePath = "assets/Operators/Franka/Franka.png";
        /* set battle info */
        battleComponent = new BattleComponent(120, 110, 30, 30, this);
        /* init skill*/
        skills.add(new Skill_Iris_Store(this));
        skills.add(new Skill_Iris_Release(this));
        skills.add(new Skill_Iris_Guard(this));
        skills.add(new Skill_Iris_Dream(this));

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

package Character;

import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Jessica.Skill_Jessica_Attack;
import Component.Skill.Jessica.Skill_Jessica_PowerStrike;
import Component.Skill.Jessica.Skill_Jessica_Smoking;
import Component.Skill.Saria.Skill_Saria_Attack;
import Component.Skill.Saria.Skill_Saria_Ca;
import Component.Skill.Saria.Skill_Saria_Medicine;
import Component.Skill.Saria.Skill_Saria_Recover;
import Level.BattleLevelBase;
import UI.SkillChooseTable;

public class Saria extends Operator{
    static final String atlasPath = "assets/Operators/Saria/char_202_demkni.atlas";
    static final String skelPath = "assets/Operators/Saria/char_202_demkni.skel";
    static final String soundPath = "assets/Operators/Saria/塞雷娅_选中干员1.wav";
    static final String[] skillPath = {"assets/Operators/Saria/塞雷娅_作战中1.wav","assets/Operators/Saria/塞雷娅_作战中2.wav"
            , "assets/Operators/Saria/塞雷娅_作战中3.wav", "assets/Operators/Saria/塞雷娅_作战中4.wav"};

    public Saria(float posX, float posY){
        super(posX, posY, defaultScale, soundPath, skillPath);
        operatorName = "塞雷娅";
        imagePath = "assets/Operators/Saria/platnm.png";
        /* set battle info */
        battleComponent = new BattleComponent(340, 50, 50, 20, this);
        /* init skill*/
        skills.add(new Skill_Saria_Attack(this));
        skills.add(new Skill_Saria_Recover(this));
        skills.add(new Skill_Saria_Medicine(this));
        skills.add(new Skill_Saria_Ca(this));
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

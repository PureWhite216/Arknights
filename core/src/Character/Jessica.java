package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Jessica.Skill_Jessica_Attack;
import Component.Skill.SkillBase;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Jessica extends Operator
{
    static final String atlasPath = "assets/Operators/Jessica/jessica.atlas";
    static final String skelPath = "assets/Operators/Jessica/jessica.skel";
    static final String soundPath = "assets/Operators/Jessica/杰西卡_选中干员2.wav";
    static final String[] skillPath = {"assets/Operators/Jessica/杰西卡_作战中1.wav","assets/Operators/Jessica/杰西卡_作战中4.wav"};
    public Jessica(float posX, float posY){
        super(posX, posY, defaultScale, soundPath, skillPath);
        /* set battle info */
        battleComponent = new BattleComponent(150, 80, 20, 0, this);
        /* init skill*/
        skills.add(new Skill_Jessica_Attack(this));
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
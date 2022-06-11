package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy1002.Skill_Enemy1002_Attack;
import Component.Skill.Texas.Skill_Texas_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class Texas extends Operator
{
    static final String atlasPath = "assets/Texas/char_102_texas.atlas";
    static final String skelPath = "assets/Texas/char_102_texas.skel";
    static final String soundPath = "assets/Texas/德克萨斯_选中干员1.wav";

    public Texas(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath);
        battleComponent = new BattleComponent(200, 60, 50, 0, this);

        skills.add(new Skill_Texas_Attack(this));

        skillChooseTable = new SkillChooseTable(this); // Set up skillChooseTable

    }


    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        super.enterLevel(currentLevel, index);
        animationComponent.getAnimationState().addAnimation(0, "Idle", true, 0f);
    }

    @Override
    protected void setAnimationComponent(float scale)
    {
        animationComponent = new AnimationComponent(atlasPath, skelPath, scale);
        /*Set Animation Mix*/
        animationComponent.getAnimationStateData().setMix("Idle", "Attack_Loop", 0.5f);
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
        /*Set Animation Mix*/
    }

}

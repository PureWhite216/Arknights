package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Enemy1002.Skill_Enemy1002_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class Enemy_1002 extends Enemy
{
    static final String atlasPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.atlas";
    static final String skelPath = "assets/enemy_1002_nsabr/enemy_1002_nsabr.skel";

    public Enemy_1002(float posX, float posY)
    {
        super(posX, posY, defaultScale);
        battleComponent = new BattleComponent(200, 40, 30, this);
        skills.add(new Skill_Enemy1002_Attack(this));
        chosenSkillIndex = 0;
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
        animationComponent.getAnimationStateData().setMix("Idle", "Attack", 0.3f);
        /*Set Animation Mix*/
    }

}

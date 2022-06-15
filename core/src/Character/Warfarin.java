package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Amiya.Skill_Amiya_PowerStrike;
import Component.Skill.Liskarm.Skill_Liskarm_Attack;
import Component.Skill.Nightmare.Skill_Nightmare_Attack;
import Component.Skill.Nightmare.Skill_Nightmare_PowerfulMagic;
import Component.Skill.Nightmare.Skill_Nightmare_SoulSteal;
import Component.Skill.Nightmare.Skill_Nightmare_TheShadow;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

public class Warfarin extends Operator{
    /*Set up Assets Path*/
    static final String atlasPath = "assets/Operators/Warfarin/char_171_warfarin.atlas";
    static final String skelPath = "assets/Operators/Warfarin/char_171_warfarin.skel";
    static final String soundPath = "assets/Operators/Nightmare/夜魔_选中干员1.wav";
    static String[] skillSoundsPath;

    public Warfarin(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        /*Set Battle Info*/
        battleComponent = new BattleComponent(100, 40, 20, 10, this);
        /*Init Skill*/
        skills.add(new Skill_Nightmare_Attack(this));
        skills.add(new Skill_Nightmare_PowerfulMagic(this));
        skills.add(new Skill_Nightmare_SoulSteal(this));
        skills.add(new Skill_Nightmare_TheShadow(this));

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
        animationComponent.getAnimationStateData().setMix("Idle", "Attack", 0.5f);
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
    }
}



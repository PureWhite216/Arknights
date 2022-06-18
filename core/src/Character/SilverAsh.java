package Character;

import Audio.AudioManager;
import Component.AnimationComponent;
import Component.BattleComponent;
import Component.Skill.Amiya.Skill_Amiya_PowerStrike;
import Component.Skill.Liskarm.Skill_Liskarm_Attack;
import Component.Skill.Liskarm.Skill_Liskarm_PowerAttack;
import Component.Skill.Liskarm.Skill_Liskarm_defense;
import Component.Skill.Liskarm.Skill_Liskarm_fightback;
import Component.Skill.Nightmare.Skill_Nightmare_Attack;
import Component.Skill.Nightmare.Skill_Nightmare_PowerfulMagic;
import Component.Skill.Nightmare.Skill_Nightmare_SoulSteal;
import Component.Skill.Nightmare.Skill_Nightmare_TheShadow;
import Component.Skill.SilverAsh.Skill_SilverAsh_attack;
import Component.Skill.SilverAsh.Skill_SilverAsh_powerattack;
import Component.Skill.SilverAsh.Skill_SilverAsh_silverattack;
import Component.Skill.SilverAsh.Skill_SilverAsh_strong;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

public class SilverAsh extends Operator{
    static final String atlasPath = "assets/Operators/SilverAsh/char_172_silverash.atlas";
    static final String skelPath = "assets/Operators/SilverAsh/char_172_silverash.skel";
    static final String soundPath = "assets/Operators/SilverAsh/银灰_选中干员2.wav";
    static String[] skillSoundsPath = {"assets/Operators/SilverAsh/银灰_作战中2.wav","assets/Operators/SilverAsh/银灰_作战中3.wav","assets/Operators/SilverAsh/银灰_作战中2.wav","assets/Operators/SilverAsh/银灰_作战中3.wav"};

    public SilverAsh(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        imagePath = "assets/Operators/SilverAsh/silverash.png";
        operatorName = "银灰";
        /*Set Battle Info*/
        battleComponent = new BattleComponent(220, 80, 45, 0, this);
        /*Init Skill*/
        skills.add(new Skill_SilverAsh_attack(this));
        skills.add(new Skill_SilverAsh_powerattack(this));
        skills.add(new Skill_SilverAsh_strong(this));
        skills.add(new Skill_SilverAsh_silverattack(this));
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
        animationComponent.getAnimationStateData().setMix("Idle", "Combat", 0.5f);
        //animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
    }
}

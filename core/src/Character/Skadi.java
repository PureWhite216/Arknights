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
import Component.Skill.Skadi.Skill_Skadi_attack;
import Component.Skill.Skadi.Skill_Skadi_attackplus;
import Component.Skill.Skadi.Skill_Skadi_powerattack;
import Component.Skill.Skadi.Skill_Skadi_pre;
import Component.Skill.SkillBase;
import Component.Skill.Amiya.Skill_Amiya_Attack;
import Level.BattleLevelBase;
import Level.LevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;

public class Skadi extends Operator{
    static final String atlasPath = "assets/Operators/Skadi/char_263_skadi2.atlas";
    static final String skelPath = "assets/Operators/Skadi/char_263_skadi2.skel";
    static final String soundPath = "assets/Operators/Skadi/斯卡蒂_选中干员1.wav";
    static String[] skillSoundsPath = {"assets/Operators/Skadi/斯卡蒂_作战中2.wav","assets/Operators/Skadi/斯卡蒂_作战中3.wav","assets/Operators/Skadi/斯卡蒂_作战中2.wav","assets/Operators/Skadi/斯卡蒂_作战中4.wav"};
    public static boolean isCreated = false;
    public Skadi(float posX, float posY)
    {
        super(posX, posY, defaultScale, soundPath, skillSoundsPath);
        imagePath = "assets/Operators/Skadi/skadi.png";
        operatorName = "斯卡蒂";
        /*Set Battle Info*/
        battleComponent = new BattleComponent(280, 90, 45, 0, this);
        /*Init Skill*/
        skills.add(new Skill_Skadi_pre(this));
        skills.add(new Skill_Skadi_attack(this));
        skills.add(new Skill_Skadi_powerattack(this));
        skills.add(new Skill_Skadi_attackplus(this));
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
        //animationComponent.getAnimationStateData().setMix("Idle", "Combat", 0.5f);
        animationComponent.getAnimationStateData().setMix("Start", "Idle", 0.3f);
    }
}

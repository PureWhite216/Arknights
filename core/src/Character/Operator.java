package Character;

import Audio.AudioManager;
import Audio.SFXName;
import Level.BattleLevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public abstract class Operator extends CharacterBase
{
    protected Sound chosenSound;
    protected SkillChooseTable skillChooseTable;
    protected Sound deadSound = Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/b_char_dead.wav"));
    public Operator(float posX, float posY, float scale, String chosenPath, String[] skillSoundsPath)
    {
        super(posX, posY, scale);
        chosenSound = Gdx.audio.newSound(Gdx.files.internal(chosenPath));
        if(skillSoundsPath == null) return;
        for(int i = 0; i <= skillSoundsPath.length - 1; i++)
        {
            skillSounds[i] = Gdx.audio.newSound(Gdx.files.internal(skillSoundsPath[i]));
        }
    }

    @Override
    public void enterLevel(BattleLevelBase currentLevel, int index)
    {
        super.enterLevel(currentLevel, index);
        chosenSkillIndex = -1;
    }

    @Override
    public void die()
    {
        if(!isDied())
        {
            currentLevel.getOperatorButtons()[index].setVisible(false);
        }
        super.die();
    }

    public SkillChooseTable getSkillChooseTable()
    {
        return skillChooseTable;
    }

    public void playChosenSound()
    {
        AudioManager.getInstance().playCharacterSound(chosenSound);
    }
}


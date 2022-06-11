package Character;

import Audio.AudioManager;
import Level.BattleLevelBase;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public abstract class Operator extends CharacterBase
{
    protected Sound chosenSound;
    protected SkillChooseTable skillChooseTable;
    public Operator(float posX, float posY, float scale, String soundPath)
    {
        super(posX, posY, scale);
        chosenSound = Gdx.audio.newSound(Gdx.files.internal(soundPath));
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


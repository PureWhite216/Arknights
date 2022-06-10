package Audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager
{
    private static AudioManager instance = new AudioManager();
    private String[] musicAssets = new String[10];
    private Sound[] hitSFX = new Sound[3];
    private Sound currentCharacterSound;
    private Music bgm;
    public static float defaultVolume = 0.0f;

    public static AudioManager getInstance()
    {
        return instance;
    }

    private AudioManager()
    {
        musicAssets[0] = "Audio/Music/Tactical Ambush.mp3";
        musicAssets[1] = "assets/Audio/Music/赴遥尘.mp3";
        hitSFX[0] = Gdx.audio.newSound(Gdx.files.internal("assets/Audio/SFX/hit_sword.mp3"));
        hitSFX[1] = Gdx.audio.newSound(Gdx.files.internal("assets/Audio/SFX/hit_arrow.mp3"));
    }

    public Sound[] getHitSFX()
    {
        return hitSFX;
    }

    public void playBGM(int index)
    {
        if(bgm != null) bgm.dispose();
        bgm = Gdx.audio.newMusic(Gdx.files.internal(musicAssets[index]));
        bgm.setLooping(true);
        bgm.setVolume(defaultVolume);
        bgm.play();
    }

    public void StopBGM()
    {
        bgm.stop();
        bgm.dispose();
    }

    public void playCharacterSound(Sound sound)
    {
        if(currentCharacterSound != null)
        {
            currentCharacterSound.stop();
        }
        sound.play(0.5f);
        currentCharacterSound = sound;
    }


    public Music getBgm()
    {
        return bgm;
    }
}

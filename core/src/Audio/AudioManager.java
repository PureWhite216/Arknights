package Audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioManager
{
    private static AudioManager instance = new AudioManager();
    private String[] musicAssets = new String[10];
    private Music bgm;
    public static float defaultVolume = 0.4f;

    public static AudioManager getInstance()
    {
        return instance;
    }

    private AudioManager()
    {
        musicAssets[0] = "Audio/Music/Tactical Ambush.mp3";
        musicAssets[1] = "assets/Audio/Music/赴遥尘.mp3";
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


    public Music getBgm()
    {
        return bgm;
    }
}

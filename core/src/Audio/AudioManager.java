package Audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class AudioManager
{
    private static AudioManager instance = new AudioManager();
    private String[] musicAssets = new String[10];
    private Music bgm;
    private float defaultVolume = 0.1f;

    public static AudioManager getInstance()
    {
        return instance;
    }

    private AudioManager()
    {
        musicAssets[0] = "Audio/Music/Tactical Ambush.mp3";
    }

    public void playBGM(int index)
    {
        if(bgm != null) bgm.dispose();
        bgm = Gdx.audio.newMusic(Gdx.files.internal(musicAssets[index]));
        bgm.setLooping(true);
        bgm.setVolume(defaultVolume);
        bgm.play();
    }

    public Music getBgm()
    {
        return bgm;
    }
}

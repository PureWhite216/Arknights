package Audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.EnumMap;

public class AudioManager
{
    private static final AudioManager instance = new AudioManager();
    private String[] musicAssets = new String[10];
    private static EnumMap<SFXName, Sound> SFX;
    private Sound currentCharacterSound;
    private Music bgm;
    public static float defaultVolume = 0.1f;

    public static AudioManager getInstance()
    {
        return instance;
    }

    private AudioManager()
    {
        musicAssets[0] = "assets/Audio/Music/战斗曲.mp3";
        musicAssets[1] = "assets/Audio/Music/赴遥尘.mp3";
        musicAssets[2] = "assets/Audio/Music/大柏墟.mp3";
        musicAssets[3] = "assets/Audio/Music/终局抵抗者.mp3";
        SFX = new EnumMap<>(SFXName.class);
        SFX.put(SFXName.sword, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/p_atk_swordwave_n.wav")));
        SFX.put(SFXName.arrow, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/p_atk_arrow_h.wav")));
        SFX.put(SFXName.pistol, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/p_atk_pistol_h.wav")));
        SFX.put(SFXName.swordMagic, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/p_atk_magicsword_n_2.wav")));
        SFX.put(SFXName.die, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/b_char_dead.wav")));
        SFX.put(SFXName.magic, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/p_imp_magspell_h.wav")));
        SFX.put(SFXName.atkBoost, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/b_char_atkboost.wav")));
        SFX.put(SFXName.defBoost, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/b_char_defboost.wav")));
        SFX.put(SFXName.healBoost, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/b_char_healboost.wav")));
        SFX.put(SFXName.healing, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/p_atk_heal_h.wav")));
        SFX.put(SFXName.shield, Gdx.audio.newSound(Gdx.files.internal("assets/Audio/BattleSFX/p_imp_shield_n.wav")));
    }

    public EnumMap<SFXName, Sound> getSFX()
    {
        return SFX;
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

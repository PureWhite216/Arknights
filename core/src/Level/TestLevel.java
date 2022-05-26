package Level;
import Audio.AudioManager;
import Character.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.spine.SkeletonMeshRenderer;

public class TestLevel extends LevelBase
{
    private CharacterBase[][] TestCharacters = new CharacterBase[2][4];
    private Texture background;
    public TestLevel(LevelManager levelManager)
    {
        super(levelManager);
        TestCharacters[0][0] = new Amiya(600,0);
        TestCharacters[0][1] = new Texas(400, 0);
        TestCharacters[0][2] = new Platnm(200, 0);
        for(int i = 0; i <= 2; i++)
        {
            TestCharacters[0][i].callSkillAnimation(SkillAnimation.levelStart);
            TestCharacters[0][i].getAnimationComponent().setScale(0.6f);
        }
        background = new Texture("BackGround/BackGroud1.jpg");
        AudioManager.getInstance().playBGM(0);
    }

    @Override
    protected void handleInput()
    {
        if(Gdx.input.isKeyJustPressed((Input.Keys.A)))
        {
            System.out.println("aaa");
            for(CharacterBase character : TestCharacters[0])
            {
                if(character == null) break;
                character.callSkillAnimation(SkillAnimation.attack);
            }
        }
    }

    @Override
    public void update(float deltaTime)
    {
        handleInput();
        for(CharacterBase character : TestCharacters[0])
        {
            if(character == null) break;
            character.update();
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch, PolygonSpriteBatch polygonSpriteBatch, SkeletonMeshRenderer renderer)
    {
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.end();

        polygonSpriteBatch.begin();
        for(CharacterBase character : TestCharacters[0])
        {
            if(character == null) break;
            character.render(polygonSpriteBatch, renderer);
        }
        polygonSpriteBatch.end();
    }

    @Override
    public void dispose()
    {
    }
}

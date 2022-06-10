package Level;

import Audio.AudioManager;
import Character.*;
import UI.SkillChooseTable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class TestLevel extends BattleLevelBase
{
    private float speed = 1f;
    private Texture background;
    private SkillChooseTable skillChooseTable;
    public TestLevel()
    {
        super();
        background = new Texture(Gdx.files.internal("assets/BackGround/BackGroud1.jpg"));

        operators[0] = new Amiya(operatorPos[0],defaultY);
        operators[1] = new Texas(operatorPos[1], defaultY);
        operators[2] = new Platnm(operatorPos[2], defaultY);
        enemies[0] = new Enemy_1002(enemyPos[0], defaultY);
        enemies[1] = new Enemy_1002(enemyPos[1], defaultY);
        enemies[2] = new Enemy_1002(enemyPos[2], defaultY);

        for(int i = 0; i <= 3; i++)
        {
            if(operators[i] == null) break;
            characters.add(operators[i]);
            operators[i].enterLevel(this, i);;
            operators[i].getAnimationComponent().setScale(0.6f);
            stage.addActor(operators[i]);
        }
        for(int i = 0; i <= 3; i++)
        {
            if(enemies[i] == null) break;
            characters.add(enemies[i]);
            enemies[i].enterLevel(this, i);;
            enemies[i].getAnimationComponent().setScale(0.6f);
            enemies[i].setTarget(operators[i]);
            stage.addActor(enemies[i]);
        }
        AudioManager.getInstance().playBGM(0);
        System.out.println("TestLevel: " + stage.getActors().size);
        
        initUI();

        for(int i = 0; i <= 3; i++)
        {
            if(operators[i] == null) break;
            operators[i].getSkillChooseTable().setPosition(operatorPos[i] + 50);
            operators[i].getSkillChooseTable().addToLevel(stage, this, i);
        }
    }



    @Override
    protected void handleInput()
    {
        if(Gdx.input.isKeyJustPressed((Input.Keys.A)))
        {
        }
        if(Gdx.input.justTouched())
        {
            System.out.printf("Pos: %d %d\n", Gdx.input.getX(), Gdx.input.getY());
        }
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        handleInput();
        for(CharacterBase character : operators)
        {
            if(character == null) break;
            character.update();
        }
        for(CharacterBase character : enemies)
        {
            if(character == null) break;
            character.update();
        }
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();

        twoColorPolygonBatch.begin();
        for(CharacterBase character : operators)
        {
            if(character == null) break;
            character.render(twoColorPolygonBatch, renderer);
        }
//        twoColorPolygonBatch.setTransformMatrix(new Matrix4().setToRotation(new Vector3(0, 1, 0), -180));
        for(CharacterBase character : enemies)
        {
            if(character == null) break;
            character.render(twoColorPolygonBatch, renderer);
        }
        twoColorPolygonBatch.end();
        stage.draw();
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new TestLevel();
    }

    @Override
    public void dispose()
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }
}

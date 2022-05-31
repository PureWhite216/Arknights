package Level;

import Battle.BattleHandler;
import Character.SkillName;
import Character.CharacterBase;
import Character.Enemy;
import Character.Operator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public abstract class BattleLevelBase extends LevelBase
{
    protected static float[] operatorPos = {700, 500, 300, 100};
    protected static float[] enemyPos = {1200, 1400, 1600, 1800};
    protected Operator[] operators = new Operator[4];
    protected ArrayList<CharacterBase> characters = new ArrayList<>();
    protected Enemy[] enemies = new Enemy[4];
    protected Button[] operatorButtons = new Button[4];
    public static float defaultY = 200;
    protected Image targetBox;
    protected float roundExecuteTimer = 0;
    protected BattleHandler battleHandler;

    protected void initUI()
    {
        targetBox = new Image(new Texture(Gdx.files.internal("assets/Images/TargetBox.png")));
        targetBox.setPosition(700 - targetBox.getWidth() / 2, defaultY);
        targetBox.setScale(0.7f, 0.7f);
        stage.addActor(targetBox);
        targetBox.setVisible(false);
        Button.ButtonStyle style = new Button.ButtonStyle();

//        Texture startTexture = new Texture(Gdx.files.internal("assets/Images/TargetBox.png"));
//        Texture downTexture = new Texture(Gdx.files.internal("assets/Images/TargetBox.png"));
//        style.up = new TextureRegionDrawable(new TextureRegion(startTexture));
//        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));
        for(int i = 0; i <= 3; i++)
        {
            operatorButtons[i] = new Button(style);
            operatorButtons[i].setHeight(460 * 0.4f);
            operatorButtons[i].setWidth(432 * 0.4f);
            operatorButtons[i].setPosition(operatorPos[i] - operatorButtons[i].getWidth() / 2, defaultY);
//            operatorButtons[i].setTouchable(Touchable.enabled);
            final int finalI = i;
            operatorButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println(finalI + "Click");
                    targetBox.setVisible(true);
                    targetBox.setPosition(operatorPos[finalI] - 0.7f * targetBox.getWidth() / 2, defaultY - 20);
                }
            });
//            System.out.println(i + operatorButtons[i].getY());
            stage.addActor(operatorButtons[i]);
        }
//        System.out.println("BattleLevel: " + stage.getActors().size);
        battleHandler = new BattleHandler(this);
    }

    protected void roundExecute()
    {
        Collections.sort(characters);
        battleHandler.execute();
    }

    public ArrayList<CharacterBase> getCharacters()
    {
        return characters;
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        battleHandler.update(deltaTime);
    }
}

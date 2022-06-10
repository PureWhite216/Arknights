package Level;

import Battle.BattleHandler;
import Character.CharacterBase;
import Character.Enemy;
import Character.Operator;
import UI.Panel_HP;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Collections;

public abstract class BattleLevelBase extends LevelBase
{
    public static float defaultY = 200;
    protected static float[] operatorPos = {750, 550, 350, 150};
    protected static float[] enemyPos = {1200, 1400, 1600, 1800};
    
    protected Operator[] operators = new Operator[4];
    protected Enemy[] enemies = new Enemy[4];
    protected ArrayList<CharacterBase> characters = new ArrayList<>();

    protected static Texture enemyBox = new Texture(Gdx.files.internal("assets/Images/EnemyBox.png"));
    protected static Texture operatorBox = new Texture(Gdx.files.internal("assets/Images/operatorBox.png"));
    protected Button[] operatorButtons = new Button[4];
    protected Button[] enemyButtons = new Button[4];
    protected Button readyButton;
    protected Button nextButton;
    protected Image preImage;
    protected Image Image_Battling;
    protected Image endImage;
    protected Image[] readyImages = new Image[4];
    protected Panel_HP[] hpPanels = new Panel_HP[8];

    protected static final Texture Texture_preImage= new Texture(Gdx.files.internal("assets/Images/准备阶段.png"));
    protected static final Texture Texture_Battling = new Texture(Gdx.files.internal("assets/Images/battling.png"));
    protected static final Texture Texture_Ready = new Texture(Gdx.files.internal("assets/Images/ready2.png"));

    protected BattleHandler battleHandler;

    protected int choosingOperator;
    protected int chosenEnemy;

    public void setChoosingOperator(int choosingOperator)
    {
        this.choosingOperator = choosingOperator;
    }
    public void setChoosingTarget(boolean choosingTarget)
    {
        isChoosingTarget = choosingTarget;
    }

    protected boolean isChoosingTarget = false;

    protected void initUI()
    {
        Texture_Ready.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture_Battling.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image_Battling = new Image(Texture_Battling);
        Image_Battling.setPosition(stage.getWidth() / 2 - Image_Battling.getWidth() / 2, stage.getHeight() * 0.75f + Image_Battling.getHeight() / 2);
        stage.addActor(Image_Battling);
        Image_Battling.setVisible(false);

        Texture_preImage.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        preImage = new Image(Texture_preImage);
        preImage.setPosition(stage.getWidth() / 2 - preImage.getWidth() / 2, stage.getHeight() * 0.75f + Image_Battling.getHeight() / 2);
        stage.addActor(preImage);

        /*Set Ready Button*/
        Texture ready_Texture = new Texture(Gdx.files.internal("assets/Button/ready.png"));
        Button.ButtonStyle ready_Style = new Button.ButtonStyle();
        ready_Style.up = new TextureRegionDrawable(new TextureRegion(ready_Texture));
        ready_Style.down = new TextureRegionDrawable(new TextureRegion(ready_Texture));
        readyButton = new Button(ready_Style);
        readyButton.setPosition(stage.getWidth() / 2 - readyButton.getWidth() / 2, stage.getHeight() * 0.7f);
        readyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Ready");
                preImage.setVisible(false);
                Image_Battling.setVisible(true);
                roundExecute();
            }
        });
        stage.addActor(readyButton);
        readyButton.setVisible(false);


        /*Set Button*/
        Button.ButtonStyle operatorStyle = new Button.ButtonStyle();
        Button.ButtonStyle enemyStyle = new Button.ButtonStyle();
        operatorStyle.over = new TextureRegionDrawable(new TextureRegion(operatorBox));
        enemyStyle.over = new TextureRegionDrawable(new TextureRegion(enemyBox));
        for(int i = 0; i <= 3; i++)
        {
            if(operators[i] == null) break;
            operatorButtons[i] = new Button(operatorStyle);
            operatorButtons[i].setHeight(460 * 0.4f);
            operatorButtons[i].setWidth(432 * 0.4f);
            operatorButtons[i].setPosition(operatorPos[i] - operatorButtons[i].getWidth() / 2, defaultY);
            final int finalI = i;
            operatorButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    for(int j = 0; j <= 3; j++)
                    {
                        if(operators[j] == null || finalI == j) continue;
                        operators[j].getSkillChooseTable().hide();
                    }
                    operators[finalI].getSkillChooseTable().switchShow();
                    operators[finalI].playChosenSound();
                }
            });
            stage.addActor(operatorButtons[i]);

            hpPanels[i] = new Panel_HP();
            hpPanels[i].addToLevel(stage, this, i);
            hpPanels[i].setPosition(operatorPos[i] - hpPanels[i].getHpImage().getWidth() / 2);
            hpPanels[i].initHP(operators[i].getBattleComponent().getHP(), operators[i].getBattleComponent().getMaxHP());

            readyImages[i] = new Image(Texture_Ready);
            readyImages[i].setPosition(operatorPos[i] - readyImages[i].getWidth() / 2, 350);
            readyImages[i].setVisible(false);
            stage.addActor(readyImages[i]);
        }

        for(int i = 0; i <= 3; i++)
        {
            if(enemies[i] == null) break;
            enemyButtons[i] = new Button(enemyStyle);
            enemyButtons[i].setHeight(460 * 0.4f);
            enemyButtons[i].setWidth(432 * 0.4f);
            enemyButtons[i].setPosition(enemyPos[i] - enemyButtons[i].getWidth() / 2, defaultY);
            final int finalI = i;
            enemyButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(isChoosingTarget)
                    {
                        chosenEnemy = finalI;
                        operators[choosingOperator].setTarget(enemies[finalI]);
                        operators[choosingOperator].getSkillChooseTable().hide();
                        readyImages[choosingOperator].setVisible(true);
                        if(checkIsReady())
                        {
                            readyButton.setVisible(true);
                        }
                    }
                    isChoosingTarget = false;
                }
            });
            stage.addActor(enemyButtons[i]);

            hpPanels[i + 4] = new Panel_HP();
            hpPanels[i + 4].addToLevel(stage, this, i + 4);
            hpPanels[i + 4].setPosition(enemyPos[i] - hpPanels[i + 4].getHpImage().getWidth() / 2);
            hpPanels[i + 4].initHP(enemies[i].getBattleComponent().getHP(), enemies[i].getBattleComponent().getMaxHP());

        }

        endImage = new Image(new Texture(Gdx.files.internal("assets/Images/BattleEnd.png")));
        endImage.setPosition(0, 0);
        endImage.setVisible(false);
        stage.addActor(endImage);

        Texture next_Texture = new Texture(Gdx.files.internal("assets/Button/next.png"));
        Texture next_pressed_Texture = new Texture(Gdx.files.internal("assets/Button/next_pressed.png"));
        Button.ButtonStyle next_Style = new Button.ButtonStyle();
        next_Style.up = new TextureRegionDrawable(new TextureRegion(next_Texture));
        next_Style.down = new TextureRegionDrawable(new TextureRegion(next_pressed_Texture));
        nextButton = new Button(next_Style);
        nextButton.setPosition(stage.getWidth() / 2 - nextButton.getWidth() / 2, stage.getHeight() * 0.2f);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("next");
                isLevelEnd = true;
            }
        });
        stage.addActor(nextButton);
        nextButton.setVisible(false);

        battleHandler = new BattleHandler(this);
    }

    public boolean checkIsReady()
    {
        for(int i = 0; i <= 3; i++)
        {
            if(operators[i] == null) break;
            if(operators[i].getTarget() == null && !operators[i].isDied()) return false;
        }
        return true;
    }


    protected void roundExecute()
    {
        for(int i = 0; i <= 3; i++)
        {
            if(operators[i] == null) break;
            readyImages[i].setVisible(false);
        }
        for(int i = 0; i <= 3; i++)
        {
            if(enemies[i] == null) break;
            enemies[i].chooseTarget();
        }
        readyButton.setVisible(false);
        Collections.sort(characters);
        battleHandler.execute();
    }

    public void battleEnd()
    {
        endImage.setVisible(true);
        nextButton.setVisible(true);
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        battleHandler.update(deltaTime);
    }
    public ArrayList<CharacterBase> getCharacters()
    {
        return characters;
    }

    public Button getReadyButton()
    {
        return readyButton;
    }

    public Operator[] getOperators()
    {
        return operators;
    }

    public Enemy[] getEnemies()
    {
        return enemies;
    }

    public Button[] getOperatorButtons()
    {
        return operatorButtons;
    }

    public Button[] getEnemyButtons()
    {
        return enemyButtons;
    }

    public Image getPreImage()
    {
        return preImage;
    }

    public Image getImage_Battling()
    {
        return Image_Battling;
    }

    public Panel_HP[] getHpPanels()
    {
        return hpPanels;
    }

    public Image[] getReadyImages()
    {
        return readyImages;
    }
}

package Level;

import Audio.AudioManager;
import Battle.BattleHandler;
import Battle.TeamManager;
import Character.CharacterBase;
import Character.Enemy;
import Character.Operator;
import UI.BattleEndTable;
import UI.ChangeOperatorTable;
import UI.Panel_AP;
import UI.Panel_HP;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

    protected Texture background = new Texture(Gdx.files.internal("assets/BackGround/BackGroud1.jpg"));
    protected static Texture enemyBox = new Texture(Gdx.files.internal("assets/Images/EnemyBox.png"));
    protected static Texture operatorBox = new Texture(Gdx.files.internal("assets/Images/operatorBox.png"));
    protected Button[] operatorButtons = new Button[4];
    protected Button[] enemyButtons = new Button[4];
    protected Button readyButton;
    protected Image preImage;
    protected Image Image_Battling;
    protected Image[] readyImages = new Image[4];
    protected Panel_HP[] hpPanels = new Panel_HP[8];
    protected Panel_AP[] apPanels = new Panel_AP[4];

    protected BattleEndTable battleEndTable;
    protected ChangeOperatorTable changeOperatorTable;

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

    protected void initLevel()
    {
        initCharacters();
        initUI();
        AudioManager.getInstance().playBGM(0);
        battleHandler = new BattleHandler(this);
    }

    protected void initCharacters()
    {
        for(int i = 0; i <= 3; i++)
        {
            if(operators[i] == null) break;
            characters.add(operators[i]);
            operators[i].enterLevel(this, i);
            operators[i].getAnimationComponent().setScale(0.6f);
            stage.addActor(operators[i]);

            operators[i].getBattleComponent().levelInit();
        }
        for(int i = 0; i <= 3; i++)
        {
            if(enemies[i] == null) break;
            characters.add(enemies[i]);
            enemies[i].enterLevel(this, i);
            enemies[i].getAnimationComponent().setScale(0.6f);
            enemies[i].setTarget(operators[i]);
            stage.addActor(enemies[i]);

            enemies[i].getBattleComponent().levelInit();
        }
    }

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

            apPanels[i] = new Panel_AP();
            apPanels[i].addToLevel(stage, this, i);
            apPanels[i].setPosition(operatorPos[i] - apPanels[i].getApPanel().getWidth() / 2);
            apPanels[i].updateAP(operators[i].getBattleComponent().getAp());

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


        for(int i = 0; i <= 3; i++)
        {
            if(operators[i] == null) break;
            operators[i].getSkillChooseTable().setPosition(operatorPos[i] + 50);
            operators[i].getSkillChooseTable().addToLevel(stage, this, i);
        }

        battleEndTable = new BattleEndTable();
        battleEndTable.addToLevel(stage, this, 0);
        battleEndTable.hide();
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
        battleHandler.execute();
    }

    public void battleEnd()
    {
        battleEndTable.show();
        TeamManager.getInstance().createNewOperator();
        changeOperatorTable = new ChangeOperatorTable();
        changeOperatorTable.addToLevel(stage, this, 0);
        AudioManager.getInstance().playBGM(3);
    }

    @Override
    protected LevelBase loadNextLevel()
    {
        return new TestLevel();
    }

    @Override
    public void update(float deltaTime)
    {
        super.update(deltaTime);
        battleHandler.update(deltaTime);
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

    /*Do Not Forget to Dispose*/
    @Override
    public void dispose()
    {

    }

    @Override
    public void resize(int width, int height)
    {

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

    public Operator getMinHPOperator(){
        double minHP = 2;
        Operator minHPOperator = null;
        for (Operator operator : operators) {
            if(operator != null && !operator.isDied()){
                if ((double) operator.getBattleComponent().getHP() / (double) operator.getBattleComponent().getMaxHP() < minHP) {
                    minHP = (double) operator.getBattleComponent().getHP() / (double) operator.getBattleComponent().getMaxHP();
                    minHPOperator = operator;
                }
            }
        }
        return minHPOperator;
    }

    public Operator getRandomOperator(){
        Operator[] randomOperators = new Operator[4];
        int i = 0;
        for (Operator operator : operators) {
            if(operator != null && !operator.isDied()){
                randomOperators[i] = operator;
                i++;
            }
        }
        int j = (int)(Math.random()*i);
        return randomOperators[j];
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

    public ChangeOperatorTable getChangeOperatorTable()
    {
        return changeOperatorTable;
    }

    public Panel_AP[] getApPanels()
    {
        return apPanels;
    }
}

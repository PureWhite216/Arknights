package UI;

import Character.Operator;
import Component.Skill.SkillBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class SkillChooseTable extends BattleUI
{
    private Button.ButtonStyle style;
    private ArrayList<Button> buttons = new ArrayList<>();
    private Image skillTable;
    private Label skillNameLabel;
    private Label.LabelStyle labelStyle;
    private static final Texture upTexture = new Texture(Gdx.files.internal("assets/Button/button1.png"));
    private static final Texture downTexture = new Texture(Gdx.files.internal("assets/Button/button1_pressed.png"));
    private Operator operator;
    private static final Texture tableTexture = new Texture(Gdx.files.internal("assets/Images/SkillTable.png"));

    public SkillChooseTable(Operator operator)
    {
        super();

        this.operator = operator;

        group = new Group();
        group.setScale(0.75f);
        group.setVisible(false);

        tableTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        skillTable = new Image(tableTexture);
        group.addActor(skillTable);
        group.setY(300);

        generateSkillButtons();
    }

    private void generateSkillButtons()
    {
        upTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        downTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        style = new Button.ButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(upTexture));
        style.down = new TextureRegionDrawable(new TextureRegion(downTexture));

        labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = new Color(0, 0, 0, 1);

        for(SkillBase skill : operator.getSkills())
        {
            Button button;
            button = new Button(style);
            button.setPosition(30, 310 - button.getHeight() * buttons.size());
            final int finalI = buttons.size();
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(operator.getSkills().get(finalI).getApCost() > operator.getBattleComponent().getAp()) return;

                    if(operator.getSkills().get(finalI).isNeedChoose())
                    {
                        level.setChoosingTarget(true);
                        level.setChoosingOperator(index);
                    }
                    else
                    {
                        operator.getSkillChooseTable().hide();
                        operator.setTarget(operator);
                    }

                    if(operator.chosenSkillIndex != -1)
                    {
                        operator.getBattleComponent().addAP(operator.getSkills().get(operator.chosenSkillIndex).getApCost());
                    }
                    operator.chosenSkillIndex = finalI;
                    operator.getBattleComponent().costAP(operator.getSkills().get(finalI).getApCost());
                    level.getApPanels()[index].updateAP(operator.getBattleComponent().getAp());
                }
            });
            buttons.add(button);
            group.addActor(button);

            skillNameLabel = new Label(skill.getSkillName(), labelStyle);
            skillNameLabel.setPosition(35, 15);
            button.addActor(skillNameLabel);
        }
    }

}

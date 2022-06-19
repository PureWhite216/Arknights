package UI;

import Audio.AudioManager;
import Battle.TeamManager;
import Character.Operator;
import Component.BattleComponent;
import Component.Skill.SkillBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

public class OperatorFormationTable extends StoryUI
{
    private static Texture Texture_operatorFormation = new Texture(Gdx.files.internal("assets/Images/OperatorFormationBackground.png"));
    private static Texture Texture_backUp = new Texture(Gdx.files.internal("assets/Button/Back.png"));
    private static Texture Texture_backHover = new Texture(Gdx.files.internal("assets/Button/BackHover.png"));
    private static Texture Texture_noInfo = new Texture(Gdx.files.internal("assets/Images/NoInfo.png"));
    private static Texture Texture_skillInfos = new Texture(Gdx.files.internal("assets/Images/SkillInfo.png"));
    private static Texture Texture_decal = new Texture(Gdx.files.internal("assets/Images/decal.png"));
    private static Texture Texture_left = new Texture(Gdx.files.internal("assets/Button/Left.png"));
    private static Texture Texture_leftOver = new Texture(Gdx.files.internal("assets/Button/Left_over.png"));
    private static Texture Texture_right = new Texture(Gdx.files.internal("assets/Button/Right.png"));
    private static Texture Texture_rightOver = new Texture(Gdx.files.internal("assets/Button/Right_over.png"));
    private Image Image_operatorFormation;
    private Image[] Image_skillInfos = new Image[4];
    private Image[] Image_decal = new Image[4];
    private Button[] Button_left = new Button[3];
    private Button.ButtonStyle Style_left;
    private Button[] Button_right = new Button[3];
    private Button.ButtonStyle Style_right;
    private Button Button_back;
    private Button.ButtonStyle Style_back;
    private Button[] Button_OperatorImage = new Button[4];
    private Button.ButtonStyle[] Style_OperatorImage = new Button.ButtonStyle[4];
    private Label[] Label_skillNames = new Label[4];
    private Label[] Label_skillInfo = new Label[4];
    private Label[] Label_skillCost = new Label[4];
    private Label[] Label_operatorName = new Label[4];
    private Label hpLabel;
    private Label atkLabel;
    private Label defLabel;
    private Label resLabel;
    private Label.LabelStyle labelStyle;
    private Label.LabelStyle labelStyleWhite;
    private Label.LabelStyle labelStyleName;
    private Label.LabelStyle labelStyleInfo;
    
    private Operator[] operators;
    private Group[] Group_skill = new Group[4];

    private int chosenIndex = 0;

    public OperatorFormationTable()
    {
        getTeamMembers();

        Texture_operatorFormation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture_noInfo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture_skillInfos.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture_decal.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        group= new Group();
        group.setVisible(false);

        Image_operatorFormation = new Image(Texture_operatorFormation);
        group.addActor(Image_operatorFormation);

        Style_back = new Button.ButtonStyle();
        Style_back.up = new TextureRegionDrawable(new TextureRegion(Texture_backUp));
        Style_back.over = new TextureRegionDrawable(new TextureRegion(Texture_backHover));

        Button_back = new Button(Style_back);
        Button_back.setPosition(70, 910);
        Button_back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });

        for(int i = 0; i <= 3; i++)
        {
            Style_OperatorImage[i] = new Button.ButtonStyle();
            Style_OperatorImage[i].up = new TextureRegionDrawable(new TextureRegion(Texture_noInfo));
        }

        for(int i = 0; i <= 1; i++)
        {
            Image_skillInfos[i] = new Image(Texture_skillInfos);
//            Image_skillInfos[i].setPosition(620 + (Texture_skillInfos.getWidth() + 70) * i, 245);
            Group_skill[i] = new Group();
            Group_skill[i].setPosition(620 + (Texture_skillInfos.getWidth() + 70) * i, 245);
            Group_skill[i].addActor(Image_skillInfos[i]);
        }

        for(int i = 2; i <= 3; i++)
        {
            Image_skillInfos[i] = new Image(Texture_skillInfos);
//            Image_skillInfos[i].setPosition(620 + (Texture_skillInfos.getWidth() + 70) * (i - 2), 245 - Texture_skillInfos.getHeight());
            Group_skill[i] = new Group();
            Group_skill[i].setPosition(620 + (Texture_skillInfos.getWidth() + 70) * (i - 2), 245 - Texture_skillInfos.getHeight());
            Group_skill[i].addActor(Image_skillInfos[i]);
        }

        labelStyleInfo = new Label.LabelStyle();
        labelStyleInfo.font = bitmapFontName;
        labelStyleInfo.fontColor = new Color(0, 0, 0, 0.9f);

        labelStyleName = new Label.LabelStyle();
        labelStyleName.font = bitmapFontName;
        labelStyleName.fontColor = new Color(1, 1, 1, 1f);

        labelStyleWhite = new Label.LabelStyle();
        labelStyleWhite.font = bitmapFont;
        labelStyleWhite.fontColor = new Color(1, 1, 1, 1f);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = new Color(0, 0, 0, 0.8f);

        for(int i = 0; i <= 3; i++)
        {
            Label_skillNames[i] = new Label("Name", labelStyleWhite);
            Label_skillNames[i].setPosition(28, 158);
            Label_skillNames[i].setFontScale(0.9f);
            Label_skillInfo[i] = new Label("Info", labelStyleInfo);
            Label_skillInfo[i].setPosition(25, 110);
            Label_skillInfo[i].setFontScale(0.75f);
            Label_skillCost[i] = new Label("0", labelStyle);
            Label_skillCost[i].setPosition(443, 159);
            Group_skill[i].addActor(Label_skillNames[i]);
            Group_skill[i].addActor(Label_skillInfo[i]);
            Group_skill[i].addActor(Label_skillCost[i]);
            group.addActor(Group_skill[i]);
        }

        for(int i = 0; i <= 3; i++)
        {
            Button_OperatorImage[3 - i] = new Button(Style_OperatorImage[3 - i]);
            Button_OperatorImage[3 - i].setPosition(460 + 267 * i, 565);
            group.addActor(Button_OperatorImage[3 - i]);
            final int finalI = 3 - i;
            Button_OperatorImage[3 - i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    chosenIndex = finalI;
                    updateInfo();
                }
            });
            Image_decal[3 - i] = new Image(Texture_decal);
            Image_decal[3 - i].setPosition(Button_OperatorImage[3 - i].getX() - 19, 545);
            Image_decal[3 - i].setTouchable(Touchable.disabled);
            group.addActor(Image_decal[3 - i]);

            String name;
            if(operators[3 - i] == null)
            {
                name = "Null";
            }
            else
            {
                name = operators[3 - i].getOperatorName();
            }
            Label_operatorName[3 - i] = new Label(name, labelStyleName);
            Label_operatorName[3 - i].setAlignment(Align.center);
            Label_operatorName[3 - i].setPosition(Image_decal[3 - i].getX() + 160, 582, Align.center);
            group.addActor(Label_operatorName[3 - i]);
        }

        Style_left = new Button.ButtonStyle();
        Style_left.up = new TextureRegionDrawable(new TextureRegion(Texture_left));
        Style_left.over = new TextureRegionDrawable(new TextureRegion(Texture_leftOver));

        Style_right = new Button.ButtonStyle();
        Style_right.up = new TextureRegionDrawable(new TextureRegion(Texture_right));
        Style_right.over = new TextureRegionDrawable(new TextureRegion(Texture_rightOver));
        for(int i = 0; i <= 2; i++)
        {
            final int finalI = 3 - i;
            Button_left[i] = new Button(Style_left);
            Button_left[i].setPosition(702 + 267 * i, 680);
            Button_left[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Operator tmp;
                    tmp = TeamManager.getInstance().teamMembers[finalI];
                    TeamManager.getInstance().teamMembers[finalI] = TeamManager.getInstance().teamMembers[finalI - 1];
                    TeamManager.getInstance().teamMembers[finalI - 1] = tmp;
                    getTeamMembers();
                    updateInfo();
                    updateImage();
                }
            });
            group.addActor(Button_left[i]);

            Button_right[i] = new Button(Style_right);
            Button_right[i].setPosition(657 + 267 * i, 680);
            Button_right[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Operator tmp;
                    tmp = TeamManager.getInstance().teamMembers[finalI];
                    TeamManager.getInstance().teamMembers[finalI] = TeamManager.getInstance().teamMembers[finalI - 1];
                    TeamManager.getInstance().teamMembers[finalI - 1] = tmp;
                    getTeamMembers();
                    updateInfo();
                    updateImage();
                }
            });
            group.addActor(Button_right[i]);
        }

        hpLabel = new Label("100/100", labelStyle);
        hpLabel.setPosition(360, 368, Align.center);
        hpLabel.setFontScale(0.8f);
        group.addActor(hpLabel);

        atkLabel = new Label("80", labelStyle);
        atkLabel.setPosition(350, 297, Align.center);
        atkLabel.setFontScale(0.8f);
        group.addActor(atkLabel);

        defLabel = new Label("40", labelStyle);
        defLabel.setPosition(350, 228, Align.center);
        defLabel.setFontScale(0.8f);
        group.addActor(defLabel);

        resLabel = new Label("10", labelStyle);
        resLabel.setPosition(350, 159, Align.center);
        resLabel.setFontScale(0.8f);
        group.addActor(resLabel);

        group.addActor(Button_back);
        updateImage();
        updateInfo();
    }

    public void updateInfo()
    {
        if(TeamManager.getInstance().teamMembers[chosenIndex] == null) return;
        Operator tmp = TeamManager.getInstance().teamMembers[chosenIndex];
        BattleComponent battleComponent = tmp.getBattleComponent();

        int currentHP = battleComponent.getHP();
        int maxHP = battleComponent.getHP();
        int atk = battleComponent.getInitialAtk();
        int def = battleComponent.getInitialDef();
        int res = battleComponent.getInitialRes();

        hpLabel.setText(currentHP + "/" + maxHP);
        atkLabel.setText(atk);
        defLabel.setText(def);
        resLabel.setText(res);

        for(Group group_skill : Group_skill)
        {
            group_skill.setVisible(false);
        }

        for(int i = 0; i < tmp.getSkills().size(); i++)
        {
            Group_skill[i].setVisible(true);
            Label_skillNames[i].setText(tmp.getSkills().get(i).getSkillName());
            Label_skillInfo[i].setText(tmp.getSkills().get(i).getSkillInfo());
            Label_skillCost[i].setText(tmp.getSkills().get(i).getApCost());
        }
    }


    private void updateImage()
    {
        for(int i = 0; i <= 3; i++)
        {
            Texture Texture_image;
            if(operators[i] != null)
            {
                Texture_image = new Texture(Gdx.files.internal(operators[i].getImagePath()));
            }
            else
            {
                Texture_image = Texture_noInfo;
            }
            Style_OperatorImage[i].up = new TextureRegionDrawable(new TextureRegion(Texture_image));
            String name;
            if(operators[i] == null)
            {
                name = "Null";
            }
            else
            {
                name = operators[i].getOperatorName();
            }
            Label_operatorName[i].setText(name);
        }
    }

    private void getTeamMembers()
    {
        this.operators = TeamManager.getInstance().teamMembers;
    }
}

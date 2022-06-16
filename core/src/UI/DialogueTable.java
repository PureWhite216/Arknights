package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DialogueTable extends StoryUI
{
    private static Texture Texture_table = new Texture(Gdx.files.internal("assets/Images/DialogueTable.png"));
    private Image Image_table;
    private String[] texts;
    private int currentIndex = 0;
    private Label Label_text;
    private Label.LabelStyle labelStyle;
    private Button button;
    private Button.ButtonStyle buttonStyle;

    public DialogueTable(String textPath)
    {
        readTextFile(textPath);
        Texture_table.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        group= new Group();

        Image_table = new Image(Texture_table);
        Image_table.setPosition(960 - Image_table.getWidth() / 2, 140);
        group.addActor(Image_table);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = new Color(0, 0, 0, 0.7f);

        Label_text = new Label(texts[currentIndex], labelStyle);
        Label_text.setPosition(360, 810, Align.topLeft);
        Label_text.setAlignment(Align.topLeft);
        group.addActor(Label_text);

        buttonStyle = new Button.ButtonStyle();
        button = new Button(buttonStyle);
        button.setWidth(Image_table.getWidth());
        button.setHeight(Image_table.getHeight());
        button.setPosition(Image_table.getX(), Image_table.getY());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    if(currentIndex < texts.length - 1)
                    {
                        currentIndex++;
                        Label_text.setAlignment(Align.topLeft);
                        Label_text.setText(texts[currentIndex]);
                    }
            }
        });
        group.addActor(button);

    }

    private void readTextFile(String filePath){
        try {
            File file = new File(filePath);
            StringBuilder tmpStringBuilder = new StringBuilder();
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    tmpStringBuilder.append(lineTxt).append("\n");
                }
                read.close();
                texts = tmpStringBuilder.toString().split("@\n");
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
}

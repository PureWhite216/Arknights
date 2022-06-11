package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import Character.Operator;


public class Panel_AP extends BattleUI
{
    private static final Texture Texture_AP = new Texture(Gdx.files.internal("assets/Images/AP.png"));
    private static final Texture Texture_Energy = new Texture(Gdx.files.internal("assets/Images/Energy.png"));
    private Image apPanel;
    private Image[] energyBoxes = new Image[6];
    private int currentAP;
    private static final int maxAP = 6;


    public Panel_AP()
    {
        super();

        Texture_AP.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        Texture_Energy.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        
        group = new Group();
        group.setVisible(true);
        group.setY(100);
        
        apPanel = new Image(Texture_AP);
        group.addActor(apPanel);

        for(int i = 0; i <= 5; i++)
        {
            energyBoxes[i] = new Image(Texture_Energy);
            energyBoxes[i].setVisible(false);
            group.addActor(energyBoxes[i]);
            energyBoxes[i].setPosition(67 + (Texture_Energy.getWidth()) * i, 7);
        }
    }

    public Image getApPanel()
    {
        return apPanel;
    }

    public void updateAP(int ap)
    {
        currentAP = ap;
        for(int i = 0; i <= 5; i++)
        {
            if(i + 1 <= currentAP)
            {
                energyBoxes[i].setVisible(true);
            }
            else
            {
                energyBoxes[i].setVisible(false);
            }
        }
    }
    
}

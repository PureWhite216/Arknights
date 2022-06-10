package Character;

import java.util.Random;

public abstract class Enemy extends CharacterBase
{
    Random random;
    int tmp;
    public Enemy(float posX, float posY, float scale)
    {
        super(posX, posY, scale);
        random = new Random();
        animationComponent.getSkeleton().setScaleX(-1);
    }

    @Override
    public void die()
    {
        if(!isDied)
        {
            currentLevel.getEnemyButtons()[index].setVisible(false);
        }
        super.die();
    }

    public void chooseTarget()
    {
        tmp = random.nextInt(10);
        int targetIndex;
        if(tmp >= 6)
        {
            targetIndex = 0;
        }
        else if(tmp >= 3)
        {
            targetIndex = 1;
        }
        else if(tmp >= 1)
        {
            targetIndex = 2;
        }
        else
        {
            targetIndex = 3;
        }
        if(currentLevel.getOperators()[targetIndex] == null || currentLevel.getOperators()[targetIndex].isDied())
        {
            chooseTarget();
        }
        else
        {
            target = currentLevel.getOperators()[targetIndex];
        }
    }
}

package Character;

public abstract class Enemy extends CharacterBase
{

    public Enemy(float posX, float posY, float scale)
    {
        super(posX, posY, scale);
        animationComponent.getSkeleton().setScaleX(-1);
    }
}

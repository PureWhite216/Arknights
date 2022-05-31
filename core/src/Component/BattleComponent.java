package Component;

public class BattleComponent
{
    private float health;
//    private float tmpHealth;
//    private float armor;
    private float atk;
    private float speed;

    public BattleComponent(float health, float atk, float speed)
    {
        this.health = health;
        this.atk = atk;
        this.speed = speed;
    }

    public void getDamage(float damage)
    {
        health -= damage;
    }

    public float getHealth()
    {
        return health;
    }

    public float getAtk()
    {
        return atk;
    }

    public float getSpeed()
    {
        return speed;
    }
}

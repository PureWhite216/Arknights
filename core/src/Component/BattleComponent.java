package Component;

import Character.CharacterBase;
import Character.Operator;

public class BattleComponent
{
    private int HP;
    private int maxHP;
//    private float tmpHP;
//    private float armor;
    private int atk;
    private int speed;
    private CharacterBase character;
    private boolean isDied = false;

    public BattleComponent(int maxHP, int atk, int speed, CharacterBase character)
    {
        this.HP = maxHP;
        this.maxHP = maxHP;
        this.atk = atk;
        this.speed = speed;
        this.character = character;
    }

    public void getDamage(int damage)
    {
        HP -= damage;
        character.getHPPanel().updateHP(HP);
        if(HP <= 0)
        {
            isDied = true;
            character.die();
        }
    }

    public boolean isDied()
    {
        return isDied;
    }

    public int getHP()
    {
        return HP;
    }

    public int getMaxHP()
    {
        return maxHP;
    }

    public int getAtk()
    {
        return atk;
    }

    public float getSpeed()
    {
        return speed;
    }
}

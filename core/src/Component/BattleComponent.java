package Component;

import Character.CharacterBase;
import Character.Operator;

public class BattleComponent
{
    private int HP;
    private int maxHP;
    private int def;
    private int res;
    private int atk;
    private int ap;
    private int apReadyToCost;
    private CharacterBase character;
    private boolean isDied = false;


    public int buff_NoDamage = 0; //大于0时角色无法受到伤害，每回合减1
    public float buff_Miss = 0; //[0,1) 闪避率
    public int buff_Dizzy = 0; //眩晕， 大于0时角色无法行动，每回合减1

    public BattleComponent(int maxHP, int atk, int def, int res, CharacterBase character)
    {
        this.HP = maxHP;
        this.maxHP = maxHP;
        this.atk = atk;
        this.def = def;
        this.res = res;
        this.character = character;
        ap = 0;
    }

    //角色受到伤害
    public void getDamage(int damage, DamageType damageType)
    {
        if(Math.random() <= buff_Miss)
        {
            return;
        }
        if(buff_NoDamage > 0)
        {
            buff_NoDamage--;
            return;
        }
        switch(damageType)
        {
            case Physical:
                damage -= def;
                if(damage <= 0) damage = 1;
                break;
            case Magical:
                damage = damage * (100 - res) / 100;
                if(damage <= 0) damage = 1;
                break;
            case Real:
                break;
        }

        HP -= damage;
        character.getHPPanel().updateHP(HP);
        if(HP <= 0)
        {
            isDied = true;
            character.die();
        }
    }

    //角色受到治疗
    public void getHealing(int heal)
    {
        if(!isDied)
        {
            HP += heal;
            character.getHPPanel().updateHP(HP);
        }
    }

    public void costAP(int cost)
    {
        ap -= cost;
    }

    public void apPlusPlus()
    {
        ap++;
        if(ap > 6) ap = 6;
    }

    public void addAP(int t)
    {
        ap += t;
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

    public int getAp()
    {
        return ap;
    }

    public void setAp(int ap)
    {
        this.ap = ap;
    }
}

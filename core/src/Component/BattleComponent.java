package Component;

import Battle.Buff.*;
import Character.CharacterBase;
import Character.Operator;

import java.util.ArrayList;

public class BattleComponent
{
    public int HP;
    public int maxHP;
    private int def;
    private int res;
    private int atk;
    private int ap;
    public int tmpDef;
    public int tmpRes;
    public int tmpAtk;
    private int apReadyToCost;
    private CharacterBase character;
    private boolean isDied = false;


    public int buff_NoDamage = 0; //大于0时角色无法受到伤害，每回合减1
    public int buff_Dizzy = 0; //眩晕， 大于0时角色无法行动，每回合减1


    public float buff_Miss = 0; //[0,1) 闪避率
    public int buff_sharp = 0; //锋利，角色攻击力暂时变为原来的2倍
    public int buff_hard = 0; //坚硬，角色的防御力暂时变为原来的1.5倍
    public int buff_fragile = 0; // 易伤，角色受到的伤害变为原来的2倍

    private ArrayList<BuffBase> buffs = new ArrayList<>();

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

    public void levelInit()
    {
        buffs.clear();
        tmpAtk = atk;
        tmpDef = def;
        tmpRes = res;
        buff_Miss = 0;
        buff_NoDamage = 0;
        ap = 2;
    }


    public void addBuff(BuffBase buff)
    {
       buffs.add(buff);
    }
    
    public void buffUpdate()
    {
        for(BuffBase buff : buffs)
        {
            if(buff.roundUpdate())
            {
                buff.buffDisable(this);
            }
        }
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
            if(HP > maxHP) HP = maxHP;
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
        return tmpAtk;
    }

    public int getDef()
    {
        return tmpDef;
    }

    public int getRes()
    {
        return tmpRes;
    }

    public int getInitialAtk()
    {
        return atk;
    }

    public int getInitialDef()
    {
        return def;
    }

    public int getInitialRes()
    {
        return res;
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

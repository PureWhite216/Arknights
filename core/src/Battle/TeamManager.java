package Battle;

import Character.*;

import java.util.Random;

public class TeamManager
{
    private static final TeamManager instance = new TeamManager();

    public static final int operatorNum = 14;

    public Operator[] teamMembers = new Operator[5];

    Random random = new Random();

    private TeamManager()
    {
        initTeam();
    }

    private void initTeam()
    {
        for(int i = 0; i <= 2; i++)
        {
            do
            {
                teamMembers[i] = getOperator(random.nextInt(operatorNum));
            }
            while(teamMembers[i] == null);

        }
//        teamMembers[0] = new Saria(0, 0);
//        teamMembers[1] = new SilverAsh(0, 0);
//        teamMembers[2] = new Franka(0, 0);
//        teamMembers[3] = new Myrtle(0, 0);
    }

    public void createNewOperator()
    {
        teamMembers[4] = null;
        while(teamMembers[4] == null)
        {
            teamMembers[4] = getOperator(random.nextInt(operatorNum));
        }
    }
    
    public Operator getOperator(int index)
    {
        switch(index)
        {
            case 0:
                if(!Amiya.isCreated)
                {
                    Amiya.isCreated = true;
                    return new Amiya(0, 0);
                }
                break;
            case 1:
                if(!Franka.isCreated)
                {
                    Franka.isCreated = true;
                    return new Franka(0, 0);
                }
                break;
            case 2:
                if(!Jessica.isCreated)
                {
                    Jessica.isCreated = true;
                    return new Jessica(0, 0);
                }
                break;
            case 3:
                if(!Liskarm.isCreated)
                {
                    Liskarm.isCreated = true;
                    return new Liskarm(0, 0);
                }
                break;
            case 4:
                if(!Myrtle.isCreated)
                {
                    Myrtle.isCreated = true;
                    return new Myrtle(0, 0);
                }
                break;
            case 5:
                if(!Nightmare.isCreated)
                {
                    Nightmare.isCreated = true;
                    return new Nightmare(0, 0);
                }
                break;
            case 6:
                if(!Platnm.isCreated)
                {
                    Platnm.isCreated = true;
                    return new Platnm(0, 0);
                }
                break;
            case 7:
                if(!Saria.isCreated)
                {
                    Saria.isCreated = true;
                    return new Saria(0, 0);
                }
                break;
            case 8:
                if(!SilverAsh.isCreated)
                {
                    SilverAsh.isCreated = true;
                    return new SilverAsh(0, 0);
                }
                break;
            case 9:
                if(!Surtr.isCreated)
                {
                    Surtr.isCreated = true;
                    return new Surtr(0, 0);
                }
                break;
            case 10:
                if(!Texas.isCreated)
                {
                    Texas.isCreated = true;
                    return new Texas(0, 0);
                }
                break;
            case 11:
                if(!Warfarin.isCreated)
                {
                    Warfarin.isCreated = true;
                    return new Warfarin(0, 0);
                }
                break;
            case 12:
                if(!Skadi.isCreated)
                {
                    Skadi.isCreated = true;
                    return new Skadi(0, 0);
                }
                break;
            case 13:
                if(!Iris.isCreated)
                {
                    Iris.isCreated = true;
                    return new Iris(0, 0);
                }
                break;
        }
        return null;
    }

    public static TeamManager getInstance()
    {
        return instance;
    }
    
}

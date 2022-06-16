package Battle;

import Character.*;

public class TeamManager
{
    private static final TeamManager instance = new TeamManager();

    public Operator[] teamMembers = new Operator[4];

    private TeamManager()
    {
        initTeam();
    }

    private void initTeam()
    {
        teamMembers[0] = new Liskarm(0, 0);
        teamMembers[1] = new Amiya(0, 0);
    }

    public static TeamManager getInstance()
    {
        return instance;
    }
}

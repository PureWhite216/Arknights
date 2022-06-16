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
        teamMembers[0] = new Amiya(0, 0);
        teamMembers[1] = new Surtr(0, 0);
        teamMembers[2] = new Warfarin(0, 0);
        teamMembers[3] = new Nightmare(0, 0);
    }

    public static TeamManager getInstance()
    {
        return instance;
    }
}

package Character;

public class OperatorManager
{
    private static OperatorManager instance = new OperatorManager();
    private Operator[] operatorsInTeam = new Operator[4];
    private Operator[] operators = new Operator[50];
    private int operatorNum = 0;

    public static OperatorManager getInstance()
    {
        return instance;
    }

    public void addOperator(Operator operator)
    {
        operators[operatorNum] = operator;
        operatorNum++;
    }

    public void changeOperatorInTeam(int index, Operator operator)
    {
        operatorsInTeam[index] = operator;
    }
}

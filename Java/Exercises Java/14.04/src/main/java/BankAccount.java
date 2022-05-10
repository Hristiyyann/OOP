public class BankAccount
{
    protected float total_money;

    public boolean deposit(float money_to_add)
    {
        if(money_to_add <= 0)
        {
            System.out.println("You can't deposit negative number or 0!");
            return false;
        }

        total_money += money_to_add;
        return true;
    }

    public boolean withdraw(float money_to_take)
    {
        if(money_to_take > total_money)
        {
            System.out.println("You can't withdraw more money than in the account!");
            return false;
        }

        total_money -= money_to_take;
        return true;
    }

    public float getTotal_money()
    {
        return total_money;
    }
}

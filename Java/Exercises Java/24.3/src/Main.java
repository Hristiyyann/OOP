public class Main
{
    public static void main(String[] args)
    {
        try
        {
            ReadingData rd = new ReadingData();
            User new_user = rd.getUser();
            ReadingDataFromFile rdf = new ReadingDataFromFile();
            User new_user2 = rdf.getUser();

            System.out.println("User 1");
            System.out.println(new_user.name + " -- "  + new_user.password + " -- " + new_user.age);
            System.out.println("User 2");
            System.out.println(new_user2.name + " -- "  + new_user2.password + " -- " + new_user2.age);
        }
        catch (Exception error)
        {
            System.out.println(error.getMessage());
        }
    }
}

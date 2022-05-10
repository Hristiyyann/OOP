import java.util.Scanner;

public class ReadingData
{
    public User getUser() throws UserCreateException {
        Scanner data = new Scanner(System.in);
        String user_name = data.nextLine();
        String user_password = data.nextLine();
        int user_age = data.nextInt();

        try {
            return new User(user_name, user_password, user_age);
        } catch (IllegalArgumentException error) {
            throw new UserCreateException("Non valid arguments");
        }
    }
}

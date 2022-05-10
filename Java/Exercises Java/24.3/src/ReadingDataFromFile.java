import java.io.File;
import java.util.Scanner;

public class ReadingDataFromFile
{
    public User getUser() throws Exception {
        File data = new File("file.txt");
        Scanner from_file = new Scanner(data);
        String user_name = from_file.nextLine();
        String user_password = from_file.nextLine();
        int user_age = from_file.nextInt();


        try {
            return new User(user_name, user_password, user_age);
        } catch (IllegalArgumentException error) {
            throw new UserCreateException("Non valid arguments");
        }
    }
}

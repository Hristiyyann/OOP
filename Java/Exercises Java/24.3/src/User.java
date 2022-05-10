public class User
{
    protected String name;
    protected String password;
    protected int age;

    public User(String name, String password, int age) throws UserCreateException
    {
            if (name.length() < 4) {
                throw new IllegalArgumentException();
            } else {
                this.name = name;
            }

            if (password.length() < 8) {
                throw new IllegalArgumentException();
            } else {
                this.password = password;
            }

            if (age < 10 || age > 150) {
                throw new IllegalArgumentException();
            } else {
                this.age = age;
            }
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public int getAge()
    {
        return age;
    }
}

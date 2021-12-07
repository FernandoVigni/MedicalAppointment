import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginManager
{
    Scanner scanner = new Scanner(System.in);


    public boolean userCheckLogin(String user, String password) throws FileNotFoundException
    {
        String userDB;
        String passwordDB;

        File fileUsers = new File("C:\\Users\\fer90\\Desktop\\TurneroTablas\\UsersLogin.txt");
        Scanner scanner = new Scanner(fileUsers);

        String line = "";
        line = scanner.nextLine();
        while (scanner.hasNextLine())
        {
            line = scanner.nextLine();
            String[] extract = line.split(",");
            userDB = extract[0];
            passwordDB = extract[1];

            if(user.equals(userDB) && password.equals(passwordDB))
                return true;
        }
        return false;
    }

    public String insertUser()
    {
        System.out.println("Please enter your username ");
        return scanner.next();
    }

    public String insertPassword()
    {
        System.out.println("Please enter your password ");
        return  scanner.next();
    }
}

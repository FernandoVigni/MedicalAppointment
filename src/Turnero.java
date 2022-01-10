import javax.print.Doc;
import java.io.*;
import java.util.Scanner;

public class Turnero
{
    Scanner scanner = new Scanner(System.in);
            AppointmentManager appointmentManager = new AppointmentManager();
    LoginManager loginManager = new LoginManager();
    DoctorsManager doctorsManager = new DoctorsManager();
    DoctorSelector doctorSelector = new DoctorSelector();
    Configurations configurations = new Configurations();
    Exit exit = new Exit();
    String user = "";

    public void run() throws IOException
    {
        System.out.println("welcome to the shift system");
        checkToLogin();
    }

    public void checkToLogin() throws IOException
    {
        String user = loginManager.insertUser();
        String password = loginManager.insertPassword();

        if(loginManager.userCheckLogin(user,password))
        {
            enterLoby(user);
        }
        else
        {
            System.out.println("Error when trying to login, please try again");
            checkToLogin();
        }
    }

    public void enterLoby(String user) throws IOException
    {
        int option;
        this.user = user;
        System.out.println("Welcome " + user + " please enter the desired option");
        System.out.println("1.- Doctors");
        System.out.println("2._ Configurations");
        System.out.println("3,_ Exit \n" );

        option = scanner.nextInt();
        choseOption(option);
    }

    public void choseOption(int option) throws IOException
    {
        switch (option)
        {
            case 1:
                optionManager(doctorSelector, doctorsManager);
                enterLoby(user);
                break;
            case 2:
                optionManager(configurations, doctorsManager);
                enterLoby(user);
                break;
            case 3:
                optionManager(exit);
                break;
        }
    }

    public void optionManager(Option option) throws IOException
    {
        option.execute();
    }
    public void optionManager(Option option, DoctorsManager doctorsManager) throws IOException
    {
        option.execute(doctorsManager, appointmentManager);
    }

/*
    // ----------------------------------------------------
    // CODIGO REFACCORIZADO PASADO DE SWITCH A POLIMORFISMO
    // ----------------------------------------------------

    public void lobyOptions() throws IOException
    {
        int option;
        option = scanner.nextInt();

        switch (option)
        {
            case 1:
                choseOption(DoctorSelector);
                enterLoby(user);
                break;

            case 2:
                System.out.println("caso 2 configuracion");
                choseAction();
                enterLoby(user);
                break;

            case 3:
                System.exit(0);
                break;
          }
      }

     public void choseAction() throws IOException
    {
        int option = scanner.nextInt();
        if(option != 0)
            addOrEraseDoctor(option, doctorsManager);
    }



    public void addOrEraseDoctor(int option ,DoctorsManager doctorsManager) throws IOException
    {
        switch (option)
        {
            case 1:
                doctorsManager.addNewDoctor();
            break;
            case 2:
                doctorsManager.eraseDoctor();
            break;
            case 3:
                appointmentManager.refreshAppointmentMasterList();
                enterLoby(user);
            break;
            case 0:
                enterLoby(user);
            break;
        }
      }
*/
}
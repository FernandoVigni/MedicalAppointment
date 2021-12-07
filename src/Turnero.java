import java.io.*;
import java.util.Scanner;

public class Turnero
{
    Scanner scanner = new Scanner(System.in);
    AppointmentManager appointmentManager = new AppointmentManager();
    LoginManager loginManager = new LoginManager();
    DoctorsManager doctorsManager = new DoctorsManager();
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
        this.user = user;
        System.out.println("Welcome " + user + " please enter the desired option");
        System.out.println("1.- Doctors");
        System.out.println("2._ Configurations");
        System.out.println("3,_ Exit \n" );
        lobyOptions();
    }

    public void lobyOptions() throws IOException
    {
        int option;
        option = scanner.nextInt();

        switch (option)
        {
            case 1:
                appointmentManager.getDoctorList();
                appointmentManager.loadDBAppointments();
                appointmentManager.doctorsSelector();
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
        System.out.println("Please chose an action to do: ");
        System.out.println("1.- add new Doctor");
        System.out.println("2.- erase Doctor");
        System.out.println("3.- Refresh Appoitments");
        System.out.println("-------------------");
        System.out.println("0.- Back");

        int option = scanner.nextInt();
        if(option != 0)
            addOrEraseDoctor(option);
    }
    public void addOrEraseDoctor(int option) throws IOException
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

                break;
        }
    }
}
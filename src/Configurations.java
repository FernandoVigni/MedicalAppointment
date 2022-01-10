import java.io.IOException;
import java.util.Scanner;

public class Configurations extends Option
{
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() throws IOException {}

    @Override
    public void execute(DoctorsManager doctorsManager, AppointmentManager appointmentManager) throws IOException {
        System.out.println("caso 2 configuracion");

        System.out.println("Please chose an action to do: ");
        System.out.println("1.- add new Doctor");
        System.out.println("2.- erase Doctor");
        System.out.println("3.- Refresh Appoitments");
        System.out.println("-------------------");
        System.out.println("0.- Back");

        int option = scanner.nextInt();

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
                break;
            case 0:
                break;
        }

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class DoctorsManager
{
    Scanner scanner = new Scanner(System.in);
    String doctorName = "";
    ArrayList doctorMasterList = new ArrayList();
    AppointmentManager appointmentManager = new AppointmentManager();

    public void addNewDoctor() throws IOException
    {
        System.out.println("Please insert the new doctors  - Name - ");
        doctorName = scanner.next();
        addDoctorTxT(doctorName);
    }

    public void addDoctorTxT(String doctor) throws IOException
    {
        FileWriter fileWriter = new FileWriter("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Doctors.txt", true);
        fileWriter.write(doctor);
        fileWriter.write("\r\n");
        fileWriter.close();
    }

    public void getDoctors() throws FileNotFoundException
    {
        File fileDoctors = new File("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Doctors.txt");
        Scanner scannDoctors = new Scanner(fileDoctors);
        String line = "";
        line = scannDoctors.next();
        while (scannDoctors.hasNextLine())
        {
            line = scannDoctors.nextLine();
            doctorMasterList.add(line);
        }
    }

    public void eraseDoctor() throws IOException
    {
        getDoctors();

        System.out.println("Select doctor to erase: ");
        for (int i = 1; i < doctorMasterList.size() ; i++)
            System.out.println (i + ".- " + doctorMasterList.get(i));

        System.out.println("-------------------------");
        System.out.println("0.- Back");

        int option = scanner.nextInt();
        appointmentManager.loadDBAppointments();

        if(option != 0)
        {
            if(option >= 0 && option <= doctorMasterList.size())
            {

                doctorMasterList.remove(option);
                System.out.println("Doctor Removed"); // esto se removio de la master list array

                System.out.println("The doctors authorized at this time to attend are: ");
                for (int i = 0; i < doctorMasterList.size() ; i++)
                {
                    System.out.println(doctorMasterList.get(i));
                }
                reWriteDoctors();
            }
            else
                eraseDoctor();
        }
    }

    public void reWriteDoctors() throws IOException
    {
        deleteTXTDoctors();
        generateEmptyDoctors();

        for (int i = 0; i < doctorMasterList.size(); i++)
        {
            String tempDoctor = doctorMasterList.get(i).toString();
            writeNewDoctorsTxT(tempDoctor);
        }
    }

    public void deleteTXTDoctors() throws IOException
    {
        File fileToDelete = new File("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Doctors.txt");
        fileToDelete.delete();
    }

    public void generateEmptyDoctors() throws IOException
    {
        FileWriter newFile = new FileWriter("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Doctors.txt");
        String Directory = "name";
        newFile.write(Directory);
        newFile.close();
    }

    public void writeNewDoctorsTxT(String doctor) throws IOException
    {
        FileWriter fileWriter = new FileWriter("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Doctors.txt", true);
        fileWriter.write(doctor);
        fileWriter.write("\r\n");
        fileWriter.close();
    }
}

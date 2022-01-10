import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppointmentManager
{
    String workingWithDoctor = "";
    DBManager dBManager = new DBManager();
    Scanner scanner = new Scanner(System.in);

    ArrayList<String> doctorsList = new ArrayList<>();
    ArrayList<Appointment> appointmentMasterList = new ArrayList<>();

    public void loadDBAppointments() throws FileNotFoundException
    {
        appointmentMasterList.clear();
          int size = dBManager.getMasterAppointmentList().size();

        for (int i = 0; i < size ; i++)
        {
            Appointment temporatlAppointmentToAdd = dBManager.appointmentMasterList.get(i);
            appointmentMasterList.add(temporatlAppointmentToAdd);
        }
    }

    public void getDoctorList() throws IOException
    {
        doctorsList.clear();
        doctorsList = dBManager.getDoctorMasterList();
    }

    public void doctorsSelector() throws IOException
    {
        int selectedDoctor = 0;
        System.out.println("please select a doctor to work with ");

        for (int i = 0; i < (doctorsList.size()) ; i++)
        {
            System.out.println((i+1) + ".- " + doctorsList.get(i));
        }
        System.out.println("---------------");
        System.out.println("0.- Back");

        selectedDoctor = scanner.nextInt();

        if(selectedDoctor >= 0 && selectedDoctor <= doctorsList.size())
        {
            if(selectedDoctor != 0)
            {
                workingWithDoctor = doctorsList.get(selectedDoctor-1);
                appointmentOptions();
            }
        }
    }

    public void appointmentOptions() throws IOException
    {
        int option = 0;

        System.out.println("Select Action: ");
        System.out.println("1.- add New Appointment");
        System.out.println("2.- delete Appointment");
        System.out.println("3.- re asign Appointment");
        System.out.println("4.- Checklist");
        System.out.println("------------------------");
        System.out.println("0.- Back");

        option = scanner.nextInt();

        switch (option)
        {
            case 1:
                printSchudles();
                System.out.println("select the hour to schedule");
                checkAppointmentPosibleToAdd(scanner.nextInt(), 1);
                break;
            case 2:
                printSchudles();
                System.out.println("select the appointment to delete");
                checkAppointmentPosibleToErase(scanner.nextInt(), 1);
                break;
            case 3:
                System.out.println("select the appointment to re asignate");
                reassignAppointment();
                break;
            case 4:
                printSchudles();
                doctorsSelector();
                break;
            case 0:
                doctorsSelector();
                break;
        }
    }

    private void printSchudles() throws FileNotFoundException
    {
        System.out.println("Dr. " + workingWithDoctor + "'s appointments scheduled for today are:" );
        getDoctorListOfAppointments(workingWithDoctor);
    }

    public void getDoctorListOfAppointments(String doctor) throws FileNotFoundException
    {
        int appointmentHour = 8;
        ArrayList<Appointment> appointmentListSpesificOfDoctor = new ArrayList<>();
        appointmentListSpesificOfDoctor = dBManager.getDoctorAppointments(doctor);

        for (int i = 0; i <= 8 ; i++)
        {
            boolean available = true;
            for (int j = 0; j < appointmentListSpesificOfDoctor.size(); j++)
            {
                if (appointmentListSpesificOfDoctor.get(j).getHourOfAppointment() == appointmentHour)
                {
                    System.out.println(appointmentHour + "hs its busy // patient name: " + appointmentListSpesificOfDoctor.get(j).getPatientName());
                    appointmentHour++;
                    available = false;
                }
            }
            if (available)
            {
                System.out.println(appointmentHour + "hs Is available");
                appointmentHour++;
            }
        }
        System.out.println("0.- Back ");
    }

    public void checkAppointmentPosibleToAdd(int hour, int method) throws IOException
    {
        ArrayList<Appointment> appointmentListSpesificOfDoctor = new ArrayList<>();
        appointmentListSpesificOfDoctor = dBManager.getDoctorAppointments(workingWithDoctor);

        if(appointmentListSpesificOfDoctor.size() == 9)
        {
            System.out.println("The appointments are all busy");
            System.out.println("please select a new doctor");
            doctorsSelector();
        }

        if(hour >= 8 && hour <= 16)
        {
            for (int i = 0; i < appointmentListSpesificOfDoctor.size(); i++)
            {
                if (hour == appointmentListSpesificOfDoctor.get(i).hourOfAppointment && appointmentListSpesificOfDoctor.get(i).doctor.equals(workingWithDoctor))
                {
                    System.out.println("Error , the schudle is bussy");
                    System.out.println("Please select another option");
                    choseMethod(method);
                }
            }
            newAppointment(hour, workingWithDoctor);
        }
        else
        {
            if(hour == 0)
            {
                doctorsSelector();
            }
            else
            {
                System.out.println("hour out of parameters, please insert another hour");
                int newHour = scanner.nextInt();
                checkAppointmentPosibleToAdd(newHour,method);
            }
        }
    }

    private void newAppointment(int hour, String workingWithDoctor) throws IOException
    {
        Appointment appointment = new Appointment(workingWithDoctor, hour, setPatientName());
        temporalPatientName = "";
        appointmentMasterList.add(appointment);
        System.out.println("se ha agendado la cita");

        String appointmentString = appointment.toString();
        writeTXTAppointment(appointmentString);

        doctorsSelector();
    }

    public String setPatientName()
    {
        if(temporalPatientName != "")
        {
            System.out.println("Se Ha re Agendado la cita de: " + temporalPatientName);
            return temporalPatientName;
        }
        else
        {
            System.out.println("Please insert patient name: ");
            String name = scanner.next();
            return name;
        }
    }

    public void writeTXTAppointment(String appointmentString) throws IOException
    {
        FileWriter fileWriter = new FileWriter("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Appointments.txt", true);
        fileWriter.write(appointmentString);
        fileWriter.write("\r\n");
        fileWriter.close();
    }

    String temporalPatientName = "";
    public void checkAppointmentPosibleToErase(int hour, int method) throws IOException
    {
        loadDBAppointments();

        if(hour >= 8 && hour <= 18)
        {
            for (int i = 0; i < appointmentMasterList.size(); i++)
            {
                if(hour == appointmentMasterList.get(i).hourOfAppointment && appointmentMasterList.get(i).doctor.equals(workingWithDoctor))
                {
                    temporalPatientName = appointmentMasterList.get(i).patientName;
                    masterEraseAppointment(hour);
                    choseMethod(method);
                }
                else
                {
                    if(i == appointmentMasterList.size())
                    {
                        System.out.println("Could not be erased");
                        System.out.println("Please select a doctor: ");
                        System.out.println("");
                    }
                }
            }
        }
        else
        {
            if(hour == 0)
                doctorsSelector();

            System.out.println("hour out of parameters");
            System.out.println("- Please insert a new Hour: ... \r");
            System.out.println("- Press 0 to Back");
            int newHour = scanner.nextInt();

            if(newHour == 0)
                doctorsSelector();

            checkAppointmentPosibleToErase(newHour,method);
        }
    }

    //TODO a futuro rehacer este metodo rancio
    private void choseMethod(int option) throws IOException
    {
        // case 1 : normal mode , doctorSelector menu
        // case 2 : First erase appointment, => add new appointment
        // case 3 : First add appointment, => erase appointment
        switch (option)
        {
            case 1:
                doctorsSelector();
            break;

            case 2:
                getDoctorListOfAppointments(workingWithDoctor);
                System.out.println("insert hour to Schudle: ");
                int hour = scanner.nextInt();
                checkAppointmentPosibleToAdd(hour, 1);
            break;

            case 3:
                getDoctorListOfAppointments(workingWithDoctor);
                System.out.println("insert hour to erase: ");
                int hourB = scanner.nextInt();
                checkAppointmentPosibleToErase(hourB, 1);
            break;
        }
    }

    public void masterEraseAppointment(int hour) throws IOException
    {
        eraseAppointmentInMasterList(hour, workingWithDoctor);
        deletAppointmentseDB();
        generateEmptyAppointmentsDB();
        fillAppointmentsTXT();
        System.out.println("appointment deleted \r");
    }

    public void deletAppointmentseDB() throws IOException
    {
        File fileToDelete = new File("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Appointments.txt");
        fileToDelete.delete();
    }

    public void generateEmptyAppointmentsDB() throws IOException
    {
        FileWriter newFile = new FileWriter("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Appointments.txt");
        String Directory = "doctor,hour,patientName";
        newFile.write(Directory);
        newFile.write("\r\n");
        newFile.close();
    }

    public void fillAppointmentsTXT() throws IOException
    {
        for (int i = 0; i < appointmentMasterList.size() ; i++)
            writeTXTAppointment(appointmentMasterList.get(i).toString());
    }

    private void eraseAppointmentInMasterList(int hour, String doctor) throws IOException
    {
        for (int i = 0; i < appointmentMasterList.size(); i++)
        {
            if (hour == appointmentMasterList.get(i).hourOfAppointment && appointmentMasterList.get(i).doctor.equals(workingWithDoctor))
                appointmentMasterList.remove(i);
        }
    }

    public void reassignAppointment() throws IOException
    {
        getDoctorListOfAppointments(workingWithDoctor);

        System.out.println("Please select hour to erase: ");
        int hour = scanner.nextInt();

        checkAppointmentPosibleToErase(hour, 2);
    }

    public void refreshAppointmentMasterList() throws IOException
    {
        ArrayList <Appointment> newMasterAppointmentList = new ArrayList<>();
        dBManager.doctorMasterList.clear();
        dBManager.doctorMasterList = dBManager.getDoctorMasterList();

        appointmentMasterList = dBManager.getMasterAppointmentList();

        for (int i = 0; i < appointmentMasterList.size(); i++)
        {
            for (int j = 0; j < dBManager.doctorMasterList.size(); j++)
            {
                if(dBManager.doctorMasterList.get(j).equals(appointmentMasterList.get(i).doctor))
                {
                    newMasterAppointmentList.add(appointmentMasterList.get(i));
                break;
                }
            }
        }
        dBManager.doctorMasterList.clear();
        appointmentMasterList = newMasterAppointmentList;
        reWriteAppointmentMasterListTXT();
    }

    public void reWriteAppointmentMasterListTXT() throws IOException
    {
        deletAppointmentseDB();
        generateEmptyAppointmentsDB();

        for (int i = 0; i < appointmentMasterList.size(); i++)
        {
            String toWrite = appointmentMasterList.get(i).toString();
            writeTXTAppointment(toWrite);
        }
    }
}




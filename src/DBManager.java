import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBManager
{
    ArrayList<String> doctorMasterList = new ArrayList<>();
    ArrayList<Appointment> appointmentMasterList = new ArrayList<>();

    public ArrayList<Appointment> getMasterAppointmentList() throws FileNotFoundException
    {
        appointmentMasterList.clear();
        String doctorVariable = "";
        String patientName = "";

        File fileToGetMasterList = new File("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Appointments.txt");
        Scanner scannMasterList = new Scanner(fileToGetMasterList);

        String line = "";
        line = scannMasterList.nextLine();

        while (scannMasterList.hasNextLine())
        {
            line = scannMasterList.nextLine();
            if(line != "")
            {
                String[] extract = line.split(",");
                doctorVariable = extract[0];
                int hour = Integer.parseInt(extract[1]);
                patientName = extract[2];

                Appointment appointment = new Appointment(doctorVariable, hour, patientName);
                appointmentMasterList.add(appointment);
            }
        }
        return appointmentMasterList;
    }

    public ArrayList getDoctorMasterList() throws FileNotFoundException
    {
        File fileDoctors = new File("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Doctors.txt");
        Scanner scannDoctors = new Scanner(fileDoctors);
        String line = "";
        line = scannDoctors.nextLine();
        while (scannDoctors.hasNextLine())
        {
            line = scannDoctors.nextLine();
            doctorMasterList.add(line);
        }
        return doctorMasterList;
    }

    public ArrayList getDoctorAppointments(String doctor) throws FileNotFoundException
    {
        String doctorVariable = "";
        String patientName = "";

        File fileAppointments = new File("C:\\Users\\fer90\\Desktop\\TurneroTablas\\Appointments.txt");
        Scanner scAppointments = new Scanner(fileAppointments);
        ArrayList<Appointment> appointmentList = new ArrayList<>();

        String line = "";
        line = scAppointments.nextLine();

        while (scAppointments.hasNextLine())
        {
            line = scAppointments.nextLine();
            String[] extract = line.split(",");

            doctorVariable = extract[0];
            int hour = Integer.parseInt(extract[1]);
            patientName = extract[2];
            if(doctor.equals(doctorVariable))
            {
                 Appointment appointment = new Appointment(doctorVariable, hour, patientName);
                 appointmentList.add(appointment);
            }
        }
        return appointmentList;
    }
}

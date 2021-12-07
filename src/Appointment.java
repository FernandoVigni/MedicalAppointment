public class Appointment
{
    String doctor;
    String patientName;
    int hourOfAppointment;

    public Appointment(String doctor, int hourOfAppointment, String patientName)
    {
        this.doctor = doctor;
        this.hourOfAppointment = hourOfAppointment;
        this.patientName = patientName;
    }

    @Override
    public String toString()
    {
        return doctor + "," + hourOfAppointment + "," + patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getHourOfAppointment() {
        return hourOfAppointment;
    }
}
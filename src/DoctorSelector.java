import java.io.IOException;

public class DoctorSelector  extends Option
{
    public void execute() throws IOException {}

    @Override
    public void execute(DoctorsManager doctorsManager, AppointmentManager appointmentManager) throws IOException
    {
        appointmentManager.getDoctorList();
        appointmentManager.loadDBAppointments();
        appointmentManager.doctorsSelector();
    }
}

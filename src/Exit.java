import java.io.IOException;

public class Exit  extends Option
{
    @Override
    public void execute() throws IOException
    {
        System.exit(0);
    }

    @Override
    public void execute(DoctorsManager doctorsManager, AppointmentManager appointmentManager) throws IOException {}
}

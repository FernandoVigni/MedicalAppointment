import java.io.IOException;

public abstract class Option
{
    public abstract void execute() throws IOException;

    public abstract void execute(DoctorsManager doctorsManager, AppointmentManager appointmentManager) throws IOException;
}

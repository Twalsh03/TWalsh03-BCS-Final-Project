import java.io.IOException;
import java.net.UnknownHostException;

public interface DeviceScan {

     public abstract void scan() throws IOException, NoSuchMethodException;


     @Override
    public String toString();
}


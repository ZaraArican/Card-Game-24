import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;

/**
 * @author Sevdenur and Chris
 * This class keeps a record of all user activities in a log file. 
 */
public class LogFile {
    public static void writeLog(String callSource, String received) throws IOException {
        try (Formatter output = new Formatter(new FileOutputStream("Logfile.txt", true))
        ) {
            if (callSource.equals("Refresh")) {
                output.format("Player requested four new cards and received " + received + ".\n");
            } else if (callSource.equals("Verify")) {
                output.format("Player submitted expression " + received + " for verification.\n");
            } else if (callSource.equals("Find")) {
                output.format("Player requested a solution.\n");
            } else if (callSource.equals("Manual")) {
                output.format(received);
            }
        }
    }
}
package genius.mohammad.flashinstaller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shell {

    public static String sendShellCommand(String[] cmd) {
        String AllText = "";
        try {
            String line;
            Process process = new ProcessBuilder(cmd).start();
            BufferedReader STDOUT = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader STDERR = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            try {
                process.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
            }
            while ((line = STDERR.readLine()) != null) {
                AllText = AllText + "\n" + line;
            }
            while ((line = STDOUT.readLine()) != null) {
                AllText = AllText + "\n" + line;
                while ((line = STDERR.readLine()) != null) {
                    AllText = AllText + "\n" + line;
                }
            }
            return AllText;
        } catch (IOException ex) {
            return "ERROR";
        }

    }
}

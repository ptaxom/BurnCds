package DiskWriter.Util;

import java.io.IOException;

public class ExecuteCommand {

    public static void Execute(String command){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec( new String[]{"/bin/bash","-c","echo "
                    + System.getenv("PASSWORD") + " |  "+command});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

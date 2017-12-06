package DiskWriter.Devices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Driver {

    private String name = "Not finded";
    private String mountPoint = "Not finded";


    private void initName(){
        Process process = null;
        BufferedReader reader = null;
        try {
            process = Runtime.getRuntime().exec( new String[]{"/bin/bash","-c","echo "
                    + System.getenv("PASSWORD") + " |  lsblk"});
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String parseLine;
            while((parseLine = reader.readLine())!= null) {
                if (parseLine.indexOf("rom") != -1 && parseLine.indexOf("/") != -1)
                {
                    name = parseLine.substring(0, parseLine.indexOf(" "));
                    mountPoint = parseLine.substring(parseLine.indexOf("/"), parseLine.length());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( process != null ) process.destroy();
        }
    }

    public Driver(){
        initName();
    }


    public String getName() {
        return name;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    @Override
    public String toString(){
        return "name: " + name + "\n" +
               "mountPoint: " + mountPoint;
    }

}

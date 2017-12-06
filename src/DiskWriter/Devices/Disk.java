package DiskWriter.Devices;

import DiskWriter.Util.CalcSize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Disk {

    private Driver driver;
    private long size;
    private long usedSize;


    public Disk(Driver driver) {
        this.driver = driver;
        updateUsedSize();
    }


    private void updateUsedSize(){
        Process process = null;
        BufferedReader reader = null;

        try {
            process = Runtime.getRuntime().exec( new String[]{"/bin/bash","-c","echo "
                    + System.getenv("PASSWORD") + " |  df -h"});
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String parseLine;
            while((parseLine = reader.readLine())!= null) {
               // System.out.println(parseLine);
                if (parseLine.indexOf(driver.getMountPoint()) != -1) {
                    usedSize = parseUsedSize(parseLine);
                    size = getSizeFromString(parseLine);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( process != null ) process.destroy();
        }
    }



    private long getSizeFromString(String input){
        String output = "";
        int pos = input.indexOf("G");
        if (pos == -1)
            pos = input.indexOf("M");
        if (pos == -1)
            pos = input.indexOf("K");
        while (input.charAt(pos) != ' ')
            output = input.charAt(pos--) + output;
        return CalcSize.Calculate(output);
    }

    private long parseUsedSize(String input){
        String output = "";
        int lastKey = 0;
        int pos = 0;
        for(int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == ' ' && input.charAt(i - 1) != ' ')
                lastKey++;
            if (lastKey == 3)
            {
                pos = i-1;
                break;
            }
        }
        while (input.charAt(pos) != ' ')
            output = input.charAt(pos--) + output;
        output = output.replace(',','.');
        return CalcSize.Calculate(output);
    }


    @Override
    public String toString() {
        return "Disk{" +
                "driver=" + driver +
                ", size=" + size +
                ", usedSize=" + usedSize +
                '}';
    }


    public long getUsedSize() {
        updateUsedSize();
        return  usedSize;
    }
}

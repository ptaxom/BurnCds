package DiskWriter;

import DiskWriter.Devices.Disk;
import DiskWriter.Devices.Driver;
import DiskWriter.Writer.WriteProcess;

public class Main {

    public static void main(String[] args) {
        Driver driver = new Driver();
//        Disk disk = new Disk(driver);
        WriteProcess.writeDir("/home/ptaxom/OAIP");


    }
}

package DiskWriter.Writer;

import DiskWriter.Devices.Disk;
import DiskWriter.Devices.Driver;
import DiskWriter.Util.ExecuteCommand;

public class WriteProcess {

    private Disk disk;
    private Driver driver;


    public static void writeDir(String dir){
        ExecuteCommand.Execute("cp -R "+dir+" /home/ptaxom/DiskBuffer");
        ExecuteCommand.Execute("umount /dev/sr0");
        ExecuteCommand.Execute("mkisofs -v -J -o Disk.iso /home/ptaxom/DiskBuffer");
        ExecuteCommand.Execute("cdrecord -dev=/dev/sr0 -v -blank=fast");
        ExecuteCommand.Execute("cdrecord -dev=/dev/sr0 -speed=16 -eject -v Disk.iso");
        ExecuteCommand.Execute("rm /home/ptaxom/Disk.iso");

    }


}

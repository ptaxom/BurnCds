package DiskWriter.Util;

public class CalcSize {

    public static long Calculate(String parseString){
        String output = parseString;
        output = output.replace(',','.');
        String k = output.substring(0,output.length()-1);
        k = k.trim();
        System.out.println(k);
        float size = Integer.parseInt(k);
        switch (output.charAt(output.length()-1))
        {
            case 'G' : size*=1000*1000*1000; break;
            case 'M' : size*=1000*1000; break;
            case 'K' : size*=1000; break;
            default: break;
        }
        return (long)size;
    }
}

package Connections;

import java.util.Arrays;

/**
 * Created by kemo on 04/02/2017.
 */
public class IPAddress {
   private short[] ip;
    public IPAddress(String s)
    {
        setIp(s);
    }
    public IPAddress(short[] ip)
    {
        this.ip = ip;
    }

    public short[] getIp() {
        return ip;
    }

    public void setIp(short[] ip) {
        this.ip = ip;
    }
    public void setIp(String ipSTR) {
        String[] ipNos = ipSTR.split("\\.");
        ip = new short[4];
        if (ipNos.length > 4) throw new RuntimeException("invalid ip Address : " + Arrays.toString(ipNos));
        for (int i = 0; i < 4; i++) {
            ip[i] =  Short.valueOf(ipNos[i]);
        }
    }

    @Override
    public String toString() {
        String s = Arrays.toString(ip);
        s = s.replace(",",".");
        s = s.replace(" ", "");
       s= s.substring(1,s.length() - 1);
        return s;
    }
}

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Function: TODO
 *
 * @author Viki
 * @date 2017/11/10 11:35
 */
public class T {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String x = "17:03:03:12:b5:48:25:74:df:11:37:bd:83:ca:71:a9:33:cd:4d:d9:08:88:11:f4:95:31:86:d5:67:de:92:9b:6d:73:9f:cc:e7:d5:40:e6:44:ee:a2:db:54:ea:66:16:11:a7:1e:11:bb:78:29:52:6d:9b:b6:7f:81:67:c7:01:08:db:c6:21:a6:5d:5d:fe:9f:9e:47:b8:06:d2:ae:eb:1d:e1:bc:7b:5c:0f:fb:fa:55:78:24:8c:f6:e6:42:c0:26:b6:a8:75:57:88:dd:94:2a:c1:cd:6c:12:d0:c9:77:33:8b:11:28:da:2d:2b:2d:89:53:73:e7:36:51:96:f9:f1:f0:df:15:a3:ad:25:73:0d:41:06:f1:50:d7:6e:22:0a:be:bb:28:35:99:98:dd:8e:c6:11:11:09:9e:26:86:59:30:da:15:c8:7b:7a:47:1f:8f:cd:93:f5:38:e9:d1:fc:d1:c8:e6:d8:2b:d9:71:8a:56:da:4b:6d:49:e4:00:29:2d:c8:ea:81:ce:d7:aa:0c:48:30:77:cc:24:3f:ca:72:52:8f:f0:9b:5c:bd:be:e3:93:da:4a:20:7c:5a:dc:e7:c7:ef:b3:3e:b8:8e:7b:88:53:85:df:fb:09:1e:79:ec:78:ef:aa:27:b5:36:90:8f:16:6c:5f:fc:8c:62:98:08:57:2e:b1:31:97:71:e0:ee:05:8f:20:da:f6:37:43:fd:ab:36:05:66:0a:27:b0:06:ca:c0:70:57:49:97:0e:d9:eb:82:2c:56:2a:99:6b:2c:68:b9:5d:49:9b:8d:9d:f0:30:9d:cf";
        String[] xarr = x.split(":");
        byte[] data = new byte[xarr.length];

        for(int idx = 0; idx < xarr.length; idx++){
            data[idx] = (byte)(Integer.valueOf(xarr[idx], 16) - 128);
        }
        for(String chars : Charset.availableCharsets().keySet()){
            System.out.println(chars + "\t" + new String(data, chars));
        }

    }
}

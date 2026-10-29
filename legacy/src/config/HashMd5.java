
package config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class HashMd5 {//types of hashes = MD5, SHA-1, SHA-224, SHA-256, SHA-A-224, SHA384, SHA-512
    

   private final char[] HEXADECIMAL = { '0', '1', '2', '3','4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
   final Properties p= new jProp().getProperties();
   String str="";
   
    public final String hash(final char[] chr){
     for (int i = 0; i < chr.length; i++) {
         str=String.valueOf(str+chr[i]);
     }
     str=str.toLowerCase();
        try {
            MessageDigest md = MessageDigest.getInstance(p.getProperty("hash"));
            byte[] bytes = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int low = (int)(bytes[i] & 0x0f);
                int high = (int)((bytes[i] & 0xf0) >> 4);
                sb.append(HEXADECIMAL[high]);
                sb.append(HEXADECIMAL[low]);
            }
           // System.out.print("\n Pasword "+str+" hash "+sb.toString());
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }finally{
            str=null;
        }
    }
}


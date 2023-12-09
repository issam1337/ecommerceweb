
package org.issam.ecommerceweb.utilize;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA {

    public static String encrypt(String passwordToHash) 
    {
        
        passwordToHash = "#Your_"+passwordToHash+"_Cart$";
        
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
       return generatedPassword;
    }
}

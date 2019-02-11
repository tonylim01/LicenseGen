import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.net.util.Base64;

public class LicenseGen {


    private static String encrypt(String inputStr) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String result="";
        Base64 base64EnDe = new Base64();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteValue = md5.digest(inputStr.getBytes());

            result = base64EnDe.encodeToString(byteValue).replaceAll("\r\n", "");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw e;
        }

        System.out.println( "Data : "+result );

        byte [] data = base64EnDe.decode( result );

        return result;
    }

    public static void main( String[] args ) {

        try {

            AES256Util aes256Util = new AES256Util();
            String enc = null;
            if(args[0].equals( "gen" )) {
                String raw = aes256Util.licenseTextRead( "/Users/jongchullim/IdeaProjects/ACS_A2S/MediaLicense.txt" );
                System.out.println( "Raw : " + raw );
                enc = aes256Util.encrypt( raw );
                aes256Util.licenseFileCreate( "/Users/jongchullim/IdeaProjects/ACS_A2S/.medialicensefile.txt", enc );
            }
            else if(args[0].equals( "dec" )) {
                enc = aes256Util.aes256fileread( "/Users/jongchullim/IdeaProjects/ACS_A2S/.medialicensefile.txt" );
                System.out.println( enc );
                String dec = aes256Util.decrypt( enc );
                System.out.println( dec );
                LicenseParsing licenseParsing = new LicenseParsing( );
                HashMap< Integer, ArrayList > licenseCount;
                licenseCount = licenseParsing.Parser( dec );
            }


        } catch ( GeneralSecurityException e ) {
            e.printStackTrace( );
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace( );
        }
    }
}

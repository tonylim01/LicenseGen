import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;

public class LicenseGen {
    public static void main( String[] args ) {
        try {
            AES256Util aes256Util = new AES256Util();
            String enc = null;
            if(args[0].equals( "gen" )) {
                String raw = aes256Util.licenseTextRead( args[1] );
                System.out.println( "Raw : " + raw );
                enc = aes256Util.encrypt( raw );
                aes256Util.licenseFileCreate( args[2], enc );
            }
            else if(args[0].equals( "dec" )) {
                enc = aes256Util.aes256fileread( args[2] );
                System.out.println( enc );
                String dec = aes256Util.decrypt( enc );
                System.out.println( dec );
                LicenseParser licenseParser = new LicenseParser( );
                HashMap< Integer, ArrayList > licenseCount;
                licenseCount = licenseParser.Parser( dec );
            }
        } catch ( GeneralSecurityException e ) {
            e.printStackTrace( );
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace( );
        }
    }
}

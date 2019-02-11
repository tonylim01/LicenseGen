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

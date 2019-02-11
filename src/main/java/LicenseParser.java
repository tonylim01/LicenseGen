import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;

public class LicenseParser {

    private String AMF = "amf0";
    private String AMR = "AMR";
    private String EVS = "EVS";

    private int AMRINT = 0;
    private int EVSINT = 1;

    public HashMap< Integer, ArrayList > Parser( String data)
    {
        JsonParser parser = new JsonParser();

        HashMap< Integer, ArrayList > licenseCount = new HashMap< Integer, ArrayList >();
        for (int i = 0; i < 6; i++) {
            ArrayList codec = new ArrayList();
            String amfName = AMF + i;
            JsonElement element = parser.parse(data);
            String name = element.getAsJsonObject().get(amfName).toString();
            JsonElement element2 = parser.parse(name);
            int amr = element2.getAsJsonObject().get(AMR).getAsInt();
            int evs = element2.getAsJsonObject().get(EVS).getAsInt();

            System.out.println("name = "+name + " : amr : "+amr+" : evs : "+evs);
            codec.add(AMRINT,amr);
            codec.add(EVSINT,evs);
            licenseCount.put(i, codec);
        }
        return licenseCount;

    }
}

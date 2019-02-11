import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;

public class LicenseParsing {

    public HashMap< Integer, ArrayList > Parser( String data)
    {
        JsonParser parser = new JsonParser();

        HashMap< Integer, ArrayList > licenseCount = new HashMap< Integer, ArrayList >();
        for (int i = 0; i < 6; i++) {
            ArrayList codec = new ArrayList();
            String amfName = "amf0"+i;
            JsonElement element = parser.parse(data);
            String name = element.getAsJsonObject().get(amfName).toString();
            JsonElement element2 = parser.parse(name);
            Integer amr = element2.getAsJsonObject().get("AMR").getAsInt();
            Integer evs = element2.getAsJsonObject().get("EVS").getAsInt();

            System.out.println("name = "+name + " : amr : "+amr+" : evs : "+evs);
            codec.add(0,amr);
            codec.add(1,evs);
            licenseCount.put(i, codec);
        }
        return licenseCount;

    }
}

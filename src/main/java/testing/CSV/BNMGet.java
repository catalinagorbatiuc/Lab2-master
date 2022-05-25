package testing.CSV;

import com.thoughtworks.xstream.XStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BNMGet {
    public static ValCurs sendGet(String data) throws Exception {

        HttpClient client = new DefaultHttpClient();

        String url = "http://bnm.md/en/official_exchange_rates?get_xml=1&date=" + data;
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        System.out.println("\nRequesting to URL : " + url);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
        XStream xstream = new XStream();
        xstream.processAnnotations(ValCurs.class);
        xstream.processAnnotations(Valute.class);
        xstream.allowTypes(new Class[]{ValCurs.class, Valute.class});

        ValCurs valCurs = (ValCurs) xstream.fromXML(rd);

        for (Valute valute : valCurs.getList()) {
            System.out.println(valute.toString());
            System.out.println();
        }
        return valCurs;
    }

}

package ligualeo;

import com.jayway.jsonpath.JsonPath;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

public class LinguaLeoService {
    private final HttpClient httpClient;

    public LinguaLeoService() {
        httpClient = HttpClient.newBuilder().build();
    }

    public List<String> getTranslations(String engWord) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://api.lingualeo.com/gettranslates?port=1001"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyProcessor.fromString("word=" + URLEncoder.encode(engWord, "UTF-8")))
                    .build();

            String body = httpClient.send(request, HttpResponse.BodyHandler.asString()).body();

            return JsonPath.parse(body).read("$.translate.*.value", List.class);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

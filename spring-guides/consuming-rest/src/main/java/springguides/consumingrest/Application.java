package springguides.consumingrest;

import org.springframework.web.client.RestTemplate;
import springguides.consumingrest.model.Page;

/**
 * Created by mengf on 1/19/2015.
 */
public class Application {
    private static final String url = "http://graph.facebook.com/pivotalsoftware";
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        Page page = restTemplate.getForObject(url, Page.class);

        System.out.println(page);
    }
}

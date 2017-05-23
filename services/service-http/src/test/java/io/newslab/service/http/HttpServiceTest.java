package io.newslab.service.http;

import io.newslab.service.http.impl.HttpRequestImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { TestConfig.class })
public class HttpServiceTest {

    @Autowired
    HttpService httpService;

    @Test
    public void test() throws Exception {
        HttpRequest httpRequest = new HttpRequestImpl("http://nypost.com/feed");
        httpRequest.setAccept("text/html,application/xhtml+xml,application/xml");


        HttpResponse httpResponse = httpService.execute(httpRequest);

        //new InputStreamReader(httpResponse.getStream()); .class //  .read(httpResponse.getStream());

        String result = new BufferedReader(new InputStreamReader(httpResponse.getStream())).lines()
                .parallel().collect(Collectors.joining("\n"));

        httpResponse.releaseConnection();

        System.out.print(result);
    }
}
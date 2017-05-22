package io.newslab.worker.dispatcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RssCrawlerDispatcher.class)
public class RssCrawlerDispatcherTest {

    @MockBean
    RssCrawlerDispatcher rssCrawlerDispatcher;

    @Test
    public void dispatchRssCrawl() throws Exception {

//        given(rssCrawlerDispatcher.dispatchRssCrawl()).will
    }

}
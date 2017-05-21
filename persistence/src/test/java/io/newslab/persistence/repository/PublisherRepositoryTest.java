package io.newslab.persistence.repository;

import io.newslab.persistence.TestConfig;
import io.newslab.persistence.model.Publisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { TestConfig.class })
@Transactional @Rollback
public class PublisherRepositoryTest {

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void findByCanonicalName() throws Exception {
        Assert.assertTrue(publisherRepository.findByCanonicalName("test-publisher.com").isPresent());
        Assert.assertFalse(publisherRepository.findByCanonicalName("non-existent.com").isPresent());
    }

}
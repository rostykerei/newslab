package io.newslab.persistence.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class KeyGeneratorTest {

    @Test
    public void generateKey() throws Exception {
        Assert.assertEquals(1000,
                Stream.generate(KeyGenerator::generateKey).limit(1000).distinct().count()
        );

        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(11, KeyGenerator.generateKey().length());
        }
    }

}
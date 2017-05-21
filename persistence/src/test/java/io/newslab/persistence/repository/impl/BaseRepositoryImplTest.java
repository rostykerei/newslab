package io.newslab.persistence.repository.impl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BaseRepositoryImplTest {

    @Test
    public void testFirstResult() throws Exception {
        Assert.assertFalse(BaseRepositoryImpl.firstResult(null).isPresent());
        Assert.assertFalse(BaseRepositoryImpl.firstResult(new ArrayList()).isPresent());

        ArrayList<Object> list = new ArrayList<>();

        Object obj1 = new Object();
        Object obj2 = new Object();

        list.add(obj1);
        list.add(obj2);

        Assert.assertTrue(BaseRepositoryImpl.firstResult(list).isPresent());

        Assert.assertEquals(obj1, BaseRepositoryImpl.firstResult(list).get());
    }
}
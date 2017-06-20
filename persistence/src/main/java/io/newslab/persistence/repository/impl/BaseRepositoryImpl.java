package io.newslab.persistence.repository.impl;

import io.newslab.persistence.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    EntityManager em;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    static <T> Optional<T> firstResult(List<T> list) {
        return (list == null || list.isEmpty()) ? Optional.empty() : Optional.of(list.get(0));
    }
}

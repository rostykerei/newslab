package io.newslab.persistence.repository.impl;

import io.newslab.persistence.model.Publisher;
import io.newslab.persistence.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class PublisherRepositoryImpl extends BaseRepositoryImpl<Publisher, Integer> implements PublisherRepository {

    @Autowired
    public PublisherRepositoryImpl(EntityManager em) {
        super(Publisher.class, em);
    }

    @Transactional(readOnly = true)
    public Optional<Publisher> findByCanonicalName(String cn) {
        return firstResult(
                em.createQuery("select p from Publisher p where p.canonicalName = :cn", Publisher.class)
                        .setParameter("cn", cn)
                        .setMaxResults(1)
                        .getResultList()
        );
    }
}
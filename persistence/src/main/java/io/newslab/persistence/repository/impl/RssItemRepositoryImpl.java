package io.newslab.persistence.repository.impl;

import io.newslab.persistence.model.Publisher;
import io.newslab.persistence.model.RssItem;
import io.newslab.persistence.repository.RssItemRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

public class RssItemRepositoryImpl  extends BaseRepositoryImpl<RssItem, Integer> implements RssItemRepository {

    public RssItemRepositoryImpl(EntityManager em) {
        super(RssItem.class, em);
    }

    @Override
    @Transactional
    public Optional<RssItem> findByPublisherAndGuid(Publisher publisher, String guid) {
        return firstResult(
                em.createQuery("select i from RssItem i " +
                        "left join fetch i.publisher " +
                        "left join fetch i.rssFeed " +
                        "where i.publisher.id = :publisherId " +
                        "and i.guidHash = :guidHash", RssItem.class)
                        .setParameter("publisherId", publisher.getId())
                        .setParameter("guidHash", DigestUtils.sha1(guid))
                        .setMaxResults(1)
                        .getResultList()
        );
    }
}

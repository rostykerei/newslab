package io.newslab.persistence.repository.impl;

import io.newslab.persistence.model.RssFeed;
import io.newslab.persistence.repository.RssFeedRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class RssFeedRepositoryImpl extends BaseRepositoryImpl<RssFeed, Integer> implements RssFeedRepository {

    public RssFeedRepositoryImpl(EntityManager em) {
        super(RssFeed.class, em);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RssFeed> findByUrl(String url) {
        return firstResult(
                em.createQuery("select f from RssFeed f " +
                        "left join fetch f.publisher " +
                        "where f.url = :url", RssFeed.class)
                .setParameter("url", url)
                .setMaxResults(1)
                .getResultList()
        );
    }

    @Override
    public Optional<RssFeed> pollFeedToProcess() {
        return firstResult(pollFeedsToProcess(1));
    }

    @Override
    @Transactional
    public List<RssFeed> pollFeedsToProcess(int num) {
        Date now = new Date();

        List<RssFeed> feeds = em.createQuery("select f from RssFeed f " +
                "where f.inProgressSince is null " +
                "and f.plannedCheck is null or f.plannedCheck <= :now " +
                "order by f.plannedCheck asc", RssFeed.class)
                .setParameter("now", now)
                .setMaxResults(num)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();

        if (feeds.isEmpty()) {
            return feeds;
        }

        List<Integer> ids = new ArrayList<>();

        for (RssFeed feed : feeds) {
            feed.setInProgressSince(now);
            ids.add(feed.getId());
        }

        em.createQuery("update RssFeed f set f.inProgressSince = :now " +
                "where f.id in (:ids)")
                .setParameter("now", now)
                .setParameter("ids", ids)
                .executeUpdate();

        return feeds;
    }

    @Override
    @Transactional
    public void releaseFeedInProgress(RssFeed feed) {
        feed.setInProgressSince(null);
        feed.setLastCheck(new Date());

        save(feed);
    }

    @Override
    @Transactional
    public int resetIdleFeeds(long busyMilliseconds) {
        return em.createQuery("update RssFeed set inProgressSince = null " +
                "where inProgressSince <= :dt")
                .setParameter("dt", new Date( System.currentTimeMillis() - busyMilliseconds ))
                .executeUpdate();
    }
}

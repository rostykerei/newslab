package io.newslab.persistence.repository;

import io.newslab.persistence.model.RssFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RssFeedRepository extends JpaRepository<RssFeed, Integer> {

    Optional<RssFeed> findByUrl(String url);

    Optional<RssFeed> pollFeedToProcess();

    List<RssFeed> pollFeedsToProcess(int num);

    void releaseFeedInProgress(RssFeed feed);

    int resetIdleFeeds(long busyMilliseconds);

}

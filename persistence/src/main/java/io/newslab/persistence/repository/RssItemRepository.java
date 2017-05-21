package io.newslab.persistence.repository;

import io.newslab.persistence.model.Publisher;
import io.newslab.persistence.model.RssItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RssItemRepository extends JpaRepository<RssItem, Integer> {

    Optional<RssItem> findByPublisherAndGuid(Publisher publisher, String guid);

}

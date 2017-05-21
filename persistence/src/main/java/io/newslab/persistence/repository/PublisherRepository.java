package io.newslab.persistence.repository;

import io.newslab.persistence.model.Publisher;

import java.util.Optional;

public interface PublisherRepository extends BaseRepository<Publisher, Integer> {

    Optional<Publisher> findByCanonicalName(String cn);

}

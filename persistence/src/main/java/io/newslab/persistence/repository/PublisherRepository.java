package io.newslab.persistence.repository;

import io.newslab.persistence.model.Publisher;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Integer> {

    Optional<Publisher> findByCanonicalName(String cn);

}

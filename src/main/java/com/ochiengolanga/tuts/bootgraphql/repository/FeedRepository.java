package com.ochiengolanga.tuts.bootgraphql.repository;

import com.ochiengolanga.tuts.bootgraphql.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}

package com.ochiengolanga.tuts.bootgraphql.repository;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
//import com.ochiengolanga.tuts.bootgraphql.naturalid.NaturalRepository;
import org.springframework.stereotype.Repository;

/*
@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
}
*/

@Repository
public interface FeedRepository extends PagingAndSortingRepository<Feed, Long> {
}


/*
@Repository
public interface FeedRepository<T, ID> extends NaturalRepository<Feed, Long> {
}
*/
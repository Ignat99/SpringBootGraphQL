package com.ochiengolanga.tuts.bootgraphql.repository;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends PagingAndSortingRepository<Feed, Long>, QueryByExampleExecutor<Feed> {
}

package com.ochiengolanga.tuts.bootgraphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.service.NosFeedService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

//@Component
public class FeedQuery implements GraphQLQueryResolver {

    @Autowired
    private NosFeedService feedService;

    public Iterable<Feed> getAllFeeds(final Integer pageNo, final Integer pageSize, final String sortBy) {
        return this.feedService.getAllFeedsService(pageNo, pageSize, sortBy);
    }

    public List<Feed> getFeeds(final Integer pageNo, final Integer pageSize, final String sortBy, final int count) {
        return this.feedService.getFeedsService(pageNo, pageSize, sortBy, count);
    }

    public Optional<Feed> getFeed(final int id) {
        return this.feedService.getFeed(id);
    }

    public long countFeeds() {
        return this.feedService.countFeeds();
    }
}

package com.ochiengolanga.tuts.bootgraphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class FeedQuery implements GraphQLQueryResolver {

    @Autowired
    private FeedService feedService;

    public Iterable<Feed> getFindAllFeeds(final Integer pageNo, final Integer pageSize, final String sortBy) {
        return this.feedService.getAllFeeds(pageNo, pageSize, sortBy);
    }

    public List<Feed> getFeeds(final Integer pageNo, final Integer pageSize, final String sortBy, final int count) {
        return this.feedService.getAllFeed(pageNo, pageSize, sortBy, count);
    }

    public Optional<Feed> getFeed(final int id) {
        return this.feedService.getFeed(id);
    }

    public long countFeeds() {
        return this.feedService.countFeeds();
    }
}
package com.ochiengolanga.tuts.bootgraphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.ochiengolanga.tuts.bootgraphql.domain.Feed;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;

import java.util.Optional;

public class FeedResolver implements GraphQLResolver<Feed> {

    private final String NAME_FEEDRESOLVER = "FeedResolver";
    private final FeedRepository feedRepository;

    public FeedResolver(FeedRepository feedRepository) {
        String methodName = NAME_FEEDRESOLVER;
        this.feedRepository = feedRepository;
    }

    public Optional<Feed> getOne(Feed feed) {
        return feedRepository.findById(1L);
    }
}
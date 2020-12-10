package com.ochiengolanga.tuts.bootgraphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import com.ochiengolanga.tuts.bootgraphql.domain.Feed;
import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;

import java.util.Optional;

public class Query implements GraphQLQueryResolver {

    private final FeedRepository feedRepository;
    private final AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, FeedRepository feedRepository) {
        this.authorRepository = authorRepository;
        this.feedRepository = feedRepository;
    }

    public Iterable<Feed> findAllFeeds() {
        return feedRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countFeeds() {
        return feedRepository.count();
    }

}
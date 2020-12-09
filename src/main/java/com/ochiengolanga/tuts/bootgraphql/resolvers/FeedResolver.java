package com.ochiengolanga.tuts.bootgraphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
//import com.ochiengolanga.tuts.bootgraphql.domain.Author;
//import com.ochiengolanga.tuts.bootgraphql.domain.Book;
import com.ochiengolanga.tuts.bootgraphql.domain.Feed;
//import com.ochiengolanga.tuts.bootgraphql.domain.dto.Joke;
//import com.ochiengolanga.tuts.bootgraphql.domain.dto.JokeWrapper;
//import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
//import com.ochiengolanga.tuts.bootgraphql.utils.feign.JokesAPIService;

import java.util.Optional;

public class FeedResolver implements GraphQLResolver<Feed> {

//    private final AuthorRepository authorRepository;
//    private final JokesAPIService jokesAPIService;

//    public FeedResolver(AuthorRepository authorRepository, JokesAPIService jokesAPIService) {
//        this.authorRepository = authorRepository;
//        this.jokesAPIService = jokesAPIService;
//    }

//    public Optional<Joke> getJoke(Feed feed) {
//        return jokesAPIService.getJoke(feed.getId()).map(JokeWrapper::getValue);
//    }

//    public Optional<Author> getAuthor(Feed feed) {
//        return authorRepository.findById(feed.getAuthor().getId());
//    }
}
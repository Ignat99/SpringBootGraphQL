package com.ochiengolanga.tuts.bootgraphql;

import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import com.ochiengolanga.tuts.bootgraphql.domain.Feed;
//import com.ochiengolanga.tuts.bootgraphql.exception.GraphQLErrorAdapter;

import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
/*
import com.ochiengolanga.tuts.bootgraphql.resolvers.BookResolver;
import com.ochiengolanga.tuts.bootgraphql.resolvers.Query;
import com.ochiengolanga.tuts.bootgraphql.utils.feign.JokesAPIService;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
*/
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;
*/
//import java.util.ArrayList;
import java.util.List;

import java.net.URL;
import java.net.MalformedURLException;
import com.icosillion.podengine.exceptions.InvalidFeedException;
import com.icosillion.podengine.exceptions.MalformedFeedException;
import com.icosillion.podengine.models.Podcast;
import com.icosillion.podengine.models.Episode;



@Component
public class ScheduledTasks {


    @Scheduled(initialDelay=10000, fixedRate = 30000)
    public void checkRSS() {

//      public void checkRSS_repo(AuthorRepository authorRepository, BookRepository bookRepository) {

//    @Autowired
//        AuthorRepository authorRepository = new AutorRepository();

        Author author = new Author("Ignat", "Ignatov");
//        authorRepository.save(author);

//    @Autowired
//        FeedRepository feedRepository = new FeedRepository();


            System.out.printf(" - Time to cheсk \n");
        try{
            URL myUrl = new URL("http://feeds.nos.nl/nosjournaal?format=xml");
            Podcast podcast = new Podcast(myUrl);
            //Display Feed Details
            List<Episode> episodes = podcast.getEpisodes();
            System.out.printf(" Time %s has %d episodes!\n", podcast.getTitle(), episodes.size());
        }catch(InvalidFeedException | MalformedFeedException | MalformedURLException ex){
            System.out.println("The url is not well formed: " + ex);
            System.out.println("Or the Feed is not well formed: " + ex);
            System.out.println("Or the channel is not well formed: " + ex);
        }

/*
            for (Episode episode: episodes) {
                bookRepository.save(new Book(
                    episode.getTitle(),
//                    episode.getDescription(),
                    "0071809251",
                    100,
                    author));
//                sleep(300000);
          }
*/
//      } //checkRSS_repo
    } //checkRSS
} //ScheduledTasks
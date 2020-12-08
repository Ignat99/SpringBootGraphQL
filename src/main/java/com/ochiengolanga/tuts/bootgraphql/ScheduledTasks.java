package com.ochiengolanga.tuts.bootgraphql;

/*
import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import com.ochiengolanga.tuts.bootgraphql.domain.Book;
import com.ochiengolanga.tuts.bootgraphql.exception.GraphQLErrorAdapter;
import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.repository.BookRepository;
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
import org.springframework.stereotype.Component;

/*
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
*/

import java.net.URL;
import com.icosillion.podengine.models.Podcast;
import com.icosillion.podengine.models.Episode;



@Component
public class ScheduledTasks {

//    public void checkRSS(AuthorRepository authorRepository, BookRepository bookRepository) {
    @Scheduled(fixedRate = 3000)
    public void checkRSS() {

//            Author author = new Author("Ignat", "Ignatov");
//            authorRepository.save(author);

            System.out.printf(" - Time to cheсk \n");

//            Podcast podcast = new Podcast(new URL("http://feeds.nos.nl/nosjournaal?format=xml"));
/*
            //Display Feed Details
            List<Episode> episodes = podcast.getEpisodes();
            System.out.printf(" Time %s has %d episodes!\n", podcast.getTitle(), episodes.size());

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
    }
}
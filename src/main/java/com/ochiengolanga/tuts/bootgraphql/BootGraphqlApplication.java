package com.ochiengolanga.tuts.bootgraphql;

import com.ochiengolanga.tuts.bootgraphql.domain.Author;
import com.ochiengolanga.tuts.bootgraphql.domain.Feed;
import com.ochiengolanga.tuts.bootgraphql.exception.GraphQLErrorAdapter;
import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import com.ochiengolanga.tuts.bootgraphql.resolvers.FeedResolver;
import com.ochiengolanga.tuts.bootgraphql.resolvers.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.net.URL;
import com.icosillion.podengine.models.Podcast;
import com.icosillion.podengine.models.Episode;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class BootGraphqlApplication {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        SpringApplication.run(BootGraphqlApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public Query query(AuthorRepository authorRepository, FeedRepository feedRepository) {
        return new Query(authorRepository, feedRepository);
    }

    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository, FeedRepository feedRepository) {
        return (args) -> {
            //Download and parse the nos.nl RSS feed http://feeds.nos.nl/nosjournaal?format=xml
            Podcast podcast = new Podcast(new URL("http://feeds.nos.nl/nosjournaal?format=xml"));

            //Display Feed Details
            List<Episode> episodes = podcast.getEpisodes();
            System.out.printf(" %s has %d episodes!\n", podcast.getTitle(), episodes.size());

            //List all episodes
            for (Episode episode: episodes) {
                System.out.println("- " + episode.getTitle());
                feedRepository.save(
                  new Feed(
                    episode.getTitle(),
                    episode.getDescription(),
                    20,
                    episode.getPubDate().toString(),
                    episode.getEnclosure().getURL().toString()
                  )
                );
            }

        };
    }
}
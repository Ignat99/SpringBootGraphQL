package com.ochiengolanga.tuts.bootgraphql.configurations;

import com.icosillion.podengine.models.Episode;
import com.icosillion.podengine.models.Podcast;
import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.exception.GraphQLErrorAdapter;
import com.ochiengolanga.tuts.bootgraphql.repository.AuthorRepository;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import com.ochiengolanga.tuts.bootgraphql.resolvers.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class EntityManagerFactoryConfiguration {

  public static final String S_HAS_D_EPISODES = " %s has %d episodes!\n";
  public static final String HTTP_FEEDS_NOS_NL_NOSJOURNAAL_FORMAT_XML =
      "http://feeds.nos.nl/nosjournaal?format=xml";
  private final EntityManagerFactory entityManagerFactory;

  public EntityManagerFactoryConfiguration(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
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
        List<GraphQLError> clientErrors =
            errors.stream().filter(this::isClientError).collect(Collectors.toList());

        List<GraphQLError> serverErrors =
            errors.stream()
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
      // Download and parse the nos.nl RSS feed http://feeds.nos.nl/nosjournaal?format=xml
      Podcast podcast = new Podcast(new URL(HTTP_FEEDS_NOS_NL_NOSJOURNAAL_FORMAT_XML));

      // Display Feed Details
      List<Episode> episodes = podcast.getEpisodes();
      log.info(S_HAS_D_EPISODES, podcast.getTitle(), episodes.size());

      // List all episodes
      for (Episode episode : episodes) {
        System.out.println("- " + episode.getTitle());
        feedRepository.save(
            new Feed(
                episode.getTitle(),
                episode.getDescription(),
                20,
                episode.getPubDate().toString(),
                episode.getEnclosure().getURL().toString()));
      }
    };
  }
}

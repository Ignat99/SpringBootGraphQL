package com.ochiengolanga.tuts.bootgraphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class FeedMutation implements GraphQLMutationResolver {

    @Autowired
    private FeedService feedService;

    public Feed createFeed(final String title, final String description, final int itemCount, final String pubDate, final String image, final String guid, final String link) {
        return this.feedService.createOrUpdateFeed(title, description, itemCount, pubDate, image, guid, link);
    }
}

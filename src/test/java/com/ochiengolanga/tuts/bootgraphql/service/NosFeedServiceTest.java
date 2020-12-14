package com.ochiengolanga.tuts.bootgraphql.service;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import org.springframework.util.StopWatch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NosFeedServiceTest {

    @Mock
    private FeedRepository feedRepository;

    @InjectMocks
    private NosFeedService target;

    @Test
    public void createNosFeed() {
        final Feed nosFeed = Feed.builder().id(1L).title("Dummy").itemCount(3).guid("aaa").build();

        Feed persistedNosFeed = new Feed("Dummy", "bla bla bla", 3, "2020-12-12", "http://image", "aaa", "http://link");

        when(this.feedRepository.save(nosFeed)).thenReturn(persistedNosFeed);

        final Feed createdFeed = target.createOrUpdateFeed("Dummy", "bla bla bla", 3, "2020-12-12", "http://image", "bbb", "http://link");

        assertThat(nosFeed).isEqualTo(createdFeed);
    }

}

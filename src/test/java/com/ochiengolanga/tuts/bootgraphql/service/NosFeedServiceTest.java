package com.ochiengolanga.tuts.bootgraphql.service;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.interfaces.FeedService;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Example;
//import com.ochiengolanga.tuts.bootgraphql.utils.LogService;
import org.springframework.util.StopWatch;
//import com.ochiengolanga.tuts.bootgraphql.interfaces.FeedService;
//import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

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

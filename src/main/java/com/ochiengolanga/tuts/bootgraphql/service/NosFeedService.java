package com.ochiengolanga.tuts.bootgraphql.service;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Example;
import com.ochiengolanga.tuts.bootgraphql.utils.LogService;
import org.springframework.util.StopWatch;
import com.ochiengolanga.tuts.bootgraphql.interfaces.FeedService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NosFeedService extends LogService implements FeedService {

    public static final String RETRIEVE_RSS = "retrieving rss new form the external services";
    public static final String SAVE_RSS = "saving rss in db";

    @Autowired
    private final FeedRepository feedRepository ;

    public NosFeedService(final FeedRepository feedRepository) {
        this.feedRepository = feedRepository ;
    }

    @Transactional(readOnly = true)
    public boolean isExistsFeed(final Feed feed) {
        //The ExampleMatcher is immutable and can be static I think
        ExampleMatcher TITLE_MATCHER = ExampleMatcher.matching()
            .withIgnorePaths("id")
            .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.ignoreCase());
        Example<Feed> example = Example.<Feed>of(feed, TITLE_MATCHER);
        return (boolean) feedRepository.exists(example);
    }

    @Transactional
    public Feed createOrUpdateFeed(final String title,final String description, final int itemCount, final String pubDate, final String image, final String guid, final String link) {
        String methodName = "createOrUpdateFeed";
        logStart(methodName);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(RETRIEVE_RSS);
        final Feed feed = (Feed) new Feed(title, description, itemCount, pubDate, image, guid, link);
        logTaskInfoAndStop(methodName, stopWatch);

        if (this.isExistsFeed(feed)) {
            stopWatch.start(SAVE_RSS);
            logTaskInfoAndStop(methodName, stopWatch);
            logExit(methodName, feed);
            return feed;
        } else {
            stopWatch.start(SAVE_RSS);
            logTaskInfoAndStop(methodName, stopWatch);
            logExit(methodName, feed);
            return this.feedRepository.save(feed);
        }
    }

    @Transactional(readOnly = true)
    public List<Feed> getAllFeed(final Integer pageNo, final Integer pageSize, final String sortBy, final int count) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return this.feedRepository.findAll(paging).stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Feed> getAllFeeds(final Integer pageNo, final Integer pageSize, final String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return this.feedRepository.findAll(paging);
    }


    @Transactional(readOnly = true)
    public Optional<Feed> getFeed(final int id) {
        return this.feedRepository.findById((long) id);
    }

    @Transactional(readOnly = true)
    public long countFeeds() {
        return this.feedRepository.count();
    }

}

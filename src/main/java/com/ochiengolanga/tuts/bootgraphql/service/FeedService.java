package com.ochiengolanga.tuts.bootgraphql.service;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedService {

    private final FeedRepository feedRepository ;

    public FeedService(final FeedRepository feedRepository) {
        this.feedRepository = feedRepository ;
    }

    @Transactional
    public Feed createFeed(final String title,final String description, final int itemCount, final String pubDate, final String image) {
        final Feed feed = new Feed();
        feed.setTitle(title);
        feed.setDescription(description);
        feed.setItemCount(itemCount);
        feed.setPubDate(pubDate);
        feed.setImage(image);
        return this.feedRepository.save(feed);
    }

    @Transactional(readOnly = true)
    public List<Feed> getAllFeed(final int count) {
        return this.feedRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Iterable<Feed> getAllFeeds() {
        return this.feedRepository.findAll();
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

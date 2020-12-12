package com.ochiengolanga.tuts.bootgraphql.service;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;

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
        final Feed feed = (Feed) new Feed(title, description, itemCount, pubDate, image);
/*        feed.setTitle(title);
        feed.setDescription(description);
        feed.setItemCount(itemCount);
        feed.setPubDate(pubDate);
        feed.setImage(image); */
        return this.feedRepository.save(feed);
    }

    @Transactional(readOnly = true)
    public List<Feed> getAllFeed(final Integer pageNo, final Integer pageSize, final String sortBy, final int count) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return this.feedRepository.findAll(paging).stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<Feed> getAllFeeds() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("title"));
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

package com.ochiengolanga.tuts.bootgraphql.domain.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;
import java.net.URL;
import java.net.MalformedURLException;
import com.icosillion.podengine.exceptions.InvalidFeedException;
import com.icosillion.podengine.exceptions.MalformedFeedException;
import com.icosillion.podengine.utils.DateUtils;
import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;
import com.ochiengolanga.tuts.bootgraphql.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import com.icosillion.podengine.models.Podcast;
import com.icosillion.podengine.models.Episode;

@Component
@Slf4j
public class ScheduledTasks {

    @Autowired
    private FeedService feedService;

    @Scheduled(initialDelay=10000, fixedRate = 300000)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void checkRSS() throws InvalidFeedException, MalformedFeedException, MalformedURLException {

        System.out.printf("- Time to cheсk\n");
        //Get podcast
        Podcast podcast = new Podcast(new URL("http://feeds.nos.nl/nosjournaal?format=xml"));

        //Display Feed Details
        List<Episode> episodes = podcast.getEpisodes();
        System.out.printf("%s has %d episodes!\n", podcast.getTitle(), episodes.size());

        // List all episodes
        for (Episode episode : episodes) {
            System.out.println("- " + episode.getTitle());

            //Create Feeds
            this.feedService.createOrUpdateFeed(
                episode.getTitle(),
                episode.getDescription(),
                1,
                podcast.getPubDateString(),
                episode.getEnclosure().getURL().toString(),
                episode.getGUID()
            );
        }

    } //checkRSS
} //ScheduledTasks
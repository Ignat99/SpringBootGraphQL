package com.ochiengolanga.tuts.bootgraphql;

import com.ochiengolanga.tuts.bootgraphql.domain.Feed;
import com.ochiengolanga.tuts.bootgraphql.repository.FeedRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.net.URL;
import java.net.MalformedURLException;
import com.icosillion.podengine.exceptions.InvalidFeedException;
import com.icosillion.podengine.exceptions.MalformedFeedException;
import com.icosillion.podengine.models.Podcast;
import com.icosillion.podengine.models.Episode;

@Component
public class ScheduledTasks {

    @Scheduled(initialDelay=10000, fixedRate = 300000)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void checkRSS() {

        System.out.printf(" - Time to cheсk \n");
        try{
            //Get podcast
            URL myUrl = new URL("http://feeds.nos.nl/nosjournaal?format=xml");
            Podcast podcast = new Podcast(myUrl);

            //Display Feed Details
            List<Episode> episodes = podcast.getEpisodes();
            System.out.printf(" Time %s has %d episodes!\n", podcast.getTitle(), episodes.size());

        }catch(InvalidFeedException | MalformedFeedException | MalformedURLException ex){
            System.out.println("The url, feed, channel is not well formed: " + ex);
        }

    } //checkRSS
} //ScheduledTasks
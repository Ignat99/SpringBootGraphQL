package com.ochiengolanga.tuts.bootgraphql.domain.task;


import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ScheduledTasks {

    @Scheduled(initialDelay=10000, fixedRate = 300000)
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void checkRSS() {

            log.info(" - Time to cheсk \n");
        try{
            URL myUrl = new URL("http://feeds.nos.nl/nosjournaal?format=xml");
            Podcast podcast = new Podcast(myUrl);
            //Display Feed Details
            List<Episode> episodes = podcast.getEpisodes();
            log.info(" Time %s has %d episodes!\n", podcast.getTitle(), episodes.size());
        }catch(InvalidFeedException | MalformedFeedException | MalformedURLException ex){
            log.error("The url is not well formed: " + ex);
            log.error("Or the Feed is not well formed: " + ex);
            log.error("Or the channel is not well formed: " + ex);
        }

    } //checkRSS
} //ScheduledTasks
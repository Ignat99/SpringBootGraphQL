package com.ochiengolanga.tuts.bootgraphql.interfaces;

import com.ochiengolanga.tuts.bootgraphql.domain.entity.Feed;

public interface FeedService {

    Feed createOrUpdateFeed(String title, String description, int itemCount, String pubDate, String image, String guid, String link);
}

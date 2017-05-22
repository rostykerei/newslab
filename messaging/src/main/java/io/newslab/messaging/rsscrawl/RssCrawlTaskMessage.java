package io.newslab.messaging.rsscrawl;

import java.io.Serializable;

public class RssCrawlTaskMessage implements Serializable {

    private int feedId;

    public RssCrawlTaskMessage(int feedId) {
        this.feedId = feedId;
    }

    public int getFeedId() {
        return feedId;
    }
}
